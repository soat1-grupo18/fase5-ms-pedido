package br.com.fiap.soat.techChallenge.gateways;

import br.com.fiap.soat.techChallenge.builders.ProdutoBuilder;
import br.com.fiap.soat.techChallenge.entities.TipoDeProduto;
import br.com.fiap.soat.techChallenge.exceptions.ProdutoNaoEncontradoException;
import br.com.fiap.soat.techChallenge.jpa.entities.ProdutoJpaEntity;
import br.com.fiap.soat.techChallenge.jpa.mappers.ProdutoMapper;
import br.com.fiap.soat.techChallenge.jpa.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class ProdutoGatewayTest {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoGateway produtoGateway;

    @Test
    void cadastrar() {
        var produto = ProdutoBuilder.build();
        var produtoInserido = produtoGateway.cadastrar(produto);
        assertNotNull(produtoInserido.getId());
        assertEquals(produto.getNome(), produtoInserido.getNome());
        assertEquals(produto.getCategoria(), produtoInserido.getCategoria());
        assertEquals(produto.getPreco(), produtoInserido.getPreco());
        assertEquals(produto.getDescricao(), produtoInserido.getDescricao());
        assertEquals(produto.getImagem(), produtoInserido.getImagem());
    }

    @Test
    void obterProdutosDoTipoLanche() {
        var listaProdutos = ProdutoBuilder.buildList(3);
        for (var produto : listaProdutos) {
            produtoRepository.save(ProdutoMapper.toEntity(produto));
        }
        assertEquals(3, produtoRepository.count());

        var produtos = produtoGateway.obterProdutosPor(TipoDeProduto.LANCHE);
        assertEquals(3, produtos.size());
    }

    @Test
    void obterListaVaziaDeProdutosDoTipoAcompanhamento() {
        var produtos = produtoGateway.obterProdutosPor(TipoDeProduto.ACOMPANHAMENTO);
        assertEquals(0, produtos.size());
    }

    @Test
    void identificarPorId() {
        var produtoJpa = produtoRepository.save(ProdutoMapper.toEntity(ProdutoBuilder.build()));
        var produto = ProdutoMapper.toDomain(produtoJpa);
        var produtoIdentificado = produtoGateway.identificarPorId(produto.getId()).get();
        assertNotNull(produtoIdentificado);
        assertEquals(produto.getId(), produtoIdentificado.getId());
        assertEquals(produto.getNome(), produtoIdentificado.getNome());
        assertEquals(produto.getCategoria(), produtoIdentificado.getCategoria());
        assertEquals(produto.getPreco(), produtoIdentificado.getPreco());
        assertEquals(produto.getDescricao(), produtoIdentificado.getDescricao());
        assertEquals(produto.getImagem(), produtoIdentificado.getImagem());
    }

    @Test
    void editar() {
        var produtoJpa = produtoRepository.save(ProdutoMapper.toEntity(ProdutoBuilder.build()));
        var nomeAntigo = produtoJpa.getNome();
        var produto = ProdutoMapper.toDomain(produtoJpa);
        produto.setNome("Novo nome");
        var produtoEditado = produtoGateway.editar(produto);
        assertNotEquals(nomeAntigo, produtoEditado.getNome());
        assertEquals(produtoJpa.getId(), produtoEditado.getId());
        assertEquals(produtoJpa.getCategoria(), produtoEditado.getCategoria());
        assertEquals(produtoJpa.getPreco(), produtoEditado.getPreco());
        assertEquals(produtoJpa.getDescricao(), produtoEditado.getDescricao());
        assertEquals(produtoJpa.getImagem(), produtoEditado.getImagem());
    }

    @Test
    void naoPodeEditarProdutoNaoEncontrado() {
        var produto = ProdutoBuilder.build();
        assertThrows(ProdutoNaoEncontradoException.class, () -> produtoGateway.editar(produto));
    }

    @Test
    void excluir() {
        var produtoJpa = produtoRepository.save(ProdutoMapper.toEntity(ProdutoBuilder.build()));
        produtoGateway.excluir(produtoJpa.getId());
        assertEquals(0, produtoRepository.count());
    }
}