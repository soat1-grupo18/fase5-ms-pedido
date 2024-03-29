package br.com.fiap.soat.techChallenge.entities;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Objects;
import java.util.UUID;

public class Produto {

    private UUID id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private TipoDeProduto categoria;
    private Float preco;
    private String descricao;
    private String imagem;

    public Produto(UUID id, String nome, TipoDeProduto categoria, Float preco, String descricao, String imagem) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.descricao = descricao;
        this.imagem = imagem;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public void setCategoria(TipoDeProduto categoria) { this.categoria = categoria; }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Float getPreco() {
        return preco;
    }

    public TipoDeProduto getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getImagem() {
        return imagem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id) && Objects.equals(nome, produto.nome) && categoria == produto.categoria && Objects.equals(preco, produto.preco) && Objects.equals(descricao, produto.descricao) && Objects.equals(imagem, produto.imagem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, categoria, preco, descricao, imagem);
    }
}