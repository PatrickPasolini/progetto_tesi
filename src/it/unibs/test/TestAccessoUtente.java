package it.unibs.test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.Test;

import it.unibs.domain.*;
import it.unibs.main.Persistence;
import it.unibs.main.PersistenceLogin;
import it.unibs.model.ModelAccesso;

class TestAccessoUtente {

	@Test
	void testPrimoAccessoConfiguratore() {
		String nome = "a";
		String password = "a";
		ModelAccesso modelAccesso = new ModelAccesso(new Persistence(), new PersistenceLogin());
		assertTrue(modelAccesso.controllaDefault(nome,password));
	}

	@Test
	void testAccessoConfiguratore() {
		String nome = "mario";
		String password = "rossi";
		Configuratore configuratore = new Configuratore(nome, password);
		HashMap<String, Utente> listUtenti = new HashMap<>();
		listUtenti.put(nome, configuratore);
		
		PersistenceLogin persistenceLogin = new PersistenceLogin();
		persistenceLogin.setListUtenti(listUtenti);
		ModelAccesso modelAccesso = new ModelAccesso(new Persistence(), persistenceLogin);
		
		assertTrue(modelAccesso.controllaAccessoConfiguratore(nome,password));
	}
	
	@Test
	void testAccessoFruitore() {
		String nome = "mario";
		String password = "rossi";
		List<String> listaComuni = new ArrayList<>();
		Fruitore fruitore = new Fruitore(nome, password,"mail@mail", new Comprensorio("c",listaComuni));
		HashMap<String, Utente> listUtenti = new HashMap<>();
		listUtenti.put(nome, fruitore);
		
		PersistenceLogin persistenceLogin = new PersistenceLogin();
		persistenceLogin.setListUtenti(listUtenti);
		ModelAccesso modelAccesso = new ModelAccesso(new Persistence(), persistenceLogin);
		
		assertTrue(modelAccesso.controllaAccessoFruitore(nome,password));
	}
	
	
}
