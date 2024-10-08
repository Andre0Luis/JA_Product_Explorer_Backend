package br.com.ja_product_explorer_backend.resource;

import br.com.ja_product_explorer_backend.model.Product;
import br.com.ja_product_explorer_backend.service.SearchExplorerService;
import br.com.ja_product_explorer_backend.service.impl.SearchExplorerServiceImpl;
import br.com.ja_product_explorer_backend.util.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/products")
public class SearchExplorerResource {

    private final SearchExplorerService explorerService;
    private final File file;

    @Autowired
    public SearchExplorerResource(SearchExplorerServiceImpl explorerService, File file) {
        this.explorerService = explorerService;
        this.file = file;
    }

    @GetMapping("{code}")
    public ResponseEntity<Product> findProduct(@PathVariable String code){

//        System.out.printf("!!!!!!!!!!!!");
//        file.convertAndSaveSql();
//        System.out.printf("!!!!!!!!!!!!");
        return ResponseEntity.ok().body(explorerService.getProductByCodeBar(code));
    }

    @GetMapping()
    public ResponseEntity<Iterable<Product>> findAllProducts(){
        return ResponseEntity.ok().body(explorerService.getAllProducts());
    }

}
