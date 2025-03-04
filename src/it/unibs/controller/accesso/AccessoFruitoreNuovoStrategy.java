package it.unibs.controller.accesso;

import it.unibs.controller.GestoreComprensoriAccesso;
import it.unibs.domain.Comprensorio;
import it.unibs.domain.Fruitore;
import it.unibs.domain.Utente;
import it.unibs.model.ModelAccesso;
import it.unibs.mylib.InputDati;
import it.unibs.view.console.ViewAccesso;

public class AccessoFruitoreNuovoStrategy implements StrategyAccesso {
	
	public AccessoFruitoreNuovoStrategy() {
		
    }

	@Override
	public void eseguiAccesso(ModelAccesso modelAccesso, ViewAccesso viewAccesso) {
		viewAccesso.msgInserisciNome();
		String nome = InputDati.leggiStringaNonVuota("").toLowerCase();
		
		while(!modelAccesso.credenzialiUnivoche(nome)) {
			viewAccesso.msgNomeUnivoco();
			nome = InputDati.leggiStringaNonVuota("").toLowerCase();
		};
		
		viewAccesso.msgInserisciPsw();
		String password = InputDati.leggiStringaNonVuota("");
		
		viewAccesso.msgInserisciMail(); 
		String indirizzoMail = InputDati.leggiStringaNonVuota("");
		
		GestoreComprensoriAccesso gestoreCopmprensoriAccesso = new GestoreComprensoriAccesso(modelAccesso,viewAccesso);
		Comprensorio comprensorio = null;
		
		comprensorio = gestoreCopmprensoriAccesso.sceltaComprensorio();
		
		Utente user = new Fruitore(nome, password, indirizzoMail, comprensorio);
		modelAccesso.salvaNewUser(nome, user);
		
		viewAccesso.msgAccessoNuovoFruitore();
		
		modelAccesso.inizializzaFruitore();

	}

}
