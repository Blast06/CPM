package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
        Parent root = FXMLLoader.load(getClass().getResource("View/sample.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/sample/styles/btnStyles.css");

        //ResponsiveHandler.addResponsiveToWindow(primaryStage);


//        Image image = new Image("sample/icons/cal.png");
//        primaryStage.getIcons().add(image);


        primaryStage.setTitle("CPM");
        primaryStage.setScene(scene);
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
