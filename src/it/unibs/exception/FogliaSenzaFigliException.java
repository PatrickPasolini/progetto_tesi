package it.unibs.exception;

public class FogliaSenzaFigliException extends Exception {
	private static final long serialVersionUID = 1L;

	public FogliaSenzaFigliException() {
        super("Non si puo' aggiungere un figlio ad una foglia");
    }
}
