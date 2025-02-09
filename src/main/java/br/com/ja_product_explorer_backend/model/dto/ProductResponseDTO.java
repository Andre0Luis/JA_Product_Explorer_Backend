package br.com.ja_product_explorer_backend.model.dto;

import br.com.ja_product_explorer_backend.model.Produtos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {

    private String nome;
    private String preco;
    private Double estoque = 1.0;
    private String codigoDeBarras;
    private LocalDateTime dataAtualizacao;

    public static ProductResponseDTO of(Produtos produtos) {
        return ProductResponseDTO.builder()
                .nome(produtos.getNome())
                .preco(produtos.getPreco())
                .estoque(produtos.getEstoque())
                .codigoDeBarras(produtos.getCodigoDeBarras().isEmpty() ? produtos.getIdCodigoDeBarras() : produtos.getCodigoDeBarras())
                .dataAtualizacao(produtos.getDataAtualizacao())
                .build();
    }

    public static List<ProductResponseDTO> of(List<Produtos> produtos) {
        return produtos.stream().map(ProductResponseDTO::of).collect(Collectors.toList());
    }

    public static Page<ProductResponseDTO> of(Page<Produtos> produtos) {
        return produtos.map(ProductResponseDTO::of);
    }
}