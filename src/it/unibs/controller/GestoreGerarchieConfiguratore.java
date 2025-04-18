package it.unibs.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.swing.JFrame;

import it.unibs.view.configuratore.ViewAddGerarchia;
import it.unibs.view.configuratore.ViewAddGerarchiaFoglia;
import it.unibs.view.configuratore.ViewAddGerarchiaNonFoglia;
import it.unibs.view.configuratore.ViewAddGerarchiaRadice;
import it.unibs.view.configuratore.ViewFattori;
import it.unibs.view.configuratore.ViewVisualizzaGerarchie;
import it.unibs.controllerGrasp.GerarchieHandler;
import it.unibs.domain.*;
import it.unibs.model.Model;

/**
 * La classe GestoreGerarchie e' un controller e gestisce le operazioni relative alla creazione e gestione delle gerarchie.
 * Consente di creare nuove gerarchie, permette la creazione dei nodi foglia e nonFoglia 
 * e la creazione dei fattori di conversione tra foglie
 */
public class GestoreGerarchieConfiguratore {
	private GerarchieHandler gerarchieHandler; 
	private JFrame frame;
	private Model model;

	private Stack<Runnable> navigationStack = new Stack<>();
	private ViewAddGerarchiaRadice viewRadice;
	private ViewAddGerarchiaFoglia viewFoglia;
	private ViewAddGerarchiaNonFoglia viewNonFoglia;
	
	public GestoreGerarchieConfiguratore(Model model, JFrame frame) {
		this.model = model;
		this.frame = frame;
		this.gerarchieHandler = new GerarchieHandler(model);
	}
	private void navigateBack() {
	    if (!navigationStack.isEmpty()) {
	        Runnable previousView = navigationStack.pop();
	        previousView.run();
	    }
    }
	private void backHome() {
		
		navigationStack.clear();
		ControllerConfiguratore controllerConfiguratore = new ControllerConfiguratore(model, frame);
		controllerConfiguratore.run();
	}

	public void inizioCreazione() {
		navigationStack.push(() -> backHomeConfiguratore());

		gerarchieHandler.resetNewGerarchia();
		viewRadice = new ViewAddGerarchiaRadice(frame);
		frame.getContentPane().add(viewRadice);
		viewRadice.setLayout(null);
		
		viewRadice.setBtnAvantiListener(e -> addRadice(viewRadice));
		viewRadice.setBtnBackListeners(e-> navigateBack());
		viewRadice.setBtnHomeListener(e->backHome());
		
		
//		gerarchieHandler.resetNewGerarchia();
//		view.msgCreazioneGerarchia();
//		NonFoglia radice = addRadice();
//		gerarchieHandler.addRadice(radice);
//		
//		costruisciGerarchia(gerarchieHandler.getRadiceNewGerarchia());
//		gerarchieHandler.addGerarchia();
	}
	
	private void addRadice(ViewAddGerarchiaRadice view) {
		String nome = view.getRadiceField(); 
		if (gerarchieHandler.checkNomeRadiceGerarchia(nome) 
				|| nome.isEmpty() || nome.equals(view.getRadicePlaceholder())) {
		    view.setNomeRadiceNonUnivoco(view);
			return;
		}
		else {
			navigationStack.push(() -> inizioCreazione());
			String descrizione = view.getDescrizioneField();
			NonFoglia radice =  new NonFoglia(nome, "", descrizione);
			gerarchieHandler.addRadice(radice);
			
			
			sceltaNodoACuiAggiungere(view);
		}
	}
	
	private void sceltaNodoACuiAggiungere(ViewAddGerarchia view ) {
		
		
		List<Gerarchia> gerarchiaInCostruzione = new ArrayList<>();
		gerarchiaInCostruzione.add(gerarchieHandler.getNewGerarchia());
		view.setBtnContinuaListener(e->sceltaTipoNodo(view.getFogliaSelezionata(),view));

		if(view instanceof ViewAddGerarchiaFoglia){
			ViewAddGerarchiaFoglia viewF = (ViewAddGerarchiaFoglia) view;
			if(gerarchieHandler.isTerminabile()) {
				viewF.setTerminabile(true);
				viewF.setBtnTerminaListener(e->confermaTerminazione(viewF));
			}
			else {
				viewF.setTerminabile(false);
			}
			
			viewF.visualizzaSceltaNodo(gerarchiaInCostruzione);

		}
		else {
			view.visualizzaSceltaNodo(gerarchiaInCostruzione);
		}
	
	}
	
	private void confermaTerminazione(ViewAddGerarchiaFoglia viewF) {
		List<Gerarchia> gerarchiaInCostruzione = new ArrayList<>();
		gerarchiaInCostruzione.add(gerarchieHandler.getNewGerarchia());
		viewF.confermaTerminazione(gerarchiaInCostruzione);
		
		
		viewFoglia.setBtnConfermaCreazione(e->{
			boolean risposta = Boolean.parseBoolean(e.getActionCommand());
	        if (risposta) {
	        	viewFoglia.creazioneConclusa(gerarchiaInCostruzione);
	        	gerarchieHandler.addGerarchia();
	        	
	        }
	        else {
	        	backHome();
	        }
		});	
	}
	
	
	
	
	/**
     * Aggiungere figli alla gerarchia in base alla scelta dell'utente
     * scelta = 1 -> aggiungi figlio non foglia
     * scelta = 2 -> aggiungi figlio foglia
     */
	private void sceltaTipoNodo(NonFoglia parent,ViewAddGerarchia view) {
		
		int scelta = view.getTipoSelezionato();
		if (parent==null || scelta ==0) {
			view.setSelezioneFallita();
    		return;
		}
		
		NonFoglia radice = (NonFoglia) gerarchieHandler.getNewGerarchia().getRadice();
		if(radice == parent) {
			System.out.println(parent.getNome());
			navigationStack.push(()->sceltaNodoACuiAggiungere(view));
		}
			
		
		switch(scelta) {
    	case 1:
            creazioneNonFoglia(parent);
            break; 
    	case 2:
//    		addFoglia(parent);
    		creazioneFoglia(parent);
    		break;
    	default:
    		break;
		}   
		
	}
	private void creazioneFoglia(NonFoglia parent) {
		 navigationStack.push(() -> sceltaNodoACuiAggiungere(viewNonFoglia != null ? viewNonFoglia : viewRadice));
		    
		
		viewFoglia = new ViewAddGerarchiaFoglia(frame, parent);
		frame.getContentPane().add(viewFoglia);
		viewFoglia.setLayout(null);
		
		viewFoglia.setBtnAvantiListener(e->addFoglia(parent));
		viewFoglia.setBtnHomeListener(e->backHome()); 
		viewFoglia.setBtnBackListeners(e ->navigateBack());
	}
	private void addFoglia(NonFoglia parent) {
		String nome = viewFoglia.getNomeField();
		if (gerarchieHandler.getNewGerarchia().checkNomeCategoria(nome)
				|| nome.isEmpty() || nome.equals(viewFoglia.getNomePlaceholder())) {
			viewFoglia.setNomeNellaGerarchiaNonUnivoco(viewFoglia);
			return;
		}
		else {
			navigationStack.push(() -> creazioneFoglia(parent));
			String descrizione = viewFoglia.getDescrizioneField();
			Foglia fogliaNew =  new Foglia(nome,descrizione,gerarchieHandler.getNewGerarchia().getNomeRadice());
			gerarchieHandler.getNewGerarchia().addCategoria(fogliaNew);
			
			sceltaFogliaFdc(parent,fogliaNew);
		}
//		Foglia fogliaNew = new Foglia(nome, descrizione,gerarchieHandler.getNewGerarchia().getNomeRadice());
//		gerarchieHandler.getNewGerarchia().addCategoria(fogliaNew);
//		
//		inserisciFattoriConversione(fogliaNew);
//		
//		gerarchieHandler.getNewGerarchia().addFoglia(fogliaNew);
//		parent.addChilds(fogliaNew);
	}
	
	private void sceltaFogliaFdc(NonFoglia parent, Foglia fogliaNew) {
//		inserisciFattoriConversione(fogliaNew);
//		viewFoglia.visualizzaSceltaFogliaFDC(gerarchiaInCostruzione);
		navigationStack.push(() -> addFoglia(parent));
		gerarchieHandler.getNewGerarchia().addFoglia(fogliaNew);
		parent.addChilds(fogliaNew); // forse aggiungendo dopo i fdc non va bene
		
		List<Gerarchia> gerarchiaInCostruzione = new ArrayList<>();
		gerarchiaInCostruzione.add(gerarchieHandler.getNewGerarchia());
		for (Gerarchia gerarchia : gerarchieHandler.getGerarchie()) {
			gerarchiaInCostruzione.add(gerarchia);
		}
		
		
		viewFoglia.visualizzaSceltaFogliaFDC(gerarchiaInCostruzione, fogliaNew);
		viewFoglia.setBtnNodoListener(e->visualizzaSceltaFogliaFdc(fogliaNew));
	}
	
	private void visualizzaSceltaFogliaFdc(Foglia fogliaNew) {
	    // Salva lo stato corrente per tornare indietro
	    navigationStack.push(() -> {
	        List<Gerarchia> gerarchiaInCostruzione = new ArrayList<>();
	        gerarchiaInCostruzione.add(gerarchieHandler.getNewGerarchia());
	        for (Gerarchia gerarchia : gerarchieHandler.getGerarchie()) {
	            gerarchiaInCostruzione.add(gerarchia);
	        }
	        viewFoglia.visualizzaSceltaFogliaFDC(gerarchiaInCostruzione, fogliaNew);
	    });
	    
	    Foglia fogliaOld = viewFoglia.getFogliaSelezionataa();
	    //TODO controlla se non seleziona nulla
	    
	    double min = getFattoreMin(fogliaNew, fogliaOld);
	    double max = getFattoreMax(fogliaNew, fogliaOld);
	    viewFoglia.visualizzaSceltaFogliaFDC(fogliaNew.getNome(), fogliaOld.getNome(), min, max);
	    
	    viewFoglia.setBtnFdcListener(e->{
	        inserimentoFdc(fogliaNew, fogliaOld);
	        sceltaNodoACuiAggiungere(viewFoglia);
	    });
	
	}
	
	
	
	private void inserimentoFdc(Foglia fogliaNew, Foglia fogliaOld) {
		double fattore = viewFoglia.getFdc();
		gerarchieHandler.calcolaFattoriConversione(fogliaNew, fogliaOld, fattore);
		
	}
	private void creazioneNonFoglia(NonFoglia parent) {
		viewNonFoglia = new ViewAddGerarchiaNonFoglia(frame, parent);
		frame.getContentPane().add(viewNonFoglia);
		viewNonFoglia.setLayout(null);
		
		viewNonFoglia.setBtnAvantiListener(e->addNonFoglia(parent));
		viewNonFoglia.setBtnHomeListener(e->backHome()); 
		viewNonFoglia.setBtnBackListeners(e ->navigateBack());
	}
	
	private void addNonFoglia(NonFoglia parent) {
		String nome = viewNonFoglia.getNomeField();
		if (gerarchieHandler.getNewGerarchia().checkNomeCategoria(nome)
				|| nome.isEmpty() || nome.equals(viewNonFoglia.getNomePlaceholder())) {
			viewNonFoglia.setNomeNellaGerarchiaNonUnivoco(viewNonFoglia);
			return;
		}
		else {
			navigationStack.push(() -> creazioneNonFoglia(parent));
			
			String descrizione = viewNonFoglia.getDescrizioneField();
			NonFoglia n =  new NonFoglia(nome, "", descrizione);
			gerarchieHandler.getNewGerarchia().addCategoria(n);
			parent.addChilds(n);
			
			List<Gerarchia> gerarchiaInCostruzione = new ArrayList<>();
			gerarchiaInCostruzione.add(gerarchieHandler.getNewGerarchia());
			
//			viewNonFoglia.visualizzaSceltaNodo(n.getNome(),gerarchiaInCostruzione);
			sceltaNodoACuiAggiungere(viewNonFoglia);
		}
	}

//	private void addFoglia(NonFoglia parent) {
//		String nome;
//		do {
//			view.msgNomeNuovaCategoria();
//			nome = InputDati.leggiStringaNonVuota("");
//		} while (gerarchieHandler.getNewGerarchia().checkNomeCategoria(nome));//check nome foglia univoco nella gerarchia
//		
//		view.msgDescrizioneCategoria();
//		String descrizione = InputDati.leggiStringa("");
//		
//		Foglia fogliaNew = new Foglia(nome, descrizione,gerarchieHandler.getNewGerarchia().getNomeRadice());
//		gerarchieHandler.getNewGerarchia().addCategoria(fogliaNew);
//		
//		inserisciFattoriConversione(fogliaNew);
//		
//		gerarchieHandler.getNewGerarchia().addFoglia(fogliaNew);
//		parent.addChilds(fogliaNew);
//	}
	
	private double getFattoreMin(Foglia fogliaNew, Foglia fogliaOld) {
		gerarchieHandler.calcolaFattoriMinMax(fogliaNew, fogliaOld);
		double min = gerarchieHandler.getFattoreMin();
//		double max = gerarchieHandler.getFattoreMax();
		return min;
	}
	private double getFattoreMax(Foglia fogliaNew, Foglia fogliaOld) {
//		gerarchieHandler.calcolaFattoriMinMax(fogliaNew, fogliaOld);
		double max = gerarchieHandler.getFattoreMax();
		return max;
	}

	
	private void inserisciFattoriConversione(Foglia fogliaNew, Foglia fogliaOld) {
//		Foglia fogliaOld = null;
//		
//		if(gerarchieHandler.getGerarchie().isEmpty() && gerarchieHandler.getNewGerarchia().foglieIsEmpty()) {
//			return;
//		}
//		else if(gerarchieHandler.getGerarchie().isEmpty() && !gerarchieHandler.getNewGerarchia().foglieIsEmpty()) {
//			fogliaOld = sceltaFogliaNewGerarchia();
//		}
//		else if(!gerarchieHandler.getGerarchie().isEmpty() && gerarchieHandler.getNewGerarchia().foglieIsEmpty()) {
//			fogliaOld = sceltaRadiceFoglia();
//		}
//		else if(!gerarchieHandler.getGerarchie().isEmpty() && !gerarchieHandler.getNewGerarchia().foglieIsEmpty()) {
//			fogliaOld = sceltaAggiuntaFattori();
//		}
//		else {
//			return;
//		}
//		
//		gerarchieHandler.calcolaFattoriMinMax(fogliaNew, fogliaOld);
//		double min = gerarchieHandler.getFattoreMin();
//		double max = gerarchieHandler.getFattoreMax();
//		
//		view.msgFattoreConversioneFoglie(fogliaNew, fogliaOld);
//		view.msgInserimentoFattoreConversione(min, max);
//		double fattore = InputDati.leggiDoubleConMinMax("", min, max);
//
//		gerarchieHandler.calcolaFattoriConversione(fogliaNew, fogliaOld, fattore);
	}
	
	/**
	 * Menu per la scelta di aggiungere il fdc della foglia appena creata con:
	 * 		- una foglia della gerarchia che si sta creando
	 * 		- una foglia di una gerarchia gia' presente
	 * @return la Foglia scelta rispetto cui aggiungere il fattore
	 * @since 1
	 */
//	private Foglia sceltaAggiuntaFattori() {
//	    int scelta = view.menuSceltaAggiuntaFattori().scegliNoExit();
//
//	    switch(scelta) {
//	    	case 1:
//	            return sceltaFogliaNewGerarchia();
//	    	case 2:
//	    		return sceltaRadiceFoglia();
//	    	default:
//	    		return null;
//	    }    
//	}
	
	/**
	 * Permette di selezionare una foglia tra tutte le foglie presenti nelle gerarchie nel model
	 * Prima permette di selezionare una radice e poi una foglia di tale radice
	 * @return foglia selezionata (richiesta al model)
	 * @since 1
	 */
//	private Foglia sceltaRadiceFoglia() {
//		ArrayList<String> nomiRadici = gerarchieHandler.getNomiRadici();
//		MyMenu menuRadici = view.menuSceltaRadice(nomiRadici);
//		int sceltaRadice = menuRadici.scegliNoExit();
//		
//		ArrayList<String> nomiFoglie = gerarchieHandler.getNomiFoglieGerarchia(sceltaRadice-1);
//		MyMenu menuFoglie = view.menuSceltaFoglia(nomiFoglie);
//		int sceltaFoglie = menuFoglie.scegliNoExit();
//		
//		return gerarchieHandler.getFogliaDaRadice(sceltaRadice-1, sceltaFoglie-1);
//	}
	
	/**
	 * Permette di selezionare una foglia all'interno della gerarchia che si sta creando 
	 * @return foglia selezionata (richiesta al model)
	 * @since 1
	 */
//	private Foglia sceltaFogliaNewGerarchia() {
//		ArrayList<String> nomiFoglie = gerarchieHandler.getNomiFoglieGerarchia(gerarchieHandler.getNewGerarchia());
//		MyMenu menuFoglie = view.menuSceltaFoglia(nomiFoglie);
//		int sceltaFoglie = menuFoglie.scegliNoExit();
//		
//		return gerarchieHandler.getNewGerarchia().getFoglia(sceltaFoglie-1);
//	}
	
	/**
	 * Permette di selezionare una foglia all'interno della gerarchia che si sta creando 
	 * e di visualizzarne i fattori di conversione
	 * @since 1
	 */ 
	public void sceltaFogliaFattori() {
		navigationStack.push(() -> backHomeConfiguratore());
		ViewFattori viewFattori = new ViewFattori(frame, gerarchieHandler.getGerarchie());
		frame.getContentPane().add(viewFattori);
		viewFattori.setLayout(null);
		viewFattori.setBtnBackListeners(e-> navigateBack());
		viewFattori.setBtnHomeListener(e->backHome());
		viewFattori.setBtnContinuaListener(e->{
			Foglia fogliaSelezionata = viewFattori.getFogliaSelezionata();
			if(fogliaSelezionata!=null) {
				navigationStack.push(() -> sceltaFogliaFattori());
				viewFattori.visualizzaFattori(gerarchieHandler.getMapFattori(),fogliaSelezionata);
			}
			viewFattori.setSceltaFallita();
		});
		
		

//		Foglia foglia = sceltaRadiceFoglia();
//		view.stampaFattoriDiCOnversioneFoglia(gerarchieHandler.getMapFattori(),foglia);
	}
		
	/**
	 * Metodo per visualizzare le gerarchie presenti
	 * @since 4
	 */
	public void stampaGerarchie() {
		ViewVisualizzaGerarchie viewGerarchie = new ViewVisualizzaGerarchie(frame,gerarchieHandler.getGerarchie() );
		frame.getContentPane().add(viewGerarchie);
		viewGerarchie.setLayout(null);
		viewGerarchie.setBtnHomeListener(e-> backHome());
		
	}
	private void backHomeConfiguratore() {
		ControllerConfiguratore controllerConfiguratore = new ControllerConfiguratore(model, frame);
		controllerConfiguratore.run();
	}
}
