package model;

import java.time.LocalDate;

public class ContaCorrente extends Conta{
	
	private double limite;
	private LocalDate dataVencimento;
	
	//Getters e Setters
	//Getter e setter ---> LIMITE
	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}
	
	//Getter e setter ---> DATA VENCIMENTO
	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	
	//Métodos
	public double consultarLimite(){
		
	}                       
}
