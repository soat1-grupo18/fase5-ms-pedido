package br.com.fiap.soat.techChallenge.usecases;

import br.com.fiap.soat.techChallenge.exceptions.ProdutoNaoEncontradoException;
import br.com.fiap.soat.techChallenge.interfaces.gateways.*;
import br.com.fiap.soat.techChallenge.usecases.model.ComandoDeNovoPedido;
import br.com.fiap.soat.techChallenge.usecases.model.ItemDoComandoDeNovoPedido;
import br.com.fiap.soat.techChallenge.interfaces.usecases.FazerCheckoutPedidoUseCasePort;
import br.com.fiap.soat.techChallenge.entities.*;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FazerCheckoutPedidoUseCase implements FazerCheckoutPedidoUseCasePort {
    private final PedidoGatewayPort pedidoGateway;
    private final ProdutoGatewayPort produtoGateway;
    private final PedidoRecebidoQueueOutGatewayPort pedidoRecebidoQueueOutGateway;

    public FazerCheckoutPedidoUseCase(
            PedidoGatewayPort pedidoGateway,
            ProdutoGatewayPort produtoGateway,
            PedidoRecebidoQueueOutGatewayPort pedidoRecebidoQueueOutGateway) {
        this.pedidoGateway = pedidoGateway;
        this.produtoGateway = produtoGateway;
        this.pedidoRecebidoQueueOutGateway = pedidoRecebidoQueueOutGateway;
    }

    @Override
    @Transactional
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

        pedidoRecebidoQueueOutGateway.enviar(pedido);

        return pedido;
    }
}
