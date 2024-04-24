package DAO;

public class Corso {

    private int ID_materia;
    private String Materia;

    public Corso(int id, String materia) {
        this.ID_materia = id;
        this.Materia = materia;
    }

    public int getID_materia() {
        return ID_materia;
    }

    public String getMateria() {
        return Materia;
    }

    public void setMateria(String materia) {
        Materia = materia;
    }

    public void setID_materia(int ID_materia) {
        this.ID_materia = ID_materia;
    }
}
