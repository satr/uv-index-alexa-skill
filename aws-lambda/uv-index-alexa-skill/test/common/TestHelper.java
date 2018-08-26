package common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public final class TestHelper {
    public static JsonNode createJsonFromString(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataMap = null;
        try {
            dataMap = objectMapper.readTree(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataMap;
    }
}
