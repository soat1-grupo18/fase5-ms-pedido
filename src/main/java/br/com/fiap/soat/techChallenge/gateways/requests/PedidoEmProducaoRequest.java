package br.com.fiap.soat.techChallenge.gateways.requests;

import br.com.fiap.soat.techChallenge.entities.Pedido;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class PedidoEmProducaoRequest {
    private UUID pedidoId;
    private UUID clienteId;
    private BigDecimal preco;
    private List<ItemDoPedidoEmProducaoRequest> itens;

    public static PedidoEmProducaoRequest fromDomain(Pedido pedido) {
        var pedidoEmProducaoRequest = new PedidoEmProducaoRequest();

        pedidoEmProducaoRequest.pedidoId = pedido.getId();
        pedidoEmProducaoRequest.clienteId = pedido.getClienteId();
        pedidoEmProducaoRequest.preco = pedido.getPreco();
        pedidoEmProducaoRequest.itens = pedido.getItens().stream().map(ItemDoPedidoEmProducaoRequest::fromDomain).toList();

        return pedidoEmProducaoRequest;
    }

    public UUID getPedidoId() {
        return pedidoId;
    }

    public UUID getClienteId() {
        return clienteId;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public List<ItemDoPedidoEmProducaoRequest> getItens() {
        return itens;
    }
}
