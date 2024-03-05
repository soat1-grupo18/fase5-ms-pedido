package br.com.fiap.soat.techChallenge.interfaces.gateways;

import br.com.fiap.soat.techChallenge.entities.Pedido;

public interface PedidoRecebidoQueueGatewayPort {
    void enviar(Pedido pedido);
}
