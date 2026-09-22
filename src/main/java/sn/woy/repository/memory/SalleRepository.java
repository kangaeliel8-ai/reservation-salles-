package sn.woy.repository;

import java.util.List;
import java.util.Optional;

import sn.woy.domain.Salle;

public interface SalleRepository {
    Salle save(Salle salle);
    Optional<Salle> findById(Long id);
    List<Salle> findAll();
    boolean existsByNom(String nom);
}