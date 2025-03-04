package it.unibs.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibs.domain.Comprensorio;
import it.unibs.main.Persistence;
import it.unibs.model.ModelComprensori;
import it.unibs.view.console.ViewConfiguratore;

class TestComprensorio {

	@Test
	void testAddComprensorio() {
		List<String> listaComuni = new ArrayList<>();
		listaComuni.add("Brandico");
		Comprensorio c = new Comprensorio("Bassa", listaComuni);
		ModelComprensori model = new ModelComprensori(new Persistence());
		model.addComprensorio(c);
		assertEquals(model.getComprensori().size(), 1);
	}
	@Test
	void testAddComprensorioNomeUnivoco() {
		List<String> listaComuni = new ArrayList<>();
		listaComuni.add("Brandico");
		Comprensorio c = new Comprensorio("Bassa", listaComuni);
		ModelComprensori model = new ModelComprensori(new Persistence());
		model.addComprensorio(c);
		assertTrue(model.checkNomeComprensorio("Bassa"));
	}
	
	@Test
	void testStampaComprensorio() {
		List<String> listaComuni = new ArrayList<>();
		listaComuni.add("Brandico");
		Comprensorio c = new Comprensorio("Bassa Bresciana", listaComuni);
		
		ModelComprensori modelC = new ModelComprensori(new Persistence());
		modelC.addComprensorio(c);
		
		ViewConfiguratore vc = new ViewConfiguratore();
		
		String output = "Comprensorio Bassa Bresciana = [Brandico, ]";
		assertEquals(vc.stampaComprensorio(c).toString(), output);
	}
	
}
