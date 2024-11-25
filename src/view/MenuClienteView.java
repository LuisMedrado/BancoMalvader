package view;

import java.awt.*;
import javax.swing.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuClienteView {
/*public static void main(String[] args) {

        JFrame Tela = new JFrame("Banco Malvader");
        Tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        Tela.setSize(400, 300); 
        Tela.setLayout(new BorderLayout());

        JLabel Titulo = new JLabel("Banco Malvader", SwingConstants.CENTER);
        Titulo.setFont(new Font("Arial", Font.BOLD, 15));
        Titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        Tela.add(Titulo, BorderLayout.NORTH);

        JPanel PainelCentral = new JPanel();
        PainelCentral.setLayout(new GridLayout(6, 1, 10, 10));

        Tela.add(PainelCentral, BorderLayout.CENTER);

        JButton OperacoesConta = new JButton("Oparacao de Conta");
        OperacoesConta.setFont(new Font("Arial", Font.PLAIN, 16));

        PainelCentral.add(OperacoesConta); 

        JButton Sair = new JButton("Sair");
        Sair.setFont(new Font("Arial", Font.PLAIN, 16));

        PainelCentral.add(Sair); 
        
        Tela.setVisible(true);
     }
*/

	    private JFrame frame;

	    /**
	     * Launch the application.
	     */
	    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		    public void run() {
			try {
			    MenuClienteView window = new MenuClienteView();
			    window.frame.setVisible(true);
			} catch (Exception e) {
			    e.printStackTrace();
			}
		    }
		});
	    }

	    /**
	     * Create the application.
	     */
	    public MenuClienteView() {
		initialize();
	    }

	    /**
	     * Initialize the contents of the frame.
	     */
	    private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 316, 333);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton botaoRetornar = new JButton("Retornar ao menu");
		botaoRetornar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    }
		});
		botaoRetornar.setBounds(79, 154, 140, 23);
		frame.getContentPane().add(botaoRetornar);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Operações de conta");
		menuBar.add(mnNewMenu);

		JMenuItem menuSaldo = new JMenuItem("Saldo");
		menuSaldo.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    }
		});
		mnNewMenu.add(menuSaldo);

		JMenuItem menuSaque = new JMenuItem("Saque");
		menuSaque.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    }
		});
		mnNewMenu.add(menuSaque);

		JMenuItem menuDeposito = new JMenuItem("Depósito");
		menuDeposito.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    }
		});
		mnNewMenu.add(menuDeposito);

		JMenuItem menuExtrato = new JMenuItem("Extrato");
		menuExtrato.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    }
		});
		mnNewMenu.add(menuExtrato);

		JMenuItem menuLimite = new JMenuItem("Consultar limite");
		menuLimite.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    }
		});
		mnNewMenu.add(menuLimite);
	    }
}
	
	