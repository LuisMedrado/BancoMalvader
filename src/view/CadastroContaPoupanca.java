package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Cliente;
import model.ContaPoupanca;
import model.Endereco;
import model.Funcionario;

public class CadastroContaPoupanca {
    public static void main(String[] args) {
	JFrame Tela = new JFrame("Banco Malvader");
	Tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	Tela.setSize(600, 600);
	Tela.setLayout(new BorderLayout());

	JLabel Titulo = new JLabel("Banco Malvader", SwingConstants.CENTER);
	Titulo.setFont(new Font("Arial", Font.BOLD, 15));
	Titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

	Tela.add(Titulo, BorderLayout.NORTH);

	JPanel Principal = new JPanel();
	Principal.setLayout(new GridLayout(12, 2, 10, 5));

	Principal.add(new JLabel("ID:"));
	JTextField IdCliente = new JTextField(20);
	Principal.add(IdCliente);

	Principal.add(new JLabel("Agencia:"));
	JTextField Agencia = new JTextField(20);
	Principal.add(Agencia);

	Principal.add(new JLabel("Numero da conta:"));
	JTextField Numero = new JTextField(20);
	Principal.add(Numero);

	Principal.add(new JLabel("Nome do Cliente:"));
	JTextField Nome = new JTextField(20);
	Principal.add(Nome);

	Principal.add(new JLabel("CPF: "));
	JTextField CPF = new JTextField(20);
	Principal.add(CPF);

	Principal.add(new JLabel("Data de Nascimento: "));
	JTextField DataNascimento = new JTextField(20);
	Principal.add(DataNascimento);

	Principal.add(new JLabel("Telefone: "));
	JTextField Telefone = new JTextField(20);
	Principal.add(Telefone);

	Principal.add(new JLabel("Senha: "));
	JPasswordField Senha = new JPasswordField(15);
	Senha.setEchoChar('*');
	Principal.add(Senha);

	Principal.add(new JLabel("Taxa de Juros: "));
	JTextField TaxaJuros = new JTextField(20);
	Principal.add(TaxaJuros);

	JPanel PainelBotao = new JPanel();
	JButton Enviar = new JButton("Enviar");
	Enviar.setFont(new Font("Arial", Font.BOLD, 16));
	PainelBotao.add(Enviar);

	Enviar.addActionListener(env -> {
	    Tela.dispose();

	    ContaPoupanca cp = new ContaPoupanca();
	    cp.setAgencia_conta(Agencia.getText());
	    cp.setNumero_conta(Integer.parseInt(Numero.getText()));
	    cp.setTaxaJuros(Double.parseDouble(TaxaJuros.getText()));

	    String id = IdCliente.getText();
	    int idCliente = Integer.parseInt(id);

	    String nome = Nome.getText();
	    String cpf = CPF.getText();
	    LocalDate dataNasc = LocalDate.parse(DataNascimento.getText());
	    String telefone = Telefone.getText();
	    char[] senha = Senha.getPassword();
	    String senhaString = new String(senha);
	    Endereco end = new Endereco(); // aqui é onde vai setar o endereco quando tiver (por enquanto tá nulo)

	    Cliente c = new Cliente(idCliente, nome, cpf, dataNasc, telefone, end, senhaString);
	    cp.setCliente(c);

	    Funcionario.abrirConta(cp);
	});

	Tela.add(new JScrollPane(Principal), BorderLayout.CENTER);
	Tela.add(PainelBotao, BorderLayout.SOUTH);

	Tela.setVisible(true);
    }
}