package DAO;

import java.sql.*;
import java.util.ArrayList;

public class DAO {
    private static final String url1 = "jdbc:mysql://localhost:3306/test";
    private static final String user = "root";
    private static final String password = "";

    public static void registerDriver() {
        /* METODO PER REGISTRARE IL DRIVER */
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            System.out.println("Driver correttamente registrato");
        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    public static ArrayList<Docente> queryDB1() {
        Connection conn1 = null;
        ArrayList<Docente> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }
            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM DOCENTE"); //Interrogo con la query sulla tabella docente
            while (rs.next()) {
                Docente p = new Docente(rs.getInt("ID_docente"), rs.getString("NOME"),rs.getString("COGNOME"), rs.getInt("ID_Corso"));
                out.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public static ArrayList<Corso> queryDB2() {
        Connection conn1 = null;
        ArrayList<Corso> x = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM CORSO"); //Interrogo con la query sulla tabella docente
            while (rs.next()) {
                Corso c = new Corso(rs.getInt("ID_materia"), rs.getString("MATERIA"));
                x.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return x;
    }

    public static ArrayList<Utente> queryDB3() {
        Connection conn1 = null;
        ArrayList<Utente> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }
            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM UTENTE"); //Interrogo con la query sulla tabella docente
            while (rs.next()) {
                Utente p = new Utente(rs.getString("NOME"),rs.getString("COGNOME"), rs.getString("Username"), rs.getString("Passwd"), rs.getInt("RUOLO"));
                out.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }
    public static ArrayList<Prenotazione> queryDB4() {
        Connection conn1 = null;
        ArrayList<Prenotazione> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }
            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM PRENOTAZIONE"); //Interrogo con la query sulla tabella docente
            while (rs.next()) {
                Prenotazione p = new Prenotazione(rs.getInt("Id_U"),rs.getInt("Id_D"), rs.getInt("Id_C"), rs.getDate("Day"), rs.getInt("Timetable"));
                out.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public static int insertDoc(int id, String nome, String cognome, int id_c) {

        String SQL_INSERT = "INSERT INTO DOCENTE VALUES (?,?,?,?)";
        Connection conn1 = null;
        PreparedStatement preparedStatement;
        ArrayList<Corso> x = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }
            Statement st = conn1.createStatement();
            preparedStatement = conn1.prepareStatement(SQL_INSERT);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, nome);
            preparedStatement.setString(3, cognome);
            preparedStatement.setInt(4, id_c);
            preparedStatement.executeUpdate();
            return 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 1;
        } finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }

    /*
    public static void operations(String op) {

        String SQL_INSERT, SQL_DELETE;
        String j;

        Scanner s = new Scanner (System.in);

        Connection conn1 = null;
        PreparedStatement preparedStatement;
        ArrayList<Corso> x = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }
            Statement st = conn1.createStatement();

            //uso il parametro passato nel metodo per capire di quale tavola si tratti!
            switch(op) {

                case "1":
                    System.out.println("Digit 1 to insert values in the table CORSO, 2 to delete");
                    j = s.nextLine();

                    switch(j) {
                        case "1":
                            System.out.println("Digit the values to insert: ID_materia (int), materia (string)");
                            SQL_INSERT = "INSERT INTO CORSO VALUES (?,?)";
                            preparedStatement = conn1.prepareStatement(SQL_INSERT);
                            int i = Integer.parseInt(s.next());
                            preparedStatement.setInt(1, i);
                            s.nextLine();
                            preparedStatement.setString(2, s.next());
                            preparedStatement.executeUpdate();
                            break;
                        case "2":
                            System.out.println("Insert the ID_materia to delete");
                            SQL_DELETE = "DELETE FROM CORSO WHERE ID_MATERIA = ?";
                            preparedStatement = conn1.prepareStatement(SQL_DELETE);
                            preparedStatement.setInt(1, s.nextInt());
                            preparedStatement.executeUpdate();
                            break;
                        default:
                            break;
                    }
                    break;
                }
           }
        */
}

