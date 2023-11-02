package resources.Controller;

import Projet.ActionDB;
import Projet.Programmeur;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Contrôleur pour gérer la modification des informations des programmeurs.
 * Il est responsable de la mise à jour des détails d'un programmeur dans la base de données
 * et également de l'affichage des champs de saisie pour la modification.
 */
public class EditProgrammerController {

    @FXML private TextField fieldNameEdit;
    @FXML private TextField fieldFornameEdit;
    @FXML private TextField fieldPseudoEdit;
    @FXML private TextField FieldDateBornEdit;
    @FXML private TextField fieldSalaryEdit;
    @FXML private TextField fieldPrimeEdit;

    @FXML private GridPane MainListePane;
    @FXML private GridPane editProgrammeurPane;

    // Référence vers le contrôleur principal
    private MainController mainController;

    private GridPane CurrentPane;

    private int idCourant = -1;

    /**
     * Définit le contrôleur principal et initialise les champs.
     * @param mainController Référence vers le MainController.
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        this.idCourant = mainController.getIdCourant();
        this.CurrentPane = mainController.getCurrentPane();
    }

    /**
     * Met à jour les informations du MainController.
     */
    public void updateMainController() {
        this.idCourant = mainController.getIdCourant();
        this.CurrentPane = mainController.getCurrentPane();
        this.fieldNameEdit = mainController.getFieldNameEdit();
        this.fieldFornameEdit = mainController.getFieldFornameEdit();
        this.fieldPseudoEdit = mainController.getFieldPseudoEdit();
        this.FieldDateBornEdit = mainController.getFieldDateBornEdit();
        this.fieldSalaryEdit = mainController.getFieldSalaryEdit();
        this.fieldPrimeEdit = mainController.getFieldPrimeEdit();
    }


    /**
     * Modifie un programmeur existant dans la base de données.
     * Lit les informations depuis les champs de texte et met à jour l'enregistrement correspondant.
     */
    @FXML
    public void modifierProgrammeur() {
        updateMainController();
        int id = idCourant;

        String nom = fieldNameEdit.getText();
        String prenom = fieldFornameEdit.getText();
        String pseudo = fieldPseudoEdit.getText();
        String dateString = FieldDateBornEdit.getText();

        String salaryString = fieldSalaryEdit.getText();
        if (salaryString == null || salaryString.isEmpty()) {
            System.err.println("Salaire non spécifié");
            return;
        }
        float salaire = Float.parseFloat(salaryString);

        String primeString = fieldPrimeEdit.getText();
        if (primeString == null || primeString.isEmpty()) {
            System.err.println("Prime non spécifié");
            return;
        }
        float prime = Float.parseFloat(primeString);

        ActionDB dataDB = new ActionDB();
        Statement statement = dataDB.getConnexion();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date dateNaissance = null;

        try {
            dateNaissance = sdf.parse(dateString);
        } catch (ParseException e) {
            System.err.println("Erreur de format de date");
            return;
        }

        dataDB.updateProgrammeurById(id, nom, prenom, dateNaissance, salaire, prime);

        // Vider les champs après la mise à jour
        fieldNameEdit.clear();
        fieldFornameEdit.clear();
        fieldPseudoEdit.clear();
        FieldDateBornEdit.clear();
        fieldSalaryEdit.clear();
        fieldPrimeEdit.clear();

        // Afficher un message de validation
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Le programmeur a été modifié avec succès!");

        mainController.updatePrint();
        mainController.visibleFalseAll();
        CurrentPane = this.mainController.getMainListePane();
        CurrentPane.setVisible(true);
        alert.showAndWait();
    }

    /**
     * Navigue vers la page de modification pour un programmeur spécifié.
     * Charge les informations du programmeur dans les champs de texte pour modification.
     */
    @FXML
    public void goToModifierProgrammeur() {
        updateMainController();
        mainController.visibleFalseAll();
        CurrentPane = this.mainController.getMainListePane();
        CurrentPane.setVisible(false);
        ActionDB dataDB = new ActionDB();
        dataDB.getConnexion();
        Programmeur p = dataDB.getProgrammeurById(idCourant);

        if (p != null) {
            if (p.getPrenom() != null) {
                fieldFornameEdit.setText(p.getPrenom());
            } else {
                fieldFornameEdit.setText("");
            }

            if (p.getNom() != null) {
                fieldNameEdit.setText(p.getNom());
            } else {
                fieldNameEdit.setText("");
            }

            if (p.getPseudo() != null) {
                fieldPseudoEdit.setText(p.getPseudo());
            } else {
                fieldPseudoEdit.setText("");
            }

            if (p.getDate() != null) {
                FieldDateBornEdit.setText(new SimpleDateFormat("dd-MM-yyyy").format(p.getDate()));
            } else {
                FieldDateBornEdit.setText("");
            }

            if (p.getSalaire() != -1) {
                fieldSalaryEdit.setText(String.valueOf(p.getSalaire()));
            } else {
                fieldSalaryEdit.setText("");
            }

            if (p.getPrime() != -1) {
                fieldPrimeEdit.setText(String.valueOf(p.getPrime()));
            } else {
                fieldPrimeEdit.setText("");
            }



            // Change le Pane visible
            mainController.visibleFalseAll();
            CurrentPane = this.mainController.getEditProgrammeurPane();
            CurrentPane.setVisible(true);
        } else {
            // Affiche une alerte si le programmeur n'est pas trouvé
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Programmeur non trouvé!");

            alert.showAndWait();
        }
    }
}
