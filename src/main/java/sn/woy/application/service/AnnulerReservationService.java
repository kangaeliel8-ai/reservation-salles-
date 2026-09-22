package sn.woy.application.service;

import sn.woy.domain.Reservation;
import sn.woy.domain.StatutReservation;
import sn.woy.repository.ReservationRepository;

public final class AnnulerReservationService {

    private final ReservationRepository reservations;

    public AnnulerReservationService(ReservationRepository reservations) {
        this.reservations = reservations;
    }

    public Reservation execute(Long reservationId) {
        Reservation reservation = reservations.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Reservation " + reservationId + " introuvable"));
        reservation.setStatut(StatutReservation.ANNULEE);
        return reservations.save(reservation);
    }
}