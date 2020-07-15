package acessoDadosObjeto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;

public class ClienteAcessoObjeto implements InterfaceAcessoDadosObjeto<Cliente> {

	public ClienteAcessoObjeto() {
		try {
			criarTabelaBanco();
		} catch (SQLException erroCriarTabela) {
			throw new RuntimeException("[ Cliente ] - Erro ao criar tabela");
		}
	}
	
	private void criarTabelaBanco() throws SQLException {
		final String CREATE_TABELA = "IF NOT EXISTS (" 
				+ "SELECT * FROM sys.tables t JOIN sys.schemas s ON " 
				+ "(t.schema_id = s.schema_id) WHERE s.name = 'acessoDadosObjeto'" 
				+ "AND t.name = 'Cliente Dados')"
				+ "CREATE TABLE Cliente"
				+ " (idCliente int IDENTITY,"
				+ "  nomeCliente VARCHAR(255),"
				+ "  PRIMARY KEY (idCliente))";
		
		Connection conexaoBanco = ConexaoBanco.pegarConexaoBancoDeDados();
		Statement declaracaoBanco = conexaoBanco.createStatement();
		declaracaoBanco.execute(CREATE_TABELA);
	}
	
	public void inserirBanco(Cliente clienteInformado) {

		Connection conexaoBanco = ConexaoBanco.pegarConexaoBancoDeDados();
		PreparedStatement preparacaoDeclaracao = null;
		ResultSet conjuntoResultados = null;
				
		try {
			String inserirBanco = "INSERT INTO Cliente (nomeCliente) VALUES (?)";
			preparacaoDeclaracao = conexaoBanco.prepareStatement(inserirBanco, Statement.RETURN_GENERATED_KEYS);
			preparacaoDeclaracao.setString(1, clienteInformado.getNomeCliente()); 
	    	
			preparacaoDeclaracao.executeUpdate();
	        
			conjuntoResultados = preparacaoDeclaracao.getGeneratedKeys();
	        
	        if (conjuntoResultados.next()) {
	        	clienteInformado.setIdCliente(conjuntoResultados.getInt(1));
	        }
			
		} catch (SQLException erroInserirBanco) {
			throw new RuntimeException("[ Cliente ] Erro ao inserir o cliente", erroInserirBanco);
		} finally {
			fecharConexaoBanco(conexaoBanco, preparacaoDeclaracao, conjuntoResultados);
		}
	}

	public void alterarBanco(Cliente clienteInformado) {
	
		Connection conexaoBanco = ConexaoBanco.pegarConexaoBancoDeDados();
		PreparedStatement preparacaoDeclaracao = null;
		ResultSet conjuntoResultados = null;
				
		try {
			String alterarBanco = "UPDATE Cliente SET nomeCliente = ? WHERE idCliente = ?";
			preparacaoDeclaracao = conexaoBanco.prepareStatement(alterarBanco);
			preparacaoDeclaracao.setString(1, clienteInformado.getNomeCliente()); 
			preparacaoDeclaracao.setInt(2, clienteInformado.getIdCliente());
			preparacaoDeclaracao.executeUpdate();	
			
		} catch (SQLException erroAlterarBanco) {
			throw new RuntimeException("[ Cliente ] Erro ao atualizar o cliente", erroAlterarBanco);
		} finally {
			fecharConexaoBanco(conexaoBanco, preparacaoDeclaracao, conjuntoResultados);
		}
	}

	public Cliente pegarPorIdBanco(int idInformado) {
		Connection conexaoBanco = ConexaoBanco.pegarConexaoBancoDeDados();
		PreparedStatement preparacaoDeclaracao = null;
		ResultSet conjuntoResultados = null;
		Cliente cliente = null;
		
		try {
			String selectBanco = "SELECT * FROM Cliente WHERE idCliente = ?";
			preparacaoDeclaracao = conexaoBanco.prepareStatement(selectBanco);
			preparacaoDeclaracao.setInt(1, idInformado);
			conjuntoResultados = preparacaoDeclaracao.executeQuery();
			
	        while (conjuntoResultados.next()) {
	        	cliente = pegarClientePorConjuntoResultados(conjuntoResultados);
	        }
			
		} catch (SQLException erroSelectBanco) {
			throw new RuntimeException("[ Cliente ] Erro ao selecionar o cliente por id", erroSelectBanco);
		} finally {
			fecharConexaoBanco(conexaoBanco, preparacaoDeclaracao, conjuntoResultados);
		}
		return cliente;
	}

	public void deletarBanco(int idInformado) {
		Connection conexaoBanco = ConexaoBanco.pegarConexaoBancoDeDados();
		PreparedStatement preparacaoDeclaracao = null;
			
		try {
			String deletarBanco = "DELETE Cliente WHERE idCliente = ?";
			preparacaoDeclaracao = conexaoBanco.prepareStatement(deletarBanco);
			preparacaoDeclaracao.setInt(1, idInformado);
			preparacaoDeclaracao.executeUpdate(); 
			
		} catch (SQLException erroDeletarBanco) {
			throw new RuntimeException("[ Cliente ] Erro ao remover o cliente por id", erroDeletarBanco);
		} finally {
			fecharConexaoBanco(conexaoBanco, preparacaoDeclaracao, null);
		}
	}

	public List<Cliente> listarTodosNoBanco() {
		Connection conexaoBanco = ConexaoBanco.pegarConexaoBancoDeDados();
		Statement declaracao = null;
		ResultSet conjuntoResultados = null;
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		
		try {
			declaracao = conexaoBanco.createStatement();
			
			String selectBanco = "SELECT * FROM Cliente";
			conjuntoResultados = declaracao.executeQuery(selectBanco);
	        
	        while (conjuntoResultados.next()) {
	        	Cliente cliente = pegarClientePorConjuntoResultados(conjuntoResultados);
	        	listaClientes.add(cliente);
	        }
			
		} catch (SQLException erroListarClientes) {
			throw new RuntimeException("[ Cliente ] Erro ao selecionar todos os cliente!", erroListarClientes);
		} finally {
			fecharConexaoBanco(conexaoBanco, declaracao, conjuntoResultados);
		}
		return listaClientes;
	}
	
	private Cliente pegarClientePorConjuntoResultados(ResultSet conjuntoResultadosInformado) throws SQLException {
		Cliente cliente = new Cliente(); 
		cliente.setIdCliente(conjuntoResultadosInformado.getInt("idCliente")); 
		cliente.setNomeCliente(conjuntoResultadosInformado.getString("nomeCliente")); 
		
		return cliente;
	}
	
	private void fecharConexaoBanco(Connection conexaoBancoInformado, Statement preparacaoDeclaracaoInformado, ResultSet conjuntoResultadosInformado) {
		try {
			if (conjuntoResultadosInformado != null) { conjuntoResultadosInformado.close(); }
			if (preparacaoDeclaracaoInformado != null) { preparacaoDeclaracaoInformado.close(); }
			if (conexaoBancoInformado != null) { conexaoBancoInformado.close(); }
		} catch (SQLException erroFecharConexao) {
			throw new RuntimeException("Erro ao fechar recursos.", erroFecharConexao);
		}
	}
}