package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import model.Cliente;
import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;
import model.Endereco;

public class ClienteDAO {

    public static boolean validarLoginComSenha(String senha) {
	String sql = "SELECT id_usuario FROM usuario WHERE senha = ?";
	try (Connection conn = ConnectionFactory.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

	    stmt.setString(1, senha);

	    ResultSet rs = stmt.executeQuery();
	    return rs.next(); // Retorna true se encontrar um resultado
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
    }
    
    public static Cliente findbyCpf(String cpf) {
    	String sql = "SELECT * FROM usuario WHERE cpf = ?";
    	String sqlCliente = "SELECT * FROM cliente WHERE id_usuario = ?";
    	
    	Cliente cliente = null;

    	try (Connection conn = ConnectionFactory.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
		    stmt.setString(1, cpf);
	    	ResultSet rs = stmt.executeQuery();
	    	
			if (rs.next()) {
				String nome = rs.getString("nome");																
				int id = rs.getInt("id_usuario");
                LocalDate data = LocalDate.parse(String.valueOf((rs.getDate("data_nascimento"))));
				String telefone = rs.getString("telefone");
				String senha = rs.getString("senha");
				
				PreparedStatement stmt1 = conn.prepareStatement(sqlCliente);
				stmt1.setInt(1, id);
		    	ResultSet rs1 = stmt1.executeQuery();
		    	
		    	if (rs1.next()) {
		    		int idcliente = rs1.getInt("id_cliente");
		    		Endereco endereco = new Endereco(); //por enquanto nao tem nada
		    		cliente = new Cliente(idcliente, nome, cpf, data, telefone, endereco, senha);
		    	}
			}
    	} catch (Exception e) {
    	    e.printStackTrace();
    	    return null;
    	}
    	
		return cliente;
    }
}

