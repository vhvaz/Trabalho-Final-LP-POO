package Entidades;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private List<ItemVenda> itens = new ArrayList<>();

    public void adicionarItem(ItemVenda item) {
        itens.add(item);
    }

    public void removerItem(ItemVenda item) {
        itens.remove(item);
    }

    public double calcularTotal() {
        return itens.stream().mapToDouble(item -> item.getProduto().getPreco() * item.getQuantidade()).sum();
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }
}