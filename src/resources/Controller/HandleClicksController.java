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
            mainController.getMainAddProgrammeur().setVisible(false);
            mainController.getMainListePane().setVisible(false);
            mainController.getEditProgrammeurPane().setVisible(false);
            mainController.getShowProgrammeur().setVisible(false);
            mainController.getDashboardPane().setVisible(true);
            mainController.getLabelStatus().setText("/Dashboard");
            mainController.getLabelStatusMini().setText("");
            mainController.getPaneStatus().setBackground(new Background(new BackgroundFill(Color.rgb(0,74,140), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else if (event.getSource() == mainController.getBtnProgrammeurs() || event.getSource() == mainController.getReturnListeBtn()) {
            mainController.getMainAddProgrammeur().setVisible(false);
            mainController.getMainListePane().setVisible(false);
            mainController.getEditProgrammeurPane().setVisible(false);
            mainController.getShowProgrammeur().setVisible(false);
            mainController.getDashboardPane().setVisible(false);
            mainController.getMainListePane().setVisible(true);
            mainController.setCurrentPane(mainController.getMainListePane());
            mainController.getLabelStatus().setText("/Dashboard/Programmeurs");
            mainController.getLabelStatusMini().setText("Programmeurs");
            mainController.getPaneStatus().setBackground(new Background(new BackgroundFill(Color.rgb(25,103,116), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else if (event.getSource() == mainController.getBtnParamètres()) {
            mainController.getMainAddProgrammeur().setVisible(false);
            mainController.getMainListePane().setVisible(false);
            mainController.getEditProgrammeurPane().setVisible(false);
            mainController.getShowProgrammeur().setVisible(false);
            mainController.getDashboardPane().setVisible(false);
            mainController.getLabelStatus().setText("/Dashboard/Paramètres");
            mainController.getLabelStatusMini().setText("Paramètres");
            mainController.getPaneStatus().setBackground(new Background(new BackgroundFill(Color.rgb(62,124,89), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else if (event.getSource() == mainController.getBtnAddProgammeurs() || event.getSource() == mainController.getAddButtonProgrammeursListe()) {
            mainController.getMainAddProgrammeur().setVisible(false);
            mainController.getMainListePane().setVisible(false);
            mainController.getEditProgrammeurPane().setVisible(false);
            mainController.getShowProgrammeur().setVisible(false);
            mainController.getDashboardPane().setVisible(false);
            mainController.getMainAddProgrammeur().setVisible(true);
            mainController.setCurrentPane(mainController.getMainAddProgrammeur());
            mainController.getLabelStatus().setText("/Dashboard/Ajouter");
            mainController.getLabelStatusMini().setText("Ajouter un programmeur");
            mainController.getPaneStatus().setBackground(new Background(new BackgroundFill(Color.rgb(112,101,19), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else if (event.getSource() == mainController.getBtnDeleteProgammeurs() || event.getSource() == mainController.getDeleteButtonProgrammeursListe()) {
            mainController.getMainAddProgrammeur().setVisible(false);
            mainController.getMainListePane().setVisible(false);
            mainController.getEditProgrammeurPane().setVisible(false);
            mainController.getShowProgrammeur().setVisible(false);
            mainController.getDashboardPane().setVisible(false);
            mainController.getLabelStatus().setText("/Dashboard/Supprimer");
            mainController.getLabelStatusMini().setText("Supprimer un programmeur");
            mainController.getPaneStatus().setBackground(new Background(new BackgroundFill(Color.rgb(120,124,191), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else if (event.getSource() == mainController.getBtnEditProgammeurs()) {
            mainController.getMainAddProgrammeur().setVisible(false);
            mainController.getMainListePane().setVisible(false);
            mainController.getEditProgrammeurPane().setVisible(false);
            mainController.getShowProgrammeur().setVisible(false);
            mainController.getDashboardPane().setVisible(false);
            mainController.getLabelStatus().setText("/Dashboard/Modifier");
            mainController.getLabelStatusMini().setText("Modifier un programmeur");
            mainController.getPaneStatus().setBackground(new Background(new BackgroundFill(Color.rgb(150, 43, 9), CornerRadii.EMPTY, Insets.EMPTY)));
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
