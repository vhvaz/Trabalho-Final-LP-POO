package Entidades;

import java.util.HashMap;
import java.util.Map;

public class Estoque {
    private Map<Produto, Integer> produtos = new HashMap<>();

    public void atualizarEstoque(Produto produto, int quantidade) {
        produtos.put(produto, quantidade);
    }

    public int consultarEstoque(Produto produto) {
        return produtos.getOrDefault(produto, 0);
    }

    public Map<Produto, Integer> getProdutos() {
        return produtos;
    }

    public void setProdutos(Map<Produto, Integer> produtos) {
        this.produtos = produtos;
    }
}