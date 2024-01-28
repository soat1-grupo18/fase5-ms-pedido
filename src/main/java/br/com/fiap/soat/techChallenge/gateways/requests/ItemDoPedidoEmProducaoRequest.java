package br.com.fiap.soat.techChallenge.gateways.requests;

import br.com.fiap.soat.techChallenge.entities.ItemDoPedido;

import java.math.BigDecimal;

public class ItemDoPedidoEmProducaoRequest {
    private String nome;
    private String descricao;
    private String categoria;
    private String imagem;
    private int quantidade;
    private BigDecimal precoUnitario;

    public static ItemDoPedidoEmProducaoRequest fromDomain(ItemDoPedido itemDoPedido) {
        var itemDoPedidoEmProducaoRequest = new ItemDoPedidoEmProducaoRequest();
        itemDoPedidoEmProducaoRequest.nome = itemDoPedido.getNome();
        itemDoPedidoEmProducaoRequest.descricao = itemDoPedido.getDescricao();
        itemDoPedidoEmProducaoRequest.categoria = itemDoPedido.getCategoria();
        itemDoPedidoEmProducaoRequest.imagem = itemDoPedido.getImagem();
        itemDoPedidoEmProducaoRequest.quantidade = itemDoPedido.getQuantidade();
        itemDoPedidoEmProducaoRequest.precoUnitario = itemDoPedido.getPrecoUnitario();
        return itemDoPedidoEmProducaoRequest;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getImagem() {
        return imagem;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }
}
