package sample;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
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
    public int primerTime,ultimoTime,inicioTardio,finalTardio,node;

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

    public int getNode() {
        return node;
    }

    public void setNode(int node) {
        this.node = node;
    }

    public CPM(SimpleStringProperty actividad, SimpleStringProperty prereq, SimpleIntegerProperty duracion, SimpleIntegerProperty costo, SimpleStringProperty importe, int primerTime, int ultimoTime, int inicioTardio, int finalTardio, int node) {
        this.actividad = actividad;
        this.prereq = prereq;
        this.duracion = duracion;
        this.costo = costo;
        this.importe = importe;
        this.primerTime = primerTime;
        this.ultimoTime = ultimoTime;
        this.inicioTardio = inicioTardio;
        this.finalTardio = finalTardio;
        this.node = node;
    }

    public void setActividad(String actividad) {
        this.actividad.set(actividad);
    }

    public CPM(String actividad, String prereq, int duracion, int costo, String importe) {
        this.actividad.set(actividad);
        this.prereq.set(prereq);
        this.duracion.set(duracion);
        this.costo.set(costo);
        this.importe.set(importe);

    }

    public CPM() {
        this.actividad = actividad;
        this.prereq = prereq;
        this.duracion = duracion;
        this.costo = costo;
        this.importe = importe;
        this.node = node;
    }

    public CPM(String act, String pre, int dur, int cost, String s, int nodosGrales){

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

    public static String SoloNumeros(String cad){
        String cadr="";
        boolean x=true;
        int i=0;
        if ( !cad.isEmpty()){
            while (x){
                String aux= String.valueOf(cad.charAt(i));
                try {
                    Integer.parseInt(aux);

                    for ( int j=i;j<cad.toCharArray().length;j++){
                        if ( cad.charAt(j)=='.'){
                            j++; }
                        Integer.parseInt(String.valueOf(cad.charAt(j)));
                        cadr = cadr+cad.charAt(j);
                        x=false;
                    } }
                catch(NumberFormatException exep){
                    i++; }
            } }
        return cadr;

    }

    public static boolean isInt(TextField input){
        try{
            int costo = Integer.parseInt(input.getText());
            return true;
        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "EN COSTO Y DURACION SOLO NUMEROS:", ButtonType.OK);
            alert.showAndWait();
            return false;

        }

    }


    public static void obtener1erTiempo(int time1, int time2){

        int result = time1 + time2;

    }





}
