package it.unibs.controller.accesso;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import it.unibs.controller.Controller;
import it.unibs.model.ModelAccesso;
import it.unibs.view.ViewStart;
import it.unibs.view.ViewAccesso;
import it.unibs.view.ViewNewFruitore;

public class ControllerAccesso implements Controller {
    private ModelAccesso modelAccesso;
    private JFrame frame;
    private Map<Integer, StrategyAccesso> strategieAccesso = new HashMap<>();
    private ViewAccesso viewAccesso;
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
            strategyAccesso.eseguiAccesso(modelAccesso, viewAccesso);
        }
    }
    
    private void accessoConfiguratore(ActionEvent e) {
        viewAccesso = new ViewAccesso(frame, "Configuratore");
        frame.getContentPane().add(viewAccesso);
        viewAccesso.setLayout(null);
        scelta = 1;
        viewAccesso.setButtonListeners(this::controlloAccesso, this::registrazioneConfiguratore);
    }
    private void registrazioneConfiguratore(ActionEvent e) {
        scelta = 2;
        controlloAccesso(e);
    }

    private void accessoFruitore(ActionEvent e) {
        viewAccesso = new ViewAccesso(frame, "Fruitore");
        frame.getContentPane().add(viewAccesso);
        viewAccesso.setLayout(null);
        scelta = 3;
        viewAccesso.setButtonListeners(this::controlloAccesso, this::registrazioneFruitore);
    }
    private void registrazioneFruitore(ActionEvent e) {
        ViewNewFruitore viewNewFruitore = new ViewNewFruitore(frame);
        frame.getContentPane().add(viewNewFruitore);
        viewNewFruitore.setLayout(null);
    	scelta = 4;
        controlloAccesso(e);
    }
}
