package br.com.fiap.soat.techChallenge.interfaces.gateways;

import br.com.fiap.soat.techChallenge.entities.Pedido;
import br.com.fiap.soat.techChallenge.gateways.responses.ProducaoResponse;
import org.springframework.http.ResponseEntity;

public interface ProducaoGatewayPort {
    ResponseEntity<ProducaoResponse> criarPedidoEmProducao(Pedido pedido);
}
