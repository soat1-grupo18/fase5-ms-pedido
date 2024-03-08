package br.com.fiap.soat.techChallenge.config;

import br.com.fiap.soat.techChallenge.interfaces.gateways.*;
import br.com.fiap.soat.techChallenge.interfaces.usecases.*;
import br.com.fiap.soat.techChallenge.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBeanConfig {

    @Bean
    public CadastrarProdutoUseCase cadastrarProdutoUseCasePort(ProdutoGatewayPort produtoGatewayPort) {
        return new CadastrarProdutoUseCase(produtoGatewayPort);
    }

    @Bean
    public EditarProdutoUseCase editarProdutoUseCasePort(ProdutoGatewayPort produtoGatewayPort) {
        return new EditarProdutoUseCase(produtoGatewayPort);
    }

    @Bean
    public ExcluirProdutoUseCase excluirProdutoUseCasePort(ProdutoGatewayPort produtoGatewayPort) {
        return new ExcluirProdutoUseCase(produtoGatewayPort);
    }

    @Bean
    public ObterProdutosPorCategoriaUseCasePort identificarProdutoUseCasePort(ProdutoGatewayPort produtoGatewayPort) {
        return new ObterProdutosPorCategoriaUseCase(produtoGatewayPort);
    }

    @Bean
    public FazerCheckoutPedidoUseCasePort fazerCheckoutPedidoUseCasePort(PedidoGatewayPort pedidoGatewayPort,
                                                                         ProdutoGatewayPort produtoGatewayPort,
                                                                         PedidoRecebidoQueueOutGatewayPort pedidoRecebidoQueueOutGatewayPort) {
        return new FazerCheckoutPedidoUseCase(pedidoGatewayPort, produtoGatewayPort, pedidoRecebidoQueueOutGatewayPort);
    }
}
