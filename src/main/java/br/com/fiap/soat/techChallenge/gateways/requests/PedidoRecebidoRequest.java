package br.com.fiap.soat.techChallenge.gateways.requests;

import br.com.fiap.soat.techChallenge.entities.Pedido;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class PedidoRecebidoRequest {
    private UUID pedidoId;
    private UUID clienteId;
    private BigDecimal preco;
    private List<ItemDoPedidoRecebidoRequest> itens;

    public static PedidoRecebidoRequest fromDomain(Pedido pedido) {
        var pedidoRecebidoRequest = new PedidoRecebidoRequest();

        pedidoRecebidoRequest.pedidoId = pedido.getId();
        pedidoRecebidoRequest.clienteId = pedido.getClienteId();
        pedidoRecebidoRequest.preco = pedido.getPreco();
        pedidoRecebidoRequest.itens = pedido.getItens().stream().map(ItemDoPedidoRecebidoRequest::fromDomain).toList();

        return pedidoRecebidoRequest;
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

    public List<ItemDoPedidoRecebidoRequest> getItens() {
        return itens;
    }
}
