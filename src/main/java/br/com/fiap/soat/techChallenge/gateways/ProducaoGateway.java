package br.com.fiap.soat.techChallenge.gateways;

import br.com.fiap.soat.techChallenge.entities.Pedido;
import br.com.fiap.soat.techChallenge.interfaces.gateways.ProducaoGatewayPort;
import org.springframework.stereotype.Component;

@Component
public class ProducaoGateway implements ProducaoGatewayPort {
    @Override
    public void iniciarProducao(Pedido pedido) {

    }
}
