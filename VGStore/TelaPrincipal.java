package Telas;

import Dao.ProdutoDAO;
import Entidades.ItemVenda;
import Entidades.Produto;
import Entidades.Sessao;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public class TelaPrincipal extends JFrame {
    private JTable table;
    private List<Produto> produtos;
    private Sessao sessao = Sessao.getInstancia();
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    public TelaPrincipal() {
        setTitle("Vitrine de Produtos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] columnNames = {"ID", "Imagem", "Nome", "Preço", "Estoque"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 1) {
                    return ImageIcon.class;
                }
                return super.getColumnClass(columnIndex);
            }
        };

        try {
            produtos = produtoDAO.listarTodos();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Produto produto : produtos) {
            ImageIcon imageIcon = null;
            try (InputStream imageStream = getClass().getClassLoader().getResourceAsStream("Resources/" + produto.getImagem())) {
                if (imageStream != null) {
                    Image image = ImageIO.read(imageStream).getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    imageIcon = new ImageIcon(image);
                } else {
                    System.err.println("Imagem não encontrada: " + produto.getImagem());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            Object[] rowData = {
                    produto.getId(),
                    imageIcon,
                    produto.getNome(),
                    produto.getPreco(),
                    produto.getEstoque()
            };
            model.addRow(rowData);
        }
        table = new JTable(model);
        table.setRowHeight(100);
        table.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof ImageIcon) {
                    return new JLabel((ImageIcon) value);
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        });
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton addToCartButton = new JButton("Adicionar ao Carrinho");
        addToCartButton.setMargin(new Insets(10, 20, 10, 20));
        addToCartButton.setFont(new Font("Arial", Font.BOLD, 14));
        addToCartButton.addActionListener(e -> adicionarAoCarrinho());
        panel.add(addToCartButton, BorderLayout.SOUTH);

        setJMenuBar(createMenuBar());
        add(panel);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Opções");
        JMenuItem loginItem = new JMenuItem("Login");
        loginItem.addActionListener(e -> {
            TelaLogin telaLogin = new TelaLogin();
            telaLogin.setVisible(true);
            telaLogin.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    if (sessao.getClienteLogado() != null) {
                        JOptionPane.showMessageDialog(null, "Bem-vindo, " + sessao.getClienteLogado().getNome() + "!");
                    }
                }
            });
        });
        JMenuItem cartItem = new JMenuItem("Carrinho");
        cartItem.addActionListener(e -> new TelaCarrinho().setVisible(true));
        JMenuItem paymentItem = new JMenuItem("Pagamento");
        paymentItem.addActionListener(e -> new TelaPagamento().setVisible(true));

        menu.add(loginItem);
        menu.add(cartItem);
        menu.add(paymentItem);
        menuBar.add(menu);

        return menuBar;
    }

    private void adicionarAoCarrinho() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            Produto produto = produtos.get(selectedRow);
            ItemVenda item = new ItemVenda(0, produto, 1, null);
            sessao.getCarrinho().adicionarItem(item);
            JOptionPane.showMessageDialog(this, "Produto adicionado ao carrinho!");
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto para adicionar ao carrinho.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaLogin().setVisible(true)); // Iniciar com a TelaLogin
    }
}
