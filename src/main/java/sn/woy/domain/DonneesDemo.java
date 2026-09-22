package sn.woy.data;

import java.util.ArrayList;
import java.util.List;

import sn.woy.domain.Salle;
import sn.woy.domain.TypeSalle;

public class DonneesDemo {

    public static List<Salle> genererSalles() {
        List<Salle> salles = new ArrayList<>();

        Salle s1 = new Salle("A101", 30, TypeSalle.SALLE_COURS);
        s1.ajouterEquipement("Videoprojecteur");
        salles.add(s1);

        Salle s2 = new Salle("TP-Info-1", 20, TypeSalle.SALLE_TP);
        s2.ajouterEquipement("20 postes informatiques");
        s2.ajouterEquipement("Tableau blanc");
        salles.add(s2);

        Salle s3 = new Salle("Amphi Descartes", 150, TypeSalle.AMPHITHEATRE);
        s3.ajouterEquipement("Sonorisation");
        s3.ajouterEquipement("Videoprojecteur");
        salles.add(s3);

        Salle s4 = new Salle("Salle Reunion Direction", 10, TypeSalle.SALLE_REUNION);
        s4.ajouterEquipement("Ecran tactile");
        salles.add(s4);

        Salle s5 = new Salle("Labo Physique", 15, TypeSalle.LABORATOIRE);
        s5.ajouterEquipement("Paillasses");
        s5.ajouterEquipement("Materiel de mesure");
        salles.add(s5);

        return salles;
    }
}