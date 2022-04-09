package gamejava;

import java.util.concurrent.TimeUnit;

public class GameJava {

    final static int INPUT_RATE = 20; //delay entre consultas del input
    final static int MIN_BOARD_SIZE = 5, MAX_BOARD_SIZE = 15; //maximo y minimo permitido valores tamaño tablero
    final static char CHAR_GUERRERO = '¥', CHAR_MAGO = '£', CHAR_SACERDOTE = '±';

    public static String INPUT = ""; //guarda la ultima tecla pulsada
    public static int menuOption = 1,
            widthBoard = 8, //valor inicial ancho del tablero
            heightBoard = 8; //valor inicial alto del tablero
    public static boolean SECTION_RUNNING = true;

    public static void main(String[] args) throws InterruptedException {

        InputListener keyInput = new InputListener(); //crea y abre la ventana java
        int numEnemies,
                character;
        boolean isGameRunning = true;

        /////////////////////////////////////////////////////
        //////////////   EMPIEZA EL PROGRAMA   //////////////
        /////////////////////////////////////////////////////
        //printWaitScreen(); //pantalla de espera para empezar
        //StartSetup.loadingAnimation(); //animacion de carga
        StartSetup.startMenu(menuOption); //pantalla mnn  menu

        do {

            do {
                switch (INPUT) {
                    case "up":
                        if (menuOption > 1) {
                            menuOption--;
                            StartSetup.startMenu(menuOption);
                            INPUT = "";
                        }
                        break;
                    case "down":
                        if (menuOption < 4) {
                            menuOption++;
                            StartSetup.startMenu(menuOption);
                            INPUT = "";
                        }
                        break;
                    case "enter":
                        switch (menuOption) {
                            case 1: //play
                                //boardSizeScreen();
                                characterSelectorScreen();
                                gameDifficultyScreen();
                                Tools.clearConsole();
                                System.out.println("ee");
                                break;
                            case 2: //tutorial
                                break;
                            case 3: //config
                                break;
                            case 4: //exit
                                Tools.clearConsole();
                                System.out.println("[PANTALLA ADIOS]");
                                SECTION_RUNNING = false;
                                isGameRunning = false;
                                keyInput.dispose(); //elimina ventana creada por JAVA
                                break;
                        }
                        INPUT = "";
                        SECTION_RUNNING = false;
                        break;
                }
                TimeUnit.MILLISECONDS.sleep(1000 / INPUT_RATE);
            } while (SECTION_RUNNING);
        } while (isGameRunning);
    }

    //////////////////////////////////////////
    //////////////  FUNCIONES  ///////////////
    //////////////////////////////////////////
    /**
     * Imprime la pantalla de espera inicial. No continua hasta recibir un
     * INPUT.
     *
     * @throws InterruptedException
     */
    public static void printWaitScreen() throws InterruptedException {
        boolean noUserActivity = true;

        Tools.clearConsole();

        StartSetup.waitScreen();
        do {
            TimeUnit.MILLISECONDS.sleep(1000 / INPUT_RATE);
            if (!INPUT.equals("")) {
                INPUT = "";
                noUserActivity = false;
            }
        } while (noUserActivity);
    }

    /**
     * Menu donde el usuario escoje el tamaño del tablero.
     *
     * @throws InterruptedException
     */
    public static void boardSizeScreen() throws InterruptedException {
        INPUT = "";
        SECTION_RUNNING = true;
        Tools.clearConsole();
        menuOption = 1;
        StartSetup.boardSizeScreen(widthBoard, heightBoard, menuOption);

        do {
            switch (INPUT) {
                case "up":
                    if (menuOption == 1) {
                        if (widthBoard > MIN_BOARD_SIZE) {
                            widthBoard--;
                            StartSetup.boardSizeScreen(widthBoard, heightBoard, menuOption);
                        }
                    } else if (menuOption == 2) {
                        if (heightBoard > MIN_BOARD_SIZE) {
                            heightBoard--;
                            StartSetup.boardSizeScreen(widthBoard, heightBoard, menuOption);
                        }
                    }
                    INPUT = "";
                    break;
                case "down":
                    if (menuOption == 1) {
                        if (widthBoard < MAX_BOARD_SIZE) {
                            widthBoard++;
                            StartSetup.boardSizeScreen(widthBoard, heightBoard, menuOption);
                        }
                    } else if (menuOption == 2) {
                        if (heightBoard < MAX_BOARD_SIZE) {
                            heightBoard++;
                            StartSetup.boardSizeScreen(widthBoard, heightBoard, menuOption);
                        }
                    }
                    INPUT = "";
                    break;
                case "left":
                    if (menuOption > 1) {
                        menuOption--;
                        StartSetup.boardSizeScreen(widthBoard, heightBoard, menuOption);
                    }
                    INPUT = "";
                    break;
                case "right":
                    if (menuOption < 3) {
                        menuOption++;
                        StartSetup.boardSizeScreen(widthBoard, heightBoard, menuOption);
                    } else if (menuOption == 3) {
                        SECTION_RUNNING = false;
                    }
                    INPUT = "";
                    break;
                case "enter":
                    if (menuOption < 3) {
                        menuOption++;
                        StartSetup.boardSizeScreen(widthBoard, heightBoard, menuOption);
                    } else if (menuOption == 3) {
                        SECTION_RUNNING = false;
                    }
                    INPUT = "";
                    break;
            }
            TimeUnit.MILLISECONDS.sleep(1000 / INPUT_RATE);
        } while (SECTION_RUNNING);
    }

    public static void characterSelectorScreen() throws InterruptedException {
        INPUT = "";
        SECTION_RUNNING = true;
        Tools.clearConsole();
        menuOption = 1;
        StartSetup.characterSelectorScreen(menuOption);

        do {
            switch (INPUT) {
                case "up":
                    INPUT = "";
                    break;
                case "down":
                    INPUT = "";
                    break;
                case "left":
                    if (menuOption > 1) {
                        menuOption--;
                        StartSetup.characterSelectorScreen(menuOption);
                    }
                    INPUT = "";
                    break;
                case "right":
                    if (menuOption < 3) {
                        menuOption++;
                        StartSetup.characterSelectorScreen(menuOption);
                    }
                    INPUT = "";
                    break;
                case "enter":
                    INPUT = "";
                    break;
            }
            TimeUnit.MILLISECONDS.sleep(1000 / INPUT_RATE);
        } while (SECTION_RUNNING);
    }

    public static void gameDifficultyScreen() {

        do {

        } while (SECTION_RUNNING);
    }
}
