package Dao;

import Entidades.Cliente;
import Entidades.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {

    public Cliente buscarPorEmailESenha(String email, String senha) throws SQLException {
        Connection conexao = ConexaoBD.conectar();
        String sql = "SELECT * FROM clientes WHERE email = ? AND senha = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, senha);
        ResultSet rs = stmt.executeQuery();

        Cliente cliente = null;
        if (rs.next()) {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            String cpf = rs.getString("cpf");
            cliente = new Cliente(id, nome, email, senha, cpf);
        }

        rs.close();
        stmt.close();
        conexao.close();

        return cliente;
    }

    public void salvar(Cliente cliente) throws SQLException {
        Connection conexao = ConexaoBD.conectar();
        String sql = "INSERT INTO clientes (nome, email, senha, cpf) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getEmail());
        stmt.setString(3, cliente.getSenha());
        stmt.setString(4, cliente.getCpf());
        stmt.executeUpdate();

        // Obter o ID gerado e definir no objeto cliente
        ResultSet generatedKeys = stmt.getGeneratedKeys();
        if (generatedKeys.next()) {
            cliente.setId(generatedKeys.getInt(1));
        }

        stmt.close();
        conexao.close();
    }
}
