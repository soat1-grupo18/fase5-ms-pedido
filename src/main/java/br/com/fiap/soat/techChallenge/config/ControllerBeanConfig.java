package br.com.fiap.soat.techChallenge.config;

import br.com.fiap.soat.techChallenge.controllers.PedidoController;
import br.com.fiap.soat.techChallenge.interfaces.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerBeanConfig {

    @Bean
    public PedidoController pedidoController(FazerCheckoutPedidoUseCasePort fazerCheckoutPedidoUseCase,
                                             CadastrarProdutoUseCasePort cadastrarProdutoUseCase,
                                             EditarProdutoUseCasePort editarProdutoUseCase,
                                             ExcluirProdutoUseCasePort excluirProdutoUseCase,
                                             ObterProdutosPorCategoriaUseCasePort obterProdutosPorCategoriaUseCase) {
        return new PedidoController(fazerCheckoutPedidoUseCase, cadastrarProdutoUseCase, editarProdutoUseCase, excluirProdutoUseCase, obterProdutosPorCategoriaUseCase);
    }
}
