package com.example.demojac1.controller;

import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import DAO.*;

@WebServlet(name = "InsertController", value = "/InsertController")
public class EditInsertController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("\n++ EditInsertController :: ");

         response.setContentType("application/json");
         PrintWriter out = response.getWriter();
         Gson gson = new Gson();
         String res, fin;
         int esito, esito1, idDoc;

         String field = request.getParameter("value"); //recupero il parametro che mi permette di fare switch() tra i case

         switch (field) {
             case "docente":

                 ArrayList<Associazione> associazione = DAO.queryDB0();

                 System.out.println("@@@@@@@ Sono in InsertController -> caso docente");
                 idDoc = Integer.parseInt(request.getParameter("idD"));
                 String name = request.getParameter("name");
                 String surname = request.getParameter("surname");
                 int idCorso = Integer.parseInt(request.getParameter("idC"));
                 int lastA = associazione.get(associazione.size()-1).getIdAssociazione();

                 esito = DAO.addDocente(idDoc, name, surname);
                 esito1 = DAO.addAssociazione(lastA, idDoc, idCorso);

                 if(esito == 1 && esito1 == 1) {
                     res = "Operazione eseguita con successo!";
                 } else {
                     if(esito == 0) {
                         res = "Operazione non eseguita : errore nell'inserimento della Ripetizione";
                     } else {
                         res = "Operazione non eseguita : errore nell'inserimento dell'Associazione";
                     }
                 }
                 fin = gson.toJson(res);
                 out.print(fin);
                 break;

             case "corso":
                 System.out.println("@@@@@@@ Sono in InsertController -> caso corso");
                 idCorso = Integer.parseInt(request.getParameter("idC"));
                 String materia = request.getParameter("materia");
                 esito = DAO.addCorso(idCorso, materia);

                 if(esito == 1) {
                     res = "Operazione eseguita con successo!";
                 } else {
                     res = "Operazione non eseguita : i valori inseriti non sono corretti";
                 }
                 fin = gson.toJson(res);
                 out.print(fin);
                 break;

             case "rip":
                 System.out.println("@@@@@@@ Sono in InsertController -> caso rip");
                 idDoc = Integer.parseInt(request.getParameter("idD"));
                 idCorso = Integer.parseInt(request.getParameter("idC"));
                 int slot = Integer.parseInt(request.getParameter("slot"));
                 String date = request.getParameter("day");

                 int idAss = -1, lastAss;
                 ArrayList<Associazione> ass = DAO.queryDB0();

                 for(Associazione a: ass) {
                     if(a.getIdAsDocente() == idDoc && a.getIdAsCorso() == idAss) {
                         idAss = a.getIdAssociazione();
                     }
                 }
                 lastAss = ass.get(ass.size()-1).getIdAssociazione()+1;

                 ArrayList<Ripetizione>rip = DAO.queryDB5();
                 int lastRip = rip.get(rip.size()-1).getIdRip()+1;


                 System.out.println("valori passati : " + idDoc + idCorso + slot + date);

                  esito = DAO.addRipetizione(lastRip, idAss, date, slot, 0);
                  esito1 = DAO.addAssociazione(lastAss, idDoc, idCorso);

                  // boolean free = checkAvailable(idDoc, idCorso, slot, date); //verifico la disponibilita del docente nella data e slot inseriti

                 if(esito == 1 && esito1 == 1) {
                        res = "Operazione eseguita con successo!";
                    } else {
                     res = "Operazione non eseguita : i valori inseriti non sono corretti";
                 }

                 fin = gson.toJson(res);
                 out.print(fin);
                 break;
            }
         out.close();
         System.out.println("\n");
    }
}
