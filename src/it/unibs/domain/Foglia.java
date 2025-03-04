package it.unibs.domain;

import java.util.ArrayList;
import java.util.Objects;

import it.unibs.exception.FogliaSenzaFigliException;
import it.unibs.main.JSONParser;

/**
 * PATTERN COMPOSITE: Leaf
 * Rappresenta una foglia all'interno di una gerarchia.
 * Eredita da {@link Categoria} e contiene un identificatore univoco.
 * L'identificatore viene calcolato utilizzando l'hash code dei campi "nomeRadice" e "nome".
 * 
 * @see Categoria
 */
public class Foglia extends Categoria {
	private int id;
	
	/**
	 * Costruttore che inizializza una foglia con il nome, la descrizione e il nome della radice specificati.
	 * Calcolo di id con nomeRadice e nome
	 * @param nome della foglia
	 * @param descrizione della foglia
	 * @param nomeRadice il nome della radice della gerarchia a cui appartiene la foglia
	 * @since 1
	 */
	public Foglia(String nome, String descrizione, String nomeRadice) {
		super(nome, descrizione);
		this.id = Objects.hash(nomeRadice, nome);
	} 

	public ArrayList<Categoria> getChilds() {
		return new ArrayList<Categoria>(); // array null
	}
	
	/**
	 * Restituisce false perche' foglia non puo' avere figli
	 * @return false 
	 * @since 1
	 */
	public boolean hasChilds() {
		return false;
	}
	
	/**
	 * Eccezione perche' foglia non puo'avere figli
	 * @param c la categoria da aggiungere come figlio
	 * @throws FogliaSenzaFigliException
	 */
	public void addChilds(Categoria c) throws Exception {
		throw new FogliaSenzaFigliException();
	}
	
	/**
     * Verifica se questa foglia e' uguale a un altro oggetto specificato.
     * 
     * @param o l'oggetto da confrontare
     * @return true se l'oggetto specificato e' uguale a questa foglia, altrimenti false
     * @see JSONParser
     * @since 1
     */
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Foglia foglia = (Foglia) o;
        return id == foglia.id;
    }

	/**
	 * Restituisce il codice hash per la foglia
	 * @return il codice hash per questa foglia
	 * @see JSONParser
	 * @since 1
	 */
	@Override
	public int hashCode() {
	    return id;
	}
}
