package gamejava;

public class color {

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

}
