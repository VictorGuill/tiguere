package utilities;

import java.io.IOException;

public class Tools {

    /**
     * Genera un numero aleatorio entre el min y el max.
     *
     * @param min Valor minimo.
     * @param max Valor maximo.
     * @return Devuelte un entero dentro del rango.
     */
    public static int random(int min, int max) {
        double random = Math.random() * (max - min + 1) + min;
        return (int) random;
    }

    /**
     * Imprime un String con colores
     *
     * @author Victor Guillén Alcaraz
     * @param color Indica el color del texto.
     * @param bg Indica el color de fondo.
     * @param text El String a imprimir.
     * @return Devuelve el String formateado.
     */
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

    /**
     * Recibe los caracteres y imprime una barra de carga.
     *
     * @author Victor Guillén Alcaraz
     * @param lenght INT: Largada de la barra
     * @param loaded INT: Parte carga de la barra
     * @param start Char inicio. Ej: '[' '(' '{'
     * @param end Char final. Ej: '[' '(' '{'
     * @param fill Char relleno parte cargada. Ej '#' '█' '▓'
     * @param empty Char espacio sin cargar. Ej '_' '-' '░'
     */
    public static void loadBar(int lenght, int loaded, char start, char end, char fill, char empty) {
        System.out.print(start);
        for (int i = 1; i <= lenght; i++) {
            if (i <= loaded) {
                System.out.print(fill);
            } else {
                System.out.print(empty);
            }
        }
        System.out.print(end);
    }

    /**
     * Limpia la consola dependiendo el entorno donde se ejecuta el programa.
     * Detecta si esta corriendo en un IDE o un CMD real. Si es CMD real,
     * detecta la plataforma y utiliza el metodo correspondiente. Si no hay
     * compatibilidad, utiliza el metodo de imprimir saltos de linea.
     *
     * @author Victor Guillén Alcaraz
     */
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

    /**
     * Imprime el caracter de principio, el medio X veces y el final. Perfecto
     * para imprimir lineas con valores fijos sin petar el codigo.
     *
     * @param start Caracter de prinpio.
     * @param mid Caracter entre medio.
     * @param size Cuantas veces se imprime el entre medio.
     * @param end Caracter final
     */
    public static void printRow(char start, char mid, int size, char end, String color) {
        System.out.print(print(color, "", String.valueOf(start)));
        for (int i = 2; i < size; i++) {
            System.out.print(print(color, "", String.valueOf(mid)));
        }
        System.out.print(print(color, "", String.valueOf(end)));
    }

    /**
     * Universal Character, funcion que imprime el caracter correcto segun la
     * plataforma. Hay que añadir el caso en el switch.
     *
     * @param character Caracter a imprimir.
     * @return Devuelve el caracter.
     */
    public static char UniversalCharacter(char character) {
        if (Tools.isRunningCMD() && Tools.isWindows()) {
            switch (character) {
                case '↑':
                    return 24;
                case '↓':
                    return 25;
                case '→':
                    return 26;
                case '←':
                    return 27;
                case '►':
                    return 16;
                case '◄':
                    return 17;
                case '▲':
                    return 30;
                case '▼':
                    return 31;
                default:
                    return character;
            }
        } else {
            return character;
        }
    }

    /**
     * String convert. Takes String and uses Universal character to convert
     * result.
     *
     * @param input
     * @return
     */
    public static String sc(String input) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            stringBuilder.append(UniversalCharacter(input.charAt(i)));
        }

        String finalString = stringBuilder.toString();
        return finalString;
    }
    //////////////// DETECT PLATFORM /////////////////
    private static String OS = System.getProperty("os.name").toLowerCase();

    public static boolean isRunningCMD() {
        if (System.console() != null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isWindows() {
        return OS.contains("win");
    }

    public static boolean isMac() {
        return OS.contains("mac");
    }

    public static boolean isUnix() {
        return (OS.contains("nix") || OS.contains("nux") || OS.contains("aix"));
    }

    /**
     * Imprime saltos de linea para dar la impresion de que la consola se ha
     * vaciado.
     */
    public static void clearIDE() {
        /*
        int newLines = 20;
        for (int i = 0; i < newLines; i++) {
            System.out.println("");
        }
         */
        //Es mejor hardcoded porque usa menos system.out y va más rapido a la hora de imprimir 
        //(ya que aparte usamos mucho system.out para el juego, cuantos menos mejor)
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    /**
     * Imprime una secuencia de caracteres que BASH reconoce como limpiar la
     * consola.
     */
    public static void clearBash() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Ejecuta un 'cls' (clear screen) en la consola de windows.
     */
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
