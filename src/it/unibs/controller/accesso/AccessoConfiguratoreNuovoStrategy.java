package it.unibs.controller.accesso;

import it.unibs.controller.ControllerConfiguratore;
import it.unibs.domain.Configuratore;
import it.unibs.domain.Utente;
import it.unibs.model.Model;
import it.unibs.model.ModelAccesso;
import it.unibs.view.accesso.*;
import it.unibs.view.atomicElements.BaseView;

public class AccessoConfiguratoreNuovoStrategy  implements StrategyAccesso {
    
	public AccessoConfiguratoreNuovoStrategy() {
		
    }
	
    @Override
    public void eseguiAccesso(ModelAccesso modelAccesso, BaseView view) {
    	 if (!(view instanceof ViewNewConfiguratore)) {
             System.out.println("Errore: vista non compatibile");
             return;
         } 
        ViewNewConfiguratore viewNewConf = (ViewNewConfiguratore) view;
        
    	String username=viewNewConf.getUsername().toLowerCase();
    	String password=viewNewConf.getPassword();
    	boolean isCredenzialiUnivoce=modelAccesso.credenzialiUnivoche(username);
    	boolean formCondition= !username.equals("")&& !password.equals("")
    							&& !username.equals("username")&& !password.equals("Password");
    	if(isCredenzialiUnivoce && formCondition) {
    		//creazione utente
    		viewNewConf.setCreazioneEseguita();
    		Utente user = new Configuratore(username, password);
    		modelAccesso.salvaNewUser(username, user);
    		
    		//accesso
    		Model model = modelAccesso.getInizializzaModel();
    		ControllerConfiguratore controllerConfiguratore = new ControllerConfiguratore(model, viewNewConf.getFrame());
    		controllerConfiguratore.run();
    	}
    	else {
    		viewNewConf.setCreazioneFallita();
    	}
    }
}

