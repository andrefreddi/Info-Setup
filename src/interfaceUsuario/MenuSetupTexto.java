package interfaceUsuario;

import java.util.List;

import acessoDadosObjeto.SetupAcessoObjeto;
import modelo.Setup;

public class MenuSetupTexto extends MenuEspecificoTexto {

	private SetupAcessoObjeto setupAcessoObjeto;

    public MenuSetupTexto() {
    	setupAcessoObjeto = new SetupAcessoObjeto();
    }

    private int obterIdSetup() {
        System.out.print("Escolha o id da setup: ");
        int idSetup = entradaUsuario.nextInt();
        entradaUsuario.nextLine();
        return idSetup;
    }

    private Setup obterDadosSetup(Setup setupInformado) {
        Setup setup;

        if (setupInformado == null) {
        	setup = new Setup();
        } else {
        	setup = setupInformado;
        }
        System.out.print("Digite o nome da setup: ");
        String nomeSetup = entradaUsuario.nextLine();
        setupInformado.setNomeSetup(nomeSetup);
        return setup;
    }

    public void adicionar() {
        System.out.println("Adicionar setup");
        System.out.println();
        Setup novoSetup = obterDadosSetup(null);
        setupAcessoObjeto.inserirBanco(novoSetup);
    }

    public void editar() {
        System.out.println("Editar setup");
        System.out.println();
        imprimirSetup();
        int idSetup = obterIdSetup();
        Setup setupModificar = setupAcessoObjeto.pegarPorIdBanco(idSetup);
        Setup novoSetup = obterDadosSetup(setupModificar);
        novoSetup.setIdSetup(setupModificar.getIdSetup());
        setupAcessoObjeto.alterarBanco(novoSetup);
    }

    public void excluir() {
        System.out.println("Excluir setup");
        System.out.println();
        imprimirSetup();
        int idSetup = obterIdSetup();
        setupAcessoObjeto.deletarBanco(idSetup);
    }

    public void listarTodos() {
        System.out.println("Lista de setup");
        System.out.println();
        imprimirSetup();
    }

    private void imprimirSetup() {
        List<Setup> listaSetup = setupAcessoObjeto.listarTodosNoBanco();
        System.out.println("id\tNome setup's");
        for (Setup setup : listaSetup) {
            System.out.println(setup.getIdSetup() + "\t" + setup.getNomeSetup());
        }
    }
}