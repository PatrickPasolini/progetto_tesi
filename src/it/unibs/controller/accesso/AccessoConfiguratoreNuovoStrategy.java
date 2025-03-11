package it.unibs.controller.accesso;

import it.unibs.domain.Configuratore;
import it.unibs.domain.Utente;
import it.unibs.model.ModelAccesso;
import it.unibs.view.*;

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
    	boolean x=modelAccesso.credenzialiUnivoche(username);
    	System.out.println(x);
    	if(x) {
    		viewNewConf.setCreazioneEseguita();
    		modelAccesso.setUser(username);
    		modelAccesso.inizializzaConfiguratore();
    		Utente user = new Configuratore(username, password);
    		modelAccesso.salvaNewUser(username, user);
    		System.out.println("Nuovo utente creato");
    	}
    	else
    		viewNewConf.setCreazioneFallita();
    }
}

