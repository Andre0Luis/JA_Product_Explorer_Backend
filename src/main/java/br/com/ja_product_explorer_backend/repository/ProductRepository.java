package br.com.ja_product_explorer_backend.repository;

import br.com.ja_product_explorer_backend.model.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Produtos, Long> {

    List<Produtos> findByIdCodigoDeBarras(String id);
    List<Produtos> findByCodigoDeBarras(String code);
}
