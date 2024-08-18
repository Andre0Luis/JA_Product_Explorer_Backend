package br.com.ja_product_explorer_backend.service.impl;

import br.com.ja_product_explorer_backend.handler.exception.ProductNotFoundException;
import br.com.ja_product_explorer_backend.model.dto.CosmosResponseDTO;
import br.com.ja_product_explorer_backend.service.CosmosService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Service
public class CosmosServiceImpl implements CosmosService {

    @Value("${spring.application.cosmos.url}")
    private static String cosmosUrl;

    @Value("${spring.application.cosmos.key}")
    private static String cosmosKey;

    private static final String COSMOS_AGENT = "Cosmos-API-Request";

    private final WebClient webClient;

    public CosmosServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(cosmosUrl).build();
    }

    public Flux<CosmosResponseDTO> getProducts(String codeBar) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/gtins/")
                        .path(codeBar)
                        .build())
                .header("X-Cosmos-Token", cosmosKey)
                .header("UserAgent", COSMOS_AGENT)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    if (clientResponse.statusCode() == HttpStatus.NOT_FOUND) {
                        log.error("Product with codeBar {} not found", codeBar);
                        return Mono.error(new ProductNotFoundException("Product with codeBar " + codeBar + " not found"));
                    }
                    return clientResponse.createException()
                            .flatMap(Mono::error);
                }).onStatus(HttpStatusCode::is5xxServerError, clientResponse -> {
                    log.error("Error in server connection on getProducts: {}", clientResponse.statusCode());
                    return clientResponse.createException()
                            .flatMap(Mono::error);
                })
                .bodyToFlux(CosmosResponseDTO.class)
                .onErrorResume(e -> {
                    log.error("Error generic on getProducts: {}", e.getMessage());
                    return Flux.empty();
                });
    }

}
