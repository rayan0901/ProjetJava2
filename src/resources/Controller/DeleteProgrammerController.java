package resources.Controller;

import Projet.ActionDB;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;

/**
 * Contrôleur gérant les opérations de suppression de programmeurs.
 * Ce contrôleur est responsable de supprimer un programmeur de la base de données et
 * de mettre à jour l'interface utilisateur en conséquence.
 */
public class DeleteProgrammerController {

    // Référence vers le contrôleur principal (MainController).
    private MainController mainController;

    /**
     * Définit le MainController associé à ce contrôleur.
     * Le MainController est nécessaire pour effectuer des mises à jour globales de l'interface utilisateur.
     * @param mainController Référence vers le MainController.
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Supprime un programmeur de la base de données en utilisant son identifiant.
     * Après la suppression, l'interface utilisateur est mise à jour pour refléter les changements.
     * Une boîte d'alerte est affichée pour informer l'utilisateur de la réussite de l'opération.
     * @param id L'identifiant du programmeur à supprimer.
     */
    @FXML
    public void supprimerProgrammeur(int id) {

        // Supprime le programmeur par son identifiant.
        mainController.getBdd().deleteProgrammeurById(id);

        // Met à jour l'interface utilisateur.
        mainController.updatePrint();
        mainController.visibleFalseAll();
        mainController.getMainListePane().setVisible(true);

        // Affiche une boîte d'alerte pour informer l'utilisateur.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Le programmeur a été supprimé avec succès !");

        alert.showAndWait();
    }
}
