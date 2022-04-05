package gamejava;

import java.io.IOException;

public class Tools {

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
