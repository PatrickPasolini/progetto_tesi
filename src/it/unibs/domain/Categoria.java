package it.unibs.domain;

import java.util.ArrayList;

/**
 * PATTERN COMPOSITE: Component(abstract)
 * Rappresenta una categoria all'interno di una gerarchia.
 * Una categoria ha un nome, una descrizione e può contenere una lista di sottocategorie (childs).
 */
public abstract class Categoria {
	
	private String nome;
	private String descrizione;
	
	/**
	 * Costruttore che inizializza una categoria con il nome e la descrizione specificati
	 * @param nome della categoria
	 * @param descrizione della categoria
	 * @since 1
	 */
	public Categoria(String nome, String descrizione) {
		this.nome = nome;
		this.descrizione = descrizione;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public String getNome() {
		return nome;
	}
	public abstract ArrayList<Categoria> getChilds();
	public abstract void addChilds(Categoria c) throws Exception;
	public abstract boolean hasChilds();
}
