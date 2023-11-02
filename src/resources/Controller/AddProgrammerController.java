package resources.Controller;

import Projet.ActionDB;
import Projet.Programmeur;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe responsable de l'ajout de nouveaux programmeurs à la base de données.
 */
public class AddProgrammerController {

    // Référence vers le contrôleur principal
    private MainController mainController;

    /**
     * Définit le contrôleur principal pour cette classe.
     *
     * @param mainController le contrôleur principal.
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Méthode FXML pour ajouter un nouveau programmeur à la base de données.
     * Cette méthode est déclenchée lors de l'appui sur le bouton "Ajouter Programmeur" de l'interface utilisateur.
     * Elle récupère les valeurs des champs, les valide, puis ajoute le nouveau programmeur.
     * Un message d'information s'affiche à la fin pour confirmer l'ajout.
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

        // Création de l'objet Programmeur
        Programmeur p = new Programmeur(prenom, nom, dateBorn, salaire, prime, pseudo);

        // Appel à la méthode d'ajout de la classe ActionDB
        dataDB.ajouterProgrammeurWithOut(p);

        // Mise à jour de l'affichage
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
