package DAO;

public class Utente {

    private final int id;
    private final String nome;
    private final String cognome;
    private final String username;
    private final String passwd;
    private final int ruolo;
    public Utente(int id, String nome, String cognome, String username, String password, int ruolo) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.passwd = password;
        this.ruolo = ruolo;
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
        return id + " " + nome + " " + cognome + " " + username + ", ruolo: " + ruolo ;
    }
}
