package sn.woy.application.exception;

public final class SalleIntrouvableException extends RuntimeException {
    public SalleIntrouvableException(Long salleId) {
        super("La salle " + salleId + " est introuvable");
    }
}