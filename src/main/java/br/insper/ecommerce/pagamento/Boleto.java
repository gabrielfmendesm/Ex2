package br.insper.ecommerce.pagamento;

import java.time.LocalDateTime;

public class Boleto extends MeioPagamento {
    String codigoBarra;

    public Boleto() {
    }

    public Boleto(boolean aprovado, LocalDateTime dataAprovacao, String codigoBarra) {
        super(aprovado, dataAprovacao);
        this.codigoBarra = codigoBarra;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }
}
