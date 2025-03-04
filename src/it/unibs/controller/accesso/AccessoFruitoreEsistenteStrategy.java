package it.unibs.controller.accesso;

import it.unibs.model.ModelAccesso;
import it.unibs.mylib.InputDati;
import it.unibs.view.console.ViewAccesso;

public class AccessoFruitoreEsistenteStrategy implements StrategyAccesso {

	public AccessoFruitoreEsistenteStrategy() {
		
    }

	@Override
	public void eseguiAccesso(ModelAccesso modelAccesso, ViewAccesso viewAccesso) {
		viewAccesso.msgInserisciNome();
		String nome = InputDati.leggiStringaNonVuota("").toLowerCase();
		viewAccesso.msgInserisciPsw();
		String password = InputDati.leggiStringaNonVuota("").toLowerCase();
		
		if(modelAccesso.controllaAccessoFruitore(nome,password)) {
			modelAccesso.setUser(nome);
			
			viewAccesso.msgAccessoEffettuato();
		}else {
			viewAccesso.msgCredenzialiErrate();
			return;
		}
		
		modelAccesso.inizializzaFruitore();	
	}

}
