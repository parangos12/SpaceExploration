package edu.spacexploration.udea.module2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class PopulationEstimation {

    // Método para estimar el número de sobrevivientes usando muestreo
    public static int estimateSurvivingCrew(String jsonFilePath, int sampleSize) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(new File(jsonFilePath));
            JsonNode crewArray = rootNode.get("crewMembers");

            if (crewArray == null || crewArray.size() == 0) {
                return 0;
            }

            Random random = new Random();
            int totalSurvivedInSample = 0;
            int totalMembers = crewArray.size();

            // Muestreo aleatorio
            for (int i = 0; i < sampleSize; i++) {
                int randomIndex = random.nextInt(totalMembers);
                JsonNode crewMember = crewArray.get(randomIndex);
                boolean survived = crewMember.get("survived").asBoolean();

                if (survived) {
                    totalSurvivedInSample++;
                }
            }

            // Estimación basada en el muestreo
            double survivalRate = (double) totalSurvivedInSample / sampleSize;
            return (int) (survivalRate * totalMembers);

        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
