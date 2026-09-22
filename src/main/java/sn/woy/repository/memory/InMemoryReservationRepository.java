package sn.woy.repository.memory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import sn.woy.domain.Reservation;
import sn.woy.repository.ReservationRepository;

public final class InMemoryReservationRepository implements ReservationRepository {

    private final Map<Long, Reservation> data = new HashMap<>();
    private long sequence = 0L;

    @Override
    public Reservation save(Reservation reservation) {
        if (reservation.getId() == null) {
            reservation.setId(++sequence);
        }
        data.put(reservation.getId(), reservation);
        return reservation;
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<Reservation> findAll() {
        return List.copyOf(data.values());
    }

    @Override
    public List<Reservation> findBySalleId(Long salleId) {
        return data.values().stream()
                .filter(r -> r.getSalle().getId().equals(salleId))
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        data.remove(id);
    }
}