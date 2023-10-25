package resources.Controller;

import Projet.ActionDB;
import Projet.Programmeur;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.text.SimpleDateFormat;

public class ShowProgrammerController {


    private MainController mainController;

    /**
     * Affiche les détails d'un programmeur spécifié par son identifiant.
     * @param id L'identifiant du programmeur
     */
    public void showProgrammeur(int id) {
        ActionDB dataDB = new ActionDB();
        dataDB.getConnexion();
        Programmeur p = dataDB.getProgrammeurById(id);

        if (p != null) {
            mainController.getFieldFornameShow().setText(p.getPrenom());
            mainController.getFieldNameShow().setText(p.getNom());
            mainController.getFieldPseudoShow().setText(p.getPseudo());
            mainController.getFieldDateBornShow().setText(new SimpleDateFormat("dd-MM-yyyy").format(p.getDate()));
            mainController.getFieldSalaryShow().setText(String.valueOf(p.getSalaire()));
            mainController.getFieldPrimeShow().setText(String.valueOf(p.getPrime()));


            // Change le Pane visible
            mainController.visibleFalseAll();
            mainController.setCurrentPane(mainController.getShowProgrammeur());
            mainController.getShowProgrammeur().setVisible(true);
        } else {
            // Affiche une alerte si le programmeur n'est pas trouvé
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Programmeur non trouvé!");

            alert.showAndWait();
        }
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

}
