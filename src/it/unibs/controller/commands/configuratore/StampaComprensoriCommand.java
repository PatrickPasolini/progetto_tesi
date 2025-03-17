package it.unibs.controller.commands.configuratore;

import it.unibs.controller.GestoreComprensoriConfiguratore;
import it.unibs.controller.commands.CommandUtente;

public class StampaComprensoriCommand implements CommandUtente {
	private GestoreComprensoriConfiguratore gestoreComprensori;
	
	public StampaComprensoriCommand(GestoreComprensoriConfiguratore gestoreComprensori) {
		this.gestoreComprensori = gestoreComprensori;
	}

	@Override
	public void execute() {
//		gestoreComprensori.stampaComprensori();		
	}
}
