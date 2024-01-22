package br.com.fiap.soat.techChallenge.presenters;

import br.com.fiap.soat.techChallenge.entities.ItemDoPedido;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ItemDoPedidoPresenterTest {

    @Test
    void fromDomain() {
        var itemDoPedido = new ItemDoPedido(UUID.randomUUID(), "Cheeseburger", "Burger 150g, queijo, molho da casa", "Lanche", "", 2, BigDecimal.valueOf(15));
        var presenter = ItemDoPedidoPresenter.fromDomain(itemDoPedido);
        assertEquals(presenter.getNome(), itemDoPedido.getNome());
        assertEquals(presenter.getDescricao(), itemDoPedido.getDescricao());
        assertEquals(presenter.getCategoria(), itemDoPedido.getCategoria());
        assertEquals(presenter.getImagem(), itemDoPedido.getImagem());
        assertEquals(presenter.getQuantidade(), itemDoPedido.getQuantidade());
        assertEquals(presenter.getPrecoUnitario(), itemDoPedido.getPrecoUnitario());
    }
}