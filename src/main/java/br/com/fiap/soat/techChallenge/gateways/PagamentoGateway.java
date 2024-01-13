package br.com.fiap.soat.techChallenge.gateways;

import br.com.fiap.soat.techChallenge.entities.Pedido;
import br.com.fiap.soat.techChallenge.interfaces.gateways.PagamentoGatewayPort;
import org.springframework.stereotype.Component;

@Component
public class PagamentoGateway implements PagamentoGatewayPort {
    @Override
    public void iniciarPagamento(Pedido pedido) {

    }
}
