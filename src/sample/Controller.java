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
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    private String checkRepetidos = "-";
    private int nodosGrales = 2;
    private int actividadID = 1;
    private int importeWNoCurrency = 0;
    private int gastosAdministrativos;

    @FXML
    private TextField campo_actividad;

    @FXML
    private TextField campo_gastosAdmin;


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

    @FXML
    private TableColumn<CPM, Integer> NodeC1;

    @FXML
    private TableColumn<CPM, Integer> ActNumberC1;

    ObservableList<CPM> cpms;

    @FXML
    private TableView<CPMDetail> tablaCPM2;

    @FXML
    private TableColumn<CPMDetail, String> actividadC2;

    @FXML
    private TableColumn<CPMDetail, Integer> duracionC2;

    @FXML
    private TableColumn<CPMDetail, Integer> tiempoPrimeroC2;

    @FXML
    private TableColumn<CPMDetail, Integer> tiempoUltimoC2;

    @FXML
    private TableColumn<?, ?> criticaC2;

    @FXML
    private Text costoTotalResumen;


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
            int dur, cost, imp;
            act = campo_actividad.getText().toUpperCase();
            pre = "";

            dur = Integer.parseInt(campo_duracion.getText());
            cost = Integer.parseInt(campo_costo.getText());
            imp = dur * cost;
            imp2 = String.valueOf(imp);
            CPM cpm = new CPM(act, pre, dur, cost, CPM.ConvertToDollar(imp2), nodosGrales, actividadID);
            tablaCPM.getItems().add(cpm);
            System.out.println(checkRepetidos);
            preReqList.addAll(act);
            boxPrereq.setItems(preReqList);
            importeWNoCurrency += imp;
            actividadID++;
            gastosAdministrativos = Integer.parseInt(campo_gastosAdmin.getText());
            limpiarCampos();


        }

        //esto chequea si algun campo esta vacio(menos el de prereq) y manda un pop up diciendo que no se pueden dejar campos vacios
        else if (campo_actividad.getText().isEmpty() || campo_duracion.getText().isEmpty() ||
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
        else if (boxPrereq.getSelectionModel().isEmpty()) {
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
            CPM cpm = new CPM(act, pre, dur, cost, CPM.ConvertToDollar(imp2), nodosGrales, actividadID);
            actividadID++;
            tablaCPM.getItems().add(cpm);
            preReqList.addAll(act);
            importeWNoCurrency += imp;
           /* boxPrereq.setItems(preReqList);
            boxPrereq.getSelectionModel().clearSelection();*/
            limpiarCampos();


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
            CPM cpm = new CPM(act, pre, dur, cost, CPM.ConvertToDollar(imp2), nodosGrales, actividadID);
            tablaCPM.getItems().add(cpm);
            actividadID++;
            preReqList.addAll(act);
            boxPrereq.setItems(preReqList);
            boxPrereq.getSelectionModel().clearSelection();
            importeWNoCurrency += imp;
            limpiarCampos();



        }

        //Currency.getAvailableCurrencies();


    }


    @FXML
    void calcular(ActionEvent event) {
        int GA = gastosAdministrativos;

        //calculo total del proyecto
        costoTotalResumen.setText(CPM.ConvertToDollar(String.valueOf(importeWNoCurrency + GA)));

        //POBLAR COLUMN ACTS DE 2DA TABLA(actividad y duracion por ahora)
        for (int i = 0; i < tablaCPM.getItems().size(); i++) {
            String act = tablaCPM.getItems().get(i).getActividad();
            int dur = tablaCPM.getItems().get(i).getDuracion();
            CPMDetail cpmDetail = new CPMDetail(act, dur);
            tablaCPM2.getItems().add(cpmDetail);

        }
        limpiarCampos();


        CPM cpm2 = new CPM();
        final int[] totalProyecto = {0, 0, 0, 0, 0, 0};
//        final  int totalProyecto = 0;
        tablaCPM.getItems().forEach(cpm -> totalProyecto[0] += cpm.getDuracion());
        System.out.println("Duraction total:" + totalProyecto[0]);

        /*tablaCPM.getItems().forEach(cpm -> totalProyecto[1] +=cpm.getCosto());
        System.out.println("Costo total: " + totalProyecto[1]);*/

        /*tablaCPM.getItems().forEach(
                cpm -> totalProyecto[2] += Integer.parseInt(String.valueOf(cpm.actNumber))

        );*/

        System.out.println("COSTO TOTAL REAL CON SIGNOS: " + CPM.ConvertToDollar(String.valueOf(importeWNoCurrency)));
        System.out.println("COSTO TOTAL REAL SIN SIGNOS: " + importeWNoCurrency);
        System.out.println("Costo:" + totalProyecto[2]);


        //obtener numero de rows
        System.out.println("CANTIDAD DE FILAS/ROWS:" + tablaCPM.getItems().size());

        //obtener x celda en x numero de row
        for (int i = 0; i < tablaCPM.getItems().size(); i++) {
            System.out.println("duracion de act: " + tablaCPM.getItems().get(i).getDuracion());
            System.out.println("numero de actividad " + tablaCPM.getItems().get(i).actNumber);
            System.out.println("prerequisito:" + tablaCPM.getItems().get(i).getPrereq());

        }


        //utilizar este metodo para calcular los tiempos finales,tempranos...etc

        System.out.println("NODOS GENERALES: " + nodosGrales);


    }

    @FXML
    void reiniciar(ActionEvent event) {
        tablaCPM.getItems().clear();
        tablaCPM2.getItems().clear();
        limpiarCampos();



    }

    private void limpiarCampos() {
        campo_actividad.setText("");
        campo_duracion.setText("");
        campo_costo.setText("");
        campo_gastosAdmin.setText("");
        boxPrereq.getSelectionModel().clearSelection();
    }


    @FXML
    void Controller(ActionEvent event) {


    }

    private void inicializarTablaCpm() {
        //EN ESTA PARTE RECORDAR QUE LOS CELLVALUEFACTORY LOS STRING'S NAME TIENEN QUE IR IGUAL QUE COMO EN LA CLASE(SIGUIENDO
        //LAS CONVENCIONES DE CASE SENSITIVE
        tablaCPM.setPlaceholder(new Label("NO HAY DATOS, INTRODUZCA DATOS PARA EMPEZAR"));
        actividadC1.setCellValueFactory(new PropertyValueFactory<CPM, String>("Actividad"));
        prereqc1.setCellValueFactory(new PropertyValueFactory<CPM, String>("Prereq"));
        duracionc1.setCellValueFactory(new PropertyValueFactory<CPM, Integer>("Duracion"));
        costoc1.setCellValueFactory(new PropertyValueFactory<CPM, String>("Costo"));
        importec1.setCellValueFactory(new PropertyValueFactory<CPM, String>("Importe"));
        NodeC1.setCellValueFactory(new PropertyValueFactory<CPM, Integer>("Node"));
        ActNumberC1.setCellValueFactory(new PropertyValueFactory<CPM, Integer>("ActNumber"));


        tablaCPM2.setPlaceholder(new Label("NO HAY DATOS TODAVIA"));
        actividadC2.setCellValueFactory(new PropertyValueFactory<CPMDetail, String>("Actividad"));
        duracionC2.setCellValueFactory(new PropertyValueFactory<CPMDetail, Integer>("Duracion"));
        tiempoPrimeroC2.setCellValueFactory(new PropertyValueFactory<CPMDetail, Integer>("Tiempo Primero"));
        tiempoUltimoC2.setCellValueFactory(new PropertyValueFactory<CPMDetail, Integer>("Tiempo Ultimo"));
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
