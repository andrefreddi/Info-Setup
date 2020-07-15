package acessoDadosObjeto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

	// Criação do login no SQL Server
	// login: infoBd
	// senha : 12345
	
	private static final String DATABASE_CONNECTION_URL = "jdbc:sqlserver://localhost;databaseName=tech_informatica;";
	private static final String USER = "infoBd";
	private static final String PASSWORD = "12345";
	private static Connection conexaoBancoDeDados = null;
	
	// ------------------------------------------------
	// Connection into database
	// ------------------------------------------------
	public static Connection pegarConexaoBancoDeDados() {
		
		try {
			
			if( conexaoBancoDeDados == null || conexaoBancoDeDados.isClosed() ) {
				conexaoBancoDeDados = DriverManager.getConnection(DATABASE_CONNECTION_URL, USER, PASSWORD);
			}
			
			System.out.println("(Banco de Dados) - Conectado banco de dados!");
			
		} catch( SQLException erroConexaoBanco ) {
			throw new RuntimeException("(Banco de Dados) - Ops, ocorreu um erro ao se conectar ao banco!");
		}
		return conexaoBancoDeDados;
	}
	// ------------------------------------------------
	
}