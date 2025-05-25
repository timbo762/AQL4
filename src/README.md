# Gestionnaire de tâches

Application de gestion des tâches pour une équipe ou une petite entreprise.

## Fonctionnalités :

La plupart des fonctionnalités nécessitent une connexion.  
Les utilisateurs non autorisés ont uniquement accès à l'écran d'accueil et au panneau de connexion ou d'inscription.

**L'administrateur (manager) peut :**

* Créer une tâche et l'assigner à n'importe quel utilisateur
* Voir la liste de tous les utilisateurs avec la possibilité de supprimer un utilisateur
* Voir la liste de toutes les tâches avec possibilité de modifier ou supprimer une tâche
* Marquer une tâche comme terminée ou non terminée

**L'utilisateur standard (employé) peut :**

* Créer une tâche uniquement pour lui-même
* Voir la liste de tous les utilisateurs sans pouvoir effectuer d'actions
* Voir la liste de toutes les tâches mais ne peut modifier ou supprimer que celles dont il est responsable
* Marquer ses propres tâches comme terminées ou non terminées

**Chaque utilisateur autorisé peut :**

* Voir son propre profil

## Développé avec

* Spring Boot
* Spring Security
* Base de données H2
* Maven
* Thymeleaf
* Bootstrap
* jQuery

## Utilisateurs de test

Copiez-collez l'adresse email et le mot de passe dans le formulaire de connexion ou cliquez sur un des boutons de démonstration sous le formulaire pour les insérer rapidement :

manager@mail.com mot de passe : 112233

ann@mail.com mot de passe : 112233

mark@mail.com mot de passe : 112233