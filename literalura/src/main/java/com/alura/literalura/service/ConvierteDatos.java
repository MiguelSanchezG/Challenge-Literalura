package com.alura.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements  IConvierteDatos{
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json,clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T obtenerPrimerResultado(String json, Class<T> clase) {
        try {
            JsonNode root = objectMapper.readTree(json);
            JsonNode first = root.path("results").path(0);
            return objectMapper.treeToValue(first, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
