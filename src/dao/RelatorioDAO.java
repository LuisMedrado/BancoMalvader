package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import model.Relatorio;

public class RelatorioDAO {
	
	public static void Inserir(Relatorio relatorio) {
		
//		String sql = "INSERT INTO relatorio (id_relatorio, tipo_relatorio, data_geracao, conteudo, id_funcionario) VALUES (NULL, ?, ?, ?, ?)";
//		
//		try (Connection conn = ConnectionFactory.conectar();
//	            PreparedStatement stmt = conn.prepareStatement(sql)) {
//	           
//	           stmt.setString(1, relatorio.getTipo_relatorio());
//	           stmt.setTimestamp(2, Timestamp.valueOf(relatorio.getDataGeracao()));
//	           stmt.setString(3, relatorio.getDados());
//	           var rs = stmt.executeQuery();
//	           
//	           if (rs.next()) {
//	               
//	               conta.numero_conta = rs.getInt("numero_conta");
//	               conta.agencia_conta = rs.getString("agencia");
//	               conta.saldo_conta = rs.getDouble("saldo");
//	               conta.tipo_conta = rs.getString("tipo_conta");
//	               super.id_usuario = rs.getInt("id_cliente");
//	               return conta;
//	           } else {
//	               System.out.println("Conta não encontrada.");
//	               return null;
//	           }
//	       } catch (SQLException sqle) {
//	           sqle.printStackTrace();
//	           return null;
//	       }	
	}
}
