package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ContaDAO {

	public static void saque(Double valor, int numConta) throws SQLException {
		String sqlSelect = "SELECT * FROM conta WHERE numero_conta = ?";
		
		String sqlUpadate = "UPDATE conta "
			+ "SET saldo = saldo - ? "
			+ "WHERE numero_conta = ?";
		
		String sqlTransacao = "INSERT INTO transacao (id_transacao, tipo_transacao, valor, data_hora, id_conta) "
				+ "VALUES (null, 'SAQUE',?,?,?)";
		
		try (Connection conn = ConnectionFactory.conectar() ) {
			conn.setAutoCommit(false);
		    try (PreparedStatement stmtsaldo = conn.prepareStatement(sqlUpadate)) {
		    	stmtsaldo.setDouble(1, valor);
		    	stmtsaldo.setInt(2, numConta);
		    	int rows = stmtsaldo.executeUpdate();
		    	
		    	if (rows > 0) {
			    	try (PreparedStatement smtselect = conn.prepareStatement(sqlSelect)) {
			    		smtselect.setInt(1, numConta);
				    	ResultSet rs = smtselect.executeQuery();
			    	
				    	if (rs.next()) {
				    		int id_conta = rs.getInt("id_conta");
			    	
				    		try (PreparedStatement stmttransacaoo = conn.prepareStatement(sqlTransacao)) {
				    			stmttransacaoo.setDouble(1, valor);
				    			stmttransacaoo.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
				    			stmttransacaoo.setInt(3, id_conta);
						    	int rows1 = stmttransacaoo.executeUpdate();
						    	
						    	if(rows1 > 0) {
						    		conn.commit();
						    		System.out.print("Saque realizado!");
						    	} else {
						    		System.out.print("Erro ao fazer insert na transacao para o saque");
						    		conn.rollback();
						    	}
						    	
				    		} catch (SQLException e) {
				    			e.printStackTrace();
					    		conn.rollback();
				    		}
				    	} else {
				    		System.out.print("Erro ao fazer update no saldo");
				    		conn.rollback();
				    	}
				    } catch (Exception e) {
						e.printStackTrace();
			    		conn.rollback();
					}
		    	} else {
		    		System.out.print("Erro ao fazer update no saldo");
		    		conn.rollback();
		    	}
		    } catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void deposito(Double valor, int numConta) throws SQLException {
		String sqlSelect = "SELECT * FROM conta WHERE numero_conta = ?";
		
		String sqlUpadate = "UPDATE conta "
			+ "SET saldo = saldo + ? "
			+ "WHERE numero_conta = ?";
		
		String sqlTransacao = "INSERT INTO transacao (id_transacao, tipo_transacao, valor, data_hora, id_conta) "
				+ "VALUES (null, 'SAQUE',?,?,?)";
		
		try (Connection conn = ConnectionFactory.conectar() ) {
			conn.setAutoCommit(false);
		    try (PreparedStatement stmtsaldo = conn.prepareStatement(sqlUpadate)) {
		    	stmtsaldo.setDouble(1, valor);
		    	stmtsaldo.setInt(2, numConta);
		    	int rows = stmtsaldo.executeUpdate();
		    	
		    	if (rows > 0) {
			    	try (PreparedStatement smtselect = conn.prepareStatement(sqlSelect)) {
			    		smtselect.setInt(1, numConta);
				    	ResultSet rs = smtselect.executeQuery();
			    	
				    	if (rs.next()) {
				    		int id_conta = rs.getInt("id_conta");
			    	
				    		try (PreparedStatement stmttransacaoo = conn.prepareStatement(sqlTransacao)) {
				    			stmttransacaoo.setDouble(1, valor);
				    			stmttransacaoo.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
				    			stmttransacaoo.setInt(3, id_conta);
						    	int rows1 = stmttransacaoo.executeUpdate();
						    	
						    	if(rows1 > 0) {
						    		conn.commit();
						    		System.out.print("Saque realizado!");
						    	} else {
						    		System.out.print("Erro ao fazer insert na transacao para o saque");
						    		conn.rollback();
						    	}
						    	
				    		} catch (SQLException e) {
				    			e.printStackTrace();
					    		conn.rollback();
				    		}
				    	} else {
				    		System.out.print("Erro ao fazer update no saldo");
				    		conn.rollback();
				    	}
				    } catch (Exception e) {
						e.printStackTrace();
			    		conn.rollback();
					}
		    	} else {
		    		System.out.print("Erro ao fazer update no saldo");
		    		conn.rollback();
		    	}
		    } catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
