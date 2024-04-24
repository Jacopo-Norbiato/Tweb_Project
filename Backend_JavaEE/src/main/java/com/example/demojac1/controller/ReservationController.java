package com.example.demojac1.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Comparator;

import DAO.*;
import com.google.gson.Gson;


@WebServlet(name = "ReservationController", value = "/ReservationController")
public class ReservationController extends HttpServlet {


    public void init() {

        //eseguo l'invocazione jdbc e ottengo i dati dal database mysql associato con DAO.registerDriver()
        DAO.registerDriver();
        System.out.println("\n++ ReservationController :: DAO REGISTRATO");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        ArrayList<Ripetizione> ripetizioni= DAO.queryDB5();
        ArrayList<Prenotazione>prenotazione = DAO.queryDB4();

        String selectedDay = request.getParameter("datePicked");
        System.out.println("++ data selezionata PRIMA: "+ selectedDay);
        selectedDay = formatDate(selectedDay);
        System.out.println("++ data selezionata DOPO: "+ selectedDay);

        // inserisco gli attributi public di Ripetizione
        operation(ripetizioni);

        //inizializzo array Object da tradurre in json
        ArrayList<Ripetizione> risultato = new ArrayList<>();
        for (Ripetizione r: ripetizioni) {
            if(r.getGiorno().equals(selectedDay)) {
                //SELEZIONO SOLO LE RIPETIZIONI ATTIVE (0)
                if(r.getExecuted() == 0) {
                    risultato.add(r);
                }
            }
        }
        for(Prenotazione p: prenotazione) {
            //se id ripetizione non corrisponde a qualche id in prenotazione
            risultato.removeIf(e -> e.getIdRip() == p.getIdPrenRipetizione());
        }
        risultato.sort(Comparator.comparing(r -> r.getGiorno())); //ordino la lista individuata per giorno

        Gson gson = new Gson();
        String s = gson.toJson(risultato);
        System.out.println("\n +++ get() in ReservationController - JSON : " + s);
        out.print(s);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        int idUser = Integer.parseInt(request.getParameter("idUser"));
        int idRip = Integer.parseInt(request.getParameter("idRipetizione"));
        int idAss = Integer.parseInt(request.getParameter("idAssociazione"));

        /* qua devo fare:
            1- inserire la ripetizione come prenotazione (aggiungo user_id agli attributi ripetizioni)
            2- rimuovere la ripetizione dalla lista ripetizioni poiche' non piu' disponibile
         */

        String esito;
        int res, res1;
        Gson gson = new Gson();
        ArrayList<Prenotazione> prenotaziones = DAO.queryDB4();
       int last;
       if(prenotaziones.size() > 0) {
           last = prenotaziones.get(prenotaziones.size()-1).getIdPrenotazione()+1;
       } else {
           last = 0; //prima prenotazione da aggiungere
       }
        System.out.println("\t Values di Prenotazione da inserire : " + last + idRip + idUser + idAss);
        res = DAO.addPrenotazione(last, idRip, idUser, idAss);

        if(res==1) {
            esito = "Operazione eseguita con successo.";
        } else {
            esito = "ERROR: a problem occured while inserting values in PRENOTAZIONE table";
        }
        System.out.println("\n -----//// ESITO OPERAZIONE: " + esito + " ////-----");
        out.print(gson.toJson(esito));
        out.close();
    }

    public String formatDate(String date) {

        String [] array = date.split("-");
        String res = "" ;
        for(int i = 0; i < array.length; i++) {
            if(array[i].length() < 2) {
                array[i] = addCharToString(array[i], '0', 0);
            }
            res = res.concat(array[i]);
            if(i != array.length-1) {
                res = res.concat("-");
            }
        }
        return res;
    }

    // metodo che mi permette di avere le date corrette anche per i mesi singoli (ad es. 2023-04-25 invece che 2023-4-25);
    public String addCharToString(String s, char c, int pos) {
            StringBuilder stringBuffer = new StringBuilder(s);
            stringBuffer.insert(pos, c);
            return stringBuffer.toString();
    }

    public int getSlot(String s) {

        int value = 0;
        switch(s){
            case "15.00 - 16.00":
                value = 1;
                break;
            case "16.00 - 17.00":
                value = 2;
                break;
            case "17.00 - 18.00":
                value = 3;
                break;
            case "18.00 - 19.00":
                value = 4;
                break;
        }
        return value;
    }
    public void operation (ArrayList<Ripetizione>ripetizioni) {

        ArrayList<Associazione>associazione = DAO.queryDB0();
        ArrayList<Corso>corsi = DAO.queryDB2();
        ArrayList<Docente>docenti = DAO.queryDB1();

        for(Ripetizione r: ripetizioni) {
            for(Associazione a: associazione) {
                if(a.getIdAssociazione() == r.getIdAssociazione()) {
                    for(Corso c: corsi) {
                        if(c.getID_materia() == a.getIdAsCorso()) {
                            r.setCorso(c.getMateria());
                        }
                    }
                    for (Docente d: docenti) {
                        if(d.getId()==a.getIdAsDocente()) {
                            r.setDocente(d.getNome() + " " + d.getCognome());
                        }
                    }
                }
            }
        }
    }
}
