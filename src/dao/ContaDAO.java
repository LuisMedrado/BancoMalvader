package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;



public class ContaDAO {
	
	 public static Conta findByIdCliente(int id) throws Exception {
	        String queryConta = "SELECT * FROM Conta WHERE id_cliente = ?";
	        try (Connection con = ConnectionFactory.conectar()) {

	            PreparedStatement pst = con.prepareStatement(queryConta);
	            pst.setInt(1, id);
	            ResultSet rs = pst.executeQuery();
	            
	            if (rs.next()) {
	                String tipo = rs.getString("tipo_conta");

	                if (tipo.equals("CORRENTE")) {
	                    ContaCorrente c = new ContaCorrente();
	                	
	                    c.setNumero_conta(rs.getInt("numero_conta"));
	                    c.setAgencia_conta(rs.getString("agencia"));
	                    c.setSaldo_conta(rs.getDouble("saldo"));
	                    //int fk_cliente = rs.getInt("id_cliente");
	                    int pk = rs.getInt("id_conta");

	                    String queryCcorrente = "SELECT * FROM Conta_Corrente WHERE id_conta = ?";
	                    PreparedStatement pst2 = con.prepareStatement(queryCcorrente);
	                    pst2.setInt(1, pk);
	                    ResultSet rs2 = pst2.executeQuery();

	                    if (rs2.next()) {
	                        c.setLimite(rs2.getDouble("limite"));
	                        c.setDataVencimento(LocalDate.parse(rs2.getString("data_vencimento")));	
	                        ConnectionFactory.desconectar(con);
	                        return c;
	                    } else {
	                        System.out.println("Erro ao obter fk da conta");
	                        return null;
	                    }

	                } else if (tipo.equals("POUPANCA")) {
	                    ContaPoupanca c = new ContaPoupanca();

	                    c.setNumero_conta(rs.getInt("numero_conta"));
	                    c.setAgencia_conta(rs.getString("agencia_conta"));
	                    c.setSaldo_conta(rs.getDouble("saldo"));
	                    //int fk_cliente = rs.getInt("fk_cliente_id");
	                    
	                    int pk = rs.getInt("id_conta");
	                    
	                    String queryPoupanca = "SELECT * FROM Conta_Poupanca WHERE id_conta = ?";
	                    PreparedStatement pst2 = con.prepareStatement(queryPoupanca);
	                    pst2.setInt(1, pk);
	                    ResultSet rs2 = pst2.executeQuery();

	                    if (rs2.next()) {
	                        c.setTaxaJuros(rs2.getDouble("taxa_rendimento"));
	                        ConnectionFactory.desconectar(con);
	                        return c;
	                    } else {
	                        System.out.println("Erro ao obter fk da conta tipo");
	                        return null;
	                    }
	                } else {
	                    System.out.println("Erro: tipo de conta invalido");
	                    return null;
	                }
	            } else {
	                System.out.println("Erro ao obter tipo de conta");
	                return null;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
   }

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
				+ "VALUES (null, 'DEPOSITO',?,?,?)";
		
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
						    		System.out.print("Deposito realizado!");
						    	} else {
						    		System.out.print("Erro ao fazer insert na transacao para o deposito");
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
