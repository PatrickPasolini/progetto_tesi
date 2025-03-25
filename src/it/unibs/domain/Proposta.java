package it.unibs.domain;

/**
 * Rappresenta una proposta di scambio di 2 prestazioni, richiesto da un Utente
 * Ha le Foglie richiesta e offerta, le ore richieste e offerte, e l'utente che richiede lo scambio.
 */
public class Proposta {
	private Foglia richiesta; 
	private Foglia offerta;
	private int oreRichiesta;
	private int oreOfferta; //calcolate sulla base degli altri attirbuti 
	private Utente fruitoreRichiedente; //fruitore che effettua la proposta di scambio
	
	/**
     * Costruisce un nuovo oggetto Proposta con i parametri specificati
     * @param richiesta la prestazione Foglia richiesta nello scambio
     * @param offerta la prestazione Foglia offerta nello scambio
     * @param oreRichiesta il numero di ore richieste
     * @param oreOfferta il numero di ore offerte
     * @param fruitoreRichiedente l'utente che avvia lo scambio
     */
	public Proposta(Foglia richiesta, 
					Foglia offerta, 
					int oreRichiesta,
					int oreOfferta,
					Utente fruitoreRichiedente) {
		this.richiesta = richiesta;
		this.offerta = offerta;
		this.oreRichiesta = oreRichiesta;
		this.oreOfferta = oreOfferta;
		this.fruitoreRichiedente = fruitoreRichiedente;
	}
	
	public Foglia getRichiesta() {
		return richiesta;
	}
	
	public Foglia getOfferta() {
		return offerta;
	}
	
	public Utente getFruitoreRichiedente() {
		return fruitoreRichiedente;
	}
	
	public int getOreRichiesta() {
		return oreRichiesta;
	}
	
	public int getOreOfferta() {
		return oreOfferta;
	}
	
	public Comprensorio getComprensorio() {
		return ((Fruitore)this.fruitoreRichiedente).getComprensorioAppartenenza();
	}
	
	/**
	 * toString usato solo per il salvataggio della Proposta su un file di log
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n\tProposta di :").append(this.fruitoreRichiedente.getNome());
		sb.append("\n\trichiesta:  [").append(this.richiesta.getNome());
		sb.append(", ").append(this.oreRichiesta).append(" ore]\n");
		sb.append("<br>").append("\tofferta:  [").append(this.offerta.getNome());
		sb.append(", ").append(this.oreRichiesta).append(" ore]");
		return sb.toString(); 
	}
} 
