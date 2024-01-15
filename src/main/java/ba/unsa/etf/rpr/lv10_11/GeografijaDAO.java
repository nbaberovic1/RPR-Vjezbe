package ba.unsa.etf.rpr.lv10_11;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GeografijaDAO {
    private static GeografijaDAO instance = null;
    private Connection conn;

    private ObservableList<Grad> gradovi = FXCollections.observableArrayList();

    private ObservableList<Drzava> drzave = FXCollections.observableArrayList();

    private PreparedStatement stmtSviGradovi;
    private PreparedStatement stmtSveDrzave;
    private PreparedStatement stmtGlavniGrad;
    private PreparedStatement stmtObrisiDrzavu;
    private PreparedStatement stmtDrzava;
    private PreparedStatement stmtDodajGrad;
    private PreparedStatement stmtAzurirajDrzavu;
    private PreparedStatement stmtDodajDrzavu;
    private PreparedStatement stmtAzurirajGradDrzava;
    private PreparedStatement stmtAzurirajGrad;
    private PreparedStatement stmtObrisiGrad;


    private void obrisiSveIzBaze() {
        Thread nitBrisanjeGradova = new Thread( () -> {
            ArrayList<Grad> listaGradova;
            synchronized (conn) {
                listaGradova = this.listGradovi();
            }

            for(Grad grad : listaGradova) {
                synchronized (conn) {
                    this.obrisiGrad(grad);
                    Thread.yield();
                }
            }
        });

        Thread nitBrisanjeDrzava = new Thread( () -> {
            ArrayList<Drzava> listaDrzava;
            synchronized (conn) {
                listaDrzava = this.listDrzave();
            }

            for(Drzava drzava : listaDrzava) {
                synchronized (conn) {
                    this.obrisiDrzavu(drzava.getNaziv());
                    Thread.yield();
                }
            }
        });

        nitBrisanjeGradova.start();
        nitBrisanjeDrzava.start();

        try {
            nitBrisanjeGradova.join();
            nitBrisanjeDrzava.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void ubaciPodatkeUBazu() {
        Thread nitUbacivanjeGradova = new Thread(() -> {
            this.dodajGrad(new Grad(1, "Pariz", 2193031, null));
            this.dodajGrad(new Grad(2, "London", 8538700, null));
            this.dodajGrad(new Grad(3, "Manchester", 520000, null));
            this.dodajGrad(new Grad(4, "Beč", 1800000, null));
            this.dodajGrad(new Grad(5, "Graz", 280800, null));
            this.dodajGrad(new Grad(6, "Sarajevo", 274879, null));
        });

        Thread nitUbacivanjeDrzava = new Thread(() -> {
           this.dodajDrzavu(new Drzava(1, "Francuska", 0));
           this.dodajDrzavu(new Drzava(2, "Velika Britanija", 0));
           this.dodajDrzavu(new Drzava(3, "Austrija", 0));
           this.dodajDrzavu(new Drzava(4, "BiH", 0));
        });

        nitUbacivanjeGradova.start();
        nitUbacivanjeDrzava.start();

        try {
            nitUbacivanjeGradova.join();
            nitUbacivanjeDrzava.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        Thread nitPoveziGradove = new Thread( () -> {
            try {
                stmtAzurirajGradDrzava.setInt(1, 1);
                stmtAzurirajGradDrzava.setInt(2, 1);
                stmtAzurirajGradDrzava.executeUpdate();
                stmtAzurirajGradDrzava.setInt(1, 2);
                stmtAzurirajGradDrzava.setInt(2, 2);
                stmtAzurirajGradDrzava.executeUpdate();
                stmtAzurirajGradDrzava.setInt(1, 2);
                stmtAzurirajGradDrzava.setInt(2, 3);
                stmtAzurirajGradDrzava.executeUpdate();
                stmtAzurirajGradDrzava.setInt(1, 3);
                stmtAzurirajGradDrzava.setInt(2, 4);
                stmtAzurirajGradDrzava.executeUpdate();
                stmtAzurirajGradDrzava.setInt(1, 3);
                stmtAzurirajGradDrzava.setInt(2, 5);
                stmtAzurirajGradDrzava.executeUpdate();
                stmtAzurirajGradDrzava.setInt(1, 4);
                stmtAzurirajGradDrzava.setInt(2, 6);
                stmtAzurirajGradDrzava.executeUpdate();
            } catch (SQLException SQLe) {
                SQLe.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Thread nitPoveziDrzave = new Thread( () -> {
            try {
                stmtAzurirajDrzavu.setInt(1, 1);
                stmtAzurirajDrzavu.setInt(2, 1);
                stmtAzurirajDrzavu.executeUpdate();
                stmtAzurirajDrzavu.setInt(1, 2);
                stmtAzurirajDrzavu.setInt(2, 2);
                stmtAzurirajDrzavu.executeUpdate();
                stmtAzurirajDrzavu.setInt(1, 4);
                stmtAzurirajDrzavu.setInt(2, 3);
                stmtAzurirajDrzavu.executeUpdate();
                stmtAzurirajDrzavu.setInt(1, 6);
                stmtAzurirajDrzavu.setInt(2, 4);
                stmtAzurirajDrzavu.executeUpdate();
            } catch (SQLException SQLe) {
                SQLe.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        nitPoveziGradove.start();
        nitPoveziDrzave.start();

        try {
            nitPoveziGradove.join();
            nitPoveziDrzave.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

   private void regenerisiBazu () {
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileInputStream("src/main/resources/ba/unsa/etf/rpr/lv10_11/SQL/dump.sql"));
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

    private void ucitajIzBaze() {
        gradovi = FXCollections.observableArrayList(this.listGradovi());
        drzave = FXCollections.observableArrayList(this.listDrzave());
    }

    private void pripremiUpite() throws SQLException {
        conn.createStatement().execute("PRAGMA foreign_keys = ON;");
        stmtSviGradovi = conn.prepareStatement("SELECT * FROM grad g LEFT OUTER JOIN drzava d ON g.drzava = d.id ORDER BY g.broj_stanovnika DESC;");
        stmtSveDrzave = conn.prepareStatement("SELECT * FROM drzava d;");
        stmtGlavniGrad = conn.prepareStatement("SELECT * FROM  drzava d, grad g WHERE d.glavni_grad = g.id and d.naziv = ?;");
        stmtObrisiDrzavu = conn.prepareStatement("DELETE FROM drzava WHERE naziv = ?;");
        stmtDrzava = conn.prepareStatement("SELECT * FROM drzava WHERE naziv = ?;");
        stmtDodajGrad = conn.prepareStatement("INSERT INTO grad VALUES (?, ?, ?, ?);");
        stmtAzurirajDrzavu = conn.prepareStatement("UPDATE drzava SET glavni_grad = ? WHERE id = ?;");
        stmtDodajDrzavu = conn.prepareStatement("INSERT INTO drzava VALUES (?, ?, ?);");
        stmtAzurirajGradDrzava = conn.prepareStatement("UPDATE grad SET drzava = ? WHERE id = ?;");
        stmtAzurirajGrad = conn.prepareStatement("UPDATE grad SET naziv = ?, broj_stanovnika = ?, drzava = ? WHERE id = ?;");
        stmtObrisiGrad = conn.prepareStatement("DELETE FROM grad WHERE id = ?;");
    }

    private GeografijaDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:src/main/resources/ba/unsa/etf/rpr/lv10_11/SQL/baza.db");
            pripremiUpite();
            obrisiSveIzBaze();
            ubaciPodatkeUBazu();
            ucitajIzBaze();
        } catch ( SQLException e ) {
            regenerisiBazu();
            try {
                pripremiUpite();
                ubaciPodatkeUBazu();
                ucitajIzBaze();
            } catch ( SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static GeografijaDAO getInstance() {
        if (instance == null) instance = new GeografijaDAO();
        return instance;
    }

    public static void removeInstance() throws SQLException {
        instance.conn.close();
    }

    public ArrayList<Grad> listGradovi() {
        ArrayList<Grad> listaGradova = new ArrayList<>();
        try {
            ResultSet rezultat = stmtSviGradovi.executeQuery();
            while (rezultat.next()) {
                listaGradova.add(new Grad(rezultat.getInt(1), rezultat.getString(2), rezultat.getInt(3), new Drzava(rezultat.getInt(5), rezultat.getString(6), rezultat.getInt(7))));
            }
        } catch (SQLException SQLex) {
            SQLex.printStackTrace();
            return null;
        } catch (Exception ex) {
            throw new RuntimeException();
        }
        return listaGradova;
    }

    public ArrayList<Drzava> listDrzave() {
        ArrayList<Drzava> listaDrzava = new ArrayList<>();
        try {
            ResultSet rezultat = stmtSveDrzave.executeQuery();
            while(rezultat.next()){
                listaDrzava.add(new Drzava(rezultat.getInt(1), rezultat.getString(2), rezultat.getInt(3)));
            }
        } catch (SQLException SQLex) {
            SQLex.printStackTrace();
            return null;
        } catch (Exception ex) {
            throw new RuntimeException();
        }

        return listaDrzava;
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
            int brojObrisanihRedova = stmtObrisiDrzavu.executeUpdate();
            if(brojObrisanihRedova > 0) {
                for(int i=0; i<drzave.size(); i++) {
                    if(drzave.get(i).getNaziv().equals(drzava)) {
                        drzave.remove(i);
                        i = i - 1;
                    }
                }
                for(int i =0; i < gradovi.size(); i++) {
                    if(gradovi.get(i).getDrzava() != null && gradovi.get(i).getDrzava().getNaziv().equals(drzava)) {
                        gradovi.remove(i);
                        i = i - 1;
                    }
                }
            }
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
                    grad.getDrzava().setId(rezultatDrzava.getInt(1));
                    stmtDodajGrad.setInt(4, grad.getDrzava().getId());
                    stmtDodajGrad.executeUpdate();
                    gradovi.add(grad);
                    if(grad.getId() == grad.getDrzava().getGlavniGrad()) {
                        stmtAzurirajDrzavu.setInt(1, grad.getId());
                        stmtAzurirajDrzavu.setInt(2, rezultatDrzava.getInt(1));
                        stmtAzurirajDrzavu.executeUpdate();
                        for(Drzava d : drzave) {
                            if(d.getId() == rezultatDrzava.getInt(1)) d.setGlavniGrad(grad.getId());
                        }
                    }
                } else {
                    System.out.println("Nema drzave u kojoj se nalazi navedeni grad!");
                }
            } else {
                stmtDodajGrad.setNull(4, Types.INTEGER);
                stmtDodajGrad.executeUpdate();
                gradovi.add(grad);
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
            drzave.add(drzava);
            if(drzava.getGlavniGrad() != 0) {
                stmtAzurirajGradDrzava.setInt(1, drzava.getId());
                stmtAzurirajGradDrzava.setInt(2, drzava.getGlavniGrad());
                stmtAzurirajGradDrzava.executeUpdate();
                for(Grad g : gradovi) {
                    if(g.getId() == drzava.getGlavniGrad()) g.setDrzava(drzava);
                }
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
            for(Grad g : gradovi) {
                if(g.getId() == grad.getId()) {
                    g.setNaziv((grad.getNaziv()));
                    g.setBrojStanovnika(grad.getBrojStanovnika());
                    g.setDrzava(grad.getDrzava());
                    break;
                }
            }
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

    public void obrisiGrad(Grad grad) {
        try {
            stmtObrisiGrad.setInt(1, grad.getId());

            if(grad.getDrzava() != null && grad.getId() == grad.getDrzava().getGlavniGrad()){
                stmtAzurirajDrzavu.setNull(1, Types.INTEGER);
                stmtAzurirajDrzavu.setInt(2, grad.getDrzava().getId());
                stmtAzurirajDrzavu.executeUpdate();
                for(Drzava d : drzave) {
                    if(d.getId() == grad.getDrzava().getId()) {
                        d.setGlavniGrad(0);
                        break;
                    }
                }
            }
            stmtObrisiGrad.executeUpdate();
            gradovi.remove(grad);
        } catch (SQLException SQLex) {
            SQLex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public ObservableList<Grad> getGradovi() {
        return this.gradovi;
    }

    public ObservableList<Drzava> getDrzave() {
        return this.drzave;
    }
}