package model;

import java.time.LocalDate;

public class ContaPoupanca extends Conta {

    private double taxaJuros;
    private LocalDate dataVencimento;

    // Getters e Setters
    // Getter e setter ---> TAXA JUROS
    public double getTaxaJuros() {
	return taxaJuros;
    }

    public void setTaxaJuros(double taxaJuros) {
	this.taxaJuros = taxaJuros;
    }

    // Getter e setter ---> DATA VENCIMENTO
    public LocalDate getDataVencimento() {
	return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
	this.dataVencimento = dataVencimento;
    }

    // Métodos
    public double calcularRendimento() {
	// Aqui pode-se implementar o cálculo do rendimento da poupança com base na taxa
	// de juros
	return 0; // Retorne o valor de rendimento calculado
    }

    public double getTaxaRendimento() {
	// TODO Auto-generated method stub
	return 0;
    }
}
