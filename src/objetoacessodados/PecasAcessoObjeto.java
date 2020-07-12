package objetoacessodados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Pecas;

public class PecasAcessoObjeto implements InterfaceAcessoObjeto<Pecas> {

	public PecasAcessoObjeto() {
		try {
			criarTabelaBanco();
		} catch (SQLException erroCriarTabela) {
			throw new RuntimeException("[ Fornecedor ] - Erro ao criar tabela");
		}
	}
	
	private void criarTabelaBanco() throws SQLException {
		final String CREATE_TABELA = "IF NOT EXISTS (" 
				+ "SELECT * FROM sys.tables t JOIN sys.schemas s ON " 
				+ "(t.schema_id = s.schema_id) WHERE s.name = 'acessoDadosObjeto'" 
				+ "AND t.name = 'Pecas Dados')"
				+ "CREATE TABLE Pecas"
				+ " (idPeca int IDENTITY,"
				+ "  nomePeca VARCHAR(255),"
				+ "  PRIMARY KEY (idPeca))";
		
		Connection conexaoBanco = ConexaoBanco.pegarConexaoBancoDeDados();
		Statement declaracaoBanco = conexaoBanco.createStatement();
		declaracaoBanco.execute(CREATE_TABELA);
	}
	
	public void inserirBanco(Pecas pecasInformado) {

		Connection conexaoBanco = ConexaoBanco.pegarConexaoBancoDeDados();
		PreparedStatement preparacaoDeclaracao = null;
		ResultSet conjuntoResultados = null;
				
		try {
			String inserirBanco = "INSERT INTO Pecas (nomePeca) VALUES (?)";
			preparacaoDeclaracao = conexaoBanco.prepareStatement(inserirBanco, Statement.RETURN_GENERATED_KEYS);
			preparacaoDeclaracao.setString(1, pecasInformado.getNomePecas()); 
			preparacaoDeclaracao.executeUpdate();
			conjuntoResultados = preparacaoDeclaracao.getGeneratedKeys();
	        
	        if (conjuntoResultados.next()) {
	        	pecasInformado.setIdPecas(conjuntoResultados.getInt(1));
	        }
			
		} catch (SQLException erroInserirBanco) {
			throw new RuntimeException("[ Pe�as ] Erro ao inserir o Pe�as", erroInserirBanco);
		} finally {
			fecharConexaoBanco(conexaoBanco, preparacaoDeclaracao, conjuntoResultados);
		}
	}

	public void alterarBanco(Pecas pecasInformado) {
	
		Connection conexaoBanco = ConexaoBanco.pegarConexaoBancoDeDados();
		PreparedStatement preparacaoDeclaracao = null;
		ResultSet conjuntoResultados = null;
				
		try {
			String alterarBanco = "UPDATE Pecas SET nomePeca = ? WHERE idPeca = ?";
			preparacaoDeclaracao = conexaoBanco.prepareStatement(alterarBanco);
			preparacaoDeclaracao.setString(1, pecasInformado.getNomePecas()); 
			preparacaoDeclaracao.setInt(2, pecasInformado.getIdPecas());
			preparacaoDeclaracao.executeUpdate();	
			
		} catch (SQLException erroAlterarBanco) {
			throw new RuntimeException("[ Pe�as ] Erro ao atualizar o pe�as", erroAlterarBanco);
		} finally {
			fecharConexaoBanco(conexaoBanco, preparacaoDeclaracao, conjuntoResultados);
		}
	}

	public Pecas pegarPorIdBanco(int idInformado) {
		Connection conexaoBanco = ConexaoBanco.pegarConexaoBancoDeDados();
		PreparedStatement preparacaoDeclaracao = null;
		ResultSet conjuntoResultados = null;
		Pecas pecas = null;
		
		try {
			String selectBanco = "SELECT * FROM Pecas WHERE idPeca = ?";
			preparacaoDeclaracao = conexaoBanco.prepareStatement(selectBanco);
			preparacaoDeclaracao.setInt(1, idInformado);
			conjuntoResultados = preparacaoDeclaracao.executeQuery();
			
	        while (conjuntoResultados.next()) {
	        	pecas = pegarPecasPorConjuntoResultados(conjuntoResultados);
	        }
			
		} catch (SQLException erroSelectBanco) {
			throw new RuntimeException("[ Pe�as ] Erro ao selecionar o pe�as por id", erroSelectBanco);
		} finally {
			fecharConexaoBanco(conexaoBanco, preparacaoDeclaracao, conjuntoResultados);
		}
		return pecas;
	}

	public void deletarBanco(int idInformado) {
		Connection conexaoBanco = ConexaoBanco.pegarConexaoBancoDeDados();
		PreparedStatement preparacaoDeclaracao = null;
			
		try {
			String deletarBanco = "DELETE Pecas WHERE idPeca = ?";
			preparacaoDeclaracao = conexaoBanco.prepareStatement(deletarBanco);
			preparacaoDeclaracao.setInt(1, idInformado);
			preparacaoDeclaracao.executeUpdate(); 
			
		} catch (SQLException erroDeletarBanco) {
			throw new RuntimeException("[ Pe�as ] Erro ao remover o pe�as por id", erroDeletarBanco);
		} finally {
			fecharConexaoBanco(conexaoBanco, preparacaoDeclaracao, null);
		}
	}

	public List<Pecas> listarTodosNoBanco() {
		Connection conexaoBanco = ConexaoBanco.pegarConexaoBancoDeDados();
		Statement declaracao = null;
		ResultSet conjuntoResultados = null;
		List<Pecas> listaPecas = new ArrayList<Pecas>();
		
		try {
			declaracao = conexaoBanco.createStatement();
			
			String selectBanco = "SELECT * FROM Pecas";
			conjuntoResultados = declaracao.executeQuery(selectBanco);
	        
	        while (conjuntoResultados.next()) {
	        	Pecas pecas = pegarPecasPorConjuntoResultados(conjuntoResultados);
	        	listaPecas.add(pecas);
	        }
			
		} catch (SQLException erroListarClientes) {
			throw new RuntimeException("[ Pe�as ] Erro ao selecionar todos os pe�as", erroListarClientes);
		} finally {
			fecharConexaoBanco(conexaoBanco, declaracao, conjuntoResultados);
		}
		return listaPecas;
	}
	
	private Pecas pegarPecasPorConjuntoResultados(ResultSet conjuntoResultadosInformado) throws SQLException {
		Pecas pecas = new Pecas(); 
		pecas.setIdPecas(conjuntoResultadosInformado.getInt("idPeca")); 
		pecas.setNomePecas(conjuntoResultadosInformado.getString("nomePeca")); 
		return pecas;
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
