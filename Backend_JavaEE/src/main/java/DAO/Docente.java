package DAO;

import DAO.Prenotazione;

import java.util.ArrayList;
import java.util.List;

public class Docente {

    private int id;
    private String nome;
    private String cognome;
    public String corso = " ";

    public Docente(int id, String nome, String cognome) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }


    @Override
    public String toString() {
        return "(" + id + ") " + nome + " " + cognome;
    }

    public String getCorsi() {
        return corso;
    }

    public void setCorso(String corsi) {
        this.corso += corsi + " . " ;
    }
}
