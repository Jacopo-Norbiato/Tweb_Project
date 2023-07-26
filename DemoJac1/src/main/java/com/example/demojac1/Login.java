package com.example.demojac1;

import DAO.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;

/*
importante!!!

Vedere dependency gson in pom.xml
 */
@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {

    @Override
    public void init() throws ServletException {
        DAO.registerDriver();
        System.out.println("Servlet Login, init() executed");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

         ArrayList<Docente> doc = DAO.queryDB1();
        ArrayList<Corso> corsi = DAO.queryDB2();
        ArrayList<Prenotazione> prenot = DAO.queryDB4();
        int [] array = new int [prenot.size()];
        int j,k;

        for(j=0;j< array.length;j++) {
            array[j]=0;
        }
         j=0;
        ArrayList<Ripetizioni> ris = new ArrayList<>();

        for (Prenotazione p: prenot) {
            Ripetizioni r = new Ripetizioni(0, "", 0, "", "", null, null);
            ArrayList<String> turni = new ArrayList<>();
            if(array[j]==0) {
                for(Corso c: corsi) {
                    if(p.getId_corso()  == c.getID_materia()) {
                        r.setId_Materia(c.getID_materia());
                        r.setMateria(c.getMateria());
                    }
                    for (Docente d: doc) {
                        if(p.getId_docente() == d.getId()) {
                            r.setId_Docente(d.getId());
                            r.setNome(d.getNome());
                            r.setCognome(d.getCognome());
                        }
                    }
                    r.setDay(p.getReservation());
                }
            }
            array[j]=1;
            k = 0;
            for(Prenotazione t:prenot) {
                if(p.getReservation().equals(t.getReservation()) && array[k]!=1) {
                    if(p.getSlots() != t.getSlots() && p.getId_docente() == t.getId_docente()) {
                       turni.add(t.getSlots());
                        array[k]=1;
                    }
                }
                k++;
            }
            r.setSlots(turni);
            if(r.getId_Materia() != 0) {
                ris.add(r);
            }
            j++;
        }
        Gson gson = new Gson();
        String s = gson.toJson(ris);

        System.out.println("String JSON: "+ s);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
