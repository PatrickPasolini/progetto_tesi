package it.unibs.domain;

/**
 * Classe che rappresente il fruitore
 * Estende la classe {@link Utente}
 */
public class Fruitore extends Utente {
	private String indirizzoMail;
	private Comprensorio comprensorioAppartenenza;
	
	/**
	 * Costruisce un nuovo Fruitore con il nome, la password, l'indirizzo email e il comprensorio specificati.
	 * @param nome dell'utente.
	 * @param password  dell'utente.
	 * @param indirizzoMail l'indirizzo email dell'utente.
	 * @param comprensorioAppartenenza il comprensorio a cui l'utente appartiene.
	 */
	public Fruitore(String nome, 
					String password, 
					String indirizzoMail, 
					Comprensorio comprensorioAppartenenza) {
		super(nome, password, false);
		
		this.indirizzoMail = indirizzoMail;
		this.comprensorioAppartenenza = comprensorioAppartenenza;
	}

	public String getIndirizzoMail() {
		return indirizzoMail;
	}
	
	public Comprensorio getComprensorioAppartenenza() {
		return comprensorioAppartenenza;
	}

	public void setIndirizzoMail(String indirizzoMail) {
		this.indirizzoMail = indirizzoMail;
	}
	
	public void setComprensorioAppartenenza(Comprensorio comprensorioAppartenenza) {
		this.comprensorioAppartenenza = comprensorioAppartenenza;
	}
}
