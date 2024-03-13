package br.com.fiap.soat.techChallenge.interfaces.gateways;

import br.com.fiap.soat.techChallenge.entities.Pedido;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface PedidoRecebidoNotificationGatewayPort {
    void enviar(Pedido pedido);
}
