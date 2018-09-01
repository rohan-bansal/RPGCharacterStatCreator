package com.rohan;

import java.util.ArrayList;
import java.util.Random;

public class GeneratorUtils {

    private ArrayList<String> total_fields;
    private String[] races = {"Dragonborn","Dwarf","Eladrin","Elf","Gnome","Half-elf","Half-Orc","Halfling","Human","Tiefling"};

    public GeneratorUtils() {
        total_fields = new ArrayList<String>()  {{
            add("PlayerName");
            add("ClassLevel");
            add("Background");
            add("CharacterName");
            add("Race"); // Not Working?
            add("Alignment");
            add("XP");
            add("AC"); //Armor Class
            add("ProfBonus"); //Proficiency Bonus
            add("Initiative");
            add("Speed");
            add("PersonalityTraits");
            add("HPMax");
            add("HPCurrent");

            add("STR"); // Strength
            add("STRmod"); //Strength Modification
        }};
    }

    public ArrayList<String> getTotal_fields() {
        return total_fields;
    }

    public String RGENraces() {
        return races[new Random().nextInt(races.length)];
    }
}
