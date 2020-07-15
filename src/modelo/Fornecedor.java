package modelo;

public class Fornecedor {

	private int idFornecedor;
	private String nomeFornecedor;
	
	public Fornecedor() { }
	
	public Fornecedor( int idFornecedorInformado, String nomeFornecedorInformado ) {
		this.idFornecedor = idFornecedorInformado;
		this.nomeFornecedor = nomeFornecedorInformado;
	}

	public int getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(int idFornecedorInformado) {
		this.idFornecedor = idFornecedorInformado;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedorInformado) {
		this.nomeFornecedor = nomeFornecedorInformado;
	}
	
	public String toString() {
		return "ID: [ " + this.idFornecedor + " ].\nNome fornecedor: [ " + this.nomeFornecedor + " ]. ";
	}
}