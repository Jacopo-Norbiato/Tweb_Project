package com.example.demojac1;

import java.io.*;
import DAO.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private static String Uname, Password, Role;
    private static String Name, Surname;

    public void init() {
        //eseguo l'invocazione jdbc e ottengo i dati dal database mysql associato con DAO.registerDriver()
        DAO.registerDriver();
        System.out.println("DAO REGISTRATO");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession s = request.getSession();
        String report = "";
        String Jsession = s.getId();
        Uname = request.getParameter("username");
        Password = request.getParameter("pw");
        System.out.println("QUAA 1 " + Uname + "/" + Password + " Jsession : " + Jsession);

        try {
            if(Uname != null && Password != null) {
                s.setAttribute("Username", Uname);
                s.setAttribute("Pw", Password);
                ArrayList<Utente> utenti = DAO.queryDB3(); // carico gli utenti del jdbc
               for (Utente t : utenti) {
                    if (t.getUsername().equals(s.getAttribute("Username")) && t.getPasswd().equals(s.getAttribute("Pw"))) {
                        Name = String.valueOf(t.getNome());
                        Surname = String.valueOf(t.getCognome());
                        Role = String.valueOf(t.getRuolo());

                        s.setAttribute("Name", Name);
                        s.setAttribute("Surname", Surname);
                        s.setAttribute("Role", Role);
                        report = "Login eseguito correttamente";
                        System.out.println("OKAYYYYY");
                        }
                }
               if (Role == null) {
                   report = "Error in login, check the values in the fields";
                   response.setContentType("text/plain");
                   out.println(" " + report);
               } else {
                   response.setContentType("text/plain");
                   out.println(" " + report);
                   //processRequest(request, response);
               }
                out.flush();
            } else {
                System.out.println("Error with user's name&password");
            }
        } finally {
            out.close();
        }
    }

    public void destroy() {
        //la destroy() per ora e' vuota siccome non vi sono cookie o tracking session attivi al momento
    }
       private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=UTF-8");

           ArrayList<Docente> docenti = DAO.queryDB1();
           System.out.println("_____________ Registrati docenti");
           ArrayList<Corso> corsi = DAO.queryDB2();
           System.out.println("_____________ Registrati corsi");
           ArrayList<Prenotazione> prenot = DAO.queryDB4();
           System.out.println("_____________ Registrati prenotazioni");
           int [] array = new int[prenot.size()];
           int j,k;
           for(j = 0; j < array.length; j++) { //inizializzo array per stampare correttamente le prenotazioni in DB
            array[j] = 0;
           }

           HttpSession s = request.getSession();
           request.setCharacterEncoding("UTF-8");
           String url = response.encodeURL("hello-servlet"); //faccio encode di un url -> stesso concetto dei cookie
           String azione = request.getParameter("action");
           PrintWriter out = response.getWriter();
               try {
                   out.println("<!DOCTYPE html>");
                   out.println("<html>");
                   out.println("<head>");
                   out.println("<title>Servlet NewServlet</title>");
                   out.println("<link rel=stylesheet href=style.css>");
                   out.println("</head>");
                   out.println("<body>");
                   out.println("<p>Sei collegato come: " + s.getAttribute("Name") + " " + s.getAttribute("Surname") + "</p>"); //cerco Name e Surname nella Session s

                   if (azione!=null && azione.equals("invalida")) {
                           System.out.println("QUI");
                           s.invalidate();
                           response.sendRedirect("index1.html");
                   }
                   if (s.getAttribute("Role").equals("1")) {
                       out.println("<article>");
                       out.println("<h2>Servlet NewServlet at " + request.getContextPath() + "</h2>");
                       out.println("<div class = ripetizioni>");
                       out.println("<h3>Prenotazioni nel JDBC: </h3>");
                       out.println("<table align=center>");
                       out.println("<caption> RIPETIZIONI </caption>");
                       out.println("<tr><td>(id) Corso</td><td>(id) Docente</td><td>Data</td><td>Orario</td></tr>");
                       j = 0;
                       for (Prenotazione p : prenot) {
                           out.println("<tr>");
                           if(array[j]==0) {
                               for (Corso c : corsi) {
                                   if (p.getId_corso() == c.getID_materia()) {
                                       out.println("<td>" + c.toString() + "</td>");
                                   }
                               }
                               for (Docente d : docenti) {
                                   if(p.getId_docente() == d.getId()) {
                                       out.println("<td>" + d.toString() + "</td>");
                                   }
                               }
                               out.println("<td>" + p.getReservation() + "</td>");
                               out.print("<td>" + p.getSlots());
                           }
                           array[j]=1;
                           k = 0;
                           for(Prenotazione t: prenot) {
                               if(p.getReservation().equals(t.getReservation()) && array[k]!=1) { //stessa data e p!=t
                                   if(p.getSlots() != t.getSlots() && p.getId_docente() == t.getId_docente()) {
                                   out.print(", " + t.getSlots());
                                   array[k]=1;
                                   }
                               }
                               k++;
                           }
                       out.println("</td>");
                       out.println("</tr>");
                       j++;
                       }
                       out.println("</article");
                       out.println("</table>");
                       out.println("<p>Invalida <a href=\"" + url + "?action=invalida\"> la sessione</a></p>");
                       } else { //s.getAttribute("Role") != 1
                       out.println("<h3>Mi spiace, non hai accesso a questo tipo di informazioni </h3>");
                       s.invalidate();
                       out.println("<p>Sessione invalidata! Premere il pulsante per tornare a index</p>");
                       out.println("<form action=\"index1.html\">"
                        + "<input type=\"submit\" name=\"submit\" value=\"Refresh\"/></p>"
                        + "</form> </p>");
                   }
                   out.println("</body>");
                   out.println("</html>");
                } finally {
                    out.close();
               }
            }
}