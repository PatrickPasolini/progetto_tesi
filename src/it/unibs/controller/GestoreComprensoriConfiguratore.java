package it.unibs.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.JFrame;

import it.unibs.controllerGrasp.ComprensoriHandler;
import it.unibs.controllerGrasp.GerarchieHandler;
import it.unibs.controllerGrasp.SalvaModificheHandler;
import it.unibs.controllerGrasp.ScambiHandler;
import it.unibs.domain.*;
import it.unibs.model.Model;
import it.unibs.view.configuratore.ViewNuovoComprensorio;
import it.unibs.view.configuratore.ViewVisualizzaComprensori;

public class GestoreComprensoriConfiguratore {
	private JFrame frame;
	private Model model;
	private List<String> comuni; 
	private Comprensorio comprensorio ;
	private ComprensoriHandler comprensoriHandler; 
	private SalvaModificheHandler salvaHandler;
	private ViewNuovoComprensorio viewNuovoComprensorio;
	ControllerConfiguratore controllerConfiguratore;
	private Stack<Runnable> navigationStack = new Stack<>();// Stack per gestire la navigazione

	
	public GestoreComprensoriConfiguratore(Model model, JFrame frame,ControllerConfiguratore controllerConfiguratore ) {
		this.model=model; 
		this.frame=frame;
		this.comuni = new ArrayList<>();
		this.comprensoriHandler = new ComprensoriHandler(model);
		this.salvaHandler=new SalvaModificheHandler(model);
		this.controllerConfiguratore = controllerConfiguratore;
	}

	private void navigateBack() {
	    if (!navigationStack.isEmpty()) {
	        Runnable previousView = navigationStack.pop();
	        previousView.run();
	    }
	}	
	public void nuovoComprensorio() {
		navigationStack.push(() -> backHome());
		viewNuovoComprensorio = new ViewNuovoComprensorio(frame);
		frame.getContentPane().add(viewNuovoComprensorio);
		viewNuovoComprensorio.setLayout(null);
		viewNuovoComprensorio.setBtnBackListeners(e-> navigateBack());
		viewNuovoComprensorio.setBtnPlusListener(e -> aggiungiComune()); 
		viewNuovoComprensorio.setBtnCreazioneListener(e -> confermaAggiungiComprensorio());
		viewNuovoComprensorio.setBtnHomeListener(e-> backHome());
	}
	
	private void aggiungiComune() {
        String comune = viewNuovoComprensorio.getComuneDaAggiungere().trim();
        if (!comune.isEmpty() && !comuni.contains(comune) && !comune.equals(viewNuovoComprensorio.getPlaceholderComune())) {
            comuni.add(comune);
            viewNuovoComprensorio.aggiornaListaComuni(comuni);
        }
    }
	
	
	private void confermaAggiungiComprensorio() {
		navigationStack.push(() -> nuovoComprensorio());
		String name=viewNuovoComprensorio.getNomeComprensorio();
		String placeholder = viewNuovoComprensorio.getPlaceholderComp();
		
		if(name.isEmpty() || name.equals(placeholder) || comuni.isEmpty()){
	        viewNuovoComprensorio.setCreazioneFallita_AlmenoUnComune();
	        return;
	    }
		
		if((!comprensoriHandler.checkNomeComprensorio(name))) {
			comprensorio = new Comprensorio(name,comuni);
			viewNuovoComprensorio.visualizzaConfermaCreazione(comprensorio);
		}
		else {
			viewNuovoComprensorio.setCreazioneFallita_NomeNonUnivoco();
		}
		
		viewNuovoComprensorio.setBtnConfermaCreazione(ev->{
			boolean risposta = Boolean.parseBoolean(ev.getActionCommand());
	        if (risposta) {
	        	aggiungiComprensorio(comprensorio);
	        }
	        else {
	        	backHome();
	        }
		});	
		
	}
	
	private void aggiungiComprensorio(Comprensorio comprensorio) {
		comprensoriHandler.addComprensorio(comprensorio);
		salvaHandler.salvaModifiche();
		viewNuovoComprensorio.setCreazioneEseguita(comprensorio);
	}
	
	private void backHome() {
		navigationStack.clear();
		
		controllerConfiguratore.run();
	}
	
	public void stampaComprensori() {
		ViewVisualizzaComprensori viewNuovoComprensorio = new ViewVisualizzaComprensori(frame,comprensoriHandler.getComprensori());
		frame.getContentPane().add(viewNuovoComprensorio);
		viewNuovoComprensorio.setLayout(null);
		viewNuovoComprensorio.setBtnBackListeners(e-> backHome());
	}

}