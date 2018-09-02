package com.rohan;

import java.util.ArrayList;
import java.util.Random;

public class GeneratorUtils {

    private ArrayList<String> total_fields;
    private ArrayList<String> answers;
    String[] races = {"Dragonborn","Dwarf","Eladrin","Elf","Gnome","Half-elf","Half-Orc","Halfling","Human","Tiefling"};
    String[] classes = {"Wizard", "Rogue", "Cleric", "Fighter", "Barbarian"};

    private String[] questions = {
            "What is your name?",
            "What is your character's name?",
            "What is your character's class? '$classes' to see a list.",
    };

    ColorUtils c = new ColorUtils();

    public GeneratorUtils() {
        total_fields = new ArrayList<String>()  {{
            add("PlayerName");
            add("CharacterName");
            add("ClassLevel");
            add("Background");
            //add("Race"); // Not Working?
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

        answers = new ArrayList<String>();
    }

    public ArrayList<String> getTotal_fields() {
        return total_fields;
    }

    public String[] getQuestions() {
        return questions;
    }

    public String RGENsection(String[] array) {
        return array[new Random().nextInt(array.length)];
    }

    public void AddAnswer(String stri) {
        answers.add(stri);
        c.linePrint("Answer Saved. (" + stri + ")", c.ANSI_GREEN);
    }
}
