package resources.Controller;
import Projet.Programmeur;
import Projet.ActionDB;

import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import javafx.scene.input.MouseEvent;

import java.net.URL;
import javafx.fxml.FXML;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.shape.Circle;
import javafx.event.EventHandler;

/**
 * Classe principale du contrôleur qui gère l'initialisation et la navigation entre les différents panneaux de l'interface.
 * <p>
 * Cette classe est responsable de l'initialisation des différents contrôleurs de panneau et fournit les méthodes nécessaires pour naviguer entre eux.
 * </p>
 */
public class MainController implements Initializable {

    //Attention tous les éléments (variables) intéractifs de scene builder sont à la fin du fichier pour facilité la lecture
    /**
     * Liste filtrée des programmeurs. Cette liste est utilisée pour appliquer des filtres de recherche
     * sur les données avant de les afficher dans la table.
     */
    private FilteredList<Programmeur> filteredData;

    /**
     * Liste triée des programmeurs. Cette liste est une version triée de filteredData et est liée directement
     * à l'affichage dans la table.
     */
    private SortedList<Programmeur> sortedData;

    /**
     * ID du programmeur actuellement sélectionné dans l'interface utilisateur.
     * Initialisé à -1 pour indiquer qu'aucun programmeur n'est sélectionné au départ.
     */
    private int idCourant = -1;

    /**
     * Pane actuel dans l'interface utilisateur. Utilisé pour manipuler les éléments de l'interface
     * associés au programmeur actuellement sélectionné.
     */
    private GridPane CurrentPane;

    /**
     * Contrôleur pour gérer l'ajout de programmeurs.
     */
    @FXML
    private AddProgrammerController addProgrammerController = new AddProgrammerController();

    /**
     * Contrôleur pour gérer l'édition de programmeurs.
     */
    @FXML
    private EditProgrammerController editProgrammerController = new EditProgrammerController();

    /**
     * Contrôleur pour gérer l'affichage des détails d'un programmeur.
     */
    @FXML
    private ShowProgrammerController showProgrammerController = new ShowProgrammerController();

    /**
     * Contrôleur pour gérer la barre de recherche.
     */
    @FXML
    private SearchBarController searchBarController = new SearchBarController();

    /**
     * Contrôleur pour gérer la suppression de programmeurs.
     */
    @FXML
    private DeleteProgrammerController deleteProgrammerController = new DeleteProgrammerController();

    /**
     * Contrôleur pour gérer les clics sur l'interface.
     */
    @FXML
    private HandleClicksController handleClicksController = new HandleClicksController();

    /**
     * Contrôleur pour gérer les paramètres de l'application.
     */
    @FXML
    private SettingsController settingsController = new SettingsController();



    /**
     * Initialise l'interface utilisateur et prépare les composants nécessaires pour l'affichage et l'interaction.
     * Cette méthode est appelée automatiquement lors du lancement de l'application.
     * <p>
     * Elle réalise plusieurs tâches:
     * <ul>
     *   <li>Établir une connexion à la base de données via {@code ActionDB}.</li>
     *   <li>Initialiser les labels statistiques de l'application (nombre de programmeurs, prime moyenne, etc.).</li>
     *   <li>Configurer l'avatar avec une forme circulaire.</li>
     *   <li>Initialiser les colonnes des tableaux et les lier aux propriétés des objets {@code Programmeur}.</li>
     *   <li>Configurer les données triées et filtrées pour les tableaux.</li>
     *   <li>Initialiser la barre de recherche.</li>
     *   <li>Configurer les contrôleurs pour diverses actions (ajout, modification, affichage et suppression de programmeurs).</li>
     * </ul>
     * </p>
     *
     * @param location   URL du fichier FXML qui a été chargé.
     * @param resources  Le bundle de ressources qui a été donné au FXMLLoader.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Établir la connexion à la base de données
        ActionDB DataBdd;
        DataBdd = new ActionDB();
        DataBdd.getConnexion();

        // Masquer tous les panneaux et rendre visible le panneau de tableau de bord
        visibleFalseAll();
        dashboardPane.setVisible(true);

        // Initialiser les statistiques affichées sur le tableau de bord
        labelNombreProgrammer.setText(DataBdd.getNumberOfProgrammerString());
        labelPrimeMoyen.setText(DataBdd.getPrimeMoyenneString() + "€");
        labelSalaireMoyen.setText(DataBdd.getSalaireMoyenString() + "€");

        // Configurer l'avatar de mon compte pour être un cercle
        Circle clipCircle;
        clipCircle = new Circle();
        clipCircle.setCenterX(avatarImageView.getFitWidth() / 2);
        clipCircle.setCenterY(avatarImageView.getFitHeight() / 2);
        clipCircle.setRadius(avatarImageView.getFitWidth() / 2);
        avatarImageView.setClip(clipCircle);

        // Initialiser les colonnes de la table des programmeurs pour correspond à la bdd
        createAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        updateAt.setCellValueFactory(new PropertyValueFactory<>("updateAt"));
        DATE_DE_NAISSANCE.setCellValueFactory(new PropertyValueFactory<>("date"));
        NOM.setCellValueFactory(new PropertyValueFactory<>("nom"));
        PRENOM.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        PRIME.setCellValueFactory(new PropertyValueFactory<>("prime"));
        PSEUDO.setCellValueFactory(new PropertyValueFactory<>("pseudo"));
        SALAIRE.setCellValueFactory(new PropertyValueFactory<>("salaire"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        // Remplir la table de pane avec des données et configurer la recherche et le tri
        data = FXCollections.observableArrayList(DataBdd.recupProgrammeurs());
        filteredData = new FilteredList<>(data, p -> true);
        searchBarController.setMainController(this);
        sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableProgrammeurs.comparatorProperty());
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filtrerData(newValue);
        });
        tableProgrammeurs.setItems(sortedData);


        // Initialiser la table du tableau de bord
        NOMDASH.setCellValueFactory(new PropertyValueFactory<>("nom"));
        PRENOMDASH.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        PSEUDODASH.setCellValueFactory(new PropertyValueFactory<>("pseudo"));
        CreateAtDash.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        tableProgrammeursDashBoard.setItems(FXCollections.observableArrayList(DataBdd.getDerniersProgrammeurs()));


        // Configurer l'écouteur pour double-clic sur une ligne de la table
        tableProgrammeurs.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    Programmeur selectedProgrammeur = tableProgrammeurs.getSelectionModel().getSelectedItem();
                    if (selectedProgrammeur != null) {
                        int selectedId = selectedProgrammeur.getId();
                        idCourant = selectedId;
                        showProgrammeur(selectedId);
                    }
                }
            }
        });

        // Définir ce contrôleur comme contrôleur principal pour d'autres contrôleurs. Pareille pour les autres
        addProgrammerController.setMainController(this);
        editProgrammerController.setMainController(this);
        showProgrammerController.setMainController(this);
        deleteProgrammerController.setMainController(this);
        handleClicksController.setMainController(this);
        settingsController.setMainController(this);
    }

    /**
     * Met à jour la liste des programmeurs affichés dans le tableau.
     * Récupère les données les plus récentes de la base de données
     * et actualise la vue.
     */
    public void updatePrint() {
        ActionDB dataDB = new ActionDB();

        dataDB.getConnexion();
        data.clear();  // Supprime les anciennes données
        data.addAll(dataDB.recupProgrammeurs());  // Ajoute les nouvelles données

        // Tri et filtre les données à nouveau si nécessaire
        sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableProgrammeurs.comparatorProperty());

        // Met à jour le tableau
        tableProgrammeurs.setItems(sortedData);
    }

    /**
     * Rend tous les panneaux principaux invisibles.
     */
    public void visibleFalseAll() {
        getMainAddProgrammeur().setVisible(false);
        getMainListePane().setVisible(false);
        getEditProgrammeurPane().setVisible(false);
        getShowProgrammeur().setVisible(false);
        getDashboardPane().setVisible(false);
        getSettingsPane().setVisible(false);

    }


    /*****************************************************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************************************************/
    /******************************************************REDIRECTION************************************************************************************************************************/
    /*****************************************************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************************************************/

    /**
     * Redirige l'action d'ajout de programmeur vers le contrôleur AddProgrammerController.
     */
    public void ajouterProgrammeur() {
        addProgrammerController.ajouterProgrammeur();
    }

    /**
     * Redirige l'action de filtrage de données vers le contrôleur SearchBarController.
     * @param terme Le terme à utiliser pour le filtrage.
     */
    public void filtrerData(String terme) {
        searchBarController.filtrerData(terme);
    }

    /**
     * Redirige l'action de modification de programmeur vers le contrôleur EditProgrammerController.
     */
    public void modifierProgrammeur() {
        editProgrammerController.modifierProgrammeur();
    }

    /**
     * Redirige vers la page de modification de programmeur via le contrôleur EditProgrammerController.
     */
    public void goToModifierProgrammeur() {
        editProgrammerController.goToModifierProgrammeur();
    }

    /**
     * Redirige l'action d'affichage d'un programmeur spécifique vers le contrôleur ShowProgrammerController.
     * @param id L'identifiant du programmeur à afficher.
     */
    public void showProgrammeur(int id) {
        showProgrammerController.showProgrammeur(id);
    }

    /**
     * Redirige l'action de suppression de programmeur vers le contrôleur DeleteProgrammerController.
     */
    @FXML
    public void supprimerProgrammeur() {
        deleteProgrammerController.supprimerProgrammeur(idCourant);
    }

    /**
     * Redirige les actions de clic sur l'interface vers le contrôleur HandleClicksController.
     * @param event L'événement de clic déclenché.
     */
    @FXML
    public void handleClicks(ActionEvent event) {
        handleClicksController.handleClicks(event);
    }

    /**
     * Redirige l'action de mise à jour de la base de données vers le contrôleur SettingsController.
     */
    @FXML
    public void updateBdd() {
        settingsController.updateBdd();
    }

    /**
     * Redirige l'action de suppression de toutes les données de la base de données vers le contrôleur SettingsController.
     */
    @FXML
    public void AllDeleteBdd() {
        settingsController.AllDeleteBdd();
    }

    /**
     * Redirige l'action d'ajout de N programmeurs vers le contrôleur SettingsController.
     */
    @FXML
    public void addNProgrammer() {
        settingsController.addNProgrammer(valueAddProgSet.getText());
    }


    /*****************************************************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************************************************/
    /**********************************************************************************GETTERS AND SETTERS************************************************************************************/
    /*****************************************************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************************************************/

    public Label getLabelNombreProgrammer() {
        return labelNombreProgrammer;
    }

    public void setLabelNombreProgrammer(Label labelNombreProgrammer) {
        this.labelNombreProgrammer = labelNombreProgrammer;
    }

    public GridPane getSettingsPane() {
        return settingsPane;
    }

    public void setSettingsPane(GridPane settingsPane) {
        this.settingsPane = settingsPane;
    }
    public int getIdCourant() {
        return idCourant;
    }

    public GridPane getCurrentPane() {
        return CurrentPane;
    }

    public void setCurrentPane(GridPane currentPane) {
        CurrentPane = currentPane;
    }

    public TextField getFieldNameEdit() {
        return fieldNameEdit;
    }

    public void setFieldNameEdit(TextField fieldNameEdit) {
        this.fieldNameEdit = fieldNameEdit;
    }

    public TextField getFieldFornameEdit() {
        return fieldFornameEdit;
    }

    public void setFieldFornameEdit(TextField fieldFornameEdit) {
        this.fieldFornameEdit = fieldFornameEdit;
    }

    public TextField getFieldPseudoEdit() {
        return fieldPseudoEdit;
    }

    public void setFieldPseudoEdit(TextField fieldPseudoEdit) {
        this.fieldPseudoEdit = fieldPseudoEdit;
    }

    public TextField getFieldDateBornEdit() {
        return FieldDateBornEdit;
    }

    public void setFieldDateBornEdit(TextField fieldDateBornEdit) {
        this.FieldDateBornEdit = fieldDateBornEdit;
    }

    public TextField getFieldSalaryEdit() {
        return fieldSalaryEdit;
    }

    public void setFieldSalaryEdit(TextField fieldSalaryEdit) {
        this.fieldSalaryEdit = fieldSalaryEdit;
    }

    public TextField getFieldPrimeEdit() {
        return fieldPrimeEdit;
    }

    public void setFieldPrimeEdit(TextField fieldPrimeEdit) {
        this.fieldPrimeEdit = fieldPrimeEdit;
    }

    public Pane getPaneStatus() {
        return PaneStatus;
    }

    public void setPaneStatus(Pane paneStatus) {
        PaneStatus = paneStatus;
    }

    public Button getBtnAccueil() {
        return btnAccueil;
    }

    public void setBtnAccueil(Button btnAccueil) {
        this.btnAccueil = btnAccueil;
    }

    public Button getBtnParamètres() {
        return btnParamètres;
    }

    public void setBtnParamètres(Button btnParamètres) {
        this.btnParamètres = btnParamètres;
    }

    public Button getBtnProgrammeurs() {
        return btnProgrammeurs;
    }

    public void setBtnProgrammeurs(Button btnProgrammeurs) {
        this.btnProgrammeurs = btnProgrammeurs;
    }

    public Label getLabelStatus() {
        return labelStatus;
    }

    public void setLabelStatus(Label labelStatus) {
        this.labelStatus = labelStatus;
    }

    public Label getLabelStatusMini() {
        return labelStatusMini;
    }

    public void setLabelStatusMini(Label labelStatusMini) {
        this.labelStatusMini = labelStatusMini;
    }

    public Button getBtnAddProgammeurs() {
        return btnAddProgammeurs;
    }

    public void setBtnAddProgammeurs(Button btnAddProgammeurs) {
        this.btnAddProgammeurs = btnAddProgammeurs;
    }

    public Button getBtnEditProgammeurs() {
        return btnEditProgammeurs;
    }

    public void setBtnEditProgammeurs(Button btnEditProgammeurs) {
        this.btnEditProgammeurs = btnEditProgammeurs;
    }

    public Button getReturnListeBtn() {
        return returnListeBtn;
    }

    public void setReturnListeBtn(Button returnListeBtn) {
        this.returnListeBtn = returnListeBtn;
    }

    public Button getEditBtnShow() {
        return editBtnShow;
    }

    public void setEditBtnShow(Button editBtnShow) {
        this.editBtnShow = editBtnShow;
    }

    public Button getDeleteButtonProgrammeursListe() {
        return deleteButtonProgrammeursListe;
    }

    public void setDeleteButtonProgrammeursListe(Button deleteButtonProgrammeursListe) {
        this.deleteButtonProgrammeursListe = deleteButtonProgrammeursListe;
    }

    public Button getAddButtonProgrammeursListe() {
        return addButtonProgrammeursListe;
    }

    public void setAddButtonProgrammeursListe(Button addButtonProgrammeursListe) {
        this.addButtonProgrammeursListe = addButtonProgrammeursListe;
    }

    public Button getBtnDeleteProgammeurs() {
        return btnDeleteProgammeurs;
    }

    public void setBtnDeleteProgammeurs(Button btnDeleteProgammeurs) {
        this.btnDeleteProgammeurs = btnDeleteProgammeurs;
    }

    public Button getBtnClose() {
        return btnClose;
    }

    public void setBtnClose(Button btnClose) {
        this.btnClose = btnClose;
    }


    public TableColumn<Programmeur, Date> getDATE_DE_NAISSANCE() {
        return DATE_DE_NAISSANCE;
    }

    public void setDATE_DE_NAISSANCE(TableColumn<Programmeur, Date> DATE_DE_NAISSANCE) {
        this.DATE_DE_NAISSANCE = DATE_DE_NAISSANCE;
    }

    public TableColumn<Programmeur, String> getNOM() {
        return NOM;
    }

    public void setNOM(TableColumn<Programmeur, String> NOM) {
        this.NOM = NOM;
    }

    public TableColumn<Programmeur, String> getPRENOM() {
        return PRENOM;
    }

    public void setPRENOM(TableColumn<Programmeur, String> PRENOM) {
        this.PRENOM = PRENOM;
    }

    public TableColumn<Programmeur, Float> getPRIME() {
        return PRIME;
    }

    public void setPRIME(TableColumn<Programmeur, Float> PRIME) {
        this.PRIME = PRIME;
    }

    public TableColumn<Programmeur, String> getPSEUDO() {
        return PSEUDO;
    }

    public void setPSEUDO(TableColumn<Programmeur, String> PSEUDO) {
        this.PSEUDO = PSEUDO;
    }

    public TableColumn<Programmeur, Float> getSALAIRE() {
        return SALAIRE;
    }

    public void setSALAIRE(TableColumn<Programmeur, Float> SALAIRE) {
        this.SALAIRE = SALAIRE;
    }


    public TableColumn<Programmeur, Integer> getId() {
        return id;
    }

    public void setId(TableColumn<Programmeur, Integer> id) {
        this.id = id;
    }

    public TableView<Programmeur> getTableProgrammeurs() {
        return tableProgrammeurs;
    }

    public void setTableProgrammeurs(TableView<Programmeur> tableProgrammeurs) {
        this.tableProgrammeurs = tableProgrammeurs;
    }

    public ObservableList<Programmeur> getData() {
        return data;
    }

    public void setData(ObservableList<Programmeur> data) {
        this.data = data;
    }

    public TextField getSearchBar() {
        return searchBar;
    }

    public void setSearchBar(TextField searchBar) {
        this.searchBar = searchBar;
    }

    public ImageView getAvatarImageView() {
        return avatarImageView;
    }

    public void setAvatarImageView(ImageView avatarImageView) {
        this.avatarImageView = avatarImageView;
    }

    public GridPane getMainAddProgrammeur() {
        return MainAddProgrammeur;
    }

    public void setMainAddProgrammeur(GridPane mainAddProgrammeur) {
        MainAddProgrammeur = mainAddProgrammeur;
    }

    public GridPane getShowProgrammeur() {
        return ShowProgrammeur;
    }

    public void setShowProgrammeur(GridPane showProgrammeur) {
        ShowProgrammeur = showProgrammeur;
    }

    public GridPane getEditProgrammeurPane() {
        return editProgrammeurPane;
    }

    public void setEditProgrammeurPane(GridPane editProgrammeurPane) {
        this.editProgrammeurPane = editProgrammeurPane;
    }

    public GridPane getMainListePane() {
        return MainListePane;
    }

    public void setMainListePane(GridPane mainListePane) {
        MainListePane = mainListePane;
    }

    public TextField getFieldNameShow() {
        return fieldNameShow;
    }

    public void setFieldNameShow(TextField fieldNameShow) {
        this.fieldNameShow = fieldNameShow;
    }

    public TextField getFieldFornameShow() {
        return fieldFornameShow;
    }

    public void setFieldFornameShow(TextField fieldFornameShow) {
        this.fieldFornameShow = fieldFornameShow;
    }

    public TextField getFieldPseudoShow() {
        return fieldPseudoShow;
    }

    public void setFieldPseudoShow(TextField fieldPseudoShow) {
        this.fieldPseudoShow = fieldPseudoShow;
    }

    public TextField getFieldDateBornShow() {
        return FieldDateBornShow;
    }

    public void setFieldDateBornShow(TextField fieldDateBornShow) {
        FieldDateBornShow = fieldDateBornShow;
    }

    public TextField getFieldSalaryShow() {
        return fieldSalaryShow;
    }

    public void setFieldSalaryShow(TextField fieldSalaryShow) {
        this.fieldSalaryShow = fieldSalaryShow;
    }

    public TextField getFieldPrimeShow() {
        return fieldPrimeShow;
    }

    public void setFieldPrimeShow(TextField fieldPrimeShow) {
        this.fieldPrimeShow = fieldPrimeShow;
    }

    public Button getAddProgrammeurBtnForm() {
        return addProgrammeurBtnForm;
    }

    public void setAddProgrammeurBtnForm(Button addProgrammeurBtnForm) {
        this.addProgrammeurBtnForm = addProgrammeurBtnForm;
    }

    public FilteredList<Programmeur> getFilteredData() {
        return filteredData;
    }

    public void setFilteredData(FilteredList<Programmeur> filteredData) {
        this.filteredData = filteredData;
    }

    public SortedList<Programmeur> getSortedData() {
        return sortedData;
    }

    public void setSortedData(SortedList<Programmeur> sortedData) {
        this.sortedData = sortedData;
    }

    public void setIdCourant(int idCourant) {
        this.idCourant = idCourant;
    }

    public AddProgrammerController getAddProgrammerController() {
        return addProgrammerController;
    }

    public void setAddProgrammerController(AddProgrammerController addProgrammerController) {
        this.addProgrammerController = addProgrammerController;
    }

    public EditProgrammerController getEditProgrammerController() {
        return editProgrammerController;
    }

    public void setEditProgrammerController(EditProgrammerController editProgrammerController) {
        this.editProgrammerController = editProgrammerController;
    }

    public TextField getFieldName() {
        return fieldName;
    }

    public void setFieldName(TextField fieldName) {
        this.fieldName = fieldName;
    }

    public TextField getFieldForname() {
        return fieldForname;
    }

    public void setFieldForname(TextField fieldForname) {
        this.fieldForname = fieldForname;
    }

    public TextField getFieldPseudo() {
        return fieldPseudo;
    }

    public void setFieldPseudo(TextField fieldPseudo) {
        this.fieldPseudo = fieldPseudo;
    }

    public TextField getFieldDateBorn() {
        return FieldDateBorn;
    }

    public TextField getValueAddProgSet() {
        return valueAddProgSet;
    }

    public void setValueAddProgSet(TextField valueAddProgSet) {
        this.valueAddProgSet = valueAddProgSet;
    }

    public void setFieldDateBorn(TextField fieldDateBorn) {
        FieldDateBorn = fieldDateBorn;
    }

    public TextField getFieldSalary() {
        return fieldSalary;
    }

    public void setFieldSalary(TextField fieldSalary) {
        this.fieldSalary = fieldSalary;
    }

    public TextField getFieldPrime() {
        return fieldPrime;
    }

    public void setFieldPrime(TextField fieldPrime) {
        this.fieldPrime = fieldPrime;
    }

    public GridPane getDashboardPane() {
        return dashboardPane;
    }

    public Label getLabelSalaireMoyen() {
        return labelSalaireMoyen;
    }

    public void setLabelSalaireMoyen(Label labelSalaireMoyen) {
        this.labelSalaireMoyen = labelSalaireMoyen;
    }

    public Label getLabelPrimeMoyen() {
        return labelPrimeMoyen;
    }

    public void setLabelPrimeMoyen(Label labelPrimeMoyen) {
        this.labelPrimeMoyen = labelPrimeMoyen;
    }

    public void setDashboardPane(GridPane dashboardPane) {
        this.dashboardPane = dashboardPane;
    }

    /**************************************************************************************************************************/
    /**************************************************************************************************************************/
    /********************************************ELEMENT INTERACTIF SCENE BUILDER**********************************************/
    /**************************************************************************************************************************/
    /**************************************************************************************************************************/
    /**************************************************************************************************************************/

    // Variables liées à l'interface graphique
    @FXML private Pane PaneStatus;
    @FXML private Button btnAccueil;
    @FXML private Button btnParamètres;
    @FXML private Button btnProgrammeurs;
    @FXML private Label labelStatus;
    @FXML private Label labelSalaireMoyen;
    @FXML private Label labelPrimeMoyen;
    @FXML private Label labelNombreProgrammer;
    @FXML private Label labelStatusMini;
    @FXML private Button btnAddProgammeurs;
    @FXML private Button btnEditProgammeurs;
    @FXML private Button returnListeBtn;
    @FXML private Button editBtnShow;
    @FXML private Button AddNProgBtn;
    @FXML private Button DeleteBddBtn;
    @FXML private Button UpdateBddBtn;
    @FXML private Button deleteButtonProgrammeursListe;
    @FXML private Button addButtonProgrammeursListe;
    @FXML private Button btnDeleteProgammeurs;
    @FXML private Button btnClose;

    // Colonnes de la table des programmeurs
    @FXML private TableColumn<Programmeur, Date> createAt;
    @FXML private TableColumn<Programmeur, Date> CreateAtDash;
    @FXML private TableColumn<Programmeur, Date> DATE_DE_NAISSANCE;
    @FXML private TableColumn<Programmeur, String> NOM;
    @FXML private TableColumn<Programmeur, String> NOMDASH;
    @FXML private TableColumn<Programmeur, String> PRENOM;
    @FXML private TableColumn<Programmeur, String> PRENOMDASH;
    @FXML private TableColumn<Programmeur, Float> PRIME;
    @FXML private TableColumn<Programmeur, String> PSEUDO;
    @FXML private TableColumn<Programmeur, String> PSEUDODASH;
    @FXML private TableColumn<Programmeur, Float> SALAIRE;
    @FXML private TableColumn<Programmeur, Date> updateAt;
    @FXML private TableColumn<Programmeur, Integer> id;

    // Tables des programmeurs
    @FXML private TableView<Programmeur> tableProgrammeurs;
    @FXML private TableView<Programmeur> tableProgrammeursDashBoard;

    // Données observables
    private ObservableList<Programmeur> data;

    // Barre de recherche
    @FXML private TextField searchBar;
    @FXML private TextField valueAddProgSet;
    @FXML private ImageView avatarImageView;

    // Panneaux de l'interface
    @FXML private GridPane MainAddProgrammeur;
    @FXML private GridPane ShowProgrammeur;
    @FXML private GridPane editProgrammeurPane;
    @FXML private GridPane MainListePane;
    @FXML private GridPane dashboardPane;
    @FXML private GridPane settingsPane;

// Blocs d'ajout de programmeur
    /**ADD**/
    @FXML private TextField fieldName;
    @FXML private TextField fieldForname;
    @FXML private TextField fieldPseudo;
    @FXML private TextField FieldDateBorn;
    @FXML private TextField fieldSalary;
    @FXML private TextField fieldPrime;

// Blocs d'édition de programmeur
    /**Edit**/
    @FXML private TextField fieldNameEdit;
    @FXML private TextField fieldFornameEdit;
    @FXML private TextField fieldPseudoEdit;
    @FXML private TextField FieldDateBornEdit;
    @FXML private TextField fieldSalaryEdit;
    @FXML private TextField fieldPrimeEdit;

// Blocs d'affichage de programmeur
    /**Show**/
    @FXML private TextField fieldNameShow;
    @FXML private TextField fieldFornameShow;
    @FXML private TextField fieldPseudoShow;
    @FXML private TextField FieldDateBornShow;
    @FXML private TextField fieldSalaryShow;
    @FXML private TextField fieldPrimeShow;
    @FXML private Button addProgrammeurBtnForm;

}
