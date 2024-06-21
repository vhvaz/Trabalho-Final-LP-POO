package Telas;

import Dao.ClienteDAO;
import Entidades.Cliente;
import Entidades.Sessao;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class TelaLogin extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField cpfField;
    private Sessao sessao = Sessao.getInstancia();
    private ClienteDAO clienteDAO = new ClienteDAO();

    public TelaLogin() {
        setTitle("Login");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailField = new JTextField(20);
        emailField.setMaximumSize(new Dimension(Integer.MAX_VALUE, emailField.getPreferredSize().height));
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField = new JPasswordField(20);
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, passwordField.getPreferredSize().height));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cpfField = new JTextField(20);
        cpfField.setMaximumSize(new Dimension(Integer.MAX_VALUE, cpfField.getPreferredSize().height));
        cpfField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setMargin(new Insets(10, 20, 10, 20));
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.addActionListener(e -> fazerLogin());

        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento
        panel.add(cpfLabel);
        panel.add(cpfField);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento
        panel.add(loginButton);

        add(panel);
    }

    private void fazerLogin() {
        String email = emailField.getText().trim();
        String senha = new String(passwordField.getPassword()).trim();
        String cpf = cpfField.getText().trim();

        // Validação simples
        if (email.isEmpty() || senha.isEmpty() || cpf.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.");
            return;
        }

        try {
            Cliente cliente = clienteDAO.buscarPorEmailESenha(email, senha);
            if (cliente == null) {
                // Cliente não encontrado, então cria um novo cliente
                cliente = new Cliente(email, "Usuário Exemplo", senha, cpf);
                clienteDAO.salvar(cliente);
            }

            sessao.setClienteLogado(cliente);
            JOptionPane.showMessageDialog(this, "Login bem-sucedido!");

            // Redirecionar para a vitrine de produtos
            SwingUtilities.invokeLater(() -> {
                new TelaPrincipal().setVisible(true);
                dispose(); // Fechar a tela de login
            });
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao fazer login: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaLogin().setVisible(true));
    }
}
