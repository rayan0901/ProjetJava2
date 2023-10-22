package resources.Controller;

import Projet.ActionDB;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;

public class DeleteProgrammerController {
    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Supprime un programmeur de la base de données.
     */
    @FXML
    public void supprimerProgrammeur(int id) {
        ActionDB dataDB = new ActionDB();
        dataDB.getConnexion();
        dataDB.deleteProgrammeurById(id);

        mainController.updatePrint();
        mainController.getMainAddProgrammeur().setVisible(false);
        mainController.getMainListePane().setVisible(false);
        mainController.getCurrentPane().setVisible(false);
        mainController.getShowProgrammeur().setVisible(false);
        mainController.getMainListePane().setVisible(true);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Le programmeur a été supprimé avec succès !");

        alert.showAndWait();
    }
}
