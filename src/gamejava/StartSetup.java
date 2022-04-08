package gamejava;

import java.util.concurrent.TimeUnit;

public class StartSetup {

    public static String colorUI = "cyan",
            colorText = "yellow",
            colorHighlight = "red";

    public static int w = 45,
            h = 11;

    ////////// PANTALLA ESPERA INICIO //////////
    public static void waitScreen() {
        Tools.clearConsole();

        for (int i = 0; i < h; i++) {
            if (i == 8) {
                System.out.print(Tools.print(colorUI, "", "▒▒▒▒▒▒▒▒▒"));
                System.out.print(Tools.print(colorText, "", " - Hit any key to begin - "));
                System.out.print(Tools.print(colorUI, "", "▒▒▒▒▒▒▒▒▒▒"));
            } else {
                for (int j = 0; j < w; j++) {
                    System.out.print(Tools.print(colorUI, "", "▒"));
                }
            }
            System.out.println("");
        }
    }

    public static void startMenu(int menuOption) {
        Tools.clearConsole();

        for (int i = 0; i < h; i++) {
            if (i == 0) {
                for (int j = 0; j < w; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(colorUI, "", "╔"));
                    } else if (j == w - 1) {
                        System.out.print(Tools.print(colorUI, "", "╗"));
                    } else {
                        System.out.print(Tools.print(colorUI, "", "═"));
                    }
                }
            } else if (i == h - 1) {
                for (int j = 0; j < w; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(colorUI, "", "╚"));
                    } else if (j == w - 1) {
                        System.out.print(Tools.print(colorUI, "", "╝"));
                    } else {
                        System.out.print(Tools.print(colorUI, "", "═"));
                    }
                }
            } else if (i == 2) {
                System.out.print(Tools.print(colorUI, "", "║                  "));
                System.out.print(Tools.print("blue", colorText, " MENU "));
                System.out.print(Tools.print(colorUI, "", "                   ║"));
            } else if (i == 5) {
                System.out.print(Tools.print(colorUI, "", "║"));
                if (menuOption == 1) {
                    System.out.print("              > ");
                    System.out.print(Tools.print("white", colorHighlight, "PLAY"));
                    System.out.print("                       ");
                } else {
                    System.out.print(Tools.print(colorText, "", "               PLAY                        "));
                }
                System.out.print(Tools.print(colorUI, "", "║"));
            } else if (i == 6) {
                System.out.print(Tools.print(colorUI, "", "║"));
                if (menuOption == 2) {
                    System.out.print("              > ");
                    System.out.print(Tools.print("white", colorHighlight, "TUTORIAL"));
                    System.out.print("                   ");
                } else {
                    System.out.print(Tools.print(colorText, "", "               TUTORIAL                    "));
                }
                System.out.print(Tools.print(colorUI, "", "║"));
            } else if (i == 7) {
                System.out.print(Tools.print(colorUI, "", "║"));
                if (menuOption == 3) {
                    System.out.print("              > ");
                    System.out.print(Tools.print("white", colorHighlight, "CONFIG"));
                    System.out.print("                     ");
                } else {
                    System.out.print(Tools.print(colorText, "", "               CONFIG                      "));
                }
                System.out.print(Tools.print(colorUI, "", "║"));
            } else if (i == 8) {
                System.out.print(Tools.print(colorUI, "", "║"));
                if (menuOption == 4) {
                    System.out.print("              > ");
                    System.out.print(Tools.print("white", colorHighlight, "EXIT"));
                    System.out.print("                       ");
                } else {
                    System.out.print(Tools.print(colorText, "", "               EXIT                        "));
                }
                System.out.print(Tools.print(colorUI, "", "║"));
            } else {
                for (int j = 0; j < w; j++) {
                    if (j == 0 || j == w - 1) {
                        System.out.print(Tools.print(colorUI, "", "║"));
                    } else {
                        System.out.print(" ");
                    }
                }

            }
            System.out.println("");
        }
    }

    public static void loadingAnimation() throws InterruptedException {
        for (int i = 0; i < 7; i++) {
            Tools.clearConsole();
            loadingStartScreen(i);
            TimeUnit.MILLISECONDS.sleep(300);
        }
        TimeUnit.MILLISECONDS.sleep(600);
    }

    public static void loadingStartScreen(int counter) throws InterruptedException {
        for (int i = 0; i < h; i++) {
            if (i == 0) {
                for (int j = 0; j < w; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(colorUI, "", "╔"));
                    } else if (j == w - 1) {
                        System.out.print(Tools.print(colorUI, "", "╗"));
                    } else {
                        System.out.print(Tools.print(colorUI, "", "═"));
                    }
                }
            } else if (i == h - 1) {
                for (int j = 0; j < w; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(colorUI, "", "╚"));
                    } else if (j == w - 1) {
                        System.out.print(Tools.print(colorUI, "", "╝"));
                    } else {
                        System.out.print(Tools.print(colorUI, "", "═"));
                    }
                }
            } else if (i == 6) {
                for (int j = 0; j < w; j++) {
                    if (j == 0 || j == w - 1) {
                        System.out.print(Tools.print(colorUI, "", "║"));
                    } else if (j == 14) {
                        System.out.print(Tools.print(colorUI, "", "["));
                    } else if (j == w - 15) {
                        System.out.print(Tools.print(colorUI, "", "]"));
                    } else if (j >= 15 && j < w - 15) {
                        if (j == 15) {
                            switch (counter) {
                                case 0:
                                    System.out.print(Tools.print(colorUI, "", "░░░░░░░░░░░░░░░"));
                                    break;
                                case 1:
                                    System.out.print(Tools.print(colorUI, "", "▓▓▒░░░░░░░░░░░░"));
                                    break;
                                case 2:
                                    System.out.print(Tools.print(colorUI, "", "▓▓▓▓▓▒░░░░░░░░░"));
                                    break;
                                case 3:
                                    System.out.print(Tools.print(colorUI, "", "▓▓▓▓▓▓▓▓▒░░░░░░"));
                                    break;
                                case 4:
                                    System.out.print(Tools.print(colorUI, "", "▓▓▓▓▓▓▓▓▓▓▓▒░░░"));
                                    break;
                                case 5:
                                    System.out.print(Tools.print(colorUI, "", "▓▓▓▓▓▓▓▓▓▓▓▓▓▒░"));
                                    break;
                                case 6:
                                    System.out.print(Tools.print(colorUI, "", "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓"));
                                    break;
                            }
                        }
                    } else {
                        System.out.print(Tools.print(colorUI, "", " "));
                    }
                }
            } else if (i == 7 && counter == 6) {
                System.out.print(Tools.print(colorUI, "", "║"));
                System.out.print("                  ");
                System.out.print("¡DONE!");
                System.out.print("                   ");
                System.out.print(Tools.print(colorUI, "", "║"));
            } else if (i == 7) {
                System.out.print(Tools.print(colorUI, "", "║"));
                System.out.print("                  ");
                System.out.print("LOADING");
                System.out.print("                  ");
                System.out.print(Tools.print(colorUI, "", "║"));
            } else {
                for (int j = 0; j < w; j++) {
                    if (j == 0 || j == w - 1) {
                        System.out.print(Tools.print(colorUI, "", "║"));
                    } else {
                        System.out.print(" ");
                    }
                }

            }
            System.out.println("");
        }
    }

    public static void boardSizeScreen(int wValue, int hValue, int optionSelected) {
        Tools.clearConsole();

        for (int i = 0; i < h; i++) {
            if (i == 0) {
                for (int j = 0; j < w; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(colorUI, "", "╔"));
                    } else if (j == w - 1) {
                        System.out.print(Tools.print(colorUI, "", "╗"));
                    } else {
                        System.out.print(Tools.print(colorUI, "", "═"));
                    }
                }
            } else if (i == h - 1) {
                for (int j = 0; j < w; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(colorUI, "", "╚"));
                    } else if (j == w - 1) {
                        System.out.print(Tools.print(colorUI, "", "╝"));
                    } else {
                        System.out.print(Tools.print(colorUI, "", "═"));
                    }
                }
            } else if (i == 2) {
                System.out.print(Tools.print(colorUI, "", "║               "));
                System.out.print(Tools.print("blue", colorText, " BOARD  SIZE "));
                System.out.print(Tools.print(colorUI, "", "               ║"));
            } else if (i == 4) {
                System.out.print(Tools.print(colorUI, "", "║               " + spaceSingleDigit(wValue - 1) + "                " + spaceSingleDigit(hValue - 1) + "        ║"));
            } else if (i == 5) {
                System.out.print(Tools.print(colorUI, "", "║"));
                System.out.print(Tools.print(colorText, "", "       WIDTH: "));
                System.out.print(Tools.print(colorUI, "", "║"));
                if (optionSelected == 1) {
                    System.out.print(Tools.print("white", "red", spaceSingleDigit(wValue)));
                } else {
                    System.out.print(Tools.print("white", "", spaceSingleDigit(wValue)));
                }
                System.out.print(Tools.print(colorUI, "", "║"));
                System.out.print(Tools.print(colorText, "", "      HEIGHT: "));
                System.out.print(Tools.print(colorUI, "", "║"));
                if (optionSelected == 2) {
                    System.out.print(Tools.print("white", "red", spaceSingleDigit(hValue)));
                } else {
                    System.out.print(Tools.print("white", "", spaceSingleDigit(hValue)));
                }
                System.out.print(Tools.print(colorUI, "", "║       ║"));
            } else if (i == 6) {
                System.out.print(Tools.print(colorUI, "", "║               " + spaceSingleDigit(wValue + 1) + "                " + spaceSingleDigit(hValue + 1) + "        ║"));
            } else if (i == 9) {
                if (optionSelected == 3) {
                    System.out.print(Tools.print(colorUI, "", "║                 "));
                    System.out.print(Tools.print("white", "red", " N E X T "));
                    System.out.print(Tools.print(colorUI, "", "                 ║"));
                } else {
                    System.out.print(Tools.print(colorUI, "", "║                  N E X T                  ║"));
                }
            } else {
                for (int j = 0; j < w; j++) {
                    if (j == 0 || j == w - 1) {
                        System.out.print(Tools.print(colorUI, "", "║"));
                    } else {
                        System.out.print(" ");
                    }
                }

            }
            System.out.println("");
        }
    }

    /**
     * Coje un int y le añade un 0 si es solo 1 digito.
     *
     * @param number Numero a formatear.
     * @return String formateada.
     */
    public static String spaceSingleDigit(int number) {
        if (number < 10) {
            return "0" + String.valueOf(number);
        } else {
            return String.valueOf(number);
        }
    }
}
