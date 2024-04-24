package DAO;

public class Associazione {

    private int idAssociazione;
    private int idAsDocente;
    private int idAsCorso;
    public String docente;
    public String corso;

    public Associazione(int a, int b, int c) {
        this.idAssociazione = a;
        this.idAsCorso = b;
        this.idAsDocente = c;
    }

    public int getIdAssociazione() {
        return idAssociazione;
    }

    public int getIdAsCorso() {
        return idAsCorso;
    }

    public int getIdAsDocente() {
        return idAsDocente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public void setCorso(String corso) {
        this.corso = corso;
    }

    public String getDocente() {
        return docente;
    }

    public String getCorso() {
        return corso;
    }
}
