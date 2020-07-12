package objetoacessodados;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {


    private static final String DATABASE_CONNECTION_URL = "jdbc:sqlserver://localhost;databaseName=techinfo;";
    private static final String USER = "n3bd";
    private static final String PASSWORD = "12345";
    private static Connection conexaoBancoDados = null;

    // ------------------------------------------------
    // Connection into database
    // ------------------------------------------------
    public static Connection pegarConexaoBancoDeDados() {

        try {

            if( conexaoBancoDados == null || conexaoBancoDados.isClosed() ) {
                conexaoBancoDados = DriverManager.getConnection(DATABASE_CONNECTION_URL, USER, PASSWORD);
            }
            
            System.out.println("Tudo conectado meu amigo");

        } catch( SQLException erroConexaoBanco ) {
            throw new RuntimeException("(Banco de Dados) - Erro404 ao conectar!");
        }
        return conexaoBancoDados;
    }
}
    // ------------------------------------------------
