package br.com.ja_product_explorer_backend.resource;

import br.com.ja_product_explorer_backend.model.Produtos;
import br.com.ja_product_explorer_backend.model.dto.ProductResponseDTO;
import br.com.ja_product_explorer_backend.service.SearchExplorerService;
import br.com.ja_product_explorer_backend.service.impl.SearchExplorerServiceImpl;
import br.com.ja_product_explorer_backend.util.File;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api/products")
@Tag(name = "Search Explorer", description = "Search Explorer API")
public class SearchExplorerResource {

    private final SearchExplorerService explorerService;

    @Autowired
    public SearchExplorerResource(SearchExplorerServiceImpl explorerService) {
        this.explorerService = explorerService;
    }

    @GetMapping("{code}")
    public ResponseEntity<Produtos> findProduct(@PathVariable String code){

        return ResponseEntity.ok().body(explorerService.getProductByCodeBar(code));
    }

    @GetMapping()
    public ResponseEntity<Page<ProductResponseDTO>> findAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok().body(explorerService.getAllProducts(page, size));
    }

}
