package br.com.fiap.soat.techChallenge.gateways;

import br.com.fiap.soat.techChallenge.entities.Pedido;
import br.com.fiap.soat.techChallenge.gateways.requests.PagamentoRequest;
import br.com.fiap.soat.techChallenge.gateways.responses.PagamentoResponse;
import br.com.fiap.soat.techChallenge.interfaces.gateways.PagamentoGatewayPort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Component
public class PagamentoGateway implements PagamentoGatewayPort {

    public PagamentoGateway(WebClient pagamentoWebClient) {
        this.pagamentoWebClient = pagamentoWebClient;
    }

    private final WebClient pagamentoWebClient;

    @Override
    public ResponseEntity<PagamentoResponse> criarPagamento(Pedido pedido) {
        var pagamentoRequest = new PagamentoRequest(pedido.getId(), pedido.getPreco());

        return pagamentoWebClient.post()
                .uri("/pagamentos")
                .bodyValue(pagamentoRequest)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.error(new WebClientResponseException
                        (response.statusCode().value(), "Bad Request", null, null, null)))
                .onStatus(HttpStatusCode::is5xxServerError, response -> Mono.error(new WebClientResponseException
                        (response.statusCode().value(), "Server Error", null, null, null)))
                .toEntity(PagamentoResponse.class)
                .block();
    }
}
