# AutoLoc

Plateforme de gestion de location de véhicules multi-agences.
Projet fil rouge du module **Architecture des Systèmes d'Information** (UP ASI, ESPRIT, 2026-2027).

## Objectifs du projet

AutoLoc est une entreprise de location de véhicules disposant de plusieurs agences réparties dans différentes villes.
Chaque agence gère une flotte de véhicules de catégories variées (citadine, berline, SUV, utilitaire).
Les clients réservent un véhicule pour une période donnée ; à la signature, un contrat est établi et des paiements y sont rattachés.

L'objectif est de numériser l'ensemble du processus au travers d'une application back-end **Spring Boot** exposant une **API REST** :

- réservation ;
- contractualisation ;
- facturation ;
- suivi de flotte ;
- relances automatiques.

## Acteurs

| Acteur | Description | Droits principaux |
| --- | --- | --- |
| **Client** | Particulier ou professionnel souhaitant louer un véhicule | Consulter les véhicules disponibles, créer / annuler une réservation, consulter ses contrats |
| **Agent d'agence** | Employé en charge de la gestion opérationnelle d'une agence | Gérer les véhicules, valider une réservation, établir un contrat, enregistrer un paiement |
| **Responsable d'agence (Manager)** | Supervise une agence et son personnel | Droits de l'agent + gestion des employés, statistiques de l'agence |
| **Administrateur** | Administre la plateforme | Gestion des agences, des catégories de véhicules, statistiques globales, configuration |

## Cas d'utilisation

- **Client** : s'inscrire, rechercher un véhicule disponible (ville, catégorie, période), réserver, annuler une réservation, consulter ses contrats.
- **Agent d'agence** : gérer les véhicules et leur statut, valider une réservation, établir un contrat, enregistrer un paiement, suivre les maintenances.
- **Responsable d'agence** : tous les cas de l'agent, gérer les employés de l'agence, consulter les statistiques de l'agence.
- **Administrateur** : gérer les agences, gérer les catégories de véhicules, consulter les statistiques globales, configurer la plateforme.

## Modules fonctionnels

- Gestion des agences et de la flotte
- Gestion des clients
- Réservation
- Contractualisation et paiement
- Tarification
- Tâches planifiées
- Reporting et qualité

## Stack technique

| Catégorie | Outils |
| --- | --- |
| Langage / Build | Java 17+, Maven |
| Framework | Spring Boot, Spring Data JPA, Spring MVC, Spring AOP, Spring Scheduler |
| Base de données | MySQL (XAMPP / phpMyAdmin) |
| Productivité | Lombok |
| Outillage | Git / GitHub, Postman, IntelliJ IDEA |

## Lancer le projet

1. Démarrer MySQL depuis XAMPP.
2. Vérifier `src/main/resources/application.properties` (base `autoloc_db`, créée automatiquement).
3. Lancer `AutoLocApplication` depuis IntelliJ.
4. L'application démarre sur le port `8089` ; les tables sont générées automatiquement (`ddl-auto=update`).

## Avancement

- [x] Atelier 0 : mise en place de l'environnement
- [x] Atelier 1 : projet Spring Boot + entités JPA (sans associations)
- [ ] Atelier 2 : associations entre entités
