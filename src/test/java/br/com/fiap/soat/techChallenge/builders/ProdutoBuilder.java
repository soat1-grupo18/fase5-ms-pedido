package br.com.fiap.soat.techChallenge.builders;

import br.com.fiap.soat.techChallenge.entities.Produto;
import br.com.fiap.soat.techChallenge.entities.TipoDeProduto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class ProdutoBuilder {
    public static Produto build() {
        return new Produto(UUID.randomUUID(), "Cheeseburger", TipoDeProduto.LANCHE, getRandomNumber(10.0F, 20.0F), "Burger 150g, queijo, molho da casa", "");
    }

    public static List<Produto> buildList(int count) {
        var list = new ArrayList<Produto>();
        for (int i = 0; i < count; i++) {
            list.add(build());
        }
        return list;
    }

    private static Float getRandomNumber(Float min, Float max) {
        Random random = new Random();
        return random.nextFloat(max - min) + min;
    }
}
