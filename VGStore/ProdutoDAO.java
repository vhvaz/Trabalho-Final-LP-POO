package Dao;

import Entidades.ConexaoBD;
import Entidades.Produto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    public List<Produto> listarTodos() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        try (Connection connection = ConexaoBD.conectar();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM produtos")) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                double preco = resultSet.getDouble("preco");
                int estoque = resultSet.getInt("estoque");
                String imagem = resultSet.getString("imagem");
                produtos.add(new Produto(id, nome, preco, estoque, imagem));
            }
        }
        return produtos;
    }
}
