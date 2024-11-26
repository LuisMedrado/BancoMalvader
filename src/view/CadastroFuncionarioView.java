package view;

import java.awt.*;
import java.time.LocalDate;

import javax.swing.*;

import model.Cliente;
import model.ContaCorrente;
import model.Endereco;
import model.Funcionario;

public class CadastroFuncionarioView {
	
	public static void main(String[] args) {
	    JFrame Tela = new JFrame("Banco Malvader");
	    Tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    Tela.setSize(400, 300); 
	    Tela.setLocationRelativeTo(null);
	    Tela.setLayout(new BorderLayout());

	    JLabel Titulo = new JLabel("Banco Malvader", SwingConstants.CENTER);
	    Titulo.setFont(new Font("Arial", Font.BOLD, 15));
	    Titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
	    
	    Tela.add(Titulo, BorderLayout.NORTH);
	      

	    JPanel Principal = new JPanel();
	    Principal.setLayout(new GridLayout(9, 2, 10, 5));

	    Principal.add(new JLabel("Codigo Funcionario: "));
	    JTextField CodigoFuncionario = new JTextField(20);
	    Principal.add(CodigoFuncionario);

	    Principal.add(new JLabel("Cargo: "));
	    JTextField Cargo = new JTextField(20);
	    Principal.add(Cargo);

	    Principal.add(new JLabel("Nome: "));
	    JTextField Nome = new JTextField(20);
	    Principal.add(Nome);

	    Principal.add(new JLabel("CPF:"));
	    JTextField CPF = new JTextField(15);
	    Principal.add(CPF);

	    Principal.add(new JLabel("Data de Nascimento:"));
	    JTextField Nascimento = new JTextField(10);
	    Principal.add(Nascimento);

	    Principal.add(new JLabel("Telefone: "));
	    JTextField Telefone = new JTextField(15);
	    Principal.add(Telefone);

	    Principal.add(new JLabel("Endereco Completo:"));
	    JTextField Endereco = new JTextField(30);
	    Principal.add(Endereco);

	    Principal.add(new JLabel("Senha: "));
	    JPasswordField Senha = new JPasswordField(15);
	    Senha.setEchoChar('*'); 
	    Principal.add(Senha);

	    JPanel PainelBotao = new JPanel();
	    JButton Enviar = new JButton("Enviar");
	    Enviar.setFont(new Font("Arial", Font.BOLD, 16));
	    PainelBotao.add(Enviar);
	    
	    Tela.add(PainelBotao, BorderLayout.SOUTH);


	    Tela.add(Principal, BorderLayout.CENTER);
	    
	    JButton Voltar = new JButton("Voltar");
        Voltar.setFont(new Font("Arial", Font.BOLD, 16));
        PainelBotao.add(Voltar);
        
        Voltar.addActionListener(env -> {            
		    MenuFuncionarioView.main(new String[] {});
		    Tela.dispose();
        });
	    
	    Enviar.addActionListener(env -> {
            Tela.dispose();
            
            String Codigo = CodigoFuncionario.getText();
            String cargo = Cargo.getText();
            String nome = Nome.getText();
            String cpf = CPF.getText();
            LocalDate dataNasc = LocalDate.parse(Nascimento.getText());
            String telefone = Telefone.getText();
            char[] senha = Senha.getPassword();
            String senhaString = new String(senha);
            Endereco end = new Endereco(); // aqui é onde vai setar o endereco quando tiver (por enquanto ta nulo)

            Funcionario c = new Funcionario(0, nome, cpf, dataNasc, telefone, end, Codigo, cargo, senhaString);

            Funcionario.cadastrarFuncionario(c);
        });

	    Tela.setVisible(true);
	    
    }

}
