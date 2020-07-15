package acessoDadosObjeto;

import java.util.List;

public interface InterfaceAcessoDadosObjeto<Tipo> {

	void inserirBanco(Tipo tipoInformado);
    void alterarBanco(Tipo tipoInformado);
    Tipo pegarPorIdBanco(int idInformado);
    void deletarBanco(int idInformado);
    List<Tipo> listarTodosNoBanco();
}