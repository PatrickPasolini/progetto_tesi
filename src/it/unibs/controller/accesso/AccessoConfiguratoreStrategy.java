package it.unibs.controller.accesso;

import it.unibs.domain.Configuratore;
import it.unibs.domain.Utente;
import it.unibs.model.ModelAccesso;
import it.unibs.mylib.InputDati;
import it.unibs.view.console.ViewAccesso;

public class AccessoConfiguratoreStrategy implements StrategyAccesso {
    
	public AccessoConfiguratoreStrategy() {
		
    }

	/**
	 * Metodo per l'accesso del configuratore
	 * se effettuato con le credenaziali predefinite chiede il cambiamento delle credenziali(nome,password)
	 * Con accesso corretto o nuove credenziali si assegna a user il Configuratore
	 * @return 1 se accesso confermato, 0 accesso negato
	 * @since 2
	 */
    @Override
    public void eseguiAccesso(ModelAccesso modelAccesso, ViewAccesso viewAccesso) {
        viewAccesso.msgInserisciNome();
        String nome = InputDati.leggiStringaNonVuota("").toLowerCase();
        viewAccesso.msgInserisciPsw();
        String password = InputDati.leggiStringaNonVuota("");

        if (modelAccesso.controllaDefault(nome, password)) {
            do {
                viewAccesso.msgNuoveCredenziali();
                viewAccesso.msgInserisciNome();
                nome = InputDati.leggiStringaNonVuota("").toLowerCase();
                viewAccesso.msgInserisciPsw();
                password = InputDati.leggiStringaNonVuota("");
            } while (!modelAccesso.credenzialiUnivoche(nome));

            Utente user = new Configuratore(nome, password);
            modelAccesso.salvaNewUser(nome, user);

            viewAccesso.msgConfermaNewConfiguratore();
        } else if (modelAccesso.controllaAccessoConfiguratore(nome, password)) {
            modelAccesso.setUser(nome);
            viewAccesso.msgAccessoEffettuato();
        } else {
            viewAccesso.msgCredenzialiErrate();
            return;
        }

        modelAccesso.inizializzaConfiguratore();
    }
	
}

