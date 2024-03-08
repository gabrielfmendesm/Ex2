package br.insper.ecommerce.pagamento;

import java.time.LocalDateTime;

public class CartaoDebito extends MeioPagamento {
    private String numeroCartao;
    private String bandeira;

    public CartaoDebito() {
    }

    public CartaoDebito(boolean aprovado, LocalDateTime dataAprovacao, String numeroCartao, String bandeira) {
        super(aprovado, dataAprovacao);
        this.numeroCartao = numeroCartao;
        this.bandeira = bandeira;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }
}
