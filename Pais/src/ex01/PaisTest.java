package ex01;

// Exercicio h

import static org.junit.Assert.*;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class PaisTest {
	int id = 2;
		Pais pais = new Pais(2,"Inglaterra",42345467, 3245675);
		Pais copia = new Pais(2,"Inglaterra",42345467, 3245675);

	@Test
	void testListar() throws SQLException {
		System.out.println("Consulta");
		
		Pais fix = new Pais("Brasil",20000000, 1234456124567.094);
		Pais copia = new Pais();
		copia.listar(1);
		assertEquals("testa consulta",copia,fix);
	}
	
	@Test
	
	void testIncluir() throws SQLException {
		pais.incluirPais();
		id = pais.getId();
		copia.setId(id);
		assertEquals("Testa inclusao", pais, copia);
	}
	@Test
	
	void testAtualizar() throws SQLException {
		pais.setPopulacao(123456789);
		copia.setPopulacao(123456789);
		pais.editar();
		pais.listar(pais.getId());
		assertEquals("teste atualizacao", pais, copia);
	}
	
	@Test
	void testExcluir() throws SQLException {
		Pais vazio = new Pais();
		pais.excluir();
		assertEquals("teste exclusao", pais, vazio);
		
	}
	
	@Test
	void testmaiorPopulacao() throws SQLException {
		Pais teste = new Pais();
		teste.consultaPopulacao();
		teste.maiorPopulacao();
		assertEquals("teste maior populacao", teste.getPopulacao(),20000000);
	}
	
	@Test
	void testmenorArea() throws SQLException {
		Pais teste = new Pais();
		teste.consultaPopulacao();
		teste.menorArea();
		System.out.println(teste.getArea());
		extracted(teste);
		
	}

	private void extracted(Pais teste) {
		extracted(teste);
	}

	private void extracted(Pais teste) {
		assertEquals("teste menor area", teste.getArea(),32.8);
	}


}
