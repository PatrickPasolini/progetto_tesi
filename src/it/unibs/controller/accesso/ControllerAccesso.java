package it.unibs.controller.accesso;

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
        strategieAccesso.put(3, new AccessoFruitoreStrategy());
    }

    public void run() {
        StartView startView = new StartView(frame);
        frame.getContentPane().add(startView);
        startView.setLayout(null);
        startView.setButtonListeners(e -> mostraSchermataAccesso(1), 
                                     e -> mostraSchermataAccesso(2));
    }

    private void mostraSchermataAccesso(int tipoAccesso) {
        String nomeAccesso = tipoAccesso == 1 ? "Configuratore" : "Fruitore";
        ViewAccesso viewAccesso = new ViewAccesso(frame, nomeAccesso);
        frame.getContentPane().add(viewAccesso);
        viewAccesso.setLayout(null);

        StrategyAccesso strategy = strategieAccesso.get(tipoAccesso);
        if (strategy != null) {
            viewAccesso.setButtonListeners(e -> strategy.eseguiAccesso(modelAccesso, viewAccesso));
        }
    }
}
