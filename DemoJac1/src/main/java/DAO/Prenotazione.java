package DAO;

import java.util.ArrayList;
import java.util.Date;
public class Prenotazione {

    private int id_utente;
    private int id_docente;
    private int id_corso;
    private Date reservation;
    private int hours;

    private ArrayList<ArrayList<Integer>> times = new ArrayList<>(5);

    public Prenotazione (int utente, int docente, int corso, Date d, int slot) {
        this.id_utente = utente;
        this.id_docente = docente;
        this.id_corso = corso;
        this.reservation = d;
        this.hours = slot;
    }
    public int getId_utente() {
        return id_utente;
    }
    public int getId_docente() {
        return id_docente;
    }
    public int getId_corso() {
        return id_corso;
    }
    public Date getReservation() {return reservation;}

    /*
    insertSlots() --> per creare un array contenente gli slot
    identificati da id_docente, id_corso, id_utente e reservation
    */
    public String getSlots() {
        switch (hours) {
                case 1:
                    return "15.00-16.00";
                case 2:
                    return "16.00-17.00";
                case 3:
                    return "17.00-18.00";
                case 4:
                    return "18.00-19.00";
                default:
                    return "";
        }
    }
    public String toString() {

        return "utente " + id_utente + ", docente " + id_docente + ", corso " + id_corso + " - data : " + reservation + " - slot : " + hours;
    }
}
