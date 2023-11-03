package utils;
// permet d'init la BDD
// toutes les strings cstes
/*
ajouter les URL
user
mdp
requêtes
 */
public class Constantes {
    private String url = "jdbc:mysql://localhost:3306/bdprojetjava";
    private String user = "root";
    private String password = "user";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
