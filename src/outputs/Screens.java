package outputs;

import gamejava.GameJava;
import utilities.Tools;
import static gamejava.GameJava.*;
import gamejava.GameScores;
import gamejava.Play;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import static outputs.Board.Character;
import static outputs.Board.Enemy;
import static outputs.Board.voidSquare;
import players.Enemies;
import players.Player;

public class Screens {

    public static String colorUI = "cyan",
            colorText = "yellow",
            colorHighlight = "red",
            enemieArmorColor = "red",
            enemieColor = "green",
            enemieSword = "red";

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
     * Pantalla elecion tama??o del tablero.
     *
     * @throws InterruptedException
     */
    public static boolean boardSizeScreen() throws InterruptedException {

        int valorInicial = (MIN_BOARD_SIZE + MAX_BOARD_SIZE) / 2;
        boolean menuActive = true,
                menuCompleted = false;

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
                    menuCompleted = true;
                    break;
                case "escape":
                    menuActive = false;
                    break;
            }
            INPUT = "";
            TimeUnit.MILLISECONDS.sleep(1000 / INPUT_RATE);
        } while (menuActive);
        return menuCompleted;
    }

    /**
     * Pantalla elecion personaje.
     *
     * @throws InterruptedException
     */
    public static boolean characterSelectorScreen() throws InterruptedException {
        boolean showingMenu = true,
                menuCompleted = false;
        Tools.clearConsole();
        menuOption = 0;
        Screens.characterSelectorScreen(menuOption);

        do {
            switch (INPUT) {
                case "left":
                    if (menuOption > 0) {
                        menuOption--;
                        Screens.characterSelectorScreen(menuOption);
                    }
                    break;
                case "right":
                    if (menuOption < 2) {
                        menuOption++;
                        Screens.characterSelectorScreen(menuOption);
                    }
                    break;
                case "enter":
                    character = menuOption;
                    showingMenu = false;
                    menuCompleted = true;
                    break;
                case "escape":
                    showingMenu = false;
                    break;
            }
            INPUT = "";
            TimeUnit.MILLISECONDS.sleep(1000 / INPUT_RATE);
        } while (showingMenu);
        return menuCompleted;
    }

    /**
     * Menu donde escogemos la dificultad del juego.
     *
     * @throws InterruptedException
     */
    public static boolean gameDifficultyScreen() throws InterruptedException {
        boolean showingMenu = true,
                menuCompleted = false;
        Tools.clearConsole();
        menuOption = 1;
        Screens.printDifficultyScreen(menuOption);

        do {
            switch (INPUT) {
                case "left":
                    if (menuOption > 1) {
                        menuOption--;
                        Screens.printDifficultyScreen(menuOption);
                    }
                    break;
                case "right":
                    if (menuOption < 3) {
                        menuOption++;
                        Screens.printDifficultyScreen(menuOption);
                    }
                    break;
                case "enter":
                    //dependiendo la dificultad, hay un rango de enemigos que apareceran
                    menuCompleted = true;
                    GameJava.difficultSelection = menuOption;
                    switch (menuOption) {
                        case 1: //easy
                            GameJava.numEnemies = Tools.random(1, 2);
                            break;
                        case 2: //medium
                            GameJava.numEnemies = Tools.random(3, 4);
                            break;
                        case 3: //hard
                            GameJava.numEnemies = Tools.random(5, 6);
                            break;
                    }
                    switch (GameJava.numEnemies) {
                        case 1:
                            enemies.add(new Enemies());
                            break;
                        case 2:
                            enemies.add(new Enemies());
                            enemies.add(new Enemies());
                            break;
                        case 3:
                            enemies.add(new Enemies());
                            enemies.add(new Enemies());
                            enemies.add(new Enemies());
                            break;
                        case 4:
                            enemies.add(new Enemies());
                            enemies.add(new Enemies());
                            enemies.add(new Enemies());
                            enemies.add(new Enemies());
                            break;
                        case 5:
                            enemies.add(new Enemies());
                            enemies.add(new Enemies());
                            enemies.add(new Enemies());
                            enemies.add(new Enemies());
                            enemies.add(new Enemies());
                            break;
                        case 6:
                            enemies.add(new Enemies());
                            enemies.add(new Enemies());
                            enemies.add(new Enemies());
                            enemies.add(new Enemies());
                            enemies.add(new Enemies());
                            enemies.add(new Enemies());
                            break;

                    }
                    showingMenu = false;
                    break;
                case "escape":
                    showingMenu = false;
                    break;
            }
            INPUT = "";
            TimeUnit.MILLISECONDS.sleep(1000 / INPUT_RATE);
        } while (showingMenu);
        return menuCompleted;
    }

    /**
     * Men?? de la pantalla final.
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
                    }
                    INPUT = "";
                    break;
                case "down":
                    if (menuEnding < 2) {
                        menuEnding++;
                        if (Player.HP == 0) {
                            Screens.youLoseScreen(menuEnding);
                        } else {
                            Screens.youWinScreen(menuEnding);
                        }
                    }
                    INPUT = "";
                    break;
                case "enter":
                    Tools.clearConsole();
                    endingGame = false;
                    if (menuEnding == 1) {
                        Play.playingGame();
                    } else {
                        Play.isPlayingGame = false;
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
                + Tools.print(colorUI, "", "          / ") + Tools.print(colorText, "", "??? ??????   ?????????????????????????????????????????????") + Tools.print(colorUI, "", " \\        \n")
                + Tools.print(colorUI, "", "         /  ") + Tools.print(colorText, "", "???????????? ??? ???????????????????????? ??????  ??????") + Tools.print(colorUI, "", "  \\       \n")
                + Tools.print(colorUI, "", "        /   ") + Tools.print(colorText, "", "??? ??????   ????????????  ???????????????????????????") + Tools.print(colorUI, "", "   \\      \n")
                + Tools.print(colorUI, "", "       /   ") + Tools.print(colorText, "", "????????????????????? ???  ??? ???????????????  ????????????") + Tools.print(colorUI, "", "   \\     \n")
                + Tools.print(colorUI, "", "      /    ") + Tools.print(colorText, "", "??????????????? ?????????  ????????????????????????????????????") + Tools.print(colorUI, "", "     \\    \n")
                + Tools.print(colorUI, "", "     /     ") + Tools.print(colorText, "", "???????????????????????????  ???????????? ??? ?????? ?????????") + Tools.print(colorUI, "", "     \\   \n")
                + Tools.print(colorUI, "", "    /  ") + Tools.print(colorText, "", "????????????????????? ??????    ??? ?????? ???????????????????????????????????????") + Tools.print(colorUI, "", "  \\  \n")
                + Tools.print(colorUI, "", "   /   ") + Tools.print(colorText, "", "???????????? ?????? ??????    ???????????? ???????????? ??? ?????? ?????????") + Tools.print(colorUI, "", "   \\ \n")
                + Tools.print(colorUI, "", "  /    ") + Tools.print(colorText, "", "????????????????????????????????????  ??? ????????????????????? ??? ??????????????????") + Tools.print(colorUI, "", "    \\\n")
                + Tools.print(colorUI, "", "  \\        ________________________        /\n")
                + Tools.print(colorUI, "", "   \\______/                        \\______/\n"));

        System.out.print("\n\n");

        System.out.println("Game developed in JAVA by:");
        System.out.println("- Maria Garriga");
        System.out.println("- Mario Molina");
        System.out.println("- Pau Pajaro");
        System.out.println("- Victor Guill??n");

        System.out.println();
    }

    /**
     * Pantalla con las instruciones del juego.
     *
     * @throws InterruptedException
     */
    public static void tutorialScreen() throws InterruptedException {
        boolean showingMenu = true;
        INPUT = "";
        Tools.clearConsole();
        printTutorialScreen();
        do {
            switch (INPUT) {
                case "enter":
                    showingMenu = false;
                    break;
            }
            INPUT = "";
            TimeUnit.MILLISECONDS.sleep(1000 / INPUT_RATE);
        } while (showingMenu);
    }

    /**
     * Pantalla con los scores del juego.
     *
     * @throws InterruptedException
     */
    public static void scoresScreen(ArrayList<GameScores> scores) throws InterruptedException {
        boolean showingMenu = true;
        INPUT = "";
        Tools.clearConsole();
        printScoresScreen(scores);
        do {
            switch (INPUT) {
                case "enter":
                    showingMenu = false;
                    break;
            }
            INPUT = "";
            TimeUnit.MILLISECONDS.sleep(1000 / INPUT_RATE);
        } while (showingMenu);
    }

    public static boolean gameConfigurationScreens() throws InterruptedException {
        int page = 1;
        boolean menuRunning = true,
                gameConfigCompleted = false;

        do {
            switch (page) {
                case 1:
                    if (boardSizeScreen()) {
                        page++;
                    } else {
                        menuRunning = false;
                        gameConfigCompleted = false;
                    }
                    break;
                case 2:
                    if (characterSelectorScreen()) {
                        page++;
                    } else {
                        page--;
                    }
                    break;
                case 3:
                    if (gameDifficultyScreen()) {
                        menuRunning = false;
                        gameConfigCompleted = true;
                    } else {
                        page--;
                    }
                    break;
            }
        } while (menuRunning);
        return gameConfigCompleted;
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
                + Tools.print(colorUI, "", "          / ") + Tools.print(colorText, "", "??? ??????   ?????????????????????????????????????????????") + Tools.print(colorUI, "", " \\        \n")
                + Tools.print(colorUI, "", "         /  ") + Tools.print(colorText, "", "???????????? ??? ???????????????????????? ??????  ??????") + Tools.print(colorUI, "", "  \\       \n")
                + Tools.print(colorUI, "", "        /   ") + Tools.print(colorText, "", "??? ??????   ????????????  ???????????????????????????") + Tools.print(colorUI, "", "   \\      \n")
                + Tools.print(colorUI, "", "       /   ") + Tools.print(colorText, "", "????????????????????? ???  ??? ???????????????  ????????????") + Tools.print(colorUI, "", "   \\     \n")
                + Tools.print(colorUI, "", "      /    ") + Tools.print(colorText, "", "??????????????? ?????????  ????????????????????????????????????") + Tools.print(colorUI, "", "     \\    \n")
                + Tools.print(colorUI, "", "     /     ") + Tools.print(colorText, "", "???????????????????????????  ???????????? ??? ?????? ?????????") + Tools.print(colorUI, "", "     \\   \n")
                + Tools.print(colorUI, "", "    /  ") + Tools.print(colorText, "", "????????????????????? ??????    ??? ?????? ???????????????????????????????????????") + Tools.print(colorUI, "", "  \\  \n")
                + Tools.print(colorUI, "", "   /   ") + Tools.print(colorText, "", "???????????? ?????? ??????    ???????????? ???????????? ??? ?????? ?????????") + Tools.print(colorUI, "", "   \\ \n")
                + Tools.print(colorUI, "", "  /    ") + Tools.print(colorText, "", "????????????????????????????????????  ??? ????????????????????? ??? ??????????????????") + Tools.print(colorUI, "", "    \\\n")
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
                    System.out.print(Tools.print(colorUI, "", "??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????"));
                    break;
                case 2: //???  ?????????????????????????????? ???                          ??????
                    System.out.print(Tools.print(colorUI, "", "???"));
                    System.out.print(Tools.print(colorText, "", "  ?????????????????????????????? ???                          "));
                    System.out.print(Tools.print(colorUI, "", "??????"));
                    break;
                case 3: //???  ??????????????? ???????????? ???                           ??????
                    System.out.print(Tools.print(colorUI, "", "???"));
                    System.out.print(Tools.print(colorText, "", "  ??????????????? ???????????? ???                           "));
                    System.out.print(Tools.print(colorUI, "", "??????"));
                    break;
                case 4: //???  ??? ??????????????????????????????                            ??????
                    System.out.print(Tools.print(colorUI, "", "???"));
                    System.out.print(Tools.print(colorText, "", "  ??? ??????????????????????????????                            "));
                    System.out.print(Tools.print(colorUI, "", "??????"));
                    break;
                case 5: //???                 ??????INFO????????????????????????????????????????????????    ???
                    System.out.print(Tools.print(colorUI, "", "???                 ??????"));
                    System.out.print(Tools.print(colorText, "", "INFO"));
                    System.out.print(Tools.print(colorUI, "", "????????????????????????????????????????????????    ???"));
                    break;
                case 6: //???    > PLAY       ??? Survive agaist the ???    ???
                    if (menuOption == 1) {
                        System.out.print(Tools.print(colorUI, "", "???    ??? "));
                        System.out.print(Tools.print("white", "red", "PLAY"));
                        System.out.print(Tools.print(colorUI, "", "       ??? "));
                        System.out.print(Tools.print(colorText, "", "Survive agaist the"));
                        System.out.print(Tools.print(colorUI, "", " ???    ???"));
                    } else if (menuOption == 2) {
                        System.out.print(Tools.print(colorUI, "", "???     PLAY        ??? "));
                        System.out.print(Tools.print(colorText, "", "Learn the basics"));
                        System.out.print(Tools.print(colorUI, "", "   ???    ???"));
                    } else if (menuOption == 3) {
                        System.out.print(Tools.print(colorUI, "", "???     PLAY        ??? "));
                        System.out.print(Tools.print(colorText, "", "Check the TOP 10"));
                        System.out.print(Tools.print(colorUI, "", "   ???    ???"));
                    } else if (menuOption == 4) {
                        System.out.print(Tools.print(colorUI, "", "???     PLAY        ??? "));
                        System.out.print(Tools.print(colorText, "", "Bye bye...      "));
                        System.out.print(Tools.print(colorUI, "", "   ???    ???"));
                    }
                    break;
                case 7: //???     TUTORIAL    ??? creatures of this  ???    ???
                    if (menuOption == 2) {
                        System.out.print(Tools.print(colorUI, "", "???    ??? "));
                        System.out.print(Tools.print("white", "red", "TUTORIAL"));
                        System.out.print(Tools.print(colorUI, "", "   ??? "));
                        System.out.print(Tools.print(colorText, "", "to play & archive"));
                        System.out.print(Tools.print(colorUI, "", "  ???    ???"));
                    } else if (menuOption == 1) {
                        System.out.print(Tools.print(colorUI, "", "???     TUTORIAL    ??? "));
                        System.out.print(Tools.print(colorText, "", "creatures of this"));
                        System.out.print(Tools.print(colorUI, "", "  ???    ???"));
                    } else if (menuOption == 3) {
                        System.out.print(Tools.print(colorUI, "", "???     TUTORIAL    ??? "));
                        System.out.print(Tools.print(colorText, "", "scores archived"));
                        System.out.print(Tools.print(colorUI, "", "    ???    ???"));
                    } else if (menuOption == 4) {
                        System.out.print(Tools.print(colorUI, "", "???     TUTORIAL    ??? "));
                        System.out.print(Tools.print(colorText, "", "I guess you aren't "));
                        System.out.print(Tools.print(colorUI, "", "???    ???"));
                    }
                    break;
                case 8: //???     SCORES      ??? good scores.       ???    ???
                    if (menuOption == 3) {
                        System.out.print(Tools.print(colorUI, "", "???    ??? "));
                        System.out.print(Tools.print("white", "red", "SCORES"));
                        System.out.print(Tools.print(colorUI, "", "     ??? "));
                        System.out.print(Tools.print(colorText, "", "in this game."));
                        System.out.print(Tools.print(colorUI, "", "      ???    ???"));
                    } else if (menuOption == 1) {
                        System.out.print(Tools.print(colorUI, "", "???     SCORES      ??? "));
                        System.out.print(Tools.print(colorText, "", "world. If you can."));
                        System.out.print(Tools.print(colorUI, "", " ???    ???"));
                    } else if (menuOption == 2) {
                        System.out.print(Tools.print(colorUI, "", "???     SCORES      ??? "));
                        System.out.print(Tools.print(colorText, "", "good scores."));
                        System.out.print(Tools.print(colorUI, "", "       ???    ???"));
                    } else if (menuOption == 4) {
                        System.out.print(Tools.print(colorUI, "", "???     SCORES      ??? "));
                        System.out.print(Tools.print(colorText, "", "strong enought."));
                        System.out.print(Tools.print(colorUI, "", "    ???    ???"));
                    }
                    break;
                case 9:
                    System.out.print(Tools.print(colorUI, "", "???                 ??????????????????????????????????????????????????????????????????    ???"));
                    break;
                case 10:
                    if (menuOption == 4) {
                        System.out.print(Tools.print(colorUI, "", "??? ??? "));
                        System.out.print(Tools.print("white", "red", "EXIT"));
                        System.out.print(Tools.print(colorUI, "", "                                    ???"));
                    } else {
                        System.out.print(Tools.print(colorUI, "", "??? EXIT                                      ???"));
                    }
                    break;
                default:
                    //Como SWITCH no acepta variables en el case, usamos un if
                    //en el default para ver si estamos en la ultima iteracion
                    if (i == h) {
                        System.out.print(Tools.print(colorUI, "", "???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????"));
                    } else {
                        System.out.print(Tools.print(colorUI, "", "???                                           ???"));
                    }
            }
            System.out.println("");
        }
        //String convert (sc) (cambia los caracteres problematicos dependiendo el entorno donde se ejecuta)
        //Si dividimos la linea en varios System.out, se percive un retraso al aparece la linea en consola muy molesto.
        System.out.println(Tools.print(colorUI, "", "   [")
                + Tools.print(colorText, "", "??????")
                + Tools.print(colorUI, "", "] - Select")
                + Tools.print(colorUI, "", "\t  [")
                + Tools.print(colorText, "", "ENTER")
                + Tools.print(colorUI, "", "] - Accept"));
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
                    System.out.print(Tools.print(colorUI, "", "??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????"));
                    break;
                case 2:
                    System.out.print(Tools.print(colorUI, "", "???                                        ??????"));
                    break;
                case 3:
                    System.out.print(Tools.print(colorUI, "", "???                                         ??????"));
                    break;
                case 4: //???            ???  ????????????????????????????????????????????????            ???
                    System.out.print(Tools.print(colorUI, "", "???"));
                    switch (counter) {
                        case 0:
                            System.out.print(Tools.print(colorUI, "", "            ???  ????????????????????????????????????????????????           "));
                            System.out.print(Tools.print(colorUI, "", "??????"));
                            break;
                        case 1:
                            System.out.print(Tools.print(colorText, "", "            ???  "));
                            System.out.print(Tools.print(colorUI, "", "????????????????????????????????????????????????           "));
                            System.out.print(Tools.print(colorUI, "", "??????"));
                            break;
                        case 2:
                            System.out.print(Tools.print(colorText, "", "            ???  ?????????"));
                            System.out.print(Tools.print(colorUI, "", "???????????????????????????????????????           "));
                            System.out.print(Tools.print(colorUI, "", "??????"));
                            break;
                        case 3:
                            System.out.print(Tools.print(colorText, "", "            ???  ??????????????????"));
                            System.out.print(Tools.print(colorUI, "", "??????????????????????????????           "));
                            System.out.print(Tools.print(colorUI, "", "??????"));
                            break;
                        case 4:
                            System.out.print(Tools.print(colorText, "", "            ???  ???????????????????????????"));
                            System.out.print(Tools.print(colorUI, "", "?????????????????????           "));
                            System.out.print(Tools.print(colorUI, "", "??????"));
                            break;
                        case 5:
                            System.out.print(Tools.print(colorText, "", "            ???  ??????????????????????????????"));
                            System.out.print(Tools.print(colorUI, "", "??????????????????           "));
                            System.out.print(Tools.print(colorUI, "", "??????"));
                            break;
                        case 6:
                            System.out.print(Tools.print(colorText, "", "            ???  ???????????????????????????????????????"));
                            System.out.print(Tools.print(colorUI, "", "?????????           "));
                            System.out.print(Tools.print(colorUI, "", "??????"));
                            break;
                        case 7:
                            System.out.print(Tools.print(colorText, "", "            ???  ????????????????????????????????????????????????           "));
                            System.out.print(Tools.print(colorUI, "", "??????"));
                            break;
                        case 8:
                            System.out.print(Tools.print(colorText, "", "               ????????????????????????????????????               "));
                            System.out.print(Tools.print(colorUI, "", "??????"));
                            break;
                    }
                    break;
                case 5: //???            ???  ??? ???????????? ????????????????????? ???            ???
                    System.out.print(Tools.print(colorUI, "", "???"));
                    switch (counter) {
                        case 0:
                            System.out.print(Tools.print(colorUI, "", "            ???  ??? ???????????? ????????????????????? ???            "));
                            System.out.print(Tools.print(colorUI, "", "???"));
                            break;
                        case 1:
                            System.out.print(Tools.print(colorText, "", "            ???  "));
                            System.out.print(Tools.print(colorUI, "", "??? ???????????? ????????????????????? ???            "));
                            System.out.print(Tools.print(colorUI, "", "???"));
                            break;
                        case 2:
                            System.out.print(Tools.print(colorText, "", "            ???  ??? ???"));
                            System.out.print(Tools.print(colorUI, "", "????????? ????????????????????? ???            "));
                            System.out.print(Tools.print(colorUI, "", "???"));
                            break;
                        case 3:
                            System.out.print(Tools.print(colorText, "", "            ???  ??? ????????????"));
                            System.out.print(Tools.print(colorUI, "", " ????????????????????? ???            "));
                            System.out.print(Tools.print(colorUI, "", "???"));
                            break;
                        case 4:
                            System.out.print(Tools.print(colorText, "", "            ???  ??? ???????????? ??????"));
                            System.out.print(Tools.print(colorUI, "", "??????????????? ???            "));
                            System.out.print(Tools.print(colorUI, "", "???"));
                            break;
                        case 5:
                            System.out.print(Tools.print(colorText, "", "            ???  ??? ???????????? ?????????"));
                            System.out.print(Tools.print(colorUI, "", "???????????? ???            "));
                            System.out.print(Tools.print(colorUI, "", "???"));
                            break;
                        case 6:
                            System.out.print(Tools.print(colorText, "", "            ???  ??? ???????????? ??????????????????"));
                            System.out.print(Tools.print(colorUI, "", "??? ???            "));
                            System.out.print(Tools.print(colorUI, "", "???"));
                            break;
                        case 7:
                            System.out.print(Tools.print(colorText, "", "            ???  ??? ???????????? ????????????????????? ???            "));
                            System.out.print(Tools.print(colorUI, "", "???"));
                            break;
                        case 8:
                            System.out.print(Tools.print(colorText, "", "                ????????? ??????????????????                 "));
                            System.out.print(Tools.print(colorUI, "", "???"));
                            break;
                    }
                    break;
                case 6: //???            ????????????????????? ?????????????????????????????????            ???
                    System.out.print(Tools.print(colorUI, "", "???"));
                    switch (counter) {
                        case 0:
                            System.out.print(Tools.print(colorUI, "", "            ????????????????????? ?????????????????????????????????            "));
                            System.out.print(Tools.print(colorUI, "", "???"));
                            break;
                        case 1:
                            System.out.print(Tools.print(colorText, "", "            ?????????"));
                            System.out.print(Tools.print(colorUI, "", "???????????? ?????????????????????????????????            "));
                            System.out.print(Tools.print(colorUI, "", "???"));
                            break;
                        case 2:
                            System.out.print(Tools.print(colorText, "", "            ??????????????????"));
                            System.out.print(Tools.print(colorUI, "", "??? ?????????????????????????????????            "));
                            System.out.print(Tools.print(colorUI, "", "???"));
                            break;
                        case 3:
                            System.out.print(Tools.print(colorText, "", "            ????????????????????? ???"));
                            System.out.print(Tools.print(colorUI, "", "??????????????????????????????            "));
                            System.out.print(Tools.print(colorUI, "", "???"));
                            break;
                        case 4:
                            System.out.print(Tools.print(colorText, "", "            ????????????????????? ????????????"));
                            System.out.print(Tools.print(colorUI, "", "?????????????????????            "));
                            System.out.print(Tools.print(colorUI, "", "???"));
                            break;
                        case 5:
                            System.out.print(Tools.print(colorText, "", "            ????????????????????? ???????????????"));
                            System.out.print(Tools.print(colorUI, "", "??????????????????            "));
                            System.out.print(Tools.print(colorUI, "", "???"));
                            break;
                        case 6:
                            System.out.print(Tools.print(colorText, "", "            ????????????????????? ????????????????????????"));
                            System.out.print(Tools.print(colorUI, "", "?????????            "));
                            System.out.print(Tools.print(colorUI, "", "???"));
                            break;
                        case 7:
                            System.out.print(Tools.print(colorText, "", "            ????????????????????? ?????????????????????????????????            "));
                            System.out.print(Tools.print(colorUI, "", "???"));
                            break;
                        case 8:
                            System.out.print(Tools.print(colorText, "", "               ????????????????????????????????????                "));
                            System.out.print(Tools.print(colorUI, "", "???"));
                            break;
                    }
                    break;
                case 8: //???        [?????????????????????????????????????????????????????????______]        ???
                    System.out.print(Tools.print(colorUI, "", "???"));
                    switch (counter) {
                        case 0:
                            System.out.print(Tools.print(colorUI, "", "      /  "));
                            System.out.print(Tools.print(colorText, "", "???"));
                            System.out.print(Tools.print(colorUI, "", "????????????????????????????????????????????????????????????????????????  /      "));
                            break;
                        case 1:
                            System.out.print(Tools.print(colorUI, "", "      ???  "));
                            System.out.print(Tools.print(colorText, "", "????????????"));
                            System.out.print(Tools.print(colorUI, "", "???????????????????????????????????????????????????????????????  ???      "));
                            break;
                        case 2:
                            System.out.print(Tools.print(colorUI, "", "      \\  "));
                            System.out.print(Tools.print(colorText, "", "?????????????????????"));
                            System.out.print(Tools.print(colorUI, "", "??????????????????????????????????????????????????????  \\      "));
                            break;
                        case 3:
                            System.out.print(Tools.print(colorUI, "", "      |  "));
                            System.out.print(Tools.print(colorText, "", "??????????????????????????????"));
                            System.out.print(Tools.print(colorUI, "", "?????????????????????????????????????????????  |      "));
                            break;
                        case 4:
                            System.out.print(Tools.print(colorUI, "", "      ???  "));
                            System.out.print(Tools.print(colorText, "", "??????????????????????????????????????????"));
                            System.out.print(Tools.print(colorUI, "", "?????????????????????????????????  ???      "));
                            break;
                        case 5:
                            System.out.print(Tools.print(colorUI, "", "      \\  "));
                            System.out.print(Tools.print(colorText, "", "???????????????????????????????????????????????????"));
                            System.out.print(Tools.print(colorUI, "", "????????????????????????  \\      "));
                            break;
                        case 6:
                            System.out.print(Tools.print(colorUI, "", "      |  "));
                            System.out.print(Tools.print(colorText, "", "????????????????????????????????????????????????????????????"));
                            System.out.print(Tools.print(colorUI, "", "???????????????  |      "));
                            break;
                        case 7:
                            System.out.print(Tools.print(colorUI, "", "      /  "));
                            System.out.print(Tools.print(colorText, "", "?????????????????????????????????????????????????????????????????????"));
                            System.out.print(Tools.print(colorUI, "", "??????  /      "));
                            break;
                        case 8:
                            System.out.print(Tools.print(colorText, "", "      ???  ???????????????????????????????????????????????????????????????????????????  ???      "));
                            break;
                    }
                    System.out.print(Tools.print(colorUI, "", "???"));
                    break;
                default:
                    //Como SWITCH no acepta variables en el case, usamos un if
                    //en el default para ver si estamos en la ultima iteracion
                    if (i == h) {
                        System.out.print(Tools.print(colorUI, "", "???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????"));
                    } else {
                        System.out.print(Tools.print(colorUI, "", "???                                           ???"));
                    }
            }
            System.out.println("");
        }
    }

    /**
     * Imprime menu eleccion tama??o tablero.
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

        System.out.println(Tools.print(colorText, "", "?????? ????????????????????????????????????  ??????????????????????????????"));
        System.out.println(Tools.print(colorText, "", "???????????? ????????????????????? ??????  ???????????????????????????    ") + Tools.print(colorUI, "", "Min: ") + Tools.print(colorText, "", String.valueOf(MIN_BOARD_SIZE)));
        System.out.println(Tools.print(colorText, "", "????????????????????? ?????????????????????  ??????????????????????????????   ") + Tools.print(colorUI, "", "Max: ") + Tools.print(colorText, "", String.valueOf(MAX_BOARD_SIZE)));

        height += 2;

        for (int i = 1; i <= height; i++) {

            switch (i) {
                case 1:
                    Tools.printRow('???', '???', voidSize + 2, '???', colorUI);
                    break;
                default:
                    //Como SWITCH no acepta variables en el case, usamos un if
                    //en el default para ver si estamos en la ultima iteracion
                    if (i == height) {
                        System.out.print(Tools.print(colorUI, "", "???"));
                        for (int j = 0; j < voidSize / 2; j++) {
                            if (j == (voidSize / 2) - 1) {
                                System.out.print(Tools.print(colorUI, "", "???"));
                            } else {
                                System.out.print(Tools.print(colorUI, "", "???"));
                            }
                        }
                        System.out.print(Tools.print(colorText, "", String.valueOf(width)));
                        System.out.print(Tools.print(colorUI, "", "???"));
                        for (int j = 0; j < (voidSize / 2) - 3; j++) {
                            System.out.print(Tools.print(colorUI, "", "???"));
                        }
                        if (width % 2 == 0) {
                            System.out.print(Tools.print(colorUI, "", "???"));
                        } else {
                            System.out.print(Tools.print(colorUI, "", "??????"));
                        }
                    } else if (i == (height / 2) - 1) {
                        System.out.print(Tools.print(colorUI, "", "???"));
                        for (int j = 1; j <= width; j++) {
                            System.out.print(Tools.print(Board.bgColor, "", Board.voidSquare));
                        }
                        System.out.print(Tools.print(colorUI, "", "???"));
                    } else if (i == height / 2) {
                        System.out.print(Tools.print(colorUI, "", "???"));
                        for (int j = 1; j <= width; j++) {
                            System.out.print(Tools.print(Board.bgColor, "", Board.voidSquare));
                        }
                        System.out.print(Tools.print(colorText, "", String.valueOf(height - 2)));
                    } else if (i == (height / 2) + 1) {
                        System.out.print(Tools.print(colorUI, "", "???"));
                        for (int j = 1; j <= width; j++) {
                            System.out.print(Tools.print(Board.bgColor, "", Board.voidSquare));
                        }
                        System.out.print(Tools.print(colorUI, "", "???"));
                    } else {
                        System.out.print(Tools.print(colorUI, "", "???"));
                        for (int j = 1; j <= width; j++) {
                            System.out.print(Tools.print(Board.bgColor, "", Board.voidSquare));
                        }
                        System.out.print(Tools.print(colorUI, "", "???"));
                    }
            }
            System.out.println("");
        }
        //controls menu
        System.out.println(Tools.print(colorUI, "", "   [") + Tools.print(colorText, "", "ESC") + Tools.print(colorUI, "", "] - EXIT\t")+Tools.print(colorUI, "", "   [") + Tools.print(colorText, "", "????????????") + Tools.print(colorUI, "", "] - Board size"));
    }

    /**
     * Imprime menu eleccion personaje.
     *
     * @param optionSelected Indica que personaje esta selecionado.
     * @param secondSelection Indica si estamos elijiendo personaje o en el
     * ENTER.
     */
    public static void characterSelectorScreen(int optionSelected) {
        Tools.clearConsole();
        System.out.print(Tools.print(colorUI, "", "??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????\n"));

        System.out.print(Tools.print(colorUI, "", "???  "));
        System.out.print(Tools.print(colorText, "", "???????????? ???????????????????????????????????????????????????????????????????????????"));
        System.out.print(Tools.print(colorUI, "", "        ??????\n"));

        System.out.print(Tools.print(colorUI, "", "???  "));
        System.out.print(Tools.print(colorText, "", "???  ???????????????????????????????????????   ??? ?????? ??????????????????"));
        System.out.print(Tools.print(colorUI, "", "         ??????\n"));

        System.out.print(Tools.print(colorUI, "", "???  "));
        System.out.print(Tools.print(colorText, "", "???????????? ?????? ??????????????? ???????????? ??? ???????????????????????????"));
        System.out.print(Tools.print(colorUI, "", "          ??????\n"));

        System.out.println(Tools.print(colorUI, "", "???                                           ???"));

        switch (optionSelected) {
            case 0:
                System.out.print(Tools.print(colorUI, "", "???"));
                System.out.print(Tools.print("red", "", "     ?????????????????????                               "));
                System.out.print(Tools.print(colorUI, "", "???\n"));

                System.out.print(Tools.print(colorUI, "", "???"));
                System.out.print(Tools.print("red", "", "     ???  "));
                System.out.print(Tools.print(colorText, "", "??"));
                System.out.print(Tools.print("red", "", "  ???"));
                System.out.print(Tools.print(colorUI, "", "        ??           ??          "));
                System.out.print(Tools.print(colorUI, "", "???\n"));

                System.out.print(Tools.print(colorUI, "", "???"));
                System.out.print(Tools.print("red", "", "     ?????????????????????                               "));
                System.out.print(Tools.print(colorUI, "", "???\n"));

                System.out.print(Tools.print(colorUI, "", "???     "));
                System.out.print(Tools.print(colorText, "", "WARRIOR"));
                System.out.print(Tools.print(colorUI, "", "     MAGICIAN     PRIEST       ???\n"));

                System.out.println(Tools.print(colorUI, "", "???        " + Tools.print(colorText, "", "???") + Tools.print(colorUI, "", "                                  ???")));
                break;
            case 1:
                System.out.print(Tools.print(colorUI, "", "???"));
                System.out.print(Tools.print("red", "", "                 ?????????????????????                   "));
                System.out.print(Tools.print(colorUI, "", "???\n"));

                System.out.print(Tools.print(colorUI, "", "???"));
                System.out.print(Tools.print(colorUI, "", "        ??        "));
                System.out.print(Tools.print("red", "", "???  "));
                System.out.print(Tools.print(colorText, "", "??"));
                System.out.print(Tools.print("red", "", "  ???"));
                System.out.print(Tools.print(colorUI, "", "        ??          "));
                System.out.print(Tools.print(colorUI, "", "???\n"));

                System.out.print(Tools.print(colorUI, "", "???"));
                System.out.print(Tools.print("red", "", "                 ?????????????????????                   "));
                System.out.print(Tools.print(colorUI, "", "???\n"));

                System.out.print(Tools.print(colorUI, "", "???     WARRIOR     "));
                System.out.print(Tools.print(colorText, "", "MAGICIAN"));
                System.out.print(Tools.print(colorUI, "", "     PRIEST       ???\n"));

                System.out.println(Tools.print(colorUI, "", "???                    " + Tools.print(colorText, "", "???") + Tools.print(colorUI, "", "                      ???")));
                break;
            case 2:
                System.out.print(Tools.print(colorUI, "", "???"));
                System.out.print(Tools.print("red", "", "                             ?????????????????????       "));
                System.out.print(Tools.print(colorUI, "", "???\n"));

                System.out.print(Tools.print(colorUI, "", "???"));
                System.out.print(Tools.print(colorUI, "", "        ??           ??        "));
                System.out.print(Tools.print("red", "", "???  "));
                System.out.print(Tools.print(colorText, "", "??"));
                System.out.print(Tools.print("red", "", "  ???"));
                System.out.print(Tools.print(colorUI, "", "       ???\n"));

                System.out.print(Tools.print(colorUI, "", "???"));
                System.out.print(Tools.print("red", "", "                             ?????????????????????       "));
                System.out.print(Tools.print(colorUI, "", "???\n"));

                System.out.print(Tools.print(colorUI, "", "???     WARRIOR     MAGICIAN     "));
                System.out.print(Tools.print(colorText, "", "PRIEST"));
                System.out.print(Tools.print(colorUI, "", "       ???\n"));

                System.out.println(Tools.print(colorUI, "", "???                                " + Tools.print(colorText, "", "???") + Tools.print(colorUI, "", "          ???")));
                break;
        }
        System.out.println(Tools.print(colorUI, "", "???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????"));
        //controls menu
        System.out.println(Tools.print(colorUI, "", "   [") + Tools.print(colorText, "", "ESC") + Tools.print(colorUI, "", "] - EXIT\t")+Tools.print(colorUI, "", "   [") + Tools.print(colorText, "", "??????") + Tools.print(colorUI, "", "] - Selection"));
    }

    /**
     * Imprime menu eleccion dificultad.
     *
     * @param menuOption Indica la opcion selecionada.
     */
    public static void printDifficultyScreen(int optionSelected) {
        Tools.clearConsole();
        System.out.print(Tools.print(colorUI, "", "??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????\n"));

        System.out.print(Tools.print(colorUI, "", "???  "));
        System.out.print(Tools.print(colorText, "", "????????????????????????????????????????????? ?????? ???????????? ???"));
        System.out.print(Tools.print(colorUI, "", "             ??????\n"));

        System.out.print(Tools.print(colorUI, "", "???  "));
        System.out.print(Tools.print(colorText, "", " ??????????????? ?????? ??????  ??? ??????  ??? ?????????"));
        System.out.print(Tools.print(colorUI, "", "              ??????\n"));

        System.out.print(Tools.print(colorUI, "", "???  "));
        System.out.print(Tools.print(colorText, "", "???????????????  ???  ?????????????????????????????????  ??? "));
        System.out.print(Tools.print(colorUI, "", "               ??????\n"));

        System.out.println(Tools.print(colorUI, "", "???                                           ???"));
        System.out.println(Tools.print(colorUI, "", "???                                           ???"));
        System.out.print(Tools.print(colorUI, "", "???     ["));

        switch (optionSelected) {
            case 1:
                System.out.print(Tools.print("green", "", "????????????????????????"));
                System.out.print(Tools.print(colorUI, "", "?????????????????????????????????????????????????????????????????????"));
                break;
            case 2:
                System.out.print(Tools.print("yellow", "", "????????????????????????????????????????????????"));
                System.out.print(Tools.print(colorUI, "", "?????????????????????????????????????????????"));
                break;
            case 3:
                System.out.print(Tools.print("red", "", "?????????????????????????????????????????????????????????????????????????????????????????????"));
                break;
        }

        System.out.print(Tools.print(colorUI, "", "]     ???\n"));
        System.out.println(Tools.print(colorUI, "", "???                                           ???"));

        switch (optionSelected) {
            case 1:
                System.out.print(Tools.print(colorUI, "", "???      "));
                System.out.print(Tools.print("green", "", "EASY"));
                System.out.print(Tools.print(colorUI, "", "       MEDIUM        HARD        ???\n"));

                System.out.println(Tools.print(colorUI, "", "???       " + Tools.print("green", "", "???") + Tools.print(colorUI, "", "                                   ???")));
                break;
            case 2:
                System.out.print(Tools.print(colorUI, "", "???      EASY       "));
                System.out.print(Tools.print("yellow", "", "MEDIUM"));
                System.out.print(Tools.print(colorUI, "", "        HARD        ???\n"));

                System.out.println(Tools.print(colorUI, "", "???                    " + Tools.print("yellow", "", "???") + Tools.print(colorUI, "", "                      ???")));
                break;
            case 3:
                System.out.print(Tools.print(colorUI, "", "???      EASY       MEDIUM        "));
                System.out.print(Tools.print("red", "", "HARD"));
                System.out.print(Tools.print(colorUI, "", "        ???\n"));

                System.out.println(Tools.print(colorUI, "", "???                                " + Tools.print("red", "", "???") + Tools.print(colorUI, "", "          ???")));
                break;
        }
        System.out.println(Tools.print(colorUI, "", "???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????"));
        //controls menu
        System.out.println(Tools.print(colorUI, "", "   [") + Tools.print(colorText, "", "ESC") + Tools.print(colorUI, "", "] - EXIT\t")+Tools.print(colorUI, "", "   [") + Tools.print(colorText, "", "??????") + Tools.print(colorUI, "", "] - Selection"));
    }

    /**
     * Imprime la pantalla final."YOU WIN"
     *
     * @param menuEnding Indica la posici??n selecionada, si PLAY AGAIN o EXIT.
     */
    public static void youWinScreen(int optionSelected) {
        Tools.clearConsole();

        System.out.println(Tools.print(colorUI, "", "??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????"));
        System.out.println(Tools.print(colorUI, "", "???                                        ??????"));
        System.out.println(Tools.print(colorUI, "", "???                                         ??????"));

        System.out.print(Tools.print(colorUI, "", "???       "));
        System.out.print(Tools.print(colorText, "", "????????? ????????? ??? ???     ??? ??? ??? ??? ????????? ???"));
        System.out.print(Tools.print(colorUI, "", "      ??????\n"));

        System.out.print(Tools.print(colorUI, "", "???       "));
        System.out.print(Tools.print(colorText, "", " ???  ??? ??? ??? ???     ??? ??? ??? ??? ??? ??? ???"));
        System.out.print(Tools.print(colorUI, "", "       ???\n"));

        System.out.print(Tools.print(colorUI, "", "???        "));
        System.out.print(Tools.print(colorText, "", "???  ????????? ?????????     ??????????????? ??? ??? ?????????"));
        System.out.print(Tools.print(colorUI, "", "       ???\n"));

        System.out.print(Tools.print(colorUI, "", "???                                           ???\n"));
        System.out.print(Tools.print(colorUI, "", "???                                           ???\n"));

        switch (optionSelected) {
            case 1:
                System.out.print(Tools.print(colorUI, "", "???           "));
                System.out.print(Tools.print("white", "red", " P L A Y   A G A I N "));
                System.out.print(Tools.print(colorUI, "", "           ???\n"));
                System.out.print(Tools.print(colorUI, "", "???                  E X I T                  ???"));
                break;
            case 2:
                System.out.print(Tools.print(colorUI, "", "???            P L A Y   A G A I N            ???\n"));
                System.out.print(Tools.print(colorUI, "", "???                 "));
                System.out.print(Tools.print("white", "red", " E X I T "));
                System.out.print(Tools.print(colorUI, "", "                 ???"));
                break;
        }

        System.out.println(Tools.print(colorUI, "", "\n???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????"));
    }

    /**
     * Imprime la pantalla final."YOU LOSE"
     */
    public static void youLoseScreen(int optionSelected) {
        Tools.clearConsole();

        System.out.println(Tools.print(colorUI, "", "??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????"));
        System.out.println(Tools.print(colorUI, "", "???                                        ??????"));
        System.out.println(Tools.print(colorUI, "", "???                                         ??????"));

        System.out.print(Tools.print(colorUI, "", "???      "));
        System.out.print(Tools.print(colorText, "", "????????? ????????? ??? ???     ???   ????????? ????????? ?????????"));
        System.out.print(Tools.print(colorUI, "", "     ??????\n"));

        System.out.print(Tools.print(colorUI, "", "???      "));
        System.out.print(Tools.print(colorText, "", " ???  ??? ??? ??? ???     ???   ??? ??? ????????? ?????? "));
        System.out.print(Tools.print(colorUI, "", "      ???\n"));

        System.out.print(Tools.print(colorUI, "", "???      "));
        System.out.print(Tools.print(colorText, "", " ???  ????????? ?????????     ????????? ????????? ????????? ?????????"));
        System.out.print(Tools.print(colorUI, "", "      ???\n"));

        System.out.print(Tools.print(colorUI, "", "???                                           ???\n"));
        System.out.print(Tools.print(colorUI, "", "???                                           ???\n"));

        switch (optionSelected) {
            case 1:
                System.out.print(Tools.print(colorUI, "", "???           "));
                System.out.print(Tools.print("white", "red", " P L A Y   A G A I N "));
                System.out.print(Tools.print(colorUI, "", "           ???\n"));

                System.out.print(Tools.print(colorUI, "", "???                  E X I T                  ???"));
                break;
            case 2:
                System.out.print(Tools.print(colorUI, "", "???            P L A Y   A G A I N            ???\n"));

                System.out.print(Tools.print(colorUI, "", "???                 "));
                System.out.print(Tools.print("white", "red", " E X I T "));
                System.out.print(Tools.print(colorUI, "", "                 ???"));
                break;
        }

        System.out.println(Tools.print(colorUI, "", "\n???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????"));
    }

    /**
     * Imprime el ring de la pantalla de pelea.
     *
     * @param character
     * @param enemy
     * @param playerTurn
     */
    public static void printRing(String character, String enemy, boolean playerTurn, int enemyPos) {
        Tools.clearConsole();

        if (playerTurn) {
            System.out.println(Tools.print(colorText, "", "?????? ??????????????????????????????  ?????????     " + Tools.print("green", "", "??? ??????????????? ????????????  ???????????? ?????????????????????")));
            System.out.println(Tools.print(colorText, "", "?????????????????? ???  ??? ???  ??????      " + Tools.print("green", "", "???????????? ?????? ????????????   ??? ??? ?????????????????????")));
            System.out.println(Tools.print(colorText, "", "???????????? ??? ???  ??? ??????????????????     " + Tools.print("green", "", " ??? ???????????????????????????   ??? ???????????????????????????")));
        } else {
            System.out.println(Tools.print(colorText, "", "?????? ??????????????????????????????  ?????????    " + Tools.print("red", "", "??????????????????????????????????????? ???  ???????????? ?????????????????????")));
            System.out.println(Tools.print(colorText, "", "?????????????????? ???  ??? ???  ??????     " + Tools.print("red", "", "?????? ??????????????? ??????????????????   ??? ??? ?????????????????????")));
            System.out.println(Tools.print(colorText, "", "???????????? ??? ???  ??? ??????????????????    " + Tools.print("red", "", "?????????????????????????????? ??? ???    ??? ???????????????????????????")));
        }
        int alto = 8;
        int ancho = 20;

        for (int i = 0; i < alto; i++) {
            if (i == 0) {
                for (int j = 0; j < (ancho * 3) - 8; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(Screens.colorUI, "", "???"));
                    } else if (j == (ancho * 3) - 22) {
                        System.out.print(Tools.print(Screens.colorUI, "", "???"));
                    } else if (j == (ancho * 3) - 9) {
                        System.out.print(Tools.print(Screens.colorUI, "", "???"));
                    } else if (j == 13) {
                        System.out.print(Tools.print(Screens.colorUI, "", "???"));
                    } else {
                        System.out.print(Tools.print(Screens.colorUI, "", "???"));
                    }
                }
            } else if (i == alto - 1) {
                for (int j = 0; j < (ancho * 3) - 8; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(Screens.colorUI, "", "???"));
                    } else if (j == (ancho * 3) - 22) {
                        System.out.print(Tools.print(Screens.colorUI, "", "???"));
                    } else if (j == 13) {
                        System.out.print(Tools.print(Screens.colorUI, "", "???"));
                    } else if (j == (ancho * 3) - 9) {
                        System.out.print(Tools.print(Screens.colorUI, "", "???"));
                    } else {
                        System.out.print(Tools.print(Screens.colorUI, "", "???"));
                    }
                }
            } else if (i == 2) {
                for (int j = 0; j < ancho; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(colorUI, "", "???"));
                    } else if (j > 4 && j < ancho - 5) {
                        if (j == ancho - 6) {
                            System.out.print(Tools.print(Screens.colorUI, "", "???"));
                        } else if (j == 5) {
                            System.out.print(Tools.print(Screens.colorUI, "", "???"));
                        } else if (j > 5 && j < 10) {
                            if (Player.HP > 90) {
                                if (j == 6) {
                                    System.out.print(Tools.print("green", "", (" ??????")));
                                } else if (j == 9) {
                                    System.out.print(Tools.print("green", "", ("?????? ")));
                                } else {
                                    System.out.print(Tools.print("green", "", "?????????"));
                                }
                            } else if (Player.HP > 80) {
                                if (j == 6) {
                                    System.out.print(Tools.print("green", "", (" ??????")));
                                } else if (j == 9) {
                                    System.out.print(Tools.print("green", "", ("???  ")));
                                } else {
                                    System.out.print(Tools.print("green", "", "?????????"));
                                }
                            } else if (Player.HP > 70) {
                                if (j == 6) {
                                    System.out.print(Tools.print("green", "", (" ??????")));
                                } else if (j == 9) {
                                    System.out.print(Tools.print("green", "", ("   ")));
                                } else {
                                    System.out.print(Tools.print("green", "", "?????????"));
                                }
                            } else if (Player.HP > 60) {
                                if (j == 6) {
                                    System.out.print(Tools.print("green", "", (" ??????")));
                                } else if (j == 8) {
                                    System.out.print(Tools.print("green", "", ("?????? ")));
                                } else if (j == 9) {
                                    System.out.print(Tools.print("green", "", ("   ")));
                                } else {
                                    System.out.print(Tools.print("green", "", "?????????"));
                                }
                            } else if (Player.HP > 50) {
                                if (j == 6) {
                                    System.out.print(Tools.print("green", "", (" ??????")));
                                } else if (j == 8) {
                                    System.out.print(Tools.print("green", "", ("???  ")));
                                } else if (j == 9) {
                                    System.out.print(Tools.print("green", "", ("   ")));
                                } else {
                                    System.out.print(Tools.print("green", "", "?????????"));
                                }
                            } else if (Player.HP > 40) {
                                if (j == 6) {
                                    System.out.print(Tools.print("yellow", "", (" ??????")));
                                } else if (j == 8) {
                                    System.out.print(Tools.print("yellow", "", ("   ")));
                                } else if (j == 9) {
                                    System.out.print(Tools.print("yellow", "", ("   ")));
                                } else {
                                    System.out.print(Tools.print("yellow", "", "?????????"));
                                }
                            } else if (Player.HP > 30) {
                                if (j == 6) {
                                    System.out.print(Tools.print("yellow", "", (" ??????")));
                                } else if (j == 7) {
                                    System.out.print(Tools.print("yellow", "", ("?????? ")));
                                } else if (j == 8) {
                                    System.out.print(Tools.print("yellow", "", ("   ")));
                                } else if (j == 9) {
                                    System.out.print(Tools.print("yellow", "", ("   ")));
                                }
                            } else if (Player.HP > 20) {
                                if (j == 6) {
                                    System.out.print(Tools.print("red", "", (" ??????")));
                                } else if (j == 7) {
                                    System.out.print(Tools.print("red", "", ("???  ")));
                                } else if (j == 8) {
                                    System.out.print(Tools.print("red", "", ("   ")));
                                } else if (j == 9) {
                                    System.out.print(Tools.print("red", "", ("   ")));
                                }
                            } else if (Player.HP > 10) {
                                if (j == 6) {
                                    System.out.print(Tools.print("red", "", (" ??????")));
                                } else if (j == 7) {
                                    System.out.print(Tools.print("red", "", ("   ")));
                                } else if (j == 8) {
                                    System.out.print(Tools.print("red", "", ("   ")));
                                } else if (j == 9) {
                                    System.out.print(Tools.print("red", "", ("   ")));
                                }
                            } else if (Player.HP > 0) {
                                if (j == 6) {
                                    System.out.print(Tools.print("red", "", (" ??? ")));
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
                            if (GameJava.enemies.get(enemyPos).HP > 90) {
                                if (j == 10) {
                                    System.out.print(Tools.print("green", "", (" " + "??????")));
                                } else if (j == 13) {
                                    System.out.print(Tools.print("green", "", ("??????" + " ")));
                                } else {
                                    System.out.print(Tools.print("green", "", "?????????"));
                                }
                            } else if (GameJava.enemies.get(enemyPos).HP > 80) {
                                if (j == 10) {
                                    System.out.print(Tools.print("green", "", (" " + " ???")));
                                } else if (j == 13) {
                                    System.out.print(Tools.print("green", "", ("??????" + " ")));
                                } else {
                                    System.out.print(Tools.print("green", "", "?????????"));
                                }
                            } else if (GameJava.enemies.get(enemyPos).HP > 70) {
                                if (j == 10) {
                                    System.out.print(Tools.print("green", "", (" " + "  ")));
                                } else if (j == 13) {
                                    System.out.print(Tools.print("green", "", ("??????" + " ")));
                                } else {
                                    System.out.print(Tools.print("green", "", "?????????"));
                                }
                            } else if (GameJava.enemies.get(enemyPos).HP > 60) {
                                if (j == 10) {
                                    System.out.print(Tools.print("green", "", ("   ")));
                                } else if (j == 11) {
                                    System.out.print(Tools.print("green", "", (" ??????")));
                                } else if (j == 13) {
                                    System.out.print(Tools.print("green", "", ("?????? ")));
                                } else {
                                    System.out.print(Tools.print("green", "", "?????????"));
                                }
                            } else if (GameJava.enemies.get(enemyPos).HP > 50) {
                                if (j == 10) {
                                    System.out.print(Tools.print("green", "", ("   ")));
                                } else if (j == 11) {
                                    System.out.print(Tools.print("green", "", ("  ???")));
                                } else if (j == 13) {
                                    System.out.print(Tools.print("green", "", ("??????" + " ")));
                                } else {
                                    System.out.print(Tools.print("green", "", "?????????"));
                                }
                            } else if (GameJava.enemies.get(enemyPos).HP > 40) {
                                if (j == 10) {
                                    System.out.print(Tools.print("green", "", ("   ")));
                                } else if (j == 11) {
                                    System.out.print(Tools.print("green", "", ("   ")));
                                } else if (j == 13) {
                                    System.out.print(Tools.print("yellow", "", ("??????" + " ")));
                                } else {
                                    System.out.print(Tools.print("yellow", "", "?????????"));
                                }
                            } else if (GameJava.enemies.get(enemyPos).HP > 30) {
                                if (j == 10) {
                                    System.out.print(Tools.print("green", "", ("   ")));
                                } else if (j == 11) {
                                    System.out.print(Tools.print("green", "", ("   ")));
                                } else if (j == 12) {
                                    System.out.print(Tools.print("yellow", "", (" ??????")));
                                } else if (j == 13) {
                                    System.out.print(Tools.print("yellow", "", ("??????" + " ")));
                                }
                            } else if (GameJava.enemies.get(enemyPos).HP > 20) {
                                if (j == 10) {
                                    System.out.print(Tools.print("green", "", ("   ")));
                                } else if (j == 11) {
                                    System.out.print(Tools.print("green", "", ("   ")));
                                } else if (j == 12) {
                                    System.out.print(Tools.print("red", "", ("  ???")));
                                } else if (j == 13) {
                                    System.out.print(Tools.print("red", "", ("??????" + " ")));
                                }
                            } else if (GameJava.enemies.get(enemyPos).HP > 10) {
                                if (j == 10) {
                                    System.out.print(Tools.print("red", "", ("   ")));
                                } else if (j == 11) {
                                    System.out.print(Tools.print("red", "", ("   ")));
                                } else if (j == 12) {
                                    System.out.print(Tools.print("red", "", ("   ")));
                                } else if (j == 13) {
                                    System.out.print(Tools.print("red", "", ("??????" + " ")));
                                }
                            } else if (GameJava.enemies.get(enemyPos).HP > 0) {
                                if (j == 10) {
                                    System.out.print(Tools.print("green", "", ("   ")));
                                } else if (j == 11) {
                                    System.out.print(Tools.print("green", "", ("   ")));
                                } else if (j == 12) {
                                    System.out.print(Tools.print("green", "", ("   ")));
                                } else if (j == 13) {
                                    System.out.print(Tools.print("red", "", ("???" + "  ")));
                                }
                            } else if (GameJava.enemies.get(enemyPos).HP <= 0) {
                                System.out.print(Tools.print("red", "", ("   ")));
                            }
                        } else {
                            System.out.print("   ");
                        }
                    } else if (j == ancho - 1) {
                        System.out.print(Tools.print(colorUI, "", "???"));
                    } else {
                        System.out.print("   ");
                    }
                }
            } else if (i == 3) {
                for (int j = 0; j < ancho; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(colorUI, "", "???"));
                    } else if (j > 0 && j <= 4) {
                        if (j == 1) {
                            System.out.print(" DMG    " + Player.DMG + "  ");
                        }
                    } else if (j > 4 && j < ancho - 5) {
                        if (j == ancho - 6) {
                            System.out.print(Tools.print(Screens.colorUI, "", "???"));
                        } else if (j == 5) {
                            System.out.print(Tools.print(Screens.colorUI, "", "???"));
                        } else {
                            System.out.print(voidSquare);
                        }
                    } else if (j >= ancho - 5 && j < ancho - 1) {
                        if (j == ancho - 5) {
                            System.out.print(" DMG    " + GameJava.enemies.get(enemyPos).attack + "  ");
                        }
                    } else if (j == ancho - 1) {
                        System.out.print(Tools.print(colorUI, "", "???"));
                    } else {
                        System.out.print("   ");
                    }
                }
            } else if (i == 4) {
                for (int j = 0; j < ancho; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(colorUI, "", "???"));
                    } else if (j > 0 && j <= 4) {
                        if (j == 1) {
                            if (Player.HP <= 0) {
                                System.out.print(" HP  0/" + Player.MAXHP + "  ");
                            } else if (Player.HP >= 100) {
                                System.out.print(" HP " + Player.HP + "/" + Player.MAXHP + " ");
                            } else if (Player.HP < 10) {
                                System.out.print(" HP " + Player.HP + "/" + Player.MAXHP + "   ");
                            } else {
                                System.out.print(" HP " + Player.HP + "/" + Player.MAXHP + "  ");
                            }
                        }
                    } else if (j > 4 && j < ancho - 5) {
                        if (j == ancho - 6) {
                            System.out.print(Tools.print(Screens.colorUI, "", "???"));
                        } else if (j == 5) {
                            System.out.print(Tools.print(Screens.colorUI, "", "???"));
                        } else if (j == 7) {
                            System.out.print(Tools.print("yellow", "", Character));
                        } else if (j == ancho - 8) {
                            System.out.print(Tools.print("red", "", Board.EnemyLeft));
                        } else {
                            System.out.print(voidSquare);
                        }
                    } else if (j >= ancho - 5 && j < ancho - 1) {
                        if (j == ancho - 5) {
                            if (GameJava.enemies.get(enemyPos).HP <= 0) {
                                System.out.print(" HP  0/" + Enemies.maxHP + "  ");
                            } else if (GameJava.enemies.get(enemyPos).HP >= 100) {
                                System.out.print(" HP " + GameJava.enemies.get(enemyPos).HP + "/" + Enemies.maxHP + " ");
                            } else if (GameJava.enemies.get(enemyPos).HP < 10) {
                                System.out.print(" HP " + GameJava.enemies.get(enemyPos).HP + "/" + Enemies.maxHP + "   ");
                            } else {
                                System.out.print(" HP " + GameJava.enemies.get(enemyPos).HP + "/" + Enemies.maxHP + "  ");
                            }
                        }
                    } else if (j == ancho - 1) {
                        System.out.print(Tools.print(colorUI, "", "???"));
                    } else {
                        System.out.print("   ");
                    }
                }
            } else if (i == 5) {
                for (int j = 0; j < ancho; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(colorUI, "", "???"));
                    } else if (j > 0 && j <= 4) {
                        if (j == 1) {
                            System.out.print(" LVL    " + Player.LV + "   ");
                        }
                    } else if (j > 4 && j < ancho - 5) {
                        if (j == ancho - 6) {
                            System.out.print(Tools.print(Screens.colorUI, "", "???"));
                        } else if (j == 5) {
                            System.out.print(Tools.print(Screens.colorUI, "", "???"));
                        } else {
                            System.out.print(voidSquare);
                        }
                    } else if (j >= ancho - 5 && j < ancho - 1) {
                        if (j == ancho - 5) {
                            System.out.print(" LVL    " + GameJava.enemies.get(enemyPos).LVL + "   ");
                        }
                    } else if (j == ancho - 1) {
                        System.out.print(Tools.print(colorUI, "", "???"));
                    }
                }
            } else {
                for (int j = 0; j < ancho; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(colorUI, "", "???"));
                    } else if (j > 4 && j < ancho - 5) {
                        if (j == ancho - 6) {
                            System.out.print(Tools.print(Screens.colorUI, "", "???"));
                        } else if (j == 5) {
                            System.out.print(Tools.print(Screens.colorUI, "", "???"));
                        } else {
                            System.out.print(voidSquare);
                        }
                    } else if (j == ancho - 1) {
                        System.out.print(Tools.print(colorUI, "", "???"));
                    } else {
                        System.out.print("   ");
                    }
                }
            }
            System.out.println("");
        }
        showAttackMenu();
    }

    public static void showAttackMenu() {
        System.out.println(Tools.print(colorUI, "", "[") + Tools.print(colorText, "", "1")
                + Tools.print(colorUI, "", "] - ") + Tools.print(colorText, "", "BASIC ATTACK"));
    }

    /**
     * Imprime la pantalla del tutorial.
     */
    public static void printTutorialScreen() {
        Tools.clearConsole();

        System.out.println(Tools.print(colorText, "", "??????????????? ???????????? ?????????????????????????????????????????????  "));
        System.out.println(Tools.print(colorText, "", "    ???  ??? ??? ??? ??? ??? ???????????????????????????  "));
        System.out.println(Tools.print(colorText, "", "???????????????  ??? ????????? ??? ???????????????????????? ????????????"));
        System.out.println("");
        System.out.println(Tools.print(colorUI, "", "Welcome to ") + Tools.print(colorText, "", "HI-SPEED NEW WAVE: SOUL HUNTER"));
        System.out.println("");
        System.out.println(Tools.print(colorUI, "", "Your mission here is to collect all "));
        System.out.println(Tools.print(colorUI, "", "the coins and defeat your enemies."));
        System.out.println("");
        System.out.println(Tools.print(colorText, "", "CONTROLS:"));
        System.out.print(Tools.print(colorUI, "", "	Use " + Tools.print(colorText, "", "???????????? ")));
        System.out.print(Tools.print(colorUI, "", "or "));
        System.out.print(Tools.print(colorText, "", "WASD"));
        System.out.print(Tools.print(colorUI, "", " to move."));
        System.out.println("");
        System.out.println(Tools.print(colorText, "", "GAME SYMBOLS:"));
        System.out.println(Tools.print(colorUI, "", "	??? Enemies: ???0???"));
        System.out.println(Tools.print(colorUI, "", "	??? Coins: $"));
        System.out.println(Tools.print(colorUI, "", "	??? Health potion: ") + Tools.print(colorUI, Enemy, "???"));
        System.out.println(Tools.print(colorUI, "", "	??? Vision potion: ") + Tools.print(colorUI, Enemy, "??"));
        System.out.println("");
        System.out.println(Tools.print(colorUI, "", "Under your current screen, "));
        System.out.println(Tools.print(colorUI, "", "you got the avaible controls."));
        System.out.println(Tools.print(colorUI, "", "you got the avaible controls."));
        System.out.println(Tools.print(colorUI, "", "[1] - ATACK	[2] - CHANGE CHARACTER"));
        System.out.println("");
        System.out.println(Tools.print(colorUI, "", "Press ENTER to exit..."));
    }

    /**
     * Imprime la pantalla de scores.
     *
     * @param scores Array dinamico con todos los scores.
     */
    public static void printScoresScreen(ArrayList<GameScores> scores) {
        System.out.println(Tools.print(colorUI, "", "-----SCORES-----"));
        if (scores.isEmpty()) {
            for (int i = 0; i < 10; i++) {
                if (i + 1 == 10) {
                    //elimina 1 espacio para que quepa el 1 del 10.
                    System.out.println(Tools.print(colorUI, "", " " + (i + 1) + " - " + "Null"));
                } else {
                    System.out.println(Tools.print(colorUI, "", "  " + (i + 1) + " - " + "Null"));
                }
            }
        } else {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println(Tools.print(colorUI, "", "  " + (i + 1) + " - " + scores.get(i).getPoints() + " points.\t" + scores.get(i).getFormatedDate()));
                } catch (IndexOutOfBoundsException e) {
                    if (i + 1 == 10) {
                        //elimina 1 espacio para que quepa el 1 del 10.
                        System.out.println(Tools.print(colorUI, "", " " + (i + 1) + " - " + "Null"));
                    } else {
                        System.out.println(Tools.print(colorUI, "", "  " + (i + 1) + " - " + "Null"));
                    }
                }
            }
        }
        System.out.println(Tools.print(colorUI, "", "Press ENTER to exit..."));
    }
}
