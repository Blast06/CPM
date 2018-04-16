package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by JulioD07 on 4/15/2018.
 */
public class CPMDetail {

    public SimpleStringProperty actividad = new SimpleStringProperty();
    public SimpleIntegerProperty duracion = new SimpleIntegerProperty();
    public SimpleIntegerProperty tiempoPrimero = new SimpleIntegerProperty();
    public SimpleIntegerProperty tiempoUltimo = new SimpleIntegerProperty();
    public SimpleStringProperty critica = new SimpleStringProperty();

    public CPMDetail(SimpleStringProperty actividad, SimpleIntegerProperty duracion, SimpleIntegerProperty tiempoPrimero, SimpleIntegerProperty tiempoUltimo, SimpleStringProperty critica) {
        this.actividad = actividad;
        this.duracion = duracion;
        this.tiempoPrimero = tiempoPrimero;
        this.tiempoUltimo = tiempoUltimo;
        this.critica = critica;
    }

    public CPMDetail(String actividad, String prereq, int duracion, int tiempoPrimero, int tiempoUltimo, int nodosGrales, String critica) {
        this.actividad.set(actividad);
        this.duracion.set(duracion);
        this.tiempoPrimero.set(tiempoPrimero);
        this.tiempoUltimo.set(tiempoUltimo);
        this.critica.set(critica);

    }

    public CPMDetail(String act, int dur) {
        this.actividad.set(act);
        this.duracion.set(dur);
    }

    public String getActividad() {
        return actividad.get();
    }

    public SimpleStringProperty actividadProperty() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad.set(actividad);
    }

    public int getDuracion() {
        return duracion.get();
    }

    public SimpleIntegerProperty duracionProperty() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion.set(duracion);
    }

    public int getTiempoPrimero() {
        return tiempoPrimero.get();
    }

    public SimpleIntegerProperty tiempoPrimeroProperty() {
        return tiempoPrimero;
    }

    public void setTiempoPrimero(int tiempoPrimero) {
        this.tiempoPrimero.set(tiempoPrimero);
    }

    public int getTiempoUltimo() {
        return tiempoUltimo.get();
    }

    public SimpleIntegerProperty tiempoUltimoProperty() {
        return tiempoUltimo;
    }

    public void setTiempoUltimo(int tiempoUltimo) {
        this.tiempoUltimo.set(tiempoUltimo);
    }

    public String getCritica() {
        return critica.get();
    }

    public SimpleStringProperty criticaProperty() {
        return critica;
    }

    public void setCritica(String critica) {
        this.critica.set(critica);
    }


}
