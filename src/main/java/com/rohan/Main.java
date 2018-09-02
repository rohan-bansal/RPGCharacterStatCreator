package com.rohan;

import org.apache.pdfbox.pdmodel.PDDocument;
import java.util.Scanner;

public class Main {

    static PDDocument pdfDoc;

    public static void main(String[] args) throws java.lang.Exception {

        System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");

        FileUtils obj = new FileUtils("Files/CharacterSheet.pdf");
        GeneratorUtils obj2 = new GeneratorUtils();
        ColorUtils c = new ColorUtils();
        Scanner input = new Scanner(System.in);
        String ph;

        obj.statusCheck();

        c.linePrint("-=-=-=-=-RPG Character Stat Generator-=-=-=-=-=-", c.ANSI_BLUE);
        c.linePrint("\n\t[s] Start Generator\n\t[i] Info\n", c.ANSI_WHITE);

        while(true) {
            c.RSlinePrint("\n>> ", c.ANSI_GREEN);
            ph = input.nextLine();
            if(ph.toLowerCase().equals("s")) {
                break;
            } else if(ph.toLowerCase().equals("i")) {
                c.linePrint("Coded by Rohan Bansal\n\nType '$r' to auto-generate\nType '$b' to move back a question\nMore optional commands appear during generation.\nMay take 5-10 seconds" +
                        " to load the form after finishing.\nDownload the form from the target/classes/Files/ path in file explorer.", c.ANSI_BLUE);
            } else {
                c.linePrint("Command not recognized.", c.ANSI_RED);
            }
        }
        start(obj2, obj, c, input, ph);

    }

    static void cannotBeGen(ColorUtils c) {
        c.linePrint("This field cannot be randomly generated.", c.ANSI_RED);
    }

    static void start(GeneratorUtils obj2, FileUtils obj, ColorUtils c, Scanner input, String ph) {
        int currentIndex = 0;

        while(true) {
            c.linePrint(obj2.getQuestions()[currentIndex], c.ANSI_YELLOW);
            c.RSlinePrint("\n>> ", c.ANSI_GREEN);
            ph = input.nextLine();

            if(ph.charAt(0) == '$') {
                if(ph.toLowerCase().equals("$classes") && currentIndex == 2) { // Lists Classes
                    for (String item : obj2.classes) {
                        c.linePrint(item, c.ANSI_BLUE);
                    }
                } else if(ph.toLowerCase().equals("$r")) { // Random Generation
                    switch (currentIndex) {
                        case 2: //Class
                            c.linePrint("Generation Successful.", c.ANSI_GREEN);
                            obj2.AddAnswer(obj2.RGENsection(obj2.classes));
                            currentIndex++;
                        default:
                            cannotBeGen(c);
                            break;
                    }
                }
            } else {
                obj2.AddAnswer(ph);
                currentIndex++;
            }
        }
    }
}

//TODO integrate PDFToolkit
