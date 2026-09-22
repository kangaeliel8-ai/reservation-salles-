# DEVLOG

## Incrément v0.5.0 — Recherches avec Streams

### Comparaison boucle vs Stream — recherche salles actives par capacité

**Probleme** : filtrer les salles actives avec capacite >= seuil, triees par capacite decroissante.

**Version boucle (non conservee)** :
```java
List<Salle> resultat = new ArrayList<>();
for (Salle s : salles) {
    if (s.isActive() && s.getCapacite() >= capaciteMin) {
        resultat.add(s);
    }
}
```

**Version Stream (retenue dans InMemorySalleRepository)** :
```java
return data.values().stream()
        .filter(Salle::isActive)
        .filter(s -> s.getCapacite() >= capaciteMin)
        .sorted((a, b) -> Integer.compare(b.getCapacite(), a.getCapacite()))
        .toList();
```

**Justification du choix** :
- **Lisibilite** : la chaine filter/filter/sorted/toList decrit l'intention en une phrase,
  sans variable intermediaire mutable a suivre mentalement.
- **Mutation d'etat** : la version boucle construit `resultat` pas a pas (etat mutable
  expose pendant tout le parcours) ; la version Stream ne mute rien, chaque etape produit
  une nouvelle vue.
- **Arret anticipe** : non utile ici (on veut tous les resultats), mais la meme logique
  appliquee a la detection de conflit utilise `anyMatch`, qui s'arrete des le premier
  match trouve — un gain reel des que la liste de reservations grandit.

**parallelStream()** : non utilise. Le volume de donnees (quelques dizaines de salles/
reservations en memoire) est trop faible pour justifier le cout de partitionnement et de
synchronisation implicite ; aucun calcul lourd par element ne le justifierait non plus.

### Detection de conflit de reservation

La regle de chevauchement vit dans `Reservation.chevauche(debut, fin)` (domaine, depuis v0.2.0).
Le Service (incrément v0.7.0) l'utilisera ainsi :

```java
boolean conflit = reservationRepository.findBySalleId(salle.getId()).stream()
        .anyMatch(r -> r.chevauche(debut, fin));
```

Le Repository ne prend pas la decision "chevauchement = refus" — il fournit uniquement
la liste des reservations d'une salle. La regle metier reste au niveau du Service,
conformement au principe vu en cours (le Repository ne contient pas de decision metier).

**Branche** : feature/04-stream-queries
**Commits** : "feat: rechercher les salles avec les streams", "feat: detecter les conflits de reservation"
**Tag** : v0.5.0
**A ameliorer** : ajouter des tests unitaires JUnit pour figer ce comportement (prevu apres v0.7.0).