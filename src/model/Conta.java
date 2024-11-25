 package model;

public abstract class Conta {
	
	protected int numero_conta;
	protected String agencia_conta;
	protected double saldo_conta;
	protected Cliente cliente;
	protected String tipo_conta;
	
	//Construtor
	/*public Conta(int numero_conta, String agencia_conta, double saldo_conta, Cliente cliente) {
		super();
		this.numero_conta = numero_conta;
		this.agencia_conta = agencia_conta;
		this.saldo_conta = saldo_conta;
		this.cliente = cliente;
	}*/
	
	//Getters e Setters
	//Getter e setter ---> NUMERO CONTA
	public int getNumero_conta() {
		return numero_conta;
	}

	public void setNumero_conta(int numero_conta) {
		this.numero_conta = numero_conta;
	}

	//Getter e setter ---> AGENCIA CONTA
	public String getAgencia_conta() {
		return agencia_conta;
	}

	public void setAgencia_conta(String agencia_conta) {
		this.agencia_conta = agencia_conta;
	}

	//Getter e setter ---> SALDO CONTA
	public double getSaldo_conta() {
		return saldo_conta;
	}

	public void setSaldo_conta(double saldo_conta) {
		this.saldo_conta = saldo_conta;
	}

	//Getter e setter ---> CLIENTE
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
	//Métodos
	public void depositar(double valor_conta) {
		
	}
	
	public boolean sacar(double valor_conta) {
		
	}
	
	public double consultarSaldo() {
		
	}
}
