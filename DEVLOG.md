
## Incrément v0.7.0 — Services et injection par constructeur

**Services crees** : `CreerReservationService`, `AnnulerReservationService`.

**Injection** : les deux Repository et le validateur sont recus par constructeur.
Aucun `new InMemory...Repository()` n'apparait dans les Services — ils dependent
uniquement des interfaces `SalleRepository` et `ReservationRepository`.

**Composition Root** : `App.java` (methode `main`) est le seul endroit ou les
implementations concretes sont choisies et assemblees.

**DIP applique** : `CreerReservationService` (module de haut niveau, contient la regle
de chevauchement) depend de `ReservationRepository` (abstraction), jamais de
`InMemoryReservationRepository` (detail technique). A la v2.0.0, une implementation
JDBC pourra remplacer le stockage memoire sans modifier le Service.

**Scenarios verifies dans App.java** :

1. Creation d'une reservation valide -> acceptee.
2. Creation d'une reservation en conflit -> `CreneauIndisponibleException` levee et interceptee.
3. Annulation d'une reservation -> statut passe a `ANNULEE`.

**Branche** : feature/06-reservation-services
**Tag** : v0.7.0
**A ameliorer** : remplacer les scenarios manuels dans `App.java` par de vrais tests JUnit.
