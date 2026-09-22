package sn.woy.application.validation;

import sn.woy.application.dto.CreerReservationCommand;
import sn.woy.application.exception.ValidationException;

public final class CreerReservationValidator {

    public void validate(CreerReservationCommand cmd) {
        if (cmd.salleId() == null) {
            throw new ValidationException("La salle est obligatoire");
        }
        if (cmd.reservateur() == null || cmd.reservateur().isBlank()) {
            throw new ValidationException("Le reservateur est obligatoire");
        }
        if (cmd.debut() == null || cmd.fin() == null) {
            throw new ValidationException("Les dates de debut et de fin sont obligatoires");
        }
        if (!cmd.debut().isBefore(cmd.fin())) {
            throw new ValidationException("Le debut doit preceder la fin");
        }
    }
}