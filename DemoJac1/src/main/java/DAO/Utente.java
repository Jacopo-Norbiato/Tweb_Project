package DAO;

public class Utente {

    private String nome;
    private String cognome;
    private String passwd;
    private String username;
    private int ruolo;

    public Utente(String nome, String cognome, String username, String password, int ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.passwd = password;
        this.ruolo = ruolo;
    }


    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public int getRuolo() {
    return ruolo;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return nome + " " + cognome;
    }
}
