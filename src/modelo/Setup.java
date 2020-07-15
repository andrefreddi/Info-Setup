package modelo;

public class Setup {

	private int idSetup;
	private String nomeSetup;
	
	public Setup() {}

	public Setup(int idSetupInformado, String nomeSetupInformado) {
		super();
		this.idSetup = idSetupInformado;
		this.nomeSetup = nomeSetupInformado;
	}

	public int getIdSetup() {
		return idSetup;
	}

	public void setIdSetup(int idSetupInformado) {
		this.idSetup = idSetupInformado;
	}

	public String getNomeSetup() {
		return nomeSetup;
	}

	public void setNomeSetup(String nomeSetupInformado) {
		this.nomeSetup = nomeSetupInformado;
	}
	
	public String toString() {
		return "ID: [ " + this.idSetup + " ].\nNome setup: [ " + this.nomeSetup + " ]. ";
	}
	
}