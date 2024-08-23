package br.com.ja_product_explorer_backend.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
public class CsvToSqlConverter {

    public String convertCsvToSql(String csvFilePath, String tableName) {
        StringBuilder sqlBuilder = new StringBuilder();
        try (CSVReader reader = new CSVReader(new FileReader(new ClassPathResource(csvFilePath).getFile()))) {
            List<String[]> records = reader.readAll();
            if (records.isEmpty()) {
                return "";
            }

            String[] headers = records.get(0);
            for (int i = 1; i < records.size(); i++) {
                String[] record = records.get(i);
                sqlBuilder.append("INSERT INTO ").append(tableName).append(" (");
                for (String header : headers) {
                    sqlBuilder.append(header).append(", ");
                }
                sqlBuilder.setLength(sqlBuilder.length() - 2); // Remove last comma and space
                sqlBuilder.append(") VALUES (");
                for (String value : record) {
                    sqlBuilder.append("'").append(value.replace("'", "''")).append("', ");
                }
                sqlBuilder.setLength(sqlBuilder.length() - 2); // Remove last comma and space
                sqlBuilder.append(");\n");
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return sqlBuilder.toString();
    }
}