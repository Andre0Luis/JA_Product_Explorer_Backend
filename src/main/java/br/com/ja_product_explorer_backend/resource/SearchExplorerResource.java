package br.com.ja_product_explorer_backend.resource;

import br.com.ja_product_explorer_backend.model.Product;
import br.com.ja_product_explorer_backend.service.SearchExplorerService;
import br.com.ja_product_explorer_backend.service.impl.SearchExplorerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("product/")
public class SearchExplorerResource {

    private final SearchExplorerService explorerService;

    @Autowired
    public SearchExplorerResource(SearchExplorerServiceImpl explorerService) {
        this.explorerService = explorerService;
    }

    @GetMapping("{code}")
    public ResponseEntity<Product> findProduct(@PathVariable String code){
   
        return ResponseEntity.ok().body(explorerService.getProductByCodeBar(code));
    }



}
