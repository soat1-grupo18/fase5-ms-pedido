package br.com.fiap.soat.techChallenge.usecases;

import br.com.fiap.soat.techChallenge.builders.ProdutoBuilder;
import br.com.fiap.soat.techChallenge.entities.TipoDeProduto;
import br.com.fiap.soat.techChallenge.interfaces.gateways.ProdutoGatewayPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ObterProdutosPorCategoriaUseCaseTest {

    @Mock
    private ProdutoGatewayPort produtoGateway;

    private ObterProdutosPorCategoriaUseCase obterProdutosPorCategoriaUseCase;

    @BeforeEach
    void initUseCase() {
        obterProdutosPorCategoriaUseCase = new ObterProdutosPorCategoriaUseCase(produtoGateway);
    }

    @Test
    void execute() {
        var listaProdutosEsperada = ProdutoBuilder.buildList(3);
        when(produtoGateway.obterProdutosPor(any(TipoDeProduto.class))).thenReturn(listaProdutosEsperada);
        var listaProdutos = obterProdutosPorCategoriaUseCase.execute(TipoDeProduto.LANCHE);
        verify(produtoGateway).obterProdutosPor(TipoDeProduto.LANCHE);
        assertEquals(3, listaProdutos.size());
    }
}