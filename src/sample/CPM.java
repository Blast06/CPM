package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by JulioD07 on 4/3/2018.
 */
public class CPM {

    public SimpleStringProperty actividad = new SimpleStringProperty();
    public SimpleStringProperty prereq = new SimpleStringProperty();
    public SimpleIntegerProperty duracion = new SimpleIntegerProperty();
    public SimpleIntegerProperty costo = new SimpleIntegerProperty();
    public SimpleStringProperty importe = new SimpleStringProperty();
    public SimpleIntegerProperty node = new SimpleIntegerProperty();
    public SimpleIntegerProperty actNumber = new SimpleIntegerProperty();



    public int primerTime,ultimoTime,inicioTardio,finalTardio;

    public CPM(SimpleStringProperty actividad, SimpleStringProperty prereq, SimpleIntegerProperty duracion, SimpleIntegerProperty costo, SimpleStringProperty importe, SimpleIntegerProperty node, SimpleIntegerProperty actNumber) {
        this.actividad = actividad;
        this.prereq = prereq;
        this.duracion = duracion;
        this.costo = costo;
        this.importe = importe;
        this.node = node;
        this.actNumber = actNumber;
    }

    public int getActNumber() {
        return actNumber.get();
    }

    public SimpleIntegerProperty actNumberProperty() {
        return actNumber;
    }

    public void setActNumber(int actNumber) {
        this.actNumber.set(actNumber);
    }

    public int getNode() {
        return node.get();
    }

    public SimpleIntegerProperty nodeProperty() {
        return node;
    }

    public void setNode(int node) {
        this.node.set(node);
    }


    public CPM() {

    }





    public CPM(SimpleStringProperty actividad, SimpleStringProperty prereq, SimpleIntegerProperty duracion, SimpleIntegerProperty costo, SimpleStringProperty importe) {
        this.actividad = actividad;
        this.prereq = prereq;
        this.duracion = duracion;
        this.costo = costo;
        this.importe = importe;

    }



    public void setActividad(String actividad) {
        this.actividad.set(actividad);
    }

    public CPM(String actividad, String prereq, int duracion, int costo, String importe, int nodosGrales, int actNumbers) {
        this.actividad.set(actividad);
        this.prereq.set(prereq);
        this.duracion.set(duracion);
        this.costo.set(costo);
        this.importe.set(importe);
        this.node.set(nodosGrales);
        this.actNumber.set(actNumbers);

    }

    public int getPrimerTime() {
        return primerTime;
    }

    public void setPrimerTime(int primerTime) {
        this.primerTime = primerTime;
    }

    public int getUltimoTime() {
        return ultimoTime;
    }

    public void setUltimoTime(int ultimoTime) {
        this.ultimoTime = ultimoTime;
    }

    public int getInicioTardio() {
        return inicioTardio;
    }

    public void setInicioTardio(int inicioTardio) {
        this.inicioTardio = inicioTardio;
    }

    public int getFinalTardio() {
        return finalTardio;
    }

    public void setFinalTardio(int finalTardio) {
        this.finalTardio = finalTardio;
    }




    public void setPrereq(String prereq) {
        this.prereq.set(prereq);
    }

    public void setDuracion(int duracion) {
        this.duracion.set(duracion);
    }

    public void setCosto(int costo) {
        this.costo.set(costo);
    }

    public void setImporte(String importe) {
        this.importe.set(importe);
    }

    public String getActividad() {
        return actividad.get();
    }




    public SimpleStringProperty actividadProperty() {
        return actividad;
    }

    public String getPrereq() {
        return prereq.get();
    }

    public SimpleStringProperty prereqProperty() {
        return prereq;
    }

    public int getDuracion() {
        return duracion.get();
    }

    public SimpleIntegerProperty duracionProperty() {
        return duracion;
    }

    public int getCosto() {
        return costo.get();
    }

    public SimpleIntegerProperty costoProperty() {
        return costo;
    }

    public String getImporte() {
        return importe.get();
    }

    public SimpleStringProperty importeProperty() {
        return importe;
    }

    public static String ConvertToDollar(String number){

        NumberFormat nf = NumberFormat.getCurrencyInstance
                (Locale.getDefault ());
        String vl = number;
        String cad = nf.format(Float.parseFloat(vl));
        return cad;
    }

    public static String ConvertToDominican(String number){

        NumberFormat nf = NumberFormat.getCurrencyInstance
                (Locale.getDefault ());
        String vl = number;
        String cad = nf.format(Float.parseFloat(vl));
        return cad;
    }












}
