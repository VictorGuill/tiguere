package gamejava;

import java.io.IOException;

public class Tools {

    //////////////////////////////////////////////
    /////////////  PRINT CON COLORES  ////////////
    //////////////////////////////////////////////
    public static String print(String color, String bg, String text) {

        String ANSI_RESET = "\u001B[0m";

        String ANSI_BLACK = "\u001B[30m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_PURPLE = "\u001B[35m";
        String ANSI_CYAN = "\u001B[36m";
        String ANSI_WHITE = "\u001B[37m";

        String ANSI_BLACK_BACKGROUND = "\u001B[40m";
        String ANSI_RED_BACKGROUND = "\u001B[41m";
        String ANSI_GREEN_BACKGROUND = "\u001B[42m";
        String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
        String ANSI_BLUE_BACKGROUND = "\u001B[44m";
        String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
        String ANSI_CYAN_BACKGROUND = "\u001B[46m";
        String ANSI_WHITE_BACKGROUND = "\u001B[47m";

        String fgColor = "", bgColor = "";

        switch (color.toLowerCase()) {
            case "black":
                fgColor = ANSI_BLACK;
                break;
            case "red":
                fgColor = ANSI_RED;
                break;
            case "green":
                fgColor = ANSI_GREEN;
                break;
            case "yellow":
                fgColor = ANSI_YELLOW;
                break;
            case "blue":
                fgColor = ANSI_BLUE;
                break;
            case "purple":
                fgColor = ANSI_PURPLE;
                break;
            case "cyan":
                fgColor = ANSI_CYAN;
                break;
            case "white":
                fgColor = ANSI_WHITE;
                break;
        }

        switch (bg.toLowerCase()) {
            case "black":
                bgColor = ANSI_BLACK_BACKGROUND;
                break;
            case "red":
                bgColor = ANSI_RED_BACKGROUND;
                break;
            case "green":
                bgColor = ANSI_GREEN_BACKGROUND;
                break;
            case "yellow":
                bgColor = ANSI_YELLOW_BACKGROUND;
                break;
            case "blue":
                bgColor = ANSI_BLUE_BACKGROUND;
                break;
            case "purple":
                bgColor = ANSI_PURPLE_BACKGROUND;
                break;
            case "cyan":
                bgColor = ANSI_CYAN_BACKGROUND;
                break;
            case "white":
                bgColor = ANSI_WHITE_BACKGROUND;
                break;
        }

        return bgColor + fgColor + text + ANSI_RESET;
    }

    //////////////////////////////////////////////
    ///////////  CLEAR CONSOLE METHOD  ///////////
    //////////////////////////////////////////////
    public static void clearConsole() {
        if (isRunningCMD() == true) {
            if (isWindows()) {
                clearCMD();
            } else if (isMac() || isUnix()) {
                clearBash();
            }
        } else {
            clearIDE();
        }
    }

    //////////////// DETECT IF RUNNING ON IDE OR REAL CONSOLE ////////////////
    public static boolean isRunningCMD() {
        if (System.console() != null) {
            return true;
        } else {
            return false;
        }
    }

    //////////////// DETECT PLATFORM /////////////////
    private static String OS = System.getProperty("os.name").toLowerCase();

    public static boolean isWindows() {
        return OS.contains("win");
    }

    public static boolean isMac() {
        return OS.contains("mac");
    }

    public static boolean isUnix() {
        return (OS.contains("nix") || OS.contains("nux") || OS.contains("aix"));
    }

    ////////// CLEAR CONSOLE FUNCTIONS /////////
    // IDE
    public static void clearIDE() {
        for (int i = 0; i < 30; i++) {
            System.out.println("");
        }
    }

    // BASH
    public static void clearBash() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // WINDOWS
    public static void clearCMD() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException ex) {
        }
    }

}
