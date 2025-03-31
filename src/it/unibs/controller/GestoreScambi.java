package it.unibs.controller;

import java.util.ArrayList;

import javax.swing.JFrame;

import it.unibs.controllerGrasp.GerarchieHandler;
import it.unibs.controllerGrasp.ScambiHandler;
import it.unibs.domain.*;
import it.unibs.model.Model;
import it.unibs.mylib.*;
import it.unibs.view.accesso.*;
import it.unibs.view.console.ViewConfiguratore;
import it.unibs.view.console.ViewFruitore;
import it.unibs.view.fruitore.ViewFormulaProposteScambio;
import it.unibs.view.fruitore.ViewNavigaGerarchie;
import it.unibs.view.fruitore.ViewRitiraProposte;
import it.unibs.view.fruitore.ViewVisualizzaProposte;

public class GestoreScambi {
	private ScambiHandler scambiHandler;
	private GerarchieHandler gerarchieHandler;
	private JFrame frame;
	private Model model;
	
	public GestoreScambi(Model model,JFrame frame) {
		super();
		this.model=model; 
		this.frame=frame;
		this.scambiHandler = new ScambiHandler(model);
		this.gerarchieHandler = new GerarchieHandler(model);
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
	public void creaProposta() {
		ViewFormulaProposteScambio viewProposte = new ViewFormulaProposteScambio(frame, gerarchieHandler.getGerarchie());
		frame.getContentPane().add(viewProposte);
		viewProposte.setLayout(null);
		

		
		
//		view.msgSceltaPrestazioneRichiesta();
//		Foglia richiesta = gestoreGerarchieFruitore.navigaGerarchia();
//		
//		view.msgInserimentoOreProposta(richiesta);
//		int oreRichiesta = InputDati.leggiInteroPositivo("");
//		
//		Foglia offerta;
//		do {
//			view.msgSceltaPrestazioneOfferta();
//			offerta = gestoreGerarchieFruitore.navigaGerarchia();
//		} while (richiesta.equals(offerta));
//		
//		
//		int oreOfferta = scambiHandler.calcolaOreDaFattore(richiesta, offerta, oreRichiesta);
//		Proposta proposta = new Proposta(richiesta, offerta, oreRichiesta, oreOfferta, scambiHandler.getUser());
//		
//		view.msgSceltaAccettaScambio(proposta);
//		if(InputDati.yesOrNo("")) {
//			scambiHandler.addScambio(proposta);
//		}
	}
	
	/**
	 * Metodo per visualizzare tutte le proposte fatte dall'utente
	 * @param gestoreScambi 
	 * @since 4
	 */
	public void visualizzaProposteUtente() {
		ArrayList<Proposta> scambiAperti = scambiHandler.getScambiApertiFruitore();
		ArrayList<Proposta> scambiChiusi = scambiHandler.getScambiChiusiFruitore();
		ArrayList<Proposta> scambiRitirati = scambiHandler.getScambiRitiratiFruitore();
		
		ViewVisualizzaProposte viewProposte = new ViewVisualizzaProposte(frame);
		frame.getContentPane().add(viewProposte);
		viewProposte.setLayout(null);
		viewProposte.setBtnApertiListeners(e-> viewProposte.visualizzaAperti(scambiAperti,scambiHandler.getNameUser()));
		viewProposte.setBtnChiusiListeners(e-> viewProposte.visualizzaChiusi(scambiChiusi,scambiHandler.getNameUser()));
		viewProposte.setBtnRitiratiListeners(e-> viewProposte.visualizzaRitirati(scambiRitirati,scambiHandler.getNameUser()));
		viewProposte.setBtnHomeListener(e-> backHome());
	}
	
	private void backHome() {
		ControllerFruitore controllerConfiguratore = new ControllerFruitore(model, frame);
		controllerConfiguratore.run();
	}
	
	/**
	 * Metodo per ritirare una proposta tra quelle aperte
	 * dopo averla scelta viene chiesta conferma sul ritiro
	 * @param gestoreScambi 
	 * 
	 * @since 4
	 */
	public void ritiraProposta() {
		ArrayList<Proposta> scambiAperti = scambiHandler.getScambiApertiFruitore();
//		ArrayList<Proposta> scambiChiusi = scambiHandler.getScambiChiusiFruitore();
//		ArrayList<Proposta> scambiRitirati = scambiHandler.getScambiRitiratiFruitore();
		
		ViewRitiraProposte viewRitiraProposte = new ViewRitiraProposte(frame,scambiAperti);
		frame.getContentPane().add(viewRitiraProposte);
		viewRitiraProposte.setLayout(null);
		
		viewRitiraProposte.setBtnRitiraListener(e-> {
				Proposta propDaRitirare = viewRitiraProposte.getPropostaSelezionata();
//				view.msgConfermaRitiroProposta(p);
				if(propDaRitirare!=null) {
					scambiHandler.ritiraScambioAperto(propDaRitirare);
					
					viewRitiraProposte.aggiornaListaScambi(scambiHandler.getScambiApertiFruitore());
				}
		});
		viewRitiraProposte.setBtnHomeListener(e-> backHome());
	
		
		
		
//		ArrayList<Proposta> scambiAperti = scambiHandler.getScambiApertiFruitore();
//		MyMenu menuProposte = view.menuSceltaRitiraProposta(scambiAperti);
//		int scelta = menuProposte.scegli();
//		
//		if(scelta != 0) {
//			Proposta p = scambiAperti.get(scelta-1);
//			
//			view.msgConfermaRitiroProposta(p);
//			if(InputDati.yesOrNo("")) {
//				scambiHandler.ritiraScambioAperto(p);
//			}
//		}
	}
}
