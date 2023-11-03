package exec;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.helper;

import java.util.Scanner;

/**
 * La classe Start est l'entrée principale de l'application.
 * Elle hérite de la classe Application de JavaFX et permet de lancer l'application
 * soit en mode console, soit avec une interface graphique.
 */
public class Start extends Application{

    /**
     * Le point d'entrée principal de l'application en version graphique.
     * @param args Les arguments de ligne de commande passés au programme.
     */
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
    /**
     * Méthode de démarrage de l'application JavaFX qui initialise et affiche la fenêtre principale.
     * @param primaryStage Le stage principal de l'application JavaFX.
     * @throws Exception En cas d'échec du chargement du fichier FXML.
     */
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