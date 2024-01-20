package br.com.fiap.soat.techChallenge.usecases;

import br.com.fiap.soat.techChallenge.builders.ProdutoBuilder;
import br.com.fiap.soat.techChallenge.entities.Produto;
import br.com.fiap.soat.techChallenge.entities.TipoDeProduto;
import br.com.fiap.soat.techChallenge.interfaces.gateways.ProdutoGatewayPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CadastrarProdutoUseCaseTest {

    @Mock
    private ProdutoGatewayPort produtoGateway;

    private CadastrarProdutoUseCase cadastrarProdutoUseCase;

    @BeforeEach
    void initUseCase() {
        cadastrarProdutoUseCase = new CadastrarProdutoUseCase(produtoGateway);
    }

    @Test
    void execute() {
        var produto = ProdutoBuilder.build();
        var produtoEsperado = ProdutoBuilder.build();
        when(produtoGateway.cadastrar(any(Produto.class))).thenReturn(produtoEsperado);
        var produtoCadastrado = cadastrarProdutoUseCase.execute(produto);
        verify(produtoGateway).cadastrar(produto);
        assertEquals(produtoEsperado, produtoCadastrado);
    }
}