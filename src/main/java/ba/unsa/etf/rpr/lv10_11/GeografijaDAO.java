package ba.unsa.etf.rpr.lv10_11;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GeografijaDAO {
    private static GeografijaDAO instance = null;
    private Connection conn;

    private PreparedStatement stmtSviGradovi;
    private PreparedStatement stmtGlavniGrad;
    private PreparedStatement stmtObrisiDrzavu;
    private PreparedStatement stmtDrzava;
    private PreparedStatement stmtDodajGrad;
    private PreparedStatement stmtAzurirajDrzavu;
    private PreparedStatement stmtDodajDrzavu;
    private PreparedStatement stmtAzurirajGradDrzava;
    private PreparedStatement stmtAzurirajGrad;

    private void regenerisiBazu () {
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileInputStream("C:\\Users\\DT User3\\IdeaProjects\\lv9\\src\\main\\resources\\dump.sql"));
            String sqlUpit = "";
            while (ulaz.hasNext()) {
                sqlUpit += ulaz.nextLine();
                if (sqlUpit.length() > 1 && sqlUpit.charAt(sqlUpit.length() - 1) == ';') {
                    try {
                        Statement stmt = conn.createStatement();
                        stmt.execute(sqlUpit);
                        sqlUpit = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            ulaz.close();
        } catch ( FileNotFoundException e) {
            System.out.println("Ne postoji SQL datoteka dump.sql nastavljam sa praznom bazom");
        }
    }

    private void pripremiUpite() throws SQLException {
        conn.createStatement().execute("PRAGMA foreign_keys = ON;");
        stmtSviGradovi = conn.prepareStatement("SELECT * FROM grad g LEFT OUTER JOIN drzava d ON g.drzava = d.id ORDER BY g.broj_stanovnika DESC;");
        stmtGlavniGrad = conn.prepareStatement("SELECT * FROM  drzava d, grad g WHERE d.glavni_grad = g.id and d.naziv = ?;");
        stmtObrisiDrzavu = conn.prepareStatement("DELETE FROM drzava WHERE naziv = ?;");
        stmtDrzava = conn.prepareStatement("SELECT * FROM drzava WHERE naziv = ?;");
        stmtDodajGrad = conn.prepareStatement("INSERT INTO grad VALUES (?, ?, ?, ?);");
        stmtAzurirajDrzavu = conn.prepareStatement("UPDATE drzava SET glavni_grad = ? WHERE id = ?;");
        stmtDodajDrzavu = conn.prepareStatement("INSERT INTO drzava VALUES (?, ?, ?);");
        stmtAzurirajGradDrzava = conn.prepareStatement("UPDATE grad SET drzava = ? WHERE id = ?;");
        stmtAzurirajGrad = conn.prepareStatement("UPDATE grad SET naziv = ?, broj_stanovnika = ?, drzava = ? WHERE id = ?;");
    }

    private GeografijaDAO() throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:src/main/resources/baza.db");
        try {
            pripremiUpite();
        } catch ( SQLException e ) {
            regenerisiBazu();
            try {
                pripremiUpite();
            } catch ( SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static GeografijaDAO getInstance() throws SQLException {
        if (instance == null) instance = new GeografijaDAO();
        return instance;
    }

    public static void removeInstance() throws SQLException {
        instance.conn.close();
    }

    public ArrayList<Grad> gradovi() throws SQLException {
        ArrayList<Grad> listaGradova = new ArrayList<>();
        ResultSet rezultat = stmtSviGradovi.executeQuery();
        while(rezultat.next()){
            listaGradova.add(new Grad(rezultat.getInt(1), rezultat.getString(2), rezultat.getInt(3), new Drzava(rezultat.getInt(5), rezultat.getString(6), rezultat.getInt(7))));
        }
        return listaGradova;
    }

    public Grad glavniGrad(String drzava) {
        Grad gGrad = null;
        try {
            stmtGlavniGrad.setString(1, drzava);
            ResultSet rezultat = stmtGlavniGrad.executeQuery();
            if(rezultat.next()) {
                gGrad = new Grad(rezultat.getInt(4), rezultat.getString(5), rezultat.getInt(6), new Drzava(rezultat.getInt(1), rezultat.getString(2), rezultat.getInt(3)));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return gGrad;
    }

    public void obrisiDrzavu(String drzava) {
        try {
            stmtObrisiDrzavu.setString(1, drzava);
            stmtObrisiDrzavu.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodajGrad(Grad grad) {
        try {
            stmtDodajGrad.setInt(1, grad.getId());
            stmtDodajGrad.setString(2, grad.getNaziv());
            stmtDodajGrad.setInt(3, grad.getBrojStanovnika());
            if (grad.getDrzava() != null) {
                stmtDrzava.setString(1, grad.getDrzava().getNaziv());
                ResultSet rezultatDrzava = stmtDrzava.executeQuery();
                if(rezultatDrzava.next()) {
                    stmtDodajGrad.setInt(4, rezultatDrzava.getInt(1));
                    stmtDodajGrad.executeUpdate();
                    if(grad.getId() == grad.getDrzava().getGlavniGrad()) {
                        stmtAzurirajDrzavu.setInt(1, grad.getId());
                        stmtAzurirajDrzavu.setInt(2, rezultatDrzava.getInt(1));
                        stmtAzurirajDrzavu.executeUpdate();
                    }
                } else {
                    System.out.println("Nema drzave u kojoj se nalazi navedeni grad!");
                }
            } else {
                stmtDodajGrad.setNull(4, Types.INTEGER);
                stmtDodajGrad.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodajDrzavu(Drzava drzava) {
        try {
            stmtDodajDrzavu.setInt(1, drzava.getId());
            stmtDodajDrzavu.setString(2, drzava.getNaziv());
            if(drzava.getGlavniGrad() == 0) {
                stmtDodajDrzavu.setNull(3, Types.INTEGER);
            } else {
                stmtDodajDrzavu.setInt(3, drzava.getGlavniGrad());
            }
            stmtDodajDrzavu.executeUpdate();
            if(drzava.getGlavniGrad() != 0) {
                stmtAzurirajGradDrzava.setInt(1, drzava.getId());
                stmtAzurirajGradDrzava.setInt(2, drzava.getGlavniGrad());
                stmtAzurirajGradDrzava.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void izmijeniGrad(Grad grad) {
        try {
            stmtAzurirajGrad.setString(1, grad.getNaziv());
            stmtAzurirajGrad.setInt(2, grad.getBrojStanovnika());
            if(grad.getDrzava() != null) {
                stmtAzurirajGrad.setInt(3, grad.getDrzava().getId());
            } else {
                stmtAzurirajGrad.setNull(3, Types.INTEGER);
            }
            stmtAzurirajGrad.setInt(4, grad.getId());
            stmtAzurirajGrad.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Drzava nadjiDrzavu(String drzava) {
        Drzava rezultatDrzava = null;
        try {
            stmtDrzava.setString(1, drzava);
            ResultSet rezultat = stmtDrzava.executeQuery();
            if(rezultat.next()) {
                rezultatDrzava = new Drzava(rezultat.getInt(1), rezultat.getString(2), rezultat.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rezultatDrzava;
    }
}