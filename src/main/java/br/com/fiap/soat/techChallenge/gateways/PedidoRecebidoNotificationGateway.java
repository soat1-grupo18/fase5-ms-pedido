package br.com.fiap.soat.techChallenge.gateways;

import br.com.fiap.soat.techChallenge.entities.Pedido;
import br.com.fiap.soat.techChallenge.gateways.requests.PedidoRecebidoRequest;
import br.com.fiap.soat.techChallenge.interfaces.gateways.PedidoRecebidoNotificationGatewayPort;
import io.awspring.cloud.sns.core.SnsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class PedidoRecebidoNotificationGateway implements PedidoRecebidoNotificationGatewayPort {

    @Value("${topic.name}")
    private String topicName;

    @Autowired
    private SnsTemplate snsTemplate;

    @Override
    public void enviar(Pedido pedido) {
        PedidoRecebidoRequest pedidoRecebidoRequest = PedidoRecebidoRequest.fromDomain(pedido);
        snsTemplate.convertAndSend(topicName, pedidoRecebidoRequest);
    }
}
