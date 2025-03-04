package it.unibs.main;

import java.util.HashMap;

import it.unibs.domain.Utente;

/**
 * Questa classe rappresenta la persistenza degli utenti dell'applicazione.
 * Contiene una mappa degli utenti
 * Fornisce metodi per accedere e modificare queste strutture dati.
 */
public class PersistenceLogin {
	private HashMap<String, Utente> listUtenti = new HashMap<>();
	
	public PersistenceLogin() {
		super();
	}

	public HashMap<String, Utente> getListUtenti() {
		return listUtenti;
	}

	public void setListUtenti(HashMap<String, Utente> listUtenti) {
		this.listUtenti = listUtenti;
	}
	
	public void isNull() {
		if(listUtenti== null)
			listUtenti = new HashMap<String, Utente>();
	}
	
	public void addUtente(Utente utente) {
        listUtenti.put(utente.getNome(), utente);
    }

    public void removeUtente(String nome) {
        listUtenti.remove(nome);
    }

    public Utente getUtente(String nome) {
        return listUtenti.get(nome);
    }
}
