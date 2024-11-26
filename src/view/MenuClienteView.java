package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;

import dao.ClienteDAO;
import dao.ContaDAO;
import model.Cliente;
import model.Conta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JToggleButton;

public class MenuClienteView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Cliente cliente_logado; 

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MenuClienteView frame = new MenuClienteView();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public MenuClienteView(Cliente cliente) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
        setSize(500, 400); 
        setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.cliente_logado = cliente;
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String[] elemento = this.cliente_logado.getNome_usuario().split(" ");
        String nome = elemento[0];
        String sobrenome = elemento[1];
		
		JLabel Ola = new JLabel("Bem vindo, " + nome + " " + sobrenome);
		Ola.setBounds(42, 21, 264, 13);
		Ola.setFont(new Font("Arial", Font.BOLD, 14));
		Ola.setForeground(new Color(0, 102, 204));
		contentPane.add(Ola);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(83, 44, 291, 309);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton Deposito = new JButton("Depósito");
		Deposito.setBounds(67, 137, 157, 21);
		Deposito.setFont(new Font("Arial", Font.BOLD, 14));
		Deposito.setBackground(new Color(0, 102, 204));
		Deposito.setForeground(Color.WHITE);
		Deposito.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
		panel.add(Deposito);
		
		JButton Saque = new JButton("Saque");
		Saque.setBounds(67, 94, 157, 21);
		Saque.setFont(new Font("Arial", Font.BOLD, 14));
		Saque.setBackground(new Color(0, 102, 204));
		Saque.setForeground(Color.WHITE);
		Saque.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));			
		panel.add(Saque);
		
		JButton Extrato = new JButton("Extrato");
		Extrato.setBounds(67, 219, 158, 21);
		Extrato.setFont(new Font("Arial", Font.BOLD, 14));
		Extrato.setBackground(new Color(0, 102, 204));
		Extrato.setForeground(Color.WHITE);
		Extrato.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));		
		panel.add(Extrato);
		
		JButton Limite = new JButton("Consultar Limite");
		Limite.setBounds(67, 180, 157, 21);
		Limite.setFont(new Font("Arial", Font.BOLD, 14));
		Limite.setBackground(new Color(0, 102, 204));
		Limite.setForeground(Color.WHITE);
		Extrato.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));		
		panel.add(Limite);
		
		JButton Sair = new JButton("Sair");
		Sair.setBounds(98, 264, 91, 21);
		Sair.setFont(new Font("Arial", Font.BOLD, 14));
		Sair.setBackground(new Color(0, 102, 204));
		Sair.setForeground(Color.WHITE);
		Sair.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));		
		panel.add(Sair);
		
		JToggleButton Saldo = new JToggleButton("---");
		Saldo.setBounds(85, 17, 119, 41);
		Saldo.setFont(new Font("Arial", Font.BOLD, 14));
		Saldo.setBackground(new Color(0, 102, 204));
		Saldo.setForeground(Color.WHITE);
		Saldo.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));	
		panel.add(Saldo);
		
		Saldo.addActionListener(e -> {
			AbstractButton saldo = (AbstractButton)e.getSource();
	        boolean a = saldo.getModel().isSelected();
	        if (a) {
	            String senha = JOptionPane.showInputDialog(this, "Digite a senha de"
	                + "usuário para vizializar o saldo.", "Confirmar usuário.", JOptionPane.INFORMATION_MESSAGE);
	                
	            if (senha.equals("123")) {  
	            	try {
						Conta conta = ContaDAO.findByIdCliente(this.cliente_logado.getId_usuario());
		                saldo.setText(" " + conta.getSaldo_conta()); 	//vai mostrar o saldo do usuario logado

					} catch (Exception e1) {
						e1.printStackTrace();
					}
	            } else {
	                JOptionPane.showMessageDialog(this, "Senha incorreta!", "Tente novamente.", JOptionPane.ERROR_MESSAGE);
	            }
	        } else {
	            saldo.setText("---");
	        }
		});
		
		Saque.addActionListener(e -> {
			SaqueView tela = new SaqueView(this.cliente_logado);
			tela.setVisible(true);
			this.dispose();
		});
		
		Deposito.addActionListener(e -> {
			DepositoView tela = new DepositoView(this.cliente_logado);
			tela.setVisible(true);
			this.dispose();
		});
		
		Limite.addActionListener(e -> {
			
		});
		
		Extrato.addActionListener(e -> {
			
		});
		
		Sair.addActionListener(e -> {
			System.exit(0);
		});
	}
}
