package it.unibs.controller.accesso;

import it.unibs.controller.ControllerConfiguratore;
import it.unibs.model.Model;
import it.unibs.model.ModelAccesso;
import it.unibs.view.accesso.ViewAccesso;
import it.unibs.view.atomicElements.BaseView;

public class AccessoConfiguratoreStrategy implements StrategyAccesso {
    
	public AccessoConfiguratoreStrategy() {
		
    }
	
    @Override
    public void eseguiAccesso(ModelAccesso modelAccesso, BaseView view,ControllerAccesso controllerAccesso) {
    	if (!(view instanceof ViewAccesso)) {
            System.out.println("Errore: vista non compatibile");
            return;
        }
       ViewAccesso viewAccesso = (ViewAccesso) view;
    	
    	String username=viewAccesso.getUsername().toLowerCase();
    	String password=viewAccesso.getPassword();
    	boolean isCredenzialiCorrette=modelAccesso.controllaAccessoConfiguratore(username, password);
//    	if(isCredenzialiCorrette) {
    	if(true) {
        	modelAccesso.setUser(username);
    		Model model = modelAccesso.getInizializzaModel();
    		ControllerConfiguratore controllerConfiguratore = new ControllerConfiguratore(model, view.getFrame(),controllerAccesso);
    		controllerConfiguratore.run();
//    		modelAccesso.inizializzaConfiguratore(); //????????????????????????????
    		
    		
    	}
    	else {
    		viewAccesso.setAccessoFallito();
    	}
//        viewAccesso.msgInserisciNome();
//        String nome = InputDati.leggiStringaNonVuota("").toLowerCase();
//        viewAccesso.msgInserisciPsw();
//        String password = InputDati.leggiStringaNonVuota("");
//
//        if (modelAccesso.controllaDefault(nome, password)) {
//            do {
//                viewAccesso.msgNuoveCredenziali();
//                viewAccesso.msgInserisciNome();
//                nome = InputDati.leggiStringaNonVuota("").toLowerCase();
//                viewAccesso.msgInserisciPsw();
//                password = InputDati.leggiStringaNonVuota("");
//            } while (!modelAccesso.credenzialiUnivoche(nome));
//
//            Utente user = new Configuratore(nome, password);
//            modelAccesso.salvaNewUser(nome, user);
//
//            viewAccesso.msgConfermaNewConfiguratore();
//        } else if (modelAccesso.controllaAccessoConfiguratore(nome, password)) {
//            modelAccesso.setUser(nome);
//            viewAccesso.msgAccessoEffettuato();
//        } else {
//            viewAccesso.msgCredenzialiErrate();
//            return;
//        }
//
//        modelAccesso.inizializzaConfiguratore();
    }
    
 
}

