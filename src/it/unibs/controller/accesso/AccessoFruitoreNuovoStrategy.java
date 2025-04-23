package it.unibs.controller.accesso;

import it.unibs.controller.ControllerFruitore;
import it.unibs.domain.Fruitore;
import it.unibs.domain.Utente;
import it.unibs.model.Model;
import it.unibs.model.ModelAccesso;
import it.unibs.view.accesso.ViewNewFruitore;
import it.unibs.view.atomicElements.BaseView;

public class AccessoFruitoreNuovoStrategy implements StrategyAccesso {
	
	public AccessoFruitoreNuovoStrategy() {
		
    }

	@Override
	public void eseguiAccesso(ModelAccesso modelAccesso, BaseView view, ControllerAccesso controllerAccesso) {
		
		if (!(view instanceof ViewNewFruitore)) {
            System.out.println("Errore: vista non compatibile");
            return;
        }
        
		ViewNewFruitore viewNewFru = (ViewNewFruitore) view;
       	String username=viewNewFru.getUsername().toLowerCase();
   		String password=viewNewFru.getPassword();
   		String email=viewNewFru.getEmail();
   		int comprensorioIndex=viewNewFru.getNomeComprensorioIndex()+1;
   		
   		boolean isCredenzialiUnivoce=modelAccesso.credenzialiUnivoche(username);
    	boolean formCondition= !username.equals("")&& !password.equals("")
    							&& !username.equals("username")&& !password.equals("Password");
   		if (comprensorioIndex>0&&isCredenzialiUnivoce && formCondition) {
   			//creazione utente
   			Utente user = new Fruitore(username, password, email, modelAccesso.getComprensorio(comprensorioIndex));
   			viewNewFru.setCreazioneEseguita();
   			modelAccesso.salvaNewUser(username, user);
   			
   			//accesso
   			Model model = modelAccesso.getInizializzaModel();
    		ControllerFruitore controllerFruitore = new ControllerFruitore(model, viewNewFru.getFrame(),controllerAccesso);
    		controllerFruitore.run();	
		}
   		else {
   			viewNewFru.setCreazioneFallita();
   		}
   		
       
   		
   		
   		
//		viewAccesso.msgInserisciNome();
//		String nome = InputDati.leggiStringaNonVuota("").toLowerCase();
//		
//		while(!modelAccesso.credenzialiUnivoche(nome)) {
//			viewAccesso.msgNomeUnivoco();
//			nome = InputDati.leggiStringaNonVuota("").toLowerCase();
//		};
//		
//		viewAccesso.msgInserisciPsw();
//		String password = InputDati.leggiStringaNonVuota("");
//		
//		viewAccesso.msgInserisciMail(); 
//		String indirizzoMail = InputDati.leggiStringaNonVuota("");
//		
//		GestoreComprensoriAccesso gestoreCopmprensoriAccesso = new GestoreComprensoriAccesso(modelAccesso,viewAccesso);
//		Comprensorio comprensorio = null;
//		
//		comprensorio = gestoreCopmprensoriAccesso.sceltaComprensorio();
//		
//		Utente user = new Fruitore(nome, password, indirizzoMail, comprensorio);
//		modelAccesso.salvaNewUser(nome, user);
//		
//		viewAccesso.msgAccessoNuovoFruitore();
//		
//		modelAccesso.inizializzaFruitore();

	}

}
