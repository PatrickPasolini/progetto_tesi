package it.unibs.controller.accesso;

import it.unibs.controller.ControllerConfiguratore;
import it.unibs.controller.ControllerFruitore;
import it.unibs.model.Model;
import it.unibs.model.ModelAccesso;
import it.unibs.mylib.InputDati;
import it.unibs.view.accesso.ViewAccesso;
import it.unibs.view.atomicElements.BaseView;

public class AccessoFruitoreStrategy implements StrategyAccesso {

	public AccessoFruitoreStrategy() {
		
    }

	@Override
	public void eseguiAccesso(ModelAccesso modelAccesso, BaseView view) {
		
		if (!(view instanceof ViewAccesso)) {
            System.out.println("Errore: vista non compatibile");
            return;
        }
       ViewAccesso viewAccesso = (ViewAccesso) view;
    	
    	String username=viewAccesso.getUsername().toLowerCase();
    	String password=viewAccesso.getPassword();
    	boolean isCredenzialiCorrette=modelAccesso.controllaAccessoFruitore(username, password);
//    	if(isCredenzialiCorrette) {
    	if(true) {
    		viewAccesso.setAccessoEseguito();
    		modelAccesso.setUser(username);

    		Model model = modelAccesso.getInizializzaModel();
    		
    		ControllerFruitore controllerFruitore = new ControllerFruitore(model, view.getFrame());
    		controllerFruitore.run();
    	} 
    	else {
    		viewAccesso.setAccessoFallito();
    	}
//		viewAccesso.msgInserisciNome();
//		String nome = InputDati.leggiStringaNonVuota("").toLowerCase();
//		viewAccesso.msgInserisciPsw();
//		String password = InputDati.leggiStringaNonVuota("").toLowerCase();
//		
//		if(modelAccesso.controllaAccessoFruitore(nome,password)) {
//			modelAccesso.setUser(nome);
//			
//			viewAccesso.msgAccessoEffettuato();
//		}else {
//			viewAccesso.msgCredenzialiErrate();
//			return;
//		}
//		
//		modelAccesso.inizializzaFruitore();	
	}
	

}
