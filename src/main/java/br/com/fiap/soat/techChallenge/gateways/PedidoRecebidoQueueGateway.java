package br.com.fiap.soat.techChallenge.gateways;

import br.com.fiap.soat.techChallenge.entities.Pedido;
import br.com.fiap.soat.techChallenge.interfaces.gateways.PedidoRecebidoQueueGatewayPort;
import org.springframework.stereotype.Component;

@Component
public class PedidoRecebidoQueueGateway implements PedidoRecebidoQueueGatewayPort {

    @Override
    public void enviar(Pedido pedido) {

    }
}
