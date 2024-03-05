package br.com.fiap.soat.techChallenge.gateways.requests;

import br.com.fiap.soat.techChallenge.entities.ItemDoPedido;

import java.math.BigDecimal;

public class ItemDoPedidoRecebidoRequest {
    private String nome;
    private String descricao;
    private String categoria;
    private String imagem;
    private int quantidade;
    private BigDecimal precoUnitario;

    public static ItemDoPedidoRecebidoRequest fromDomain(ItemDoPedido itemDoPedido) {
        var itemDoPedidoRecebidoRequest = new ItemDoPedidoRecebidoRequest();
        itemDoPedidoRecebidoRequest.nome = itemDoPedido.getNome();
        itemDoPedidoRecebidoRequest.descricao = itemDoPedido.getDescricao();
        itemDoPedidoRecebidoRequest.categoria = itemDoPedido.getCategoria();
        itemDoPedidoRecebidoRequest.imagem = itemDoPedido.getImagem();
        itemDoPedidoRecebidoRequest.quantidade = itemDoPedido.getQuantidade();
        itemDoPedidoRecebidoRequest.precoUnitario = itemDoPedido.getPrecoUnitario();
        return itemDoPedidoRecebidoRequest;
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
