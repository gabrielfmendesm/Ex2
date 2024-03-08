package br.insper.ecommerce.compra;

import br.insper.ecommerce.produto.Produto;

public class Item {
    private Integer quantidade;
    private Produto produto;

    public Item() {

    }

    public Item(Integer quantidade, Produto produto) {
        this.quantidade = quantidade;
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
