package com.rohan;

import org.apache.pdfbox.pdmodel.PDDocument;

public class Main {

    static PDDocument pdfDoc;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) throws java.lang.Exception {

        System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");

        FileUtils obj = new FileUtils("Files/CharacterSheet.pdf");
        GeneratorUtils obj2 = new GeneratorUtils();
        obj.statusCheck();

        System.out.println(ANSI_WHITE + "-=-=-=-=-RPG Character Stat Generator-=-=-=-=-=-" + ANSI_RESET);

    }


}

//TODO integrate PDFToolkit