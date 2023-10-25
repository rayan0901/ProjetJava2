package resources.Controller;

import Projet.ActionDB;
import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.sql.Statement;

public class SettingsController {

    private MainController mainController;
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void updateBdd(){
        ActionDB DataBdd;
        DataBdd = new ActionDB();
        DataBdd.getConnexion();

        mainController.updatePrint();
        mainController.getLabelNombreProgrammer().setText(DataBdd.getNumberOfProgrammerString());
        mainController.getLabelPrimeMoyen().setText(DataBdd.getPrimeMoyenneString() + "€");
        mainController.getLabelSalaireMoyen().setText(DataBdd.getSalaireMoyenString() + "€");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("La base de donnée à été mise à jour avec succès !");
        alert.showAndWait();
    }

    public void updateBdd(boolean print){
        ActionDB DataBdd;
        DataBdd = new ActionDB();
        DataBdd.getConnexion();

        mainController.updatePrint();
        mainController.getLabelNombreProgrammer().setText(DataBdd.getNumberOfProgrammerString());
        mainController.getLabelPrimeMoyen().setText(DataBdd.getPrimeMoyenneString() + "€");
        mainController.getLabelSalaireMoyen().setText(DataBdd.getSalaireMoyenString() + "€");
    }

    public void AllDeleteBdd() {
        ActionDB DataBdd;
        DataBdd = new ActionDB();
        DataBdd.getConnexion();

        Statement statement = DataBdd.getConnexion();
        String sql = "DELETE FROM programmeur";

        try {
            int rowsDeleted = statement.executeUpdate(sql);

            if (rowsDeleted > 0) {
                System.out.println("Tous les programmeurs ont été supprimés.");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Tous les programmeurs ont été supprimés !");
                alert.showAndWait();
            } else {
                System.out.println("Fail : aucun programmeur n'a été supprimé.");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Fail : aucun programmeur n'a été supprimé.!");

                alert.showAndWait();
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression des programmeurs : " + e.getMessage());
        }
        this.updateBdd(false);
    }

    public void addNProgrammer(String n)
    {
        ActionDB DataBdd;
        DataBdd = new ActionDB();
        DataBdd.getConnexion();
        int nombre;

        try
        {
            nombre = Integer.parseInt(n);
            if (nombre > 0 && nombre <= 1000)
            {
                DataBdd.fillBdd(nombre);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText(nombre + " programmeurs ont été ajoutés !");
                alert.showAndWait();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Le nombre doit être positif et inférieur à 1000.");
                alert.showAndWait();
            }
        }
        catch (NumberFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un nombre valide.");
            alert.showAndWait();
        }

        this.updateBdd(false);
    }


}
