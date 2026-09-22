package sn.woy.application.exception;

public final class SalleInactiveException extends RuntimeException {
    public SalleInactiveException(Long salleId) {
        super("La salle " + salleId + " est inactive");
    }
}