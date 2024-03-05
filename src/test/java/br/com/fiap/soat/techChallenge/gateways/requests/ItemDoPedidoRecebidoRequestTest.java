package br.com.fiap.soat.techChallenge.gateways.requests;

import br.com.fiap.soat.techChallenge.builders.PedidoBuilder;
import br.com.fiap.soat.techChallenge.builders.ProdutoBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemDoPedidoRecebidoRequestTest {

    @Test
    void fromDomain() {
        var itemDoPedido = PedidoBuilder.buildItem(ProdutoBuilder.build());
        var request = ItemDoPedidoRecebidoRequest.fromDomain(itemDoPedido);
        assertEquals(itemDoPedido.getNome(), request.getNome());
        assertEquals(itemDoPedido.getDescricao(), request.getDescricao());
        assertEquals(itemDoPedido.getCategoria(), request.getCategoria());
        assertEquals(itemDoPedido.getImagem(), request.getImagem());
        assertEquals(itemDoPedido.getQuantidade(), request.getQuantidade());
        assertEquals(itemDoPedido.getPrecoUnitario(), request.getPrecoUnitario());

    }
}