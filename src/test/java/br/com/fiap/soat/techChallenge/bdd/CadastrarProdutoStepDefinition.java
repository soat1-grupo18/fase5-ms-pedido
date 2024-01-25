package br.com.fiap.soat.techChallenge.bdd;

import br.com.fiap.soat.techChallenge.builders.ProdutoBuilder;
import br.com.fiap.soat.techChallenge.presenters.ProdutoPresenter;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class CadastrarProdutoStepDefinition {

    private Response response;

    private String ENDPOINT_PRODUTOS = "http://localhost:8082/produtos";

    @Quando("eu cadastrar o produto")
    public void euCadastrarOProduto() {
        var produtoRequest = ProdutoBuilder.buildRequest();
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(produtoRequest)
                .when().post(ENDPOINT_PRODUTOS);
        response.then().extract().as(ProdutoPresenter.class);
    }

    @Então("o produto deve ser cadastrado com sucesso")
    public void oProdutoDeveSerCadastradoComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }
}
