package ex01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Pais {
	
	int id;
	String nome;
	long populacao;
	double area;
	
	ArrayList<Pais> lista = new ArrayList<>();
	Connection conexao = null;
	ConexaoBancoDeDados bd = new ConexaoBancoDeDados();
	
	public Pais() {
		
	}
	public Pais(long populacao) {
		this.populacao = populacao;
	}
	
	//exercicio b	
	public Pais(int id, String nome, int populacao, double area) {
		this.id = id;
		this.nome = nome;
		this.populacao = populacao;
		this.area = area;
	}
	
	public Pais( String nome, int populacao, double area) {
		this.nome = nome;
		this.populacao = populacao;
		this.area = area;
	}
	
	//exercício c
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getNomeDoPais() {
		return nome;
	}
	public void getNomeDoPais(String nome) {
		this.nome = nome;
	}
	public long getPopulacao() {
		return populacao;
	}
	public void setPopulacao(long populacao) {
		this.populacao = populacao;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	
	//exercício d
	public void incluirPais() throws SQLException {
		Connection conn = bd.conectar();
		String query = "INSERT INTO pais(nome,populacao,area) VALUES (?,?,?)";
		
		try (PreparedStatement stm = conn.prepareStatement(query);){
			stm.setString(1, getNomeDoPais());
			stm.setLong(2, getPopulacao());
			stm.setDouble(3, getArea());
			stm.execute();
		}
		catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			try {
				conn.rollback();
			}
			catch (SQLException e1) {
				System.out.println("Erro: " + e.getMessage());
			}
		}
	}
	
	public void excluir() throws SQLException {
		
		Connection conn = bd.conectar();
		String sqlDelete = "DELETE FROM pais Where id = ?";
		try (PreparedStatement stm = conn.prepareStatement(sqlDelete);){
			stm.setInt(1, getId());
			stm.execute();
		}
		catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			try {
				conn.rollback();
			}
			catch (SQLException e1) {
				System.out.println("Erro: " + e.getMessage());
			}
		}
	}
	
	public void editar() throws SQLException {
		Connection conn = bd.conectar();
		String sqlUpdate = "UPDATE pais SET populacao= ?, area=? WHERE id=? ";
		try(PreparedStatement stm = conn.prepareStatement(sqlUpdate);){
			stm.setLong(1, getPopulacao());
			stm.setDouble(2, getArea());
			stm.setInt(3, getId());
			stm.execute();
		}
		catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			}
			catch (SQLException e1) {
				System.out.println("Erro: " + e.getMessage());
			}
		}
	}
	
	public ArrayList<Pais> consultaPopulacao () throws SQLException {
		Connection conn = bd.conectar();
		String consulta = "select id,nome,populacao,area from pais";
		
		
		try (PreparedStatement stm = conn.prepareStatement(consulta); ResultSet rs = stm.executeQuery();){
			while(rs.next()) {
				Pais p = new Pais();
				p.setId(rs.getInt("id"));
				p.setPopulacao(rs.getLong("populacao"));
				p.getNomeDoPais(rs.getString("nome"));
				p.setArea(rs.getDouble("area"));
				lista.add(p);			
			}
		}
		catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		return lista;
	}
	
	public void listar(int p) throws SQLException {
		
		String carrega = "SELECT nome,populacao,area FROM pais WHERE id=?";
		ConexaoBancoDeDados db = new ConexaoBancoDeDados();
		
		try(Connection conn = db.conectar(); PreparedStatement stm = conn.prepareStatement(carrega);){
			stm.setInt(1, p);
			try (ResultSet rs = stm.executeQuery();){
				while(rs.next())	{
					getNomeDoPais(rs.getString("nome"));
					setPopulacao(rs.getLong("populacao"));
					setArea(rs.getDouble("area"));
				}
			}
			
		}
		catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
	//Exercício e
	public void maiorPopulacao() throws SQLException {
		long popula = 0;
		String nome = null;
		for(Pais populacao : lista) {
			if(popula < populacao.getPopulacao()) {
				popula = populacao.getPopulacao();
				nome = populacao.getNomeDoPais();
				this.getNomeDoPais(nome);
				this.setPopulacao(popula);		
			}
		}
		System.out.println("Pais: " + nome + ", Popupacao: " + popula);
	}
	
	//Exercício f
	public void menorArea()  {
		String nomeDoPais = null; 
		double area = 0;
		
		for(Pais pais : lista) {	
			if(area == 0 || area > pais.getArea()) {
				area = pais.getArea(); nomeDoPais = pais.getNomeDoPais();
			this.getNomeDoPais(nomeDoPais);;
			this.setArea(area);
			}
		}
		System.out.println("Pais: " + nomeDoPais + ", Area: " + area);
	}
	
	//Exercício g
	public void vetorTresPaises() {
		int i = 0;
		for( i = 0 ; i <= 2; i++) {
			System.out.println("Pais:" + lista.get(i).getNomeDoPais());
		}
	}
	
	// exercicio a
	//Segue o script do banco de dados
/*
	 create table pais(
		id int not null auto_increment,
		nome varchar(100) null,
		populacao bigint(20) null,
		area decimal(15,2) null,
	primary key(id)
	);
*/
	
	//Inclusão de valores
/*
	 INSERT INTO pais
	(nome, populacao, area)
    values
    ('Brasil', 20000000, 1234456124567.094);
*/
	 
	
}
