package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.*;

import dao.ConnectionFactory;
import dao.ContaDAO;

public class Cliente extends Usuario {
    
    private String senha;

    // Constructor
    public Cliente(int id_usuario, String nome_usuario, String cpf_usuario, LocalDate dataNascimento_usuario,
            String telefone_usuario, Endereco endereco, String senha) {
        super(id_usuario, nome_usuario, cpf_usuario, dataNascimento_usuario, telefone_usuario, endereco);
        this.senha = senha;
    }

    // Getters and Setters for senha
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

	
	//Métodos
	public double consultarSaldo(Conta conta) {
		String sql = "SELECT saldo FROM conta WHERE numero_conta = ?;";
		
		 try (Connection conn = ConnectionFactory.conectar();
		      PreparedStatement stmt = conn.prepareStatement(sql)) {
		           
		           stmt.setInt(1, conta.getNumero_conta());
		           var rs = stmt.executeQuery();
		           
		           if (rs.next()) {
		        	   double saldo = rs.getDouble("saldo");
		        	   conta.setSaldo_conta(saldo);
		        	   return saldo;
		           } else {
		               System.out.println("Conta não encontrada.");
		               return 0;
		           }
		       } catch (SQLException sqle) {
		           sqle.printStackTrace();
		           return 0;
		       }
	}

	public void depositar(Conta conta, double valor) throws SQLException {
		ContaDAO.deposito(valor, conta.getNumero_conta());

	}
	
	public void sacar(Conta conta, double valor) throws SQLException {
		ContaDAO.saque(valor, conta.getNumero_conta());
	}
	
	public String consultarExtrato() {
		return "";
	}
	
	public double consultarLimite(ContaCorrente cc) {
		String sql = "SELECT limite FROM conta_corrente WHERE id_conta = ?;";
		
		 try (Connection conn = ConnectionFactory.conectar();
	      PreparedStatement stmt = conn.prepareStatement(sql)) {
	           
           stmt.setInt(1, cc.getNumero_conta());
           var rs = stmt.executeQuery();
           
           if (rs.next()) {
        	   double limite = rs.getDouble("limite");
        	   cc.setLimite(limite);
        	   return limite;
           } else {
               System.out.println("Conta não encontrada.");
               return 0;
           }
       } catch (SQLException sqle) {
           sqle.printStackTrace();
           return 0;
       }
	}
}
	
	