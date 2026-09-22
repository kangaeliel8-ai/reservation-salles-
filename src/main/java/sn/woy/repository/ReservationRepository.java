package sn.woy.repository;

import java.util.List;
import java.util.Optional;

import sn.woy.domain.Reservation;

public interface ReservationRepository {
    Reservation save(Reservation reservation);
    Optional<Reservation> findById(Long id);
    List<Reservation> findAll();
    List<Reservation> findBySalleId(Long salleId);
    void deleteById(Long id);
}