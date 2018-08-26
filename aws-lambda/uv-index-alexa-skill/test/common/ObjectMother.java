package common;

import com.amazonaws.util.StringInputStream;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public final class ObjectMother {

    public static String getStringFromJson(String jsonFilePath) {
        ClassLoader classLoader = ObjectMother.class.getClassLoader();
        File jsonFile = new File(classLoader.getResource(jsonFilePath).getFile());
        if (!jsonFile.exists()) {
            System.out.println("File not found: " + jsonFilePath);
            return "";
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return new String(Files.readAllBytes(Paths.get(jsonFile.getCanonicalPath())));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static InputStream getRequestForIntentUvIndexLevelJson(String fileName) {
        try {
            String stringFromJson = getStringFromJson("testdata/"+ fileName);
            return new StringInputStream(stringFromJson);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getRandomString() {
        return UUID.randomUUID().toString();
    }
}
