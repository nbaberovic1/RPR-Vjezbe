package ba.unsa.etf.rpr.tutorijal06;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class Controller {
    @FXML
    private Label display;
    private String unos = "";

    private boolean trazenaOperacija = true;

    public void clickMod(ActionEvent actionEvent){
            unos = new StringBuilder().append(unos).append("%").toString();
            trazenaOperacija = true;
    }

    public void clickDiv(ActionEvent actionEvent) {
        unos = new StringBuilder().append(unos).append("/").toString();
        trazenaOperacija = true;
    }

    public void clickX(ActionEvent actionEvent) {
        unos = new StringBuilder().append(unos).append("X").toString();
        trazenaOperacija = true;
    }

    public void click7(ActionEvent actionEvent) {
        if(trazenaOperacija) {
            display.setText("7");
            trazenaOperacija = false;
        }
        else if(display.getText().length() == 1 && display.getText().charAt(0) == '0'){
            display.setText("7");
        }else{
            display.setText(display.getText() + "7");
        }
        unos = new StringBuilder().append(unos).append("7").toString();
    }

    public void click8(ActionEvent actionEvent) {
        if(trazenaOperacija) {
            display.setText("8");
            trazenaOperacija = false;
        }
        else if(display.getText().length() == 1 && display.getText().charAt(0) == '0'){
            display.setText("8");
        }else{
            display.setText(display.getText() + "8");
        }
        unos = new StringBuilder().append(unos).append("8").toString();
    }

    public void click9(ActionEvent actionEvent) {
        if(trazenaOperacija) {
            display.setText("9");
            trazenaOperacija = false;
        }
        else if(display.getText().length() == 1 && display.getText().charAt(0) == '0'){
            display.setText("9");
        }else{
            display.setText(display.getText() + "9");
        }
        unos = new StringBuilder().append(unos).append("9").toString();
    }

    public void clickMin(ActionEvent actionEvent) {
        unos = new StringBuilder().append(unos).append("-").toString();
        trazenaOperacija = true;
    }

    public void clickPlus(ActionEvent actionEvent) {
        unos = new StringBuilder().append(unos).append("+").toString();
        trazenaOperacija = true;
    }

    public void click4(ActionEvent actionEvent) {
        if(trazenaOperacija) {
            display.setText("4");
            trazenaOperacija = false;
        }
        else if(display.getText().length() == 1 && display.getText().charAt(0) == '0'){
            display.setText("4");
        }else{
            display.setText(display.getText() + "4");
        }
        unos = new StringBuilder().append(unos).append("4").toString();
    }

    public void click5(ActionEvent actionEvent) {
        if(trazenaOperacija) {
            display.setText("5");
            trazenaOperacija = false;
        }
        else if(display.getText().length() == 1 && display.getText().charAt(0) == '0'){
            display.setText("5");
        }else{
            display.setText(display.getText() + "5");
        }
        unos = new StringBuilder().append(unos).append("5").toString();
    }

    public void click6(ActionEvent actionEvent) {
        if(trazenaOperacija) {
            display.setText("6");
            trazenaOperacija = false;
        }
        else if(display.getText().length() == 1 && display.getText().charAt(0) == '0'){
            display.setText("6");
        }else{
            display.setText(display.getText() + "6");
        }
        unos = new StringBuilder().append(unos).append("6").toString();
    }

    public void click1(ActionEvent actionEvent) {
        if(trazenaOperacija) {
            display.setText("1");
            trazenaOperacija = false;
        }
        else if(display.getText().length() == 1 && display.getText().charAt(0) == '0'){
            display.setText("1");
        }else{
            display.setText(display.getText() + "1");
        }
        unos = new StringBuilder().append(unos).append("1").toString();
    }

    public void click2(ActionEvent actionEvent) {
        if(trazenaOperacija) {
            display.setText("2");
            trazenaOperacija = false;
        }
        else if(display.getText().length() == 1 && display.getText().charAt(0) == '0'){
            display.setText("2");
        }else{
            display.setText(display.getText() + "2");
        }
        unos = new StringBuilder().append(unos).append("2").toString();
    }

    public void click3(ActionEvent actionEvent) {
        if(trazenaOperacija) {
            display.setText("3");
            trazenaOperacija = false;
        }
        else if(display.getText().length() == 1 && display.getText().charAt(0) == '0'){
            display.setText("3");
        }else{
            display.setText(display.getText() + "3");
        }
        unos = new StringBuilder().append(unos).append("3").toString();
    }

    public void click0(ActionEvent actionEvent) {
        if(trazenaOperacija) {
            display.setText("0");
            trazenaOperacija = false;
            unos = new StringBuilder().append(unos).append("0").toString();
        }else if(display.getText().length() != 1 || display.getText().charAt(0) != '0'){
            display.setText(display.getText() + "0");
            unos = new StringBuilder().append(unos).append("0").toString();
        }
    }

    public void clickPoint(ActionEvent actionEvent) {
        if(trazenaOperacija) {
            display.setText(".");
            trazenaOperacija = false;
        }
        else {
            display.setText(display.getText() + ".");
        }
        unos = new StringBuilder().append(unos).append(".").toString();
    }

    public void clickRes(ActionEvent actionEvent) {
        trazenaOperacija = true;
        ArrayList<Character> operacija = new ArrayList<>();
        for(int i=0; i<unos.length(); i = i + 1){
            if(unos.charAt(i) == '%' || unos.charAt(i) == '/' ||
                    unos.charAt(i) == 'X' || unos.charAt(i) == '-'
                    || unos.charAt(i) == '+'){
                if(i==0 || i == unos.length()-1 || (!Character.isDigit(unos.charAt(i-1)) && unos.charAt(i-1) != '.')
                        || (!Character.isDigit(unos.charAt(i+1)) && unos.charAt(i+1) != '.')){
                    display.setText("Greska: neispravan unos!");
                    unos = "";
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
                operacija.remove(i);
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

        display.setText(String.valueOf(rezultat));

        unos = "";
    }
}
