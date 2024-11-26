package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import dao.ConnectionFactory;

public class Funcionario extends Usuario {

    // Declarando Atributos
    private String codigoFuncionario;
    private String cargo;
    private String senha_funcionario;

    // Construtor
    public Funcionario(int id_usuario, String nome_usuario, String cpf_usuario, LocalDate dataNascimento_usuario,
	    String telefone_usuario, Endereco endereco, String codigoFuncionario, String cargo,
	    String senha_funcionario) {
	super(id_usuario, nome_usuario, cpf_usuario, dataNascimento_usuario, telefone_usuario, endereco);
	this.codigoFuncionario = codigoFuncionario;
	this.cargo = cargo;
	this.senha_funcionario = senha_funcionario;
    }

    // Getters e Setters
    // Getter e setter ---> CODIGO FUNCIONARIO
    public String getCodigoFuncionario() {
	return codigoFuncionario;
    }

    public void setCodigoFuncionario(String codigoFuncionario) {
	this.codigoFuncionario = codigoFuncionario;
    }

    // Getter e setter ---> CARGO
    public String getCargo() {
	return cargo;
    }

    public void setCargo(String cargo) {
	this.cargo = cargo;
    }

    // Getter e setter ---> SENHA
    public String getSenha_funcionario() {
	return senha_funcionario;
    }

    public void setSenha_funcionario(String senha_funcionario) {
	this.senha_funcionario = senha_funcionario;
    }

    // Métodos
    public static void abrirConta(Conta conta, Endereco end) {
	if (conta instanceof ContaCorrente) {
	    ContaCorrente cc = (ContaCorrente) conta;
	    Cliente cl = cc.getCliente();

	    // SQL statements
	    String sqlUsuario = "INSERT INTO usuario (nome, cpf, data_nascimento, telefone, tipo_usuario, senha) VALUES (?, ?, ?, ?, 'CLIENTE', ?)";
	    String sqlCliente = "INSERT INTO cliente (id_usuario) VALUES (?)";
	    String sqlConta = "INSERT INTO conta (numero_conta, agencia, tipo_conta, id_cliente) VALUES (?, ?, 'CORRENTE', ?)";
	    String sqlCC = "INSERT INTO conta_corrente (limite, data_vencimento, id_conta) VALUES (?, ?, ?)";
	    String sqlEndereco = "INSERT INTO banco_malvader.endereco (cep, local, numero_casa, bairro, cidade, estado, id_usuario) VALUES (?, ?, ?, ?, ?, ?, ?);";

	    try (Connection conn = ConnectionFactory.conectar()) {
		conn.setAutoCommit(false); // Início da transação

		try {
		    // Inserindo na tabela usuario
		    int idUsuario;
		    try (PreparedStatement stmtUsuario = conn.prepareStatement(sqlUsuario,
			    Statement.RETURN_GENERATED_KEYS)) {
			stmtUsuario.setString(1, cl.getNome_usuario());
			stmtUsuario.setString(2, cl.getCpf_usuario());
			stmtUsuario.setString(3, String.valueOf(cl.getDataNascimento_usuario())); // Formatar
												  // corretamente
			stmtUsuario.setString(4, cl.getTelefone_usuario());
			stmtUsuario.setString(5, cl.getSenha());

			stmtUsuario.executeUpdate();

			try (ResultSet rs = stmtUsuario.getGeneratedKeys()) {
			    if (rs.next()) {
				idUsuario = rs.getInt(1); // ID gerado para usuario
			    } else {
				throw new SQLException("Falha ao obter o ID gerado para o usuário.");
			    }
			}
		    }

		    // Inserindo na tabela cliente
		    int idCliente;
		    try (PreparedStatement stmtCliente = conn.prepareStatement(sqlCliente,
			    Statement.RETURN_GENERATED_KEYS)) {
			stmtCliente.setInt(1, idUsuario);
			stmtCliente.executeUpdate();

			try (ResultSet rs = stmtCliente.getGeneratedKeys()) {
			    if (rs.next()) {
				idCliente = rs.getInt(1); // ID gerado para cliente
			    } else {
				throw new SQLException("Falha ao obter o ID gerado para o cliente.");
			    }
			}
		    }

		    // Inserindo na tabela conta
		    int idConta;
		    try (PreparedStatement stmtConta = conn.prepareStatement(sqlConta,
			    Statement.RETURN_GENERATED_KEYS)) {
			stmtConta.setInt(1, cc.getNumero_conta());
			stmtConta.setString(2, cc.getAgencia_conta());
			stmtConta.setInt(3, idCliente); // Associando ao cliente correto
			stmtConta.executeUpdate();

			try (ResultSet rs = stmtConta.getGeneratedKeys()) {
			    if (rs.next()) {
				idConta = rs.getInt(1); // ID gerado para conta
			    } else {
				throw new SQLException("Falha ao obter o ID gerado para a conta.");
			    }
			}
		    }

		    // Inserindo na tabela conta_corrente
		    try (PreparedStatement stmtCC = conn.prepareStatement(sqlCC)) {
			stmtCC.setDouble(1, cc.getLimite());
			stmtCC.setString(2, String.valueOf(cc.getDataVencimento())); // Formatar corretamente
			stmtCC.setInt(3, idConta);
			stmtCC.executeUpdate();
		    }
		    
		    // Receba endereçamento
		    try (PreparedStatement stmtEndereco = conn.prepareStatement(sqlEndereco)) {
		    	stmtEndereco.setString(1, end.getCep());
		    	stmtEndereco.setString(2, end.getLocal());
		    	stmtEndereco.setInt(3, end.getNumeroCasa());
		    	stmtEndereco.setString(4, end.getBairro());
		    	stmtEndereco.setString(5, end.getCidade());
		    	stmtEndereco.setString(6, end.getEstado());
		    	stmtEndereco.setInt(7, idUsuario);
		    	stmtEndereco.executeUpdate();
		    }

		    // Confirmando a transação
		    conn.commit();
		    JOptionPane.showMessageDialog(null, "Conta cadastrada com sucesso!");
		} catch (SQLException e) {
		    // Desfazendo a transação em caso de erros
		    conn.rollback();
		    e.printStackTrace();
		    JOptionPane.showMessageDialog(null, "Erro ao cadastrar conta: " + e.getMessage());
		}
	    } catch (SQLException sqle) {
		sqle.printStackTrace();
		JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + sqle.getMessage());
	    }

	} else if (conta instanceof ContaPoupanca) {
	    ContaPoupanca cp = (ContaPoupanca) conta;
	    Cliente cl = cp.getCliente();

	    // SQL statements
	    String sqlUsuario = "INSERT INTO usuario (nome, cpf, data_nascimento, telefone, tipo_usuario, senha) VALUES (?, ?, ?, ?, 'CLIENTE', ?)";
	    String sqlCliente = "INSERT INTO cliente (id_usuario) VALUES (?)";
	    String sqlConta = "INSERT INTO conta (numero_conta, agencia, tipo_conta, id_cliente) VALUES (?, ?, 'POUPANCA', ?)";
	    String sqlCP = "INSERT INTO conta_poupanca (taxa_rendimento, id_conta) VALUES (?, ?)";
	    String sqlEndereco = "INSERT INTO banco_malvader.endereco (cep, local, numero_casa, bairro, cidade, estado, id_usuario) VALUES (?, ?, ?, ?, ?, ?, ?);";

	    try (Connection conn = ConnectionFactory.conectar()) {
		conn.setAutoCommit(false); // Início da transação

		try {
		    // Inserindo na tabela usuario
		    int idUsuario;
		    try (PreparedStatement stmtUsuario = conn.prepareStatement(sqlUsuario,
			    Statement.RETURN_GENERATED_KEYS)) {
				stmtUsuario.setString(1, cl.getNome_usuario());
				stmtUsuario.setString(2, cl.getCpf_usuario());
				stmtUsuario.setString(3, String.valueOf(cl.getDataNascimento_usuario())); // Formatar
													  // corretamente
				stmtUsuario.setString(4, cl.getTelefone_usuario());
				stmtUsuario.setString(5, cl.getSenha());
	
				stmtUsuario.executeUpdate();
	
				try (ResultSet rs = stmtUsuario.getGeneratedKeys()) {
				    if (rs.next()) {
				    	idUsuario = rs.getInt(1); // ID gerado para usuario
				    } else {
				    	throw new SQLException("Falha ao obter o ID gerado para o usuário.");
				    }
				}
		    }

		    // Inserindo na tabela cliente
		    int idCliente;
		    try (PreparedStatement stmtCliente = conn.prepareStatement(sqlCliente,
			    Statement.RETURN_GENERATED_KEYS)) {
			stmtCliente.setInt(1, idUsuario);
			stmtCliente.executeUpdate();

			try (ResultSet rs = stmtCliente.getGeneratedKeys()) {
			    if (rs.next()) {
				idCliente = rs.getInt(1); // ID gerado para cliente
			    } else {
				throw new SQLException("Falha ao obter o ID gerado para o cliente.");
			    }
			}
		    }

		    // Inserindo na tabela conta
		    int idConta;
		    try (PreparedStatement stmtConta = conn.prepareStatement(sqlConta,
			    Statement.RETURN_GENERATED_KEYS)) {
			stmtConta.setInt(1, cp.getNumero_conta());
			stmtConta.setString(2, cp.getAgencia_conta());
			stmtConta.setInt(3, idCliente); // Associando ao cliente correto
			stmtConta.executeUpdate();

			try (ResultSet rs = stmtConta.getGeneratedKeys()) {
			    if (rs.next()) {
				idConta = rs.getInt(1); // ID gerado para conta
			    } else {
				throw new SQLException("Falha ao obter o ID gerado para a conta.");
			    }
			}
		    }

		    // Inserindo na tabela conta_corrente
		    try (PreparedStatement stmtCP = conn.prepareStatement(sqlCP)) {
			stmtCP.setDouble(1, cp.getTaxaRendimento()); // Definir a taxa de rendimento
			stmtCP.setInt(2, idConta); // Definir o ID da conta
			stmtCP.executeUpdate();
		    }
		    
		    try (PreparedStatement stmtEndereco = conn.prepareStatement(sqlEndereco)) {
		    	stmtEndereco.setString(1, end.getCep());
		    	stmtEndereco.setString(2, end.getLocal());
		    	stmtEndereco.setInt(3, end.getNumeroCasa());
		    	stmtEndereco.setString(4, end.getBairro());
		    	stmtEndereco.setString(5, end.getCidade());
		    	stmtEndereco.setString(6, end.getEstado());
		    	stmtEndereco.setInt(7, idUsuario);
		    	stmtEndereco.executeUpdate();
		    }
		    

		    // Confirmando a transação
		    conn.commit();
		    JOptionPane.showMessageDialog(null, "Conta cadastrada com sucesso!");
		} catch (SQLException e) {
		    // Desfazendo a transação em caso de erro
		    conn.rollback();
		    e.printStackTrace();
		    JOptionPane.showMessageDialog(null, "Erro ao cadastrar conta: " + e.getMessage());
		}
	    } catch (SQLException sqle) {
		sqle.printStackTrace();
		JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + sqle.getMessage());
	    }
	}
    }

    public static void encerrarConta(int numeroConta) {
	String sqlSelectConta = "SELECT * FROM conta WHERE numero_conta = ?;";
	String sqlDeleteConta = "DELETE FROM conta WHERE numero_conta = ?;";

	try (Connection conn = ConnectionFactory.conectar()) {
	    conn.setAutoCommit(false);

	    try (PreparedStatement stmtSelect = conn.prepareStatement(sqlSelectConta)) {
		stmtSelect.setInt(1, numeroConta);

		try (ResultSet rs = stmtSelect.executeQuery()) {
		    if (rs.next()) {
			int idConta = rs.getInt("id_conta");
			String tipo = rs.getString("tipo_conta");
			
			if (tipo.equals("CORRENTE")) {
				String sqlDeleteContaCorrente = "DELETE FROM conta_corrente WHERE id_conta = ?;";

				try (PreparedStatement stmtDeleteCorrente = conn.prepareStatement(sqlDeleteContaCorrente)) {
				    stmtDeleteCorrente.setInt(1, idConta);
				    int rows = stmtDeleteCorrente.executeUpdate();
				    
				    if (rows < 0) {
				    	conn.rollback();
						JOptionPane.showMessageDialog(null, "Erro ao encerrar conta tipo corente: ");
				    }
				}
				
			} else if (tipo.equals("POUPANCA")) {
				String sqlDeleteContaPoupanca = "DELETE FROM conta_poupanca WHERE id_conta = ?;";

				try (PreparedStatement stmtDeleteCorrente = conn.prepareStatement(sqlDeleteContaPoupanca)) {
				    stmtDeleteCorrente.setInt(1, idConta);
				    int rows = stmtDeleteCorrente.executeUpdate();
				    
				    if (rows < 0) {
				    	conn.rollback();
						JOptionPane.showMessageDialog(null, "Erro ao encerrar conta tipo corente: ");
				    }
				}
			}	

			// Excluir da tabela conta
			try (PreparedStatement stmtDeleteConta = conn.prepareStatement(sqlDeleteConta)) {
			    stmtDeleteConta.setInt(1, numeroConta);
			    stmtDeleteConta.executeUpdate();
			}

			conn.commit();
			JOptionPane.showMessageDialog(null, "Conta encerrada com sucesso!");
		    } else {
			JOptionPane.showMessageDialog(null, "Conta não encontrada!");
		    }
		}
	    } catch (SQLException e) {
		conn.rollback();
		e.printStackTrace();
		JOptionPane.showMessageDialog(null, "Erro ao encerrar conta: " + e.getMessage());
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	    JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
	}
    }

    public Conta consultarDadosConta(Conta conta) {
	String sql = "SELECT numero_conta, agencia, saldo, tipo_conta, id_cliente FROM conta WHERE numero_conta = ?;";

	try (Connection conn = ConnectionFactory.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

	    stmt.setInt(1, conta.numero_conta);
	    var rs = stmt.executeQuery();

	    if (rs.next()) {

		conta.numero_conta = rs.getInt("numero_conta");
		conta.agencia_conta = rs.getString("agencia");
		conta.saldo_conta = rs.getDouble("saldo");
		conta.tipo_conta = rs.getString("tipo_conta");
		super.id_usuario = rs.getInt("id_cliente");
		return conta;
	    } else {
		System.out.println("Conta não encontrada.");
		return null;
	    }
	} catch (SQLException sqle) {
	    sqle.printStackTrace();
	    return null;
	}
    }

    public Cliente consultarDadosCliente(Cliente cliente) {
	String sql = "SELECT u.id_usuario, u.nome AS nome_usuario, u.cpf AS cpf_usuario, u.data_nascimento, u.telefone"
		+ "u.senha, e.cep, e.local, e.numero_casa, e.bairro, e.cidade, e.estado FROM usuario"
		+ "u JOIN endereco e ON u.id_usuario = e.id_usuario WHERE u.id_usuario = ?;";

	try (Connection conn = ConnectionFactory.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

	    stmt.setInt(1, id_usuario);
	    var rs = stmt.executeQuery();

	    if (rs.next()) {

		// Criando o objeto Cliente com os dados obtidos
		super.id_usuario = rs.getInt("id_cliente");
		cliente.nome_usuario = rs.getString("nome_usuario");
		cliente.cpf_usuario = rs.getString("cpf_usuario");
		cliente.dataNascimento_usuario = rs.getObject("data_nascimento", LocalDate.class);
		cliente.telefone_usuario = rs.getString("telefone");
		cliente.setSenha(rs.getString("senha"));

		// Criando o objeto Endereco com os dados obtidos
		Endereco endereco = new Endereco();
		endereco.setCep(rs.getString("cep"));
		endereco.setLocal(rs.getString("local"));
		endereco.setNumeroCasa(rs.getInt("numero_casa"));
		endereco.setBairro(rs.getString("bairro"));
		endereco.setCidade(rs.getString("cidade"));
		endereco.setEstado(rs.getString("estado"));

		cliente.endereco = endereco;

		return cliente;
	    } else {
		System.out.println("Cliente não encontrado.");
		return null;
	    }
	} catch (SQLException sqle) {
	    sqle.printStackTrace();
	    return null;
	}
    }

    /*
     * public void alterarDadosConta(Conta conta) { var rs = stmt.executeQuery();
     * String numero_conta = JOptionPane.showInputDialog("Novo nome do titular:",
     * rs.getString("nome")); String novoSaldo =
     * JOptionPane.showInputDialog("Novo saldo da conta:", rs.getString("saldo"));
     * 
     * String sqlAtualizar = "UPDATE Conta SET nome = ?, saldo = ? WHERE id = ?";
     * try (PreparedStatement stmtAtualizar =
     * conexao.prepareStatement(sqlAtualizar)) { stmtAtualizar.setString(1,
     * novoNome); stmtAtualizar.setDouble(2, Double.parseDouble(novoSaldo));
     * stmtAtualizar.setInt(3, Integer.parseInt(idConta)); int linhasAfetadas =
     * stmtAtualizar.executeUpdate();
     * 
     * if (linhasAfetadas > 0) { JOptionPane.showMessageDialog(null,
     * "Dados da conta atualizados com sucesso!"); } else {
     * JOptionPane.showMessageDialog(null, "Falha ao atualizar os dados."); } } }
     * 
     * } catch (SQLException e) { JOptionPane.showMessageDialog(null,
     * "Erro ao conectar ao banco de dados: " + e.getMessage());
     * e.printStackTrace(); } } }
     * 
     * }
     */

    public void alterarDadosCliente(Cliente cliente) {

    }

    public static void cadastrarFuncionario(Funcionario funcionario) {

	String sqlUsuario = "INSERT INTO usuario (nome, cpf, data_nascimento, telefone, tipo_usuario, senha) VALUES (?, ?, ?, ?, 'FUNCIONARIO', ?)";
	String sqlFuncionario = "INSERT INTO funcionario (id_funcionario, codigo_funcionario, cargo, id_usuario) VALUES (?, ?, ?, ?)";

	try (Connection conn = ConnectionFactory.conectar()) {
	    conn.setAutoCommit(false);

	    try (PreparedStatement stmtUsuario = conn.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS)) {
		stmtUsuario.setString(1, funcionario.getNome_usuario());
		stmtUsuario.setString(2, funcionario.getCpf_usuario());
		stmtUsuario.setString(3, String.valueOf(funcionario.getDataNascimento_usuario()));
		stmtUsuario.setString(4, funcionario.getTelefone_usuario());
		stmtUsuario.setString(5, funcionario.getSenha_funcionario());

		stmtUsuario.executeUpdate();

		ResultSet rs = stmtUsuario.getGeneratedKeys();
		int idUsuario = 0;
		if (rs.next()) {
		    idUsuario = rs.getInt(1);
		}

		try (PreparedStatement stmtFuncionario = conn.prepareStatement(sqlFuncionario)) {
		    stmtFuncionario.setInt(1, idUsuario);
		    stmtFuncionario.setString(2, funcionario.getCodigoFuncionario());
		    stmtFuncionario.setString(3, funcionario.getCargo());
		    stmtFuncionario.setInt(4, idUsuario);
		    stmtFuncionario.executeUpdate();
		}

		conn.commit();
		JOptionPane.showMessageDialog(null, "Funcionario cadastrada com sucesso!");
	    } catch (SQLException e) {
		conn.rollback();
		e.printStackTrace();
		JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionario: " + e.getMessage());
	    }
	} catch (SQLException sqle) {
	    sqle.printStackTrace();
	    JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + sqle.getMessage());
	}

    }

    public void gerarRelatorioMovimentacao() {

    }

}
