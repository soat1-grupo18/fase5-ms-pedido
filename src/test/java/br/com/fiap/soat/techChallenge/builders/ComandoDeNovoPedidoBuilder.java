package br.com.fiap.soat.techChallenge.builders;

import br.com.fiap.soat.techChallenge.usecases.model.ComandoDeNovoPedido;
import br.com.fiap.soat.techChallenge.usecases.model.ItemDoComandoDeNovoPedido;

import java.util.ArrayList;
import java.util.UUID;

public class ComandoDeNovoPedidoBuilder {

    public static ComandoDeNovoPedido build() {
        var itens = new ArrayList<ItemDoComandoDeNovoPedido>();
        for (int i = 0; i < 3; i++) {
            itens.add(new ItemDoComandoDeNovoPedido(UUID.randomUUID(), 2));
        }
        return new ComandoDeNovoPedido(UUID.randomUUID(), itens);
    }
}
