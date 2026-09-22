package sn.woy.domain;

import java.time.LocalDateTime;

public class Reservation extends AbstractEntity {

    private final Salle salle;
    private final String reservateur;
    private String motif;
    private final LocalDateTime debut;
    private final LocalDateTime fin;
    private StatutReservation statut;

    public Reservation(Salle salle, String reservateur, LocalDateTime debut, LocalDateTime fin) {
        this.salle = salle;
        this.reservateur = reservateur;
        this.debut = debut;
        this.fin = fin;
        this.statut = StatutReservation.EN_ATTENTE;
    }

    public Salle getSalle() {
        return salle;
    }

    public String getReservateur() {
        return reservateur;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public LocalDateTime getDebut() {
        return debut;
    }

    public LocalDateTime getFin() {
        return fin;
    }

    public StatutReservation getStatut() {
        return statut;
    }

    public void setStatut(StatutReservation statut) {
        this.statut = statut;
    }

    public boolean chevauche(LocalDateTime autreDebut, LocalDateTime autreFin) {
        return autreDebut.isBefore(this.fin) && autreFin.isAfter(this.debut);
    }

    @Override
    public String toString() {
        return "Reservation{id=" + getId() + ", salle=" + (salle != null ? salle.getNom() : "null") +
                ", reservateur='" + reservateur + "', debut=" + debut + ", fin=" + fin +
                ", statut=" + statut + '}';
    }
}