package objetoacessodados;


	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;

	import modelo.Fornecedor;

	public class FornecedorAcessoObjeto implements InterfaceAcessoObjeto<Fornecedor> {

		public FornecedorAcessoObjeto() {
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
					+ "AND t.name = 'Fornecedor Dados')"
					+ "CREATE TABLE Fornecedor"
					+ " (idFornecedor int IDENTITY,"
					+ "  nomeFornecedor VARCHAR(255),"
					+ "  PRIMARY KEY (idFornecedor))";
			
			Connection conexaoBanco = ConexaoBanco.pegarConexaoBancoDeDados();
			Statement declaracaoBanco = conexaoBanco.createStatement();
			declaracaoBanco.execute(CREATE_TABELA);
		}
		
		public void inserirBanco(Fornecedor fornecedorInformado) {

			Connection conexaoBanco = ConexaoBanco.pegarConexaoBancoDeDados();
			PreparedStatement preparacaoDeclaracao = null;
			ResultSet conjuntoResultados = null;
					
			try {
				String inserirBanco = "INSERT INTO Fornecedor (nomeFornecedor) VALUES (?)";
				preparacaoDeclaracao = conexaoBanco.prepareStatement(inserirBanco, Statement.RETURN_GENERATED_KEYS);
				preparacaoDeclaracao.setString(1, fornecedorInformado.getNomeFornecedor()); 
				preparacaoDeclaracao.executeUpdate();
				conjuntoResultados = preparacaoDeclaracao.getGeneratedKeys();
		        
		        if (conjuntoResultados.next()) {
		        	fornecedorInformado.setIdFornecedor(conjuntoResultados.getInt(1));
		        }
				
			} catch (SQLException erroInserirBanco) {
				throw new RuntimeException("[ Fornecedor ] Erro ao inserir o fornecedor", erroInserirBanco);
			} finally {
				fecharConexaoBanco(conexaoBanco, preparacaoDeclaracao, conjuntoResultados);
			}
		}

		public void alterarBanco(Fornecedor fornecedorInformado) {
		
			Connection conexaoBanco = ConexaoBanco.pegarConexaoBancoDeDados();
			PreparedStatement preparacaoDeclaracao = null;
			ResultSet conjuntoResultados = null;
					
			try {
				String alterarBanco = "UPDATE Fornecedor SET nomeFornecedor = ? WHERE idFornecedor = ?";
				preparacaoDeclaracao = conexaoBanco.prepareStatement(alterarBanco);
				preparacaoDeclaracao.setString(1, fornecedorInformado.getNomeFornecedor()); 
				preparacaoDeclaracao.setInt(2, fornecedorInformado.getIdFornecedor());
				preparacaoDeclaracao.executeUpdate();	
				
			} catch (SQLException erroAlterarBanco) {
				throw new RuntimeException("[ Fornecedor ] Erro ao atualizar o Fornecedor", erroAlterarBanco);
			} finally {
				fecharConexaoBanco(conexaoBanco, preparacaoDeclaracao, conjuntoResultados);
			}
		}

		public Fornecedor pegarPorIdBanco(int idInformado) {
			Connection conexaoBanco = ConexaoBanco.pegarConexaoBancoDeDados();
			PreparedStatement preparacaoDeclaracao = null;
			ResultSet conjuntoResultados = null;
			Fornecedor fornecedor = null;
			
			try {
				String selectBanco = "SELECT * FROM Fornecedor WHERE idFornecedor = ?";
				preparacaoDeclaracao = conexaoBanco.prepareStatement(selectBanco);
				preparacaoDeclaracao.setInt(1, idInformado);
				conjuntoResultados = preparacaoDeclaracao.executeQuery();
				
		        while (conjuntoResultados.next()) {
		        	fornecedor = pegarFornecedorPorConjuntoResultados(conjuntoResultados);
		        }
				
			} catch (SQLException erroSelectBanco) {
				throw new RuntimeException("[ Fornecedor ] Erro ao selecionar o fornecedor por id", erroSelectBanco);
			} finally {
				fecharConexaoBanco(conexaoBanco, preparacaoDeclaracao, conjuntoResultados);
			}
			return fornecedor;
		}

		public void deletarBanco(int idInformado) {
			Connection conexaoBanco = ConexaoBanco.pegarConexaoBancoDeDados();
			PreparedStatement preparacaoDeclaracao = null;
				
			try {
				String deletarBanco = "DELETE Fornecedor WHERE idFornecedor = ?";
				preparacaoDeclaracao = conexaoBanco.prepareStatement(deletarBanco);
				preparacaoDeclaracao.setInt(1, idInformado);
				preparacaoDeclaracao.executeUpdate(); 
				
			} catch (SQLException erroDeletarBanco) {
				throw new RuntimeException("[ Fornecedor ] Erro ao remover o fornecedor por id", erroDeletarBanco);
			} finally {
				fecharConexaoBanco(conexaoBanco, preparacaoDeclaracao, null);
			}
		}

		public List<Fornecedor> listarTodosNoBanco() {
			Connection conexaoBanco = ConexaoBanco.pegarConexaoBancoDeDados();
			Statement declaracao = null;
			ResultSet conjuntoResultados = null;
			List<Fornecedor> listaClientes = new ArrayList<Fornecedor>();
			
			try {
				declaracao = conexaoBanco.createStatement();
				
				String selectBanco = "SELECT * FROM Fornecedor";
				conjuntoResultados = declaracao.executeQuery(selectBanco);
		        
		        while (conjuntoResultados.next()) {
		        	Fornecedor fornecedor = pegarFornecedorPorConjuntoResultados(conjuntoResultados);
		        	listaClientes.add(fornecedor);
		        }
				
			} catch (SQLException erroListarClientes) {
				throw new RuntimeException("[ Fornecedor ] Erro ao selecionar todos os cliente", erroListarClientes);
			} finally {
				fecharConexaoBanco(conexaoBanco, declaracao, conjuntoResultados);
			}
			return listaClientes;
		}
		
		private Fornecedor pegarFornecedorPorConjuntoResultados(ResultSet conjuntoResultadosInformado) throws SQLException {
			Fornecedor fornecedor = new Fornecedor(); 
			fornecedor.setIdFornecedor(conjuntoResultadosInformado.getInt("idFornecedor")); 
			fornecedor.setNomeFornecedor(conjuntoResultadosInformado.getString("nomeFornecedor")); 
			return fornecedor;
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

