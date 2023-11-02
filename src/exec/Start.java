package exec;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.helper;

import java.util.Scanner;


public class Start extends Application{

    public static void main(String[] args) {
        Scanner sc;

        sc = new Scanner(System.in);
        System.out.println("<<<<< BIENVENUE >>>>>");
        int choix;
        do {
            System.out.println("Comment lancer le programme ?");
            System.out.println("1. En console");
            System.out.println("2. Avec l'interface graphique");
            System.out.print("Choix : ");
            choix = helper.askInteger();
            switch (choix) {
                case 1:
                    Menu menu = new Menu();
                    menu.execution();
                    break;
                case 2:
                    launch(args);  // Méthode pour lancer l'application JavaFX
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        } while (choix != 1 && choix != 2);
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