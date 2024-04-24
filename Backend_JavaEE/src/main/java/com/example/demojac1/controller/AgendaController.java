package com.example.demojac1.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import DAO.*;
import com.google.gson.Gson;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;


@WebServlet(name = "AgendaController", value = "/AgendaController")
public class AgendaController extends HttpServlet {
    @Override
   public void init() {
        //eseguo l'invocazione jdbc e ottengo i dati dal database mysql associato con DAO.registerDriver()
        DAO.registerDriver();

        System.out.println("\n++ AgendaController :: DAO REGISTRATO");
        ArrayList<Ripetizione> rip = DAO.queryDB5();
        LocalDate selectedDate, today = LocalDate.now();
        for (Ripetizione r: rip) {
            int esito;
            selectedDate = LocalDate.parse(r.getGiorno());
            if (selectedDate.isBefore(today)) {
                //Modifico lo stato della ripetizione scaduta (not today)
                esito = DAO.setRipetizione(r.getIdRip(), 2);
                if (esito != 1) {
                    System.out.println("An error occurred while updating the values compared to the current date");
                }
            }
        }
        System.out.println("++ AgendaController :: Tabelle Ripetizioni e Prenotazioni aggiornato\n");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        System.out.println("------------");

        int idU = Integer.parseInt(request.getParameter("id"));
        System.out.println("\tVALORE INVIATO : " + idU);
        ArrayList<Prenotazione>prenotazioni = DAO.queryDB4();
        ArrayList<Prenotazione> ris = new ArrayList<>();
        ArrayList<Ripetizione> rip = DAO.queryDB5();

        for(Prenotazione p: prenotazioni) {
                 for (Ripetizione r: rip) {
                     if(r.getIdRip() == p.getIdPrenRipetizione()) {
                        p.setExecuted(r.getExecuted());
                        p.setGiorno(r.getGiorno());
                        p.setStato(r.getExecuted());
                        p.setHour(r.getSlot());
                    }
                }
                if(p.getIdPrenUser() == idU) {
                ris.add(p);
                System.out.println("\tAGGIUNTA");
            }
        }
        ris.sort(Comparator.comparing(r -> r.getGiorno()));
        System.out.println("\nDopo sort();");
        if(ris.size()>0) {
            System.out.println("\tQUI");
            ArrayList<Corso>corso = DAO.queryDB2();
            ArrayList<Docente>docente = DAO.queryDB1();
            ArrayList<Associazione> associazione = DAO.queryDB0();

            for (Prenotazione p: ris) {
                for(Associazione a : associazione) {
                    if(p.getIdPrenAss() == a.getIdAssociazione()) {
                        for (Corso c: corso) {
                            if(a.getIdAsCorso() == c.getID_materia()) {
                                p.setCorso(c.getMateria());
                            }
                        }
                        for (Docente d: docente) {
                            if(a.getIdAsDocente() == d.getId()) {
                                p.setDocente(d.getNome() + " " + d.getCognome());
                            }
                        }
                    }
                }
            }
        }

        Gson gson = new Gson();
        String s = gson.toJson(ris);
        System.out.println("\n +++ get() in AgendaController - JSON : " + s);
        out.print(s);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String operation = request.getParameter("action");
        RequestDispatcher rd;
        ServletContext ctx;
        if (operation != null) {
            ctx = getServletContext();
            switch (operation) {
                case "REMOVE":
                    System.out.println("Sono in REMOVE");
                    rd = ctx.getNamedDispatcher("AgendaRemoveController");
                    rd.forward(request, response);
                    break;
                case "CHECK":
                    System.out.println("Sono in CHECK");
                    rd = ctx.getNamedDispatcher("AgendaCheckController");
                    rd.forward(request, response);
                    break;
            }
        } else {
            Gson gson = new Gson();
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            String res = "Warning! RequestDispatcher didn't work correctly!";
            String s = gson.toJson(res);
            out.print(s);
        }
    }

}
