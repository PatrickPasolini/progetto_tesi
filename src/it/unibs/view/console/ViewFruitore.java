package it.unibs.view.console;

import java.util.ArrayList;
import java.util.List;

import it.unibs.domain.Foglia;
import it.unibs.domain.Proposta;
import it.unibs.mylib.MyMenu;
/**
 * Gestisce l'interfaccia utente per il fruitore.
 * Fornisce metodi per l'interazione con l'utente e la visualizzazione dei dati.
 */
public class ViewFruitore extends View{
	private final static String[] SCELTE_FRUITORE = new String[]{
			"Naviga Gerarchie",
			"Formula proposte di scambio di prestazioni",
			"Visualizza proposte",
			"Ritira proposta"};
public final static MyMenu MENU_SCELTE_FRUITORE = new MyMenu("Scegli: ", SCELTE_FRUITORE);
	
	public ViewFruitore() {
	}
	
	public void stampaMenuRun() {
		MENU_SCELTE_FRUITORE.stampaMenu();
	}
	
	public MyMenu getMenuRun() {
		return MENU_SCELTE_FRUITORE;
	}
	
	/**
	 * Creazione e stampa del Menu per la scelta del dominio del campo 
	 * @param campo
	 * @param domini
	 * @return MyMenu per la scelta del dominio
	 * @since 2
	 */
	public MyMenu menuSceltaDominio(String campo, List<String> domini) {
		String msg = "Scegli dominio del campo: " + campo;
		
		return menuSceltaArrayList(msg, domini);
	}
	
	/**
	 * Stampa un messaggio per indicare all'utente la foglia selezionata 
	 * @param foglia 
	 * @since 2
	 */
	public void msgFogliaSelezionata(Foglia foglia) {
		StringBuilder sb = new StringBuilder();
		sb.append("\nHAI SELEZIONATO LA FOGLIA: ").append(foglia.getNome());
		
		if(!foglia.getDescrizione().equals(""))
			sb.append("  descrizone :[ ").append(foglia.getDescrizione()).append(" ]");
		
		sb.append("\n");
		
		System.out.println(sb);
	}

	/**
	 * @since 3
	 */
	public void msgSceltaPrestazioneRichiesta() {
		System.out.println("\nFormula una proposta di scambio di prestazioni:");
		System.out.println("-->seleziona la prestazione d'opera che necessiti");		
	}
	
	/**
	 * @param richiesta
	 * @since 3
	 */
	public void msgInserimentoOreProposta(Foglia richiesta) {
		System.out.println("Quante ore necessiti di " + richiesta.getNome() + " :" );
	}

	/**
	 * @since 3
	 */
	public void msgSceltaPrestazioneOfferta() {
		System.out.println("\n-->seleziona la prestazione d'opera che vuoi offrire(diversa dalla richiesta)");
	}
	
	/**
	 * Stampa un messaggio per la conferma dello scambio
	 * @param proposta da stampare
	 * @since 3
	 */
	public void msgSceltaAccettaScambio(Proposta proposta) {
		StringBuilder sb = new StringBuilder();
		sb.append("Hai scelto il seguente scambio: ");
		sb.append(stampaScambio(proposta));
		sb.append("\n\n").append("conformare l'offerta?(y/n)");
		
		System.out.println(sb);
	}
	
	/**
	 * @since 4
	 */
	public void msgVisualizzaProposte() {
		System.out.println("Proposte create");
	}
	
	/**
	 * Metodo per scambiare gli scambi aperti del fruitore
	 * @param scambiAperti lista di scambi aperti
	 * @since 4
	 */
	public void stampaScambiApertiFruitore(List<Proposta> scambiAperti) {
		System.out.println(stampaScambi(scambiAperti, "Scambi aperti: "));
	}
	
	/**
	 * Metodo per scambiare gli scambi chiusi del fruitore
	 * @param scambiChiusi lista di scambi chiusi
	 * @since 4
	 */
	public void stampaScambiChiusiFruitore(List<Proposta> scambiChiusi) {
		System.out.println(stampaScambi(scambiChiusi, "Scambi chiusi: "));
	}

	/**
	 * Metodo per scambiare gli scambi ritirati del fruitore
	 * @param scambiRitirati lista di scambi ritirati
	 * @since 4
	 */
	public void stampaScambiRitiratiFruitore(List<Proposta> scambiRitirati) {
		System.out.println(stampaScambi(scambiRitirati, "Scambi ritirati: "));
	}

	/**
	 * Menu per scelta della proposta da ritirare 
	 * @param scambi lista degli scambi da scegliere
	 * @return menu per la scelta
	 * @since 4
	 */
	public MyMenu menuSceltaRitiraProposta(List<Proposta> scambi) {
		String[] s = getScambiString(scambi).toArray(new String[0]);
		MyMenu menu = new MyMenu("Scegli proposta da ritirare", s);
		menu.stampaMenu();
		
		return menu;
	}
	
	/**
	 * Metodo per generare ArrayList con le string delle proposte della lista
	 * @param scambi la lista di proposte
	 * @return lista di string delle proposte
	 * @since 4
	 */
	private ArrayList<String> getScambiString(List<Proposta> scambi) {
		ArrayList<String> scambiString = new ArrayList<>();
		
		for(Proposta p : scambi) {
			scambiString.add(stampaScambio(p).toString());
		}
		
		return scambiString;
	}
	
	/**
	 * Messaggio per conferma di ritiro della proposta
	 * @param proposta
	 * @since 4
	 */
	public void msgConfermaRitiroProposta(Proposta proposta) {
		System.out.println("Confermare ritiro di: ");
		System.out.println(stampaScambio(proposta));
	}
}
