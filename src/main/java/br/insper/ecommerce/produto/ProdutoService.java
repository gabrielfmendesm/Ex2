package br.insper.ecommerce.produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoService {

    private List<Produto> produtos = new ArrayList<>();

    public void cadastrarProduto(String nome, Double preco) {

        if (nome.equals("") || preco <= 0) {
            System.out.println("Dados do produto invalido.");
        } else {
            Produto produto = new Produto(nome, preco);
            produtos.add(produto);
            System.out.println("Produto cadastrado com sucesso.");
        }

    }

    public Produto buscarProduto(String nome) {
        for (Produto produto : produtos) {
            if (nome.equalsIgnoreCase(produto.getNome())) {
                return produto;
            }
        }
        return null;
    }

    public void listarProdutos() {
        System.out.println("Lista de produtos:");
        for (Produto produto : produtos) {
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Preço: " + produto.getPreco());
        }
    }

    public void excluirProduto(String nome) {
        Produto produtoRemover = null;
        for (Produto produto : produtos) {
            if (nome.equalsIgnoreCase(produto.getNome())) {
                produtoRemover = produto;
            }
        }
        if (produtoRemover != null) {
            System.out.println("Produto removido com sucesso");
            produtos.remove(produtoRemover);
        } else {
            System.out.println("Produto não encontrado");
        }
    }

}
