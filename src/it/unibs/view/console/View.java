package it.unibs.view.console;

import java.util.*;

import it.unibs.domain.Proposta;
import it.unibs.mylib.MyMenu;

public class View {
	public View() {
		super();
	}
	
	public void stampaTerminaPorgramma() {
		System.out.println("programma terminato");
	}
	
	/**
	 * Costruttore di un MyMenu con le scelte in ArrayList
	 * @param messaggio da visualizzare come titolo del menu
	 * @param scelte l'elenco delle opzioni da mostrare nel menu
	 * @return MyMenu creato
	 * @since 1
	 */
	protected MyMenu menuSceltaArrayList(String msg, List<String> scelte) {
		String[] s = scelte.toArray(new String[0]);
		MyMenu menu = new MyMenu(msg, s);
		menu.stampaMenuNoExit();
		
		return menu;
	}
	
	/**
	 * Genera una stringa formattata per visualizzare i dettagli di una proposta
	 * @param proposta da stampare
	 * @return stringa della proposta da stampare
	 * @since 4
	 */
	protected StringBuilder stampaScambio(Proposta proposta) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n\n");
		sb.append(" - richiesta:  [").append(proposta.getRichiesta().getNome());
		sb.append(", ").append(proposta.getOreRichiesta()).append(" ore]\n");
		sb.append(" | offerta:  [").append(proposta.getOfferta().getNome());
		sb.append(", ").append(proposta.getOreOfferta()).append(" ore]");
		
		return sb;
	}
	
	/**
	 * Genera una stringa formattata per visualizzare tutti gli scambi della lista
	 * @param scambi lista di proposte da stampare
	 * @param titolo titolo della lista
	 * @return stringa delle proposte da stampare
	 * @since 4
	 */
	protected StringBuilder stampaScambi(List<Proposta> scambi, String titolo) {
		StringBuilder sb = new StringBuilder();
		sb.append(titolo);
		
		for(Proposta p : scambi) {
			sb.append(stampaScambio(p));
			sb.append("\n");
		}
				
		return sb;
	}

	/**
	 * Menu per scelta della radice
	 * @param nomiRadici nomi delle radici passate
	 * @return MyMenu per la scelta
	 * @since 2
	 */
	public MyMenu menuSceltaRadice(ArrayList<String> nomiRadici) {
		return menuSceltaArrayList("Scelta radice", nomiRadici);
	}
}
