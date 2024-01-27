package br.com.fiap.soat.techChallenge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${ms.pagamento.url}")
    private String msPagamentoUrl;

    @Value("${ms.producao.url}")
    private String msProducaoUrl;

    @Bean
    public WebClient pagamentoWebClient() {
        return buildWebClient(msPagamentoUrl);
    }

    @Bean
    public WebClient producaoWebClient() {
        return buildWebClient(msProducaoUrl);
    }

    private WebClient buildWebClient(String url) {
        return WebClient.builder()
                .baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}