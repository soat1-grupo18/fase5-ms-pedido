package br.com.fiap.soat.techChallenge.api;

import br.com.fiap.soat.techChallenge.api.requests.PedidoRequest;
import br.com.fiap.soat.techChallenge.api.requests.ProdutoRequest;
import br.com.fiap.soat.techChallenge.controllers.PedidoController;
import br.com.fiap.soat.techChallenge.entities.TipoDeProduto;
import br.com.fiap.soat.techChallenge.presenters.PedidoPresenter;
import br.com.fiap.soat.techChallenge.presenters.ProdutoPresenter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "API de Pedidos")
public class PedidoApi {

    private final PedidoController pedidoController;

    public PedidoApi(PedidoController pedidoController) {
        this.pedidoController = pedidoController;
    }

    @Operation(summary = "Fazer checkout do pedido", description = "Realiza o checkout após a seleção dos produtos do pedido.")
    @PostMapping("/pedidos")
    public ResponseEntity<PedidoPresenter> fazerCheckoutPedido(@RequestBody PedidoRequest pedidoRequest) {
        return ResponseEntity.ok(pedidoController.fazerCheckoutPedido(pedidoRequest.toDomain()));
    }

    @Operation(summary = "Obter produtos por categoria", description = "Retorna uma lista de produtos filtrada pelo tipo de produto informado.")
    @GetMapping("/produtos/{categoria}")
    public ResponseEntity<List<ProdutoPresenter>> obterProdutosPorCategoria(@PathVariable TipoDeProduto categoria) {
        return ResponseEntity.ok(pedidoController.obterProdutosPorCategoria(categoria));
    }

    @Operation(summary = "Cadastrar produto", description = "Cadastra um novo produto com os dados informados.")
    @PostMapping("/produtos")
    public ResponseEntity<ProdutoPresenter> cadastrarProduto(@Valid @RequestBody ProdutoRequest produtoRequest) {
        return ResponseEntity.ok(pedidoController.cadastrarProduto(produtoRequest.toDomain(null)));
    }

    @Operation(summary = "Editar produto", description = "Atualiza um produto identificado pelo id com os dados informados.")
    @PutMapping("/produtos/{id}")
    public ResponseEntity<ProdutoPresenter> editarProduto(
            @PathVariable(value="id") UUID id,
            @Valid @RequestBody ProdutoRequest produtoRequest
    ) {
        return ResponseEntity.ok(pedidoController.editarProduto(produtoRequest.toDomain(id)));
    }

    @Operation(summary = "Excluir produto", description = "Exclui um produto das opções para pedido.")
    @DeleteMapping("/produtos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirProduto(@PathVariable(value="id") UUID id) {
        pedidoController.excluirProduto(id);
    }
}

