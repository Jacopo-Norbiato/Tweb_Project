package com.example.demojac1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import DAO.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;

@WebServlet(name = "Verifica", value = "/Verifica")
public class Verifica extends HttpServlet {
   public void init() throws ServletException {
        DAO.registerDriver();
        System.out.println("Servlet Verifica, init() executed");
    }

    /*
        ATTENZIONE AL CONTENT TYPE!!
        Non puo' essere html perche' il content type ci dice
        quali contenuti e di che tipo stiamo spedendo. Normalmente e' settato
        a file xml/html
        SE DOBBIAMO SPEDIRE OGGETTI JSON --> setContentType("application/json");
        JSON CI SERVE PER SPEDIRE DATI NELL'INTERAZIONE CLIENT-SERVER!!
        */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        System.out.println("QUI");
        ArrayList<Docente> doc = DAO.queryDB1();
        System.out.println("Dopo DAO query");

        Gson gson = new Gson();
        String s = gson.toJson(doc);

        System.out.println("String JSON: "+ s);
        out.print(s);
        //response.sendRedirect(getServletContext().getContextPath()));
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
