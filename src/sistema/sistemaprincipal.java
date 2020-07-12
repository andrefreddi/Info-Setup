package sistema;

import modelo.Fornecedor;
import objetoacessodados.ConexaoBanco;

public class sistemaprincipal {

	public static void main(String[] args) {

	Fornecedor f = new Fornecedor();
	System.out.println(f.toString());
	
	ConexaoBanco.pegarConexaoBancoDeDados();

	}

	
}
