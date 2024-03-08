package br.insper.ecommerce.compra;

import br.insper.ecommerce.cliente.Cliente;
import br.insper.ecommerce.pagamento.MeioPagamento;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CompraService {

    private List<Compra> compras = new ArrayList<>();

    public void cadastrarCompra(LocalDateTime dataCompra, Double precoTotal, Cliente cliente, MeioPagamento meioPagamento) {
        Compra compra = new Compra(dataCompra, precoTotal, cliente, meioPagamento);
        compras.add(compra);
    }

    public void listarCompras() {
        for (Compra compra : compras) {
            System.out.println("Data da compra: " + compra.getDataCompra());
            System.out.println("Preço total: " + compra.getPrecoTotal());
            System.out.println("Cliente: " + compra.getCliente().getNome());
            System.out.println("Meio de pagamento: " + compra.getMeioPagamento().getClass().getSimpleName());
            System.out.println();
        }
    }
}
