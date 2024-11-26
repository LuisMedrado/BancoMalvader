package view;

import java.awt.*;
import javax.swing.*;

import dao.ClienteDAO;
import model.Cliente;
import model.Usuario;

public class LoginView {

    public static void main(String[] args) {
        // Configuração da tela principal
        JFrame Tela = new JFrame("Banco Malvader - Login");
        Tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Tela.setSize(450, 350);
        Tela.setLayout(new BorderLayout());

        // Título estilizado
        JLabel Titulo = new JLabel("Banco Malvader", SwingConstants.CENTER);
        Titulo.setFont(new Font("Verdana", Font.BOLD, 22));
        Titulo.setForeground(new Color(0, 102, 204));
        Titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        Tela.add(Titulo, BorderLayout.NORTH);

        // Painel central para o formulário com espaçamento ajustado
        JPanel PainelCentral = new JPanel();
        PainelCentral.setLayout(new GridLayout(4, 2, 10, 10));
        PainelCentral.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Campo para CPF
        JLabel LabelCPF = new JLabel("CPF:");
        LabelCPF.setFont(new Font("Arial", Font.PLAIN, 14));
        PainelCentral.add(LabelCPF);
        JTextField CampoCPF = new JTextField(15);
        CampoCPF.setFont(new Font("Arial", Font.PLAIN, 14));
        PainelCentral.add(CampoCPF);

        // Campo para Senha
        JLabel LabelSenha = new JLabel("Senha:");
        LabelSenha.setFont(new Font("Arial", Font.PLAIN, 14));
        PainelCentral.add(LabelSenha);
        JPasswordField CampoSenha = new JPasswordField(15);
        CampoSenha.setFont(new Font("Arial", Font.PLAIN, 14));
        PainelCentral.add(CampoSenha);

        // Seletor de tipo de usuário
        JLabel LabelTipoUsuario = new JLabel("Tipo de Usuário:");
        LabelTipoUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        PainelCentral.add(LabelTipoUsuario);
        String[] Opcoes = {"Cliente", "Funcionário"};
        JComboBox<String> Selecao = new JComboBox<>(Opcoes);
        Selecao.setFont(new Font("Arial", Font.PLAIN, 14));
        PainelCentral.add(Selecao);

        // Botão Enviar com alinhamento e estilo ajustado
        PainelCentral.add(new JLabel()); // Espaço vazio para alinhamento
        JButton BotaoEnviar = new JButton("Login");
        BotaoEnviar.setFont(new Font("Arial", Font.BOLD, 14));
        BotaoEnviar.setBackground(new Color(0, 102, 204));
        BotaoEnviar.setForeground(Color.WHITE);
        BotaoEnviar.setFocusPainted(false);
        PainelCentral.add(BotaoEnviar);

        // Adiciona o painel central à tela
        Tela.add(PainelCentral, BorderLayout.CENTER);

        // Ação do botão de login
    	BotaoEnviar.addActionListener(e -> {
    	    String cpf = CampoCPF.getText().trim();
    	    String senha = new String(CampoSenha.getPassword());
    	    String tipoUsuario = (String) Selecao.getSelectedItem();

    	    // Validar login para Cliente ou Funcionário
    	    if (Usuario.validarLogin(cpf, senha, tipoUsuario)) {
    		Tela.dispose();

    		// Redireciona conforme o tipo de usuário
    		if (tipoUsuario.equals("Cliente")) {
    			Cliente cliente = ClienteDAO.findbyCpf(cpf);
    			
    			MenuClienteView frame = new MenuClienteView(cliente);
    			frame.setVisible(true);
    			Tela.dispose();
    			
        		JOptionPane.showMessageDialog(Tela, "Login bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    		} else {
    		    MenuFuncionarioView.main(new String[] {});
        		JOptionPane.showMessageDialog(Tela, "Login bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    		}
    	    } else {
    		JOptionPane.showMessageDialog(Tela, "CPF ou senha incorretos", "Erro", JOptionPane.ERROR_MESSAGE);
    	    }
    	});

        // Torna a tela visível e centraliza na tela
        Tela.setLocationRelativeTo(null);
        Tela.setVisible(true);
    }
}
