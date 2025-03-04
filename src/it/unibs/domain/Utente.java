package it.unibs.domain;

/**
 * Classe che rappresenta un utente generico del sistema.
 */
public class Utente {
	private String nome;
	private String password;
	private boolean isConfiguratore;
	
	/**
	 * Costruisce un nuovo oggetto Utente con il nome, la password e se e' o no un configuratore
	 * @param nome dell'utente.
	 * @param password dell'utente.
	 * @param isConfiguratore True se l'utente è un configuratore, false altrimenti.
	 */
	public Utente(String nome, String password, boolean isConfiguratore) {
		this.nome = nome;
		this.password = password;
		this.isConfiguratore = isConfiguratore;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setConfiguratore(boolean isConfiguratore) {
		this.isConfiguratore = isConfiguratore;
	}
	
	public boolean getConfiguratore() {
		return isConfiguratore;
	}
}