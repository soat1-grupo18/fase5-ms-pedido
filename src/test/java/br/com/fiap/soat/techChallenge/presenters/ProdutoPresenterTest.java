package br.com.fiap.soat.techChallenge.presenters;

import br.com.fiap.soat.techChallenge.builders.ProdutoBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoPresenterTest {

    @Test
    void fromDomain() {
        var produto = ProdutoBuilder.build();
        var presenter = ProdutoPresenter.fromDomain(produto);
        assertEquals(presenter.getId(), produto.getId());
        assertEquals(presenter.getNome(), produto.getNome());
        assertEquals(presenter.getDescricao(), produto.getDescricao());
        assertEquals(presenter.getImagem(), produto.getImagem());
        assertEquals(presenter.getPreco(), produto.getPreco());
        assertEquals(presenter.getCategoria(), produto.getCategoria());
    }
}