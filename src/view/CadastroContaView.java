/*package view;

import java.awt.*;
import javax.swing.*;

public class CadastroContaView {

	public static void main(String[] args) {
        JFrame Tela = new JFrame("Banco Malvader");
      Tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
      Tela.setSize(400, 300); 
      Tela.setLayout(new BorderLayout());

      JLabel Titulo = new JLabel("Banco Malvader", SwingConstants.CENTER);
      Titulo.setFont(new Font("Arial", Font.BOLD, 15));
      Titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
      
      Tela.add(Titulo, BorderLayout.NORTH);

      JPanel PainelConta = new JPanel();
      PainelConta.setLayout(new FlowLayout()); 
      PainelConta.setBorder(BorderFactory.createTitledBorder("Escolha o tipo de conta"));
      
      JRadioButton ContaPoupanca = new JRadioButton("Conta Poupanca (CP)");
      JRadioButton ContaCorrente = new JRadioButton("Conta Corrente (CC)");
      
      JButton Escolher = new JButton("Escolher");
      Escolher.setFont(new Font("Arial", Font.PLAIN, 16));

      ButtonGroup GrupoContas = new ButtonGroup();
      GrupoContas.add(ContaPoupanca);
      GrupoContas.add(ContaCorrente);
      
      PainelConta.add(ContaPoupanca);
      PainelConta.add(ContaCorrente);
      PainelConta.add(Escolher);



      Tela.add(PainelConta, BorderLayout.CENTER);


      Tela.setVisible(true);   
  }

	
}
*/

package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroContaView {

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

        JPanel PainelConta = new JPanel();
        PainelConta.setLayout(new FlowLayout());
        PainelConta.setBorder(BorderFactory.createTitledBorder("Escolha o tipo de conta"));

        JRadioButton ContaPoupanca = new JRadioButton("Conta Poupança (CP)");
        JRadioButton ContaCorrente = new JRadioButton("Conta Corrente (CC)");

        JButton Escolher = new JButton("Escolher");
        Escolher.setFont(new Font("Arial", Font.PLAIN, 16));

        ButtonGroup GrupoContas = new ButtonGroup();
        GrupoContas.add(ContaPoupanca);
        GrupoContas.add(ContaCorrente);

        PainelConta.add(ContaPoupanca);
        PainelConta.add(ContaCorrente);
        PainelConta.add(Escolher);
        Tela.add(PainelConta, BorderLayout.CENTER);
        
        JButton Voltar = new JButton("Voltar");
        Voltar.setFont(new Font("Arial", Font.BOLD, 16));
        PainelConta.add(Voltar);
        
        Voltar.addActionListener(env -> {            
        	MenuFuncionarioView.main(new String[] {});
    	    Tela.dispose();
        });

        // Adiciona ActionListener ao botão "Escolher"
        Escolher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ContaCorrente.isSelected()) {
                    CadastroContaCorrente.main(new String[]{}); // Abre a classe CadastroContaCorrente
                    Tela.dispose(); // Fecha a tela atual
                } else if (ContaPoupanca.isSelected()) {
                    CadastroContaPoupanca.main(new String[]{}); // Abre a classe CadastroContaPoupanca
                    Tela.dispose(); // Fecha a tela atual
                } else {
                    JOptionPane.showMessageDialog(Tela, "Selecione um tipo de conta!", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        Tela.setVisible(true);
    }
}
