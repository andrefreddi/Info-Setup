package modelo;

public class Pecas {

	private int idPeca;
	private String nomePeca;
	
	public Pecas() { }
	
	public Pecas(int idPecaInformado, String nomePecaInformado) {
		this.idPeca = idPecaInformado;
		this.nomePeca = nomePecaInformado;
	}

	public int getIdPeca() {
		return idPeca;
	}

	public void setIdPeca(int idPecaInformado) {
		this.idPeca = idPecaInformado;
	}

	public String getNomePeca() {
		return nomePeca;
	}

	public void setNomePeca(String nomePecaInformado) {
		this.nomePeca = nomePecaInformado;
	}
	
	public String toString() {
		return "ID: [ " + this.idPeca + " ].\nNome peça: [ " + this.nomePeca + " ]. ";
	}
	
}