package br.com.ja_product_explorer_backend.util;

import br.com.ja_product_explorer_backend.model.Produtos;
import br.com.ja_product_explorer_backend.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoImportService {

    @Autowired
    private ProductRepository produtoRepository;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

//    @PostConstruct
//    public void callImportCsvDoGoogleDrive() throws IOException {
//        importarCsvDoGoogleDrive();
//    }

    @Scheduled(cron = "0 0 3 * * *") // Executa todo dia às 03:00
    public void importarCsvDoGoogleDrive() throws IOException {
        String url = "https://drive.google.com/uc?export=download&id=14AfPEfB_gPDtzxU01uZXNXiVIME2Qp-K";
        InputStream inputStream = new URL(url).openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        List<Produtos> produtos = new ArrayList<>();

        String line;
        boolean isFirstLine = true;

        while ((line = reader.readLine()) != null) {
            if (isFirstLine) {
                isFirstLine = false;
                continue; // pula o cabeçalho
            }

            String[] parts = line.split(";");
            try {
                Produtos p = new Produtos();
                p.setNome(parts[1]); // Nome do produto
                p.setPreco(parts[5]); // Preço de venda 1
                p.setEstoque(parseDouble(parts[7]));
                p.setCodigoDeBarras(parts[55]);
                p.setIdCodigoDeBarras(parts[56]);
                p.setDataAtualizacao(parts[64]); // Data_Atualização_Preço
                produtos.add(p);
            } catch (Exception e) {
                System.out.println("Erro ao processar linha: " + line + " - " + e.getMessage());
            }
        }

        produtoRepository.saveAll(produtos);
    }

    private Double parseDouble(String value) {
        try {
            return Double.parseDouble(value.replace(",", "."));
        } catch (Exception e) {
            return 0.0;
        }
    }

    private LocalDateTime parseDate(String value) {
        try {
            return LocalDateTime.parse(value, dateFormatter);
        } catch (Exception e) {
            return null;
        }
    }
}
