package br.com.fiap.soat.techChallenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient pagamentoWebClient() {
        return buildWebClient("http://localhost:8083");
    }

    @Bean
    public WebClient producaoWebClient() {
        return buildWebClient("http://localhost:8084");
    }

    private WebClient buildWebClient(String url) {
        return WebClient.builder()
                .baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}