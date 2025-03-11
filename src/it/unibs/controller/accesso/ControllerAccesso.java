package it.unibs.controller.accesso;

import java.awt.event.ActionEvent;
import java.util.*;
import javax.swing.JFrame;
import it.unibs.controller.Controller;
import it.unibs.domain.Comprensorio;
import it.unibs.model.ModelAccesso;
import it.unibs.view.*;

public class ControllerAccesso implements Controller {
	private ModelAccesso modelAccesso;
    private JFrame frame;
    private Map<Integer, StrategyAccesso> strategieAccesso = new HashMap<>();
    private BaseView currentView; // Sostituisce viewAccesso con una variabile più generale
    private int scelta;
    
    public ControllerAccesso(ModelAccesso modelAccesso) {
        this.modelAccesso = modelAccesso;
        inizializzaStrategieAccesso();
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    private void inizializzaStrategieAccesso() {
        strategieAccesso.put(1, new AccessoConfiguratoreStrategy());
        strategieAccesso.put(2, new AccessoConfiguratoreNuovoStrategy());
        strategieAccesso.put(3, new AccessoFruitoreStrategy());
        strategieAccesso.put(4, new AccessoFruitoreNuovoStrategy());
    }

    public void run() {
        ViewStart startView = new ViewStart(frame);
        frame.getContentPane().add(startView);
        startView.setLayout(null);
        startView.setButtonListeners(this::accessoConfiguratore,this::accessoFruitore);
    }
    
    private void controlloAccesso(ActionEvent e) {
        StrategyAccesso strategyAccesso = strategieAccesso.get(scelta);
        if (strategyAccesso != null) {
            strategyAccesso.eseguiAccesso(modelAccesso, currentView);
        }
    }
    
    private void accessoConfiguratore(ActionEvent e) {
        currentView = new ViewAccesso(frame, "Configuratore");
        frame.getContentPane().add(currentView);
        currentView.setLayout(null);
        scelta = 1;
        ((ViewAccesso) currentView).setButtonListeners(this::controlloAccesso, this::accessoConfiguratoreNuovo);
    }
    private void accessoConfiguratoreNuovo(ActionEvent e) {
    	currentView = new ViewNewConfiguratore(frame);
        frame.getContentPane().add(currentView);
        currentView.setLayout(null);
        scelta = 2;
        ((ViewNewConfiguratore) currentView).setButtonListeners(this::controlloAccesso);
    }

    private void accessoFruitore(ActionEvent e) {
        currentView = new ViewAccesso(frame, "Fruitore");
        frame.getContentPane().add(currentView);
        currentView.setLayout(null);
        scelta = 3;
        ((ViewAccesso) currentView).setButtonListeners(this::controlloAccesso, this::accessoFruitoreNuovo);
    }
    
    private void accessoFruitoreNuovo(ActionEvent e) {
    	ArrayList<String> nomiComp = new ArrayList<>();
		for (Comprensorio c : modelAccesso.getComprensori()) {
			nomiComp.add(c.stampaComprensorio());
		}
		String[] nomiComprensori = nomiComp.toArray(new String[0]);

//TODO CHIEDERE AL MAIN DIRETTAMENTE LA LISTA DI NOMI E TOGLIERE QUESTA LOGICA DALLA VIEW
//		modelAccesso;
		
		currentView = new ViewNewFruitore(frame, nomiComprensori);

        frame.getContentPane().add(currentView);
        currentView.setLayout(null);
    	scelta = 4;
    	((ViewNewFruitore) currentView).setButtonListeners(this::controlloAccesso);
    }
}
