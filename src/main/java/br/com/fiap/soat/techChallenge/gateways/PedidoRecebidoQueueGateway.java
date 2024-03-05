package br.com.fiap.soat.techChallenge.gateways;

import br.com.fiap.soat.techChallenge.entities.Pedido;
import br.com.fiap.soat.techChallenge.gateways.requests.PedidoRecebidoRequest;
import br.com.fiap.soat.techChallenge.interfaces.gateways.PedidoRecebidoQueueGatewayPort;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class PedidoRecebidoQueueGateway implements PedidoRecebidoQueueGatewayPort {

    @Value("${queue.name.pedido-recebido}")
    private String queueName;

    @Autowired
    private SqsTemplate template;

    @Override
    public void enviar(Pedido pedido) {
        var pedidoRecebidoRequest = PedidoRecebidoRequest.fromDomain(pedido);
        template.send(to -> to.queue(queueName).payload(pedidoRecebidoRequest));
    }
}
