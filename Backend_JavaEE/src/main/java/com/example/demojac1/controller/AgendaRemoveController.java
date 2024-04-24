package com.example.demojac1.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

import DAO.*;
import com.google.gson.Gson;
@WebServlet(name = "AgendaRemoveController", value = "/AgendaRemoveController")
public class AgendaRemoveController extends HttpServlet {
     public void init() {
        //eseguo l'invocazione jdbc e ottengo i dati dal database mysql associato con DAO.registerDriver()
        DAO.registerDriver();
        System.out.println("\n++ AgendaRemoveController :: DAO REGISTRATO");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        int idPren = Integer.parseInt(request.getParameter("idPren"));
        int idRip = Integer.parseInt(request.getParameter("idPrenRip"));
        int idPrenUser = Integer.parseInt(request.getParameter("idPrenUser"));

        String esito;
        int res, res1;
        Gson gson = new Gson();

        //elimino la prenotazione associata
        //non faccio altro -> la ripetizione è già in stato attivo

        res = DAO.deletePrenotazione(idPren, idRip, idPrenUser);
        System.out.println("\t\tqui dopo deletePrenotazione");
        if(res == 1) {
         esito = "Operazione eseguita con successo.";
        } else {
            esito = "ERROR: a problem occured while deleting values from PRENOTAZIONE table";
        }
        System.out.println("\n -----//// ESITO OPERAZIONE: " + esito + " ////-----");
        out.print(gson.toJson(esito));
        out.close();

    }
}

