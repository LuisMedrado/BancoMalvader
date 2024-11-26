package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;

import model.Cliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(110, 69, 217, 260);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton Deposito = new JButton("Depósito");
		Deposito.setBounds(30, 123, 144, 21);
		panel.add(Deposito);
		
		JButton Saque = new JButton("Saque");
		Saque.setBounds(30, 92, 144, 21);
		panel.add(Saque);
		
		JButton Extrato = new JButton("Extrato");
		Extrato.setBounds(30, 185, 144, 21);
		panel.add(Extrato);
		
		JButton Limite = new JButton("Consultar Limite");
		Limite.setBounds(30, 154, 144, 21);
		panel.add(Limite);
		
		JButton Sair = new JButton("Sair");
		Sair.setBounds(72, 216, 76, 21);
		panel.add(Sair);
		
		JToggleButton Saldo = new JToggleButton("---");
		Saldo.setBounds(60, 21, 88, 45);
		panel.add(Saldo);
		
		JLabel lblNewLabel = new JLabel("Opções");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(191, 34, 46, 25);
		contentPane.add(lblNewLabel);
		
		Saldo.addActionListener(e -> {
			AbstractButton saldo = (AbstractButton)e.getSource();
	        boolean a = saldo.getModel().isSelected();
	        if (a) {
	            String senha = JOptionPane.showInputDialog(this, "Digite a senha de"
	                + "usuário para vizializar o saldo.", "Confirmar usuário.", JOptionPane.INFORMATION_MESSAGE);
	                
	            if (senha.equals("123")) {  	//vai ser a senha do usuario
	                saldo.setText("arrumar"); 	//vai mostrar o saldo do usuario logado
	            } else {
	                JOptionPane.showMessageDialog(this, "Senha incorreta!", "Tente novamente.", JOptionPane.ERROR_MESSAGE);
	            }
	        } else {
	            saldo.setText("---");
	        }
		});
		
		Saque.addActionListener(e -> {
			SaqueView.main(null);
		});
		
		Deposito.addActionListener(e -> {
			
		});
		
		Limite.addActionListener(e -> {
			
		});
		
		Extrato.addActionListener(e -> {
			
		});
		
		Sair.addActionListener(e -> {
			
		});
	}
}
