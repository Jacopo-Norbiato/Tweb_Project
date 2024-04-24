package DAO;

import java.util.Date;
public class Prenotazione {

    private int idPrenotazione;
    private int idPrenRipetizione;
    private int idPrenUser;
    private int idPrenAss;
    public String giorno;
    public String hour;
    public int executed;

    public String corso;
    public String docente;
    public String stato;

    public Prenotazione (int pren, int prenR, int prenU, int prenA) {
        this.idPrenotazione = pren;
        this.idPrenRipetizione = prenR;
        this.idPrenUser = prenU;
        this.idPrenAss = prenA;
    }

    public int getIdPrenRipetizione() {
        return idPrenRipetizione;
    }

    public int getIdPrenUser() {
        return idPrenUser;
    }
    public int getIdPrenotazione() {
        return idPrenotazione;
    }

    public int getExecuted() {
        return executed;
    }

    public void setExecuted(int executed) {
        this.executed = executed;
    }

    public int getIdPrenAss() {
        return idPrenAss;
    }

    public String getGiorno() {
        return giorno.toString();
    }

    public void setGiorno(String giorno) {
        this.giorno = giorno;
    }


    public void setHour(int slot) {
        if(slot == 1) {
            this.hour = "15.00 - 16.00";
        } else if (slot==2) {
            this.hour = "16.00 - 17.00";
        } else if (slot==3) {
            this.hour = "17.00 - 18.00";
        } else if (slot==4) {
            this.hour = "18.00 - 19.00";
        } else {
            this.hour = "error";
        }
    }
    public String getHour() {
        return hour;
    }

    public String getStato() {
        return this.stato;
    }

    public void setStato(int v) {

        switch (v) {
            case 0:
                this.stato= "Attiva";
                break;
            case 1:
                this.stato= "Effettuata";
                break;
            case 2:
                this.stato= "Disdetta";
                break;
             default:
                this.stato= "";
                break;
        }
    }

    public void setCorso(String corso) {
        this.corso = corso;
    }

    public String getCorso() {
        return corso;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }
}
