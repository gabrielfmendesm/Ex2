package br.insper.ecommerce.pagamento;

import java.time.LocalDateTime;

public class MeioPagamento {
    protected Boolean aprovado;
    protected LocalDateTime dataAprovacao;

    public MeioPagamento() {
    }

    public MeioPagamento(boolean aprovado, LocalDateTime dataAprovacao) {
        this.aprovado = aprovado;
        this.dataAprovacao = dataAprovacao;
    }

    public Boolean getAprovado() {
        return aprovado;
    }

    public void setAprovado(Boolean aprovado) {
        this.aprovado = aprovado;
    }

    public LocalDateTime getDataAprovacao() {
        return dataAprovacao;
    }

    public void setDataAprovacao(LocalDateTime dataAprovacao) {
        this.dataAprovacao = dataAprovacao;
    }
}
