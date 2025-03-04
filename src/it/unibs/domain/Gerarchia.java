package it.unibs.domain;

import java.util.*;

/**
 * Rappresenta una gerarchia composta da una radice, categorie e foglie.
 * Una gerarchia e' costituita da una radice istanza di {@link NonFoglia},
 * e da una lista di categorie e foglie, istanze di {@link Foglia}.
 * 
 * @see NonFoglia
 * @see Foglia
 */
public class Gerarchia {
	private Categoria radice;
	private ArrayList<Categoria> categorie = new ArrayList<>();
	private ArrayList<Foglia> foglie = new ArrayList<>();
	 
    /**
     *  Costruttore predefinito della gerarchia.
     *  @since 1
     */
	public Gerarchia() {
	}
	
	/** 
	 * Costruttore che inizializza la gerarchia con una radice
	 * @param radice della gerarchia
	 * @since 1
	 */
	public Gerarchia(NonFoglia radice) {
		super();
		this.radice = radice;
		categorie.add(radice);
	}
		
	public Categoria getRadice() {
		return radice;
	}
	
	public void setRadice(Categoria radice) {
		this.radice = radice;
		categorie.add(radice);
	}
	
	public String getNomeRadice() {
		return radice.getNome();
	}
	
	public ArrayList<Foglia> getFoglie() {
		return foglie;
	}

	public void setFoglie(ArrayList<Foglia> foglie) {
		this.foglie = foglie;
	}
	
	public void addCategoria(Categoria c) {
		this.categorie.add(c);
	}
	
	public void addFoglia(Foglia f) {
		this.foglie.add(f);
	}
	
	public ArrayList<Categoria> getCategorie() {
		return categorie;
	}
	
	/**
	 * Verifica se la gerarchia contiene una categoria con il nome inserito
	 * @param name il nome della nuova categoria
	 * @return true se la gerarchia contiene una categoria con il nome specificato, altrimenti false
	 * @since 1
	 */
	public boolean checkNomeCategoria(String name) {
		return categorie.stream().anyMatch(c -> c.getNome().equals(name));
	}

	/**
	 * Verifica se la gerarchia ha la radice
	 * @return true se ha radice
	 * @since 1
	 */
	public boolean hasRadice() {
		return radice != null;
	}

	/**
	 * Verifica se {@link #foglie} e' vuota
	 * @return true se la lista delle foglie della gerarchia è vuota
	 * @since 1
	 */
	public boolean foglieIsEmpty() {
		return foglie.isEmpty();
	}
	
    /**
     * Restituisce la foglia nella posizione nella lista delle foglie dato l'indice.
     * @param index l'indice della foglia nella lista delle foglie
     * @return la foglia corrispondente
     * @since 1
     */
	public Foglia getFoglia(int index) {
		return foglie.get(index);
	}
}