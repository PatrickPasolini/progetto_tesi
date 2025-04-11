package it.unibs.controller.commands.configuratore;

import it.unibs.controller.GestoreComprensoriConfiguratore;
import it.unibs.controller.commands.CommandUtente;

public class AggiungiComprensorioCommand implements CommandUtente {
	private GestoreComprensoriConfiguratore gestoreComprensori;

	public AggiungiComprensorioCommand(GestoreComprensoriConfiguratore gestoreComprensori) {
		this.gestoreComprensori = gestoreComprensori;
	}

	@Override
	public void execute() {
		gestoreComprensori.nuovoComprensorio();		
	}
}
