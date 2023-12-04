package ba.unsa.etf.rpr.tutorijal06;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private Label ekran;

    private boolean pritisnutResultBtn = false;

    public void clickMod(ActionEvent actionEvent){
        if(pritisnutResultBtn) {
            ekran.setText("%");
            pritisnutResultBtn = false;
        }
        else {
            ekran.setText(ekran.getText() + "%");
        }
    }

    public void clickDiv(ActionEvent actionEvent) {
        if(pritisnutResultBtn) {
            ekran.setText("/");
            pritisnutResultBtn = false;
        }
        else {
            ekran.setText(ekran.getText() + "/");
        }
    }

    public void clickX(ActionEvent actionEvent) {
        if(pritisnutResultBtn) {
            ekran.setText("X");
            pritisnutResultBtn = false;
        }
        else {
            ekran.setText(ekran.getText() + "X");
        }
    }

    public void click7(ActionEvent actionEvent) {
        if(pritisnutResultBtn) {
            ekran.setText("7");
            pritisnutResultBtn = false;
        }
        else {
            ekran.setText(ekran.getText() + "7");
        }
    }

    public void click8(ActionEvent actionEvent) {
        if(pritisnutResultBtn) {
            ekran.setText("8");
            pritisnutResultBtn = false;
        }
        else {
            ekran.setText(ekran.getText() + "8");
        }
    }

    public void click9(ActionEvent actionEvent) {
        if(pritisnutResultBtn) {
            ekran.setText("9");
            pritisnutResultBtn = false;
        }
        else {
            ekran.setText(ekran.getText() + "9");
        }
    }

    public void clickMin(ActionEvent actionEvent) {
        if(pritisnutResultBtn) {
            ekran.setText("-");
            pritisnutResultBtn = false;
        }
        else {
            ekran.setText(ekran.getText() + "-");
        }
    }

    public void clickPlus(ActionEvent actionEvent) {
        if(pritisnutResultBtn) {
            ekran.setText("+");
            pritisnutResultBtn = false;
        }
        else {
            ekran.setText(ekran.getText() + "+");
        }
    }

    public void click4(ActionEvent actionEvent) {if(pritisnutResultBtn) {
        ekran.setText("4");
        pritisnutResultBtn = false;
        }
        else {
        ekran.setText(ekran.getText() + "4");
        }
    }

    public void click5(ActionEvent actionEvent) {
        if(pritisnutResultBtn) {
            ekran.setText("5");
            pritisnutResultBtn = false;
        }
        else {
            ekran.setText(ekran.getText() + "5");
        }
    }

    public void click6(ActionEvent actionEvent) {
        if(pritisnutResultBtn) {
            ekran.setText("6");
            pritisnutResultBtn = false;
        }
        else {
            ekran.setText(ekran.getText() + "6");
        }
    }

    public void click1(ActionEvent actionEvent) {
        if(pritisnutResultBtn) {
            ekran.setText("1");
            pritisnutResultBtn = false;
        }
        else {
            ekran.setText(ekran.getText() + "1");
        }
    }

    public void click2(ActionEvent actionEvent) {
        if(pritisnutResultBtn) {
            ekran.setText("2");
            pritisnutResultBtn = false;
        }
        else {
            ekran.setText(ekran.getText() + "2");
        }
    }

    public void click3(ActionEvent actionEvent) {
        if(pritisnutResultBtn) {
            ekran.setText("3");
            pritisnutResultBtn = false;
        }
        else {
            ekran.setText(ekran.getText() + "3");
        }
    }

    public void click0(ActionEvent actionEvent) {
        if(pritisnutResultBtn) {
            ekran.setText("0");
            pritisnutResultBtn = false;
        }
        else {
            ekran.setText(ekran.getText() + "0");
        }
    }

    public void clickPoint(ActionEvent actionEvent) {
        if(pritisnutResultBtn) {
            ekran.setText(".");
            pritisnutResultBtn = false;
        }
        else {
            ekran.setText(ekran.getText() + ".");
        }
    }

    public void clickRes(ActionEvent actionEvent) {
        ekran.setText("12345");
        pritisnutResultBtn = true;
    }
}
