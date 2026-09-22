package sn.woy.application.dto;

import java.time.LocalDateTime;

public record CreerReservationCommand(
        Long salleId,
        String reservateur,
        LocalDateTime debut,
        LocalDateTime fin
) {}