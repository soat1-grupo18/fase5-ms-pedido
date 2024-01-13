package br.com.fiap.soat.techChallenge.gateways;

import br.com.fiap.soat.techChallenge.exceptions.PedidoNaoEncontradoException;
import br.com.fiap.soat.techChallenge.jpa.entities.PedidoJpaEntity;
import br.com.fiap.soat.techChallenge.jpa.repositories.PedidoRepository;
import br.com.fiap.soat.techChallenge.entities.Pedido;
import br.com.fiap.soat.techChallenge.interfaces.gateways.PedidoGatewayPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PedidoGateway implements PedidoGatewayPort {
    public PedidoGateway(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    private final PedidoRepository pedidoRepository;

    @Override
    @Transactional
    public Pedido inserirPedido(Pedido pedido) {
        PedidoJpaEntity pedidoJpaEntity = PedidoJpaEntity.fromDomain(pedido);

        pedidoRepository.save(pedidoJpaEntity);

        return pedidoJpaEntity.toDomain();
    }

    @Override
    public Pedido obterPedido(UUID pedidoId) {
        var pedidoO = pedidoRepository.findById(pedidoId);
        if (pedidoO.isEmpty()) {
            throw PedidoNaoEncontradoException.aPartirDoId(pedidoId);
        }
        return pedidoO.get().toDomain();
    }
}
