package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.SQLException;

import javax.swing.border.EtchedBorder;

import dao.ContaDAO;
import model.Cliente;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class SaqueView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Valor;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaqueView frame = new SaqueView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public SaqueView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(400, 300);
	    setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Saque");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(158, 27, 48, 19);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(48, 67, 275, 155);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Valor do saque:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(59, 23, 139, 13);
		panel.add(lblNewLabel_1);
		
		Valor = new JTextField();
		Valor.setBounds(81, 46, 96, 19);
		panel.add(Valor);
		Valor.setColumns(10);
		
		JButton Confirmar = new JButton("Confirmar");
		Confirmar.setBounds(81, 94, 96, 21);
		panel.add(Confirmar);
		
		Confirmar.addActionListener(e -> {
			//SO TESTANDO
			Double valor = Double.parseDouble(Valor.getText());
			//this.usuario_logado.saque(valor, numero_conta);
			try {
				ContaDAO.saque(valor, 100001); //aqui vai ser o numero da conta de quem tiver logado;
	    		JOptionPane.showMessageDialog(this, "Saque realizado!");
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
		});
	}
}
