package br.com.fiap.soat.techChallenge.gateways;

import br.com.fiap.soat.techChallenge.builders.PedidoBuilder;
import br.com.fiap.soat.techChallenge.jpa.entities.PedidoJpaEntity;
import br.com.fiap.soat.techChallenge.jpa.repositories.PedidoRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class PedidoGatewayTest {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PedidoGateway pedidoGateway;

    @Test
    void inserirPedido() {
        var pedido = PedidoBuilder.build();
        pedido.setId(null);
        var pedidoInserido = pedidoGateway.inserirPedido(pedido);
        assertNotNull(pedidoInserido.getId());
        assertEquals(pedido.getClienteId(), pedidoInserido.getClienteId());
        assertEquals(pedido.getItens().size(), pedidoInserido.getItens().size());
        assertEquals(pedido.getDataDeCriacao(), pedidoInserido.getDataDeCriacao());
    }

    @Test
    void obterPedido() {
        var pedidoJpa = PedidoJpaEntity.fromDomain(PedidoBuilder.build());
        var pedido = pedidoRepository.save(pedidoJpa);
        var pedidoInserido = pedido.toDomain();
        assertNotNull(pedidoInserido.getId());

        var pedidoObtido = pedidoGateway.obterPedido(pedidoInserido.getId());
        assertEquals(pedidoInserido.getId(), pedidoObtido.getId());
        assertEquals(pedidoInserido.getClienteId(), pedidoObtido.getClienteId());
        assertEquals(pedidoInserido.getItens().size(), pedidoObtido.getItens().size());
        assertEquals(pedidoInserido.getDataDeCriacao(), pedidoObtido.getDataDeCriacao());
    }
}