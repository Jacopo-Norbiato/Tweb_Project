package com.example.demojac1.controller;

import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import DAO.DAO;
import DAO.Prenotazione;
@WebServlet(name = "AgendaCheckController", value = "/AgendaCheckController")
public class AgendaCheckController extends HttpServlet {

    public void init() {

        //eseguo l'invocazione jdbc e ottengo i dati dal database mysql associato con DAO.registerDriver()
        DAO.registerDriver();
        System.out.println("\n++ AgendaCheckController :: DAO REGISTRATO");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        System.out.println("\t\t qui");
        int idRip = Integer.parseInt(request.getParameter("idPrenRip"));
        System.out.println("\t\t qui");
        int idPrenUser = Integer.parseInt(request.getParameter("idPrenUser"));
        System.out.println("\t\t qui");
        String esito;
        int res;
        System.out.println("\t\tValori passati :" + idRip + " " + idPrenUser);
        res = DAO.setRipetizione(idRip, 1);
        System.out.println("\t\tQui dopo setRipetizione");

        if(res==1) { //prenotazione disdetta -> la ripetizione torna disponibile
                        esito = "Operazione eseguita con successo.";
        } else {

            esito = "ERROR: si è verificato un problema nell'aggiornamento del Calendario.";
        }

        System.out.println("\n -----//// ESITO OPERAZIONE: " + esito + " ////-----");
        out.print(gson.toJson(esito));
        out.close();
    }
}
