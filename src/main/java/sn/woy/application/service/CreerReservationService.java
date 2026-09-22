package sn.woy.application.service;

import sn.woy.application.dto.CreerReservationCommand;
import sn.woy.application.exception.CreneauIndisponibleException;
import sn.woy.application.exception.SalleInactiveException;
import sn.woy.application.exception.SalleIntrouvableException;
import sn.woy.application.validation.CreerReservationValidator;
import sn.woy.domain.Reservation;
import sn.woy.domain.Salle;
import sn.woy.repository.ReservationRepository;
import sn.woy.repository.SalleRepository;

public final class CreerReservationService {

    private final SalleRepository salles;
    private final ReservationRepository reservations;
    private final CreerReservationValidator validator;

    public CreerReservationService(SalleRepository salles,
                                    ReservationRepository reservations,
                                    CreerReservationValidator validator) {
        this.salles = salles;
        this.reservations = reservations;
        this.validator = validator;
    }

    public Reservation execute(CreerReservationCommand cmd) {
        validator.validate(cmd);

        Salle salle = salles.findById(cmd.salleId())
                .orElseThrow(() -> new SalleIntrouvableException(cmd.salleId()));

        if (!salle.isActive()) {
            throw new SalleInactiveException(salle.getId());
        }

        boolean conflit = reservations.findBySalleId(salle.getId()).stream()
                .anyMatch(r -> r.chevauche(cmd.debut(), cmd.fin()));

        if (conflit) {
            throw new CreneauIndisponibleException(salle.getId());
        }

        Reservation reservation = new Reservation(salle, cmd.reservateur(), cmd.debut(), cmd.fin());
        return reservations.save(reservation);
    }
}