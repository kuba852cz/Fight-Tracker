package com.example.fighttracker.Logic;

import com.example.fighttracker.Models.Fighter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * Manages the application's data layer.
 * Responsible for loading and storing fighter data from a local JSON resource file.
 */

public class AppData {

    private Map<String, List<Fighter>> allDivisions;

    /**
     * Loads fighter data from the embedded "Fighters.json" file.
     * Parses the JSON content into a map categorized by weight divisions.
     */

    public void loadFighters() {
        try {
            Gson gson = new Gson();

            java.io.InputStream inputStream = getClass().getResourceAsStream("/Fighters.json");

            if (inputStream == null) {
                System.out.println("ERROR: File were not found!");
                return;
            }

            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

            Type type = new TypeToken<Map<String, List<Fighter>>>(){}.getType();
            allDivisions = gson.fromJson(reader, type);

            System.out.println("Fighters loaded! 🏆");
            reader.close();
        } catch (Exception e) {
            System.out.println("Error with loading JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a list of fighters for a specific weight division.
     *
     * @param weightClass The name of the weight division (e.g., "Lightweight").
     * @return A list of fighters in the specified division, or null if not found.
     */

    public List<Fighter> getDivision(String weightClass) {
        return allDivisions.get(weightClass);
    }


}
