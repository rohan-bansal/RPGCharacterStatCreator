package com.rohan;

import java.util.HashMap;
import java.util.Random;

public class GeneratorUtils {

    private HashMap<String, String> total_fields;
    private HashMap<String, String> questions;
    private String[] fieldList;
    String[] races = {"Dragonborn","Dwarf","Eladrin","Elf","Gnome","Half-elf","Half-Orc","Halfling","Human","Tiefling"};
    String[] classes = {"Wizard", "Rogue", "Cleric", "Fighter", "Barbarian"};
    String[] alignments = {"Lawful Good", "Neutral Good", "Chaotic Good", "Lawful Neutral", "Neutral"};
    String[] backgrounds = {"Noble", "Cook", "Thief", "Adventurer", "Blacksmith", "Armorsmith", "Acolyte", "Archeologist", "Entertainer", "Soldier", "Outlander"};

    String[] unfillables = {"XP", "ProfBonus", "Inspiration", "Speed"};

    private ColorUtils c = new ColorUtils();

    public GeneratorUtils() {

        fieldList = new String[] {"PlayerName", "CharacterName", "ClassLevel", "Alignment", "Background", "Race", "XP", "ProfBonus", "Inspiration", "Speed"};

         questions = new HashMap<String, String>() {{
                put("PlayerName", "What is your name?");
                put("CharacterName", "What is your character's name?");
                put("ClassLevel", "What is your character's class? '$classes' to see a list.");
                put("Alignment", "What side is your character aligned to? '$alignments' to see a list.");
                put("Background", "What is your character's history (background)? (e.g. noble, adventurer, cook, thief, blacksmith)");
                put("Race", "What is your character's fictional race? '$races' to see a list.");

         }};

        total_fields = new HashMap<>();
        for(String item : fieldList) {
            if(item.equals("Race")) {

            } else {
                total_fields.put(item, "");
            }
        }

//            add("AC"); //Armor Class
//            add("Initiative");
//            add("Speed");
//            add("PersonalityTraits");
//            add("HPMax");
//            add("HPCurrent");
//            add("STR"); // Strength
//            add("STRmod"); //Strength Modification

    }

    public HashMap<String, String> getTotal_fields() {
        return total_fields;
    }

    public HashMap<String, String> getQuestions() {
        return questions;
    }

    public String[] getFieldList() {
        return fieldList;
    }

    public String RGENsection(String[] array) {
        return array[new Random().nextInt(array.length)];
    }

    public void AddAnswer(String field, String stri, boolean... dontPrint) {
        total_fields.put(field, stri);
        if(dontPrint.length > 0) {

        } else {
            c.linePrint("Answer Saved. (" + stri + ")", c.ANSI_GREEN);
        }
    }

    public String calculateSpeed() {
        String currentClass = total_fields.get("ClassLevel").split(" ")[0];
        if(currentClass.equals("Dwarf") || currentClass.equals("Gnome") || currentClass.equals("Halfling") || currentClass.equals("Tiefling")) {
             return "20";
        } else {
            return "30";
        }
    }
}
