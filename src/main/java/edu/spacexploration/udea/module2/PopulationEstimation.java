package edu.spacexploration.udea.module2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PopulationEstimation {

    public static void main(String[] args) {
        // Total population of crew members (example)
        int totalPopulation = 1000; // Total crew members
        List<Boolean> crewMembers = new ArrayList<>(totalPopulation);

        // Simulate the survival status of crew members
        Random random = new Random();
        for (int i = 0; i < totalPopulation; i++) {
            // Assuming a 70% survival rate for this example
            crewMembers.add(random.nextDouble() < 0.7);
        }

        // Size of the sample we will take
        int sampleSize = 100; // Size of the sample for estimation

        // Estimate survivors using simple random sampling
        int estimatedSurvivors = estimateSurvivors(crewMembers, sampleSize);
        System.out.println("Estimated number of surviving crew members: " + estimatedSurvivors);
    }

    public static int estimateSurvivors(List<Boolean> crewMembers, int sampleSize) {
        Random random = new Random();
        int survivorsInSample = 0;

        // Simple Random Sampling
        for (int i = 0; i < sampleSize; i++) {
            int index = random.nextInt(crewMembers.size());
            if (crewMembers.get(index)) {
                survivorsInSample++;
            }
        }

        // Estimate total survivors based on sample
        double survivalRate = (double) survivorsInSample / sampleSize;
        int estimatedSurvivors = (int) (survivalRate * crewMembers.size());

        return estimatedSurvivors;
    }
}