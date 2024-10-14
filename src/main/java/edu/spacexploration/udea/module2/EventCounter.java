package edu.spacexploration.udea.module2;

import java.util.HashMap;
import java.util.Map;

public class EventCounter {
    // Data structure to hold the count of events
    private Map<String, Integer> eventCounts;

    // Constructor
    public EventCounter() {
        eventCounts = new HashMap<>();
    }

    // Method to increment the count of a specific event
    public void incrementEvent(String eventName) {
        eventCounts.put(eventName, eventCounts.getOrDefault(eventName, 0) + 1);
    }

    // Method to get the count of a specific event
    public int getEventCount(String eventName) {
        return eventCounts.getOrDefault(eventName, 0);
    }

    // Method to display all events and their counts
    public void displayEventCounts() {
        System.out.println("Event Counts:");
        for (Map.Entry<String, Integer> entry : eventCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        EventCounter counter = new EventCounter();

        // Simulate tracking some events
        counter.incrementEvent("click");
        counter.incrementEvent("view");
        counter.incrementEvent("purchase");
        counter.incrementEvent("click");
        counter.incrementEvent("view");

        // Display the counts of all events
        counter.displayEventCounts();

        // Get the count of a specific event
        int clickCount = counter.getEventCount("click");
        System.out.println("Total clicks: " + clickCount);
    }
}