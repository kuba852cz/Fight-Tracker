package com.example.fighttracker.Logic;

import com.example.fighttracker.Models.Fighter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class AppData {

    private Map<String, List<Fighter>> allDivisions;

    public void loadFighters() {
        try {
            Gson gson = new Gson();

            java.io.InputStream inputStream = getClass().getResourceAsStream("/com/example/fighttracker/fighters.json");

            if (inputStream == null) {
                System.out.println("CHYBA: Soubor fighters.json nebyl nalezen!");
                return;
            }

            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

            Type type = new TypeToken<Map<String, List<Fighter>>>(){}.getType();
            allDivisions = gson.fromJson(reader, type);

            System.out.println("Databáze bojovníků úspěšně načtena! 🏆");
            reader.close();
        } catch (Exception e) {
            System.out.println("Chyba při načítání JSONu: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Fighter> getDivision(String weightClass) {
        return allDivisions.get(weightClass);
    }


}
