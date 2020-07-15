package sistema;

import java.util.List;

import interfaceUsuario.MenuPrincipalTexto;
import modelo.Cliente;
import modelo.Fornecedor;
import modelo.Pecas;
import modelo.Setup;

public class Sistema {

	public static void main(String[] args) {
		
		MenuPrincipalTexto menu = new MenuPrincipalTexto();
		menu.executa();
	}

	private static void imprimeClientes(List<Cliente> clienteInformado) {
		System.out.println("Lista de cliente:");
        for(Cliente cliente : clienteInformado) {
			System.out.println(cliente);
		}
	}
	
	private static void imprimeFornecedor(List<Fornecedor> fornecedorInformado) {
		System.out.println("Lista de fornecedor:");
        for(Fornecedor fornecedor : fornecedorInformado) {
			System.out.println(fornecedor);
		}
	}
	
	private static void imprimePecas(List<Pecas> pecaInformado) {
		System.out.println("Lista de peças:");
        for (Pecas pecas : pecaInformado) {
			System.out.println(pecas);
		}
	}
	
	private static void imprimeSetup(List<Setup> setupInformado) {
		System.out.println("Lista de setup:");
        for (Setup setup : setupInformado) {
			System.out.println(setup);
		}
	}
}