package br.com.ja_product_explorer_backend.repository;

import br.com.ja_product_explorer_backend.model.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Produtos, Long> {

    Produtos findByIdCodigoDeBarras(String id);
    Produtos findByCodigoDeBarras(String code);
}
