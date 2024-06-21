package Dao;

import Entidades.ConexaoBD;
import Entidades.ItemVenda;
import Entidades.Venda;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VendaDAO {
    public void salvar(Venda venda) throws SQLException {
        Connection connection = null;
        PreparedStatement stmtVenda = null;
        PreparedStatement stmtItem = null;

        try {
            connection = ConexaoBD.conectar();
            connection.setAutoCommit(false);

            String sqlVenda = "INSERT INTO vendas (cliente_id, data) VALUES (?, ?)";
            stmtVenda = connection.prepareStatement(sqlVenda, PreparedStatement.RETURN_GENERATED_KEYS);
            stmtVenda.setInt(1, venda.getCliente().getId());
            stmtVenda.setDate(2, venda.getData());
            stmtVenda.executeUpdate();

            ResultSet generatedKeys = stmtVenda.getGeneratedKeys();
            if (generatedKeys.next()) {
                venda.setId(generatedKeys.getInt(1));
            }

            String sqlItem = "INSERT INTO itens_venda (venda_id, produto_id, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";
            stmtItem = connection.prepareStatement(sqlItem);

            for (ItemVenda item : venda.getItens()) {
                stmtItem.setInt(1, venda.getId());
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
