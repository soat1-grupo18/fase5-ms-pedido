package br.com.fiap.soat.techChallenge.usecases;

import br.com.fiap.soat.techChallenge.builders.ComandoDeNovoPedidoBuilder;
import br.com.fiap.soat.techChallenge.builders.ProdutoBuilder;
import br.com.fiap.soat.techChallenge.interfaces.gateways.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FazerCheckoutPedidoUseCaseTest {

    @Mock
    private PedidoGatewayPort pedidoGateway;
    @Mock
    private ProdutoGatewayPort produtoGateway;
    @Mock
    private PedidoRecebidoQueueGatewayPort pedidoRecebidoQueueGateway;

    private FazerCheckoutPedidoUseCase fazerCheckoutPedidoUseCase;

    @BeforeEach
    void initUseCase() {
        fazerCheckoutPedidoUseCase = new FazerCheckoutPedidoUseCase(pedidoGateway, produtoGateway, pedidoRecebidoQueueGateway);
    }

    @Test
    void execute() {
        when(produtoGateway.identificarPorId(any())).thenReturn(Optional.of(ProdutoBuilder.build()));
        when(pedidoGateway.inserirPedido(any())).thenAnswer(i -> i.getArgument(0));

        var comando = ComandoDeNovoPedidoBuilder.build();
        var pedido = fazerCheckoutPedidoUseCase.execute(comando);
        assertNotNull(pedido.getDataDeCriacao());
        assertNotNull(pedido.getClienteId());
        assertNotNull(pedido.getPreco());
        assertEquals(3, pedido.getItens().size());
    }
}