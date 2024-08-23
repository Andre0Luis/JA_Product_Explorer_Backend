package br.com.ja_product_explorer_backend.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

@Component
public class File {

    @Autowired
    private CsvToSqlConverter csvToSqlConverter;

    public void convertAndSaveSql() {
        System.out.printf("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        String sql = csvToSqlConverter.convertCsvToSql("data.csv", "product");
        System.out.printf("SQL: %s", sql);
        try (FileWriter fileWriter = new FileWriter("C:\\data.sql")) {
            fileWriter.write(sql);
            System.out.printf("SQL file created successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}