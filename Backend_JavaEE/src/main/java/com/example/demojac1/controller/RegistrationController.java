package com.example.demojac1.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import DAO.DAO;
import DAO.*;
import com.google.gson.Gson;

@WebServlet(name = "RegistrationController", value = "/RegistrationController")
public class RegistrationController extends HttpServlet {

    @Override
    public void init() throws ServletException {
        DAO.registerDriver();
        System.out.println("\n++ RegistrationController :: DAO REGISTRATO");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id, role, result;
        String name, surname, password, username;

        name = request.getParameter("name");
        surname = request.getParameter("surname");
        username = request.getParameter("us_name");
        password = request.getParameter("password");

        ArrayList<Utente> utente = DAO.queryDB3();

       //encrypto la password da salvare nel Database!
        password = Service.encryptMD5(password);

        //client registrato, ruolo da studente == 1, admin == 2
        id = utente.get(utente.size()-1).getId()+1;

        role = 1; //di default il cliente registrato e' uno studente
        result = DAO.addUser(id, name, surname, password, username, role);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String esito;
        if(result == 1) {
            esito = "Registrato correttamente !";
        } else {
            esito = "L'operazione non e' andata a buon fine";
        }
        System.out.println("Esito: " + esito);
        Gson gson = new Gson();
        String s = gson.toJson(esito);
        out.print(s); //passo la risposta come response json all'interfaccia utente!
    }

}
