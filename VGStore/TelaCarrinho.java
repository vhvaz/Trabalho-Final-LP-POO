package Telas;

import Dao.VendaDAO;
import Entidades.Cliente;
import Entidades.ItemVenda;
import Entidades.Sessao;
import Entidades.Venda;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class TelaCarrinho extends JFrame {
    private JTable table;
    private Sessao sessao = Sessao.getInstancia();
    private VendaDAO vendaDAO = new VendaDAO();
    private JLabel totalQuantityLabel;
    private JLabel totalPriceLabel;

    public TelaCarrinho() {
        setTitle("Carrinho de Compras");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Carrinho de Compras", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Nome", "Quantidade", "Preço"};
        List<ItemVenda> itens = sessao.getCarrinho().getItens();
        Object[][] data = new Object[itens.size()][4];
        for (int i = 0; i < itens.size(); i++) {
            data[i][0] = itens.get(i).getProduto().getId();
            data[i][1] = itens.get(i).getProduto().getNome();
            data[i][2] = itens.get(i).getQuantidade();
            data[i][3] = itens.get(i).getProduto().getPreco();
        }
        table = new JTable(data, columnNames);
        table.setRowHeight(30);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel summaryPanel = new JPanel(new GridLayout(3, 1));
        totalQuantityLabel = new JLabel("Quantidade Total: " + calcularQuantidadeTotal(), SwingConstants.CENTER);
        totalQuantityLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalPriceLabel = new JLabel("Valor Total: R$ " + calcularValorTotal(), SwingConstants.CENTER);
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        summaryPanel.add(totalQuantityLabel);
        summaryPanel.add(totalPriceLabel);

        JButton finalizeButton = new JButton("Finalizar Compra");
        finalizeButton.setFont(new Font("Arial", Font.BOLD, 14));
        finalizeButton.setMargin(new Insets(10, 20, 10, 20));
        finalizeButton.addActionListener(e -> abrirTelaPagamento());
        summaryPanel.add(finalizeButton);

        panel.add(summaryPanel, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);
    }

    private int calcularQuantidadeTotal() {
        return sessao.getCarrinho().getItens().stream().mapToInt(ItemVenda::getQuantidade).sum();
    }

    private double calcularValorTotal() {
        return sessao.getCarrinho().getItens().stream().mapToDouble(item -> item.getProduto().getPreco() * item.getQuantidade()).sum();
    }

    private void abrirTelaPagamento() {
        // Abre a tela de pagamento
        new TelaPagamento().setVisible(true);
        dispose(); // Fecha a tela do carrinho
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaCarrinho().setVisible(true));
    }
}
