package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

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




}
