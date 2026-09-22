package sn.woy.application.exception;

public final class CreneauIndisponibleException extends RuntimeException {
    public CreneauIndisponibleException(Long salleId) {
        super("Le creneau demande est indisponible pour la salle " + salleId);
    }
}