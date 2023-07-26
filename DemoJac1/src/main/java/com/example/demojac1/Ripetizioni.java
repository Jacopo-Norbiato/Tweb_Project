package com.example.demojac1;


import java.util.ArrayList;
import java.util.Date;

public class Ripetizioni {

    private int id_Materia;
    private String materia;
    private int id_Docente;
    private String nome;
    private String cognome;
    private Date day;
    private ArrayList<String> slots;

    public Ripetizioni(int idM, String Mat, int idD, String nomeD, String cognomeD, Date d, ArrayList<String>x) {
        this.id_Materia = idM;
        this.materia = Mat;
        this.id_Docente = idD;
        this.nome = nomeD;
        this.cognome = cognomeD;
        this.day = d;
        this.slots = x;
    }

    public int getId_Docente() {
        return id_Docente;
    }

    public int getId_Materia() {
        return id_Materia;
    }

    public String getMateria() {
        return materia;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public Date getDay() {
        return day;
    }

    public ArrayList<String> getSlots() {
        return slots;
    }

    public void setId_Materia(int id_Materia) {
        this.id_Materia = id_Materia;
    }

    public void setId_Docente(int id_Docente) {
        this.id_Docente = id_Docente;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDay(Date day) {
        this.day = day;
    }


    public void setSlots(ArrayList<String> slots) {
        this.slots = slots;
    }
}

