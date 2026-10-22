# Atelier 5 : Spring MVC, API REST, Postman et Swagger

## A. Endpoints

L'application tourne sur `http://localhost:8089`. Les codes ci-dessous sont ceux du projet **actuel** :
- **succès :** les méthodes n'ont pas encore de `@ResponseStatus`, donc tout succès renvoie `200` ;
- **erreurs :** il n'y a pas encore de `GlobalExceptionHandler`, donc une `ResourceNotFoundException` produit une erreur `500`.

| Contrôleur | Verbe et route | Service appelé | Succès | Erreurs |
| --- | --- | --- | --- | --- |
| `ClientController` | `POST /api/Client/AddClient` | `ajouterClient` | 200 | 400 (JSON invalide), 500 (colonne obligatoire nulle, email ou permis en double) |
| | `GET /api/Client/GetAll` | `recupererClients` | 200 | — |
| | `GET /api/Client/GetClient/{id}` | `recupererClientById` | 200 | 500 (id absent) |
| | `PUT /api/Client/EditClient` | `modifierClient` | 200 | 500 (colonne obligatoire nulle) |
| | `DELETE /api/Client/DeleteClient/{id}` | `supprimerClient` | 200 | 500 (clé étrangère : réservations liées) |
| `AgenceController` | `POST /api/Agence/AddAgence`, `GET /api/Agence/GetAll`, `GET /api/Agence/GetAgence/{id}`, `PUT /api/Agence/EditAgence`, `DELETE /api/Agence/DeleteAgence/{id}` | `ajouterAgence`, `recupererAgences`, `recupererAgenceById`, `modifierAgence`, `supprimerAgence` | 200 | 500 (id absent, clé étrangère) |
| `EmployeController` | même schéma, sous `/api/Employe/...` | méthodes de `IEmploye` | 200 | 500 |
| `VehiculeController` | même schéma, sous `/api/Vehicule/...` | méthodes de `IVehicule` | 200 | 500 |
| `EquipementController` | même schéma, sous `/api/Equipement/...` | méthodes de `IEquipement` | 200 | 500 |
| `ReservationController` | même schéma, sous `/api/Reservation/...` | méthodes de `IReservation` | 200 | 500 |
| `ContratController` | même schéma, sous `/api/Contrat/...` | méthodes de `IContrat` | 200 | 500 |
| `MaintenanceController` | même schéma, sous `/api/Maintenance/...` | méthodes de `IMaintenance` | 200 | 500 |
| `PaiementController` | `GET /api/Paiement/GetAll`, `GET /api/Paiement/GetPaiement/{id}` | `recupererPaiements`, `recupererPaiementById` | 200 | 500 (id absent) ; un POST, PUT ou DELETE sur une de ces routes renvoie 405, et sur une route inexistante (`/AddPaiement`) 404 |

> Cible de l'atelier :
> - des routes au pluriel, sans verbe dans l'URL (`/api/clients`) ;
> - `201` pour un POST et `204` pour un DELETE ;
> - `404` et `400` grâce au `GlobalExceptionHandler`.
>
> *À compléter une fois ces points réalisés.*

## B. Annotations Jackson

| Association | Annotation retenue | Justification |
| --- | --- | --- |
| Contrat ↔ Paiement | `@JsonManagedReference("contrat-paiements")` sur `Contrat.paiements`, `@JsonBackReference("contrat-paiements")` sur `Paiement.contrat` | **Composition** (`cascade = ALL`) : les paiements sont créés dans le JSON de leur contrat. La paire évite la boucle infinie, et Jackson relie chaque paiement à son contrat à la lecture du JSON. |
| Agence ↔ Vehicule | `@JsonIgnore` sur `Agence.vehicules` | Un véhicule est créé seul et désigne son agence (`"agence": {"idAgence": 1}`). On ignore la liste côté inverse. |
| Agence ↔ Employe | `@JsonIgnore` sur `Agence.employes` | Même raisonnement. |
| Client ↔ Reservation | `@JsonIgnore` sur `Client.reservations` | Une réservation désigne son client. `@JsonBackReference` sur `Reservation.client` est déconseillé par l'atelier : la propriété serait ignorée à la lecture, et le POST créerait une réservation sans client. |
| Vehicule ↔ Equipement | `@JsonIgnore` sur `Equipement.vehicules` | Vehicule est le côté propriétaire de la table de jointure : la liste d'équipements reste visible dans le véhicule. |
| Reservation ↔ Contrat | `@JsonIgnore` sur `Contrat.reservation` | Reservation porte la clé étrangère vers Contrat ; le côté inverse est ignoré. |

**Différence entre `@JsonBackReference` et `@JsonIgnore` :**
- **`@JsonIgnore`** retire complètement le champ du JSON, en écriture comme en lecture.
- **`@JsonBackReference`** s'utilise en paire avec `@JsonManagedReference`. Le champ n'est pas écrit dans la réponse, mais Jackson le **renseigne automatiquement** à la lecture : il remplit `paiement.contrat` avec le contrat qui contient le paiement.

## C. Postman et Swagger

| Outil | Ce qu'il apporte | Exemple rencontré |
| --- | --- | --- |
| Postman | Client HTTP pour **tester** l'API : on écrit et rejoue les requêtes, et on vérifie le statut et le corps de la réponse. | Le `POST /api/Client/AddClient` renvoyait `400`, car l'id `long` primitif ne pouvait pas recevoir `null`. Corrigé en passant les ids en `Long`. L'erreur `Invalid protocol` venait du verbe `GET` saisi dans la barre d'URL. |
| Swagger UI (SpringDoc) | **Documente** l'API à partir des contrôleurs (spécification OpenAPI) et permet d'essayer les endpoints depuis le navigateur. | *à compléter : SpringDoc n'est pas encore installé.* |

## D. Anomalies SonarQube for IDE

| Règle | Explication | Correction |
| --- | --- | --- |
| java:S4684 (Security) | Les contrôleurs reçoivent les entités JPA en `@RequestBody` (2 occurrences par contrôleur, POST et PUT). Un client peut modifier des champs gérés par le serveur, comme l'id, le statut ou la validité d'un contrat : c'est le *mass assignment*. | Exposition volontaire et provisoire (Atelier 5, section 1.3). Correction prévue au Chapitre 10 avec des DTO qui ne contiennent que les champs autorisés. |
| *à compléter* | *à compléter* | *à compléter* |

## E. Questions de compréhension

**1. Quel composant reçoit toutes les requêtes, et comment retrouve-t-il la méthode du contrôleur à appeler ?**
Le **`DispatcherServlet`** de Spring MVC. Il lit le verbe HTTP et l'URL, puis cherche la méthode correspondante parmi les mappings déclarés : `@RequestMapping` sur la classe, combiné à `@GetMapping`, `@PostMapping`… sur la méthode. Il appelle cette méthode, et la valeur renvoyée est convertie en JSON par Jackson.

**2. Pourquoi `ContratController` dépend-il de `IContratService`, et non de `ContratRepository` ni de `ContratServiceImpl` ?**
- **Pas le repository :** le contrôleur ne doit jamais accéder directement aux données. Il passe par le service, qui porte la logique métier.
- **Pas l'implémentation :** dépendre de l'interface assure un couplage faible. On peut changer d'implémentation sans modifier le contrôleur.

**3. Pourquoi un POST renvoie-t-il 201 et un DELETE 204 ? Pourquoi une ressource absente donne-t-elle 404 et non 500 ?**
- **201 Created** indique qu'une nouvelle ressource a été créée.
- **204 No Content** indique que la suppression a réussi et qu'il n'y a rien à renvoyer.
- **404** signifie « la ressource demandée n'existe pas » : c'est une erreur **du client**, qui a demandé un mauvais id. **500** désigne une panne **du serveur**. Le `GlobalExceptionHandler` traduit `ResourceNotFoundException` en 404.

**4. Pourquoi `PaiementController` n'expose-t-il que des GET ? Quelle réponse reçoit un POST `/api/Paiement/...` ?**
Paiement fait partie de la **composition** Contrat → Paiement : on crée, modifie et supprime un paiement en passant par son contrat, grâce à la cascade. Le contrôleur n'expose donc que la lecture. Un POST sur une route existante, comme `/api/Paiement/GetAll`, reçoit **405 Method Not Allowed**, car aucune méthode n'est associée à ce verbe sur cette route. Avec les routes au pluriel de l'atelier, `POST /api/paiements` renvoie aussi 405. Avec nos routes actuelles, `POST /api/Paiement/AddPaiement` renvoie 404, car cette route n'existe pas.

**5. Quels risques présente l'exposition directe des entités JPA ? Pourquoi `Contrat.paiements` utilise-t-il `@JsonManagedReference` alors que `Agence.vehicules` utilise `@JsonIgnore` ?**
- **Risques :**
  - couplage entre l'API et la structure de la base ;
  - champs internes visibles dans les réponses ;
  - *mass assignment* (règle S4684) ;
  - boucles de sérialisation et erreurs sur les proxys LAZY.
- **`@JsonManagedReference` contre `@JsonIgnore` :**
  - Les paiements sont créés **dans** le JSON du contrat (composition) : il faut que Jackson renseigne le lien enfant → parent, d'où la paire Managed/Back.
  - Un véhicule est créé **seul** et désigne son agence par son id : la liste côté Agence n'est pas nécessaire dans le JSON, on l'ignore.
