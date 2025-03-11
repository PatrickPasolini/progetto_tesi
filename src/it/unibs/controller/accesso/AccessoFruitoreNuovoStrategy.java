package it.unibs.controller.accesso;

import it.unibs.domain.Comprensorio;
import it.unibs.domain.Fruitore;
import it.unibs.domain.Utente;
import it.unibs.model.ModelAccesso;
import it.unibs.mylib.InputDati;
import it.unibs.view.BaseView;
import it.unibs.view.ViewAccesso;
import it.unibs.view.ViewNewConfiguratore;
import it.unibs.view.ViewNewFruitore;

public class AccessoFruitoreNuovoStrategy implements StrategyAccesso {
	
	public AccessoFruitoreNuovoStrategy() {
		
    }

	@Override
	public void eseguiAccesso(ModelAccesso modelAccesso, BaseView view) {
		
		if (!(view instanceof ViewNewConfiguratore)) {
            System.out.println("Errore: vista non compatibile");
            return;
        }
        
		ViewNewFruitore viewNewFru = (ViewNewFruitore) view;
       	String username=viewNewFru.getUsername().toLowerCase();
   		String password=viewNewFru.getPassword();
   		String email=viewNewFru.getEmail();
   		// comprensorio
//   		Utente user = new Fruitore(username, password, email, comprensorio);
//   		Utente user = new Fruitore(username, password, email, comprensorio);
//		modelAccesso.salvaNewUser(nome, user);
//		viewAccesso.msgAccessoNuovoFruitore();
//		modelAccesso.inizializzaFruitore();
       
   		
   		
   		
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
