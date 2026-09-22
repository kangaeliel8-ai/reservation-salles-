// domain/Salle.java
package com.ism.reservationsalles.domain;

import java.util.ArrayList;
import java.util.List;

public class Salle extends AbstractEntity {

    private String nom;
    private int capacite;
    private TypeSalle type;
    private final List<String> equipements;
    private boolean active;

    public Salle(String nom, int capacite, TypeSalle type) {
        this.nom = nom;
        this.capacite = capacite;
        this.type = type;
        this.equipements = new ArrayList<>();
        this.active = true;
    }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public int getCapacite() { return capacite; }
    public void setCapacite(int capacite) { this.capacite = capacite; }
    public TypeSalle getType() { return type; }
    public void setType(TypeSalle type) { this.type = type; }
    public List<String> getEquipements() { return equipements; }
    public void ajouterEquipement(String equipement) { this.equipements.add(equipement); }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    @Override
    public String toString() {
        return "Salle{id=" + getId() + ", nom='" + nom + "', capacite=" + capacite +
                ", type=" + type + ", active=" + active + '}';
    }
}