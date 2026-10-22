# Atelier 4 : IoC, injection de dépendances et couche Service

## A. Modes d'injection comparés

| Critère | Injection par constructeur | Injection par attribut (`@Autowired` sur le champ) |
| --- | --- | --- |
| Champ `final` possible ? | Oui | Non : Spring affecte le champ après la construction de l'objet |
| Dépendance visible dans la signature de la classe ? | Oui : elle apparaît dans le constructeur | Non : elle est cachée dans un champ privé |
| Classe utilisable avec `new` hors conteneur, sans dépendance `null` ? | Oui : impossible de créer l'objet sans lui fournir son repository | Non : avec `new`, le champ reste `null` (`NullPointerException` au premier appel) |
| Signalé par SonarQube for IDE ? | Non | Oui, règle *java:S6813 (à vérifier dans le panneau lors de l'expérience)* |

**Convention AutoLoc :**
- injection par constructeur, avec des champs `final` typés par une interface ;
- Lombok `@RequiredArgsConstructor` génère le constructeur ;
- comme la classe n'a qu'un seul constructeur, Spring l'utilise sans `@Autowired`.

## B. Services

| Service | Dépendances injectées | Mode | Justification |
| --- | --- | --- | --- |
| `ContratServiceImpl` | `IContratRepo` | Constructeur | Le Contrat porte la composition avec ses paiements ; la dépendance obligatoire est fournie à la création, et le champ est `final`. |
| `PaiementServiceImpl` | `IPaiementRepo` | Constructeur | Lecture des paiements. Leur création et leur suppression passent par le Contrat. |
| `AgenceServiceImpl` | `IAgenceRepo` | Constructeur | Dépendance unique et obligatoire, typée par interface (proxy Spring Data). |
| `EmployeServiceImpl` | `IEmployeRepo` | Constructeur | Idem. |
| `VehiculeServiceImpl` | `IVehiculeRepo` | Constructeur | Idem. |
| `EquipementServiceImpl` | `IEquipementRepo` | Constructeur | Idem. |
| `ClientServiceImpl` | `IClientRepo` | Constructeur | Idem. |
| `ReservationServiceImpl` | `IReservationRepo` | Constructeur | Idem. |
| `MaintenanceServiceImpl` | `IMaintenanceRepo` | Constructeur | Idem. |

Points communs à tous les services :
- annotés `@Service` et `@RequiredArgsConstructor` ;
- **sans état** : leur seul attribut est le repository `final` ;
- aucun `new` de repository ;
- la recherche par id lève `ResourceNotFoundException` quand l'entité est absente (`orElseThrow`).

## C. Messages d'erreur du conteneur

**Message 1 : `required a bean of type 'IContratService' that could not be found`**
- Exception : `NoSuchBeanDefinitionException`, enveloppée dans une `UnsatisfiedDependencyException`.
- Bean concerné : `ContratController`, dont le constructeur réclame un `IContratService`.
- Causes probables :
  - l'annotation `@Service` a été oubliée sur `ContratServiceImpl` ;
  - la classe est placée hors du package racine `tn.esprit.autoloc`, donc ignorée par le scan des composants.
- Correction : ajouter `@Service`, ou déplacer la classe dans un sous-package de `tn.esprit.autoloc`.

**Message 2 : `required a single bean, but 2 were found: emailNotificateur, smsNotificateur`**
- Exception : `NoUniqueBeanDefinitionException`. Deux beans implémentent le même type, et Spring ne sait pas lequel injecter.
- Deux corrections possibles :
  1. `@Primary` **sur la classe** à utiliser par défaut, par exemple `EmailNotificateur`.
  2. `@Qualifier("smsNotificateur")` **sur le paramètre du constructeur** de `NotificationController`, pour choisir explicitement le bean par son nom.

**Message 3 : cycle `clientServiceImpl` ↔ `reservationServiceImpl`**
- Exception : `BeanCurrentlyInCreationException` (dépendance circulaire).
- Pourquoi l'injection par constructeur rend le cycle impossible à contourner : pour construire A, Spring doit d'abord construire B, et pour construire B, il lui faut A déjà construit. Aucun des deux objets ne peut exister en premier.
- Piste de conception :
  - extraire la responsabilité commune dans un troisième service dont les deux dépendent ;
  - ou faire dépendre un service du **repository** de l'autre entité plutôt que de son service.

## D. Anomalies SonarQube for IDE

| Règle | Explication | Correction apportée |
| --- | --- | --- |
| java:S3655 (Reliability) | `findById(id).get()` lève `NoSuchElementException` si l'id n'existe pas : le cas d'absence n'est pas traité. Relevé dans les 9 services. | `findById(id).orElseThrow(() -> new ResourceNotFoundException("Entité", id))` : l'absence devient une exception métier explicite. |
| java:S6813 (injection par attribut) | *à compléter après l'expérience de l'étape 2* | Retour à l'injection par constructeur (`@RequiredArgsConstructor` + champ `final`). |
| java:S112 (exception générique) | *à compléter après l'expérience de l'étape 4* : lever `RuntimeException` ne dit pas quel type d'erreur s'est produit. | Exception métier dédiée `ResourceNotFoundException`. |

## E. Questions de compréhension

**1. Où est le `new` de `ContratServiceImpl`, et qui l'exécute ?**
Il n'y en a aucun dans notre code. C'est le **conteneur Spring** (`ApplicationContext`) qui l'exécute au démarrage :
- il détecte la classe grâce à `@Service` et au scan des composants ;
- il appelle son constructeur, généré par Lombok ;
- il lui passe le proxy de `IContratRepo`.

C'est l'inversion de contrôle.

**2. Pourquoi un contrôleur dépend-il de `IContratService` et non de `ContratServiceImpl` ?**
Pour un **couplage faible**. Le contrôleur ne connaît que le contrat (l'interface), pas l'implémentation. On peut donc changer ou remplacer l'implémentation sans toucher au contrôleur : une autre classe, ou une fausse implémentation pour les tests.

**3. Pourquoi un service singleton doit-il rester sans état ? Donner un exemple de bug.**
Le scope par défaut est singleton : **une seule instance**, partagée par toutes les requêtes et tous les threads. Un attribut modifiable serait donc partagé entre utilisateurs.

Exemple de bug : on stocke un attribut `Contrat contratCourant` dans le service. Deux requêtes simultanées l'écrasent mutuellement, et l'utilisateur A finit par modifier le contrat de l'utilisateur B.

**4. Pourquoi `update` charge-t-il l'entité existante avant d'appeler `save`, au lieu de lui passer directement l'objet reçu ?**
`save` sur un objet qui a un id fait un *merge* : il remplace **toutes** les colonnes.
- Un champ absent du JSON écraserait donc la valeur en base par `null`.
- Avec `orphanRemoval`, une liste de paiements absente viderait les paiements du contrat.

En chargeant l'existant puis en recopiant uniquement les attributs modifiables, on ne change que ce qui doit l'être.

> Dans le projet, les méthodes `modifierX` appellent encore `save` directement sur l'objet reçu. La mise à jour par chargement puis recopie reste à implémenter.

**5. Quelle différence entre `@Component` et `@Bean` ? Entre `@Primary` et `@Qualifier` ? Pourquoi `IPaiementService` n'expose-t-il ni création, ni modification, ni suppression ?**
- **`@Component` et `@Bean` :**
  - `@Component` (et ses spécialisations `@Service`, `@Repository`, `@Controller`) se pose **sur une classe** : le scan la détecte et Spring l'instancie.
  - `@Bean` se pose **sur une méthode** d'une classe `@Configuration`, qui crée et renvoie l'objet elle-même. On l'utilise pour une classe qu'on ne peut pas annoter (bibliothèque, JDK) ou qui demande une construction particulière.
- **`@Primary` et `@Qualifier` :**
  - `@Primary` se pose **sur la classe** et la désigne comme candidat par défaut quand plusieurs beans ont le même type.
  - `@Qualifier("nom")` se pose **au point d'injection** et choisit explicitement un bean par son nom.
- **Paiement en lecture seule :** Contrat → Paiement est une **composition**. Un paiement n'existe qu'à travers son contrat (`cascade = ALL`), donc on le crée, le modifie et le supprime en passant par le Contrat. Exposer ces opérations sur Paiement permettrait de créer des paiements orphelins ou incohérents avec leur contrat.

> Dans le projet, la lecture seule est appliquée dans `PaiementController` (seulement des GET). L'interface `IPaiement` expose encore `ajouterPaiement` et `supprimerPaiement`.
