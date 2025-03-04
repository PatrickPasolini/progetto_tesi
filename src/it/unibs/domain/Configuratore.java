package it.unibs.domain;

/**
 * Classe che rappresente il configuratore
 * Estende la classe {@link Utente}
 */
public class Configuratore extends Utente {
	public Configuratore(String nome, String password) {
		super(nome, password, true);
	}
}
