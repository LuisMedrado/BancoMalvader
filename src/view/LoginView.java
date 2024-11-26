package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Usuario;

public class LoginView {

    public static void main(String[] args) {
	// Configuração da tela principal
	JFrame Tela = new JFrame("Banco_Malvader");
	Tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	Tela.setSize(400, 300);
    Tela.setLocationRelativeTo(null);
	Tela.setLayout(new BorderLayout());

	// Título
	JLabel Titulo = new JLabel("Banco Malvader", SwingConstants.CENTER);
	Titulo.setFont(new Font("Arial", Font.BOLD, 18));
	Titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
	Tela.add(Titulo, BorderLayout.NORTH);

	// Painel central para formulário
	JPanel PainelCentral = new JPanel();
	PainelCentral.setLayout(new GridLayout(4, 2, 10, 10));

	// Campo para CPF
	PainelCentral.add(new JLabel("CPF:"));
	JTextField CampoCPF = new JTextField(15);
	PainelCentral.add(CampoCPF);

	// Campo para Senha
	PainelCentral.add(new JLabel("Senha:"));
	JPasswordField CampoSenha = new JPasswordField(15);
	PainelCentral.add(CampoSenha);

	// Seletor de tipo de usuário
	PainelCentral.add(new JLabel("Tipo de Usuário:"));
	String[] Opcoes = { "Cliente", "Funcionario" };
	JComboBox<String> Selecao = new JComboBox<>(Opcoes);
	PainelCentral.add(Selecao);

	// Botão Enviar
	JButton BotaoEnviar = new JButton("Login");
	PainelCentral.add(new JLabel()); // Espaço vazio para alinhamento
	PainelCentral.add(BotaoEnviar);

	// Adiciona painel à tela
	Tela.add(PainelCentral, BorderLayout.CENTER);

	// Ação do botão de login
	BotaoEnviar.addActionListener(e -> {
	    String cpf = CampoCPF.getText().trim();
	    String senha = new String(CampoSenha.getPassword());
	    String tipoUsuario = (String) Selecao.getSelectedItem();

	    // Validar login para Cliente ou Funcionário
	    if (Usuario.validarLogin(cpf, senha, tipoUsuario)) {
		JOptionPane.showMessageDialog(Tela, "Login bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		Tela.dispose();

		// Redireciona conforme o tipo de usuário
		if (tipoUsuario.equals("Cliente")) {
//			Cliente cliente = new Cliente();
//		    MenuClienteView.
		} else {
		    MenuFuncionarioView.main(new String[] {});
		}
	    } else {
		JOptionPane.showMessageDialog(Tela, "CPF ou senha incorretos", "Erro", JOptionPane.ERROR_MESSAGE);
	    }
	});

	// Torna a tela visível
	Tela.setVisible(true);
    }
}
