package it.unibs.model;

import java.util.ArrayList;
import java.util.List;

import it.unibs.domain.Comprensorio;
import it.unibs.main.Persistence;

public class ModelComprensori {
	private List<Comprensorio> comprensori = new ArrayList<Comprensorio>();
	
	public ModelComprensori(Persistence persistence) {
		this.comprensori = persistence.getComprensori();
	}

	public List<Comprensorio> getComprensori() {
		return comprensori;
	}
	
	/**
	 * Aggiunta del nuovo comprensorio ai comprensori
	 * @param nuovo comprensorio
	 * @since 1
	 */
	public void addComprensorio(Comprensorio comprensorio) {
		comprensori.add(comprensorio);
	}

	/**
	 * Controllo univocita' del nome del comprensorio
	 * @param nome del nuovo comprensorio
	 * @return true se il nome e' gia' presente
	 * @since 1
	 */
	public boolean checkNomeComprensorio(String name) {
		return comprensori.stream().anyMatch(c -> c.getName().equals(name));
	}
}
