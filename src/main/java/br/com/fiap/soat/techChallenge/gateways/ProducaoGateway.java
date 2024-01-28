package br.com.fiap.soat.techChallenge.gateways;

import br.com.fiap.soat.techChallenge.entities.Pedido;
import br.com.fiap.soat.techChallenge.gateways.requests.PedidoEmProducaoRequest;
import br.com.fiap.soat.techChallenge.gateways.responses.ProducaoResponse;
import br.com.fiap.soat.techChallenge.interfaces.gateways.ProducaoGatewayPort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Component
public class ProducaoGateway implements ProducaoGatewayPort {

    private final WebClient producaoWebClient;

    public ProducaoGateway(WebClient producaoWebClient) {
        this.producaoWebClient = producaoWebClient;
    }
    @Override
    public ResponseEntity<ProducaoResponse> criarPedidoEmProducao(Pedido pedido) {
        var pedidoEmProducaoRequest = PedidoEmProducaoRequest.fromDomain(pedido);

        return producaoWebClient.post()
                .uri("/pedidos")
                .bodyValue(pedidoEmProducaoRequest)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.error(new WebClientResponseException
                        (response.statusCode().value(), "Bad Request", null, null, null)))
                .onStatus(HttpStatusCode::is5xxServerError, response -> Mono.error(new WebClientResponseException
                        (response.statusCode().value(), "Server Error", null, null, null)))
                .toEntity(ProducaoResponse.class)
                .block();

    }
}
