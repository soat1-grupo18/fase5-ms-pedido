package br.com.fiap.soat.techChallenge.gateways.requests;

import br.com.fiap.soat.techChallenge.builders.PedidoBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoEmProducaoRequestTest {

    @Test
    void fromDomain() {
        var pedido = PedidoBuilder.build();
        var request = PedidoEmProducaoRequest.fromDomain(pedido);
        assertEquals(pedido.getId(), request.getPedidoId());
        assertEquals(pedido.getClienteId(), request.getClienteId());
        assertEquals(pedido.getPreco(), request.getPreco());
        assertEquals(pedido.getItens().size(), request.getItens().size());

    }
}