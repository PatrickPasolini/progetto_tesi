package it.unibs.controller;

import java.util.ArrayList;

import javax.swing.JFrame;

import it.unibs.controllerGrasp.GerarchieHandler;
import it.unibs.controllerGrasp.ScambiHandler;
import it.unibs.domain.*;
import it.unibs.model.Model;
import it.unibs.mylib.*;
import it.unibs.view.configuratore.ViewScambiCategoria;
import it.unibs.view.console.ViewConfiguratore;
import it.unibs.view.fruitore.ViewFormulaProposteScambio;
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

	// #GESTORE SCAMBI-CONFIGURATORE
	
	// ## visualizza proposte aperte/chiuse/ritirate di una prestazione d'opera
	/**
	 * Metodo per visualizzare tutte le proposte fatte dall'utente
	 * @since 4
	 */
	public void visualizzaProposteFoglia() {
		ViewScambiCategoria viewProposte = new ViewScambiCategoria(frame, gerarchieHandler.getGerarchie());
		frame.getContentPane().add(viewProposte);
		viewProposte.setLayout(null);
				
		viewProposte.setBtnContinuaListener(e-> visualizzaSceltaScambi(viewProposte));
	}
	
	private void visualizzaSceltaScambi(ViewScambiCategoria viewProposte) {
		Foglia foglia = viewProposte.getFogliaSelezionata();
		viewProposte.visualizzaSceltaScambi();
		
		ArrayList<Proposta> scambiAperti = scambiHandler.getScambiApertiFoglia(foglia);
		ArrayList<Proposta> scambiChiusi = scambiHandler.getScambiChiusiFoglia(foglia);
		ArrayList<Proposta> scambiRitirati = scambiHandler.getScambiRitiratiFoglia(foglia);	
		
		viewProposte.setBtnBackListeners(e->visualizzaProposteFoglia());
		viewProposte.setBtnApertiListeners(e-> viewProposte.visualizzaAperti(scambiAperti));
		viewProposte.setBtnChiusiListeners(e-> viewProposte.visualizzaChiusi(scambiChiusi));
		viewProposte.setBtnRitiratiListeners(e-> viewProposte.visualizzaRitirati(scambiRitirati));
		viewProposte.setBtnHomeListener(e-> backHomeConfiguratore());
	}
	
	
	
	
	
	
	
	private void backHomeConfiguratore() {
		ControllerConfiguratore controllerConfiguratore = new ControllerConfiguratore(model, frame);
		controllerConfiguratore.run();
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
	
	private Foglia richiesta;
	private Foglia offerta;
	private int oreRichiesta;
	private int oreOfferta;
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
		
		viewProposte.setBtnContinuaListener(e->{
			richiesta = viewProposte.getFogliaSelezionata();
			viewProposte.visualizzaRichiesta(richiesta);
		});
		
		viewProposte.setBtnConfermaRichiestaListener(e->{
			oreRichiesta = viewProposte.getOreRichiesta();
			viewProposte.visualizzaSceltaOfferta();
		});
		
		viewProposte.setBtnConfermaOffertaListener(e->{
			offerta = viewProposte.getFogliaSelezionata();
			oreOfferta = scambiHandler.calcolaOreDaFattore(richiesta, offerta, oreRichiesta);
			viewProposte.visualizzaPropostaFormulata(richiesta,oreRichiesta,offerta,oreOfferta);
			;
			System.out.println(offerta.getNome());

		}
		);
		viewProposte.setBtnConfermaCreazione(e -> {
            boolean risposta = Boolean.parseBoolean(e.getActionCommand());
            
            if (risposta) {
            	viewProposte.visualizzaCreazione(richiesta, offerta, oreRichiesta, oreOfferta);
    			Proposta proposta = new Proposta(richiesta, offerta, oreRichiesta, oreOfferta, scambiHandler.getUser());
    			scambiHandler.addScambio(proposta);
			}
            else {
            	backHomeFruitore();
            }
            

		});
		viewProposte.setBtnHome(e ->backHomeFruitore());
		
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
		viewProposte.setBtnHomeListener(e-> backHomeFruitore());
	}
	
	private void backHomeFruitore() {
		ControllerFruitore controllerFruitore = new ControllerFruitore(model, frame);
		controllerFruitore.run();
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
		viewRitiraProposte.setBtnHomeListener(e-> backHomeFruitore());
	
		//TODO CHIEDI CONFERMA PER ELIMINARE

	}
}
