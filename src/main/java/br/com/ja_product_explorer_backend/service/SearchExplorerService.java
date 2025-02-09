package br.com.ja_product_explorer_backend.service;

import br.com.ja_product_explorer_backend.model.Produtos;

public interface SearchExplorerService {

    Produtos getProductByCodeBar(String code);

    Iterable<Produtos> getAllProducts();

}
