package objetoacessodados;

import java.util.List;

public interface InterfaceAcessoObjeto<Tipo> {

	void inserirBanco(Tipo tipoInformado);
	void alterarBanco(Tipo tipoInformado);
	Tipo pegarPorIdBanco(int idInformado);
    void deletarBanco(int id);
    List<Tipo> listarTodosNoBanco();
    
}
