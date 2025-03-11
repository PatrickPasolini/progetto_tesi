package it.unibs.controller.accesso;

import it.unibs.domain.Configuratore;
import it.unibs.domain.Utente;
import it.unibs.model.ModelAccesso;
import it.unibs.view.ViewAccesso;

public class AccessoConfiguratoreNuovoStrategy  implements StrategyAccesso {
    
	public AccessoConfiguratoreNuovoStrategy() {
		
    }
	
    @Override
    public void eseguiAccesso(ModelAccesso modelAccesso, ViewAccesso viewAccesso) {
    	
    	String username=viewAccesso.getUsername().toLowerCase();
    	String password=viewAccesso.getPassword();
    	boolean x=modelAccesso.credenzialiUnivoche(username);
    	System.out.println(x);
    	if(x) {
    		viewAccesso.setAccessoEseguito();
    		modelAccesso.setUser(username);
    		modelAccesso.inizializzaConfiguratore();
    		Utente user = new Configuratore(username, password);
    		modelAccesso.salvaNewUser(username, user);
    		System.out.println("Nuovo utente creato");
    	}
    	else
    		viewAccesso.setAccessoFallito();
    }
}

