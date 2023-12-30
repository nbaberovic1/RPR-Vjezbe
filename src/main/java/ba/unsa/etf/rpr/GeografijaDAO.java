package ba.unsa.etf.rpr;

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

    private GeografijaDAO() throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:src/main/resources/baza.db");
        try {
            stmtSviGradovi = conn.prepareStatement("SELECT * FROM grad g LEFT OUTER JOIN drzava d ON g.drzava = d.id ORDER BY g.broj_stanovnika DESC;");
            stmtGlavniGrad = conn.prepareStatement("SELECT * FROM  drzava d, grad g WHERE d.glavni_grad = g.id and d.naziv = ?;");
            stmtObrisiDrzavu = conn.prepareStatement("DELETE FROM drzava WHERE naziv = ?;");
            stmtDrzava = conn.prepareStatement("SELECT * FROM drzava WHERE naziv = ?;");
            stmtDodajGrad = conn.prepareStatement("INSERT INTO grad VALUES (?, ?, ?, ?);");
            stmtAzurirajDrzavu = conn.prepareStatement("UPDATE drzava SET glavni_grad = ? WHERE id = ?;");
        } catch ( SQLException e ) {
            regenerisiBazu();
            try {
                stmtSviGradovi = conn.prepareStatement("SELECT * FROM grad g LEFT OUTER JOIN drzava d ON g.drzava = d.id ORDER BY g.broj_stanovnika DESC;");
                stmtGlavniGrad = conn.prepareStatement("SELECT * FROM  drzava d, grad g WHERE d.glavni_grad = g.id and d.naziv = ?;");
                stmtObrisiDrzavu = conn.prepareStatement("DELETE FROM drzava WHERE naziv = ?;");
                stmtDrzava = conn.prepareStatement("SELECT * FROM drzava WHERE naziv = ?;");
                stmtDodajGrad = conn.prepareStatement("INSERT INTO grad VALUES (?, ?, ?, ?);");
                stmtAzurirajDrzavu = conn.prepareStatement("UPDATE drzava SET glavni_grad = ? WHERE id = ?;");
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
        /*Collections.sort(listaGradova, new Comparator<Grad>() {
            @Override
            public int compare(Grad g1, Grad g2) {
                return Integer.compare(g2.getBrojStanovnika(), g1.getBrojStanovnika());
            }
        });*/
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
}



















