package modelo;

public class Cliente {
	
	private int idCliente;
	private String nomeCliente;
	
	public Cliente() {}
	
	public Cliente( int idClienteInformado, String nomeClienteInformado ) {
		this.idCliente = idClienteInformado;
		this.nomeCliente = nomeClienteInformado;
	}
	
	public int getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente( int idClienteInformado ) {
		this.idCliente = idClienteInformado;
	}
	
	public String getNomeCliente() {
		return nomeCliente;
	}
	
	public void setNomeCliente( String nomeClienteInformado ) {
		this.nomeCliente = nomeClienteInformado;
	}
	
	public String toString() {
		return "ID: [ " + this.idCliente + " ].\nNome cliente: [ " + this.nomeCliente + " ]. ";
	}
	
}
