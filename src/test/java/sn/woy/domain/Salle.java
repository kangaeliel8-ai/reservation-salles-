package sn.woy.reservation.domain;

public final class Salle extends AbstractEntity {
    private String nom;
    private String batiment;
    private int capacite;
    private boolean active;
    private TypeSalle type;

    public Salle(String nom, String batiment, int capacite, TypeSalle type) {
        this.nom = nom;
        this.batiment = batiment;
        this.capacite = capacite;
        this.type = type;
        this.active = true;
    }

    public boolean peutAccueillir(int nb) {
        return active && nb <= capacite;
    }

    public void changerCapacite(int nouvelle) {
        if (nouvelle <= 0) throw new IllegalArgumentException("Capacité invalide");
        this.capacite = nouvelle;
    }

    public String getNom() { return nom; }
    public String getBatiment() { return batiment; }
    public int getCapacite() { return capacite; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public TypeSalle getType() { return type; }

    @Override
    public String toString() {
        return nom + " (" + capacite + " places, " + type + ")";
    }
}