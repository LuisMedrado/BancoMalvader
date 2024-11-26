package view;

import java.awt.*;
import javax.swing.*;

import model.Funcionario;

public class EncerramentoConta {
	public static void main(String[] args) {

        JFrame Tela = new JFrame("Banco Malvader");
        Tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        Tela.setSize(400, 200); 
        Tela.setLocationRelativeTo(null);
        Tela.setLayout(new BorderLayout()); 

        JLabel Titulo = new JLabel("Banco Malvader", SwingConstants.CENTER);
        Titulo.setFont(new Font("Arial", Font.BOLD, 15)); 
        Titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); 
        Tela.add(Titulo, BorderLayout.NORTH); 

        JPanel Principal = new JPanel();
        Principal.setLayout(new GridLayout(12, 2, 10, 5));

        Principal.add(new JLabel("Numero da conta:"));
        JTextField Numero = new JTextField(20);
        Principal.add(Numero);

        Principal.add(new JLabel("Senha: "));
        JPasswordField Senha = new JPasswordField(15);
        Senha.setEchoChar('*'); 
        Principal.add(Senha);

        JPanel PainelBotao = new JPanel();
        JButton Enviar = new JButton("Enviar");
        Enviar.setFont(new Font("Arial", Font.BOLD, 16));
        PainelBotao.add(Enviar);
        
        JButton Voltar = new JButton("Voltar");
        Voltar.setFont(new Font("Arial", Font.BOLD, 16));
        PainelBotao.add(Voltar);
        
        Tela.add(PainelBotao, BorderLayout.SOUTH);     
        
        Enviar.addActionListener(env -> {            
            String senha = String.valueOf(Senha.getPassword());
            	
            if(senha.equals("123")) {
            	Funcionario.encerrarConta(Integer.parseInt(Numero.getText()));
            } else {
            	JOptionPane.showMessageDialog(Tela, "Tente novamente.", "Senha incorreta!", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        Voltar.addActionListener(env -> {            
		    MenuFuncionarioView.main(new String[] {});
		    Tela.dispose();
        });
       
        Tela.add(new JScrollPane(Principal), BorderLayout.CENTER);
        Tela.setVisible(true);
    }

}
