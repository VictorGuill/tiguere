package outputs;

import gamejava.GameJava;
import utilities.Tools;
import static gamejava.GameJava.*;
import java.util.concurrent.TimeUnit;

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
            Screens.boardSizeScreen(valorInicial, valorInicial);
            firstTime = false;
            Board.firstPrint = true;
        } else {
            Screens.boardSizeScreen(widthBoard, heightBoard);
            Board.firstPrint = true;
        }

        do {
            if (Tools.isRunningCMD()) {
                switch (INPUT) {
                    case "up":
                        if (heightBoard > MIN_BOARD_SIZE) {
                            heightBoard--;
                            Screens.boardSizeScreen(widthBoard, heightBoard);
                        }
                        break;
                    case "down":
                        if (heightBoard < MAX_BOARD_SIZE) {
                            heightBoard++;
                            Screens.boardSizeScreen(widthBoard, heightBoard);
                        }
                        break;
                }
            } else {
                switch (INPUT) {
                    case "up":
                        if (heightBoard < MAX_BOARD_SIZE) {
                            heightBoard++;
                            Screens.boardSizeScreen(widthBoard, heightBoard);
                        }
                        break;
                    case "down":
                        if (heightBoard > MIN_BOARD_SIZE) {
                            heightBoard--;
                            Screens.boardSizeScreen(widthBoard, heightBoard);
                        }
                        break;
                }
            }
            switch (INPUT) {
                case "left":
                    if (widthBoard > MIN_BOARD_SIZE) {
                        widthBoard--;
                        Screens.boardSizeScreen(widthBoard, heightBoard);
                    }
                    break;
                case "right":
                    if (widthBoard < MAX_BOARD_SIZE) {
                        widthBoard++;
                        Screens.boardSizeScreen(widthBoard, heightBoard);
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
                            numEnemies = Tools.random(1, 2);
                            break;
                        case 2: //medium
                            numEnemies = Tools.random(2, 3);
                            break;
                        case 3: //hard
                            numEnemies = Tools.random(4, 6);
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

        System.out.println("");
    }

    //
    //
    //
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
     * @param wValue Valor de anchura.
     * @param hValue Valor de altura.
     * @param optionSelected Indica si estamos en la columna izquierda, derecha
     * o el boton ENTER.
     */
    public static void boardSizeScreen(int wValue, int hValue) {
        Tools.clearConsole();

        //cada casilla pasa de valer 1 a el ancho del heuco determinado por el Board
        int voidSize = wValue * Board.voidSquare.length();

        //╔╗ ╔═╗╔═╗╦═╗╔╦╗  ╔═╗╦╔═╗╔═╗   ╔════╦════╗
        System.out.print(Tools.print(colorText, "", "╔╗ ╔═╗╔═╗╦═╗╔╦╗  ╔═╗╦╔═╗╔═╗"));
        System.out.print(Tools.print(colorUI, "", "   ╔═w══╦═h══╗\n"));

        //╠╩╗║ ║╠═╣╠╦╝ ║║  ╚═╗║╔═╝║╣    ║ 15 X 15 ║
        System.out.print(Tools.print(colorText, "", "╠╩╗║ ║╠═╣╠╦╝ ║║  ╚═╗║╔═╝║╣ "));
        System.out.print(Tools.print(colorUI, "", "   ║ "));
        System.out.print(Tools.print(colorText, "", String.valueOf(wValue)));
        System.out.print(Tools.print(colorUI, "", " X "));
        System.out.print(Tools.print(colorText, "", String.valueOf(hValue)));
        System.out.print(Tools.print(colorUI, "", " ║\n"));

        //╚═╝╚═╝╩ ╩╩╚══╩╝  ╚═╝╩╚═╝╚═╝   ╚════╩════╝
        System.out.print(Tools.print(colorText, "", "╚═╝╚═╝╩ ╩╩╚══╩╝  ╚═╝╩╚═╝╚═╝"));
        System.out.print(Tools.print(colorUI, "", "   ╚════╩════╝\n"));

        for (int i = 1; i <= hValue; i++) {

            switch (i) {
                case 1:
                    Tools.printRow('╔', '═', voidSize + 2, '╗', colorUI);
                    break;
                default:
                    //Como SWITCH no acepta variables en el case, usamos un if
                    //en el default para ver si estamos en la ultima iteracion
                    if (i == hValue) {
                        Tools.printRow('╚', '═', voidSize + 2, '╝', colorUI);
                    } else {
                        System.out.print(Tools.print(colorUI, "", "║"));
                        for (int j = 1; j <= voidSize; j++) {
                            System.out.print(Tools.print(Board.bgColor, "", Board.voidCharacterSides));
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
