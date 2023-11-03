package exec;
import Projet.ActionDB;
import utils.helper;

import java.sql.SQLOutput;


public class Menu {

    /**
     * La méthode exécution permet à l'utilisateur d'interagir avec le menu version back
     */
    public void execution () {
        while (true) {

            System.out.println("<<<<<<< MENU >>>>>>>");
            System.out.println("1. Afficher tous les programmeurs");
            System.out.println("2. Afficher un programmeur");
            System.out.println("3. Supprimer un programmeur");
            System.out.println("4. Ajouter un programmeur");
            System.out.println("5. Modifier le salaire");
            System.out.println("6. Quitter le programme");
            System.out.print("Quel est votre choix ? : ");

            //lancer la bdd
            ActionDB bdd = new ActionDB();
            bdd.getConnexion();

            //lancer le menu
            int choice;
            do {
                choice = helper.askInteger();
                switch (choice) {
                    case 1:
                        // Afficher tous les programmeurs de la BDD
                        //System.out.println("Affichez tous les programmeurs :");
                        bdd.afficheProgrammeurs();
                        break;
                    case 2:
                        // Afficher le programmeur souhaité par l'utilisateur
                        System.out.print("Id du programmeur à afficher : ");
                        int idPrint = helper.askInteger();
                        bdd.printProgrammeurById(idPrint);
                        break;
                    case 3:
                        //supprimer un programmeur
                        System.out.print("Id du programmeur à supprimer : ");
                        int idDelete = helper.askInteger();
                        bdd.deleteProgrammeurById(idDelete);
                        break;
                    case 4:
                        //ajouter un programmeur dans la base de données
                        bdd.ajouterProgrammeur();
                        break;

                    case 5:
                        //modifier le salaire
                        System.out.print("Id du programmeur dont vous souhaitez modifier le salaire : ");
                        int id5 = helper.askInteger();
                        bdd.updateSalaireProgrammeurById(id5);
                        break;
                    case 6:
                        //quitter le programme
                        return;
                    default:
                        System.out.println("ERREUR! Veuillez saisir un nombre entre 1 et 6.");
                        System.out.print("Quel est votre choix ? : ");
                        break;
                }
            }
            while (choice > 6 || choice < 1);
        }
    }
}