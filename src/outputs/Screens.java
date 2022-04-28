package outputs;

import gamejava.GameJava;
import utilities.Tools;
import static gamejava.GameJava.*;
import gamejava.Play;
import java.util.concurrent.TimeUnit;
import static outputs.Board.Character;
import static outputs.Board.Enemy;
import static outputs.Board.voidSquare;
import players.Enemies;
import players.Player;

public class Screens {

    public static String colorUI = "cyan",
            colorText = "yellow",
            colorHighlight = "red";

    public static int w = 45, h = 11;
    public static boolean firstTime = true;

    //
    //
    //
    //////////////////////////////////////////
    // Funciones que leen el INPUT y llaman
    // a las funciones de imprimir fotograma.
    //////////////////////////////////////////
    /**
     * Pantalla de espera inicial. No continua hasta recibir un INPUT.
     *
     * @throws InterruptedException
     */
    public static void printWaitScreen() throws InterruptedException {
        int counter = 0;
        boolean state = false; //Indica si texto con fondo o sin.

        Screens.waitScreen(state);

        do {
            //Una iteracion 50ms, cada iteracion +1 al contador.
            //En la iteracion 20 llevamos 1s (20x50=1000ms), entramos al IF y vuelta a empezar (contador = 0).
            //Con esto conseguimos leer el INPUT rapido, pero cambiar el fotograma de la consola cada 1s.
            if ((counter * 50) == 1000) {
                if (state) {
                    state = false; //apagado
                    Screens.waitScreen(state);
                } else {
                    state = true; //encendido
                    Screens.waitScreen(state);
                }
                counter = 0;
            }
            TimeUnit.MILLISECONDS.sleep(1000 / INPUT_RATE);
            counter++;
        } while (INPUT.equals(""));
        INPUT = "";
    }

    /**
     * Animacion carga del juego.
     *
     * @throws InterruptedException
     */
    public static void loadingAnimation() throws InterruptedException {
        for (int i = 0; i <= 8; i++) {
            Tools.clearConsole();
            loadingStartScreen(i);
            if (GameJava.INPUT.equals("enter")) {
                i = h;
            }
            TimeUnit.MILLISECONDS.sleep(250);
        }
        TimeUnit.MILLISECONDS.sleep(500);
    }

    /**
     * Pantalla elecion tamaño del tablero.
     *
     * @throws InterruptedException
     */
    public static void boardSizeScreen() throws InterruptedException {

        int valorInicial = (MIN_BOARD_SIZE + MAX_BOARD_SIZE) / 2;
        boolean menuActive = true;

        Tools.clearConsole();

        if (firstTime) {
            widthBoard = valorInicial;
            heightBoard = valorInicial;
            printBoardSize();
            firstTime = false;
            Board.firstPrint = true;
        } else {
            printBoardSize();
            Board.firstPrint = true;
        }

        do {
            if (Tools.isRunningCMD()) {
                switch (INPUT) {
                    case "up":
                        if (heightBoard > MIN_BOARD_SIZE) {
                            heightBoard--;
                            printBoardSize();
                        }
                        break;
                    case "down":
                        if (heightBoard < MAX_BOARD_SIZE) {
                            heightBoard++;
                            printBoardSize();
                        }
                        break;
                }
            } else {
                switch (INPUT) {
                    case "up":
                        if (heightBoard < MAX_BOARD_SIZE) {
                            heightBoard++;
                            printBoardSize();
                        }
                        break;
                    case "down":
                        if (heightBoard > MIN_BOARD_SIZE) {
                            heightBoard--;
                            printBoardSize();
                        }
                        break;
                }
            }
            switch (INPUT) {
                case "left":
                    if (widthBoard > MIN_BOARD_SIZE) {
                        widthBoard--;
                        printBoardSize();
                    }
                    break;
                case "right":
                    if (widthBoard < MAX_BOARD_SIZE) {
                        widthBoard++;
                        printBoardSize();
                    }
                    break;
                case "enter":
                    menuActive = false;
                    break;
            }
            INPUT = "";
            TimeUnit.MILLISECONDS.sleep(1000 / INPUT_RATE);
        } while (menuActive);
    }

    /**
     * Pantalla elecion personaje.
     *
     * @throws InterruptedException
     */
    public static void characterSelectorScreen() throws InterruptedException {
        SECTION_RUNNING = true;
        Tools.clearConsole();
        menuOption = 1;
        Screens.characterSelectorScreen(menuOption, secondSelection);

        do {
            switch (INPUT) {
                case "up":
                    if (secondSelection == 2) {
                        secondSelection = 1;
                        Screens.characterSelectorScreen(menuOption, secondSelection);
                    }
                    INPUT = "";
                    break;
                case "down":
                    if (secondSelection == 1) {
                        secondSelection = 2;
                        Screens.characterSelectorScreen(menuOption, secondSelection);
                    }
                    INPUT = "";
                    break;
                case "left":
                    if (menuOption > 1 && secondSelection == 1) {
                        menuOption--;
                        Screens.characterSelectorScreen(menuOption, secondSelection);
                    }
                    INPUT = "";
                    break;
                case "right":
                    if (menuOption < 3 && secondSelection == 1) {
                        menuOption++;
                        Screens.characterSelectorScreen(menuOption, secondSelection);
                    } else if (secondSelection == 2) {
                        character = menuOption - 1;
                        SECTION_RUNNING = false;
                    }
                    INPUT = "";
                    break;
                case "enter":
                    if (secondSelection == 1) {
                        secondSelection = 2;
                        Screens.characterSelectorScreen(menuOption, secondSelection);
                    } else if (secondSelection == 2) {
                        character = menuOption - 1;
                        SECTION_RUNNING = false;
                    }
                    INPUT = "";
                    break;
            }
            TimeUnit.MILLISECONDS.sleep(1000 / INPUT_RATE);
        } while (SECTION_RUNNING);
        secondSelection = 1;
    }

    /**
     * Menu donde escogemos la dificultad del juego.
     *
     * @throws InterruptedException
     */
    public static void gameDifficultyScreen() throws InterruptedException {
        SECTION_RUNNING = true;
        Tools.clearConsole();
        numEnemies = 1;
        menuOption = 1;
        Screens.gameDifficultyScreen(menuOption);

        do {
            switch (INPUT) {
                case "up":
                    if (menuOption > 1) {
                        menuOption--;
                        Screens.gameDifficultyScreen(menuOption);
                        INPUT = "";
                    }
                    break;
                case "down":
                    if (menuOption < 3) {
                        menuOption++;
                        Screens.gameDifficultyScreen(menuOption);
                        INPUT = "";
                    }
                    break;
                case "right":
                case "enter":
                    //dependiendo la dificultad, hay un rango de enemigos que apareceran
                    GameJava.difficultSelection = menuOption;
                    switch (menuOption) {
                        case 1: //easy
                            GameJava.numEnemies = Tools.random(1, 2);
                            break;
                        case 2: //medium
                            GameJava.numEnemies = Tools.random(2, 3);
                            break;
                        case 3: //hard
                            GameJava.numEnemies = Tools.random(4, 6);
                            break;
                    }
                    Tools.clearConsole();
                    SECTION_RUNNING = false;
                    INPUT = "";
                    break;
            }
            TimeUnit.MILLISECONDS.sleep(1000 / INPUT_RATE);
        } while (SECTION_RUNNING);
    }

    /**
     * Menú de la pantalla final.
     *
     * @throws InterruptedException
     */
    public static void endGameScreen() throws InterruptedException {
        int menuEnding = 1;
        boolean endingGame = true;

        if (Player.HP == 0) {
            Screens.youLoseScreen(1);
        } else {
            Screens.youWinScreen(1);
        }

        do {
            switch (INPUT) {
                case "up":
                    if (menuEnding > 1) {
                        menuEnding--;
                        if (Player.HP == 0) {
                            Screens.youLoseScreen(menuEnding);
                        } else {
                            Screens.youWinScreen(menuEnding);
                        }
                        INPUT = "";
                    }
                    break;
                case "down":
                    if (menuEnding < 2) {
                        menuEnding++;
                        menuEnding = 2;
                        if (Player.HP == 0) {
                            Screens.youLoseScreen(menuEnding);
                        } else {
                            Screens.youWinScreen(menuEnding);
                        }
                        INPUT = "";
                    }
                    break;
                case "enter":
                    Tools.clearConsole();
                    endingGame = false;
                    if (menuEnding == 1) {
                        Play.playingGame();
                    } else {
                        Play.isPlayingGame = false;
                        INPUT = "4";
                    }
                    break;
            }
            TimeUnit.MILLISECONDS.sleep(1000 / INPUT_RATE);
        } while (endingGame);
    }

    /**
     * Imprime el logo junto a los creditos del juego.
     */
    public static void credits() {
        Tools.clearConsole();

        System.out.print(
                ""
                + Tools.print(colorUI, "", "           ________________________          \n")
                + Tools.print(colorUI, "", "          / ") + Tools.print(colorText, "", "╦ ╦╦   ╔═╗╔═╗╔═╗╔═╗╔╦╗") + Tools.print(colorUI, "", " \\        \n")
                + Tools.print(colorUI, "", "         /  ") + Tools.print(colorText, "", "╠═╣║ ═ ╚═╗╠═╝║╣ ║╣  ║║") + Tools.print(colorUI, "", "  \\       \n")
                + Tools.print(colorUI, "", "        /   ") + Tools.print(colorText, "", "╩ ╩╩   ╚═╝╩  ╚═╝╚═╝═╩╝") + Tools.print(colorUI, "", "   \\      \n")
                + Tools.print(colorUI, "", "       /   ") + Tools.print(colorText, "", "╔╗╔╔═╗╦ ╦  ╦ ╦╔═╗╦  ╦╔═╗") + Tools.print(colorUI, "", "   \\     \n")
                + Tools.print(colorUI, "", "      /    ") + Tools.print(colorText, "", "║║║║╣ ║║║  ║║║╠═╣╚╗╔╝║╣") + Tools.print(colorUI, "", "     \\    \n")
                + Tools.print(colorUI, "", "     /     ") + Tools.print(colorText, "", "╝╚╝╚═╝╚╩╝  ╚╩╝╩ ╩ ╚╝ ╚═╝") + Tools.print(colorUI, "", "     \\   \n")
                + Tools.print(colorUI, "", "    /  ") + Tools.print(colorText, "", "╔═╗╔═╗╦ ╦╦    ╦ ╦╦ ╦╔╗╔╔╦╗╔═╗╦═╗") + Tools.print(colorUI, "", "  \\  \n")
                + Tools.print(colorUI, "", "   /   ") + Tools.print(colorText, "", "╚═╗║ ║║ ║║    ╠═╣║ ║║║║ ║ ║╣ ╠╦╝") + Tools.print(colorUI, "", "   \\ \n")
                + Tools.print(colorUI, "", "  /    ") + Tools.print(colorText, "", "╚═╝╚═╝╚═╝╩═╝  ╩ ╩╚═╝╝╚╝ ╩ ╚═╝╩╚═") + Tools.print(colorUI, "", "    \\\n")
                + Tools.print(colorUI, "", "  \\        ________________________        /\n")
                + Tools.print(colorUI, "", "   \\______/                        \\______/\n"));

        System.out.print("\n\n");

        System.out.println("Game developed in JAVA by:");
        System.out.println("- Maria Garriga");
        System.out.println("- Mario Molina");
        System.out.println("- Pau Pajaro");
        System.out.println("- Victor Guillén");

        System.out.println();
    }

    //////////////////////////////////////
    // Funciones que imprimen fotogramas
    // segun los valores que reciben.
    //////////////////////////////////////
    /**
     * Imprime logo del juego.
     *
     * @param isON Indica si el texto es con fondo rojo o no.
     */
    public static void waitScreen(boolean isON) {
        Tools.clearConsole();
        System.out.print(
                ""
                + Tools.print(colorUI, "", "           ________________________          \n")
                + Tools.print(colorUI, "", "          / ") + Tools.print(colorText, "", "╦ ╦╦   ╔═╗╔═╗╔═╗╔═╗╔╦╗") + Tools.print(colorUI, "", " \\        \n")
                + Tools.print(colorUI, "", "         /  ") + Tools.print(colorText, "", "╠═╣║ ═ ╚═╗╠═╝║╣ ║╣  ║║") + Tools.print(colorUI, "", "  \\       \n")
                + Tools.print(colorUI, "", "        /   ") + Tools.print(colorText, "", "╩ ╩╩   ╚═╝╩  ╚═╝╚═╝═╩╝") + Tools.print(colorUI, "", "   \\      \n")
                + Tools.print(colorUI, "", "       /   ") + Tools.print(colorText, "", "╔╗╔╔═╗╦ ╦  ╦ ╦╔═╗╦  ╦╔═╗") + Tools.print(colorUI, "", "   \\     \n")
                + Tools.print(colorUI, "", "      /    ") + Tools.print(colorText, "", "║║║║╣ ║║║  ║║║╠═╣╚╗╔╝║╣") + Tools.print(colorUI, "", "     \\    \n")
                + Tools.print(colorUI, "", "     /     ") + Tools.print(colorText, "", "╝╚╝╚═╝╚╩╝  ╚╩╝╩ ╩ ╚╝ ╚═╝") + Tools.print(colorUI, "", "     \\   \n")
                + Tools.print(colorUI, "", "    /  ") + Tools.print(colorText, "", "╔═╗╔═╗╦ ╦╦    ╦ ╦╦ ╦╔╗╔╔╦╗╔═╗╦═╗") + Tools.print(colorUI, "", "  \\  \n")
                + Tools.print(colorUI, "", "   /   ") + Tools.print(colorText, "", "╚═╗║ ║║ ║║    ╠═╣║ ║║║║ ║ ║╣ ╠╦╝") + Tools.print(colorUI, "", "   \\ \n")
                + Tools.print(colorUI, "", "  /    ") + Tools.print(colorText, "", "╚═╝╚═╝╚═╝╩═╝  ╩ ╩╚═╝╝╚╝ ╩ ╚═╝╩╚═") + Tools.print(colorUI, "", "    \\\n")
                + Tools.print(colorUI, "", "  \\                                        /\n"));

        if (isON) {
            System.out.println(Tools.print(colorUI, "", "   \\_______") + Tools.print("white", "red", " Hit any key to begin... ") + Tools.print(colorUI, "", "______/ "));
        } else {
            System.out.println(Tools.print(colorUI, "", "   \\_______") + Tools.print("red", "", " Hit any key to begin... ") + Tools.print(colorUI, "", "______/ "));
        }
    }

    /**
     * Imprime menu inicial.
     *
     * @param menuOption Determina que opcion tenemos selecionada.
     */
    public static void startMenu(int menuOption) {
        Tools.clearConsole();

        for (int i = 1; i <= h; i++) {
            //utilitzamos un if para detectar la ultima linea porque switch no funciona con variables

            switch (i) {
                case 1:
                    System.out.print(Tools.print(colorUI, "", "╔════════════════════════════════════════╗"));
                    break;
                case 2: //║  ┌┬┐┌─┐┌┐┌┬ ┬                          ╚╗
                    System.out.print(Tools.print(colorUI, "", "║"));
                    System.out.print(Tools.print(colorText, "", "  ┌┬┐┌─┐┌┐┌┬ ┬                          "));
                    System.out.print(Tools.print(colorUI, "", "╚╗"));
                    break;
                case 3: //║  │││├┤ ││││ │                           ╚╗
                    System.out.print(Tools.print(colorUI, "", "║"));
                    System.out.print(Tools.print(colorText, "", "  │││├┤ ││││ │                           "));
                    System.out.print(Tools.print(colorUI, "", "╚╗"));
                    break;
                case 4: //║  ┴ ┴└─┘┘└┘└─┘                            ╚╗
                    System.out.print(Tools.print(colorUI, "", "║"));
                    System.out.print(Tools.print(colorText, "", "  ┴ ┴└─┘┘└┘└─┘                            "));
                    System.out.print(Tools.print(colorUI, "", "╚╗"));
                    break;
                case 5: //║                 ┌─INFO───────────────┐    ║
                    System.out.print(Tools.print(colorUI, "", "║                 ┌─"));
                    System.out.print(Tools.print(colorText, "", "INFO"));
                    System.out.print(Tools.print(colorUI, "", "───────────────┐    ║"));
                    break;
                case 6: //║    > PLAY       │ Survive agaist the │    ║
                    if (menuOption == 1) {
                        System.out.print(Tools.print(colorUI, "", "║    " + Tools.sc("►") + " "));
                        System.out.print(Tools.print("white", "red", "PLAY"));
                        System.out.print(Tools.print(colorUI, "", "       │ "));
                        System.out.print(Tools.print(colorText, "", "Survive agaist the"));
                        System.out.print(Tools.print(colorUI, "", " │    ║"));
                    } else if (menuOption == 2) {
                        System.out.print(Tools.print(colorUI, "", "║     PLAY        │ "));
                        System.out.print(Tools.print(colorText, "", "Learn the basics"));
                        System.out.print(Tools.print(colorUI, "", "   │    ║"));
                    } else if (menuOption == 3) {
                        System.out.print(Tools.print(colorUI, "", "║     PLAY        │ "));
                        System.out.print(Tools.print(colorText, "", "Bye bye..."));
                        System.out.print(Tools.print(colorUI, "", "         │    ║"));
                    }
                    break;
                case 7: //║     TUTORIAL    │ creatures of this  │    ║
                    if (menuOption == 2) {
                        System.out.print(Tools.print(colorUI, "", "║    " + Tools.sc("►") + " "));
                        System.out.print(Tools.print("white", "red", "TUTORIAL"));
                        System.out.print(Tools.print(colorUI, "", "   │ "));
                        System.out.print(Tools.print(colorText, "", "to play & archive"));
                        System.out.print(Tools.print(colorUI, "", "  │    ║"));
                    } else if (menuOption == 1) {
                        System.out.print(Tools.print(colorUI, "", "║     TUTORIAL    │ "));
                        System.out.print(Tools.print(colorText, "", "creatures of this"));
                        System.out.print(Tools.print(colorUI, "", "  │    ║"));
                    } else if (menuOption == 3) {
                        System.out.print(Tools.print(colorUI, "", "║     TUTORIAL    │ "));
                        System.out.print(Tools.print(colorText, "", "I guess you aren't"));
                        System.out.print(Tools.print(colorUI, "", " │    ║"));
                    }
                    break;
                case 8: //║     EXIT        │ world. If you can. │    ║
                    if (menuOption == 3) {
                        System.out.print(Tools.print(colorUI, "", "║    " + Tools.sc("►") + " "));
                        System.out.print(Tools.print("white", "red", "EXIT"));
                        System.out.print(Tools.print(colorUI, "", "       │ "));
                        System.out.print(Tools.print(colorText, "", "strong enought."));
                        System.out.print(Tools.print(colorUI, "", "    │    ║"));
                    } else if (menuOption == 1) {
                        System.out.print(Tools.print(colorUI, "", "║     EXIT        │ "));
                        System.out.print(Tools.print(colorText, "", "world. If you can."));
                        System.out.print(Tools.print(colorUI, "", " │    ║"));
                    } else if (menuOption == 2) {
                        System.out.print(Tools.print(colorUI, "", "║     EXIT        │ "));
                        System.out.print(Tools.print(colorText, "", "good scores."));
                        System.out.print(Tools.print(colorUI, "", "       │    ║"));
                    }
                    break;
                case 9:
                    System.out.print(Tools.print(colorUI, "", "║                 └────────────────────┘    ║"));
                    break;
                default:
                    //Como SWITCH no acepta variables en el case, usamos un if
                    //en el default para ver si estamos en la ultima iteracion
                    if (i == h) {
                        System.out.print(Tools.print(colorUI, "", "╚═══════════════════════════════════════════╝"));
                    } else {
                        System.out.print(Tools.print(colorUI, "", "║                                           ║"));
                    }
            }
            System.out.println("");
        }
        //String convert (sc) (cambia los caracteres problematicos dependiendo el entorno donde se ejecuta)
        //Si dividimos la linea en varios System.out, se percive un retraso al aparece la linea en consola muy molesto.
        System.out.println(Tools.print(colorUI, "", "   [") + Tools.print(colorText, "", Tools.sc("↑↓")) + Tools.print(colorUI, "", "] - Select") + Tools.print(colorUI, "", "\t  [") + Tools.print(colorText, "", "ENTER") + Tools.print(colorUI, "", "] - Accept"));
    }

    /**
     * Imprime animacion de carga.
     *
     * @param counter Indica cual de los 7 pasos de la animacion reproducir.
     * @throws InterruptedException
     */
    public static void loadingStartScreen(int counter) throws InterruptedException {
        Tools.clearConsole();

        for (int i = 1; i <= h; i++) {
            //utilitzamos un if para detectar la ultima linea porque switch no funciona con variables

            switch (i) {
                case 1:
                    System.out.print(Tools.print(colorUI, "", "╔════════════════════════════════════════╗"));
                    break;
                case 2:
                    System.out.print(Tools.print(colorUI, "", "║                                        ╚╗"));
                    break;
                case 3:
                    System.out.print(Tools.print(colorUI, "", "║                                         ╚╗"));
                    break;
                case 4: //║            ╦  ┌─┐┌─┐┌┬┐┬┌┐┌┌─┐            ║
                    System.out.print(Tools.print(colorUI, "", "║"));
                    switch (counter) {
                        case 0:
                            System.out.print(Tools.print(colorUI, "", "            ┬  ┌─┐┌─┐┌┬┐┬┌┐┌┌─┐           "));
                            System.out.print(Tools.print(colorUI, "", "╚╗"));
                            break;
                        case 1:
                            System.out.print(Tools.print(colorText, "", "            ╦  "));
                            System.out.print(Tools.print(colorUI, "", "┌─┐┌─┐┌┬┐┬┌┐┌┌─┐           "));
                            System.out.print(Tools.print(colorUI, "", "╚╗"));
                            break;
                        case 2:
                            System.out.print(Tools.print(colorText, "", "            ╦  ╔═╗"));
                            System.out.print(Tools.print(colorUI, "", "┌─┐┌┬┐┬┌┐┌┌─┐           "));
                            System.out.print(Tools.print(colorUI, "", "╚╗"));
                            break;
                        case 3:
                            System.out.print(Tools.print(colorText, "", "            ╦  ╔═╗╔═╗"));
                            System.out.print(Tools.print(colorUI, "", "┌┬┐┬┌┐┌┌─┐           "));
                            System.out.print(Tools.print(colorUI, "", "╚╗"));
                            break;
                        case 4:
                            System.out.print(Tools.print(colorText, "", "            ╦  ╔═╗╔═╗╔╦╗"));
                            System.out.print(Tools.print(colorUI, "", "┬┌┐┌┌─┐           "));
                            System.out.print(Tools.print(colorUI, "", "╚╗"));
                            break;
                        case 5:
                            System.out.print(Tools.print(colorText, "", "            ╦  ╔═╗╔═╗╔╦╗╦"));
                            System.out.print(Tools.print(colorUI, "", "┌┐┌┌─┐           "));
                            System.out.print(Tools.print(colorUI, "", "╚╗"));
                            break;
                        case 6:
                            System.out.print(Tools.print(colorText, "", "            ╦  ╔═╗╔═╗╔╦╗╦╔╗╔"));
                            System.out.print(Tools.print(colorUI, "", "┌─┐           "));
                            System.out.print(Tools.print(colorUI, "", "╚╗"));
                            break;
                        case 7:
                            System.out.print(Tools.print(colorText, "", "            ╦  ╔═╗╔═╗╔╦╗╦╔╗╔╔═╗           "));
                            System.out.print(Tools.print(colorUI, "", "╚╗"));
                            break;
                        case 8:
                            System.out.print(Tools.print(colorText, "", "               ╔╦╗╔═╗╔╗╔╔═╗               "));
                            System.out.print(Tools.print(colorUI, "", "╚╗"));
                            break;
                    }
                    break;
                case 5: //║            ║  │ │├─┤ │││││││ ┬            ║
                    System.out.print(Tools.print(colorUI, "", "║"));
                    switch (counter) {
                        case 0:
                            System.out.print(Tools.print(colorUI, "", "            │  │ │├─┤ │││││││ ┬            "));
                            System.out.print(Tools.print(colorUI, "", "║"));
                            break;
                        case 1:
                            System.out.print(Tools.print(colorText, "", "            ║  "));
                            System.out.print(Tools.print(colorUI, "", "│ │├─┤ │││││││ ┬            "));
                            System.out.print(Tools.print(colorUI, "", "║"));
                            break;
                        case 2:
                            System.out.print(Tools.print(colorText, "", "            ║  ║ ║"));
                            System.out.print(Tools.print(colorUI, "", "├─┤ │││││││ ┬            "));
                            System.out.print(Tools.print(colorUI, "", "║"));
                            break;
                        case 3:
                            System.out.print(Tools.print(colorText, "", "            ║  ║ ║╠═╣"));
                            System.out.print(Tools.print(colorUI, "", " │││││││ ┬            "));
                            System.out.print(Tools.print(colorUI, "", "║"));
                            break;
                        case 4:
                            System.out.print(Tools.print(colorText, "", "            ║  ║ ║╠═╣ ║║"));
                            System.out.print(Tools.print(colorUI, "", "│││││ ┬            "));
                            System.out.print(Tools.print(colorUI, "", "║"));
                            break;
                        case 5:
                            System.out.print(Tools.print(colorText, "", "            ║  ║ ║╠═╣ ║║║"));
                            System.out.print(Tools.print(colorUI, "", "││││ ┬            "));
                            System.out.print(Tools.print(colorUI, "", "║"));
                            break;
                        case 6:
                            System.out.print(Tools.print(colorText, "", "            ║  ║ ║╠═╣ ║║║║║║"));
                            System.out.print(Tools.print(colorUI, "", "│ ┬            "));
                            System.out.print(Tools.print(colorUI, "", "║"));
                            break;
                        case 7:
                            System.out.print(Tools.print(colorText, "", "            ║  ║ ║╠═╣ ║║║║║║║ ╦            "));
                            System.out.print(Tools.print(colorUI, "", "║"));
                            break;
                        case 8:
                            System.out.print(Tools.print(colorText, "", "                ║║║ ║║║║║╣                 "));
                            System.out.print(Tools.print(colorUI, "", "║"));
                            break;
                    }
                    break;
                case 6: //║            ╩═╝└─┘┴ ┴─┴┘┴┘└┘└─┘            ║
                    System.out.print(Tools.print(colorUI, "", "║"));
                    switch (counter) {
                        case 0:
                            System.out.print(Tools.print(colorUI, "", "            ┴─┘└─┘┴ ┴─┴┘┴┘└┘└─┘            "));
                            System.out.print(Tools.print(colorUI, "", "║"));
                            break;
                        case 1:
                            System.out.print(Tools.print(colorText, "", "            ╩═╝"));
                            System.out.print(Tools.print(colorUI, "", "└─┘┴ ┴─┴┘┴┘└┘└─┘            "));
                            System.out.print(Tools.print(colorUI, "", "║"));
                            break;
                        case 2:
                            System.out.print(Tools.print(colorText, "", "            ╩═╝╚═╝"));
                            System.out.print(Tools.print(colorUI, "", "┴ ┴─┴┘┴┘└┘└─┘            "));
                            System.out.print(Tools.print(colorUI, "", "║"));
                            break;
                        case 3:
                            System.out.print(Tools.print(colorText, "", "            ╩═╝╚═╝╩ ╩"));
                            System.out.print(Tools.print(colorUI, "", "─┴┘┴┘└┘└─┘            "));
                            System.out.print(Tools.print(colorUI, "", "║"));
                            break;
                        case 4:
                            System.out.print(Tools.print(colorText, "", "            ╩═╝╚═╝╩ ╩═╩╝"));
                            System.out.print(Tools.print(colorUI, "", "┴┘└┘└─┘            "));
                            System.out.print(Tools.print(colorUI, "", "║"));
                            break;
                        case 5:
                            System.out.print(Tools.print(colorText, "", "            ╩═╝╚═╝╩ ╩═╩╝╩"));
                            System.out.print(Tools.print(colorUI, "", "┘└┘└─┘            "));
                            System.out.print(Tools.print(colorUI, "", "║"));
                            break;
                        case 6:
                            System.out.print(Tools.print(colorText, "", "            ╩═╝╚═╝╩ ╩═╩╝╩╝╚╝"));
                            System.out.print(Tools.print(colorUI, "", "└─┘            "));
                            System.out.print(Tools.print(colorUI, "", "║"));
                            break;
                        case 7:
                            System.out.print(Tools.print(colorText, "", "            ╩═╝╚═╝╩ ╩═╩╝╩╝╚╝╚═╝            "));
                            System.out.print(Tools.print(colorUI, "", "║"));
                            break;
                        case 8:
                            System.out.print(Tools.print(colorText, "", "               ═╩╝╚═╝╝╚╝╚═╝                "));
                            System.out.print(Tools.print(colorUI, "", "║"));
                            break;
                    }
                    break;
                case 8: //║        [██████████████████▒______]        ║
                    System.out.print(Tools.print(colorUI, "", "║"));
                    switch (counter) {
                        case 0:
                            System.out.print(Tools.print(colorUI, "", "      /  "));
                            System.out.print(Tools.print(colorText, "", "▒"));
                            System.out.print(Tools.print(colorUI, "", "▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒  /      "));
                            break;
                        case 1:
                            System.out.print(Tools.print(colorUI, "", "      ─  "));
                            System.out.print(Tools.print(colorText, "", "███▓"));
                            System.out.print(Tools.print(colorUI, "", "▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒  ─      "));
                            break;
                        case 2:
                            System.out.print(Tools.print(colorUI, "", "      \\  "));
                            System.out.print(Tools.print(colorText, "", "██████▓"));
                            System.out.print(Tools.print(colorUI, "", "▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒  \\      "));
                            break;
                        case 3:
                            System.out.print(Tools.print(colorUI, "", "      |  "));
                            System.out.print(Tools.print(colorText, "", "█████████▓"));
                            System.out.print(Tools.print(colorUI, "", "▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒  |      "));
                            break;
                        case 4:
                            System.out.print(Tools.print(colorUI, "", "      ─  "));
                            System.out.print(Tools.print(colorText, "", "█████████████▓"));
                            System.out.print(Tools.print(colorUI, "", "▒▒▒▒▒▒▒▒▒▒▒  ─      "));
                            break;
                        case 5:
                            System.out.print(Tools.print(colorUI, "", "      \\  "));
                            System.out.print(Tools.print(colorText, "", "████████████████▓"));
                            System.out.print(Tools.print(colorUI, "", "▒▒▒▒▒▒▒▒  \\      "));
                            break;
                        case 6:
                            System.out.print(Tools.print(colorUI, "", "      |  "));
                            System.out.print(Tools.print(colorText, "", "███████████████████▓"));
                            System.out.print(Tools.print(colorUI, "", "▒▒▒▒▒  |      "));
                            break;
                        case 7:
                            System.out.print(Tools.print(colorUI, "", "      /  "));
                            System.out.print(Tools.print(colorText, "", "██████████████████████▓"));
                            System.out.print(Tools.print(colorUI, "", "▒▒  /      "));
                            break;
                        case 8:
                            System.out.print(Tools.print(colorText, "", "      ═  █████████████████████████  ═      "));
                            break;
                    }
                    System.out.print(Tools.print(colorUI, "", "║"));
                    break;
                default:
                    //Como SWITCH no acepta variables en el case, usamos un if
                    //en el default para ver si estamos en la ultima iteracion
                    if (i == h) {
                        System.out.print(Tools.print(colorUI, "", "╚═══════════════════════════════════════════╝"));
                    } else {
                        System.out.print(Tools.print(colorUI, "", "║                                           ║"));
                    }
            }
            System.out.println("");
        }
    }

    /**
     * Imprime menu eleccion tamaño tablero.
     *
     * @param width Valor de anchura.
     * @param height Valor de altura.
     */
    public static void printBoardSize() {
        int width = GameJava.widthBoard,
                height = GameJava.heightBoard;

        Tools.clearConsole();

        //cada casilla pasa de valer 1 a el ancho del heuco determinado por el Board
        int voidSize = width * Board.voidSquare.length();

        System.out.println(Tools.print(colorText, "", "╔╗ ╔═╗╔═╗╦═╗╔╦╗  ╔═╗╦╔═╗╔═╗"));
        System.out.println(Tools.print(colorText, "", "╠╩╗║ ║╠═╣╠╦╝ ║║  ╚═╗║╔═╝║╣    ") + "Min: " + MIN_BOARD_SIZE);
        System.out.println(Tools.print(colorText, "", "╚═╝╚═╝╩ ╩╩╚══╩╝  ╚═╝╩╚═╝╚═╝   ") + "Max: " + MAX_BOARD_SIZE);

        height += 2;

        for (int i = 1; i <= height; i++) {

            switch (i) {
                case 1:
                    Tools.printRow('╔', '═', voidSize + 2, '╗', colorUI);
                    break;
                default:
                    //Como SWITCH no acepta variables en el case, usamos un if
                    //en el default para ver si estamos en la ultima iteracion
                    if (i == height) {
                        System.out.print(Tools.print(colorUI, "", "╚"));
                        for (int j = 0; j < voidSize / 2; j++) {
                            if (j == (voidSize / 2) - 1) {
                                System.out.print(Tools.print(colorUI, "", "╗"));
                            } else {
                                System.out.print(Tools.print(colorUI, "", "═"));
                            }
                        }
                        System.out.print(Tools.print(colorText, "", String.valueOf(width)));
                        System.out.print(Tools.print(colorUI, "", "╔"));
                        for (int j = 0; j < (voidSize / 2) - 3; j++) {
                            System.out.print(Tools.print(colorUI, "", "═"));
                        }
                        if (width % 2 == 0) {
                            System.out.print(Tools.print(colorUI, "", "╝"));
                        } else {
                            System.out.print(Tools.print(colorUI, "", "═╝"));
                        }
                    } else if (i == (height / 2) - 1) {
                        System.out.print(Tools.print(colorUI, "", "║"));
                        for (int j = 1; j <= width; j++) {
                            System.out.print(Tools.print(Board.bgColor, "", Board.voidSquare));
                        }
                        System.out.print(Tools.print(colorUI, "", "╚"));
                    } else if (i == height / 2) {
                        System.out.print(Tools.print(colorUI, "", "║"));
                        for (int j = 1; j <= width; j++) {
                            System.out.print(Tools.print(Board.bgColor, "", Board.voidSquare));
                        }
                        System.out.print(Tools.print(colorText, "", String.valueOf(height - 2)));
                    } else if (i == (height / 2) + 1) {
                        System.out.print(Tools.print(colorUI, "", "║"));
                        for (int j = 1; j <= width; j++) {
                            System.out.print(Tools.print(Board.bgColor, "", Board.voidSquare));
                        }
                        System.out.print(Tools.print(colorUI, "", "╔"));
                    } else {
                        System.out.print(Tools.print(colorUI, "", "║"));
                        for (int j = 1; j <= width; j++) {
                            System.out.print(Tools.print(Board.bgColor, "", Board.voidSquare));
                        }
                        System.out.print(Tools.print(colorUI, "", "║"));
                    }
            }
            System.out.println("");
        }
    }

    /**
     * Imprime menu eleccion personaje.
     *
     * @param optionSelected Indica que personaje esta selecionado.
     * @param secondSelection Indica si estamos elijiendo personaje o en el
     * ENTER.
     */
    public static void characterSelectorScreen(int optionSelected, int secondSelection) {
        Tools.clearConsole();

        for (int i = 0; i < h; i++) {
            if (i == 0) {
                Tools.printRow('╔', '═', w, '╗', colorUI);
            } else if (i == h - 1) {
                Tools.printRow('╚', '═', w, '╝', colorUI);
            } else if (i == 2) {
                System.out.print(Tools.print(colorUI, "", "║             "));
                System.out.print(Tools.print("blue", colorText, " CHOOSE CHARACTER "));
                System.out.print(Tools.print(colorUI, "", "            ║"));
            } else if (i == 5) {
                System.out.print(Tools.print(colorUI, "", "║                             ┌─────┐       ║"));
            } else if (i == 6) {
                if (optionSelected == 1) {
                    System.out.print(Tools.print(colorUI, "", "║             "));
                    if (secondSelection == 1) {
                        System.out.print(Tools.print("white", "red", "WARRIOR"));
                    } else {
                        System.out.print(Tools.print(colorText, "", "WARRIOR"));
                    }
                    System.out.print(Tools.print(colorUI, "", " >       │  "));
                    System.out.print(Tools.print(colorText, "", String.valueOf(GameJava.CHAR_GUERRERO)));
                    System.out.print(Tools.print(colorUI, "", "  │       ║"));
                } else if (optionSelected == 2) {
                    System.out.print(Tools.print(colorUI, "", "║           < "));
                    if (secondSelection == 1) {
                        System.out.print(Tools.print("white", "red", "WIZARD"));
                    } else {
                        System.out.print(Tools.print(colorText, "", "WIZARD"));
                    }
                    System.out.print(Tools.print(colorUI, "", " >        │  "));
                    System.out.print(Tools.print(colorText, "", String.valueOf(GameJava.CHAR_MAGO)));
                    System.out.print(Tools.print(colorUI, "", "  │       ║"));
                } else if (optionSelected == 3) {
                    System.out.print(Tools.print(colorUI, "", "║           < "));
                    if (secondSelection == 1) {
                        System.out.print(Tools.print("white", "red", "PRIEST"));
                    } else {
                        System.out.print(Tools.print(colorText, "", "PRIEST"));
                    }
                    System.out.print(Tools.print(colorUI, "", "          │  "));
                    System.out.print(Tools.print(colorText, "", String.valueOf(GameJava.CHAR_SACERDOTE)));
                    System.out.print(Tools.print(colorUI, "", "  │       ║"));
                }
            } else if (i == 7) {
                System.out.print(Tools.print(colorUI, "", "║                             └─────┘       ║"));
            } else if (i == 9) {
                if (secondSelection == 2) {
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
     * Imprime menu eleccion dificultad.
     *
     * @param menuOption Indica la opcion selecionada.
     */
    public static void gameDifficultyScreen(int menuOption) {
        Tools.clearConsole();

        for (int i = 0; i < h; i++) {
            if (i == 0) {
                Tools.printRow('╔', '═', w, '╗', colorUI);
            } else if (i == h - 1) {
                Tools.printRow('╚', '═', w, '╝', colorUI);
            } else if (i == 2) {
                System.out.print(Tools.print(colorUI, "", "║             "));
                System.out.print(Tools.print("blue", colorText, " GAME DIFFICULTY "));
                System.out.print(Tools.print(colorUI, "", "             ║"));
            } else if (i == 5) {
                System.out.print(Tools.print(colorUI, "", "║"));
                if (menuOption == 1) {
                    System.out.print("                 > ");
                    System.out.print(Tools.print("white", colorHighlight, "EASY"));
                    System.out.print("                    ");
                } else {
                    System.out.print(Tools.print(colorText, "", "                   EASY                    "));
                }
                System.out.print(Tools.print(colorUI, "", "║"));
            } else if (i == 6) {
                System.out.print(Tools.print(colorUI, "", "║"));
                if (menuOption == 2) {
                    System.out.print("                > ");
                    System.out.print(Tools.print("white", colorHighlight, "MEDIUM"));
                    System.out.print("                   ");
                } else {
                    System.out.print(Tools.print(colorText, "", "                  MEDIUM                   "));
                }
                System.out.print(Tools.print(colorUI, "", "║"));
            } else if (i == 7) {
                System.out.print(Tools.print(colorUI, "", "║"));
                if (menuOption == 3) {
                    System.out.print("                 > ");
                    System.out.print(Tools.print("white", colorHighlight, "HARD"));
                    System.out.print("                    ");
                } else {
                    System.out.print(Tools.print(colorText, "", "                   HARD                    "));
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

    /**
     * Imprime la pantalla final."YOU WIN"
     *
     * @param menuEnding Indica la posición selecionada, si PLAY AGAIN o EXIT.
     */
    public static void youWinScreen(int menuEnding) {
        Tools.clearConsole();

        for (int i = 0; i < h; i++) {
            if (i == 0) {
                Tools.printRow('╔', '═', w, '╗', colorUI);
            } else if (i == h - 1) {
                Tools.printRow('╚', '═', w, '╝', colorUI);
            } else if (i == 3) {
                System.out.print(Tools.print(colorUI, "", "║       "));
                System.out.print(Tools.print(colorText, "", "╚╦╝ ╔═╗ ╦ ╦     ╦ ╦ ╦ ╦ ╔═╗ ╔"));
                System.out.print(Tools.print(colorUI, "", "       ║"));
            } else if (i == 4) {
                System.out.print(Tools.print(colorUI, "", "║        "));
                System.out.print(Tools.print(colorText, "", "║  ║ ║ ║ ║     ║ ║ ║ ║ ║ ║ ║"));
                System.out.print(Tools.print(colorUI, "", "       ║"));
            } else if (i == 5) {
                System.out.print(Tools.print(colorUI, "", "║        "));
                System.out.print(Tools.print(colorText, "", "╩  ╚═╝ ╚═╝     ╚═╩═╝ ╩ ╝ ╚═╝"));
                System.out.print(Tools.print(colorUI, "", "       ║"));
            } else if (i == 8) {
                if (menuEnding == 1) {
                    System.out.print(Tools.print(colorUI, "", "║           "));
                    System.out.print(Tools.print("white", "red", " P L A Y   A G A I N "));
                    System.out.print(Tools.print(colorUI, "", "           ║"));
                } else {
                    System.out.print(Tools.print(colorUI, "", "║            P L A Y   A G A I N            ║"));

                }
            } else if (i == 9) {
                if (menuEnding == 2) {
                    System.out.print(Tools.print(colorUI, "", "║                 "));
                    System.out.print(Tools.print("white", "red", " E X I T "));
                    System.out.print(Tools.print(colorUI, "", "                 ║"));
                } else {
                    System.out.print(Tools.print(colorUI, "", "║                  E X I T                  ║"));

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
     * Imprime la pantalla final."YOU LOSE"
     *
     * @param menuEnding Indica la posición selecionada, si PLAY AGAIN o EXIT.
     */
    public static void youLoseScreen(int menuEnding) {
        Tools.clearConsole();

        for (int i = 0; i < h; i++) {
            if (i == 0) {
                Tools.printRow('╔', '═', w, '╗', colorUI);
            } else if (i == h - 1) {
                Tools.printRow('╚', '═', w, '╝', colorUI);
            } else if (i == 3) {
                System.out.print(Tools.print(colorUI, "", "║      "));
                System.out.print(Tools.print(colorText, "", "╚╦╝ ╔═╗ ╦ ╦     ╦   ╔═╗ ╔═╗ ╔═╗"));
                System.out.print(Tools.print(colorUI, "", "      ║"));
            } else if (i == 4) {
                System.out.print(Tools.print(colorUI, "", "║       "));
                System.out.print(Tools.print(colorText, "", "║  ║ ║ ║ ║     ║   ║ ║ ╚═╗ ║╣ "));
                System.out.print(Tools.print(colorUI, "", "      ║"));
            } else if (i == 5) {
                System.out.print(Tools.print(colorUI, "", "║       "));
                System.out.print(Tools.print(colorText, "", "╩  ╚═╝ ╚═╝     ╩═╝ ╚═╝ ╚═╝ ╚═╝"));
                System.out.print(Tools.print(colorUI, "", "      ║"));
            } else if (i == 8) {
                if (menuEnding == 1) {
                    System.out.print(Tools.print(colorUI, "", "║           "));
                    System.out.print(Tools.print("white", "red", " P L A Y   A G A I N "));
                    System.out.print(Tools.print(colorUI, "", "           ║"));
                } else {
                    System.out.print(Tools.print(colorUI, "", "║            P L A Y   A G A I N            ║"));

                }
            } else if (i == 9) {
                if (menuEnding == 2) {
                    System.out.print(Tools.print(colorUI, "", "║                 "));
                    System.out.print(Tools.print("white", "red", " E X I T "));
                    System.out.print(Tools.print(colorUI, "", "                 ║"));
                } else {
                    System.out.print(Tools.print(colorUI, "", "║                  E X I T                  ║"));

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
    
    public static void printRing(String character, String enemy, boolean playerTurn) {
        Tools.clearConsole();
        System.out.println(playerTurn);
        int alto = 15;
        int ancho = 20;

        for (int i = 0; i < alto; i++) {
            if (i == 0) {
                for (int j = 0; j < (ancho * 3) - 8; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(Screens.colorUI, "", "╔"));
                    } else if (j == (ancho * 3) - 22) {
                        System.out.print(Tools.print(Screens.colorUI, "", "╦"));
                    } else if (j == (ancho * 3) - 9) {
                        System.out.print(Tools.print(Screens.colorUI, "", "╗"));
                    } else if (j == 13) {
                        System.out.print(Tools.print(Screens.colorUI, "", "╦"));
                    } else {
                        System.out.print(Tools.print(Screens.colorUI, "", "═"));
                    }
                }
            } else if (i == alto - 1) {
                for (int j = 0; j < (ancho * 3) - 8; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(Screens.colorUI, "", "╚"));
                    } else if (j == (ancho * 3) - 22) {
                        System.out.print(Tools.print(Screens.colorUI, "", "╩"));
                    } else if (j == 13) {
                        System.out.print(Tools.print(Screens.colorUI, "", "╩"));
                    } else if (j == (ancho * 3) - 9) {
                        System.out.print(Tools.print(Screens.colorUI, "", "╝"));
                    } else {
                        System.out.print(Tools.print(Screens.colorUI, "", "═"));
                    }
                }
            } else if (i == 5) {
                for (int j = 0; j < ancho; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(colorUI, "", "║"));
                    } else if (j > 4 && j < ancho - 5) {
                        if (j == ancho - 6) {
                            System.out.print(Tools.print(Screens.colorUI, "", "║"));
                        } else if (j == 5) {
                            System.out.print(Tools.print(Screens.colorUI, "", "║"));
                        } else if (j > 5 && j < 10) {
                            if (Player.HP > 90) {
                                if (j == 6) {
                                    System.out.print(Tools.print("green", "", (" ██")));
                                } else if (j == 9) {
                                    System.out.print(Tools.print("green", "", ("██ ")));
                                } else {
                                    System.out.print(Tools.print("green", "", "███"));
                                }
                            } else if (Player.HP > 80) {
                                if (j == 6) {
                                    System.out.print(Tools.print("green", "", (" ██")));
                                } else if (j == 9) {
                                    System.out.print(Tools.print("green", "", ("█  ")));
                                } else {
                                    System.out.print(Tools.print("green", "", "███"));
                                }
                            } else if (Player.HP > 70) {
                                if (j == 6) {
                                    System.out.print(Tools.print("green", "", (" ██")));
                                } else if (j == 9) {
                                    System.out.print(Tools.print("green", "", ("   ")));
                                } else {
                                    System.out.print(Tools.print("green", "", "███"));
                                }
                            } else if (Player.HP > 60) {
                                if (j == 6) {
                                    System.out.print(Tools.print("green", "", (" ██")));
                                } else if (j == 8) {
                                    System.out.print(Tools.print("green", "", ("██ ")));
                                } else if (j == 9) {
                                    System.out.print(Tools.print("green", "", ("   ")));
                                } else {
                                    System.out.print(Tools.print("green", "", "███"));
                                }
                            } else if (Player.HP > 50) {
                                if (j == 6) {
                                    System.out.print(Tools.print("green", "", (" ██")));
                                } else if (j == 8) {
                                    System.out.print(Tools.print("green", "", ("█  ")));
                                } else if (j == 9) {
                                    System.out.print(Tools.print("green", "", ("   ")));
                                } else {
                                    System.out.print(Tools.print("green", "", "███"));
                                }
                            } else if (Player.HP > 40) {
                                if (j == 6) {
                                    System.out.print(Tools.print("yellow", "", (" ██")));
                                } else if (j == 8) {
                                    System.out.print(Tools.print("yellow", "", ("   ")));
                                } else if (j == 9) {
                                    System.out.print(Tools.print("yellow", "", ("   ")));
                                } else {
                                    System.out.print(Tools.print("yellow", "", "███"));
                                }
                            } else if (Player.HP > 30) {
                                if (j == 6) {
                                    System.out.print(Tools.print("yellow", "", (" ██")));
                                } else if (j == 7) {
                                    System.out.print(Tools.print("yellow", "", ("██ ")));
                                } else if (j == 8) {
                                    System.out.print(Tools.print("yellow", "", ("   ")));
                                } else if (j == 9) {
                                    System.out.print(Tools.print("yellow", "", ("   ")));
                                }
                            } else if (Player.HP > 20) {
                                if (j == 6) {
                                    System.out.print(Tools.print("red", "", (" ██")));
                                } else if (j == 7) {
                                    System.out.print(Tools.print("red", "", ("█  ")));
                                } else if (j == 8) {
                                    System.out.print(Tools.print("red", "", ("   ")));
                                } else if (j == 9) {
                                    System.out.print(Tools.print("red", "", ("   ")));
                                }
                            } else if (Player.HP > 10) {
                                if (j == 6) {
                                    System.out.print(Tools.print("red", "", (" ██")));
                                } else if (j == 7) {
                                    System.out.print(Tools.print("red", "", ("   ")));
                                } else if (j == 8) {
                                    System.out.print(Tools.print("red", "", ("   ")));
                                } else if (j == 9) {
                                    System.out.print(Tools.print("red", "", ("   ")));
                                }
                            } else if (Player.HP > 0) {
                                if (j == 6) {
                                    System.out.print(Tools.print("red", "", (" █ ")));
                                } else if (j == 7) {
                                    System.out.print(Tools.print("red", "", ("   ")));
                                } else if (j == 8) {
                                    System.out.print(Tools.print("red", "", ("   ")));
                                } else if (j == 9) {
                                    System.out.print(Tools.print("red", "", ("   ")));
                                }
                            } else if (Player.HP <= 0) {
                                System.out.print(Tools.print("red", "", ("   ")));
                            }
                        } else if (j >= 10 && j < 14) {
                            if (Enemies.HP > 90) {
                                if (j == 10) {
                                    System.out.print(Tools.print("green", "", (" " + "██")));
                                } else if (j == 13) {
                                    System.out.print(Tools.print("green", "", ("██" + " ")));
                                } else {
                                    System.out.print(Tools.print("green", "", "███"));
                                }
                            } else if (Enemies.HP > 80) {
                                if (j == 10) {
                                    System.out.print(Tools.print("green", "", (" " + " █")));
                                } else if (j == 13) {
                                    System.out.print(Tools.print("green", "", ("██" + " ")));
                                } else {
                                    System.out.print(Tools.print("green", "", "███"));
                                }
                            } else if (Enemies.HP > 70) {
                                if (j == 10) {
                                    System.out.print(Tools.print("green", "", (" " + "  ")));
                                } else if (j == 13) {
                                    System.out.print(Tools.print("green", "", ("██" + " ")));
                                } else {
                                    System.out.print(Tools.print("green", "", "███"));
                                }
                            } else if (Enemies.HP > 60) {
                                if (j == 10) {
                                    System.out.print(Tools.print("green", "", ("   ")));
                                } else if (j == 11) {
                                    System.out.print(Tools.print("green", "", (" ██")));
                                } else if (j == 13) {
                                    System.out.print(Tools.print("green", "", ("██ ")));
                                } else {
                                    System.out.print(Tools.print("green", "", "███"));
                                }
                            } else if (Enemies.HP > 50) {
                                if (j == 10) {
                                    System.out.print(Tools.print("green", "", ("   ")));
                                } else if (j == 11) {
                                    System.out.print(Tools.print("green", "", ("  █")));
                                } else if (j == 13) {
                                    System.out.print(Tools.print("green", "", ("██" + " ")));
                                } else {
                                    System.out.print(Tools.print("green", "", "███"));
                                }
                            } else if (Enemies.HP > 40) {
                                if (j == 10) {
                                    System.out.print(Tools.print("green", "", ("   ")));
                                } else if (j == 11) {
                                    System.out.print(Tools.print("green", "", ("   ")));
                                } else if (j == 13) {
                                    System.out.print(Tools.print("yellow", "", ("██" + " ")));
                                } else {
                                    System.out.print(Tools.print("yellow", "", "███"));
                                }
                            } else if (Enemies.HP > 30) {
                                if (j == 10) {
                                    System.out.print(Tools.print("green", "", ("   ")));
                                } else if (j == 11) {
                                    System.out.print(Tools.print("green", "", ("   ")));
                                } else if (j == 12) {
                                    System.out.print(Tools.print("yellow", "", (" ██")));
                                } else if (j == 13) {
                                    System.out.print(Tools.print("yellow", "", ("██" + " ")));
                                }
                            } else if (Enemies.HP > 20) {
                                if (j == 10) {
                                    System.out.print(Tools.print("green", "", ("   ")));
                                } else if (j == 11) {
                                    System.out.print(Tools.print("green", "", ("   ")));
                                } else if (j == 12) {
                                    System.out.print(Tools.print("red", "", ("  █")));
                                } else if (j == 13) {
                                    System.out.print(Tools.print("red", "", ("██" + " ")));
                                }
                            } else if (Enemies.HP > 10) {
                                if (j == 10) {
                                    System.out.print(Tools.print("red", "", ("   ")));
                                } else if (j == 11) {
                                    System.out.print(Tools.print("red", "", ("   ")));
                                } else if (j == 12) {
                                    System.out.print(Tools.print("red", "", ("   ")));
                                } else if (j == 13) {
                                    System.out.print(Tools.print("red", "", ("██" + " ")));
                                }
                            } else if (Enemies.HP > 0) {
                                if (j == 10) {
                                    System.out.print(Tools.print("green", "", ("   ")));
                                } else if (j == 11) {
                                    System.out.print(Tools.print("green", "", ("   ")));
                                } else if (j == 12) {
                                    System.out.print(Tools.print("green", "", ("   ")));
                                } else if (j == 13) {
                                    System.out.print(Tools.print("red", "", ("█" + "  ")));
                                }
                            } else if (Enemies.HP <= 0) {
                                System.out.print(Tools.print("red", "", ("   ")));
                            }
                        } else {
                            System.out.print("   ");
                        }
                    } else if (j == ancho - 1) {
                        System.out.print(Tools.print(colorUI, "", "║"));
                    } else {
                        System.out.print("   ");
                    }
                }
            } else if (i == 6) {
                for (int j = 0; j < ancho; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(colorUI, "", "║"));
                    } else if (j > 0 && j <= 4) {
                        if (j == 1) {
                            System.out.print(" DMG    " + Player.DMG + "  ");
                        } else {
                            System.out.print("");
                        }

                    } else if (j > 4 && j < ancho - 5) {
                        if (j == ancho - 6) {
                            System.out.print(Tools.print(Screens.colorUI, "", "║"));
                        } else if (j == 5) {
                            System.out.print(Tools.print(Screens.colorUI, "", "║"));
                        } else {
                            System.out.print(voidSquare);
                        }
                    } else if (j >= ancho - 5 && j < ancho - 1) {
                        if (j == ancho - 5) {
                            System.out.print(" DMG    " + Enemies.attack + "  ");
                        } else {
                            System.out.print("");
                        }

                    } else if (j == ancho - 1) {
                        System.out.print(Tools.print(colorUI, "", "║"));
                    } else {
                        System.out.print("   ");
                    }
                }
            } else if (i == 7) {
                for (int j = 0; j < ancho; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(colorUI, "", "║"));
                    } else if (j > 0 && j <= 4) {
                        if (j == 1) {
                            if (Player.HP >= 100) {
                                System.out.print(" HP " + Player.HP + "/" + Player.MAXHP + " ");
                            } else if (Player.HP < 10) {
                                System.out.print(" HP " + Player.HP + "/" + Player.MAXHP + "   ");
                            } else {
                                System.out.print(" HP " + Player.HP + "/" + Player.MAXHP + "  ");
                            }

                        } else {
                            System.out.print("");
                        }

                    } else if (j > 4 && j < ancho - 5) {
                        if (j == ancho - 6) {
                            System.out.print(Tools.print(Screens.colorUI, "", "║"));
                        } else if (j == 5) {
                            System.out.print(Tools.print(Screens.colorUI, "", "║"));
                        } else if (j == 7) {
                            System.out.print(Character);
                        } else if (j == ancho - 8) {
                            System.out.print(Enemy);
                        } else {
                            System.out.print(voidSquare);
                        }
                    } else if (j >= ancho - 5 && j < ancho - 1) {
                        if (j == ancho - 5) {
                            if (Enemies.HP >= 100) {
                                System.out.print(" HP " + Enemies.HP + "/" + Enemies.maxHP + " ");
                            } else if (Enemies.HP < 10) {
                                System.out.print(" HP " + Enemies.HP + "/" + Enemies.maxHP + "   ");
                            } else {
                                System.out.print(" HP " + Enemies.HP + "/" + Enemies.maxHP + "  ");
                            }

                        } else {
                            System.out.print("");
                        }

                    } else if (j == ancho - 1) {
                        System.out.print(Tools.print(colorUI, "", "║"));
                    } else {
                        System.out.print("   ");
                    }
                }
            } else if (i == 8) {
                for (int j = 0; j < ancho; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(colorUI, "", "║"));
                    } else if (j > 0 && j <= 4) {
                        if (j == 1) {
                            System.out.print(" LVL    " + Player.LV + "   ");
                        } else {
                            System.out.print("");
                        }

                    } else if (j > 4 && j < ancho - 5) {
                        if (j == ancho - 6) {
                            System.out.print(Tools.print(Screens.colorUI, "", "║"));
                        } else if (j == 5) {
                            System.out.print(Tools.print(Screens.colorUI, "", "║"));
                        } else {
                            System.out.print(voidSquare);
                        }
                    } else if (j >= ancho - 5 && j < ancho - 1) {
                        if (j == ancho - 5) {
                            System.out.print(" LVL    " + Enemies.LVL + "   ");
                        } else {
                            System.out.print("");
                        }

                    } else if (j == ancho - 1) {
                        System.out.print(Tools.print(colorUI, "", "║"));
                    } else {
                        System.out.print("   ");
                    }
                }
            } else {
                for (int j = 0; j < ancho; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(colorUI, "", "║"));
                    } else if (j > 4 && j < ancho - 5) {
                        if (j == ancho - 6) {
                            System.out.print(Tools.print(Screens.colorUI, "", "║"));
                        } else if (j == 5) {
                            System.out.print(Tools.print(Screens.colorUI, "", "║"));
                        } else {
                            System.out.print(voidSquare);
                        }
                    } else if (j == ancho - 1) {
                        System.out.print(Tools.print(colorUI, "", "║"));
                    } else {
                        System.out.print("   ");
                    }

                }
            }
            System.out.println("");
        }
    }

}
