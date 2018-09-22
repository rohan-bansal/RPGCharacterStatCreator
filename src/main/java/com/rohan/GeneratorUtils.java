package com.rohan;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GeneratorUtils {

    private HashMap<String, String> total_fields;
    private HashMap<String, String> questions;
    private String[] fieldList;
    String[] unfillables;
    String[] races;
    String[] classes;
    String[] alignments;
    String[] backgrounds;
    String[] profs;
    String[] currentProfs;

    private ColorUtils c = new ColorUtils();

    public GeneratorUtils() {

        races = new String[] {"Dragonborn","Dwarf","Eladrin","Elf","Gnome","Half-elf","Half-Orc","Halfling","Human","Tiefling"};
        classes = new String[] {"Wizard", "Rogue", "Cleric", "Fighter", "Barbarian"};
        alignments = new String[] {"Lawful Good", "Neutral Good", "Chaotic Good", "Lawful Neutral", "Neutral"};
        backgrounds = new String[] {"Noble", "Cook", "Thief", "Adventurer", "Blacksmith", "Armorsmith", "Acolyte", "Archeologist", "Entertainer", "Soldier", "Outlander"};
        profs = new String[] {"STR", "DEX", "CON", "INT", "WIS", "CHA"};
        currentProfs = new String[2];

        unfillables = new String[] {"ProBonus", "Speed", "Passive", "AC", "HD", "Initiative"};
        fieldList = new String[] {"PlayerName", "CharacterName", "ClassLevel", "Alignment", "Background", "Race", "ProBonus", "Initiative", "Speed", "Passive", "AC", "HD"};

         questions = new HashMap<String, String>() {{
                put("PlayerName", "What is your name?");
                put("CharacterName", "What is your character's name?");
                put("ClassLevel", "What is your character's class? '$classes' to see a list.");
                put("Alignment", "What side is your character aligned to? '$alignments' to see a list.");
                put("Background", "What is your character's history (background)? (for example, but not limited to, noble, adventurer, cook, thief, blacksmith)");
                put("Race", "What is your character's fictional race? '$races' to see a list.");

         }};

        total_fields = new HashMap<>();
        for(String item : fieldList) {
            if(item.equals("Race")) {

            } else {
                total_fields.put(item, "");
            }
        }

//            add("PersonalityTraits");
//            add("HPMax");
//            add("HPCurrent");

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

    public Integer RGENAbilityScore() {
        return ThreadLocalRandom.current().nextInt(8, 18 + 1);
    }

    public void AddAnswer(String field, String stri, boolean... dontPrint) {
        total_fields.put(field, stri);
        if(dontPrint.length > 0) {

        } else {
            c.linePrint("Answer Saved. (" + stri + ")", c.ANSI_GREEN);
        }
    }

    public String calculateSpeed() {
        String currentRace = total_fields.get("ClassLevel").split(" ")[0];
        if(currentRace.equals("Dwarf") || currentRace.equals("Gnome") || currentRace.equals("Halfling") || currentRace.equals("Tiefling")) {
             return "20";
        } else {
            return "30";
        }
    }

    public void calculateAbilityScores(FileUtils f) {
        String[] ASfields = {"STR", "DEX", "CON", "INT", "WIS", "CHA"};

        HashMap<String, Integer> scores = new HashMap<>();

        for(String item : ASfields) {
            scores.put(item, RGENAbilityScore());
        }

        for(String element : scores.keySet()) {

            String plusMinus = ((Math.floor( (double) ((scores.get(element) - 10) / 2))) < 0) ? "" : "+";

            AddAnswer(element, Integer.toString(scores.get(element)), false); //score

            AddAnswer(element + "mod", plusMinus + Integer.toString((int) (Math.floor( (double) ((scores.get(element) - 10) / 2)))), false); //modifier

        }
    }

    public void setAllSkills() {

        HashMap<String, String[]> skills = new HashMap<>();
        String[] STR = {"SavingThrows", "Athletics"};
        String[] DEX = {"SavingThrows2", "Acrobatics", "SleightofHand", "Stealth"};
        String[] CON = {"SavingThrows3"};
        String[] INT = {"SavingThrows4", "Arcana", "History", "Investigation", "Nature", "Religion"};
        String[] WIS = {"SavingThrows5", "Animal Handling", "Insight", "Medicine", "Perception", "Survival"};
        String[] CHA = {"SavingThrows6", "Deception", "Intimidation", "Performance", "Persuasion"};

        skills.put("STR", STR);
        skills.put("DEX", DEX);
        skills.put("CON", CON);
        skills.put("INT", INT);
        skills.put("WIS", WIS);
        skills.put("CHA", CHA);

        for(String element : profs) {
            for (String item : skills.get(element)) {
                if(Arrays.asList(currentProfs).contains(element)) {
                    AddAnswer(item, "+" + Integer.toString(Integer.parseInt(total_fields.get(element + "mod")) + Integer.parseInt(total_fields.get("ProBonus"))), true);
                } else {
                    AddAnswer(item, total_fields.get(element + "mod"), true);
                }
            }
        }
    }

}












