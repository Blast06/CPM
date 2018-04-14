package sample;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    private String checkRepetidos = "-";
    private int nodosGrales = 0;

    @FXML
    private TextField campo_actividad;


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


    private int posicionCpmEnTabla;

    @FXML
    private ComboBox boxPrereq;

    private ObservableList<String> preReqList = FXCollections.observableArrayList();

    @FXML
    void agregar(ActionEvent event) {


        //VERIFICA SI ES LA PRIMERA ACTIVIDAD QUE SE INGRESA, PARA ASI NO TENER QUE LLENAR EL PREREQUISITO y SI CUALQUIERA DE LOS
        // OTROS CAMPOS(SIN INCLUIR LOS PREREQUISITOS) ESTA LLENO
        if (checkRepetidos.length() < 2 && !campo_actividad.getText().isEmpty() && !campo_costo.getText().isEmpty() &&
                !campo_duracion.getText().isEmpty()) {


            checkRepetidos = checkRepetidos.concat(campo_actividad.getText().toUpperCase());
            String act, pre, imp2;
            int dur, cost, imp,singleNode;
            act = campo_actividad.getText().toUpperCase();
            pre = "";
            nodosGrales++;
            dur = Integer.parseInt(campo_duracion.getText());
            cost = Integer.parseInt(campo_costo.getText());
            imp = dur * cost;
            imp2 = String.valueOf(imp);
            CPM cpm = new CPM(act, pre, dur, cost, CPM.ConvertToDollar(imp2));
            cpm.node = nodosGrales;
            tablaCPM.getItems().add(cpm);
            System.out.println(checkRepetidos);
            preReqList.addAll(act);
            boxPrereq.setItems(preReqList);




        }

        //esto chequea si algun campo esta vacio y manda un pop up diciendo que no se pueden dejar campos vacios
        else if (campo_actividad.getText().isEmpty()  || campo_duracion.getText().isEmpty() ||
                campo_costo.getText().isEmpty()) {


            Alert alert = new Alert(Alert.AlertType.ERROR, "LLENAR TODOS LOS CAMPOS", ButtonType.OK);
            alert.showAndWait();


        }

        //EN CASO DE QUE SE INTRODUZCA EL MISMO NOMBRE DE ACTIVIDAD ANTERIOR
        else if (checkRepetidos.contains(campo_actividad.getText().toUpperCase())) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NO PUEDES USAR EL MISMO NOMBRE DE ACTIVIDAD", ButtonType.OK);
            alert.showAndWait();


        }


        //en caso de que no esten vacios y solo este el prereq vacio, procede a llenar
        else if (boxPrereq.getSelectionModel().isEmpty()){
            checkRepetidos = checkRepetidos.concat(campo_actividad.getText().toUpperCase());
            String act, pre, imp2;
            int dur, cost, imp;
            nodosGrales++;
            act = campo_actividad.getText().toUpperCase();
            pre = "";
            dur = Integer.parseInt(campo_duracion.getText());
            cost = Integer.parseInt(campo_costo.getText());
            imp = dur * cost;
            imp2 = String.valueOf(imp);
            CPM cpm = new CPM(act, pre, dur, cost, CPM.ConvertToDollar(imp2));
            cpm.node = nodosGrales;
            tablaCPM.getItems().add(cpm);
            preReqList.addAll(act);
           /* boxPrereq.setItems(preReqList);
            boxPrereq.getSelectionModel().clearSelection();*/

        }

        //EN CASO DE QUE LOS PREREQ SI ESTEN LLENOS
        else {
            checkRepetidos = checkRepetidos.concat(campo_actividad.getText().toUpperCase());
            String act, pre, imp2;
            int dur, cost, imp;
            nodosGrales++;
            act = campo_actividad.getText().toUpperCase();
            pre = boxPrereq.getValue().toString().toUpperCase();
            dur = Integer.parseInt(campo_duracion.getText());
            cost = Integer.parseInt(campo_costo.getText());
            imp = dur * cost;
            imp2 = String.valueOf(imp);
            CPM cpm = new CPM(act, pre, dur, cost, CPM.ConvertToDollar(imp2));
            cpm.node = nodosGrales;
            tablaCPM.getItems().add(cpm);
            preReqList.addAll(act);
            boxPrereq.setItems(preReqList);
            boxPrereq.getSelectionModel().clearSelection();

        }

        //Currency.getAvailableCurrencies();




    }


    @FXML
    void calcular(ActionEvent event) {
        int GA = 15000;
        CPM cpm2 = new CPM();
        final int[] totalProyecto = {0, 0, 0, 0, 0, 0};
//        final  int totalProyecto = 0;
        tablaCPM.getItems().forEach(cpm -> totalProyecto[0] += cpm.getDuracion());
        System.out.println("Duraction total:" + totalProyecto[0]);

        /*tablaCPM.getItems().forEach(cpm -> totalProyecto[1] +=cpm.getCosto());
        System.out.println("Costo total: " + totalProyecto[1]);*/

        tablaCPM.getItems().forEach(
                cpm -> totalProyecto[2] += cpm.getCosto()

        );
        System.out.println("Costo:" + totalProyecto[2]);


        //obtener numero de rows
        System.out.println(tablaCPM.getItems().size());

        //obtener x celda en x numero de row
        for (int i = 0; i <tablaCPM.getItems().size() ; i++) {
            System.out.println(tablaCPM.getItems().get(i).getDuracion());

        }


        //utilizar este metodo para calcular los tiempos finales,tempranos...etc

        System.out.println(nodosGrales);


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

        //PARA EVITAR QUE NO SE INGRESEN NUMEROS
        campo_costo.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    campo_costo.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        //PARA EVITAR QUE NO SE INGRESEN DATOS DIFERENTES A NUMEROS
        campo_duracion.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    campo_duracion.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });


        /*reiniciar.setVisible(false);
        calcular.setVisible(false);
        anadir.setVisible(true);*/
    }
}
