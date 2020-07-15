package interfaceUsuario;

import java.util.Scanner;

abstract class MenuEspecificoTexto {
	
    abstract public void adicionar();
    abstract public void editar();
    abstract public void excluir();
    abstract public void listarTodos();
    protected Scanner entradaUsuario;

    public MenuEspecificoTexto() {
        entradaUsuario = new Scanner(System.in);
    }
}