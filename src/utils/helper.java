package utils;

import java.util.Scanner;

public class helper
{
    /**
     * this method asks the user to prompt and integer using scanner
     * @return the integer prompted by the user
     */
    public static int askInteger() {

        Scanner sc = new Scanner(System.in);
        int choice = 0; // Vous pouvez initialiser cette variable avec une valeur par défaut

        while (true) {
            System.out.print("Veuillez entrer un entier : ");
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                break; // Sort de la boucle si l'entrée est un entier valide
            } else {
                System.out.println("Ce n'est pas un entier. Réessayez.");
                sc.nextLine(); // Nettoie la ligne incorrecte de l'entrée
            }
        }
        return choice;
    }

    /**
     * Cette méthode récupère une chaine de caractères entrée par l'utilisateur
     * @return la chaine de caractères entrée
     */
    public static String askString() {

        Scanner sc = new Scanner(System.in);
        String choice = null; // Vous pouvez initialiser cette variable avec une valeur par défaut

        while (true) {
            System.out.print("Veuillez entrer une chaine de caractère : ");
            if (sc.hasNextLine()) {
                choice = sc.nextLine();
                break; // Sort de la boucle si l'entrée est un entier valide
            } else {
                System.out.println("Ce n'est pas une chaine de caractère valide. Réessayez.");
                sc.nextLine(); // Nettoie la ligne incorrecte de l'entrée
            }
        }
        return choice;
    }

    /**
     * Cette méthode récupère un float à un utilisateur
     * @return le float entré par l'utilisateur
     */
    public static float askFloat() {

        Scanner sc = new Scanner(System.in);
        float choice = 0; // Vous pouvez initialiser cette variable avec une valeur par défaut

        while (true) {
            System.out.print("Veuillez entrer un float : ");
            if (sc.hasNextFloat()) {
                choice = sc.nextFloat();
                break; // Sort de la boucle si l'entrée est un entier valide
            } else {
                System.out.println("Ce n'est pas une float valide. Réessayez.");
                sc.nextFloat(); // Nettoie la ligne incorrecte de l'entrée
            }
        }
        return choice;
    }
}
