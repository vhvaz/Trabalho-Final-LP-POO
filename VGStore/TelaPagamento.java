package Telas;

import Dao.VendaDAO;
import Entidades.Cliente;
import Entidades.Sessao;
import Entidades.Venda;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;

public class TelaPagamento extends JFrame {
    private Sessao sessao = Sessao.getInstancia();
    private VendaDAO vendaDAO = new VendaDAO();

    public TelaPagamento() {
        setTitle("Pagamento");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel paymentLabel = new JLabel("Dados de Pagamento:");
        paymentLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(paymentLabel, gbc);

        gbc.gridwidth = 1;

        JLabel methodLabel = new JLabel("Método de Pagamento:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(methodLabel, gbc);

        JComboBox<String> paymentMethods = new JComboBox<>(new String[]{"Cartão de Crédito", "Pix"});
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(paymentMethods, gbc);

        JLabel cardNumberLabel = new JLabel("Número do Cartão:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(cardNumberLabel, gbc);

        JTextField cardNumberField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(cardNumberField, gbc);

        JLabel cardHolderLabel = new JLabel("Nome do Titular:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(cardHolderLabel, gbc);

        JTextField cardHolderField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(cardHolderField, gbc);

        JLabel expiryDateLabel = new JLabel("Data de Validade:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(expiryDateLabel, gbc);

        JTextField expiryDateField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(expiryDateField, gbc);

        JLabel cvvLabel = new JLabel("CVV:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(cvvLabel, gbc);

        JTextField cvvField = new JTextField(5);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(cvvField, gbc);

        // Esconder campos inicialmente
        cardNumberLabel.setVisible(false);
        cardNumberField.setVisible(false);
        cardHolderLabel.setVisible(false);
        cardHolderField.setVisible(false);
        expiryDateLabel.setVisible(false);
        expiryDateField.setVisible(false);
        cvvLabel.setVisible(false);
        cvvField.setVisible(false);

        paymentMethods.addActionListener(e -> {
            boolean isCardPayment = paymentMethods.getSelectedItem().equals("Cartão de Crédito");
            cardNumberLabel.setVisible(isCardPayment);
            cardNumberField.setVisible(isCardPayment);
            cardHolderLabel.setVisible(isCardPayment);
            cardHolderField.setVisible(isCardPayment);
            expiryDateLabel.setVisible(isCardPayment);
            expiryDateField.setVisible(isCardPayment);
            cvvLabel.setVisible(isCardPayment);
            cvvField.setVisible(isCardPayment);
            revalidate();
            repaint();
        });

        JButton confirmButton = new JButton("Confirmar Pagamento");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmButton.setFont(new Font("Arial", Font.BOLD, 14));
        confirmButton.addActionListener(e -> finalizarCompra(paymentMethods.getSelectedItem().toString()));
        panel.add(confirmButton, gbc);

        add(panel);
    }

    private void finalizarCompra(String metodoPagamento) {
        Cliente clienteLogado = sessao.getClienteLogado();
        if (clienteLogado == null) {
            JOptionPane.showMessageDialog(this, "Você precisa estar logado para finalizar a compra.");
            return;
        }

        Venda venda = new Venda(0, clienteLogado, new Date(System.currentTimeMillis()), sessao.getCarrinho().getItens());
        try {
            vendaDAO.salvar(venda);
            JOptionPane.showMessageDialog(this, "Compra finalizada com sucesso! Método de Pagamento: " + metodoPagamento);
            sessao.getCarrinho().getItens().clear();
            dispose(); // Fecha a tela de pagamento
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao finalizar a compra: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPagamento().setVisible(true));
    }
}
