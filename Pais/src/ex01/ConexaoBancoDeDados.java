package ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBancoDeDados {

		static {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			}
			catch(ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
		
		public static Connection conectar() throws SQLException {
		    String local = "localhost";
			String porta = "3306";
			String database = "pais";
			String usuario = "root";
			String senha = "root";
			return DriverManager.getConnection("jdbc:mysql://"+local+":"+porta+"/"+database+"?useSSL=true&useTimezone=true&serverTimezone=UTC", usuario, senha);
		
		}
		
		public static void desconectar(Connection conexao) throws SQLException {
			conexao.close();
		}
}
