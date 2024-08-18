package br.com.ja_product_explorer_backend.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CosmosResponseDTO {

    @JsonAlias("description")
    private String name;

    @JsonAlias("barcode_image")
    private String image;

    @JsonAlias("height")
    private String height;

    @JsonAlias("gtin")
    private String barCode;

    @JsonAlias("updated_at")
    private String updatedAt;

}
