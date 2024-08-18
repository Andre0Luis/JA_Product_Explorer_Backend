package br.com.ja_product_explorer_backend.service;

import br.com.ja_product_explorer_backend.model.Product;

public interface SearchExplorerService {

    Product getProductByCodeBar(String code);

    Iterable<Product> getAllProducts();

}
