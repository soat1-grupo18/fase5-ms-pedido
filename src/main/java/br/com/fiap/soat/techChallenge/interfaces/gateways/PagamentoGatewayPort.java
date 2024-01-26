package br.com.fiap.soat.techChallenge.interfaces.gateways;

import br.com.fiap.soat.techChallenge.entities.Pedido;
import br.com.fiap.soat.techChallenge.gateways.responses.PagamentoResponse;
import org.springframework.http.ResponseEntity;

public interface PagamentoGatewayPort {
    ResponseEntity<PagamentoResponse> criarPagamento(Pedido pedido);
}
