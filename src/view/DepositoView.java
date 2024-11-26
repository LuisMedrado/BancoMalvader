package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Cliente;
import model.Conta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.border.EtchedBorder;

import dao.ContaDAO;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BorderFactory;

public class DepositoView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Cliente cliente_logado;
	private JTextField Valor;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					DepositoView frame = new DepositoView();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public DepositoView(Cliente cliente) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.cliente_logado = cliente;
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDeposito = new JLabel("Deposito");
		lblDeposito.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeposito.setForeground(new Color(0, 102, 204));
		lblDeposito.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDeposito.setBounds(155, 48, 90, 19);
		contentPane.add(lblDeposito);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(68, 77, 275, 155);
		contentPane.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("Valor do deposito:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(0, 102, 204));
		lblNewLabel_1.setBounds(59, 23, 139, 13);
		panel.add(lblNewLabel_1);
		
		Valor = new JTextField();
		Valor.setColumns(10);
		Valor.setBounds(81, 46, 96, 19);
		panel.add(Valor);
		
		JButton Confirmar = new JButton("Confirmar");
		Confirmar.setForeground(Color.WHITE);
		Confirmar.setFont(new Font("Arial", Font.BOLD, 10));
		Confirmar.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
		Confirmar.setBackground(new Color(0, 102, 204));
		Confirmar.setBounds(76, 91, 111, 21);
		panel.add(Confirmar);
		
		JButton Voltar = new JButton("Voltar");
		Voltar.setForeground(Color.WHITE);
		Voltar.setFont(new Font("Arial", Font.BOLD, 10));
		Voltar.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
		Voltar.setBackground(new Color(0, 102, 204));
		Voltar.setBounds(86, 124, 91, 21);
		panel.add(Voltar);
		
		Voltar.addActionListener(e -> {
			MenuClienteView tela = new MenuClienteView(this.cliente_logado);
			tela.setVisible(true);
			this.dispose();
		});
		
		Confirmar.addActionListener(e -> {
			Double valor = Double.parseDouble(Valor.getText());
			try {
				Conta conta = ContaDAO.findByIdCliente(this.cliente_logado.getId_usuario());
				
				this.cliente_logado.depositar(conta, valor);
				JOptionPane.showMessageDialog(this, "Depósito realizado!"); 
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		
	}

}
