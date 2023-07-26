package com.example.demojac1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletController", value = "/ServletController")
public class ServletController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("****** doGet di ServletController");
        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

                request.setCharacterEncoding("UTF-8");
                ServletContext ctx = getServletContext();
                String operation = request.getParameter("op");

                RequestDispatcher rd = ctx.getRequestDispatcher("/index.html");
                if(operation != null) {
                    switch(operation) {

                        case "login":
                            System.out.println("****** case home");
                            rd = ctx.getRequestDispatcher("/login.jsp");
                            break;
                        case "verifica":
                            System.out.println("****** case Verifica");
                            rd = ctx.getRequestDispatcher("/verifica.jsp");
                            break;
                        case "prova":
                            System.out.println("****** case Prova");
                            rd = ctx.getRequestDispatcher("/prova.jsp");
                            break;
                        default:
                            break;
                    }
                    rd.forward(request, response);
                }

    }
}
