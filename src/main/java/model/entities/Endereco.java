package model.entities;

public class Endereco {
	
	private Integer id_endereco;
	private String cep;
	private String logradouro;
	private int numero;
	private String cidade;
	private String estado;
	
	public Endereco() {}
	
	public Endereco(Integer id, String cep, String logradouro, int numero, String cidade, String estado) {
		this.id_endereco = id;
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.cidade = cidade;
		this.estado = estado;
	}
	
	public Integer getId() {
		return id_endereco;
	}
	
	public void setId(Integer id) {
		this.id_endereco = id;
	}
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}
	
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		return this.getLogradouro() + this.getNumero();
	}

}
