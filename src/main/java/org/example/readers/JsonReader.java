package org.example.readers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.Expense;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JsonReader {

    public static List<Expense> read(String resourcePath) {
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream inputStream = JsonReader.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                System.err.println("❌ Dosya bulunamadı: " + resourcePath);
                return List.of();
            }

            return mapper.readValue(inputStream, new TypeReference<List<Expense>>() {});
        } catch (IOException e) {
            System.err.println("❌ Json okunurken hata oluştu: " + e.getMessage());
            return List.of();
        }
    }
}
