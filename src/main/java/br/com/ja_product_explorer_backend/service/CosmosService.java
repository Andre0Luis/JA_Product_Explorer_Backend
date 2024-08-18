package br.com.ja_product_explorer_backend.service;

import br.com.ja_product_explorer_backend.model.dto.CosmosResponseDTO;
import reactor.core.publisher.Flux;

public interface CosmosService {

    /**
     * Fetches product data from an external API using the provided barcode.
     *
     * @param codeBar The barcode of the product to fetch data for.
     * @return A Flux of CosmosResponseDTO objects containing the product data.
     */
    Flux<CosmosResponseDTO> getProducts(String codeBar);
}
