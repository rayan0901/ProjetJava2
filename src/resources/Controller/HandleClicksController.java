package resources.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class HandleClicksController {

    private MainController mainController;

    /**
     * Gère les clics sur les différents boutons de l'interface.
     * @param event L'événement déclenché lors du clic.
     */
    @FXML
    public void handleClicks(ActionEvent event) {

        if (event.getSource() == mainController.getBtnAccueil()) {
            mainController.updateBdd(false);
            mainController.visibleFalseAll();
            mainController.getDashboardPane().setVisible(true);
            mainController.getLabelStatus().setText("/Dashboard");
            mainController.getLabelStatusMini().setText("");
            mainController.getPaneStatus().setBackground(new Background(new BackgroundFill(Color.rgb(0,74,140), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else if (event.getSource() == mainController.getBtnProgrammeurs() || event.getSource() == mainController.getReturnListeBtn()) {
            mainController.visibleFalseAll();
            mainController.getMainListePane().setVisible(true);
            mainController.setCurrentPane(mainController.getMainListePane());
            mainController.getLabelStatus().setText("/Dashboard/Programmeurs");
            mainController.getLabelStatusMini().setText("Programmeurs");
            mainController.getPaneStatus().setBackground(new Background(new BackgroundFill(Color.rgb(25,103,116), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else if (event.getSource() == mainController.getBtnParamètres()) {
            mainController.visibleFalseAll();
            mainController.getSettingsPane().setVisible(true);
            mainController.getLabelStatus().setText("/Dashboard/Paramètres");
            mainController.getLabelStatusMini().setText("Paramètres");
            mainController.getPaneStatus().setBackground(new Background(new BackgroundFill(Color.rgb(62,124,89), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else if (event.getSource() == mainController.getBtnAddProgammeurs() || event.getSource() == mainController.getAddButtonProgrammeursListe()) {
            mainController.visibleFalseAll();
            mainController.getMainAddProgrammeur().setVisible(true);
            mainController.setCurrentPane(mainController.getMainAddProgrammeur());
            mainController.getLabelStatus().setText("/Dashboard/Ajouter");
            mainController.getLabelStatusMini().setText("Ajouter un programmeur");
            mainController.getPaneStatus().setBackground(new Background(new BackgroundFill(Color.rgb(112,101,19), CornerRadii.EMPTY, Insets.EMPTY)));
        }

    }

    /**
     * Ferme l'application lorsque le bouton 'Fermer' est cliqué.
     * @param event L'événement de clic de souris
     */
    @FXML
    private void handleClose(javafx.scene.input.MouseEvent event) {
        if (event.getSource() == mainController.getBtnClose()) {
            System.exit(0);
        }
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
