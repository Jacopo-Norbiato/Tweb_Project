package com.example.demojac1.controller;

import DAO.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.google.gson.Gson;


@WebServlet(name = "Login", value = "/Login")
public class LoginController extends HttpServlet {

    @Override
    public void init() throws ServletException {
        DAO.registerDriver();
        System.out.println("\n++ LoginController :: DAO REGISTRATO");
        }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();


        ArrayList<Utente> utente = DAO.queryDB3();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println(getClass().getName() + " / METODO doPost(), valori : " + username + "/" + password);

        HttpSession session = request.getSession();

        String jsessionID = session.getId(); /* vedere sweb-session-utente */
        session.setAttribute("username", username);


        boolean var = false;
        ArrayList<Integer> t = new ArrayList<>();
        for (Utente ut : utente) {
            if (ut.getUsername().equals(username)) {

                //Effettuo una verifica della password gia' encryptata nel DB e quella in chiaro passata dal frontend
                String encrypt = ut.getPasswd();
                if (Service.checkMD5(encrypt, password)) {
                    var = true;
                    t.add(ut.getId());
                    t.add(ut.getRuolo());
                } else {
                    var = false;
                    t.add(-1);
                    t.add(-1);
                }
            }
        }

        Gson json = new Gson();
        String s;


        //salvo id utente e il suo ruolo (user/admin) nella session così da poter ri-utilizzarli
        if (t.size() != 0 && var) {

            String id = Integer.toString(t.get(0));
            String role = Integer.toString(t.get(1));
            session.setAttribute("id", id);
            session.setAttribute("role", role);
            s = json.toJson(t);

        } else {
            t.add(-1);
            t.add(-1);
            s = json.toJson(t);
            out.print("Utente non riconosciuto");
        }
        System.out.println("Esito: " + var + " / valori passati : " + t);
        out.print(s);
        out.close();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}





