package com.example.demojac1.controller;

import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;

import DAO.*;
@WebServlet(name = "EditController", value = "/EditController")
public class EditController extends HttpServlet {

    public void init() {
        System.out.println("\n");
        //eseguo l'invocazione jdbc e ottengo i dati dal database mysql associato con DAO.registerDriver()
        DAO.registerDriver();
        System.out.println("\n++ EditController :: DAO REGISTRATO");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        Gson gson = new Gson();
        String res;
        ArrayList<Docente>docente ;
        ArrayList<Corso>corso;
        ArrayList<Ripetizione>ripetizione;
        ArrayList<Associazione>associazione;
        if (action.equals("getDocenti")) {
            docente = DAO.queryDB1();
            corso = DAO.queryDB2();
            associazione = DAO.queryDB0();

            for (Docente d : docente) {
                for(Associazione a: associazione) {
                    if(a.getIdAsDocente() == d.getId()) {
                        for (Corso c : corso) {
                            if (a.getIdAsCorso() == c.getID_materia()) {
                                d.setCorso(c.getMateria());
                                System.out.println("\tDocente : " + d.getNome() + d.getCognome() + " Corso : " + c.getMateria());
                            }
                        }
                    }
                }
            }
            docente.sort(Comparator.comparing(doc -> doc.getId()));
            res = gson.toJson(docente);
            out.print(res);
            out.close();
        }

        if (action.equals("getCorsi")) {
            corso = DAO.queryDB2();
            corso.sort(Comparator.comparing(c -> c.getID_materia()));
            res = gson.toJson(corso);
            out.print(res);
            out.close();
        }

        if(action.equals("getRipetizioni")) {

            ripetizione = DAO.queryDB5();
            docente = DAO.queryDB1();
            corso = DAO.queryDB2();
            associazione = DAO.queryDB0();


            System.out.println("Qui in getRipetizioni");
            boolean check = false;
            for (Ripetizione r: ripetizione) {
                for(Associazione a: associazione) {
                    if(a.getIdAssociazione() == r.getIdAssociazione() && !check) {
                        for (Corso c : corso) {
                            if (a.getIdAsCorso() == c.getID_materia()) {
                                r.setCorso(c.getMateria());
                            }
                        }
                        for (Docente d : docente) {
                            if (a.getIdAsDocente() == d.getId()) {
                                r.setDocente(d.getNome() + " " + d.getCognome());
                            }
                        }
                        check = true;
                    }
                }
                check = false;
            }
            ripetizione.sort(Comparator.comparing(r -> r.getGiorno()));
            res = gson.toJson(ripetizione);
            out.print(res);
            out.close();
        }
        else {
            res = "Error";
            out.print(res);
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* Siccome il RequestDispatcher e' invocato dalla post di EditController,
        verro indirizzato sullo stesso metodo nella servlet selezionata dalla forward
         */

        String operation = request.getParameter("action");
        RequestDispatcher rd;
        ServletContext ctx;
        if (operation != null) {
            ctx = getServletContext();
            switch (operation) {
                case "ADD":
                    System.out.println("Sono in ADD");
                    rd = ctx.getNamedDispatcher("InsertController");
                    rd.forward(request, response);
                    break;
                case "DELETE":
                    System.out.println("Sono in DELETE");
                    rd = ctx.getNamedDispatcher("DeleteController");
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
