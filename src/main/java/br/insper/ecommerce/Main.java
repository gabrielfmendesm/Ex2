package br.insper.ecommerce;

import br.insper.ecommerce.cliente.Cliente;
import br.insper.ecommerce.cliente.ClienteService;
import br.insper.ecommerce.compra.Compra;
import br.insper.ecommerce.compra.CompraService;
import br.insper.ecommerce.compra.Item;
import br.insper.ecommerce.produto.Produto;
import br.insper.ecommerce.produto.ProdutoService;
import br.insper.ecommerce.pagamento.Boleto;
import br.insper.ecommerce.pagamento.Pix;
import br.insper.ecommerce.pagamento.CartaoCredito;
import br.insper.ecommerce.pagamento.CartaoDebito;

import java.util.Scanner;

import java.time.LocalDateTime;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        String opcao = "0";

        ClienteService clienteService = new ClienteService();
        ProdutoService produtoService = new ProdutoService();
        CompraService compraService = new CompraService();
        Compra compra = new Compra();

        while(!opcao.equalsIgnoreCase("9")) {

            System.out.println("""
                    1 - Cadastrar Cliente
                    2 - Listar Clientes
                    3 - Excluir Cliente
                    4 - Cadastrar Produto
                    5 - Listar Produtos
                    6 - Excluir Produto
                    7 - Cadastrar Compra
                    8 - Listar Compras
                    9 - Sair
                    """);
            opcao = scanner.nextLine();
            if (opcao.equalsIgnoreCase("1")) {
                System.out.println("Digite o nome do cliente:");
                String nome = scanner.nextLine();
                System.out.println("Digite o CPF do cliente;");
                String cpf = scanner.nextLine();
                clienteService.cadastrarCliente(nome, cpf);
            }

            if (opcao.equalsIgnoreCase("2")) {
                clienteService.listarClientes();
            }

            if (opcao.equalsIgnoreCase("3")) {
                System.out.println("Digite o cpf do cliente para deletar:");
                String cpf = scanner.nextLine();
                clienteService.excluirClientes(cpf);
            }

            if (opcao.equalsIgnoreCase("4")) {
                System.out.println("Digite o nome do produto:");
                String nome = scanner.nextLine();
                System.out.println("Digite o preço do produto;");
                Double preco = scanner.nextDouble();
                scanner.nextLine();
                produtoService.cadastrarProduto(nome, preco);
            }

            if (opcao.equalsIgnoreCase("5")) {
                produtoService.listarProdutos();
            }

            if (opcao.equalsIgnoreCase("6")) {
                System.out.println("Digite o nome do produto para deletar:");
                String nome = scanner.nextLine();
                produtoService.excluirProduto(nome);
            }

            if (opcao.equalsIgnoreCase("7")) {
                compra.getItens().clear();
                System.out.println("Digite o cpf do cliente:");
                String cpf = scanner.nextLine();
                Cliente cliente = clienteService.buscarCliente(cpf);
                if (cliente == null) {
                    System.out.println("Cliente não encontrado");
                } else {
                    System.out.println("Olá " + cliente.getNome());
                    while (true) {
                        System.out.println("""
                                1 - Adicionar Produto
                                2 - Listar Produtos
                                3 - Finalizar Compra
                                """);
                        String opcaoCompra = scanner.nextLine();
                        if (opcaoCompra.equalsIgnoreCase("1")) {
                            System.out.println("Digite o nome do produto:");
                            String nomeProduto = scanner.nextLine();
                            Produto produto = produtoService.buscarProduto(nomeProduto);
                            if (produto == null) {
                                System.out.println("Produto não encontrado");
                            } else {
                                System.out.println("Digite a quantidade:");
                                Integer quantidade = scanner.nextInt();
                                scanner.nextLine();
                                boolean achou = false;
                                for (Item item : compra.getItens()) {
                                    if (item.getProduto().getNome().equalsIgnoreCase(produto.getNome())) {
                                        item.setQuantidade(item.getQuantidade() + quantidade);
                                        System.out.println("Produto adicionado com sucesso.");
                                        achou = true;
                                        break;
                                    }
                                }
                                if (!achou) {
                                    Item item = new Item(quantidade, produto);
                                    compra.adicionarItem(item);
                                    System.out.println("Produto adicionado com sucesso.");
                                }
                            }
                        }

                        if (opcaoCompra.equalsIgnoreCase("2")) {
                            for (Item item : compra.getItens()) {
                                System.out.println("Nome: " + item.getProduto().getNome());
                                System.out.println("Preço: " + item.getProduto().getPreco());
                                System.out.println("Quantidade: " + item.getQuantidade());
                            }
                        }

                        if (opcaoCompra.equalsIgnoreCase("3")) {
                            compra.calculaPrecoTotal();
                            System.out.println("Preço total: " + compra.getPrecoTotal());
                            System.out.println("Digite o meio de pagamento:");
                            System.out.println("""
                                    1 - Boleto
                                    2 - Pix
                                    3 - Crédito
                                    4 - Débito
                                    """);
                            String meioPagamento = scanner.nextLine();
                            if (meioPagamento.equalsIgnoreCase("1")) {
                                LocalDateTime dataCompra = LocalDateTime.now();
                                System.out.println("Digite o código de barras:");
                                String codigoBarra = scanner.nextLine();
                                compraService.cadastrarCompra(dataCompra, compra.getPrecoTotal(), cliente, new Boleto(true, dataCompra, codigoBarra));
                                System.out.println("Compra finalizada com sucesso.");
                                break;
                            }

                            if (meioPagamento.equalsIgnoreCase("2")) {
                                LocalDateTime dataCompra = LocalDateTime.now();
                                System.out.println("Digite a chave de origem:");
                                String chaveOrigem = scanner.nextLine();
                                System.out.println("Digite o QR Code:");
                                String qrCode = scanner.nextLine();
                                compraService.cadastrarCompra(dataCompra, compra.getPrecoTotal(), cliente, new Pix(true, dataCompra, chaveOrigem, qrCode));
                                System.out.println("Compra finalizada com sucesso.");
                                break;
                            }

                            if (meioPagamento.equalsIgnoreCase("3")) {
                                LocalDateTime dataCompra = LocalDateTime.now();
                                System.out.println("Digite o número do cartão:");
                                String numeroCartao = scanner.nextLine();
                                System.out.println("Digite a bandeira do cartão:");
                                String bandeira = scanner.nextLine();
                                compraService.cadastrarCompra(dataCompra, compra.getPrecoTotal(), cliente, new CartaoCredito(true, dataCompra, numeroCartao, bandeira));
                                System.out.println("Compra finalizada com sucesso.");
                                break;
                            }

                            if (meioPagamento.equalsIgnoreCase("4")) {
                                LocalDateTime dataCompra = LocalDateTime.now();
                                System.out.println("Digite o número do cartão:");
                                String numeroCartao = scanner.nextLine();
                                System.out.println("Digite a bandeira do cartão:");
                                String bandeira = scanner.nextLine();
                                compraService.cadastrarCompra(dataCompra, compra.getPrecoTotal(), cliente, new CartaoDebito(true, dataCompra, numeroCartao, bandeira));
                                System.out.println("Compra finalizada com sucesso.");
                                break;
                            }
                        }
                    }
                }
            }

            if (opcao.equalsIgnoreCase("8")) {
                compraService.listarCompras();
            }
        }
    }
}