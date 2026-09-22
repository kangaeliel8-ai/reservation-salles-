package sn.woy.reservation.domain;

import java.time.LocalDateTime;

public final class Reservation extends AbstractEntity {
    private Salle salle;
    private String nomReservateur;
    private LocalDateTime debut;
    private LocalDateTime fin;
    private StatutReservation statut;

    public Reservation(Salle salle, String nomReservateur,
                       LocalDateTime debut, LocalDateTime fin) {
        this.salle = salle;
        this.nomReservateur = nomReservateur;
        this.debut = debut;
        this.fin = fin;
        this.statut = StatutReservation.CONFIRMEE;
    }

    public boolean chevauche(LocalDateTime d, LocalDateTime f) {
        return d.isBefore(this.fin) && f.isAfter(this.debut);
    }

    public Salle getSalle() { return salle; }
    public String getNomReservateur() { return nomReservateur; }
    public LocalDateTime getDebut() { return debut; }
    public LocalDateTime getFin() { return fin; }
    public StatutReservation getStatut() { return statut; }
    public void setStatut(StatutReservation statut) { this.statut = statut; }
}