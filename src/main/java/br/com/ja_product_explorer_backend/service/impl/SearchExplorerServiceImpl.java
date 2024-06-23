package br.com.ja_product_explorer_backend.service.impl;

import br.com.ja_product_explorer_backend.model.Product;
import br.com.ja_product_explorer_backend.repository.ProductRepository;
import br.com.ja_product_explorer_backend.service.SearchExplorerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchExplorerServiceImpl implements SearchExplorerService {

    private final ProductRepository productRepository;

    @Autowired
    public SearchExplorerServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product getProductByCodeBar(String code) {
        return productRepository.findByIdCodigoDeBarras(code);
    }


}
