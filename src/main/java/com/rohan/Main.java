package com.rohan;

import org.apache.pdfbox.pdmodel.PDDocument;
import java.util.Scanner;

public class Main {

    static PDDocument pdfDoc;
    private static boolean checkUnfillable;


    public static void main(String[] args) throws java.lang.Exception {

        System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");

        FileUtils obj = new FileUtils("CharacterSheet.pdf");
        GeneratorUtils obj2 = new GeneratorUtils();
        ColorUtils c = new ColorUtils();
        Scanner input = new Scanner(System.in);
        String ph;

        obj.statusCheck();

        c.linePrint("-=-=-=-=-RPG Character Stat Generator-=-=-=-=-=-", c.ANSI_BLUE);
        c.linePrint("\n\t[s] Start Generator\n\t[i] Info\n\t[q] Quit", c.ANSI_WHITE);

        while(true) {
            c.RSlinePrint("\n>> ", c.ANSI_GREEN);
            ph = input.nextLine();
            if (ph.toLowerCase().equals("s")) {
                break;
            } else if (ph.toLowerCase().equals("i")) {
                c.linePrint("Coded by Rohan Bansal\n\nType '$r' to auto-generate\nType '$q' to quit\nMore optional commands appear during " +
                        "generation.\nMay take 5-10 seconds to load the form after finishing.\nDownload the form from the jar directory in file explorer.", c.ANSI_BLUE);
            } else if(ph.toLowerCase().equals("q")) {
                System.exit(0);
            }else {
                c.linePrint("Command not recognized.", c.ANSI_RED);
            }
        }
        start(obj2, obj, c, input);

    }

    static void CanGen(ColorUtils c, GeneratorUtils obj2, boolean works, String... fields) {
        if(works) {
            c.linePrint("Generation Successful.", c.ANSI_GREEN);
            obj2.AddAnswer(fields[0], fields[1]);
        } else {
            c.linePrint("This field cannot be randomly generated.", c.ANSI_RED);
        }
    }

    static void start(GeneratorUtils obj2, FileUtils obj, ColorUtils c, Scanner input) throws  java.io.IOException { //TODO user may edit answers in the end
        String ph;

        for(String keypart : obj2.getFieldList()) {

            for(String check : obj2.unfillables) {

                if(keypart.equals(check)) {
                    checkUnfillable = true;
                    break;
                } else {
                    checkUnfillable = false;
                }
            }

            if(checkUnfillable) {
                switch(keypart) {
                    case "Inspiration": //ProfBonus - Switched?
                        obj2.AddAnswer(keypart, "+2", true);
                        break;
                    case "Speed":
                        obj2.AddAnswer(keypart, obj2.calculateSpeed(), true);
                        break;
                    default:
                        obj2.AddAnswer(keypart, "0", true);
                }
            } else {
                c.linePrint(obj2.getQuestions().get(keypart), c.ANSI_YELLOW);
                while(true) {
                    c.RSlinePrint("\n>> ", c.ANSI_GREEN);
                    ph = input.nextLine();
                    boolean default_ = false;
                    if(ph.charAt(0) == '$') {
                        if(ph.toLowerCase().equals("$classes") && keypart.equals("ClassLevel")) { // Lists Classes
                            for (String item : obj2.classes) {
                                c.linePrint(item, c.ANSI_BLUE);
                            }
                        } else if (ph.toLowerCase().equals("$alignments") && keypart.equals("Alignment")) {
                            for (String element : obj2.alignments) {
                                c.linePrint(element, c.ANSI_BLUE);
                            }
                        } else if (ph.toLowerCase().equals("$races") && keypart.equals("Race")) {
                            for (String element : obj2.races) {
                                c.linePrint(element, c.ANSI_BLUE);
                            }
                        } else if(ph.toLowerCase().equals("$r")) { // Random Generation
                            switch (keypart) {
                                case "ClassLevel": //Class
                                    CanGen(c, obj2, true, keypart, obj2.RGENsection(obj2.classes) + "  lvl.1");
                                    break;
                                case "Alignment": // Alignments
                                    CanGen(c, obj2, true, keypart, obj2.RGENsection(obj2.alignments));
                                    break;
                                case "Background": //Background
                                    CanGen(c, obj2, true, keypart, obj2.RGENsection(obj2.backgrounds));
                                    break;
                                default:
                                    CanGen(c, obj2, false);
                                    default_ = true;
                                    break;
                            }
                            if(!default_) {
                                break;
                            } else {

                            }
                        } else if(ph.toLowerCase().equals("$q")) {
                            System.exit(0);
                        }
                    } else {
                        if(keypart.equals("ClassLevel")) {
                            obj2.AddAnswer(keypart, ph + " lvl.1");
                        } else if(keypart.equals("Race")) {
                            obj2.AddAnswer("ClassLevel", ph + " " + obj2.getTotal_fields().get("ClassLevel"));
                        } else {
                            obj2.AddAnswer(keypart, ph);
                        }
                        break;
                    }
                }
            }
        }
        obj.setAllFields(obj2.getTotal_fields());
    }
}

