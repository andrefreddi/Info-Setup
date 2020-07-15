package interfaceUsuario;

import java.util.List;

import acessoDadosObjeto.ClienteAcessoObjeto;
import modelo.Cliente;

public class MenuClienteTexto extends MenuEspecificoTexto {

	private ClienteAcessoObjeto clienteAcessoObjeto;

    public MenuClienteTexto() {
    	clienteAcessoObjeto = new ClienteAcessoObjeto();
    }

    private int obterIdCliente() {
        System.out.print("Escolha o id da cliente: ");
        int idCliente = entradaUsuario.nextInt();
        entradaUsuario.nextLine();
        return idCliente;
    }

    private Cliente obterDadosCliente(Cliente clienteInformado) {
        Cliente cliente;

        if (clienteInformado == null) {
        	cliente = new Cliente();
        } else {
        	cliente = clienteInformado;
        }

        System.out.print("Digite o nome da cliente: ");
        String nomeCliente = entradaUsuario.nextLine();
        cliente.setNomeCliente(nomeCliente);
        return cliente;
    }

    public void adicionar() {
        System.out.println("Adicionar cliente");
        System.out.println();
        Cliente novoCliente = obterDadosCliente(null);
        clienteAcessoObjeto.inserirBanco(novoCliente);
    }

    public void editar() {
        System.out.println("Editar cliente");
        System.out.println();
        imprimirCliente();
        int idCliente = obterIdCliente();
        Cliente clienteModificar = clienteAcessoObjeto.pegarPorIdBanco(idCliente);
        Cliente novoCliente = obterDadosCliente(clienteModificar);
        novoCliente.setIdCliente(clienteModificar.getIdCliente());
        clienteAcessoObjeto.alterarBanco(novoCliente);
    }

    public void excluir() {
        System.out.println("Excluir cliente");
        System.out.println();
        imprimirCliente();
        int idCliente = obterIdCliente();
        clienteAcessoObjeto.deletarBanco(idCliente);
    }

    public void listarTodos() {
        System.out.println("Lista de clientes");
        System.out.println();
        imprimirCliente();
    }

    private void imprimirCliente() {
        List<Cliente> listaCLiente = clienteAcessoObjeto.listarTodosNoBanco();
        System.out.println("id\tNome cliente");
        for (Cliente cliente : listaCLiente) {
            System.out.println(cliente.getIdCliente() + "\t" + cliente.getNomeCliente());
        }
    }
}