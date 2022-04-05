package gamejava;

import java.util.concurrent.TimeUnit;

public class GameJava {

    public static String INPUT = ""; //guarda la ultima tecla pulsada
    public static int inputRate = 20; //delay entre consultas del input

    public static void main(String[] args) throws InterruptedException {
        boolean isGameRunning = true, sectionRunning = true;
        InputListener keyInput = new InputListener(); //crea y abre la ventana java

        Tools.clearConsole(); //sin este primer clear, no funcionan los colores en CMD windows

        do {
            //////////////////////////////////////////
            /////////  START GAME SECTION  ///////////
            //////////////////////////////////////////
            //
            printStartSection(); //imprime pantalla inicio
            do {
                TimeUnit.MILLISECONDS.sleep(1000 / inputRate);
                sectionRunning = endSectionIf("enter"); //acaba el do-while(seccion) si se pulsa enter
            } while (sectionRunning);

            //////////////////////////////////////////
            ///////  SETUP SETTINGS SECTION  /////////
            //////////////////////////////////////////
            //
            printGameBoard();
            System.out.println("Pulsa 'ESC' para cerrar...");
            do {
                TimeUnit.MILLISECONDS.sleep(1000 / inputRate);
                sectionRunning = endSectionIf("escape");
            } while (sectionRunning);

            //////////////////////////////////////////
            ////////  INGAME PLAYING SECTION  ////////
            //////////////////////////////////////////
            //
            //////////////////////////////////////////
            /////////////  END SECTION  //////////////
            //////////////////////////////////////////
            System.out.println("╔ ═ ╦ ╗ ╠ ║ ╝ ╚");
            System.out.println("¥ § ±  ¥/ §┘ ±>  $  ");

            int userInteger = 0;
            sectionRunning = true;
            //Tools.clearConsole();
            printOptionMenu(userInteger);

            do {
                TimeUnit.MILLISECONDS.sleep(1000 / inputRate);

                switch (INPUT) {
                    case "up":
                        if (userInteger < 10) {
                            userInteger++;
                            Tools.clearConsole();
                            printOptionMenu(userInteger);
                        }
                        break;
                    case "down":
                        if (userInteger > 0) {
                            userInteger--;
                            Tools.clearConsole();
                            printOptionMenu(userInteger);
                        }
                        break;
                    case "enter":
                        sectionRunning = false;
                        resetInput();
                        break;
                }

                resetInput();

            } while (sectionRunning);

            isGameRunning = false;
            keyInput.dispose();

        } while (isGameRunning);
    }

    //////////////////////////////////////////
    //////////////  FUNCIONES  ///////////////
    //////////////////////////////////////////
    //imprime la pantalla de espera para empezar
    public static void printStartSection() {
        int w = 40, h = 9;
        String title = color.print("cyan", "", "▒▒▒▒▒▒▒▒ ")
                + "Press " + color.print("white", "red", "ENTER")
                + " to start..." + color.print("cyan", "", " ▒▒▒▒▒▒▒");

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (i == h / 2) {
                    if (j == 0) {
                        System.out.print(title);
                    }
                } else {
                    System.out.print(color.print("cyan", "", "▒"));
                }
            }
            System.out.println("");
        }
    }

    public static void printGameBoard() {
        int w = 40, h = 9;
        
        for (int i = 0; i < h + 2; i++) {
            if (i == 0) { //primera linea
                for (int j = 0; j < w + 2; j++) {
                    if (j == 0) {
                        System.out.print(color.print("red", "", "╔"));
                    } else if (j == w + 1) {
                        System.out.print(color.print("red", "", "╗"));
                    } else {
                        System.out.print(color.print("red", "", "═"));
                    }
                }
            } else if (i == h + 1) { //ultima linea
                for (int j = 0; j < w + 2; j++) {
                    if (j == 0) {
                        System.out.print(color.print("red", "", "╚"));
                    } else if (j == w + 1) {
                        System.out.print(color.print("red", "", "╝"));
                    } else {
                        System.out.print(color.print("red", "", "═"));
                    }
                }
            } else { //lineas intemedias
                for (int j = 0; j < w + 2; j++) {
                    if (j == 0 || j == w + 1) {
                        System.out.print(color.print("red", "", "║"));
                    } else {
                        System.out.print(color.print("cyan", "", "▒"));
                    }
                }
            }
            System.out.println("");
        }
    }

    public static void printOptionMenu(int userInteger) {
        int w = 40, h = 9;

        for (int i = 0; i < h + 2; i++) {
            if (i == 0) { //primera linea
                for (int j = 0; j < w + 2; j++) {
                    if (j == 0) {
                        System.out.print(color.print("red", "", "╔"));
                    } else if (j == w + 1) {
                        System.out.print(color.print("red", "", "╗"));
                    } else {
                        System.out.print(color.print("red", "", "═"));
                    }
                }
            } else if (i == h + 1) { //ultima linea
                for (int j = 0; j < w + 2; j++) {
                    if (j == 0) {
                        System.out.print(color.print("red", "", "╚"));
                    } else if (j == w + 1) {
                        System.out.print(color.print("red", "", "╝"));
                    } else {
                        System.out.print(color.print("red", "", "═"));
                    }
                }
            } else if (i == (h / 2) + 1) { // linea media
                for (int j = 0; j < w + 2; j++) {
                    if (j == 0 || j == w + 1) {
                        System.out.print(color.print("red", "", "║"));
                    } else if (j == 1) {
                        System.out.print(color.print("cyan", "", "                 -" + color.print("white", "red", " " + String.valueOf(userInteger) + " ") + "-                  "));
                    }
                }
            } else { //lineas intemedias
                for (int j = 0; j < w + 2; j++) {
                    if (j == 0 || j == w + 1) {
                        System.out.print(color.print("red", "", "║"));
                    } else {
                        System.out.print(color.print("cyan", "", " "));
                    }
                }
            }
            System.out.println("");
        }
    }

    //reseteal el input a "" (vacio)
    public static void resetInput() {
        INPUT = "";
    }

    //acaba la seccion si se pulsa la tecla que se pasa
    public static boolean endSectionIf(String closeKey) {
        if (INPUT.equals(closeKey)) {
            resetInput();
            Tools.clearConsole();
            return false;
        } else {
            return true;
        }
    }
}
