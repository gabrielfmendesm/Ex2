package br.insper.ecommerce.pagamento;

import java.time.LocalDateTime;

public class Pix extends MeioPagamento {
    private String chaveOrigem;
    private String qrCode;

    public Pix() {

    }

    public Pix(boolean aprovado, LocalDateTime dataAprovacao, String chaveOrigem, String qrCode) {
        super(aprovado, dataAprovacao);
        this.chaveOrigem = chaveOrigem;
        this.qrCode = qrCode;

        Boleto boleto = new Boleto();
        boleto.codigoBarra = "";

    }

    public String getChaveOrigem() {
        return chaveOrigem;
    }

    public void setChaveOrigem(String chaveOrigem) {
        this.chaveOrigem = chaveOrigem;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}
