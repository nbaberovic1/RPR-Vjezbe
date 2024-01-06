module ba.unsa.etf.rpr.lv10_11 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ba.unsa.etf.rpr.lv10_11 to javafx.fxml;
    exports ba.unsa.etf.rpr.lv10_11;
}