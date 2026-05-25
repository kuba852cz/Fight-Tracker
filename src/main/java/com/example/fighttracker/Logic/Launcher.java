package com.example.fighttracker.Logic;

import com.example.fighttracker.Models.Fighter;
import javafx.application.Application;

import java.util.List;

public class Launcher {
    public static void main(String[] args) {
        //Application.launch(FightTrackerApp.class, args);

        AppData appData = new AppData();

        // 2. Načteme kompletní JSON do paměti
        appData.loadFighters();

        // 3. Vytáhneme si testovací divizi (musí přesně sedět s klíčem v JSONu)
        List<Fighter> flyweights = appData.getDivision("Lightweight");

        // 4. Ověříme, že v seznamu něco je, a vypíšeme šampiona
        if (flyweights != null && !flyweights.isEmpty()) {
            Fighter champ = flyweights.get(0);
            System.out.println("👑 Šampion Flyweight: " + champ.getName());
            System.out.println("📊 Skóre: " + champ.getRecord());
            System.out.println("🥋 Styl: " + champ.getFightingStyle());
            System.out.println("Rank: " + champ.getRank());
            System.out.println("Flag: " + champ.getFlag());
        } else {
            System.out.println("⚠️ Divize se nenačetla nebo je prázdná.");
        }
    }
}
