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


public class MainController implements Initializable {
    @FXML
    private Pane PaneStatus;

    @FXML
    private Button btnAccueil;

    @FXML
    private Button btnParamètres;

    @FXML
    private Button btnProgrammeurs;

    @FXML
    private Label labelStatus;

    @FXML
    private Label labelSalaireMoyen;

    @FXML
    private Label labelPrimeMoyen;

    @FXML
    private Label labelNombreProgrammer;

    @FXML
    private Label labelStatusMini;

    @FXML
    private Button btnAddProgammeurs;

    @FXML
    private Button btnEditProgammeurs;

    @FXML
    private Button returnListeBtn;

    @FXML
    private Button editBtnShow;

    @FXML
    private Button deleteButtonProgrammeursListe;

    @FXML
    private Button addButtonProgrammeursListe;

    @FXML
    private Button btnDeleteProgammeurs;

    @FXML
    private Button btnClose;

    @FXML
    private TableColumn<Programmeur, Date> createAt;

    @FXML
    private TableColumn<Programmeur, Date> CreateAtDash;

    @FXML
    private TableColumn<Programmeur, Date> DATE_DE_NAISSANCE;

    @FXML
    private TableColumn<Programmeur, String> NOM;

    @FXML
    private TableColumn<Programmeur, String> NOMDASH;

    @FXML
    private TableColumn<Programmeur, String> PRENOM;

    @FXML
    private TableColumn<Programmeur, String> PRENOMDASH;

    @FXML
    private TableColumn<Programmeur, Float> PRIME;

    @FXML
    private TableColumn<Programmeur, String> PSEUDO;

    @FXML
    private TableColumn<Programmeur, String> PSEUDODASH;

    @FXML
    private TableColumn<Programmeur, Float> SALAIRE;

    @FXML
    private TableColumn<Programmeur, Date> updateAt;

    @FXML
    private TableColumn<Programmeur, Integer> id;


    @FXML
    private TableView<Programmeur> tableProgrammeurs;

    @FXML
    private TableView<Programmeur> tableProgrammeursDashBoard;

    private ObservableList<Programmeur> data;

    @FXML
    private TextField searchBar;

    @FXML
    private ImageView avatarImageView;

    @FXML
    private GridPane MainAddProgrammeur;

    @FXML
    private GridPane ShowProgrammeur;

    @FXML
    private GridPane editProgrammeurPane;

    @FXML
    private GridPane MainListePane;

    @FXML
    private GridPane dashboardPane;


    /**ADD**/
    @FXML
    private TextField fieldName;
    @FXML
    private TextField fieldForname;
    @FXML
    private TextField fieldPseudo;
    @FXML
    private TextField FieldDateBorn;
    @FXML
    private TextField fieldSalary;
    @FXML
    private TextField fieldPrime;

    /**Edit**/
    @FXML
    private TextField fieldNameEdit;
    @FXML
    private TextField fieldFornameEdit;
    @FXML
    private TextField fieldPseudoEdit;
    @FXML
    private TextField FieldDateBornEdit;
    @FXML
    private TextField fieldSalaryEdit;
    @FXML
    private TextField fieldPrimeEdit;

    /**Show**/
    @FXML
    private TextField fieldNameShow;
    @FXML
    private TextField fieldFornameShow;
    @FXML
    private TextField fieldPseudoShow;
    @FXML
    private TextField FieldDateBornShow;
    @FXML
    private TextField fieldSalaryShow;
    @FXML
    private TextField fieldPrimeShow;

    @FXML
    private Button addProgrammeurBtnForm;
    private FilteredList<Programmeur> filteredData;
    private SortedList<Programmeur> sortedData;

    private int idCourant = -1 ;

    private GridPane CurrentPane;

    @FXML
    private AddProgrammerController addProgrammerController = new AddProgrammerController();

    @FXML
    private EditProgrammerController editProgrammerController = new EditProgrammerController();

    @FXML
    private ShowProgrammerController showProgrammerController = new ShowProgrammerController();
    @FXML
    private SearchBarController searchBarController = new SearchBarController();

    @FXML
    private DeleteProgrammerController deleteProgrammerController = new DeleteProgrammerController();

    @FXML
    private HandleClicksController handleClicksController = new HandleClicksController();


    /**
     * Initialise l'interface utilisateur et prépare les composants nécessaires.
     * @param location URL
     * @param resources Ressources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ActionDB DataBdd;
        DataBdd = new ActionDB();
        DataBdd.getConnexion();
        //DataBdd.fillBdd(200);

        CurrentPane = MainListePane;
        CurrentPane.setVisible(true);
        MainAddProgrammeur.setVisible(false);
        dashboardPane.setVisible(true);
        MainListePane.setVisible(false);
        ShowProgrammeur.setVisible(false);

        labelNombreProgrammer.setText(DataBdd.getNumberOfProgrammerString());
        labelPrimeMoyen.setText(DataBdd.getPrimeMoyenneString() + "€");
        labelSalaireMoyen.setText(DataBdd.getSalaireMoyenString() + "€");

        Circle clipCircle;
        clipCircle = new Circle();
        clipCircle.setCenterX(avatarImageView.getFitWidth() / 2);
        clipCircle.setCenterY(avatarImageView.getFitHeight() / 2);
        clipCircle.setRadius(avatarImageView.getFitWidth() / 2);

        avatarImageView.setClip(clipCircle);

        createAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        updateAt.setCellValueFactory(new PropertyValueFactory<>("updateAt"));

        DATE_DE_NAISSANCE.setCellValueFactory(new PropertyValueFactory<>("date"));
        NOM.setCellValueFactory(new PropertyValueFactory<>("nom"));
        PRENOM.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        PRIME.setCellValueFactory(new PropertyValueFactory<>("prime"));
        PSEUDO.setCellValueFactory(new PropertyValueFactory<>("pseudo"));
        SALAIRE.setCellValueFactory(new PropertyValueFactory<>("salaire"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        data = FXCollections.observableArrayList(DataBdd.recupProgrammeurs());
        filteredData = new FilteredList<>(data, p -> true);
        searchBarController.setMainController(this);
        sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableProgrammeurs.comparatorProperty());
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filtrerData(newValue);
        });
        tableProgrammeurs.setItems(sortedData);


        NOMDASH.setCellValueFactory(new PropertyValueFactory<>("nom"));
        PRENOMDASH.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        PSEUDODASH.setCellValueFactory(new PropertyValueFactory<>("pseudo"));
        CreateAtDash.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        tableProgrammeursDashBoard.setItems(FXCollections.observableArrayList(DataBdd.getDerniersProgrammeurs()));

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
        addProgrammerController.setMainController(this);
        editProgrammerController.setMainController(this);
        showProgrammerController.setMainController(this);
        deleteProgrammerController.setMainController(this);
        handleClicksController.setMainController(this);
    }

    /**
     * Met à jour la liste des programmeurs affichés.
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


    /*****************************************************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************************************************/
    /******************************************************REDIRECTION**********************************************************/
    /*****************************************************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************************************************/

    public void ajouterProgrammeur() {
        addProgrammerController.ajouterProgrammeur();
    }

    public void filtrerData(String terme) {
        searchBarController.filtrerData(terme);
    }

    public void modifierProgrammeur() {
        editProgrammerController.modifierProgrammeur();
    }

    public void goToModifierProgrammeur() {
        editProgrammerController.goToModifierProgrammeur();
    }

    public void showProgrammeur(int id) {
        showProgrammerController.showProgrammeur(id);
    }

    @FXML
    public void supprimerProgrammeur() {
        deleteProgrammerController.supprimerProgrammeur(idCourant);
    }

    @FXML
    public void handleClicks(ActionEvent event) {
        handleClicksController.handleClicks(event);
    }

    /*****************************************************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************************************************/
    /**********************************************************************************GETTERS AND SETTERS************************************************************************************/
    /*****************************************************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************************************************/

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
}
