package it.unibs.controller.accesso;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import it.unibs.controller.Controller;
import it.unibs.model.ModelAccesso;
import it.unibs.view.StartView;
import it.unibs.view.ViewAccesso;

public class ControllerAccesso implements Controller {
    private ModelAccesso modelAccesso;
    private JFrame frame;
    private Map<Integer, StrategyAccesso> strategieAccesso = new HashMap<>();

    public ControllerAccesso(ModelAccesso modelAccesso) {
        this.modelAccesso = modelAccesso;
        inizializzaStrategieAccesso();
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    private void inizializzaStrategieAccesso() {
        strategieAccesso.put(1, new AccessoConfiguratoreStrategy());
        strategieAccesso.put(2, new AccessoFruitoreNuovoStrategy());
        strategieAccesso.put(3, new AccessoFruitoreEsistenteStrategy());
    }

    public void run() {
	   StartView startView = new StartView(frame);
	   frame.getContentPane().add(startView);
	   startView.setLayout(null);
	   startView.setButtonListeners(this::accessoConfiguratore, this::accessoFruitore);
	   
	   
	   //gestione delle strategy
    }
    
    ViewAccesso viewAccesso;
//TODO GESTIRE MEGLIO LE TIPOLOGIE DI ACCESSO, C'É ANCHE RIDONDANZA NEI DUE METODI 
    private void accessoConfiguratore(ActionEvent e) {
        viewAccesso = new ViewAccesso(frame,"Configuratore");
		frame.getContentPane().add(viewAccesso);
		viewAccesso.setLayout(null);
//		viewAccesso.setButtonListeners();
		viewAccesso.setButtonListeners(this::controlloAccesso);

      
    }
    private void controlloAccesso(ActionEvent e) {
    	String username=viewAccesso.getUsername();
    	String password=viewAccesso.getPassword();
    	boolean x=modelAccesso.controllaAccesso(username, password);
    	System.out.println(x);
    	if(x)
    		viewAccesso.setAccessoEseguito();
    	else
    		viewAccesso.setAccessoFallito();
    }

    private void accessoFruitore(ActionEvent e) {
        viewAccesso = new ViewAccesso(frame,"Fruitore");
		frame.getContentPane().add(viewAccesso);
		viewAccesso.setLayout(null);
		viewAccesso.setButtonListeners(this::controlloAccesso);
    }
}
