# Atelier 3 : couche Repository (Spring Data JPA)

## 1. Choix de l'interface

Les neuf repositories se trouvent dans le package `tn.esprit.autoloc.Repositories`. Ils étendent tous `JpaRepository<Entité, Long>`. Au démarrage, les logs indiquent `Found 9 JPA repository interfaces`.

| Interface | Étend | Justification |
| --- | --- | --- |
| `IContratRepo` | `JpaRepository<Contrat, Long>` | CRUD complet, `findAll` renvoie une `List`, `saveAndFlush` disponible. Le Contrat est le parent de la composition Contrat → Paiement (`cascade = ALL`) : c'est par lui qu'on crée ou retire des paiements. |
| `IPaiementRepo` | `JpaRepository<Paiement, Long>` | Nécessaire pour **lire** les paiements (liste, recherche par id). Règle de la composition : le code métier ne crée ni ne supprime un paiement directement, il passe par le Contrat. |
| `IAgenceRepo` | `JpaRepository<Agence, Long>` | CRUD complet sur les agences, avec listes, tri et pagination si besoin (statistiques par agence). |
| `IEmployeRepo` | `JpaRepository<Employe, Long>` | CRUD complet ; un employé est créé seul et désigne son agence (`ManyToOne`). |
| `IVehiculeRepo` | `JpaRepository<Vehicule, Long>` | CRUD complet ; le tri et la pagination serviront à la recherche de véhicules disponibles. |
| `IEquipementRepo` | `JpaRepository<Equipement, Long>` | CRUD complet sur le catalogue d'équipements (`ManyToMany` avec Vehicule). |
| `IClientRepo` | `JpaRepository<Client, Long>` | CRUD complet ; `findAll` renvoie une `List`, directement exploitable par le service. |
| `IReservationRepo` | `JpaRepository<Reservation, Long>` | CRUD complet ; la réservation porte les clés étrangères vers Client, Vehicule et Contrat. |
| `IMaintenanceRepo` | `JpaRepository<Maintenance, Long>` | CRUD complet sur l'historique de maintenance d'un véhicule. |

**Pourquoi `JpaRepository` plutôt qu'une autre interface :**
- **`CrudRepository`** suffit pour le CRUD, mais `findAll` renvoie un `Iterable`, moins pratique qu'une `List`.
- **`PagingAndSortingRepository`** n'apporte que le tri et la pagination, sans CRUD depuis Spring Data 3.
- **`JpaRepository`** cumule les deux, en version `List`, et ajoute `flush`, `saveAndFlush`, `getReferenceById` et les suppressions en lot.

**Remarques :**
- **`@Repository` est facultative** sur une interface Spring Data, car le proxy est généré au démarrage. Elle est conservée dans le projet par cohérence de style.
- **Les suppressions en lot** (`deleteAllInBatch`, `deleteAllByIdInBatch`) contournent le contexte de persistance : ni la cascade ni `orphanRemoval` ne s'appliquent. Supprimer des contrats de cette façon échouerait sur la contrainte de clé étrangère des paiements.
- **`deleteById`** ne lève aucune exception si l'id n'existe pas. C'est au service d'imposer cette règle (Atelier 4).

## 2. Comportement observé (logs SQL)

| Opération | Requêtes SQL attendues | Observé |
| --- | --- | --- |
| `save(contrat)` avec 2 paiements, contrat nouveau | 1 `insert into contrat`, puis 2 `insert into paiement` (cascade PERSIST) | *à compléter* |
| `save(contrat)` sur un contrat existant | `select` puis `update contrat` (merge) | *à compléter* |
| `findById(id)` | 1 `select ... where id_contrat=?` ; renvoie un `Optional` | *à compléter* |
| `deleteById(id)` sur un contrat | `select`, puis `delete from paiement` (cascade REMOVE), puis `delete from contrat` | *à compléter* |

## 3. Anomalies SonarQube for IDE

| Anomalie | Règle / explication | Correction apportée |
| --- | --- | --- |
| *à compléter* | *à compléter* | *à compléter* |
| *à compléter* | *à compléter* | *à compléter* |
| *à compléter* | *à compléter* | *à compléter* |

> Pistes : imports inutilisés (java:S1128), noms de packages non conformes (java:S120), `@Data` sur une entité. Lancer l'analyse sur les packages `Entities` et `Repositories`.
