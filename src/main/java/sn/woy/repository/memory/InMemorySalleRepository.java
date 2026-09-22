package sn.woy.repository.memory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import sn.woy.domain.Salle;
import sn.woy.repository.SalleRepository;

public final class InMemorySalleRepository implements SalleRepository {

    private final Map<Long, Salle> data = new HashMap<>();
    private long sequence = 0L;

    @Override
    public Salle save(Salle salle) {
        if (salle.getId() == null) {
            salle.setId(++sequence);
        }
        data.put(salle.getId(), salle);
        return salle;
    }

    @Override
    public Optional<Salle> findById(Long id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<Salle> findAll() {
        return List.copyOf(data.values());
    }

    @Override
    public boolean existsByNom(String nom) {
        return data.values().stream()
                .anyMatch(s -> s.getNom().equalsIgnoreCase(nom));
    }

    @Override
    public List<Salle> findByCapaciteMinimale(int capaciteMin) {
        return data.values().stream()
                .filter(Salle::isActive)
                .filter(s -> s.getCapacite() >= capaciteMin)
                .sorted((a, b) -> Integer.compare(b.getCapacite(), a.getCapacite()))
                .toList();
    }
}