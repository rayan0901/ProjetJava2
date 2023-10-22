package Projet;

import utils.helper;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Cette classe représente un programmeur avec des informations telles que le prénom, le nom, la date de naissance,
 * le salaire, la prime et le pseudo.
 */
public class Programmeur {
    private String prenom;
    private String nom;
    private Date date;
    private float salaire;
    private float prime;
    private String pseudo;

    private Date createdAt;

    private Date updateAt;

    private int id;

    /**
     * Constructeur par défaut de la classe Programmeur.
     */
    public Programmeur(){}

    /**
     * Constructeur avec des paramètres pour initialiser un objet Programmeur.
     *
     * @param prenom Le prénom du programmeur.
     * @param nom Le nom du programmeur.
     * @param date La date de naissance du programmeur.
     * @param salaire Le salaire du programmeur.
     * @param prime La prime du programmeur.
     * @param pseudo Le pseudo du programmeur.
     */
    public Programmeur(String prenom, String nom, Date date, float salaire, float prime, String pseudo) {
        this.prenom = prenom;
        this.nom = nom;
        this.date = date;
        this.salaire = salaire;
        this.prime = prime;
        this.pseudo = pseudo;
        this.createdAt = new Date();
        this.updateAt = new Date();
    }

    /**
     * Constructeur avec des paramètres pour initialiser un objet Programmeur.
     *
     * @param prenom Le prénom du programmeur.
     * @param nom Le nom du programmeur.
     * @param date La date de naissance du programmeur.
     * @param salaire Le salaire du programmeur.
     * @param prime La prime du programmeur.
     * @param pseudo Le pseudo du programmeur.
     * @param id L'id du programmeur
     */
    public Programmeur(String prenom, String nom, Date date, float salaire, float prime, String pseudo, int id) {
        this.prenom = prenom;
        this.nom = nom;
        this.date = date;
        this.salaire = salaire;
        this.prime = prime;
        this.pseudo = pseudo;
        this.id = id;
        this.createdAt = new Date();
        this.updateAt = new Date();
    }

    public Programmeur(String prenom, String nom, Date date, float salaire, float prime, String pseudo, int id, Date createdAt, Date updateAt) {
        this.prenom = prenom;
        this.nom = nom;
        this.date = date;
        this.salaire = salaire;
        this.prime = prime;
        this.pseudo = pseudo;
        this.id = id;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    /**
     * Créér un programmeur en demandant à l'utilisateur les informations nécessaires
     * @return
     */
    public static Programmeur scanProgrammeur() {
        Scanner sc;
        Programmeur p;
        String dateStr;
        Date date;
        SimpleDateFormat sdf, sdfMySQL;
        String prenom;
        String nom;
        String pseudo;
        float salaire;
        float prime;
        String dateMySQL;

        sc = new Scanner(System.in);
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdfMySQL = new SimpleDateFormat("yyyy-MM-dd");  // pour MySQL
        date = null;

        System.out.println("Ajoutons un nouveau programmeur");
        do {
            System.out.println("Entrez son prénom :");
            prenom = sc.next();
            if (prenom.length() > 255) {
                System.out.println("Oups, c'est trop long. Veuillez réessayer.");
            }
        } while (prenom.length() > 255);
        prenom = prenom.substring(0, Math.min(prenom.length(), 255));

        do {
            System.out.println("Entrez son nom :");
            nom = sc.next();
            if (nom.length() > 255) {
                System.out.println("Oups, c'est trop long. Veuillez réessayer.");
            }
        } while (nom.length() > 255);
        nom = nom.substring(0, Math.min(nom.length(), 255));

        sc.nextLine(); // Pour consommer le caractère de nouvelle ligne restant

        while (date == null) {
            System.out.println("Entrez la date de naissance au format dd/MM/yyyy: ");
            dateStr = sc.nextLine();
            try {
                date = sdf.parse(dateStr);
                dateMySQL = sdfMySQL.format(date);  // pour MySQL
                System.out.println("Date de naissance en format MySQL: " + dateMySQL);
            } catch (Exception e) {
                System.out.println("Format de date incorrect, essayez encore.");
            }
        }

        do {
            System.out.println("Entrez son pseudo :");
            pseudo = sc.next();
            if (pseudo.length() > 255) {
                System.out.println("Oups, c'est trop long. Veuillez réessayer.");
            }
        } while (pseudo.length() > 255);
        pseudo = pseudo.substring(0, Math.min(pseudo.length(), 255));  // Limité à 255 caractères comme indiqué
        System.out.println("Entrez son salaire :");
        salaire = helper.askFloat();
        System.out.println("Entrez sa prime :");
        prime = helper.askFloat();

        p = new Programmeur(prenom, nom, date, salaire, prime, pseudo);

        return p;
    }



    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'objet Programmeur.
     *
     * @return Une chaîne de caractères représentant l'objet Programmeur.
     */
    @Override
    public String toString()
    {
        return  "Id      : " + id + '\n' +
                "Nom     : " + nom + '\n' +
                "Prenom  : " + prenom + '\n' +
                "Pseudo  : " + pseudo + '\n' +
                "Date    : " + date + '\n' +
                "Salaire : " + salaire + '\n' +
                "Prime   : " + prime + '\n' +
                "----------------------------------------";
    }

    /**
     * Récupère le prénom du programmeur.
     *
     * @return Le prénom du programmeur.
     */
    public String getPrenom(){
        return this.prenom;
    }

    /**
     * Récupère le nom du programmeur.
     *
     * @return Le nom du programmeur.
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * Récupère la date de naissance du programmeur.
     *
     * @return La date de naissance du programmeur.
     */
    public Date getDate(){
        return this.date;
    }

    /**
     * Récupère le salaire du programmeur.
     *
     * @return Le salaire du programmeur.
     */
    public float getSalaire(){
        return this.salaire;
    }

    /**
     * Récupère la prime du programmeur.
     *
     * @return La prime du programmeur.
     */
    public float getPrime(){
        return this.prime;
    }

    /**
     * Récupère le pseudo du programmeur.
     *
     * @return Le pseudo du programmeur.
     */
    public String getPseudo(){
        return this.pseudo;
    }

    /**
     * Récupère l'id du programmeur.
     *
     * @return id Le nouvel id du programmeur.
     */
    public int getId()
    {
        return id;
    }

    /**
     * Modifie le prénom du programmeur.
     *
     * @param prenom Le nouveau prénom du programmeur.
     */
    public void setPrenom(String prenom){
        this.prenom = prenom;
    }

    /**
     * Modifie le nom du programmeur.
     *
     * @param nom Le nouveau nom du programmeur.
     */
    public void setNom(String nom){
        this.nom = nom;
    }

    /**
     * Modifie la date de naissance du programmeur.
     *
     * @param date La nouvelle date de naissance du programmeur.
     */
    public void setDate(Date date){
        this.date = date;
    }

    /**
     * Modifie le salaire du programmeur.
     *
     * @param salaire Le nouveau salaire du programmeur.
     */
    public void setSalaire(float salaire){
        this.salaire = salaire;
    }

    /**
     * Modifie la prime du programmeur.
     *
     * @param prime La nouvelle prime du programmeur.
     */
    public void setPrime(float prime){
        this.prime = prime;
    }

    /**
     * Modifie le pseudo du programmeur.
     *
     * @param pseudo Le nouveau pseudo du programmeur.
     */
    public void setPseudo(String pseudo){
        this.pseudo = pseudo;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}