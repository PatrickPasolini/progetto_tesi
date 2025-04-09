package it.unibs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import it.unibs.controllerGrasp.ComprensoriHandler;
import it.unibs.controllerGrasp.SalvaModificheHandler;
import it.unibs.domain.*;
import it.unibs.model.Model;
import it.unibs.mylib.InputDati;
import it.unibs.view.accesso.*;
import it.unibs.view.configuratore.ViewNuovoComprensorio;
import it.unibs.view.configuratore.ViewVisualizzaComprensori;
import it.unibs.view.console.ViewConfiguratore;

public class GestoreComprensoriConfiguratore {
	private JFrame frame;
	private Model model;
	private List<String> comuni; 
	private ComprensoriHandler comprensoriHandler; 
	private SalvaModificheHandler salvaHandler;
	private ViewNuovoComprensorio viewNuovoComprensorio;
	
	public GestoreComprensoriConfiguratore(Model model, JFrame frame) {
		this.model=model; 
		this.frame=frame;
		this.comuni = new ArrayList<>();
		this.comprensoriHandler = new ComprensoriHandler(model);
		this.salvaHandler=new SalvaModificheHandler(model);
	}

	public void nuovoComprensorio() {
		viewNuovoComprensorio = new ViewNuovoComprensorio(frame);
		frame.getContentPane().add(viewNuovoComprensorio);
		viewNuovoComprensorio.setLayout(null);
		viewNuovoComprensorio.setBtnBackListeners(e-> backHome());
		viewNuovoComprensorio.setBtnPlusListener(e -> aggiungiComune()); 
		viewNuovoComprensorio.setBtnCreazioneListener(e -> aggiungiComprensorio());
		viewNuovoComprensorio.setBtnHomeListener(e-> backHome());
	}
	
	private void aggiungiComune() {
        String comune = viewNuovoComprensorio.getComuneDaAggiungere().trim();
        if (!comune.isEmpty() && !comuni.contains(comune) && !comune.equals(viewNuovoComprensorio.getPlaceholderComune())) {
            comuni.add(comune);
            viewNuovoComprensorio.aggiornaListaComuni(comuni);
        }
    }
	
	private void aggiungiComprensorio() {
		String name=viewNuovoComprensorio.getNomeComprensorio();
		String placeholder = viewNuovoComprensorio.getPlaceholderComp();
		
		if(name.isEmpty() || name.equals(placeholder) || comuni.isEmpty()){
	        viewNuovoComprensorio.setCreazioneFallita_AlmenoUnComune();
	        return;
	    }
		
		if((!comprensoriHandler.checkNomeComprensorio(name))) {
			comprensoriHandler.addComprensorio(new Comprensorio(name,comuni));
			salvaHandler.salvaModifiche();	
			viewNuovoComprensorio.setCreazioneEseguita(name);
		}
		else {
			viewNuovoComprensorio.setCreazioneFallita_NomeNonUnivoco();
		}
	}
	
	private void backHome() {
		ControllerConfiguratore controllerConfiguratore = new ControllerConfiguratore(model, frame);
		controllerConfiguratore.run();
	}
	
	public void stampaComprensori() {
		ViewVisualizzaComprensori viewNuovoComprensorio = new ViewVisualizzaComprensori(frame,comprensoriHandler.getComprensori());
		frame.getContentPane().add(viewNuovoComprensorio);
		viewNuovoComprensorio.setLayout(null);
		viewNuovoComprensorio.setBtnHomeListener(e-> backHome());
	}

}