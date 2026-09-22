# Diagramme de dependances - Repository memoire (v0.4.0)

## Relations

- `SalleRepository` (interface) <-- `InMemorySalleRepository` (implementation)
- `ReservationRepository` (interface) <-- `InMemoryReservationRepository` (implementation)
- `InMemorySalleRepository` utilise `Map<Long, Salle>` en interne (encapsule, ne l'expose jamais)
- `InMemoryReservationRepository` utilise `Map<Long, Reservation>` en interne
- `Reservation` reference `Salle` (association directe, pas de dependance circulaire)
- Les interfaces ne dependent d'aucune classe technique (pas de HashMap, ArrayList exposes dans les signatures)

## Schema textuel

Service (futur) --> SalleRepository (interface) <---- InMemorySalleRepository
Service (futur) --> ReservationRepository (interface) <---- InMemoryReservationRepository

## Justification

Le Repository isole le stockage. Le futur Service ne dependra que des interfaces
`SalleRepository` et `ReservationRepository`, jamais des implementations `InMemory*`.
Cela permettra a la v2.0.0 (JDBC) de remplacer le stockage sans modifier les Services.