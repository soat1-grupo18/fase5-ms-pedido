package br.com.fiap.soat.techChallenge.usecases;

import br.com.fiap.soat.techChallenge.exceptions.ProdutoNaoEncontradoException;
import br.com.fiap.soat.techChallenge.interfaces.gateways.*;
import br.com.fiap.soat.techChallenge.usecases.model.ComandoDeNovoPedido;
import br.com.fiap.soat.techChallenge.usecases.model.ItemDoComandoDeNovoPedido;
import br.com.fiap.soat.techChallenge.interfaces.usecases.FazerCheckoutPedidoUseCasePort;
import br.com.fiap.soat.techChallenge.entities.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FazerCheckoutPedidoUseCase implements FazerCheckoutPedidoUseCasePort {
    private final PedidoGatewayPort pedidoGateway;
    private final ProdutoGatewayPort produtoGateway;
    private final PedidoRecebidoQueueGatewayPort pedidoRecebidoQueueGateway;

    public FazerCheckoutPedidoUseCase(
            PedidoGatewayPort pedidoGateway,
            ProdutoGatewayPort produtoGateway,
            PedidoRecebidoQueueGatewayPort pedidoRecebidoQueueGateway) {
        this.pedidoGateway = pedidoGateway;
        this.produtoGateway = produtoGateway;
        this.pedidoRecebidoQueueGateway = pedidoRecebidoQueueGateway;
    }

    @Override
    public Pedido execute(ComandoDeNovoPedido comandoDeNovoPedido) {
        Pedido pedido = new Pedido();

        pedido.setDataDeCriacao(LocalDateTime.now());

        pedido.setClienteId(comandoDeNovoPedido.getClienteId());

        for (ItemDoComandoDeNovoPedido itemSolicitado : comandoDeNovoPedido.getItens()) {
            Produto produto = produtoGateway
                    .identificarPorId(itemSolicitado.getProdutoId())
                    .orElseThrow(() -> ProdutoNaoEncontradoException.aPartirDeProdutoId(itemSolicitado.getProdutoId()));

            ItemDoPedido item = new ItemDoPedido(
                    null,
                    produto.getNome(),
                    produto.getDescricao(),
                    produto.getCategoria().toString(),
                    produto.getImagem(),
                    itemSolicitado.getQuantidade(),
                    BigDecimal.valueOf(produto.getPreco())
            );

            pedido.adicionarItem(item);
        }

        pedido = pedidoGateway.inserirPedido(pedido);

        pedidoRecebidoQueueGateway.enviar(pedido);

        return pedido;
    }
}
