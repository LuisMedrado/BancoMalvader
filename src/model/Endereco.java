package model;

public class Endereco {
	
	//Declarando Atributos
	private String cep;
	private String local;
	private int numeroCasa;
	private String bairro;
	private String cidade;
	private String estado;
	
	
	//Getter e setter ---> CEP
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	//Getter e setter ---> LOCAL
	public String getLocal() {
		return local;
	}
	
	public void setLocal(String local) {
		this.local = local;
	}
	
	//Getter e setter ---> NUMERO DA CASA
	public int getNumeroCasa() {
		return numeroCasa;
	}
	
	public void setNumeroCasa(int numeroCasa) {
		this.numeroCasa = numeroCasa;
	}
	
	//Getter e setter ---> BAIRRO
	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	//Getter e setter ---> CIDADE
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	//Getter e setter ---> ESTADO
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	//Sobrescrevendo método toString
	@Override
	public String toString() {
		return "Endereco [cep=" + cep + ", local=" + local + ", numeroCasa=" + numeroCasa + ", bairro=" + bairro
				+ ", cidade=" + cidade + ", estado=" + estado + "]";
	}
	
	
}
