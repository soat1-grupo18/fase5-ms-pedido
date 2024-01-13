package br.com.fiap.soat.techChallenge.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Pedido {
    private UUID id;
    private UUID clienteId;
    private BigDecimal preco;
    private List<ItemDoPedido> itens;
    private LocalDateTime dataDeCriacao;

    public Pedido(UUID id,
                  UUID clienteId,
                  BigDecimal preco,
                  List<ItemDoPedido> itens,
                  LocalDateTime dataDeCriacao) {
        this.id = id;
        this.clienteId = clienteId;
        this.preco = preco;
        this.itens = itens;
        this.dataDeCriacao = dataDeCriacao;
    }

    public Pedido() {
        this.preco = BigDecimal.ZERO;
        this.itens = new ArrayList<>();
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public UUID getClienteId() {
        return clienteId;
    }

    public void setClienteId(UUID clienteId) {
        this.clienteId = clienteId;
    }

    public void adicionarItem(ItemDoPedido item) {
        this.itens.add(item);
        this.preco = this.preco.add(item.getPrecoUnitario().multiply(new BigDecimal(item.getQuantidade())));
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setItens(List<ItemDoPedido> itens) {
        this.itens = itens;
    }

    public List<ItemDoPedido> getItens() {
        return itens;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(LocalDateTime dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }
}
