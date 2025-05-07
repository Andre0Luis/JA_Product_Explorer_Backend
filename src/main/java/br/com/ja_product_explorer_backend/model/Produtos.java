package br.com.ja_product_explorer_backend.model;

import br.com.ja_product_explorer_backend.model.dto.ProductResponseDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Produtos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String preco;
    private Double estoque = 1.0;
    private String idCodigoDeBarras;
    private String codigoDeBarras;
    private String dataAtualizacao;

    public ProductResponseDTO of(Produtos produtos) {
        return ProductResponseDTO.builder()
                .nome(produtos.getNome())
                .preco(produtos.getPreco())
                .estoque(produtos.getEstoque())
                .codigoDeBarras(produtos.getCodigoDeBarras().isEmpty() ? produtos.getIdCodigoDeBarras() : produtos.getCodigoDeBarras())
                .dataAtualizacao(produtos.getDataAtualizacao())
                .build();
    }

    public List<ProductResponseDTO> of(List<Produtos> produtos) {
        return produtos.stream().map(this::of).collect(Collectors.toList());
    }

    public Page<ProductResponseDTO> of(Page<Produtos> produtos) {
        return produtos.map(this::of);
    }

}
