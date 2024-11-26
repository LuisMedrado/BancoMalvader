package view;

import dao.ConnectionFactory;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;

public class MenuFuncionarioView {
    public static void main(String[] args) {
        JFrame Tela = new JFrame("Banco Malvader - Menu Funcionário");
        Tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Tela.setSize(550, 350);
        Tela.setLayout(new BorderLayout());

        // Título estilizado
        JLabel Titulo = new JLabel("Banco Malvader - Funcionários", SwingConstants.CENTER);
        Titulo.setFont(new Font("Verdana", Font.BOLD, 22));
        Titulo.setForeground(new Color(0, 102, 204));
        Titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        Tela.add(Titulo, BorderLayout.NORTH);

        // Painel central com botões
        JPanel PainelCentral = new JPanel();
        PainelCentral.setLayout(new GridLayout(4, 2, 10, 10));
        PainelCentral.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Botões com funcionalidades existentes
        JButton AberturaConta = criarBotao("Abertura de Conta");
        AberturaConta.addActionListener(e -> {
            Tela.dispose();
            CadastroContaView.main(new String[]{});
        });
        PainelCentral.add(AberturaConta);

        JButton EncerramentoContaBT = criarBotao("Encerramento de Conta");
        EncerramentoContaBT.addActionListener(e -> {
            Tela.dispose();
            EncerramentoConta.main(new String[]{});
        });
        PainelCentral.add(EncerramentoContaBT);

        JButton ConsultaDados = criarBotao("Consulta de Dados");
        ConsultaDados.addActionListener(e -> {
            JFrame frameEntrada = new JFrame("Consultar dados");
            frameEntrada.setSize(300, 150);
            frameEntrada.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frameEntrada.setLayout(null);

            JLabel labelEntrada = new JLabel("Digite o número da conta do cliente:");
            labelEntrada.setBounds(20, 20, 250, 25);
            frameEntrada.add(labelEntrada);

            JTextField textField = new JTextField();
            textField.setBounds(20, 50, 250, 25);
            frameEntrada.add(textField);

            JButton button = criarBotao("Enviar");
            button.setBounds(80, 90, 100, 25);
            frameEntrada.add(button);

            button.addActionListener(ent -> {
                String inputText = textField.getText();
                try {
                    int inputInt = Integer.parseInt(inputText);
                    String sql = "SELECT numero_conta, agencia, saldo, tipo_conta, id_cliente FROM conta WHERE numero_conta = ?;";

                    try (Connection conn = ConnectionFactory.conectar();
                         PreparedStatement stmt = conn.prepareStatement(sql)) {

                        stmt.setInt(1, inputInt);
                        var rs = stmt.executeQuery();

                        if (rs.next()) {
                            int numero_conta = rs.getInt("numero_conta");
                            String agencia_conta = rs.getString("agencia");
                            double saldo_conta = rs.getDouble("saldo");
                            String tipo_conta = rs.getString("tipo_conta");
                            int id_usuario = rs.getInt("id_cliente");

                            JOptionPane.showMessageDialog(frameEntrada,
                                    "Número da conta: " + numero_conta + "\n" +
                                            "Agência: " + agencia_conta + "\n" +
                                            "Saldo: " + saldo_conta + "\n" +
                                            "Tipo: " + tipo_conta + "\n" +
                                            "ID do usuário: " + id_usuario);
                        } else {
                            JOptionPane.showMessageDialog(frameEntrada, "Conta não encontrada.");
                        }
                    } catch (SQLException sqle) {
                        JOptionPane.showMessageDialog(frameEntrada, "Erro no banco de dados.");
                        sqle.printStackTrace();
                    }
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(frameEntrada, "Entrada inválida.");
                }
            });

            frameEntrada.setVisible(true);
        });
        PainelCentral.add(ConsultaDados);

        JButton AlteracaoDados = criarBotao("Alteração de Dados");
        PainelCentral.add(AlteracaoDados);

        JButton GeracaoRelatorio = criarBotao("Geração de Relatório");
        PainelCentral.add(GeracaoRelatorio);

        JButton CadastrarFuncionario = criarBotao("Novo Funcionário");
        CadastrarFuncionario.addActionListener(e -> {
            Tela.dispose();
            CadastroFuncionarioView.main(new String[]{});
        });
        PainelCentral.add(CadastrarFuncionario);

        JButton Sair = criarBotao("Sair");
        Sair.addActionListener(e -> Tela.dispose());
        PainelCentral.add(Sair);

        Tela.add(PainelCentral, BorderLayout.CENTER);

        // Centralizar a tela
        Tela.setLocationRelativeTo(null);
        Tela.setVisible(true);
    }

    // Método para criar botões estilizados
    private static JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setBackground(new Color(0, 102, 204));
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        return botao;
    }
}
