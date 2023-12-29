package ba.unsa.etf.rpr;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GeografijaDAO {
    private static GeografijaDAO instance = null;
    private Connection conn;

    private PreparedStatement stmtSviGradovi;

    private GeografijaDAO() throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:src/main/resources/baza.db");
        stmtSviGradovi = conn.prepareStatement("SELECT * FROM grad g, drzava d WHERE g.drzava = d.id ORDER BY g.broj_stanovnika DESC;");
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
