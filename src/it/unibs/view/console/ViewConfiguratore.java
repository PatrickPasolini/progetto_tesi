package it.unibs.view.console;

import java.util.*;

import it.unibs.domain.*;
import it.unibs.mylib.MyMenu;

/**
 * Gestisce l'interfaccia utente per il configuratore.
 * Fornisce metodi per l'interazione con l'utente e la visualizzazione dei dati.
 */
public class ViewConfiguratore extends View {
	private final static String CORNICE = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
	private final static String[] SCELTE_CONFIGURATORE = new String[]{
			"Aggiungi comprensorio",
			"Aggiungi gerarchia",
			"Salva Modifiche",
			"Visualizza comprensori",
			"Visualizza gerarchie",
			"Visualizza fattori di una categoria",
			"Visualizza scambi di una categoria",
			"Contatta utenti di uno scambio"};
public final static MyMenu MENU_SCELTE_CONFIG = new MyMenu("Scegli:", SCELTE_CONFIGURATORE);

	public ViewConfiguratore() {
		super();
	}
	
	public void stampaMenuRun() {
		MENU_SCELTE_CONFIG.stampaMenu();
	}
	
	public MyMenu getMenuRun() {
		return MENU_SCELTE_CONFIG;
	}
	
	public void msgNuovoComprensorioNome() {
		System.out.println("Inserisci un nome univoco per il tuo comprensorio: ");
	}

	public void msgNuovoComprensorioComuni() {
		System.out.println("Inserisci lista comuni, almeno 1 (Enter per confermare, @ per terminare): ");
	}

	public void msgCreazioneGerarchia() {
		System.out.println("Creazione della gerarchia:\n");
	}

	public void msgNomeRadiceGerarchia() {
		System.out.println("Inserire nome della radice: ");
	}

	public void msgNomeCampoGerarchia() {
		System.out.println("Inserire nome del campo: ");
	}
	
	/**
	 * Crea un menu di scelta per la selezione del tipo di categoria da aggiungere come figlio nella gerarchia
	 * @param c la categoria di cui si seleziona il tipo di figlio da aggiungere
	 * @param dominio il dominio associato al figlio da aggiungere
	 * @return MyMenu creato
	 * @since 1
	 */
	public MyMenu menuSceltaFigliGerarchia(NonFoglia c, String dominio) {
		System.out.println("Categoria : " +c.getNome() + "\t|\t campo : " + c.getCampo() + "\t|\t dominio : " + dominio);
		MyMenu menuFigli = new MyMenu("Scelta categoria: ", new String[] {"Categoria non foglia", "Categoria foglia"});
		menuFigli.stampaMenuNoExit();
		
		return menuFigli;
	}

	public void msgNomeNuovaCategoria() {
		System.out.println("Inserire nome della categoria: ");
	}
	
	public void msgFattoreConversioneFoglie(Foglia f1, Foglia f2) {
		System.out.println("Fattore di conversione di " + f1.getNome() + " rispetto a " + f2.getNome());
	}

	public void msgInserimentoFattoreConversione(double min, double max) {
		System.out.printf("Inserire valore compreso tra %.4f e %.4f\n",min, max);
	}
	
	public void msgDescrizioneCategoria() {
		System.out.println("Inserisci una descrizione per la categoria o premi invio per ignorare");
	}

	public void msgInputDominiCampo(NonFoglia n) {
		System.out.println("Inserire domini (almeno 1) del campo: [" + n.getCampo() + "] di " + n.getNome());
	}

	public void msgInputDominio() {
		System.out.print("dominio (@ per terminare): ");
	}

	public void msgInputDominiEmpty() {
		System.out.println("Inserire almeno 1 dominio");
	}
	
	/**
	 * Menu per scegliere se iniziare o meno la creazione della gerarchia
	 * @return
	 */
	public MyMenu menuInizioCreazioneGerarchia(){
		MyMenu menuInizio = new MyMenu("Creare nuova categoria", new String[] {"crea"});
		menuInizio.stampaMenu();
		return menuInizio;
	}
	
	/**
	 * Menu per scelta delle foglie
	 * @param nomiRadici nomi delle foglie passate
	 * @return MyMenu per la scelta
	 * @since 1
	 */
	public MyMenu menuSceltaFoglia(ArrayList<String> nomiFoglie) {
		return menuSceltaArrayList("Scelta foglia", nomiFoglie);
	}

	/**
	 * Menu per scelta della gerarchia
	 * @return MyMenu per la scelta
	 * @since 1
	 */
	public MyMenu menuSceltaAggiuntaFattori() {
		MyMenu menuGerarchia = new MyMenu("Scelta gerarchia corrente o esistente: ", new String[] {"Gerarchia corrente", "Gerarchia esistente"});
		menuGerarchia.stampaMenuNoExit();
		return menuGerarchia;
	}

	/**
	 * Stampa la lista dei comprensori con i relativi comuni
	 * @param comprensori lista di comprensori da stampare
	 * @since 1
	 */
	public void stampaComprensori(List<Comprensorio> comprensori) {
		System.out.println(CORNICE);
		System.out.println("LISTA COMPRENSORI:");
		
		for (Comprensorio c : comprensori) {
			System.out.println(stampaComprensorio(c));
		}
	}
	
	/**
	 * Metodo per formattare la stampa di un comrpensorio
	 * @param c il comprensorio da stampare
	 * @return la stringa del comprensorio da stampare
	 * @since 1
	 */
	public StringBuilder stampaComprensorio(Comprensorio c) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Comprensorio - " + c.getName() +" = [" );
		
		for (String comune : c.getComuni()) {
			sb.append(comune).append(", ");
		}
		sb.append("]");

		return sb;
	}
	
	/**
	 * Stampa le gerarchie, a partire dalla radice di ciascuna gerarchia
	 * @param gerarchie l'iterabile contenente le gerarchie da stampare
	 * @since 1
	 */
	public void stampaGerarchie(Iterable<Gerarchia> gerarchie) {
		System.out.println(CORNICE);
		
		for (Gerarchia g : gerarchie) {
			Categoria radice = g.getRadice();
			StringBuilder sb = new StringBuilder();
			System.out.println(stampaGerarchia(radice, 0, sb));
		}
	}
	
	/**
	 * Metodo di supporto per la stampa ricorsiva della gerarchia
	 * @param parent la categoria di partenza per la costruzione di sb
	 * @param i l'indentazione corrente
	 * @param sb lo StringBuilder su cui costruire la rappresentazione della gerarchia
	 * @return lo StringBuilder contenente la rappresentazione della gerarchia
	 * @since 1
	 */
	public StringBuilder stampaGerarchia(Categoria parent,int i,StringBuilder sb) {
		for (int j = 0; j < i; j++) {
			sb.append("\t");
		}
		sb.append(parent.getNome());
		
		if (parent instanceof NonFoglia) {
			NonFoglia n = (NonFoglia) parent;
			sb.append(" - campo :[ ").append(n.getCampo()).append(" ]");
		}
		
		if(!parent.getDescrizione().equals(""))
			sb.append(" - descrizone :[ ").append(parent.getDescrizione()).append(" ]");
		
		sb.append("\n");
		i++;
		
		for (Categoria child : parent.getChilds()) {
            stampaGerarchia(child, i, sb );
        }
		
		return sb;
	}

	/**
	 * Stampa i fattori di conversione tra foglie di categorie
	 * @param mapFattori la nestedMap dei fattori di conversione da stampare {@link NestedMap}
	 * @since 1
	 */
	public void stampaFattori(NestedMap<Foglia, Foglia, Double> mapFattori) {
		StringBuilder sb = new StringBuilder();
		sb.append("Fattori di conversione:\n");
		
		for(Foglia f : mapFattori.keySet()) {
			sb.append("Fattori di ").append(f.getNome());
			
			Map<Foglia, Double> fattoriF = mapFattori.get(f);
			for(Map.Entry<Foglia, Double> entry : fattoriF.entrySet()) {
				Foglia fogliaF2 = entry.getKey();
	            Double fattore = entry.getValue();
	            
	            sb.append("\n\t|").append(fogliaF2.getNome()).append(" = ").append(fattore);
	        }
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

	/**
	 * Stampa i fattori di conversione di una Foglia di riferimento
	 * @param mapFattori la nestedMap dei fattori di conversione da stampare {@link NestedMap}
	 * @param foglia la foglia di riferimento
	 * @since 1
	 */
	public void stampaFattoriDiCOnversioneFoglia(NestedMap<Foglia, Foglia, Double> mapFattori, Foglia foglia) {
		StringBuilder sb = new StringBuilder();
		sb.append("Fattori di ").append(foglia.getNome());
		
		Map<Foglia, Double> fattoriF = mapFattori.get(foglia);
		for(Map.Entry<Foglia, Double> entry : fattoriF.entrySet()) {
			Foglia fogliaF2 = entry.getKey();
            Double fattore = entry.getValue();
            
            sb.append("\n\t|").append(fogliaF2.getNome()).append(" = ");
            sb.append(String.format("%.6f", fattore));
        }
		
		System.out.println(sb);
	}

	/**
	 * @since 4
	 */
	public void msgVisualizzaProposteFoglia(Foglia foglia) {
		System.out.println("Proposte riguardanti: " + foglia.getNome());
	}

	/**
	 * Metodo per scambiare gli scambi aperti della caetgoria
	 * @param scambiAperti lista di scambi aperti
	 * @since 4
	 */
	public void stampaScambiApertiFoglia(ArrayList<Proposta> scambiAperti) {
		System.out.println(stampaScambi(scambiAperti, "Scambi aperti: "));	
	}

	/**
	 * Metodo per scambiare gli scambi chiusi della caetgoria
	 * @param scambiChiusi lista di scambi chiusi
	 * @since 4
	 */
	public void stampaScambiChiusiFoglia(ArrayList<Proposta> scambiChiusi) {
		System.out.println(stampaScambi(scambiChiusi, "Scambi chiusi: "));
	}

	/**
	 * Metodo per scambiare gli scambi ritirati della caetgoria
	 * @param scambiRitirati lista di scambi ritirati
	 * @since 4
	 */
	public void stampaScambiRitiratiFoglia(ArrayList<Proposta> scambiRitirati) {
		System.out.println(stampaScambi(scambiRitirati, "Scambi ritirati: "));
	}
	
	
	/**
	 * Menu per scelta di uno scambio completo
	 * @param nomiScambi i nomi degli scambi
	 * @return MyMenu per la scelta
	 * @since 4
	 */
	public MyMenu menuSceltaScambio(ArrayList<String> nomiScambi) {
		return menuSceltaArrayList("Scelta scambio", nomiScambi);
	}
	
	/**
	 * Metodo per stampare le prestazioni offerte e ricevute da ogni utente
	 * che partecipa in uno scambio
	 * @param scambio lo scambio da stampare
	 * @since 4
	 */
	public void stampaScambioCompleto(Scambio scambio) {
		StringBuilder sb = new StringBuilder();
		sb.append("Scambio: " + scambio.getNome()).append("\n");
		
		for(Proposta p : scambio.getScambio()) {
			sb.append(stampaScambioMail(scambio, p)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	/**
	 * Genera una stringa formattata per visualizzare i dettagli degli utenti
	 * che ricevono o offrono prestazioni in una proposta dllo scambio
	 * @param scambio lo scambio di riferimento
	 * @param proposta la proposta di riferimento
	 * @return stringa della proposta da stampare
	 * @since 4
	 */
	private StringBuilder stampaScambioMail(Scambio scambio, Proposta proposta) {
		StringBuilder sb = new StringBuilder();
		
		Fruitore utente = (Fruitore)proposta.getFruitoreRichiedente();

		Foglia richiesta = proposta.getRichiesta();
		Foglia offerta = proposta.getOfferta();
		
		Fruitore ricevente = null;
		Fruitore offerente = null;
		
		for(Proposta p2 : scambio.getScambio()) {
			if(offerta.equals(p2.getRichiesta()))
				ricevente = (Fruitore)p2.getFruitoreRichiedente();
			
			if(richiesta.equals(p2.getOfferta()))
				offerente = (Fruitore)p2.getFruitoreRichiedente();
		}
		
		sb.append("\t").append("Utente: " + utente.getNome()).append("   mail: " + utente.getIndirizzoMail());
		sb.append("\n\t\t-").append("riceve: " + richiesta.getNome()).append(" per [" + proposta.getOreRichiesta() + " ore]")
			.append("  dall'utente: " + offerente.getNome()).append("   mail: " + offerente.getIndirizzoMail());
		sb.append("\n\t\t-").append("offre: " + offerta.getNome()).append(" per [" + proposta.getOreOfferta() + " ore]")
			.append("  all'utente: " + ricevente.getNome()).append("   mail: " + ricevente.getIndirizzoMail());

		return sb;
	}

	/**
	 * Messaggio per avvisare che non ci sono scambi completati da visualizzare
	 * @since 4
	 */
	public void msgScambiCompletiVuoto() {
		System.out.println("Non ci sono scambi completati");	
	}

}
