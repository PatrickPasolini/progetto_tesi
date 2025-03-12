package it.unibs.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import it.unibs.controller.ControllerConfiguratore;
import it.unibs.controller.ControllerFruitore;
import it.unibs.domain.Comprensorio;
import it.unibs.domain.Utente;
import it.unibs.main.JSONParser;
import it.unibs.main.Main;
import it.unibs.main.Persistence;
import it.unibs.main.PersistenceLogin;
import it.unibs.view.console.ViewConfiguratore;
import it.unibs.view.console.ViewFruitore;

public class ModelAccesso{
	private static final String NAME_DEFAULT = "a"; // Nome predefinito
	private static final String PSW_DEFAULT = "a"; // Password predefinita

	private Persistence persistence; 
	private PersistenceLogin persistenceLogin;
	
	private List<Comprensorio> comprensori = new ArrayList<Comprensorio>();
	private HashMap<String, Utente> listUtenti = new HashMap<String, Utente>();   //TODO ho tolto il null come parametro
	
	private Utente user;
	
	public ModelAccesso(Persistence persistence, PersistenceLogin persistenceLogin) {
		this.persistence = persistence;
		this.persistenceLogin = persistenceLogin;
		
		this.comprensori = persistence.getComprensori();
		this.listUtenti = persistenceLogin.getListUtenti();
	}
	
	public Utente getUser() {
		return user;
	}

	public void setUser(String nome) {
		this.user = listUtenti.get(nome);
	}
	
	public void setNewUser(Utente user) {
		this.user = user;
	}

	public void addUtente(String nome, Utente user) {
		listUtenti.put(nome, user);
	}
	
	public List<Comprensorio> getComprensori() {
		return comprensori;
	}
	
	public Comprensorio getComprensorio(int index) {
		return comprensori.get(index-1);
	}
	
	public HashMap<String, Utente> getListUtenti() {
		return listUtenti;
	}

	public void setListUtenti(HashMap<String, Utente> listUtenti) {
		this.listUtenti = listUtenti;
	}
	
	public void salva() {
		JSONParser.saveDataToJson(persistenceLogin, Main.CREDENTIALS);
	}
	
	public void salvaNewUser(String nome, Utente user) {
		setNewUser(user);
		addUtente(nome, user);
		salva();
		
	}
	
	
	
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@PERCHE LI AVEVO MESSI QUI@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	/**
     * Inizializza MVC configuratore dell'applicazione e avvia la view del configuratore
     * @param model Il model dell'applicazione
     * @since 2
     */
//	public void inizializzaConfiguratore() {
//		Model model = new Model(persistence);
//		model.setUser(user);
//		
//		ViewConfiguratore viewConfiguratore = new ViewConfiguratore();
//		ControllerConfiguratore controllerConfiguratore = new ControllerConfiguratore(model, viewConfiguratore);
//		controllerConfiguratore.run();
//	}
	
	/**
     * Inizializza MVC fruitore dell'applicazione e avvia la view del fruitore
     * @param model Il model dell'applicazione
     * @since 2
     */
	public void inizializzaFruitore() {
		Model model = new Model(persistence);
		model.setUser(user);
		
//		ViewFruitore viewFruitore = new ViewFruitore();
//		ControllerFruitore controllerFruitore = new ControllerFruitore(model, viewFruitore);
//		controllerFruitore.run(); 
	}
	
	public Model getInizializzaModel() {
		Model model = new Model(persistence);
		model.setUser(user);
		return model;
	}
	
	
	
	
	
	/**
     * Controlla l'accesso utilizzando le credenziali fornite
     * @param name Il nome utente
     * @param psw La password utente
     * @return true se le credenziali sono valide (ignoreCase del nome) , false altrimenti
     * @since 1
     */
	public boolean controllaAccesso(String name, String psw) {
		name = name.toLowerCase(); 
		if(listUtenti.containsKey(name)) {
			if((psw).equals(listUtenti.get(name).getPassword())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo per il controllo dell'accesso del configuratore
	 * @param name nome utente
	 * @param psw password
	 * @return vero se le credenziali sono corrette ed e' un configuratore
	 * @since 2
	 */
	public boolean controllaAccessoConfiguratore(String name, String psw) {
		if(!controllaAccesso(name, psw)) {
			return false;
		}
		else {
			return listUtenti.get(name).getConfiguratore();
		}
	}

	/**
	 * Metodo per il controllo dell'accesso del fruitore
	 * @param name nome utente
	 * @param psw password
	 * @return vero se le credenziali sono corrette ed e' un fruitore
	 * @since 2
	 */
	public boolean controllaAccessoFruitore(String name, String psw) {
		if(!controllaAccesso(name, psw)) {
			return false;
		}
		else {
			return !listUtenti.get(name).getConfiguratore();
		}
	}

	/**
     * Controlla se le credenziali fornite corrispondono alle credenziali predefinite, per l'accesso del Configuratore
     * @param nome Il nome utente
     * @param password La password utente
     * @return true se le credenziali corrispondono alle credenziali predefinite, false altrimenti
     * @since 1
     */
	public boolean controllaDefault(String nome, String password) {
		return nome.equals(NAME_DEFAULT) && password.equals(PSW_DEFAULT) ? true : false;
	}

	/**
     * 
     * TODO: check controllo sul nome diverso anche da 'a'
     * 
     * Verifica che il nome utente fornito non sia gia' presente nella lista delle credenziali.
     * @param nomeNew Il nome utente da verificare
     * @return true se il nome utente � unico, false altrimenti
     * @since 1
     */
	public boolean credenzialiUnivoche(String nomeNew) {
		return (!listUtenti.containsKey(nomeNew.toLowerCase()) && !nomeNew.equals(NAME_DEFAULT));
	}

	public String[] getNomiComprensori() {
		String[] nomi = new String[comprensori.size()];
		int i = 0;
		for (Comprensorio comp : comprensori) {
		    nomi[i++] = comp.getName();
		}
		return nomi;
	}
}