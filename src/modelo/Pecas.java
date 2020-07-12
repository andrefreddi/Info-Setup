package modelo;

public class Pecas {
	
	private int idPecas;
	private String nomePecas;
	
	public Pecas() {}
	
	public Pecas(int idPecasInformado, String nomePecasInformado) {
		super();
		this.idPecas = idPecasInformado;
		this.nomePecas = nomePecasInformado;
	}


	public int getIdPecas() {
		return idPecas;
	}


	public void setIdPecas(int idPecasInformado) {
		this.idPecas = idPecasInformado;
	}


	public String getNomePecas() {
		return nomePecas;
	}


	public void setNomePecas(String nomePecasInformado) {
		this.nomePecas = nomePecasInformado;
	}

	public String toString() {
		return "Id: [ " + this.idPecas + " ].\nNome: [ " + this.nomePecas + "]. ";
	}
	

}
