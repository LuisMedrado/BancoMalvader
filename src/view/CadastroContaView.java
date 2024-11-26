package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CadastroContaView {

    public static void main(String[] args) {
        JFrame Tela = new JFrame("Banco Malvader - Cadastro de Conta");
        Tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Tela.setSize(450, 350);
        Tela.setLayout(new BorderLayout());

        // Título estilizado
        JLabel Titulo = new JLabel("Cadastro de Conta", SwingConstants.CENTER);
        Titulo.setFont(new Font("Verdana", Font.BOLD, 22));
        Titulo.setForeground(new Color(0, 102, 204));
        Titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        Tela.add(Titulo, BorderLayout.NORTH);

        // Painel central para seleção de conta
        JPanel PainelConta = new JPanel();
        PainelConta.setLayout(new GridLayout(3, 1, 10, 10));
        PainelConta.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Botões de rádio estilizados
        JRadioButton ContaPoupanca = new JRadioButton("Conta Poupança (CP)");
        ContaPoupanca.setFont(new Font("Arial", Font.PLAIN, 14));

        JRadioButton ContaCorrente = new JRadioButton("Conta Corrente (CC)");
        ContaCorrente.setFont(new Font("Arial", Font.PLAIN, 14));

        ButtonGroup GrupoContas = new ButtonGroup();
        GrupoContas.add(ContaPoupanca);
        GrupoContas.add(ContaCorrente);

        // Botão "Escolher" estilizado com padding reduzido
        JButton Escolher = new JButton("Escolher");
        Escolher.setFont(new Font("Arial", Font.BOLD, 18));
        Escolher.setBackground(new Color(0, 102, 204));
        Escolher.setForeground(Color.WHITE);
        Escolher.setFocusPainted(false);
        Escolher.setMargin(new Insets(5, 10, 5, 10)); // Padding ajustado

        // Adiciona componentes ao painel
        PainelConta.add(ContaPoupanca);
        PainelConta.add(ContaCorrente);
        PainelConta.add(Escolher);

        Tela.add(PainelConta, BorderLayout.CENTER);

        // Adiciona ActionListener ao botão "Escolher"
        Escolher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ContaCorrente.isSelected()) {
                    CadastroContaCorrente.main(new String[]{}); // Abre a classe CadastroContaCorrente
                    Tela.dispose(); // Fecha a tela atual
                } else if (ContaPoupanca.isSelected()) {
                    CadastroContaPoupancaView.main(new String[]{}); // Abre a classe CadastroContaPoupanca
                    Tela.dispose(); // Fecha a tela atual
                } else {
                    JOptionPane.showMessageDialog(Tela, "Selecione um tipo de conta!", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Centralizar a tela
        Tela.setLocationRelativeTo(null);
        Tela.setVisible(true);
    }
}
