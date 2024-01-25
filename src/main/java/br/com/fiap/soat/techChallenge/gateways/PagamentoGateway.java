package br.com.fiap.soat.techChallenge.gateways;

import br.com.fiap.soat.techChallenge.entities.Pedido;
import br.com.fiap.soat.techChallenge.interfaces.gateways.PagamentoGatewayPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class PagamentoGateway implements PagamentoGatewayPort {

//    @Autowired
//    private WebClient webClient;

    @Override
    public void iniciarPagamento(Pedido pedido) {
//        webClient.post()
//                .uri("/pagamentos")
//                .bodyValue(pedido)
//                .retrieve()
//                .bodyToMono(Pedido.class)
//                .block();
    }
}
