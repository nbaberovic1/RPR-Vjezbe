package ba.unsa.etf.rpr.tutorijal06;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

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
        pritisnutResultBtn = true;
        String unos = ekran.getText();
        ArrayList<Character> operacija = new ArrayList<>();
        for(int i=0; i<unos.length(); i = i + 1){
            if(unos.charAt(i) == '%' || unos.charAt(i) == '/' ||
                    unos.charAt(i) == 'X' || unos.charAt(i) == '-'
                    || unos.charAt(i) == '+'){
                if(i==0 || i == unos.length()-1 || (!Character.isDigit(unos.charAt(i-1)) && unos.charAt(i-1) != '.')
                        || (!Character.isDigit(unos.charAt(i+1)) && unos.charAt(i+1) != '.')){
                    ekran.setText("Greska: neispravan unos!");
                    return;
                }
                operacija.add(unos.charAt(i));
            }
        }
        String brojevi = unos.replaceAll("[^\\d.]", " ");
        String[] broj = brojevi.split("\\s+");
        ArrayList<Double> listaBrojeva = new ArrayList<>();
        for(int i =0; i<broj.length; i++){
            listaBrojeva.add(Double.parseDouble(broj[i]));
        }

        for(int i=0; i<operacija.size(); i = i + 1){
            if(operacija.get(i).equals('X')){
                listaBrojeva.set(i, listaBrojeva.get(i) * listaBrojeva.get(i + 1));
                listaBrojeva.remove(i + 1);
                operacija.remove(i);
                i = i - 1;
            }else if(operacija.get(i).equals('/')){
                listaBrojeva.set(i, listaBrojeva.get(i) / listaBrojeva.get(i + 1));
                listaBrojeva.remove(i + 1);
                operacija.remove(i);
                i = i - 1;
            }else if(operacija.get(i).equals('%')){
                listaBrojeva.set(i, listaBrojeva.get(i) % listaBrojeva.get(i + 1));
                listaBrojeva.remove(i + 1);
                operacija.remove(i);kl
                i = i - 1;
            }
        }

        double rezultat = 0;
        if(listaBrojeva.size() > 0) rezultat = listaBrojeva.get(0);

        for(int i=0; i<operacija.size(); i = i + 1){
            if(operacija.get(i).equals('+')){
                rezultat += listaBrojeva.get(i + 1).doubleValue();
            }else{
                rezultat -= listaBrojeva.get(i + 1).doubleValue();
            }
        }

        ekran.setText(String.valueOf(rezultat));
    }
}
