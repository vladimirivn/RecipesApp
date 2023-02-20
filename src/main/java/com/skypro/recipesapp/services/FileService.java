package com.skypro.recipesapp.services;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public interface FileService {

    <T> void saveToFile(Map<Long, T> map, Path path);

    <T> Map<Long, T> readFromFile(Path path, TypeReference<HashMap<Long, T>> typeReference);

    void createNewFile(Path path) throws IOException;

    void uploadFile(MultipartFile file, Path path) throws IOException;
}
