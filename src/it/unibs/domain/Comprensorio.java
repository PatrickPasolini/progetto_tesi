package it.unibs.domain;

import java.util.*;

/**
 * Rappresenta un comprensorio geografico che contiene una lista di comuni.
 * Un comprensorio ha un nome e una lista di comuni che lo compongono.
 */
public class Comprensorio {
	private String name;
	private List<String> comuni = new ArrayList<String>();
	
	/**
	 * Costruttore che inizializza un comprensorio con il nome e la lista di comuni specificati.
	 * @param name del comprensorio
	 * @param comuni  di comuni che compongono il comprensorio
	 * @since 1
	 */
	public Comprensorio(String name, List<String> comuni) {
		this.name = name;
		this.comuni = comuni;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<String> getComuni() {
		return comuni;
	}
	
	public void setComuni(List<String> comuni) {
		this.comuni = comuni;
	}
	
	/**
	 * Metodo per ritornare la stringa da stampare per la lista di comprensori
	 * @return
	 * @since 2
	 */
	public String stampaComprensorio() {
		return new String(name + "\t" + stampaComuni());
	}
	
	/**
	 * Metodo per ritornare la stringa con la lista dei comuni del comprensorio
	 * @return
	 * @since 2
	 */
	public StringBuilder stampaComuni() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		
		int size = comuni.size();
		
		for (int i = 0; i < size; i++) {
			sb.append(comuni.get(i));
			
			if (i < size - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		
		return sb;
	}
	
}
