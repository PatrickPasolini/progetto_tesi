package it.unibs.controller.accesso;

import it.unibs.model.ModelAccesso;
import it.unibs.view.BaseView;
import it.unibs.view.ViewAccesso;
import it.unibs.view.ViewNewConfiguratore;

public class AccessoConfiguratoreStrategy implements StrategyAccesso {
    
	public AccessoConfiguratoreStrategy() {
		
    }
	
    @Override
    public void eseguiAccesso(ModelAccesso modelAccesso, BaseView view) {
    	if (!(view instanceof ViewNewConfiguratore)) {
            System.out.println("Errore: vista non compatibile");
            return;
        }
        
       ViewAccesso viewNewConf = (ViewAccesso) view;
    	
    	
    	String username=viewNewConf.getUsername().toLowerCase();
    	String password=viewNewConf.getPassword();
    	boolean x=modelAccesso.controllaAccessoConfiguratore(username, password);
    	System.out.println(x);
    	if(x) {
    		viewNewConf.setAccessoEseguito();
    		modelAccesso.setUser(username);
    		modelAccesso.inizializzaConfiguratore();
    	}
    	else
    		viewNewConf.setAccessoFallito();
    	
    	
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

