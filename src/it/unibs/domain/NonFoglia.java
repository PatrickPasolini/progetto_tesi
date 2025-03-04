package it.unibs.domain;

import java.util.*;

/**
 * PATTERN COMPOSITE: Composite
 * Rappresenta una categoria non foglia all'interno di una gerarchia.
 * Oltre alle caratteristiche di base definite nella classe madre {@link Categoria}, 
 * contiene informazioni specifiche come il campo associato e una lista di domini associati al campo.
 * 
 * @see Categoria
 */
public class NonFoglia extends Categoria {
	private String campo;
	private List<String> domini = new ArrayList<>();
	private ArrayList<Categoria> childs = new ArrayList<>();
	
	/**
	 * Costruttore della classe NonFoglia
	 * @param nome della categoria
	 * @param campo associato alla categoria non foglia
	 * @param descrizione della categoria
	 * @since 1
	 */
	public NonFoglia(String nome, String campo, String descrizione) {
		super(nome, descrizione);
		this.campo = campo;
	}
	
	public ArrayList<Categoria> getChilds() {
		return childs;
	}
	
	public String getCampo() {
		return campo;
	}
	
	public void setCampo(String campo) {
		this.campo = campo;
	}
	
	public List<String> getDomini() {
		return domini;
	}
	
	public void setDomini(List<String> domini) {
		this.domini = domini;
	}
	
	/**
	 * Aggiunge un dominio alla lista dei domini
	 * @param dominio da aggiungere
	 * @since 1
	 */
	public void addDominio(String dominio) {
		this.domini.add(dominio);
	}
	
	/**
	 * Verifica se la lista dei domini associati alla categoria non foglia è vuota
	 * @return true se la lista è vuota, altrimenti false
	 * @since 1
	 */
	public boolean dominiIsEmpty() {
		return domini.isEmpty();
	}

	/**
	 * Verifica che la categoria ha figli
	 * @return true se la categoria ha figli
	 * @since 1
	 */
	public boolean hasChilds() {
		return !childs.isEmpty();
	}

	/**
     * Aggiunge una nuovo figlio a #{@link Categoria#childs}.
     * @param c nuovo Categoria da aggiungere
     * @since 1
     */
	public void addChilds(Categoria c) {
		this.childs.add(c);
	}
	
}