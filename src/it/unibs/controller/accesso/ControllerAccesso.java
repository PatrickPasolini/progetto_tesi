package it.unibs.controller.accesso;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import it.unibs.controller.Controller;
import it.unibs.model.ModelAccesso;
import it.unibs.mylib.MyMenu;
import it.unibs.view.*;
import it.unibs.view.ViewAccesso;

public class ControllerAccesso implements Controller {
	private ModelAccesso modelAccesso;
//	private ViewAccesso viewAccesso;
	private JFrame frame;
    private Map<Integer, StrategyAccesso> strategieAccesso = new HashMap<>();
	
	public ControllerAccesso(ModelAccesso modelAccesso) {
		super();
		this.modelAccesso = modelAccesso;
		inizializzaStrategieAccesso();
	}
	
//	public void setView(ViewAccesso viewAccesso) {
//		this.viewAccesso = viewAccesso;
//	}
	public void setFrame(JFrame frame) {
		this.frame=frame;
	}
	
	private void inizializzaStrategieAccesso() {
		strategieAccesso.put(1, new AccessoConfiguratoreStrategy());
		strategieAccesso.put(2, new AccessoFruitoreNuovoStrategy());
		strategieAccesso.put(3, new AccessoFruitoreEsistenteStrategy());
    }

	public void run() {
//		MyMenu menuLogin = viewAccesso.getMenuRun();
//		viewAccesso.stampaMenuRun();
//		
//		int scelta = menuLogin.scegli();
//        StrategyAccesso strategyAccesso = strategieAccesso.get(scelta);
//		
//		if(strategyAccesso != null) {
//			strategyAccesso.eseguiAccesso(modelAccesso, viewAccesso);
//		}
//		
//		viewAccesso.stampaTerminaPorgramma();
		
//		ViewAccesso viewAccesso = new ViewAccesso(frame);
//		frame.getContentPane().add(viewAccesso);
//		viewAccesso.setLayout(null);
		
		StartView startView = new StartView(frame);
		frame.getContentPane().add(startView);
		startView.setLayout(null);
		
	}
	
}


