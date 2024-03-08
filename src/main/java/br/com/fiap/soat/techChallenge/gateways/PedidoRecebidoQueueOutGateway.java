package br.com.fiap.soat.techChallenge.gateways;

import br.com.fiap.soat.techChallenge.entities.Pedido;
import br.com.fiap.soat.techChallenge.gateways.requests.PedidoRecebidoRequest;
import br.com.fiap.soat.techChallenge.interfaces.gateways.PedidoRecebidoQueueOutGatewayPort;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;


@Component
public class PedidoRecebidoQueueOutGateway implements PedidoRecebidoQueueOutGatewayPort {

    @Value("${queue.name.pedido-recebido}")
    private String queueName;

    @Autowired
    private SqsAsyncClient sqsAsyncClient;

    @Override
    public void enviar(Pedido pedido) {
        SqsTemplate template = SqsTemplate.builder().sqsAsyncClient(this.sqsAsyncClient)
                .configureDefaultConverter(converter -> converter.setPayloadTypeHeaderValueFunction(msg -> null))
                .build();

        var pedidoRecebidoRequest = PedidoRecebidoRequest.fromDomain(pedido);
        template.send(to -> to.queue(queueName).payload(pedidoRecebidoRequest));
    }
}
