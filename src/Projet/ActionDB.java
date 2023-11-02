package Projet;

import java.sql.*;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.github.javafaker.Faker;
import utils.helper;

/**
 * Cette classe gère les interactions avec la base de données pour récupérer et afficher les informations
 * sur les programmeurs.
 */
public class ActionDB {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String url = "jdbc:mysql://localhost:3306/bdprojetjava";
    private String user = "root";
    private String password = "user";

    /**
     * Établit une connexion à la base de données et renvoie un objet Statement pour exécuter des requêtes SQL.
     *
     * @return Un objet Statement pour exécuter des requêtes SQL.
     * @throws RuntimeException Si une erreur survient lors de la création de la connexion.
     */
    public Statement getConnexion(){
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }

    public Connection getConnexion(int i) {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    /**
     * Récupère la liste de tous les programmeurs depuis la base de données.
     *
     * @return Une liste d'objets Programmeur contenant les informations des programmeurs.
     */
    public ArrayList<Programmeur> recupProgrammeurs(){
        ArrayList<Programmeur> listeProgrammeurs = new ArrayList<>();
        try {
            statement = getConnexion();
            ResultSet res = statement.executeQuery("SELECT * FROM programmeur");
            // Récupérer les Programmeurs

            while (res.next()){
                // Ordre : prenom, nom, date, salaire, prime, pseudo, id, create, update
                Programmeur p = new Programmeur(res.getString("PRENOM"), res.getString("NOM"), res.getDate("DATE_DE_NAISSANCE"), res.getFloat("SALAIRE"), res.getFloat("PRIME"), res.getString("PSEUDO"), res.getInt("id"), res.getDate("createdAt"), res.getDate("updateAt"));
                listeProgrammeurs.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        return listeProgrammeurs;
    }

    /**
     * Affiche tous les programmeurs récupérés depuis la base de données.
     */
    public void afficheProgrammeurs(){
        ArrayList<Programmeur> listeProgrammeurs = recupProgrammeurs();
        // Afficher tous les programmeurs
        System.out.println("On affiche les programmeurs :");
        for (Programmeur programmeur : listeProgrammeurs) {
            System.out.println(programmeur.toString());
        }
    }

    /**
     * Affiche une liste de programmeurs sélectionnés.
     *
     * @param listeProgrammeurs La liste des programmeurs à afficher.
     */
    public void afficheSelectProgrammeurs(ArrayList<Programmeur> listeProgrammeurs){
        // Afficher tous les programmeurs
        System.out.println("On affiche les programmeurs sélectionnés :");
        for (Programmeur programmeur : listeProgrammeurs) {
            System.out.println(programmeur);
        }
    }

    /**
     * Affiche un programmeur selon son id
     * @param id
     */
    public void printProgrammeurById(int id)
    {
        ArrayList<Programmeur> listProgrammeur = recupProgrammeurs();
        for (Programmeur programmeur : listProgrammeur)
        {
            if (programmeur.getId() == id)
            {
                System.out.println("Programmeur n°" + id + " :");
                System.out.println(programmeur);
                return ;
            }
        }
        System.out.println("Aucun programmeur trouvé pour cet id.");
        return ;
    }

    /**
     * Supprime un programmeur selon son id
     * @param id
     */
    public void deleteProgrammeurById(int id) {
        Statement statement = getConnexion();

        try {
            int rowsDeleted = statement.executeUpdate("DELETE FROM programmeur WHERE id = " + id);

            if (rowsDeleted > 0) {
                System.out.println("Le programmeur a été supprimé de la base de données.");
            } else {
                System.out.println("Aucun programmeur trouvé pour cet ID.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du programmeur : " + e.getMessage());
        }
    }

    /**
     * Cette méthode permet de mettre à jour le salaire d'un programmeur selon son id
     * @param id
     */
    public void updateSalaireProgrammeurById(int id) {
        ArrayList<Programmeur> listProgrammeur = recupProgrammeurs();

        for (Programmeur programmeur : listProgrammeur) {
            if (programmeur.getId() == id) {
                System.out.println("Quel est le nouveau salaire du programmeur n°" + id + " ?");
                float salaire = helper.askFloat();

                try {
                    statement.executeUpdate("UPDATE programmeur SET salaire = " + salaire + " WHERE id = " + id);
                    System.out.println("Le salaire a bien été modifié.");
                    return;
                } catch (SQLException e) {
                    System.err.println("Erreur lors de la mise à jour du salaire : " + e.getMessage());
                    return;
                }
            }
        }
        System.out.println("Aucun programmeur trouvé pour cet id.");
    }

    /**
     * Cette méthode permet d'ajouter un programmeur à la base de donnée en demandant à l'utilisateur les données.
     */
    public void ajouterProgrammeur() {
        Programmeur p = Programmeur.scanProgrammeur();
        Statement statement = getConnexion();

        SimpleDateFormat sdfMySQL = new SimpleDateFormat("yyyy-MM-dd");
        String dateMySQL = sdfMySQL.format(p.getDate());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedCreatedAt = sdf.format(p.getCreatedAt());
        String formattedUpdatedAt = sdf.format(p.getUpdateAt());

        String sql = "INSERT INTO programmeur (prenom, nom, DATE_DE_NAISSANCE, salaire, prime, pseudo, createdAt, updateAt) VALUES ('" +
                p.getPrenom() + "', '" + p.getNom() + "', '" + dateMySQL + "', " + p.getSalaire() + ", " + p.getPrime() + ", '" + p.getPseudo() + "', '" + formattedCreatedAt + "', '" + formattedUpdatedAt + "')";

        try {
            int rowsInserted = statement.executeUpdate(sql);

            if (rowsInserted > 0) {
                System.out.println("Le programmeur a été ajouté à la base de données.");
            } else {
                System.out.println("Fail : le programmeur n'a pas pu être ajouté.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du programmeur : " + e.getMessage());
        }
    }


    /**
     * Remplit la base de données avec un nombre spécifié de programmeurs générés aléatoirement.
     *
     * @param n Le nombre de programmeurs à ajouter à la base de données.
     *
     * <p>
     * Après la génération des données, une requête SQL d'insertion est construite et exécutée pour insérer le programmeur dans la base de données.
     * Si l'insertion est réussie, un message de confirmation est affiché. En cas d'erreur lors de l'exécution de la requête, un message d'erreur est affiché.
     * </p>
     *
     * @throws SQLException Si une erreur survient lors de l'exécution de la requête SQL.
     */
    public void fillBdd(int n) {
        Connection conn = getConnexion(0);
        Faker faker = new Faker();

        String sql = "INSERT INTO programmeur (prenom, nom, DATE_DE_NAISSANCE, salaire, prime, pseudo, createdAt, updateAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            for (int i = 0; i < n; i++) {
                Programmeur p = new Programmeur();

                p.setPrenom(faker.name().firstName());
                p.setNom(faker.name().lastName());
                p.setDate(faker.date().birthday());
                p.setSalaire((float) faker.number().randomDouble(2, 3000, 7000));
                p.setPrime((float) faker.number().randomDouble(2, 0, 2000));
                p.setPseudo(faker.name().username());
                p.setCreatedAt(new Date());
                p.setUpdateAt(new Date());

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                ((PreparedStatement) preparedStatement).setString(1, p.getPrenom());
                preparedStatement.setString(2, p.getNom());
                preparedStatement.setString(3, sdf.format(p.getDate()));
                preparedStatement.setFloat(4, p.getSalaire());
                preparedStatement.setFloat(5, p.getPrime());
                preparedStatement.setString(6, p.getPseudo());
                preparedStatement.setString(7, sdf.format(p.getCreatedAt()));
                preparedStatement.setString(8, sdf.format(p.getUpdateAt()));

                int rowsInserted = preparedStatement.executeUpdate();

                if (rowsInserted > 0) {
                    //System.out.println("Le programmeur a été ajouté à la base de données.");
                } else {
                    System.out.println("Fail : le programmeur n'a pas pu être ajouté.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du programmeur : " + e.getMessage());
        }
    }

    /**
     * Ajoute un programmeur spécifié à la base de données.
     *
     * @param p Le programmeur à ajouter à la base de données.
     *
     * <p>
     * Après la construction de la requête, elle est exécutée et, si l'insertion est réussie, un message de confirmation est affiché. En cas d'erreur lors de l'exécution de la requête, un message d'erreur est affiché.
     * </p>
     *
     * @throws SQLException Si une erreur survient lors de l'exécution de la requête SQL.
     */
    public void ajouterProgrammeurWithOut(Programmeur p) {
        Statement statement = getConnexion();

        SimpleDateFormat sdfMySQL = new SimpleDateFormat("yyyy-MM-dd");
        String dateMySQL = sdfMySQL.format(p.getDate());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedCreatedAt = sdf.format(p.getCreatedAt());
        String formattedUpdatedAt = sdf.format(p.getUpdateAt());

        String sql = "INSERT INTO programmeur (prenom, nom, DATE_DE_NAISSANCE, salaire, prime, pseudo, createdAt, updateAt) VALUES ('" +
                p.getPrenom() + "', '" + p.getNom() + "', '" + dateMySQL + "', " + p.getSalaire() + ", " + p.getPrime() + ", '" + p.getPseudo() + "', '" + formattedCreatedAt + "', '" + formattedUpdatedAt + "')";

        try {
            int rowsInserted = statement.executeUpdate(sql);

            if (rowsInserted > 0) {
                System.out.println("Le programmeur a été ajouté à la base de données.");
            } else {
                System.out.println("Fail : le programmeur n'a pas pu être ajouté.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du programmeur : " + e.getMessage());
        }
    }

    /**
     * Récupère un programmeur de la base de données en fonction de son identifiant.
     *
     * @param id L'identifiant du programmeur à récupérer.
     * @return Un objet Programmeur correspondant à l'identifiant donné ou null si aucun programmeur avec cet identifiant n'est trouvé.
     *
     * @throws SQLException Si une erreur survient lors de l'exécution de la requête SQL.
     */
    public Programmeur getProgrammeurById(int id) {
        Programmeur programmeur = null;
        try {
            statement = getConnexion();
            ResultSet res = statement.executeQuery("SELECT * FROM programmeur WHERE id = " + id);
            if (res.next()) {
                // Ordre : prenom, nom, date, salaire, prime, pseudo, id
                programmeur = new Programmeur(res.getString("PRENOM"), res.getString("NOM"), res.getDate("DATE_DE_NAISSANCE"), res.getFloat("SALAIRE"), res.getFloat("PRIME"), res.getString("PSEUDO"), res.getInt("id"));
            }
        } catch (SQLException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
        return programmeur;
    }

    /**
     * Met à jour les données d'un programmeur spécifié par son identifiant.
     *
     * @param id L'identifiant du programmeur à mettre à jour.
     * @param nom Le nouveau nom du programmeur.
     * @param prenom Le nouveau prénom du programmeur.
     * @param dateNaissance La nouvelle date de naissance du programmeur.
     * @param salaire Le nouveau salaire du programmeur.
     * @param prime La nouvelle prime du programmeur.
     *
     * @throws SQLException Si une erreur survient lors de l'exécution de la requête SQL.
     */
    public void updateProgrammeurById(int id, String nom, String prenom, String pseudo, Date dateNaissance, float salaire, float prime) {
        Statement statement = getConnexion();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNaissanceStr = sdf.format(dateNaissance);

        String query = "UPDATE programmeur SET nom = '" + nom + "', prenom = '" + prenom + "', date_de_naissance = '" + dateNaissanceStr + "', pseudo = '" + pseudo + "', salaire = " + salaire + ", prime = " + prime + " WHERE id = " + id;

        try {
            int rowsUpdated = statement.executeUpdate(query);
            if (rowsUpdated > 0) {
                System.out.println("Le programmeur a bien été mis à jour.");
            } else {
                System.out.println("Aucun programmeur trouvé pour cet ID.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour du programmeur : " + e.getMessage());
        }
    }

    /**
     * Calcule le nombre total de programmeurs dans la base de données.
     *
     * @return Le nombre total de programmeurs.
     *
     * <b>Remarque</b> : En cas d'erreur SQL, un message d'erreur est affiché et la fonction renvoie 0.
     */
    public int getNumberOfProgrammer()
    {
        Statement statement;
        int count = 0;

        statement = getConnexion();

        String sql = "SELECT COUNT(*) FROM programmeur";

        try
        {
            ResultSet res = statement.executeQuery(sql);
            if (res.next())
            {
                count = res.getInt(1);
            }
        }
        catch (SQLException e)
        {
            System.err.println("Erreur lors du comptage des programmeurs : " + e.getMessage());
        }
        return count;
    }

    /**
     * Calcule et renvoie le nombre total de programmeurs dans la base de données sous forme de chaîne.
     *
     * @return Le nombre total de programmeurs sous forme de chaîne.
     */
    public String getNumberOfProgrammerString()
    {
        int count;
        String countStr;

        count = getNumberOfProgrammer();
        countStr = Integer.toString(count);

        return countStr;
    }

    /**
     * Calcule le salaire moyen des programmeurs dans la base de données.
     *
     * @return Le salaire moyen des programmeurs.
     *
     * <b>Remarque</b> : En cas d'erreur SQL, un message d'erreur est affiché et la fonction renvoie 0.
     */
    public float getSalaireMoyen()
    {
        Statement statement;
        float salaireMoyen = 0;

        statement = getConnexion();
        String sql = "SELECT AVG(salaire) FROM programmeur";

        try
        {
            ResultSet res = statement.executeQuery(sql);
            if (res.next())
            {
                salaireMoyen = res.getFloat(1);
            }
        }
        catch (SQLException e)
        {
            System.err.println("Erreur lors du calcul du salaire moyen : " + e.getMessage());
        }
        return salaireMoyen;
    }

    /**
     * Calcule et renvoie le salaire moyen des programmeurs dans la base de données sous forme de chaîne, formaté à deux décimales.
     *
     * @return Le salaire moyen des programmeurs sous forme de chaîne.
     */
    public String getSalaireMoyenString()
    {
        return String.format("%.2f", getSalaireMoyen());
    }


    /**
     * Calcule la prime moyenne des programmeurs dans la base de données.
     *
     * @return La prime moyenne des programmeurs.
     *
     * <b>Remarque</b> : En cas d'erreur SQL, un message d'erreur est affiché et la fonction renvoie 0.
     */
    public float getPrimeMoyenne()
    {
        Statement statement;
        float primeMoyenne = 0;

        statement = getConnexion();
        String sql = "SELECT AVG(prime) FROM programmeur";

        try
        {
            ResultSet res = statement.executeQuery(sql);
            if (res.next())
            {
                primeMoyenne = res.getFloat(1);
            }
        }
        catch (SQLException e)
        {
            System.err.println("Erreur lors du calcul de la prime moyenne : " + e.getMessage());
        }
        return primeMoyenne;
    }

    /**
     * Calcule et renvoie la prime moyenne des programmeurs dans la base de données sous forme de chaîne, formaté à deux décimales.
     *
     * @return La prime moyenne des programmeurs sous forme de chaîne.
     */
    public String getPrimeMoyenneString()
    {
        return String.format("%.2f", getPrimeMoyenne());
    }


    /**
     * Récupère les 10 derniers programmeurs ajoutés à la base de données, basé sur la date de création.
     *
     * @return Une liste des 10 derniers programmeurs ajoutés.
     *
     * <b>Remarque</b> : En cas d'erreur SQL, un message d'erreur est affiché et la fonction renvoie une liste vide.
     */
    public ArrayList<Programmeur> getDerniersProgrammeurs()
    {
        ArrayList<Programmeur> derniersProgrammeurs = new ArrayList<>();
        Statement statement;

        statement = getConnexion();
        String sql = "SELECT * FROM programmeur ORDER BY createdAt DESC LIMIT 10";

        try
        {
            ResultSet res = statement.executeQuery(sql);
            while (res.next())
            {
                Programmeur p = new Programmeur(res.getString("PRENOM"), res.getString("NOM"), res.getDate("DATE_DE_NAISSANCE"), res.getFloat("SALAIRE"), res.getFloat("PRIME"), res.getString("PSEUDO"), res.getInt("id"), res.getDate("createdAt"), res.getDate("updateAt"));
                derniersProgrammeurs.add(p);
                //System.out.println(p.getNom());
            }
        }
        catch (SQLException e)
        {
            System.err.println("Erreur lors de la récupération des derniers programmeurs : " + e.getMessage());
        }
        return derniersProgrammeurs;
    }

}
