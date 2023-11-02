🚀 **ProjetJava** 
---

Bienvenue sur ProjetJava, une application CRUD développée en JavaFX. 😊

📂 **Structure de l'architecture** 
- 📁 `.idea`: Configuration et métadonnées de IntelliJ IDEA.
- 📁 `lib`: Contient les SDKs JavaFX pour différentes plateformes.
- 📁 `out`: Répertoire des fichiers compilés.
- 📁 `src`: Code source de l'application.
- 📁 `data`: Données liées à l'application.
- 📁 `exec`: Exécutables et scripts associés.
- 📁 `Projet`: Code source principal de l'application.
- 📁 `resources`: Ressources telles que les styles, les contrôleurs et les images nécessaires pour l’interface graphique.

---

🖥️ **Projet de Gestion de Programmeurs** 
---

Ce projet est une application JavaFX dédiée à la gestion des informations des programmeurs. Il s'agit d'un CRUD (Create, Read, Update, Delete) qui permet aux utilisateurs d'ajouter, de lire, de mettre à jour et de supprimer des informations sur les programmeurs de manière interactive. De plus, le projet est relié à une base de données, ce qui permet de stocker et de récupérer les informations de manière persistante.

**Classe Programmeur** 🧑‍💻

La classe Programmeur est la base de ce projet. Elle modélise un programmeur avec une gamme d'attributs représentant des informations personnelles et professionnelles.

🔹 **Attributs**
- `prenom`: Le prénom du programmeur.
- `nom`: Le nom du programmeur.
- `date`: La date de naissance du programmeur.
- `salaire`: Le salaire du programmeur.
- `prime`: La prime du programmeur.
- `pseudo`: Le pseudo du programmeur.
- `createdAt`: La date de création du programmeur.
- `updateAt`: La date de mise à jour du programmeur.
- `id`: L'identifiant du programmeur.

🔹 **Fonctionnalités**
- **Dashboard**: Affiche des statistiques sur le salaire moyen, la prime, le nombre total de programmeurs et les 10 derniers programmeurs ajoutés.
- **Liste de programmeurs**:
  - **Recherche**: Trouvez rapidement un programmeur par son nom, prénom ou pseudo.
  - **Tri**: Triez les programmeurs par nom, prénom, salaire, prime, etc.
  - **Affichage détaillé**: Cliquez sur un programmeur pour voir tous ses détails.
- **Affichage d'un programmeur**:
  - Voir tous les détails du programmeur.
  - Modifier les informations du programmeur.
  - Supprimer le programmeur de la base de données.
- **Ajout d'un programmeur**: Formulaire pour ajouter un nouveau programmeur avec validation des entrées.
- **Paramètres**:
  - Réinitialiser la BDD: Supprime toutes les données et réinitialise la base de données.
  - Actualiser la BDD: Met à jour les données.
  - Génération aléatoire de programmeurs: Ajoute jusqu'à 1000 programmeurs générés aléatoirement à l'aide de la bibliothèque Faker.

🔹 **Technologie utilisée**
- **JavaFX**: Pour la création d'une interface utilisateur interactive.
- **Scene Builder**: Utilisé pour concevoir l'interface graphique de l'application de manière visuelle.
- **Base de données**: Le projet est connecté à une base de données pour permettre un stockage durable des données. Les opérations CRUD sont effectuées en utilisant des requêtes SQL pour interagir avec cette base.
- **Fake Java**: permet de générer les programmeurs aléatoirement tout en restant cohérent.

🔹 **Architecture du code**:
Le code est divisé en deux catégories, le back et le front. Le code back est celui utilisé pour faire l'affichage dans la console. Tandis que le code front est celui utilisé pour l’affichage. À cause de JavaFx, le code du front est structuré sur une base Controller et d’entité pour chaque besoin de l’application. Le tout est centralisé dans un controller appelé MainController. Il sera le controller qui redirigera l'action vers le controller concerné.

🔹 **Configuration nécessaire**
Afin d'utiliser et d'exécuter ce projet correctement, vous devez avoir installé :
- JDK 20 et JRE (Java Development Kit & Java Runtime Environment).
- JavaFX 21-0-1 et Scene Builder pour la gestion de l'interface graphique.
- Une base de données compatible (par exemple, MySQL ou SQLite).

🔹 **Comment utiliser ce projet**
- **Cloner le dépôt**: Clonez ce dépôt sur votre machine locale.
- **Configuration de la base de données**: Assurez-vous d'avoir configuré correctement votre
- **Ouvrez dans un IDE**: Ouvrez le projet dans un environnement de développement intégré (IDE) comme IntelliJ IDEA ou Eclipse.
- **Exécuter l'application**: Localisez la classe principale (`src/exec/Start.java`) et lancez le programme.
- **Utiliser l'application**: 
  - Choisissez d’ouvrir le projet sur la console ou avec une interface graphique.
  - Dans la console, il suffit de suivre le menu affiché.
  - Avec l’interface graphique : une interface utilisateur s'ouvrira. Suivez les instructions à l'écran pour interagir avec les données des programmeurs.
    - **Dashboard** permet de visualiser la base de données avec des statistiques sur les programmeurs.
    - **Liste Programmeurs** est le tableau des programmeurs présents dans la base de données avec leur informations.
    - **Ajouter un programmeur** permet de remplir les informations pour ajouter un nouveau programmeur dans la base de données.
    - **Paramètres** permet d’actualiser ou de réinitialiser la base de données. Et d’ajouter un nombre entre 1 et 1000 programmeurs générés aléatoirement.

---

🎉 **Conclusion** 
---

Ce projet de Gestion de Programmeurs est un excellent exemple d'une application JavaFX CRUD bien conçue et robuste, reliée à une base de données. Il démontre les compétences nécessaires pour créer une application de bureau moderne et fonctionnelle. Nous vous invitons à explorer le code, à contribuer ou à l'utiliser comme inspiration pour vos propres projets. 😄
