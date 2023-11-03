package data;

import Projet.Programmeur;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public interface ActionBDDImpl {
    public Statement getConnexion();

    public ArrayList<Programmeur> recupProgrammeurs();

    public void afficheProgrammeurs();

    public void afficheSelectProgrammeurs(ArrayList<Programmeur> listeProgrammeurs);

    public void printProgrammeurById(int id);

    public void deleteProgrammeurById(int id);

    public void updateSalaireProgrammeurById(int id);

    public void ajouterProgrammeur();

    public void fillBdd(int n);

    public void ajouterProgrammeurWithOut(Programmeur p);

    public Programmeur getProgrammeurById(int id);

    public void updateProgrammeurById(int id, String nom, String prenom, String pseudo, Date dateNaissance, float salaire, float prime);

    public int getNumberOfProgrammer();

    public String getNumberOfProgrammerString();

    public float getSalaireMoyen();

    public String getSalaireMoyenString();

    public float getPrimeMoyenne();

    public String getPrimeMoyenneString();

    public ArrayList<Programmeur> getDerniersProgrammeurs();

    public Connection getConnexion(int i);
}
