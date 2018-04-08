package sample;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Currency;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public String checkRepetidos = "-";

    @FXML
    private TextField campo_actividad;

    @FXML
    private TextField campo_prereq;

    @FXML
    private TextField campo_duracion;

    @FXML
    private TextField campo_costo;

    @FXML
    private Button anadir;

    @FXML
    private Button reiniciar;

    @FXML
    private Button calcular;

    @FXML
    private TableView<CPM> tablaCPM;

    @FXML
    private TableColumn<CPM, String> actividadC1;

    @FXML
    private TableColumn<CPM, String> prereqc1;

    @FXML
    private TableColumn<CPM, Integer> duracionc1;

    @FXML
    private TableColumn<CPM, String> costoc1;

    @FXML
    private TableColumn<CPM, String> importec1;

    ObservableList<CPM> cpms;

    @FXML
    private JFXDialog dialog;

    private int posicionCpmEnTabla;

    @FXML
    private ComboBox boxPrereq;

    private ObservableList<String> preReqList = FXCollections.observableArrayList();

    @FXML
    void agregar(ActionEvent event) {


        //VERIFICA SI ES LA PRIMERA ACTIVIDAD QUE SE INGRESA, PARA ASI NO TENER QUE LLENAR EL PREREQUISITO y SI CUALQUIERA DE LOS
        // OTROS CAMPOS(SIN INCLUIR LOS PREREQUISITOS) ESTA LLENO
        if (checkRepetidos.length() < 2 && !campo_actividad.getText().isEmpty() && !campo_duracion.getText().isEmpty() &&
                !campo_costo.getText().isEmpty()) {
            checkRepetidos = checkRepetidos.concat(campo_actividad.getText().toUpperCase());
            String act, pre, imp2;
            int dur, cost, imp;
            act = campo_actividad.getText().toUpperCase();
            pre = "";
            dur = Integer.parseInt(campo_duracion.getText());
            cost = Integer.parseInt(campo_costo.getText());
            imp = dur * cost;
            imp2 = String.valueOf(imp);
            CPM cpm = new CPM(act, pre, dur, cost, CPM.ConvertToDollar(imp2));
            tablaCPM.getItems().add(cpm);
            System.out.println(checkRepetidos);
            preReqList.addAll(act);
            boxPrereq.setItems(preReqList);



        }

        //esto chequea si algun campo esta vacio y manda un pop up diciendo que no se pueden dejar campos vacios
        else if (campo_actividad.getText().isEmpty() || boxPrereq.getSelectionModel().isEmpty() || campo_duracion.getText().isEmpty() ||
                campo_costo.getText().isEmpty()) {


            Alert alert = new Alert(Alert.AlertType.ERROR, "LLENAR TODOS LOS CAMPOS", ButtonType.OK);
            alert.showAndWait();


        }

        //EN CASO DE QUE SE INTRODUZCA EL MISMO NOMBRE DE ACTIVIDAD ANTERIOR
        else if (checkRepetidos.contains(campo_actividad.getText().toUpperCase())) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NO PUEDES USAR EL MISMO NOMBRE DE ACTIVIDAD", ButtonType.OK);
            alert.showAndWait();


        }


        //en caso de que no esten vacios, procede a llenar
        else {
            checkRepetidos = checkRepetidos.concat(campo_actividad.getText().toUpperCase());
            String act, pre, imp2;
            int dur, cost, imp;
            act = campo_actividad.getText().toUpperCase();
            pre = boxPrereq.getValue().toString().toUpperCase();
            dur = Integer.parseInt(campo_duracion.getText());
            cost = Integer.parseInt(campo_costo.getText());
            imp = dur * cost;
            imp2 = String.valueOf(imp);
            CPM cpm = new CPM(act, pre, dur, cost, CPM.ConvertToDollar(imp2));
            tablaCPM.getItems().add(cpm);
            preReqList.addAll(act);
            boxPrereq.setItems(preReqList);
            boxPrereq.getSelectionModel().clearSelection();

        }

        //Currency.getAvailableCurrencies();


    }


    @FXML
    void calcular(ActionEvent event) {


    }

    @FXML
    void reiniciar(ActionEvent event) {

    }


    @FXML
    void Controller(ActionEvent event) {


    }

    private void inicializarTablaCpm() {
        //EN ESTA PARTE RECORDAR QUE LOS CELLVALUEFACTORY LOS STRING'S NAME TIENEN QUE IR IGUAL QUE COMO EN LA CLASE(SIGUIENDO
        //LAS CONVENCIONES DE CASE SENSITIVE
        actividadC1.setCellValueFactory(new PropertyValueFactory<CPM, String>("Actividad"));
        prereqc1.setCellValueFactory(new PropertyValueFactory<CPM, String>("Prereq"));
        duracionc1.setCellValueFactory(new PropertyValueFactory<CPM, Integer>("Duracion"));
        costoc1.setCellValueFactory(new PropertyValueFactory<CPM, String>("Costo"));
        importec1.setCellValueFactory(new PropertyValueFactory<CPM, String>("Importe"));

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        inicializarTablaCpm();


        /*reiniciar.setVisible(false);
        calcular.setVisible(false);
        anadir.setVisible(true);*/
    }
}
