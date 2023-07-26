package DAO;

public class Docente {

    private int id;
    private String nome;
    private String cognome;
    private int id_Corso;

    public Docente(int id, String nome, String cognome, int corso) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.id_Corso = corso;
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

    public int getId_Corso() {
        return id_Corso;
    }

    @Override
    public String toString() {
        return "(" + id + ") " + nome + " " + cognome;
    }

}
