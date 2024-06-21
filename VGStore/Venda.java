package Entidades;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Venda {
    private int id;
    private Cliente cliente;
    private Date data;
    private List<ItemVenda> itens;

    public Venda(int id, Cliente cliente, Date data, List<ItemVenda> itens) {
        this.id = id;
        this.cliente = cliente;
        this.data = data;
        this.itens = itens;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }

    public void salvar() throws SQLException {
        Connection connection = null;
        PreparedStatement stmtVenda = null;
        PreparedStatement stmtItem = null;

        try {
            connection = ConexaoBD.conectar();
            connection.setAutoCommit(false);

            String sqlVenda = "INSERT INTO vendas (cliente_id, data) VALUES (?, ?)";
            stmtVenda = connection.prepareStatement(sqlVenda, PreparedStatement.RETURN_GENERATED_KEYS);
            stmtVenda.setInt(1, cliente.getId());
            stmtVenda.setDate(2, data);
            stmtVenda.executeUpdate();

            ResultSet generatedKeys = stmtVenda.getGeneratedKeys();
            if (generatedKeys.next()) {
                this.id = generatedKeys.getInt(1);
            }

            String sqlItem = "INSERT INTO itens_venda (venda_id, produto_id, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";
            stmtItem = connection.prepareStatement(sqlItem);

            for (ItemVenda item : itens) {
                stmtItem.setInt(1, this.id);
                stmtItem.setInt(2, item.getProduto().getId());
                stmtItem.setInt(3, item.getQuantidade());
                stmtItem.setDouble(4, item.getProduto().getPreco());
                stmtItem.addBatch();
            }

            stmtItem.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (stmtItem != null) {
                stmtItem.close();
            }
            if (stmtVenda != null) {
                stmtVenda.close();
            }
            if (connection != null) {
                connection.setAutoCommit(true);
                ConexaoBD.desconectar(connection);
            }
        }
    }
}
