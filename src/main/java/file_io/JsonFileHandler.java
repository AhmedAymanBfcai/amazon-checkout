package file_io;

import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonFileHandler {
    private static final Logger LOGGER = Logger.getLogger(JsonFileHandler.class.getName());

    public static Properties readFromPropertiesFile(String domainFilePath) {

        LOGGER.info("Attempting to read properties file from path: " + domainFilePath);
        Properties properties = new Properties();

        if (domainFilePath == null || domainFilePath.isEmpty()) {
            LOGGER.severe("File path is null or empty.");
            return properties;
        }

        Path path = Paths.get(domainFilePath);
        if (!Files.exists(path)) {
            LOGGER.severe("Properties file does not exist: " + domainFilePath);
            return properties;
        }

        try (InputStream input = new BufferedInputStream(Files.newInputStream(path))) {
            properties.load(input);
        } catch (IOException ex) {
            LOGGER.severe("Error reading properties file: " + ex.getMessage());
        }

        return properties;
    }

    public static JSONObject readJsonFile(String jsonFilePath) {
        LOGGER.info("Attempting to read JSON file from path: " + jsonFilePath);

        if (jsonFilePath == null || jsonFilePath.isEmpty()) {
            LOGGER.severe("File path validation failed: null or empty value provided.");
            return null;
        }

        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get(jsonFilePath)), StandardCharsets.UTF_8);
            LOGGER.fine("Successfully read JSON file content. Size: " + jsonContent.length() + " characters");

            JSONObject jsonObject = new JSONObject(jsonContent);
            LOGGER.info("JSON file successfully parsed into JSONObject.");

            return jsonObject;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading the JSON file at path: " + jsonFilePath, e);
        } catch (org.json.JSONException e) {
            LOGGER.log(Level.SEVERE, "Error parsing the JSON content into JSONObject.", e);
        }

        return null;
    }
}