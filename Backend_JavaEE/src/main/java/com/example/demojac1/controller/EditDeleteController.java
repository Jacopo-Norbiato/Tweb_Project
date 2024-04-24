package com.example.demojac1.controller;

import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;

import DAO.*;
@WebServlet(name = "DeleteController", value = "/DeleteController")
public class EditDeleteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("\n++ EditDeleteController :: ");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String field = request.getParameter("value");
        ArrayList<Associazione> associazione;
        Gson gson = new Gson();
        String res, fin, materia;
        int esito, esito1, idDoc, idCorso = -1, idRip, idAss;

        switch (field) {
             case "docente":

                 System.out.println("@@@@@@@ Sono in DeleteController -> caso docente");
                 idDoc = Integer.parseInt(request.getParameter("idD"));
                 materia = request.getParameter("Corso");
                 ArrayList<Corso> corso = DAO.queryDB2();

                 //cerco id corso passato
                 for(Corso c: corso) {
                     if(c.getMateria().equals(materia)) {
                         idCorso = c.getID_materia();
                     }
                 }

                 associazione = DAO.queryDB0();
                 boolean success = true;

                 //verifico che al docente selezionato corrisponda il corso
                 for(Associazione a: associazione) {
                     if(success) {
                        if(a.getIdAsCorso() == idCorso && a.getIdAsDocente()==idDoc) {
                            idAss = a.getIdAssociazione();
                            //rimuovo la row di Associazione  collegata all'id Docente
                            esito1 = DAO.deleteAssociazione(idAss);
                            if(esito1 == 0) {
                                success = false;
                            }
                        }
                     }
                 }
                 if(success) {
                    esito = DAO.deleteDocente(idDoc);
                 } else {
                     esito = -1;
                 }

                 if(esito == 1 && success) {
                     res = "Operazione eseguita con successo!";
                 } else {
                     if (esito == 0) {
                         res = "Operazione non eseguita : errore nell'eliminazione dei dati Docente";

                     } else {
                         res = "Operazione non eseguita : errore nell'eliminazione dei dati Associazione";
                     }
                 }

                 fin = gson.toJson(res);
                 out.print(fin);
                 break;

             case "corso":

                 System.out.println("@@@@@@@ Sono in DeleteController -> caso corso");
                 idCorso = Integer.parseInt(request.getParameter("idC"));
                 associazione = DAO.queryDB0();
                 boolean ok = true;

                 //verifico
                 for(Associazione a: associazione) {
                     if(ok) {
                        if(a.getIdAsCorso() == idCorso) {
                            idAss = a.getIdAssociazione();
                            //rimuovo la row di Associazione collegata all'id Corso
                            esito1 = DAO.deleteAssociazione(idAss);
                            if(esito1 == 0) {
                                ok = false;
                            }
                        }
                     }
                 }
                 if(ok) {
                     esito = DAO.deleteCorso(idCorso);
                 } else {
                     esito = -1;
                 }

                 if(esito == 1 && ok) {
                     res = "Operazione eseguita con successo!";
                 } else {
                    if (esito == 0) {
                         res = "Operazione non eseguita : errore nell'eliminazione dei dati Corso";

                     } else {
                         res = "Operazione non eseguita : errore nell'eliminazione dei dati Associazione";
                     }
                 }
                 fin = gson.toJson(res);
                 out.print(fin);
                 break;

             case "rip":

                 System.out.println("@@@@@@@ Sono in DeleteController -> caso rip");

                 idRip = Integer.parseInt(request.getParameter("idR"));
                 int slot = Integer.parseInt(request.getParameter("time"));
                 String date = request.getParameter("giornata");
                 date = splitDate(date);

                 System.out.println("_______________Giorno passato in request : " + date);

                 // DEVO RIMUOVERE ANCHE LE PRENOTAZIONI A CUI E' ASSOCIATO L'ID DELLA RIPETIZIONE
                 // ALTRIMENTI HO UNA CONSTRAINT ERROR IN SQL
                 ArrayList<Prenotazione> prenotazione = DAO.queryDB4();

                 int outcome = 1;
                 for(Prenotazione p: prenotazione) {
                     if(p.getIdPrenRipetizione() == idRip) {
                         outcome = DAO.deletePrenotazione(p.getIdPrenotazione(), idRip, p.getIdPrenUser());
                     }
                 }

                 //QUINDI ORA ELIMINO LA RIPETIZIONE
                 esito = DAO.deleteRipetizione(idRip, date, slot);

                 if(esito == 1 && outcome == 1) {
                     res = "Operazione eseguita con successo!";
                 } else {
                     if(esito == 0) {
                         res = "Operazione non eseguita : i valori inseriti non sono corretti";
                     }
                     res = "Operazione non eseguita : i valori inseriti non sono corretti";
                 }
                 fin = gson.toJson(res);
                 out.print(fin);
                 break;
         }
         out.close();

    }

    public String splitDate(String s) {
        String[] array = s.split(" ");
        System.out.println(array.toString()); //{lug}, {20,}, {2023}

        String[] months = {"gen", "feb", "mar", "apr", "mag", "giu", "lug", "ago", "set", "ott", "nov", "dic"};
        int j = -1;
        for (int i = 0; i < months.length; i++) {
            if(array[0].equals(months[i])) {
               j = i+1;
            }
        }
        String res;
        if (j<10) {
             String digit = String.valueOf(j);
             String op = "0";
             String l = op+digit;
             res = array[2]+"-"+l+"-"+array[1];
        } else {
            res = array[2]+"-"+j+"-"+array[1];
        }
        res = res.substring(0, res.length()-1);
        return res;
    }
}
