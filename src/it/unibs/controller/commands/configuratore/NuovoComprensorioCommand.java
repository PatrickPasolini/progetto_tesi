package it.unibs.controller.commands.configuratore;

import it.unibs.controller.GestoreComprensoriConfiguratore;
import it.unibs.controller.commands.CommandUtente;

public class NuovoComprensorioCommand implements CommandUtente {
	private GestoreComprensoriConfiguratore gestoreComprensori;

	public NuovoComprensorioCommand(GestoreComprensoriConfiguratore gestoreComprensori) {
		this.gestoreComprensori = gestoreComprensori;
	}

	@Override
	public void execute() {
		gestoreComprensori.nuovoComprensorio();		
	}
}
