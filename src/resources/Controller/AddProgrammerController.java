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
        String salaryString = mainController.getFieldSalary().getText().replace(',', '.');
        String primeString = mainController.getFieldPrime().getText().replace(',', '.');
        String dateString = mainController.getFieldDateBorn().getText();

        if (validateInputs(prenom, nom, pseudo, salaryString, primeString, dateString)) {
            Date dateBorn;
            try {
                dateBorn = new SimpleDateFormat("dd-MM-yyyy").parse(dateString);
            } catch (ParseException e) {
                System.err.println("Erreur de format de date");
                return;
            }

            Programmeur p = new Programmeur(prenom, nom, dateBorn, Float.parseFloat(salaryString), Float.parseFloat(primeString), pseudo);
            mainController.getBdd().ajouterProgrammeurWithOut(p);
            mainController.updatePrint();
            clearAllField();
            alertSuccessAdd();
        }
    }

    /**
     * Valide les entrées du formulaire pour l'ajout d'un programmeur.
     */
    boolean validateInputs(String prenom, String nom, String pseudo, String salary, String prime, String date) {
        if (salary.isEmpty() || prime.isEmpty()) {
            System.err.println("Salaire ou Prime non spécifié");
            return false;
        }
        if (!validateString(prenom) || !validateString(nom) || !validateString(pseudo)) {
            alertError("Entrez uniquement des lettres, espaces, tirets ou apostrophes pour les noms et pseudos.");
            return false;
        }
        if (!validateDecimal(salary) || !validateDecimal(prime)) {
            alertError("Entrez un montant valide.");
            return false;
        }
        if (!date.matches("^\\d{2}-\\d{2}-\\d{4}$")) {
            alertError("Entrez une date valide au format dd-MM-yyyy.");
            return false;
        }
        return true;
    }

    /**
     * Valide une chaîne de caractères pour s'assurer qu'elle contient uniquement des lettres, espaces, tirets ou apostrophes.
     *
     * @param s La chaîne à valider.
     * @return boolean Indique si la chaîne est valide.
     */
    private boolean validateString(String s) {
        return s.matches("^[a-zA-Z\\s-'’]+$");
    }

    /**
     * Valide une chaîne de caractères pour s'assurer qu'elle représente un nombre décimal.
     *
     * @param s La chaîne à valider.
     * @return boolean Indique si la chaîne est valide.
     */
    private boolean validateDecimal(String s) {
        return s.matches("^\\d+\\.\\d{1,2}$");
    }

    /**
     * Vide tous les champs du formulaire.
     */
    public void clearAllField() {
        // Vider les champs
        mainController.getFieldForname().clear();
        mainController.getFieldName().clear();
        mainController.getFieldPseudo().clear();
        mainController.getFieldDateBorn().clear();
        mainController.getFieldSalary().clear();
        mainController.getFieldPrime().clear();
    }

    /**
     * Affiche une alerte indiquant que le programmeur a été ajouté avec succès.
     */
    public void alertSuccessAdd() {
        // Afficher un message de validation
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Le programmeur a été ajouté avec succès!");

        alert.showAndWait();
    }

    /**
     * Affiche une alerte d'erreur avec un message spécifique.
     *
     * @param message Le message d'erreur à afficher.
     */
    public void alertError(String message)
    {
        Alert alert;
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
