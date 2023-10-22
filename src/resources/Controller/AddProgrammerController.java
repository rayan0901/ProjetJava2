package resources.Controller;

import Projet.ActionDB;
import Projet.Programmeur;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddProgrammerController {

    private MainController mainController;
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Ajoute un nouveau programmeur à la base de données.
     */
    @FXML
    public void ajouterProgrammeur() {
        String prenom = mainController.getFieldForname().getText();
        String nom = mainController.getFieldName().getText();
        String pseudo = mainController.getFieldPseudo().getText();
        String dateString = mainController.getFieldDateBorn().getText();
        String salaryString = mainController.getFieldSalary().getText();
        if (salaryString == null || salaryString.isEmpty()) {
            System.err.println("Salaire non spécifié");
            return;
        }
        float salaire = Float.parseFloat(salaryString);
        String primeString = mainController.getFieldPrime().getText();
        if (primeString == null || primeString.isEmpty()) {
            System.err.println("Prime non spécifié");
            return;
        }
        float prime = Float.parseFloat(mainController.getFieldPrime().getText());
        ActionDB dataDB = new ActionDB();

        dataDB.getConnexion();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date dateBorn = null;
        try {
            dateBorn = sdf.parse(dateString);
        } catch (ParseException e) {
            // Gérer l'erreur
            System.err.println("Erreur de format de date");
            return;
        }

        Programmeur p = new Programmeur(prenom, nom, dateBorn, salaire, prime, pseudo);
        dataDB.ajouterProgrammeurWithOut(p);
        mainController.updatePrint();

        // Vider les champs
        mainController.getFieldForname().clear();
        mainController.getFieldName().clear();
        mainController.getFieldPseudo().clear();
        mainController.getFieldDateBorn().clear();
        mainController.getFieldSalary().clear();
        mainController.getFieldPrime().clear();

        // Afficher un message de validation
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Le programmeur a été ajouté avec succès!");

        alert.showAndWait();
    }


}
