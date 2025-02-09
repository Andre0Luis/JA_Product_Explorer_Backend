package br.com.ja_product_explorer_backend.service.impl;

import br.com.ja_product_explorer_backend.model.Produtos;
import br.com.ja_product_explorer_backend.repository.ProductRepository;
import br.com.ja_product_explorer_backend.service.SearchExplorerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SearchExplorerServiceImpl implements SearchExplorerService {

    private final ProductRepository productRepository;

    @Autowired
    public SearchExplorerServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Produtos getProductByCodeBar(String code) {
        try {
            log.info("Searching for product with code: " + code);
            var product = productRepository.findByCodigoDeBarras(code);
            if (product == null) {
                product = productRepository.findByIdCodigoDeBarras(code);
            }
            return product;
             } catch (Exception e) {
                log.error("Error while searching for product with code: " + code, e);
                return new Produtos();
        }
    }

    @Override
    public Iterable<Produtos> getAllProducts() {
        return productRepository.findAll();
    }

}
