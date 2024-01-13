package br.com.fiap.soat.techChallenge.interfaces.gateways;

import br.com.fiap.soat.techChallenge.entities.Pedido;

public interface PagamentoGatewayPort {
    void iniciarPagamento(Pedido pedido);
}
