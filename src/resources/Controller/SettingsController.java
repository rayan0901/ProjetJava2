package resources.Controller;

import Projet.ActionDB;
import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe responsable de la gestion des paramètres et des opérations sur la base de données.
 */
public class SettingsController {

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
     * Met à jour les informations affichées à partir de la base de données.
     * Cette méthode rafraîchit également les informations sur l'interface utilisateur.
     */
    public void updateBdd(){
        mainController.updatePrint();
        mainController.getLabelNombreProgrammer().setText(mainController.getBdd().getNumberOfProgrammerString());
        mainController.getLabelPrimeMoyen().setText(mainController.getBdd().getPrimeMoyenneString() + "€");
        mainController.getLabelSalaireMoyen().setText(mainController.getBdd().getSalaireMoyenString() + "€");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("La base de donnée à été mise à jour avec succès !");
        alert.showAndWait();
    }

    /**
     * Surcharge de la méthode {@code updateBdd}, avec un paramètre pour gérer l'affichage.
     *
     * @param print indicateur pour savoir si l'opération doit être imprimée.
     */
    public void updateBdd(boolean print){
        mainController.updatePrint();
        mainController.getLabelNombreProgrammer().setText(mainController.getBdd().getNumberOfProgrammerString());
        mainController.getLabelPrimeMoyen().setText(mainController.getBdd().getPrimeMoyenneString() + "€");
        mainController.getLabelSalaireMoyen().setText(mainController.getBdd().getSalaireMoyenString() + "€");
    }

    /**
     * Supprime tous les programmeurs de la base de données.
     * Une boîte de dialogue d'information ou d'erreur apparaît pour indiquer le résultat de l'opération.
     */
    public void AllDeleteBdd() {
        Statement statement = mainController.getBdd().getConnexion();
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

    /**
     * Ajoute un certain nombre de programmeurs à la base de données.
     * Une boîte de dialogue d'information ou d'erreur apparaît pour indiquer le résultat de l'opération.
     *
     * @param n le nombre de programmeurs à ajouter.
     */
    public void addNProgrammer(String n)
    {
        int nombre;

        try
        {
            nombre = Integer.parseInt(n);
            if (nombre > 0 && nombre <= 1000)
            {
                mainController.getBdd().fillBdd(nombre);
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
