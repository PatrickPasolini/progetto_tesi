package it.unibs.controller;

import java.util.ArrayList;

import it.unibs.controllerGrasp.ScambiHandler;
import it.unibs.domain.*;
import it.unibs.model.Model;
import it.unibs.mylib.*;
import it.unibs.view.*;
import it.unibs.view.console.ViewConfiguratore;
import it.unibs.view.console.ViewFruitore;

public class GestoreScambi {
	private ScambiHandler scambiHandler;
	
	public GestoreScambi(Model model) {
		super();
		this.scambiHandler = new ScambiHandler(model);
	}

	// GESTORE SCAMBI-CONFIGURATORE
	
	/**
	 * Metodo per visualizzare tutte le proposte fatte dall'utente
	 * @since 4
	 */
	public void visualizzaProposteFoglia(ViewConfiguratore view,Foglia foglia) {
		ArrayList<Proposta> scambiAperti = scambiHandler.getScambiApertiFoglia(foglia);
		ArrayList<Proposta> scambiChiusi = scambiHandler.getScambiChiusiFoglia(foglia);
		ArrayList<Proposta> scambiRitirati = scambiHandler.getScambiRitiratiFoglia(foglia);
				
		view.msgVisualizzaProposteFoglia(foglia);
		view.stampaScambiApertiFoglia(scambiAperti);
		view.stampaScambiChiusiFoglia(scambiChiusi);
		view.stampaScambiRitiratiFoglia(scambiRitirati);
	}
	
	/**
	 * Metodo per visualizzare le mail e le informazioni deglu utenti di un insieme
	 * di scambi chiusi
	 * Se non ci sono scambi completi viene stampato un messaggio
	 * Se ci sono scambi completi si sceglie lo scambio con un menu
	 * @since 4 
	 */
	public void visualizzaScambiCompleti(ViewConfiguratore view) {
		if(scambiHandler.getScambiCompleti().isEmpty()) {
			view.msgScambiCompletiVuoto();
			return;
		}
		
		MyMenu menuScambio = view.menuSceltaScambio(scambiHandler.getNomiScambiCopleti());
		int scelta = menuScambio.scegli();
		
		view.stampaScambioCompleto(scambiHandler.getScambioCompleto(scelta-1));
	}

	
	// GESTORE SCAMBI-FRUITORE
	
	/**
	 * Permette al fruitore la formulazione di una proposta di scambio di prestazioni
	 * richiedendo un quantitativo di ore di una prestazione 
	 * e offrendo un altro tipo di prestazione
	 * il sistema calcola le ore necessarie della prestazione offerta sulla base dei fdc
	 * Se il fruitore conferma lo scambio viene salvato in forma persistente 
	 *@since 3
	 */
	public void creaProposta(ViewFruitore view, GestoreGerarchieFruitore gestoreGerarchieFruitore) {
		view.msgSceltaPrestazioneRichiesta();
		Foglia richiesta = gestoreGerarchieFruitore.navigaGerarchia();
		
		view.msgInserimentoOreProposta(richiesta);
		int oreRichiesta = InputDati.leggiInteroPositivo("");
		
		Foglia offerta;
		do {
			view.msgSceltaPrestazioneOfferta();
			offerta = gestoreGerarchieFruitore.navigaGerarchia();
		} while (richiesta.equals(offerta));
		
		
		int oreOfferta = scambiHandler.calcolaOreDaFattore(richiesta, offerta, oreRichiesta);
		Proposta proposta = new Proposta(richiesta, offerta, oreRichiesta, oreOfferta, scambiHandler.getUser());
		
		view.msgSceltaAccettaScambio(proposta);
		if(InputDati.yesOrNo("")) {
			scambiHandler.addScambio(proposta);
		}
	}
	
	/**
	 * Metodo per visualizzare tutte le proposte fatte dall'utente
	 * @param gestoreScambi 
	 * @since 4
	 */
	public void visualizzaProposteUtente(ViewFruitore view) {
		ArrayList<Proposta> scambiAperti = scambiHandler.getScambiApertiFruitore();
		ArrayList<Proposta> scambiChiusi = scambiHandler.getScambiChiusiFruitore();
		ArrayList<Proposta> scambiRitirati = scambiHandler.getScambiRitiratiFruitore();
				
		view.msgVisualizzaProposte();
		
		view.stampaScambiApertiFruitore(scambiAperti);
		view.stampaScambiChiusiFruitore(scambiChiusi);
		view.stampaScambiRitiratiFruitore(scambiRitirati);
	}
	
	/**
	 * Metodo per ritirare una proposta tra quelle aperte
	 * dopo averla scelta viene chiesta conferma sul ritiro
	 * @param gestoreScambi 
	 * 
	 * @since 4
	 */
	public void ritiraProposta(ViewFruitore view) {
		ArrayList<Proposta> scambiAperti = scambiHandler.getScambiApertiFruitore();
		MyMenu menuProposte = view.menuSceltaRitiraProposta(scambiAperti);
		int scelta = menuProposte.scegli();
		
		if(scelta != 0) {
			Proposta p = scambiAperti.get(scelta-1);
			
			view.msgConfermaRitiroProposta(p);
			if(InputDati.yesOrNo("")) {
				scambiHandler.ritiraScambioAperto(p);
			}
		}
	}
}
