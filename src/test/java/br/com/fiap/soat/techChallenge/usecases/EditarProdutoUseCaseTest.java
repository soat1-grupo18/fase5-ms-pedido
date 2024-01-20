package br.com.fiap.soat.techChallenge.usecases;

import br.com.fiap.soat.techChallenge.builders.ProdutoBuilder;
import br.com.fiap.soat.techChallenge.entities.Produto;
import br.com.fiap.soat.techChallenge.interfaces.gateways.ProdutoGatewayPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EditarProdutoUseCaseTest {

    @Mock
    private ProdutoGatewayPort produtoGateway;

    private EditarProdutoUseCase editarProdutoUseCase;

    @BeforeEach
    void initUseCase() {
        editarProdutoUseCase = new EditarProdutoUseCase(produtoGateway);
    }

    @Test
    void execute() {
        var produto = ProdutoBuilder.build();
        var produtoEsperado = ProdutoBuilder.build();
        when(produtoGateway.editar(any(Produto.class))).thenReturn(produtoEsperado);
        var produtoEditado = editarProdutoUseCase.execute(produto);
        verify(produtoGateway).editar(produto);
        assertEquals(produtoEsperado, produtoEditado);
    }
}