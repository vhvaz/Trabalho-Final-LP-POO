package Entidades;

public class FormaPagamento {
    private String tipo;
    private String dadosPagamento;

    public FormaPagamento(String tipo, String dadosPagamento) {
        this.tipo = tipo;
        this.dadosPagamento = dadosPagamento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDadosPagamento() {
        return dadosPagamento;
    }

    public void setDadosPagamento(String dadosPagamento) {
        this.dadosPagamento = dadosPagamento;
    }
}
