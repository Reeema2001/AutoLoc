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
- [x] Atelier 2 : associations entre entités (OneToOne, OneToMany, ManyToOne, ManyToMany, cascade, fetch)
- [x] Atelier 3 : Spring Data JPA et Repository (un repository `JpaRepository` par entité)
- Atelier 4 : IoC, injection de dépendances et couche Service
  - [x] Une interface et une implémentation `@Service` par entité, injection par constructeur
  - [x] Identifiants des entités en `Long`
  - [x] Exception métier `ResourceNotFoundException` (recherche par id avec `orElseThrow`)
  - [x] Correction SonarQube for IDE : règle java:S3655 (`Optional.get()` sans vérification)
- Atelier 5 : Spring MVC et API REST
  - [x] `ClientController` : ajout, affichage, recherche par id, modification, suppression (testé avec Postman)
  - [x] Contrôleurs Agence, Employe, Vehicule, Equipement, Reservation, Contrat et Maintenance (CRUD)
  - [x] `PaiementController` en lecture seule (les paiements sont créés en cascade avec leur contrat)
  - [x] Annotations Jackson contre les boucles de sérialisation (`@JsonManagedReference` / `@JsonBackReference` pour Contrat ↔ Paiement, `@JsonIgnore` côté inverse)
  - [x] Documentation Swagger avec SpringDoc : `OpenApiConfig`, `@Tag` et `@Operation` sur les contrôleurs (`/autoloc/swagger-ui/index.html`)
- Atelier 6 : affectation et désaffectation entre entités (Chapitre 7)
  - [x] `EmployeController` : désaffecter un employé de son agence, puis l'affecter à une autre (`PUT /api/Employe/desaffecterEmploye/{idEmploye}`, `PUT /api/Employe/affecterEmploye/{idEmploye}/{idAgence}`)
  - [x] `VehiculeController` : affecter un véhicule à une agence (`PUT /api/Vehicule/affecterVehicule/{idVehicule}/{idAgence}`)
  - [x] `VehiculeController` : affecter et désaffecter un équipement (`PUT /api/Vehicule/affecterEquipement/{idVehicule}/{idEquipement}`, `PUT /api/Vehicule/desaffecterEquipement/{idVehicule}/{idEquipement}`)
  - [x] Services en `@Transactional` : chargement par `findById` (`orElseThrow`), modification du côté propriétaire, puis `save`
- Activité DTO (Chapitre 10 : Spring DTO et MapStruct)
  - [x] MapStruct 1.5.5.Final ajouté au `pom.xml` (dépendance et annotation processor, avec `lombok-mapstruct-binding`)
  - [x] Scénario 1 : `ClientRequestDto` (nom et prénom obligatoires, email et téléphone facultatifs), `ClientResponseDto`, `ClientMapper` ; `ajouterClient` et `recupererClientById` adaptés aux DTO (`POST /api/Client/AddClient`, `GET /api/Client/GetClient/{id}`)
  - [x] Scénario 2 : `ReservationResponseDto` (réservation + nom/prénom du client + marque/modèle/immatriculation du véhicule), `ReservationMapper` avec `@Mapping` sur les relations ; `GET /api/Reservation/GetAll` renvoie des DTO, nouvelle route `GET /api/Reservation/GetReservationsClient/{idClient}` (requête JPQL `@Query`)
  - [x] Entité `Client` sans validators (ils sont dans `ClientRequestDto`) ; email, téléphone et numéro de permis facultatifs ; date d'inscription fixée par le service
  - [x] Nouvelle base de données `autoloc_dto_db` (créée par Hibernate au démarrage)
