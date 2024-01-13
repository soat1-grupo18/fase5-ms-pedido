package br.com.fiap.soat.techChallenge.interfaces.gateways;

import br.com.fiap.soat.techChallenge.entities.Pedido;

import java.util.UUID;

public interface PedidoGatewayPort {
    Pedido inserirPedido(Pedido pedido);
    Pedido obterPedido(UUID pedidoId);
}
