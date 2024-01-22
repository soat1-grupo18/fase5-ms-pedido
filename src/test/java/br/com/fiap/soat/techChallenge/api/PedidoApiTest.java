package br.com.fiap.soat.techChallenge.api;

import br.com.fiap.soat.techChallenge.api.requests.ItemDoPedidoRequest;
import br.com.fiap.soat.techChallenge.api.requests.PedidoRequest;
import br.com.fiap.soat.techChallenge.api.requests.ProdutoRequest;
import br.com.fiap.soat.techChallenge.builders.PedidoBuilder;
import br.com.fiap.soat.techChallenge.builders.ProdutoBuilder;
import br.com.fiap.soat.techChallenge.config.CoreExceptionsAdvicer;
import br.com.fiap.soat.techChallenge.controllers.PedidoController;
import br.com.fiap.soat.techChallenge.entities.Produto;
import br.com.fiap.soat.techChallenge.entities.TipoDeProduto;
import br.com.fiap.soat.techChallenge.presenters.PedidoPresenter;
import br.com.fiap.soat.techChallenge.presenters.ProdutoPresenter;
import br.com.fiap.soat.techChallenge.usecases.FazerCheckoutPedidoUseCase;
import br.com.fiap.soat.techChallenge.usecases.model.ComandoDeNovoPedido;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PedidoApiTest {

    private MockMvc mockMvc;

    @Mock
    private PedidoController pedidoController;

    @BeforeEach
    void setUp() {
        PedidoApi pedidoApi = new PedidoApi(pedidoController);
        mockMvc = MockMvcBuilders.standaloneSetup(pedidoApi)
                .setControllerAdvice(new CoreExceptionsAdvicer())
                .build();
    }

    @Test
    void fazerCheckoutPedido() throws Exception {
        var pedidoRequest = PedidoBuilder.buildRequest();

        var pedido = PedidoBuilder.build();

        when(pedidoController.fazerCheckoutPedido(any(ComandoDeNovoPedido.class)))
                .thenAnswer(i -> PedidoPresenter.fromDomain(pedido));

        mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pedidoRequest)))
                .andExpect(status().isOk());

        verify(pedidoController, times(1)).fazerCheckoutPedido(any());
    }

    @Test
    void obterProdutosPorCategoria() throws Exception {
        List<Produto> produtos = ProdutoBuilder.buildList(3);

        when(pedidoController.obterProdutosPorCategoria(any()))
                .thenReturn(produtos.stream().map(ProdutoPresenter::fromDomain).toList());

        mockMvc.perform(get("/produtos/{categoria}", TipoDeProduto.LANCHE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(pedidoController, times(1)).obterProdutosPorCategoria(any());
    }

    @Test
    void cadastrarProduto() throws Exception {
        var produtoRequest = ProdutoBuilder.buildRequest();

        when(pedidoController.cadastrarProduto(any()))
                .thenAnswer(i -> ProdutoPresenter.fromDomain(i.getArgument(0)));

        mockMvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(produtoRequest)))
                .andExpect(status().isOk());

        verify(pedidoController, times(1))
                .cadastrarProduto(any());
    }

    @Test
    void editarProduto() throws Exception {
        var produtoRequest = ProdutoBuilder.buildRequest();

        when(pedidoController.editarProduto(any()))
                .thenAnswer(i -> ProdutoPresenter.fromDomain(i.getArgument(0)));

        mockMvc.perform(put("/produtos/{id}", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(produtoRequest)))
                .andExpect(status().isOk());

        verify(pedidoController, times(1)).editarProduto(any());
    }

    @Test
    void excluirProduto() throws Exception {
        mockMvc.perform(delete("/produtos/{id}", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(pedidoController, times(1)).excluirProduto(any());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}