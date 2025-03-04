package it.unibs.view.console;

import it.unibs.mylib.MyMenu;

public class ViewAccesso extends View {
public final static String[] SCELTE_ACCESSO= new String[]{
			"Configuratore",
			"Nuovo Fruitore",
			"Fruitore Esistente"};
	private final static MyMenu MENU_SCELTE_ACCESSO = new MyMenu("Scegliere accesso:", SCELTE_ACCESSO);

	public ViewAccesso() {
		super();
	}
	
	public void stampaMenuRun() {
		MENU_SCELTE_ACCESSO.stampaMenu();
	}
	
	public MyMenu getMenuRun() {
		return MENU_SCELTE_ACCESSO;
	}
	
	public void msgInserisciNome() {
		System.out.println("Inserisci Nome: ");
	}
	
	public void msgInserisciPsw() {
		System.out.println("Inserisci Password: ");
	}

	public void msgNuoveCredenziali() {
		System.out.println("Inserire nuove  credenziali");
	}

	public void msgConfermaNewConfiguratore() {
		System.out.println("CREDENZIALI CAMBIATE E ACCESSO EFFETTUATO");		
	}
	
	public void msgAccessoEffettuato() {
		System.out.println("ACCESSO EFFETTUATO CON SUCCESSO");	
	}

	public void msgCredenzialiErrate() {
		System.out.println("CREDENZIALI ERRATE");
	}
	
	public void msgNomeUnivoco() {
		System.out.println("Nome gia' in uso, inserisci un nome univoco:");
	}

	public void msgInserisciMail() {
		System.out.println("Inserisci indirizzo di posta elettronica: ");
	}

	public void msgAccessoNuovoFruitore() {
		System.out.println("ACCOUNT CREATO E ACCESSO EFFETTUATO");
	}

	public MyMenu menuSceltaComprensorio(String[] nomiComprenosori) {
		MyMenu menuSceltaComp = new MyMenu("Scelta comprensorio di appartenenza:", nomiComprenosori);
		menuSceltaComp.stampaMenuNoExit();
		return menuSceltaComp;
	}
	
}