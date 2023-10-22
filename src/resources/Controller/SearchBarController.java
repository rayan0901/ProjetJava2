package resources.Controller;

import Projet.Programmeur;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

public class SearchBarController {

    private FilteredList<Programmeur> filteredData;
    private SortedList<Programmeur> sortedData;
    private MainController mainController;

    /**
     * Filtre les données dans la table en fonction du terme de recherche entré.
     * @param terme Le terme à chercher dans les données.
     */
    protected void filtrerData(String terme) {
        updateMainController();
        filteredData.setPredicate(programmeur -> {
            if (terme == null || terme.isEmpty()) {
                return true;
            }

            String termeMinuscule = terme.toLowerCase();

            if (String.valueOf(programmeur.getId()).contains(terme)) {
                return true;
            }

            if (programmeur.getNom().toLowerCase().contains(termeMinuscule)) {
                return true;
            }

            if (programmeur.getPrenom().toLowerCase().contains(termeMinuscule)) {
                return true;
            }

            if (programmeur.getPseudo().toLowerCase().contains(termeMinuscule)) {
                return true;
            }

            if (String.valueOf(programmeur.getSalaire()).contains(terme)) {
                return true;
            }

            if (String.valueOf(programmeur.getPrime()).contains(terme)) {
                return true;
            }

            // Vous devrez convertir la Date en String selon le format que vous utilisez.
            if (String.valueOf(programmeur.getDate()).contains(terme)) {
                return true;
            }

            return false; // Ne correspond pas.
        });
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        this.filteredData = mainController.getFilteredData();
        this.sortedData = mainController.getSortedData();
    }

    public void updateMainController() {
        this.filteredData = mainController.getFilteredData();
        this.sortedData = mainController.getSortedData();
    }
}
