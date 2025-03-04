package it.unibs.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;

import it.unibs.controller.accesso.ControllerAccesso;
import it.unibs.model.ModelAccesso;
import it.unibs.view.ViewAccesso;


public class MainApp {

	public static final String PERSISTENCE = "./Data/persistenceVB.json"; //percorso file.json contenente i dati dell'applicazione
	public static final String CREDENTIALS = "./Data/credentialsVB.json";//percorso file.json contenente le credenziali di accesso 
	public static Persistence persistence;
	static PersistenceLogin persistenceLogin;
	
	private JFrame frame;
	public static void main(String[] args) {
		System.out.println("start");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try { 
					MainApp window = new MainApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MainApp() {
		loadPersistence();
		loadPersistenceLogin();
		inizializzaAccesso();
	}

	private void startFormAccesso() {
//		frame = new JFrame();
//		frame.setBounds(0, 0, 1200, 700);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setResizable(true);
//		frame.getContentPane().setLayout(new BorderLayout(0, 0));
//		frame.setMinimumSize(new Dimension(500, 650));
//		
//		ViewAccesso viewAccesso = new ViewAccesso(frame);
//		frame.getContentPane().add(viewAccesso);
//		viewAccesso.setLayout(null);
		
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
	
	private void inizializzaAccesso() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1200, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setMinimumSize(new Dimension(500, 650));
		
		ModelAccesso modelAccesso = new ModelAccesso(persistence, persistenceLogin);
		ControllerAccesso controllerAccesso = new ControllerAccesso(modelAccesso);
		controllerAccesso.setFrame(frame);
		controllerAccesso.run();
	
	}
}