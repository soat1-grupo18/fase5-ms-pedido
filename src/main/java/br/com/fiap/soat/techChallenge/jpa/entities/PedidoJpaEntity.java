package br.com.fiap.soat.techChallenge.jpa.entities;

import br.com.fiap.soat.techChallenge.entities.Pedido;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "pedidos")
public class PedidoJpaEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;
    private UUID clienteId;
    private BigDecimal preco;
    private LocalDateTime dataDeCriacao;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemDoPedidoJpaEntity> itens;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getClienteId() {
        return clienteId;
    }

    public void setClienteId(UUID clienteId) {
        this.clienteId = clienteId;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public List<ItemDoPedidoJpaEntity> getItens() {
        return itens;
    }

    public void setItens(List<ItemDoPedidoJpaEntity> itens) {
        this.itens = itens;
    }

    public Pedido toDomain() {
        return new Pedido(
            id,
            clienteId,
            preco,
            itens.stream().map(ItemDoPedidoJpaEntity::toDomain).collect(Collectors.toList()),
            dataDeCriacao
        );
    }

    public static PedidoJpaEntity fromDomain(Pedido pedido) {
        PedidoJpaEntity pedidoJpaEntity = new PedidoJpaEntity();

        pedidoJpaEntity.setId(pedido.getId());
        pedidoJpaEntity.setClienteId(pedido.getClienteId());
        pedidoJpaEntity.setPreco(pedido.getPreco());
        pedidoJpaEntity.setItens(pedido.getItens().stream().map(itemDoPedido -> {
            ItemDoPedidoJpaEntity itemDoPedidoJpaEntity = ItemDoPedidoJpaEntity.fromDomain(itemDoPedido);

            itemDoPedidoJpaEntity.setPedido(pedidoJpaEntity);

            return itemDoPedidoJpaEntity;
        }).collect(Collectors.toList()));
        pedidoJpaEntity.setDataDeCriacao(pedido.getDataDeCriacao());

        return pedidoJpaEntity;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(LocalDateTime dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }
}
