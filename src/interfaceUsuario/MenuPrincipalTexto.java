package interfaceUsuario;

import java.util.Scanner;

public class MenuPrincipalTexto {

	private static final int CLIENTE = 1;
	private static final int FORNECEDOR = 2;
	private static final int PECAS = 3;
	private static final int SETUP = 4;
	
	private static final int ADICIONAR = 1;
	private static final int LISTAR = 2;
	private static final int EDITAR = 3;
	private static final int EXCLUIR = 4;
	
	private enum Estado {PRINCIPAL, CLIENTE, FORNECEDOR, PECAS, SETUP};
	
	private Estado estadoAtual;
	private Scanner entradaUsuario;
	
	public MenuPrincipalTexto() {
		estadoAtual = Estado.PRINCIPAL;
		entradaUsuario = new Scanner(System.in);
	}
	
	private void imprimeMenuPrincipal() {
		System.out.println("1 - Administração de Clientes");
		System.out.println("2 - Administração de Fornecedores");
		System.out.println("3 - Administração de Peças");
		System.out.println("4 - Administração de Setup");
	}
	
	private void imprimeMenuSecundário(String tipoMenu) {
		System.out.println("Administração de " + tipoMenu);
		System.out.println();
		System.out.println("1 - Adicionar");
		System.out.println("2 - Listar");
		System.out.println("3 - Editar");
		System.out.println("4 - Excluir");
	}
	
	public void executa() {
		int opcao;
		MenuEspecificoTexto menuEspecificoTexto;
		
		do {
			System.out.println("Administração do AliExpress"); // Título
			System.out.println();
			
			switch(estadoAtual) {
			case CLIENTE:
				imprimeMenuSecundário("Cliente");
				break;
			case FORNECEDOR:
				imprimeMenuSecundário("Fornecedor");
				break;
			case PECAS:
				imprimeMenuSecundário("Peças");
				break;
			case SETUP:
				imprimeMenuSecundário("Setup");
				break;
			default:
				imprimeMenuPrincipal();
				break;
			}
			
			System.out.println();
			System.out.println("0 - Sair");
			
			System.out.println();
			System.out.print("Escolha uma opção: ");
	
			opcao = entradaUsuario.nextInt();
			entradaUsuario.nextLine();
			
			System.out.println("Você escolheu a opção: " + opcao);
			System.out.println();
				
			if (estadoAtual == Estado.PRINCIPAL) {
				
				switch (opcao) {
				
				case CLIENTE:
					estadoAtual = Estado.CLIENTE;
					break;
				case FORNECEDOR:
					estadoAtual = Estado.FORNECEDOR;
					break;
				case PECAS:
					estadoAtual = Estado.PECAS;
					break;
				case SETUP:
					estadoAtual = Estado.SETUP;
					break;
				default:
					estadoAtual = Estado.PRINCIPAL;
				}
			} else {
				menuEspecificoTexto = new MenuClienteTexto();

                if (estadoAtual == Estado.CLIENTE) {
                    menuEspecificoTexto = new MenuClienteTexto();
                } else if( estadoAtual == Estado.FORNECEDOR ) {
                    menuEspecificoTexto = new MenuFornecedorTexto();
                } else if( estadoAtual == Estado.PECAS ) {
                    menuEspecificoTexto = new MenuPecasTexto();
                } else if( estadoAtual == Estado.SETUP ) {
                    menuEspecificoTexto = new MenuSetupTexto();
                }
                
				switch (opcao) {
					case ADICIONAR:
						menuEspecificoTexto.adicionar();
						break;
					case EDITAR:
						menuEspecificoTexto.editar();
						break;
					case EXCLUIR:
						menuEspecificoTexto.excluir();
						break;
					case LISTAR:
						menuEspecificoTexto.listarTodos();
						break;
					default:
						System.out.println("Opção inválida. Tente novamente!");
				}
			}
		} while (opcao != 0);
	}
}