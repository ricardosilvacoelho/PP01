package ex01;
import java.sql.Connection;
import java.sql.SQLException;

public class MainTeste {

	public static void main(String[] args) throws SQLException {
		Connection conexao=null;
		Pais p;
		
		ConexaoBancoDeDados db = new ConexaoBancoDeDados();
		conexao= db.conectar();
		
		p = new Pais("Inglaterra", 42345467, 3245675);
		p.incluirPais();
		
		p = new Pais();
		p.consultaPopulacao();
		p.maiorPopulacao();
		System.out.println("***");
		
		p.menorArea();
		System.out.println("***");
		p.vetorTresPaises();
		
		Pais pais = new Pais();
		pais.listar(1);

	}

}
