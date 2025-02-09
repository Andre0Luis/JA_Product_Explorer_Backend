package br.com.ja_product_explorer_backend.service;

import br.com.ja_product_explorer_backend.model.Produtos;
import br.com.ja_product_explorer_backend.model.dto.ProductResponseDTO;
import org.springframework.data.domain.Page;

public interface SearchExplorerService {

    Produtos getProductByCodeBar(String code);

    Page<ProductResponseDTO> getAllProducts(int page, int size);
}