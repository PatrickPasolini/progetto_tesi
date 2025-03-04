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
	
	   startView.setButtonListeners(this::accediConfiguratore, this::accediFruitore);
	   
	   
	   //gestione delle strategy
    }

    private void accediConfiguratore(ActionEvent e) {
        ViewAccesso viewAccesso = new ViewAccesso(frame,"Configuratore");
		frame.getContentPane().add(viewAccesso);
		viewAccesso.setLayout(null);
      
    }

    private void accediFruitore(ActionEvent e) {
        ViewAccesso viewAccesso = new ViewAccesso(frame,"Fruitore");
		frame.getContentPane().add(viewAccesso);
		viewAccesso.setLayout(null);
    }
}
