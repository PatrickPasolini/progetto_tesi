package it.unibs.main;

import it.unibs.controller.accesso.ControllerAccesso;
import it.unibs.model.ModelAccesso;
import it.unibs.view.*;
import it.unibs.view.console.ViewAccesso;

/**
 * Questa classe rappresenta il punto di ingresso dell'applicazione.
 * Gestisce l'accesso e l'inizializzazione del configuratore.
 * @since 1
 */
public class Main {
	public static final String PERSISTENCE = "./Data/persistenceVB.json"; //percorso file.json contenente i dati dell'applicazione
	public static final String CREDENTIALS = "./Data/credentialsVB.json";//percorso file.json contenente le credenziali di accesso 
	
	public static Persistence persistence;
public static PersistenceLogin persistenceLogin;
	
	public static void main(String[] args) {
		loadPersistence();
		loadPersistenceLogin();
		
		inizializzaAccesso();
	}
	
	private static void loadPersistence() {
		persistence = (Persistence) JSONParser.loadPersistence(PERSISTENCE, Persistence.class);
		
		if (persistence == null)
			persistence = new Persistence();
		
		persistence.isNull();
	}
	
	private static void loadPersistenceLogin() {
		persistenceLogin = (PersistenceLogin) JSONParser.loadPersistence(CREDENTIALS, PersistenceLogin.class);
		
		if (persistenceLogin == null)
			persistenceLogin = new PersistenceLogin();
		
		persistenceLogin.isNull();
	}
	
	private static void inizializzaAccesso() {
//		ModelAccesso modelAccesso = new ModelAccesso(persistence, persistenceLogin);
//		ControllerAccesso controllerAccesso = new ControllerAccesso(modelAccesso);
//		ViewAccesso viewAccesso = new ViewAccesso();
//		controllerAccesso.setView(viewAccesso);
//		
//		controllerAccesso.run();
	}

}
