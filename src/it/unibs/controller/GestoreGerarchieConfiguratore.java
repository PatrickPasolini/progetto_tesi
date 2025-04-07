package it.unibs.controller;


import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import it.unibs.view.configuratore.ViewAddGerarchiaNonFoglia;
import it.unibs.view.configuratore.ViewAddGerarchiaRadice;
import it.unibs.view.configuratore.ViewVisualizzaGerarchie;
import it.unibs.controllerGrasp.GerarchieHandler;
import it.unibs.domain.*;
import it.unibs.model.Model;

/**
 * La classe GestoreGerarchie e' un controller e gestisce le operazioni relative alla creazione e gestione delle gerarchie.
 * Consente di creare nuove gerarchie, permette la creazione dei nodi foglia e nonFoglia 
 * e la creazione dei fattori di conversione tra foglie
 */
public class GestoreGerarchieConfiguratore {
	private GerarchieHandler gerarchieHandler; 
	private JFrame frame;
	private Model model;

	private ViewAddGerarchiaRadice viewRadice;
	
	public GestoreGerarchieConfiguratore(Model model, JFrame frame) {
		this.model = model;
		this.gerarchieHandler = new GerarchieHandler(model);
		this.frame = frame;
	}
	
	private void backHome() {
		ControllerConfiguratore controllerConfiguratore = new ControllerConfiguratore(model, frame);
		controllerConfiguratore.run();
	}
	
	
	private NonFoglia radice;
	private List<String> domini = new ArrayList<>();
	/**
	 * Menu per scegliere se continuare con la creazione o terminare prima che inizi la creazione
	 * @since 1
	 */
	public void inizioCreazione() {
		gerarchieHandler.resetNewGerarchia();
		viewRadice = new ViewAddGerarchiaRadice(frame);
		frame.getContentPane().add(viewRadice);
		viewRadice.setLayout(null);
		
		viewRadice.setBtnAvantiListener(e -> {
			radice = addRadice();
			gerarchieHandler.addRadice(radice);
			viewRadice.visualizzaSceltaNodo(radice.getNome(),radice.getCampo(),radice.getDomini());
		});
		viewRadice.setBtnPlusListener(e -> addDominiNonFoglia()); 
		
		viewRadice.setBtnConfermaCreazione(e -> {
            boolean foglia = Boolean.parseBoolean(e.getActionCommand());
            
            if (foglia) {
            	
			}
            else {
            	ViewAddGerarchiaNonFoglia viewNonFoglia = new ViewAddGerarchiaNonFoglia(frame,gerarchieHandler.getGerarchie());
            	frame.getContentPane().add(viewNonFoglia);
            	viewNonFoglia.setLayout(null);
            	
//            	viewNonFoglia.aggiornaAlbero(gerarchieHandler.getGerarchie());
            }
            

		});
		
//		gerarchieHandler.resetNewGerarchia();
//		view.msgCreazioneGerarchia();
//		NonFoglia radice = addRadice();
//		gerarchieHandler.addRadice(radice);
//		
//		costruisciGerarchia(gerarchieHandler.getRadiceNewGerarchia());
//		gerarchieHandler.addGerarchia();
	}
	
	 /**
     * Costruisce la gerarchia in modo ricorsivo partendo dalla Categoria fornita come parametro
     * Se parent e' istanza di Foglia il metodo non fa niente
     * Altrimenti, chiede l'inserimento di un figlio (Categoria) per ogni dominio di parent
     * per poi chiamare costruisciGerarchia su ogni figlio (ricorsione)
     * @param parent la categoria genitore
     * @since 1
     */
	private void costruisciGerarchia(Categoria parent) {
		if(parent instanceof Foglia)
			return;
		
		for(String dominio : ((NonFoglia)parent).getDomini()) {
			aggiungiFigli((NonFoglia) parent, dominio);
		}
		
		for (Categoria child : parent.getChilds()) {
            costruisciGerarchia(child);
        }
	}
	
	 /**
     * Metodo costituito da un meno che permette di aggiungere figli alla gerarchia in base alla scelta dell'utente
     * scelta = 1 -> aggiungi figlio non foglia
     * scelta = 2 -> aggiungi figlio foglia
     *
     * @param parent       la categoria genitore 
     * @param dominio il dominio della categoria genitore
     * @since 1
     */
	private void aggiungiFigli(NonFoglia parent, String dominio) {
//	    int scelta = view.menuSceltaFigliGerarchia(parent, dominio).scegliNoExit();
//
//	    switch(scelta) {
//	    	case 1:
//	            addNonFoglia(parent);
//	            break; 
//	    	case 2:
//	    		addFoglia(parent);
//	    		break;
//	    	default:
//	    		break;
//	    }    
	}
	
	/**
     * Aggiunge la radice della gerarchia, chiede l'inserimento di un nome, descrizone(facoltativa), campo 
     * Controlla che il nome della radice sia univoco, per permettere alle gerarchie di essere distinte {@link Model#checkNomeRadiceGerarchia(String)}}
     * @return la radice della gerarchia
     * @since 1
     */
	private NonFoglia addRadice() {
		String nome = viewRadice.getRadiceField();
		String descrizione = viewRadice.getDescrizioneField();
		String campo = viewRadice.getCampoField();
		NonFoglia r =  new NonFoglia(nome, campo, descrizione);
		for (String d : domini) {
			r.addDominio(d);
		}
		return r;
		
//		String nome;
//		do {
//			view.msgNomeRadiceGerarchia();
//			nome = InputDati.leggiStringaNonVuota("");
//		} while (gerarchieHandler.checkNomeRadiceGerarchia(nome));
//		
//		view.msgDescrizioneCategoria();
//		String descrizione = InputDati.leggiStringa("");
//		
//		view.msgNomeCampoGerarchia();
//		String campo = InputDati.leggiStringaNonVuota("");
//		
//		NonFoglia r =  new NonFoglia(nome, campo, descrizione);
//		
//		addDominiNonFoglia(r);
//		
//		return r;
	}
	
	 /**
     * Aggiunge una non foglia alla gerarchia, chiedendo all'Utente : nome,descrizione(facoltativa),campo
     * Il nome della Categoria(sia Foglia che NonFoglia) deve essere univoco all'interno della gerarchia 
     * 		{@link Gerarchia#checkNomeCategoria(String)}
     * @param parent Categoria padre a cui aggiungere la categoria NonFoglia
     * @since 1
     */
	private void addNonFoglia(NonFoglia parent) {
//		String nome;
//		do {
//			view.msgNomeNuovaCategoria();
//			nome = InputDati.leggiStringaNonVuota("");
//		} while (gerarchieHandler.getNewGerarchia().checkNomeCategoria(nome));
//		
//		view.msgDescrizioneCategoria();
//		String descrizione = InputDati.leggiStringa("");
//		
//		view.msgNomeCampoGerarchia();
//		String campo = InputDati.leggiStringaNonVuota("");
//		
//		NonFoglia n = new NonFoglia(nome, campo, descrizione);
//		addDominiNonFoglia(n);
//		
//		gerarchieHandler.getNewGerarchia().addCategoria(n);
//		parent.addChilds(n);
	}
	
	 /**
     * Aggiunge una foglia alla gerarchia, chiedendo all'Utente : nome,descrizione(facoltativa)
     * e delega l'inserimento dei fattori al metodo {@link #inserisciFattoriConversione(Foglia)}
     * Il nome della Categoria(sia Foglia che NonFoglia) deve essere univoco all'interno della gerarchia
     * 		{@link Gerarchia#checkNomeCategoria(String)}
     * @param parent Categoria padre a cui aggiungere la categoria Foglia
     * @since 1
     */
	private void addFoglia(NonFoglia parent) {
//		String nome;
//		do {
//			view.msgNomeNuovaCategoria();
//			nome = InputDati.leggiStringaNonVuota("");
//		} while (gerarchieHandler.getNewGerarchia().checkNomeCategoria(nome));//check nome foglia univoco nella gerarchia
//		
//		view.msgDescrizioneCategoria();
//		String descrizione = InputDati.leggiStringa("");
//		
//		Foglia fogliaNew = new Foglia(nome, descrizione,gerarchieHandler.getNewGerarchia().getNomeRadice());
//		gerarchieHandler.getNewGerarchia().addCategoria(fogliaNew);
//		
//		inserisciFattoriConversione(fogliaNew);
//		
//		gerarchieHandler.getNewGerarchia().addFoglia(fogliaNew);
//		parent.addChilds(fogliaNew);
	}
	
	/**
     * Permette l'inserimento di almeno un dominio all'parametro NonFoglia, 
     * per terminare l'inserimento e' richiesto all'utente di inserire il carattere '@'
     * le String dominio devono essere diverse dal carattere '@'
     * @param n Categoria NonFoglia a cui aggiungere i domini
     * @since 1
     */
	private void addDominiNonFoglia() {
		String dominio = viewRadice.getDominioDaAggiungere().trim();
        
		if (!dominio.isEmpty()&& !domini.contains(dominio) && !dominio.equals(viewRadice.getPlaceholderDominio())) {
			domini.add(dominio);
            viewRadice.aggiornaListaComuni(domini);
		}
		
//		String d = "";
//		view.msgInputDominiCampo(n);
//		do {
//			view.msgInputDominio();
//			d = InputDati.leggiStringaNonVuota("");
//			
//			if (!d.equals("@")) {
//	            n.addDominio(d);
//	        } else if (n.dominiIsEmpty()) {
//	        	view.msgInputDominiEmpty();
//	            d = "";
//	        }
//			
//		} while(!d.equals("@"));
	}
	
	/**
	 * Permette l'inserimento dei fattori di conversione a fogliaNew
	 * Se e' la prima foglia della nuova gerarchia e ne e' presente un altra gerarchia,
	 * 		richiesto fdc all'interno della gerarchia  {@link #sceltaFogliaNewGerarchia()}
	 * 
	 * Se e' la prima foglia della nuova gerarchia e ne e' presente almeno un altra gerarchia,
	 * 		richiesto fdc con una foglia di un'altra gerarchia {@link #sceltaRadiceFoglia()}
	 * 
	 * Se non e' la prima foglia della nuova gerarchia e ne e' presente almeno un altra gerarchia,
	 * 		l'utente puï¿½ scegliere verso quale foglia inserire il fdc (tra tutte le gerarchie)  {@link #sceltaAggiuntaFattori()}
	 * 
	 * I fdc devono essere compresi tra un min e un max definiti nel model per rispettare i requisiti 
	 * Delega a {@link Model#calcolaFattoriConversione(Foglia, Foglia, double)} il calcolo dei fdc darivabili 
	 * 
	 * @return se fogliaNew e' la prima foglia in assoluto delle Gerarchie presenti nel main 
	 * @param fogliaNew
	 * @since 1
	 */
	private void inserisciFattoriConversione(Foglia fogliaNew) {
//		Foglia fogliaOld = null;
//		
//		if(gerarchieHandler.getGerarchie().isEmpty() && gerarchieHandler.getNewGerarchia().foglieIsEmpty()) {
//			return;
//		}
//		else if(gerarchieHandler.getGerarchie().isEmpty() && !gerarchieHandler.getNewGerarchia().foglieIsEmpty()) {
//			fogliaOld = sceltaFogliaNewGerarchia();
//		}
//		else if(!gerarchieHandler.getGerarchie().isEmpty() && gerarchieHandler.getNewGerarchia().foglieIsEmpty()) {
//			fogliaOld = sceltaRadiceFoglia();
//		}
//		else if(!gerarchieHandler.getGerarchie().isEmpty() && !gerarchieHandler.getNewGerarchia().foglieIsEmpty()) {
//			fogliaOld = sceltaAggiuntaFattori();
//		}
//		else {
//			return;
//		}
//		
//		gerarchieHandler.calcolaFattoriMinMax(fogliaNew, fogliaOld);
//		double min = gerarchieHandler.getFattoreMin();
//		double max = gerarchieHandler.getFattoreMax();
//		
//		view.msgFattoreConversioneFoglie(fogliaNew, fogliaOld);
//		view.msgInserimentoFattoreConversione(min, max);
//		double fattore = InputDati.leggiDoubleConMinMax("", min, max);
//
//		gerarchieHandler.calcolaFattoriConversione(fogliaNew, fogliaOld, fattore);
	}
	
	/**
	 * Menu per la scelta di aggiungere il fdc della foglia appena creata con:
	 * 		- una foglia della gerarchia che si sta creando
	 * 		- una foglia di una gerarchia gia' presente
	 * @return la Foglia scelta rispetto cui aggiungere il fattore
	 * @since 1
	 */
//	private Foglia sceltaAggiuntaFattori() {
//	    int scelta = view.menuSceltaAggiuntaFattori().scegliNoExit();
//
//	    switch(scelta) {
//	    	case 1:
//	            return sceltaFogliaNewGerarchia();
//	    	case 2:
//	    		return sceltaRadiceFoglia();
//	    	default:
//	    		return null;
//	    }    
//	}
	
	/**
	 * Permette di selezionare una foglia tra tutte le foglie presenti nelle gerarchie nel model
	 * Prima permette di selezionare una radice e poi una foglia di tale radice
	 * @return foglia selezionata (richiesta al model)
	 * @since 1
	 */
//	private Foglia sceltaRadiceFoglia() {
//		ArrayList<String> nomiRadici = gerarchieHandler.getNomiRadici();
//		MyMenu menuRadici = view.menuSceltaRadice(nomiRadici);
//		int sceltaRadice = menuRadici.scegliNoExit();
//		
//		ArrayList<String> nomiFoglie = gerarchieHandler.getNomiFoglieGerarchia(sceltaRadice-1);
//		MyMenu menuFoglie = view.menuSceltaFoglia(nomiFoglie);
//		int sceltaFoglie = menuFoglie.scegliNoExit();
//		
//		return gerarchieHandler.getFogliaDaRadice(sceltaRadice-1, sceltaFoglie-1);
//	}
	
	/**
	 * Permette di selezionare una foglia all'interno della gerarchia che si sta creando 
	 * @return foglia selezionata (richiesta al model)
	 * @since 1
	 */
//	private Foglia sceltaFogliaNewGerarchia() {
//		ArrayList<String> nomiFoglie = gerarchieHandler.getNomiFoglieGerarchia(gerarchieHandler.getNewGerarchia());
//		MyMenu menuFoglie = view.menuSceltaFoglia(nomiFoglie);
//		int sceltaFoglie = menuFoglie.scegliNoExit();
//		
//		return gerarchieHandler.getNewGerarchia().getFoglia(sceltaFoglie-1);
//	}
	
	/**
	 * Permette di selezionare una foglia all'interno della gerarchia che si sta creando 
	 * e di visualizzarne i fattori di conversione
	 * @since 1
	 */ 
	public void sceltaFogliaFattori() {
		System.out.println("btnVisualizzaFattori é linked");
		
//		Foglia foglia = sceltaRadiceFoglia();
//		view.stampaFattoriDiCOnversioneFoglia(gerarchieHandler.getMapFattori(),foglia);
	}
	
	/**
	 * Permette di selezionare una foglia all'interno della gerarchia che si sta creando
	 * e di visualizzarne le proposte che richiedono o offrono la prestazione
	 * @since 4
	 */
	public Foglia sceltaFogliaScambi() {
//		return sceltaRadiceFoglia();
		return null;
	}
		
	/**
	 * Metodo per visualizzare le gerarchie presenti
	 * @since 4
	 */
	public void stampaGerarchie() {
		ViewVisualizzaGerarchie viewGerarchie = new ViewVisualizzaGerarchie(frame,gerarchieHandler.getGerarchie() );
		frame.getContentPane().add(viewGerarchie);
		viewGerarchie.setLayout(null);
		viewGerarchie.setBtnHomeListener(e-> backHome());
//		viewGerarchie.setLeafDoubleClickListener(e -> System.out.println(viewGerarchie.getCategoriaSelezionata().getNome()));
		
	}
	
}
