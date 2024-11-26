package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dao.ConnectionFactory;

public class MenuFuncionarioView {
    public static void main(String[] args) {
	JFrame Tela = new JFrame("Banco Malvader");
	Tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	Tela.setSize(400, 300);
    Tela.setLocationRelativeTo(null);

	Tela.setLayout(new BorderLayout());

	JLabel Titulo = new JLabel("Banco Malvader", SwingConstants.CENTER);
	Titulo.setFont(new Font("Arial", Font.BOLD, 15));
	Titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

	Tela.add(Titulo, BorderLayout.NORTH);

	JButton AberturaConta = new JButton("Abertura de Conta");
	AberturaConta.setFont(new Font("Arial", Font.PLAIN, 16));

	AberturaConta.addActionListener(e -> {
	    Tela.dispose();
	    CadastroContaView.main(new String[] {});
	});

	JPanel PainelCentral = new JPanel();

	PainelCentral.add(AberturaConta);

	Tela.add(PainelCentral, BorderLayout.CENTER);

	JButton EncerramentoContaBT = new JButton("Encerramento de Conta");
	EncerramentoContaBT.setFont(new Font("Arial", Font.PLAIN, 16));

	EncerramentoContaBT.addActionListener(e -> {
	    Tela.dispose();
	    EncerramentoConta.main(new String[] {});
	});

	PainelCentral.add(EncerramentoContaBT);

	JButton ConsultaDados = new JButton("Consulta de Dados");
	ConsultaDados.setFont(new Font("Arial", Font.PLAIN, 16));

	ConsultaDados.addActionListener(ec -> {

	    JFrame frameEntrada = new JFrame("Consultar dados");
	    frameEntrada.setSize(300, 150);
	    frameEntrada.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    frameEntrada.setLayout(null);

	    JLabel labelEntrada = new JLabel("Digite o número da conta do cliente a ser consultado:");
	    labelEntrada.setBounds(20, 20, 100, 25);
	    frameEntrada.add(labelEntrada);

	    JTextField textField = new JTextField();
	    textField.setBounds(120, 20, 150, 25);
	    frameEntrada.add(textField);

	    JButton button = new JButton("Enviar");
	    button.setBounds(100, 60, 100, 25);
	    frameEntrada.add(button);

	    button.addActionListener(ent -> {
		String inputText = textField.getText();
		int inputInt = Integer.parseInt(inputText);

		String sql = "SELECT numero_conta, agencia, saldo, tipo_conta, id_cliente FROM conta WHERE numero_conta = ?;";

		try (Connection conn = ConnectionFactory.conectar();
			PreparedStatement stmt = conn.prepareStatement(sql)) {

		    stmt.setInt(1, inputInt);
		    var rs = stmt.executeQuery();

		    if (rs.next()) {

			int numero_conta = rs.getInt("numero_conta");
			String agencia_conta = rs.getString("agencia");
			double saldo_conta = rs.getDouble("saldo");
			String tipo_conta = rs.getString("tipo_conta");
			int id_usuario = rs.getInt("id_cliente");

			JOptionPane.showMessageDialog(frameEntrada,
				"número da conta: " + numero_conta + ",\n" + "agência da conta: " + agencia_conta
					+ ",\n" + "saldo da conta: " + saldo_conta + ",\n" + "tipo da conta: "
					+ tipo_conta + ",\n" + "id do usuário: " + id_usuario + ".");
		    } else {
			System.out.println("Conta não encontrada.");
		    }
		} catch (SQLException sqle) {
		    sqle.printStackTrace();
		    //System.exit(0);
		}
	    });

	    frameEntrada.setVisible(true);

	});

	PainelCentral.add(ConsultaDados);

	JButton AlteracaoDados = new JButton("Alteracao de Dados");
	AlteracaoDados.setFont(new Font("Arial", Font.PLAIN, 16));

	PainelCentral.add(AlteracaoDados);

	JButton GeracaoRelatorio = new JButton("Geracao de Relatorio");
	GeracaoRelatorio.setFont(new Font("Arial", Font.PLAIN, 16));

	PainelCentral.add(GeracaoRelatorio);

	JButton CadastrarFuncionario = new JButton("Novo Funcionario");
	CadastrarFuncionario.setFont(new Font("Arial", Font.PLAIN, 16));

	CadastrarFuncionario.addActionListener(e -> {
		String input = JOptionPane.showInputDialog(Tela, "Digite a senha do"
                + "administrador continuar.", "Confirmar permissão.", JOptionPane.INFORMATION_MESSAGE);
		if (input.equals("123")) {
			Tela.dispose();
		    CadastroFuncionarioView.main(new String[] {});
		} else {
            JOptionPane.showMessageDialog(Tela, "Senha incorreta!", "Tente novamente.", JOptionPane.ERROR_MESSAGE);
		}
	});

	PainelCentral.add(CadastrarFuncionario);

	JButton Sair = new JButton("Sair");
	Sair.setFont(new Font("Arial", Font.PLAIN, 16));
	
	Sair.addActionListener(e -> {
		System.exit(0);
	});

	PainelCentral.add(Sair);

	Tela.setVisible(true);
    }

}
