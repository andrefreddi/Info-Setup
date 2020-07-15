package interfaceUsuario;

import java.util.List;

import acessoDadosObjeto.FornecedorAcessoObjeto;
import modelo.Fornecedor;

public class MenuFornecedorTexto extends MenuEspecificoTexto {

	private FornecedorAcessoObjeto fornecedorAcessoObjeto;

    public MenuFornecedorTexto() {
    	fornecedorAcessoObjeto = new FornecedorAcessoObjeto();
    }

    private int obterIdFornecedor() {
        System.out.print("Escolha o id da fornecedor: ");
        int idFornecedor = entradaUsuario.nextInt();
        entradaUsuario.nextLine();
        return idFornecedor;
    }

    private Fornecedor obterDadosFornecedor(Fornecedor fornecedorInformado) {
        Fornecedor fornecedor;

        if (fornecedorInformado == null) {
        	fornecedor = new Fornecedor();
        } else {
        	fornecedor = fornecedorInformado;
        }
        System.out.print("Digite o nome da fornecedor: ");
        String nomeFornecedor = entradaUsuario.nextLine();
        fornecedorInformado.setNomeFornecedor(nomeFornecedor);
        return fornecedor;
    }

    public void adicionar() {
        System.out.println("Adicionar fornecedor");
        System.out.println();
        Fornecedor novoFornecedor = obterDadosFornecedor(null);
        fornecedorAcessoObjeto.inserirBanco(novoFornecedor);
    }

    public void editar() {
        System.out.println("Editar fornecedor");
        System.out.println();
        imprimirFornecedor();
        int idFornecedor = obterIdFornecedor();
        Fornecedor fornecedorModificar = fornecedorAcessoObjeto.pegarPorIdBanco(idFornecedor);
        Fornecedor novoFornecedor = obterDadosFornecedor(fornecedorModificar);
        novoFornecedor.setIdFornecedor(fornecedorModificar.getIdFornecedor());
        fornecedorAcessoObjeto.alterarBanco(novoFornecedor);
    }

    public void excluir() {
        System.out.println("Excluir fornecedor");
        System.out.println();
        imprimirFornecedor();
        int idFornecedor = obterIdFornecedor();
        fornecedorAcessoObjeto.deletarBanco(idFornecedor);
    }

    public void listarTodos() {
        System.out.println("Lista de fornecedores");
        System.out.println();
        imprimirFornecedor();
    }

    private void imprimirFornecedor() {
        List<Fornecedor> listaFornecedor = fornecedorAcessoObjeto.listarTodosNoBanco();
        System.out.println("id\tNome fornecedor");
        for (Fornecedor fornecedor : listaFornecedor) {
            System.out.println(fornecedor.getIdFornecedor() + "\t" + fornecedor.getNomeFornecedor());
        }
    }
}