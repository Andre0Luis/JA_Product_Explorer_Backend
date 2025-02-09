package br.com.ja_product_explorer_backend.resource;

import br.com.ja_product_explorer_backend.model.dto.CosmosResponseDTO;
import br.com.ja_product_explorer_backend.service.CosmosService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;

@Controller
@RequestMapping("/api/cosmos")
@Tag(name = "Cosmos", description = "Cosmos API")
public class CosmosResource {

    private final CosmosService cosmosService;

    @Autowired
    public CosmosResource(CosmosService cosmosService){
        this.cosmosService = cosmosService;
    }

    @GetMapping("/products/{code}")
    public ResponseEntity<Flux<CosmosResponseDTO>> getProductByCosmos(@PathVariable String code){

        return ResponseEntity.ok().body(cosmosService.getProducts(code));
    }
}
