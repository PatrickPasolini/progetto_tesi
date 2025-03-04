package it.unibs.exception;

public class ListaComprensoriVuotaException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public ListaComprensoriVuotaException() {
        super("Non esistono comprensori");
    }

}
