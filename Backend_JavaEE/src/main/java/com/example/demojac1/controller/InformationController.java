package com.example.demojac1.controller;

import DAO.*;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

@WebServlet(name = "InformationController", value = "/InformationController")
public class InformationController extends HttpServlet {

    ArrayList<Docente> docente = new ArrayList<>();
    ArrayList<Corso> corso = new ArrayList<>();
    ArrayList<Ripetizione>ripetizione = new ArrayList<>();
    ArrayList<Associazione>associazione = new ArrayList<>();
    public void init() {

        //eseguo l'invocazione jdbc e ottengo i dati dal database mysql associato con DAO.registerDriver()
        DAO.registerDriver();
        this.docente = DAO.queryDB1();
        this.corso = DAO.queryDB2();
        this.ripetizione = DAO.queryDB5();
        this.associazione = DAO.queryDB0();
        System.out.println("\n++ InformationController :: DAO REGISTRATO");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");

        System.out.println("Action passata dalla request : " + action);
        int id;
        Gson gson = new Gson();
        String res;
        switch (action) {
            case "getUser":
                ArrayList<Utente> ris;
                ris = getUser();
                res = gson.toJson(ris);
                System.out.println("JSON passato : " + res);
                out.print(res);
                break;
            case "getPrenotazioni":
                ArrayList<Prenotazione> risp;
                id = Integer.parseInt(request.getParameter("id"));
                risp = getPrenotazioni(id);
                res = gson.toJson(risp);
                System.out.println("JSON passato : " + res);
                out.print(res);
                break;
            default:
                break;
        }
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public ArrayList<Utente> getUser() {

        ArrayList<Utente> utente;
        utente = DAO.queryDB3();

        //devo escludere i casi in cui user e' un admin (ruolo == 2)
        utente.removeIf(e -> e.getRuolo() == 2);
        utente.sort(Comparator.comparing(r -> r.getId())); //ordino le liste per id_utente
        System.out.println(utente);
        return utente;
    }

    public ArrayList<Prenotazione> getPrenotazioni(int id) {

        ArrayList<Prenotazione> prenotazione;
        prenotazione = DAO.queryDB4();
        prenotazione.removeIf(e -> e.getIdPrenUser() != id); //ottengo dalla lista solamente i valori relativi all'id utente

        for (Prenotazione p : prenotazione) {
            for(Ripetizione r: ripetizione) {
                if(r.getIdRip() == p.getIdPrenRipetizione()) {
                    p.setExecuted(r.getExecuted());
                    p.setStato(r.getExecuted());
                    p.setHour(r.getSlot());
                    p.setGiorno(r.getGiorno());
                    for(Associazione a: associazione) {
                        if(a.getIdAssociazione() == r.getIdAssociazione()) {
                            for(Docente d: docente) {
                                if(a.getIdAsDocente() == d.getId()) {
                                    p.setDocente(d.getNome() + " " + d.getCognome());
                                }
                            }
                            for(Corso c: corso) {
                                if(c.getID_materia() == a.getIdAsCorso()) {
                                    p.setCorso(c.getMateria());
                                }
                            }
                        }
                    }
                }
            }
        }
        int i;
        prenotazione.sort(Comparator.comparing(p -> p.getGiorno()));
        System.out.println("PRENOTAZIONE : " + prenotazione.toString());
        ArrayList<Prenotazione> ris = new ArrayList<>();

        // inverto la lista for descending order
        for(i = prenotazione.size()-1; i != -1; i--) {
            ris.add(prenotazione.get(i));
        }
        return ris;
    }

}