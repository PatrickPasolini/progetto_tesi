package it.unibs.controller.accesso;

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
    	if(isCredenzialiCorrette) {
    		viewAccesso.setAccessoEseguito();
    		modelAccesso.setUser(username);
    		modelAccesso.inizializzaFruitore();
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
