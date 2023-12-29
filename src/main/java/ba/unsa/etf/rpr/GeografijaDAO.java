package ba.unsa.etf.rpr;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class GeografijaDAO {
    private static GeografijaDAO instance = null;
    private Connection conn;

    private PreparedStatement stmtSviGradovi;

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
            stmtSviGradovi = conn.prepareStatement("SELECT * FROM grad g, drzava d WHERE g.drzava = d.id ORDER BY g.broj_stanovnika DESC;");
        } catch ( SQLException e ) {
            regenerisiBazu();
            try {
                stmtSviGradovi = conn.prepareStatement("SELECT * FROM grad g, drzava d WHERE g.drzava = d.id ORDER BY g.broj_stanovnika DESC;");
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
}
