package modelo;

public class setup {
	
	private int idSetup;
	private String nomeSetup;
	
	public setup() {}

	public setup(int idSetupFornecido, String nomeSetupFornecido) {
		super();
		this.idSetup = idSetupFornecido;
		this.nomeSetup = nomeSetupFornecido;
	}

	public int getIdSetup() {
		return idSetup;
	}

	public void setIdSetup(int idSetupFornecido) {
		this.idSetup = idSetupFornecido;
	}

	public String getNomeSetup() {
		return nomeSetup;
	}

	public void setNomeSetup(String nomeSetupFornecido) {
		this.nomeSetup = nomeSetupFornecido;
	}
	
	public String toString() {
		return "Id: [ " + this.idSetup + " ].\nNome: [ " + this.nomeSetup + "]. ";
	}
	

}
