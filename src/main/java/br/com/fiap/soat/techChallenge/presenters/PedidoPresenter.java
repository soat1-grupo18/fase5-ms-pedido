package br.com.fiap.soat.techChallenge.presenters;

import br.com.fiap.soat.techChallenge.entities.Pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PedidoPresenter {
    private UUID id;
    private BigDecimal preco;
    private List<ItemDoPedidoPresenter> itens;
    private LocalDateTime dataDeCriacao;

    public UUID getId() {
        return id;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public List<ItemDoPedidoPresenter> getItens() {
        return itens;
    }

    public static PedidoPresenter fromDomain(Pedido pedido) {
        PedidoPresenter pedidoPresenter = new PedidoPresenter();

        pedidoPresenter.id = pedido.getId();
        pedidoPresenter.preco = pedido.getPreco();
        pedidoPresenter.itens = pedido.getItens().stream().map(ItemDoPedidoPresenter::fromDomain).collect(Collectors.toList());
        pedidoPresenter.dataDeCriacao = pedido.getDataDeCriacao();

        return pedidoPresenter;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}
