package interfaceUsuario;

import java.util.List;

import acessoDadosObjeto.PecasAcessoObjeto;
import modelo.Pecas;

public class MenuPecasTexto extends MenuEspecificoTexto {

	private PecasAcessoObjeto pecasAcessoObjeto;

    public MenuPecasTexto() {
    	pecasAcessoObjeto = new PecasAcessoObjeto();
    }

    private int obterIdPecas() {
        System.out.print("Escolha o id da peça: ");
        int idPeca = entradaUsuario.nextInt();
        entradaUsuario.nextLine();
        return idPeca;
    }

    private Pecas obterDadosPecas(Pecas pecaInformado) {
        Pecas peca;

        if (pecaInformado == null) {
        	peca = new Pecas();
        } else {
        	peca = pecaInformado;
        }
        System.out.print("Digite o nome da peça: ");
        String nomePeca = entradaUsuario.nextLine();
        pecaInformado.setNomePeca(nomePeca);
        return peca;
    }

    public void adicionar() {
        System.out.println("Adicionar peça");
        System.out.println();
        Pecas novoPeca = obterDadosPecas(null);
        pecasAcessoObjeto.inserirBanco(novoPeca);
    }

    public void editar() {
        System.out.println("Editar peça");
        System.out.println();
        imprimirPecas();
        int idPeca = obterIdPecas();
        Pecas pecaModificar = pecasAcessoObjeto.pegarPorIdBanco(idPeca);
        Pecas novoPeca = obterDadosPecas(pecaModificar);
        novoPeca.setIdPeca(pecaModificar.getIdPeca());
        pecasAcessoObjeto.alterarBanco(novoPeca);
    }

    public void excluir() {
        System.out.println("Excluir peça");
        System.out.println();
        imprimirPecas();
        int idPeca = obterIdPecas();
        pecasAcessoObjeto.deletarBanco(idPeca);
    }

    public void listarTodos() {
        System.out.println("Lista de peças");
        System.out.println();
        imprimirPecas();
    }

    private void imprimirPecas() {
        List<Pecas> listaPecas = pecasAcessoObjeto.listarTodosNoBanco();
        System.out.println("id\tNome peças");
        for (Pecas pecas : listaPecas) {
            System.out.println(pecas.getIdPeca() + "\t" + pecas.getNomePeca());
        }
    }
}