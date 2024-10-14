package edu.spacexploration.udea.module2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class EventCounter {

    // Método para contar eventos específicos
    public static int countEvents(String jsonFilePath, String eventType) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(new File(jsonFilePath));
            JsonNode eventsArray = rootNode.get("events");

            if (eventsArray == null || eventsArray.size() == 0) {
                return 0;
            }

            int eventCount = 0;

            // Contar eventos del tipo especificado
            for (JsonNode event : eventsArray) {
                String type = event.get("type").asText();
                if (type.equalsIgnoreCase(eventType)) {
                    eventCount++;
                }
            }

            return eventCount;

        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
