package com.skypro.recipesapp.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor

public class FileService {

    private final ObjectMapper objectMapper;

    public <T> void saveDataToFile(Map<Long, T> map, Path path) {
        try {
            createNewDataFile(path);
            String json = objectMapper.writeValueAsString(map);
            Files.writeString(path, json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> Map<Long, T> readDataFromFile(Path path, TypeReference<HashMap<Long, T>> typeReference) {
        try {
            String json = Files.readString(path);
            if (json.isEmpty()) {
                return new HashMap<>();
            }
            return objectMapper.readValue(json, typeReference);
        } catch (NoSuchFileException e) {
            return new HashMap<>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createNewDataFile(Path path) throws IOException {
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
