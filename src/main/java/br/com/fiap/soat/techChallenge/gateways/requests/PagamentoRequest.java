package br.com.fiap.soat.techChallenge.gateways.requests;

import java.math.BigDecimal;
import java.util.UUID;

public class PagamentoRequest {
    private UUID pedidoId;
    private BigDecimal total;

    public PagamentoRequest(UUID pedidoId, BigDecimal total) {
        this.pedidoId = pedidoId;
        this.total = total;
    }

    public UUID getPedidoId() {
        return pedidoId;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
