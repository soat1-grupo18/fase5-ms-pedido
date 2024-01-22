package br.com.fiap.soat.techChallenge.presenters;

import br.com.fiap.soat.techChallenge.builders.PedidoBuilder;
import br.com.fiap.soat.techChallenge.entities.Pedido;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PedidoPresenterTest {

    @Test
    void fromDomain() {
        var pedido = PedidoBuilder.build();
        var presenter = PedidoPresenter.fromDomain(pedido);
        assertEquals(presenter.getId(), pedido.getId());
        assertEquals(presenter.getPreco(), pedido.getPreco());
        assertEquals(presenter.getDataDeCriacao(), pedido.getDataDeCriacao());
        assertEquals(presenter.getItens().size(), pedido.getItens().size());
    }
}