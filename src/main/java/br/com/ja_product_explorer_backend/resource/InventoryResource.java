package br.com.ja_product_explorer_backend.resource;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/inventory")
@Tag(name = "Inventory", description = "Inventory API")
public class InventoryResource {


}
