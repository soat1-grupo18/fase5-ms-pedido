package br.com.fiap.soat.techChallenge.controllers;

import br.com.fiap.soat.techChallenge.entities.Pedido;
import br.com.fiap.soat.techChallenge.entities.Produto;
import br.com.fiap.soat.techChallenge.entities.TipoDeProduto;
import br.com.fiap.soat.techChallenge.interfaces.usecases.*;
import br.com.fiap.soat.techChallenge.presenters.PedidoPresenter;
import br.com.fiap.soat.techChallenge.presenters.ProdutoPresenter;
import br.com.fiap.soat.techChallenge.usecases.model.ComandoDeNovoPedido;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PedidoController {

    private final FazerCheckoutPedidoUseCasePort fazerCheckoutPedidoUseCase;
    private final ObterProdutosPorCategoriaUseCasePort obterProdutosPorCategoriaUseCase;
    private final CadastrarProdutoUseCasePort cadastrarProdutoUseCase;
    private final EditarProdutoUseCasePort editarProdutoUseCase;
    private final ExcluirProdutoUseCasePort excluirProdutoUseCase;
    public PedidoController(FazerCheckoutPedidoUseCasePort fazerCheckoutPedidoUseCase,
                            CadastrarProdutoUseCasePort cadastrarProdutoUseCase,
                            EditarProdutoUseCasePort editarProdutoUseCase,
                            ExcluirProdutoUseCasePort excluirProdutoUseCase,
                            ObterProdutosPorCategoriaUseCasePort obterProdutosPorCategoriaUseCase) {
        this.fazerCheckoutPedidoUseCase = fazerCheckoutPedidoUseCase;
        this.cadastrarProdutoUseCase = cadastrarProdutoUseCase;
        this.editarProdutoUseCase = editarProdutoUseCase;
        this.excluirProdutoUseCase = excluirProdutoUseCase;
        this.obterProdutosPorCategoriaUseCase = obterProdutosPorCategoriaUseCase;
    }

    public PedidoPresenter fazerCheckoutPedido(ComandoDeNovoPedido comandoDeNovoPedido) {
        Pedido pedido = fazerCheckoutPedidoUseCase.execute(comandoDeNovoPedido);
        return PedidoPresenter.fromDomain(pedido);
    }

    public List<ProdutoPresenter> obterProdutosPorCategoria(TipoDeProduto categoria) {
        List<Produto> produtos = obterProdutosPorCategoriaUseCase.execute(categoria);
        return produtos.stream().map(ProdutoPresenter::fromDomain).collect(Collectors.toList());
    }

    public ProdutoPresenter cadastrarProduto(Produto produto) {
        return ProdutoPresenter.fromDomain(cadastrarProdutoUseCase.execute(produto));
    }

    public ProdutoPresenter editarProduto(Produto produto) {
        return ProdutoPresenter.fromDomain(editarProdutoUseCase.execute(produto));
    }

    public void excluirProduto(UUID id) {
        excluirProdutoUseCase.execute(id);
    }
}
