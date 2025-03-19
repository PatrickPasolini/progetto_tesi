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
import it.unibs.view.console.ViewConfiguratore;

public class GestoreComprensoriConfiguratore {
	private JFrame frame;
	private ComprensoriHandler comprensoriHandler; 
	private ViewNuovoComprensorio viewNuovoComprensorio;
	private List<String> comuni ; 
	private SalvaModificheHandler salvaHandler;
	private Model model;
	
	public GestoreComprensoriConfiguratore(Model model, JFrame frame) {
		this.model=model; 
		this.frame=frame;
		this.comprensoriHandler = new ComprensoriHandler(model);
		this.comuni = new ArrayList<>();
		this.salvaHandler=new SalvaModificheHandler(model);
	}

	public void nuovoComprensorio() {
		viewNuovoComprensorio = new ViewNuovoComprensorio(frame);
		frame.getContentPane().add(viewNuovoComprensorio);
		viewNuovoComprensorio.setLayout(null);
		viewNuovoComprensorio.setBtnPlusListener(e -> aggiungiComune()); 
		viewNuovoComprensorio.setBtnCreazioneListener(e -> aggiungiComprensorio());
		viewNuovoComprensorio.setBtnHomeListener(e-> backHome());
	}
	
	private void backHome() {
		ControllerConfiguratore controllerConfiguratore = new ControllerConfiguratore(model, frame);
		controllerConfiguratore.run();
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
	        viewNuovoComprensorio.setCreazioneFallita();
	        return;
	    }
		
		if((!comprensoriHandler.checkNomeComprensorio(name))) {
			
			comprensoriHandler.addComprensorio(new Comprensorio(name,comuni));
			salvaHandler.salvaModifiche();	
			viewNuovoComprensorio.setCreazioneEseguita(name);
		}
		else {
			viewNuovoComprensorio.setCreazioneFallita();
		}
	}

//	private void aggiungiComprensorio() {
//	    String name = viewNuovoComprensorio.getNomeComprensorio().trim();
//	    String placeholder = (String) viewNuovoComprensorio.getPlaceholderComp();
//
//	    // Controllo sul nome: deve essere diverso da vuoto e dal placeholder
//	    if (name.isEmpty() || name.equals(placeholder)) {
//	        viewNuovoComprensorio.setAccessoFallito("Inserire un nome valido per il comprensorio!");
//	        return;
//	    }
//	    
//	    // Controllo che ci sia almeno un comune inserito
//	    if (comuni.isEmpty()) {
//	        viewNuovoComprensorio.setAccessoFallito("Inserire almeno un comune!");
//	        return;
//	    }
//	    
//	    // Se il nome non è già presente
//	    if (!comprensoriHandler.checkNomeComprensorio(name)) {
//	        comprensoriHandler.addComprensorio(new Comprensorio(name, comuni));
//	        salvaHandler.salvaModifiche();
//	        viewNuovoComprensorio.setAccessoEseguito(name);
//	    } else {
//	        viewNuovoComprensorio.setAccessoFallito("Nome comprensorio gia' presente, riprova!");
//	    }
//	}

}