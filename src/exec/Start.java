package exec;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Start extends Application{

    public static void main(String[] args) {
        //launch(args);  // Méthode pour lancer l'application JavaFX
        Menu menu = new Menu();
        menu.execution();
        System.exit(0);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/fmxl/home.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../resources/CSS/Table.css").toExternalForm());
        primaryStage.setTitle("P.Manage");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}