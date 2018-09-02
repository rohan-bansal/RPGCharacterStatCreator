package com.rohan;

public class ColorUtils {

    public final String ANSI_RESET = "\u001B[0m";
    public final String ANSI_BLACK = "\u001B[30m";
    public final String ANSI_RED = "\u001B[31m";
    public final String ANSI_GREEN = "\u001B[32m";
    public final String ANSI_YELLOW = "\u001B[33m";
    public final String ANSI_BLUE = "\u001B[34m";
    public final String ANSI_PURPLE = "\u001B[35m";
    public final String ANSI_CYAN = "\u001B[36m";
    public final String ANSI_WHITE = "\u001B[37m";

    void linePrint(String string, String color) {
        System.out.println(color + string + ANSI_RESET);
    }

    void RSlinePrint(String string, String color) { //Right Strip
        System.out.print(color + string + ANSI_RESET);
    }

}
