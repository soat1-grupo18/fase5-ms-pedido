package br.com.fiap.soat.techChallenge.usecases;

import br.com.fiap.soat.techChallenge.interfaces.gateways.ProdutoGatewayPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ExcluirProdutoUseCaseTest {

    @Mock
    private ProdutoGatewayPort produtoGateway;

    private ExcluirProdutoUseCase excluirProdutoUseCase;

    @BeforeEach
    void initUseCase() {
        excluirProdutoUseCase = new ExcluirProdutoUseCase(produtoGateway);
    }

    @Test
    void execute() {
        var id = UUID.randomUUID();
        excluirProdutoUseCase.execute(id);
        Mockito.verify(produtoGateway).excluir(id);
    }
}