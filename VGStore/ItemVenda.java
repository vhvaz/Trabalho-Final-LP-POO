package Entidades;

public class ItemVenda {
    private int id;
    private Produto produto;
    private int quantidade;
    private Venda venda;

    public ItemVenda(int id, Produto produto, int quantidade, Venda venda) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.venda = venda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
}