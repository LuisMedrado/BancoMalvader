package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FuncionarioDAO {

    public static boolean validarLoginComSenha(String senha) {
	String sql = "SELECT id_funcionario FROM funcionario WHERE senha = ?";
	try (Connection conn = ConnectionFactory.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

	    stmt.setString(1, senha);

	    ResultSet rs = stmt.executeQuery();
	    return rs.next(); // Retorna true se encontrar um resultado
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
    }
}
