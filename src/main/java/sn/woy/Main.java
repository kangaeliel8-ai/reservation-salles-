package sn.woy;

import java.time.LocalDateTime;
import java.util.List;

import sn.woy.application.dto.CreerReservationCommand;
import sn.woy.application.service.AnnulerReservationService;
import sn.woy.application.service.CreerReservationService;
import sn.woy.application.validation.CreerReservationValidator;
import sn.woy.data.DonneesDemo;
import sn.woy.domain.Salle;
import sn.woy.repository.ReservationRepository;
import sn.woy.repository.SalleRepository;
import sn.woy.repository.memory.InMemoryReservationRepository;
import sn.woy.repository.memory.InMemorySalleRepository;

public final class Main {

    public static void main(String[] args) {
        // Composition Root : on choisit les implementations ici, nulle part ailleurs
        SalleRepository salleRepository = new InMemorySalleRepository();
        ReservationRepository reservationRepository = new InMemoryReservationRepository();
        CreerReservationValidator validator = new CreerReservationValidator();

        CreerReservationService creerReservationService =
                new CreerReservationService(salleRepository, reservationRepository, validator);
        AnnulerReservationService annulerReservationService =
                new AnnulerReservationService(reservationRepository);

        // Chargement des donnees de demo
        List<Salle> sallesDemo = DonneesDemo.genererSalles();
        sallesDemo.forEach(salleRepository::save);
        Salle salleTest = sallesDemo.get(0);

        System.out.println("Salles chargees : " + salleRepository.findAll().size());

        // Scenario 1 : creation acceptee
        var cmd1 = new CreerReservationCommand(
                salleTest.getId(), "Alice",
                LocalDateTime.of(2026, 9, 25, 9, 0),
                LocalDateTime.of(2026, 9, 25, 11, 0));
        var r1 = creerReservationService.execute(cmd1);
        System.out.println("Reservation creee : " + r1);

        // Scenario 2 : conflit refuse
        var cmd2 = new CreerReservationCommand(
                salleTest.getId(), "Bob",
                LocalDateTime.of(2026, 9, 25, 10, 0),
                LocalDateTime.of(2026, 9, 25, 12, 0));
        try {
            creerReservationService.execute(cmd2);
        } catch (RuntimeException e) {
            System.out.println("Refus attendu : " + e.getMessage());
        }

        // Scenario 3 : annulation
        var annulee = annulerReservationService.execute(r1.getId());
        System.out.println("Reservation annulee : " + annulee.getStatut());
    }
}