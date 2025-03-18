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
	
	public GestoreComprensoriConfiguratore(Model model, JFrame frame) {
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
		if(!comprensoriHandler.checkNomeComprensorio(name)) {
			comprensoriHandler.addComprensorio(new Comprensorio(name,comuni));
			salvaHandler.salvaModifiche();	
			this.viewNuovoComprensorio.setAccessoEseguito();
		}
		else {
			this.viewNuovoComprensorio.setAccessoFallito();
		}
	}
}