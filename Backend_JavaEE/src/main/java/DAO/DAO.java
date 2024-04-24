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
            System.out.println("+++++// DRIVER REGISTRATO //+++++");
        } catch (SQLException e) {
            System.out.println("+++++// ERROR: " + e.getMessage() + " //+++++");
        }
    }

    public static ArrayList<Associazione> queryDB0() {
        Connection conn1 = null;
        ArrayList<Associazione> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("++++++ CONNECTED TO THE DATABASE - QUERYDB1() - DOCENTE ++++++ ");
            }
            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ASSOCIAZIONE"); //Interrogo con la query sulla tabella docente
            while (rs.next()) {
                Associazione p = new Associazione(rs.getInt("IdAssociazione"), rs.getInt("IdAsCorso"),rs.getInt("IdAsDocente"));
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


    public static ArrayList<Docente> queryDB1() {
        Connection conn1 = null;
        ArrayList<Docente> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("++++++ CONNECTED TO THE DATABASE - QUERYDB1() - DOCENTE ++++++ ");
            }
            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM DOCENTE"); //Interrogo con la query sulla tabella docente
            while (rs.next()) {
                Docente p = new Docente(rs.getInt("IdDocente"), rs.getString("Nome"),rs.getString("Cognome"));
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
        ArrayList<Corso> ris = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("++++++ CONNECTED TO THE DATABASE - QUERYDB2() - CORSO ++++++ ");
                        }

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM CORSO"); //Interrogo con la query sulla tabella docente
            while (rs.next()) {
                Corso c = new Corso(rs.getInt("IdCorso"), rs.getString("Materia"));
                ris.add(c);
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
        return ris;
    }

    public static ArrayList<Utente> queryDB3() {
        Connection conn1 = null;
        ArrayList<Utente> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("++++++ CONNECTED TO THE DATABASE - QUERYDB3() - UTENTE ++++++ ");
            }
            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM USER"); //Interrogo con la query sulla tabella docente
            while (rs.next()) {
                Utente u = new Utente(rs.getInt("IdUser"), rs.getString("Nome"),rs.getString("Cognome"), rs.getString("Username"), rs.getString("Password"), rs.getInt("Ruolo"));
                out.add(u);
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
        System.out.println("Utenti correttamente registrati" + out);
        return out;
    }


    public static ArrayList<Prenotazione> queryDB4() {
        Connection conn1 = null;
        ArrayList<Prenotazione> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("++++++ CONNECTED TO THE DATABASE - QUERYDB4() - PRENOTAZIONE ++++++ ");
            }
            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM PRENOTAZIONE"); //Interrogo con la query sulla tabella docente
            while (rs.next()) {
                Prenotazione p = new Prenotazione(rs.getInt("IdPrenotazione"), rs.getInt("IdPrenRipetizione"), rs.getInt("IdPrenUser"), rs.getInt("IdPrenAssociazione"));
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

    public static ArrayList<Ripetizione> queryDB5() {
        Connection conn1 = null;
        ArrayList<Ripetizione> out = new ArrayList<>();
            try {
                conn1 = DriverManager.getConnection(url1, user, password);
                if (conn1 != null) {
                System.out.println("++++++ CONNECTED TO THE DATABASE - QUERYDB5() - RIPETIZIONE ++++++ ");
                }
                Statement st = conn1.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM RIPETIZIONE");
                while (rs.next()) {
                    Ripetizione r = new Ripetizione(rs.getInt("IdRipetizione"), rs.getInt("IdRipAssociazione"), rs.getDate("Giorno"), rs.getInt("Slot"),rs.getInt("Executed"));
                    out.add(r);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
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

        /*
    ************************************************************************** INSERT
     */
    public static int addDocente(int id, String nome, String cognome) {

        String SQL_INSERT = "INSERT IGNORE INTO DOCENTE VALUES (?,?,?)";
        Connection conn1 = null;
        PreparedStatement preparedStatement;
        ArrayList<Corso> x = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("++++++ CONNECTED TO THE DATABASE ++++++ ");
            }
            Statement st = conn1.createStatement();
            preparedStatement = conn1.prepareStatement(SQL_INSERT);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, nome);
            preparedStatement.setString(3, cognome);
            preparedStatement.executeUpdate();
            return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
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

     public static int addCorso(int id, String materia) {

        String SQL_INSERT = "INSERT IGNORE INTO CORSO VALUES (?,?)";
        Connection conn1 = null;
        PreparedStatement preparedStatement;
        ArrayList<Corso> x = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("++++++ CONNECTED TO THE DATABASE ++++++ ");
            }
            Statement st = conn1.createStatement();
            preparedStatement = conn1.prepareStatement(SQL_INSERT);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, materia);
            preparedStatement.executeUpdate();
            return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
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
     public static int addAssociazione(int idAss, int iddocente, int idcorso) {

        String SQL_INSERT = "INSERT IGNORE INTO DOCENTE VALUES (?,?,?)";
        Connection conn1 = null;
        PreparedStatement preparedStatement;
        ArrayList<Corso> x = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("++++++ CONNECTED TO THE DATABASE ++++++ ");
            }
            Statement st = conn1.createStatement();
            preparedStatement = conn1.prepareStatement(SQL_INSERT);
            preparedStatement.setInt(1, idAss);
            preparedStatement.setInt(2, idcorso);
            preparedStatement.setInt(3, iddocente);
            preparedStatement.executeUpdate();
            return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
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
    public static int addUser(int id, String nome, String cognome, String pword, String username, int role) {

        System.out.println("\n++++++ addUser()");
        String SQL_INSERT = "INSERT IGNORE INTO USER VALUES (?,?,?,?,?,?)";
        Connection conn1 = null;
        PreparedStatement preparedStatement;
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("++++++ CONNECTED TO THE DATABASE ++++++ ");
            }
            Statement st = conn1.createStatement();
            System.out.println("Qui dopo execute della prima istruzione");
            preparedStatement = conn1.prepareStatement(SQL_INSERT);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, nome);
            preparedStatement.setString(3, cognome);
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, pword);
            preparedStatement.setInt(6, role);
            preparedStatement.executeUpdate();
            return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
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

    public static int addPrenotazione(int idPren, int idPrenRip, int idPrenUser, int idPrenAss) {

        System.out.println("\n++++++ addRipetizione()");
        String SQL_INSERT = "INSERT INTO PRENOTAZIONE VALUES(?,?,?,?)";
        Connection conn1 = null;
        PreparedStatement preparedStatement;
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("++++++ CONNECTED TO THE DATABASE ++++++ ");
            }
            Statement st = conn1.createStatement();

            preparedStatement = conn1.prepareStatement(SQL_INSERT);
            preparedStatement.setInt(1, idPren);
            preparedStatement.setInt(2, idPrenRip);
            preparedStatement.setInt(3, idPrenUser);
            preparedStatement.setInt(4, idPrenAss);
            preparedStatement.executeUpdate();

            return 1;
        }  catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
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

    public static int addRipetizione(int idRip, int idRipAss, String giorno, int slot, int executed) {

        System.out.println("\n++++++ addPrenotazione()");
        String SQL_INSERT = "INSERT IGNORE INTO RIPETIZIONE VALUES(?,?,?,?,?)";
        Connection conn1 = null;
        PreparedStatement preparedStatement;
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("++++++ CONNECTED TO THE DATABASE ++++++ ");
            }
            Statement st = conn1.createStatement();

            preparedStatement = conn1.prepareStatement(SQL_INSERT);
            preparedStatement.setInt(1, idRip);
            preparedStatement.setInt(2, idRipAss);
            preparedStatement.setDate(3, java.sql.Date.valueOf(giorno));
            preparedStatement.setInt(4, slot);
            preparedStatement.setInt(5, executed);
            preparedStatement.executeUpdate();

            return 1;
        }  catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
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
    ************************************************************************** DELETE
     */

    public static int deleteDocente(int id) {

        String SQL_DELETE = "DELETE FROM DOCENTE WHERE IdDocente = ?";
        Connection conn1 = null;
        PreparedStatement preparedStatement;
        ArrayList<Corso> x = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("++++++ CONNECTED TO THE DATABASE ++++++ ");
            }
            Statement st = conn1.createStatement();
            preparedStatement = conn1.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
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

    public static int deleteCorso(int id) {

        String SQL_DELETE = "DELETE FROM CORSO WHERE IdCorso = ?";
        Connection conn1 = null;
        PreparedStatement preparedStatement;
        ArrayList<Corso> x = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("++++++ CONNECTED TO THE DATABASE ++++++ ");
            }
            Statement st = conn1.createStatement();
            preparedStatement = conn1.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
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
    public static int deleteAssociazione(int id) {

        String SQL_DELETE = "DELETE FROM ASSOCIAZIONE WHERE IdAssociazione = ?";
        Connection conn1 = null;
        PreparedStatement preparedStatement;
        ArrayList<Corso> x = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("++++++ CONNECTED TO THE DATABASE ++++++ ");
            }
            Statement st = conn1.createStatement();
            preparedStatement = conn1.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
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
    public static int deleteRipetizione(int idRip, String date, int slot) {

        System.out.println("\n++++++ deleteRipetizione()");
        String SQL_DELETE = "DELETE FROM RIPETIZIONI WHERE IdRipetizione = ? AND Giorno=? AND Slot=?";
        Connection conn1 = null;
        PreparedStatement preparedStatement;
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("++++++ CONNECTED TO THE DATABASE ++++++ ");
            }
            Statement st = conn1.createStatement();

            preparedStatement = conn1.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, idRip);
            preparedStatement.setDate(2, java.sql.Date.valueOf(date));
            preparedStatement.setInt(3, slot);
            preparedStatement.executeUpdate();
             System.out.println("++++++ RIPETIZIONE deleted");
            return 1;
        }  catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
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

    public static int deletePrenotazione(int idPren, int idPrenRip, int idPrenUser) {

        System.out.println("\n++++++ deletePrenotazione()");
        String SQL_DELETE = "DELETE FROM PRENOTAZIONE WHERE IdPrenotazione = ? AND IdPrenRipetizione = ? AND IdPrenUser= ?";
        Connection conn1 = null;
        PreparedStatement preparedStatement;
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("+++++++++ Connected to the database test");
            }
            Statement st = conn1.createStatement();

            preparedStatement = conn1.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, idPren);
            preparedStatement.setInt(2, idPrenRip);
            preparedStatement.setInt(3, idPrenUser);
            preparedStatement.executeUpdate();
            System.out.println("++++++ PRENOTAZIONE deleted");
            return 1;
        }  catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
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

    public static int setRipetizione(int idRipetizione, int value) {

        System.out.println("\n++++++ setPrenotazione()");
        String SQL_UPDATE = "UPDATE RIPETIZIONE SET Executed = ?  WHERE IdRipetizione = ? ";
        Connection conn1 = null;
        PreparedStatement preparedStatement;
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("+++++++++ Connected to the database test");
            }
            Statement st = conn1.createStatement();

            preparedStatement = conn1.prepareStatement(SQL_UPDATE);
            preparedStatement.setInt(1, value);
            preparedStatement.setInt(2, idRipetizione);
            preparedStatement.executeUpdate();
            System.out.println("++++++ RIPETIZIONE updated");
            return 1;
        }  catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
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
}

