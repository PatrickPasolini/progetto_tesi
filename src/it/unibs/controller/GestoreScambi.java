package it.unibs.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.swing.JFrame;
import it.unibs.controllerGrasp.GerarchieHandler;
import it.unibs.controllerGrasp.ScambiHandler;
import it.unibs.domain.*;
import it.unibs.model.Model;
import it.unibs.view.configuratore.ViewContattaUtentiScambio;
import it.unibs.view.configuratore.ViewScambiCategoria;
import it.unibs.view.fruitore.ViewFormulaProposteScambio;
import it.unibs.view.fruitore.ViewRitiraProposte;
import it.unibs.view.fruitore.ViewVisualizzaProposte;

public class GestoreScambi {
	private Model model;
	private JFrame frame;
	private ScambiHandler scambiHandler;
	private GerarchieHandler gerarchieHandler;
	private Stack<Runnable> navigationStack = new Stack<>();// Stack per gestire la navigazione
	
	public GestoreScambi(Model model,JFrame frame) {
		super();
		this.model=model; 
		this.frame=frame;
		this.scambiHandler = new ScambiHandler(model);
		this.gerarchieHandler = new GerarchieHandler(model);
	}
	
	// Metodo generico per gestire la navigazione indietro
	private void navigateBack() {
	    if (!navigationStack.isEmpty()) {
	        Runnable previousView = navigationStack.pop();
	        previousView.run();
	    }
	}	

	// #GESTORE SCAMBI-CONFIGURATORE
	private void backHomeConfiguratore() {
		ControllerConfiguratore controllerConfiguratore = new ControllerConfiguratore(model, frame);
		controllerConfiguratore.run();
	}
	
	// ## VISUALIZZA PROPOSTE APERTE/CHIUSE/RITIRATE DI UNA PRESTAZIONE D'OPERA
	public void visualizzaProposteFoglia() {
		navigationStack.push(() -> backHomeConfiguratore());
	    ViewScambiCategoria viewProposte = new ViewScambiCategoria(frame, gerarchieHandler.getGerarchie());
	    frame.getContentPane().add(viewProposte);
	    viewProposte.setLayout(null);
	    viewProposte.setBtnBackListeners(e -> navigateBack());
	    viewProposte.setBtnContinuaListener(e -> sceltaScambi(viewProposte));
	}
	private void sceltaScambi(ViewScambiCategoria viewProposte) {
	    navigationStack.push(() -> visualizzaProposteFoglia());
	    Foglia foglia = viewProposte.getFogliaSelezionata();
	    if(foglia == null) {
	        viewProposte.setSceltaFallita();
	    } else {
	        viewProposte.visualizzaSceltaScambi();
	        viewProposte.setBtnBackListeners(e -> navigateBack());
	        visualizzaScambi(viewProposte, foglia);
	    }
	}
	private void visualizzaScambi(ViewScambiCategoria viewProposte, Foglia foglia) {
	    viewProposte.setBtnApertiListeners(e -> visualizzaAperti(viewProposte, foglia));
	    viewProposte.setBtnChiusiListeners(e -> visualizzaChiusi(viewProposte, foglia));
	    viewProposte.setBtnRitiratiListeners(e -> visualizzaRitirati(viewProposte, foglia));
	    viewProposte.setBtnHomeListener(e -> backHomeConfiguratore());
	}
	private void visualizzaAperti(ViewScambiCategoria viewProposte,Foglia foglia) {
		navigationStack.push(() -> sceltaScambi(viewProposte));
		ArrayList<Proposta> scambiAperti = scambiHandler.getScambiApertiFoglia(foglia);
		viewProposte.visualizzaAperti(scambiAperti, foglia.getNome());
	}
	private void visualizzaChiusi(ViewScambiCategoria viewProposte,Foglia foglia) {
		navigationStack.push(() -> sceltaScambi(viewProposte));
		ArrayList<Proposta> scambiChiusi = scambiHandler.getScambiChiusiFoglia(foglia);
		viewProposte.visualizzaChiusi(scambiChiusi, foglia.getNome());
	}
	private void visualizzaRitirati(ViewScambiCategoria viewProposte,Foglia foglia) {
		navigationStack.push(() -> sceltaScambi(viewProposte));
		ArrayList<Proposta> scambiRitirati = scambiHandler.getScambiRitiratiFoglia(foglia);
		viewProposte.visualizzaRitirati(scambiRitirati, foglia.getNome());
	}
	
	
	// ## VISUALIZZA GLI SCAMBI COMPLETI
	/**
	 * Metodo per visualizzare le mail e le informazioni deglu utenti di un insieme
	 * di scambi chiusi
	 * Se non ci sono scambi completi viene stampato un messaggio
	 * Se ci sono scambi completi si sceglie lo scambio con un menu
	 * @since 4 
	 */
	public void visualizzaScambiCompleti() {
		List<Scambio> scambi = scambiHandler.getScambiCompleti();
		ViewContattaUtentiScambio viewContatta = new ViewContattaUtentiScambio(frame,scambiHandler.getNomiScambiCopleti());	
		frame.getContentPane().add(viewContatta);
		viewContatta.setLayout(null);
		
		if(scambiHandler.getScambiCompleti().isEmpty()) {
//			view.msgScambiCompletiVuoto();
			return;
		}
		
		viewContatta.setBtnHomeListener(e->backHomeConfiguratore());
		viewContatta.setBtnSelezioneListener(e-> {
			int index = viewContatta.getIndexScambioSelezionato();
			Scambio scambioSelezionato = scambiHandler.getScambioCompleto(index);
			viewContatta.mostraDettagliScambio(scambioSelezionato);
		});
		
	}
	
	
	// # GESTORE SCAMBI-FRUITORE
	private Foglia richiesta;
	private Foglia offerta;
	private int oreRichiesta;
	private int oreOfferta;
	private void backHomeFruitore() {
		ControllerFruitore controllerFruitore = new ControllerFruitore(model, frame);
		controllerFruitore.run();
	}
	/**
	 * Permette al fruitore la formulazione di una proposta di scambio di prestazioni
	 * richiedendo un quantitativo di ore di una prestazione 
	 * e offrendo un altro tipo di prestazione
	 * il sistema calcola le ore necessarie della prestazione offerta sulla base dei fdc
	 * Se il fruitore conferma lo scambio viene salvato in forma persistente 
	 *@since 3
	 */
	
	public void creaProposta() {		
		navigationStack.push(() -> backHomeFruitore());
		ViewFormulaProposteScambio viewProposte = new ViewFormulaProposteScambio(frame, gerarchieHandler.getGerarchie());
		frame.getContentPane().add(viewProposte);
		viewProposte.setLayout(null);
		viewProposte.setBtnContinuaListener(e-> sceltaRichiesta(viewProposte));
		
		viewProposte.setBtnBackListeners(e-> navigateBack());
		viewProposte.setBtnHome(e ->backHomeFruitore());
	}
	private void sceltaRichiesta(ViewFormulaProposteScambio viewProposte) {
		navigationStack.push(() -> creaProposta());
		if(viewProposte.getFogliaSelezionata()!=null)
			richiesta = viewProposte.getFogliaSelezionata();
		viewProposte.visualizzaRichiesta(richiesta);
		viewProposte.setBtnConfermaRichiestaListener(e-> sceltaOreRichiesta(viewProposte));
	}
	private void sceltaOreRichiesta(ViewFormulaProposteScambio viewProposte) {
		navigationStack.push(() -> sceltaRichiesta(viewProposte));
		oreRichiesta = viewProposte.getOreRichiesta();
		viewProposte.visualizzaSceltaOfferta();
		viewProposte.setBtnConfermaOffertaListener(e->sceltaOfferta(viewProposte));
	}
	private void sceltaOfferta(ViewFormulaProposteScambio viewProposte) {
		navigationStack.push(() -> sceltaOreRichiesta(viewProposte));
		offerta = viewProposte.getFogliaSelezionata();
		oreOfferta = scambiHandler.calcolaOreDaFattore(richiesta, offerta, oreRichiesta);
		viewProposte.visualizzaPropostaFormulata(richiesta,oreRichiesta,offerta,oreOfferta);
		viewProposte.setBtnConfermaCreazione(e-> {
			boolean risposta = Boolean.parseBoolean(e.getActionCommand());
	        if (risposta) {
	        	navigationStack.push(() -> sceltaOfferta(viewProposte));
	        	viewProposte.visualizzaCreazione(richiesta, offerta, oreRichiesta, oreOfferta);
				Proposta proposta = new Proposta(richiesta, offerta, oreRichiesta, oreOfferta, scambiHandler.getUser());
				scambiHandler.addScambio(proposta);
			}
	        else {
	        	backHomeFruitore();
	        }
		});
	}
	
	
	/**
	 * Metodo per visualizzare tutte le proposte fatte dall'utente
	 * @param gestoreScambi 
	 * @since 4
	 */
	public void visualizzaProposteUtente() {
		navigationStack.push(() -> backHomeFruitore());
		ArrayList<Proposta> scambiAperti = scambiHandler.getScambiApertiFruitore();
		ArrayList<Proposta> scambiChiusi = scambiHandler.getScambiChiusiFruitore();
		ArrayList<Proposta> scambiRitirati = scambiHandler.getScambiRitiratiFruitore();
		
		ViewVisualizzaProposte viewProposte = new ViewVisualizzaProposte(frame);
		frame.getContentPane().add(viewProposte);
		viewProposte.setLayout(null);
		
		viewProposte.setBtnApertiListeners(e-> {
			navigationStack.push(() -> visualizzaProposteUtente());
			viewProposte.visualizzaAperti(scambiAperti,scambiHandler.getNameUser());
		});
		viewProposte.setBtnChiusiListeners(e-> {
			navigationStack.push(() -> visualizzaProposteUtente());
			viewProposte.visualizzaChiusi(scambiChiusi,scambiHandler.getNameUser());
		});	 
		viewProposte.setBtnRitiratiListeners(e-> {
			navigationStack.push(() -> visualizzaProposteUtente());
			viewProposte.visualizzaRitirati(scambiRitirati,scambiHandler.getNameUser());
		});
		viewProposte.setBtnBackListeners(e-> navigateBack());
		viewProposte.setBtnHomeListener(e-> backHomeFruitore());
	}
	
	
	
	// ## RITIRO PROPOSTE
	/**
	 * Metodo per ritirare una proposta tra quelle aperte
	 * dopo averla scelta viene chiesta conferma sul ritiro
	 * @param gestoreScambi 
	 * 
	 * @since 4
	 */
	public void ritiraProposta() {
		navigationStack.push(() -> backHomeFruitore());
		
		ArrayList<Proposta> scambiAperti = scambiHandler.getScambiApertiFruitore();
		ViewRitiraProposte viewRitiraProposte = new ViewRitiraProposte(frame,scambiAperti);
		frame.getContentPane().add(viewRitiraProposte);
		viewRitiraProposte.setLayout(null);
		
		viewRitiraProposte.setBtnHomeListener(e-> backHomeFruitore());
		if (!scambiAperti.isEmpty()) {
			viewRitiraProposte.setBtnRitiraListener(e-> visualizzaConferma(viewRitiraProposte));
			viewRitiraProposte.setBtnBackListeners(e -> navigateBack());
		}
		else {
			viewRitiraProposte.visualizzaNessunoScambioRitirabile();
		}
		
	}
	public void visualizzaConferma(ViewRitiraProposte viewRitiraProposte) {
		Proposta propDaRitirare = viewRitiraProposte.getPropostaSelezionata();
		if(propDaRitirare!=null) { 
			navigationStack.push(() -> ritiraProposta());
			viewRitiraProposte.visualizzaConfermaRitiro(propDaRitirare);
			
			viewRitiraProposte.setBtnConfermaCreazione(ev->{
				boolean risposta = Boolean.parseBoolean(ev.getActionCommand());
		        if (risposta) {
		        	viewRitiraProposte.visualizzaRitiroEffettuato(propDaRitirare);
		        	scambiHandler.ritiraScambioAperto(propDaRitirare);
		        }
		        else {
		        	backHomeFruitore();
		        }
			});	
		}
		else {
			viewRitiraProposte.setSelezioneFallita();
		}
		
	}
	
}
