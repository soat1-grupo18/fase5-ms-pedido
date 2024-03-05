package br.com.fiap.soat.techChallenge.gateways;

import br.com.fiap.soat.techChallenge.entities.Pedido;
import br.com.fiap.soat.techChallenge.interfaces.gateways.PedidoRecebidoQueueGatewayPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class PedidoRecebidoQueueGateway implements PedidoRecebidoQueueGatewayPort {

    @Value("${queue.name.pedido}")
    private String queueName;

    @Autowired
    private SqsTemplate template;

    @Override
    public void enviar(Pedido pedido) {
        template.send(to -> to.queue(queueName).payload(pedido));
    }
}
