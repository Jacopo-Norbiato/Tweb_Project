package DAO;

import java.util.Date;


public class Ripetizione {

    private int idRip;
    private int idAssociazione;
    private Date giorno;
    private int slot;
    private int executed;
    public String corso;
    public String docente;
    public String hour;
    public String stato;
    public Ripetizione(int idRip, int idAss, Date d, int slot, int ex) {
        this.idRip = idRip;
        this.idAssociazione = idAss;
        this.giorno = d;
        this.slot = slot;
        this.executed = ex;
        this.hour = setHour(slot);
        this.stato = setStato(ex);
    }

    public int getIdRip() {
        return idRip;
    }

    public int getIdAssociazione() {
        return idAssociazione;
    }

    public int getExecuted() {
        return executed;
    }
    public int getSlot() {
        return this.slot;
    }

    public String getGiorno() {

        String s = this.giorno.toString();
        return s;
    }


    public String getCorso() {
        return this.corso;
    }

    public void setCorso(String corso) {
        this.corso = corso;
    }

    public String getDocente() {
        return this.docente;
    }
    public void setDocente(String docente) {
        this.docente = docente;
    }

    public String getHour() {
        return this.hour;
    }
    public String setHour(int slot) {
        if(slot == 1) {
            return "15.00 - 16.00";
        } else if (slot==2) {
            return "16.00 - 17.00";
        } else if (slot==3) {
            return "17.00 - 18.00";
        } else if (slot==4) {
            return "18.00 - 19.00";
        } else {
            return  null;
        }
    }
    public String setStato(int v) {

        switch (v) {
            case 0:
                return "Attiva";
            case 1:
                return "Effettuata";
            case 2:
                return "Disdetta";
            default:
                return "";
        }
    }
    public String getStato() {
        return this.stato;
    }
}

