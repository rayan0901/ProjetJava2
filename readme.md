<!-- TOC -->
* [Projet de Gestion de Programmeurs](#projet-de-gestion-de-programmeurs)
    * [Classe Programmeur](#classe-programmeur)
        * [Attributs](#attributs)
        * [Méthodes](#méthodes)
    * [Comment utiliser ce projet](#comment-utiliser-ce-projet)
<!-- TOC -->

## Projet de Gestion de Programmeurs

Ce projet permet de gérer les informations relatives aux programmeurs. Il s'agit d'un CRUD complet pour les programmeurs. Réa

### Classe Programmeur

La classe `Programmeur` est le cœur de ce projet. Elle représente un programmeur avec différentes informations telles que le prénom, le nom, la date de naissance, le salaire, la prime, le pseudo, la date de création et la date de mise à jour.

#### Attributs

- `prenom`: Le prénom du programmeur.
- `nom`: Le nom du programmeur.
- `date`: La date de naissance du programmeur.
- `salaire`: Le salaire du programmeur.
- `prime`: La prime du programmeur.
- `pseudo`: Le pseudo du programmeur.
- `createdAt`: La date de création du programmeur.
- `updateAt`: La date de mise à jour du programmeur.
- `id`: L'identifiant du programmeur.

#### Méthodes

- `scanProgrammeur()`: Cette méthode statique permet de créer un nouvel objet `Programmeur` en demandant à l'utilisateur d'entrer les informations nécessaires. Elle valide également les entrées pour s'assurer qu'elles respectent les contraintes de taille et de format.

### Comment utiliser ce projet

1. Clonez le dépôt sur votre machine locale.
2. Ouvrez le projet dans votre IDE préféré.
3. Naviguez vers la classe principale et exécutez le programme.
4. Suivez les instructions à l'écran pour ajouter de nouveaux programmeurs et gérer les informations existantes.