package objetoacessodados;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;

	import modelo.setup;

	public class SetupAcessoObjeto implements InterfaceAcessoObjeto<setup> {

		public SetupAcessoObjeto() {
			try {
				criarTabelaBanco();
			} catch (SQLException erroCriarTabela) {
				throw new RuntimeException("[ setup ] - Erro ao criar tabela");
			}
		}
		
		private void criarTabelaBanco() throws SQLException {
			final String CREATE_TABELA = "IF NOT EXISTS (" 
					+ "SELECT * FROM sys.tables t JOIN sys.schemas s ON " 
					+ "(t.schema_id = s.schema_id) WHERE s.name = 'acessoDadosObjeto'" 
					+ "AND t.name = 'Cliente Dados')"
					+ "CREATE TABLE Cliente"
					+ " (idSetup int IDENTITY,"
					+ "  nomeSetup VARCHAR(255),"
					+ "  PRIMARY KEY (idSetup))";
			
			Connection conexaoBanco = ConexaoBanco.pegarConexaoBancoDeDados();
			Statement declaracaoBanco = conexaoBanco.createStatement();
			declaracaoBanco.execute(CREATE_TABELA);
		}
		
		public void inserirBanco(setup setupInformado) {

			Connection conexaoBanco = ConexaoBanco.pegarConexaoBancoDeDados();
			PreparedStatement preparacaoDeclaracao = null;
			ResultSet conjuntoResultados = null;
					
			try {
				String inserirBanco = "INSERT INTO Setup (nomeSetup) VALUES (?)";
				preparacaoDeclaracao = conexaoBanco.prepareStatement(inserirBanco, Statement.RETURN_GENERATED_KEYS);
				preparacaoDeclaracao.setString(1, setupInformado.getNomeSetup()); 
		    	
				preparacaoDeclaracao.executeUpdate();
		        
				conjuntoResultados = preparacaoDeclaracao.getGeneratedKeys();
		        
		        if (conjuntoResultados.next()) {
		        	setupInformado.setIdSetup(conjuntoResultados.getInt(1));
		        }
				
			} catch (SQLException erroInserirBanco) {
				throw new RuntimeException("[ Setup ] Erro ao inserir o setup", erroInserirBanco);
			} finally {
				fecharConexaoBanco(conexaoBanco, preparacaoDeclaracao, conjuntoResultados);
			}
		}

		public void alterarBanco(setup setupInformado) {
		
			Connection conexaoBanco = ConexaoBanco.pegarConexaoBancoDeDados();
			PreparedStatement preparacaoDeclaracao = null;
			ResultSet conjuntoResultados = null;
					
			try {
				String alterarBanco = "UPDATE Setup SET nomeSetup = ? WHERE idSetup = ?";
				preparacaoDeclaracao = conexaoBanco.prepareStatement(alterarBanco);
				preparacaoDeclaracao.setString(1, setupInformado.getNomeSetup()); 
				preparacaoDeclaracao.setInt(2, setupInformado.getIdSetup());
				preparacaoDeclaracao.executeUpdate();	
				
			} catch (SQLException erroAlterarBanco) {
				throw new RuntimeException("[ Setup ] Erro ao atualizar o cliente", erroAlterarBanco);
			} finally {
				fecharConexaoBanco(conexaoBanco, preparacaoDeclaracao, conjuntoResultados);
			}
		}

		public setup pegarPorIdBanco(int idInformado) {
			Connection conexaoBanco = ConexaoBanco.pegarConexaoBancoDeDados();
			PreparedStatement preparacaoDeclaracao = null;
			ResultSet conjuntoResultados = null;
			setup setup = null;
			
			try {
				String selectBanco = "SELECT * FROM Setup WHERE idSetup = ?";
				preparacaoDeclaracao = conexaoBanco.prepareStatement(selectBanco);
				preparacaoDeclaracao.setInt(1, idInformado);
				conjuntoResultados = preparacaoDeclaracao.executeQuery();
				
		        while (conjuntoResultados.next()) {
		        	setup = pegarSetupPorConjuntoResultados(conjuntoResultados);
		        }
				
			} catch (SQLException erroSelectBanco) {
				throw new RuntimeException("[ Setup ] Erro ao selecionar o setup por id", erroSelectBanco);
			} finally {
				fecharConexaoBanco(conexaoBanco, preparacaoDeclaracao, conjuntoResultados);
			}
			return setup;
		}

		public void deletarBanco(int idInformado) {
			Connection conexaoBanco = ConexaoBanco.pegarConexaoBancoDeDados();
			PreparedStatement preparacaoDeclaracao = null;
				
			try {
				String deletarBanco = "DELETE Setup WHERE idSetup = ?";
				preparacaoDeclaracao = conexaoBanco.prepareStatement(deletarBanco);
				preparacaoDeclaracao.setInt(1, idInformado);
				preparacaoDeclaracao.executeUpdate(); 
				
			} catch (SQLException erroDeletarBanco) {
				throw new RuntimeException("[ Setup ] Erro ao remover o setup por id", erroDeletarBanco);
			} finally {
				fecharConexaoBanco(conexaoBanco, preparacaoDeclaracao, null);
			}
		}

		public List<setup> listarTodosNoBanco() {
			Connection conexaoBanco = ConexaoBanco.pegarConexaoBancoDeDados();
			Statement declaracao = null;
			ResultSet conjuntoResultados = null;
			List<setup> listaSetup = new ArrayList<setup>();
			
			try {
				declaracao = conexaoBanco.createStatement();
				
				String selectBanco = "SELECT * FROM Setup";
				conjuntoResultados = declaracao.executeQuery(selectBanco);
		        
		        while (conjuntoResultados.next()) {
		        	setup setup = pegarSetupPorConjuntoResultados(conjuntoResultados);
		        	listaSetup.add(setup);
		        }
				
			} catch (SQLException erroListarSetup) {
				throw new RuntimeException("[ Setup ] Erro ao selecionar todos os setup", erroListarSetup);
			} finally {
				fecharConexaoBanco(conexaoBanco, declaracao, conjuntoResultados);
			}
			return listaSetup;
		}
		
		private setup pegarSetupPorConjuntoResultados(ResultSet conjuntoResultadosInformado) throws SQLException {
			setup setup = new setup(); 
			setup.setIdSetup(conjuntoResultadosInformado.getInt("idSetup")); 
			setup.setNomeSetup(conjuntoResultadosInformado.getString("nomeSetup")); 
			
			return setup;
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


