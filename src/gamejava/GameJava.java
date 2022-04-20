package gamejava;

import java.util.concurrent.TimeUnit;

public class GameJava {

    final static int INPUT_RATE = 20; //delay entre consultas del input

    public static String INPUT = ""; //guarda la ultima tecla pulsada
    public static int menuOption = 1;

    public static void main(String[] args) throws InterruptedException {
        InputListener keyInput = new InputListener(); //crea y abre la ventana java

<<<<<<< Updated upstream
<<<<<<< Updated upstream
        int widthBoard,
                heightBoard,
                numEnemies,
                character;

        boolean isGameRunning = true,
                sectionRunning;
=======
=======
>>>>>>> Stashed changes
        boolean isGameRunning = true;
        playable = new Player[3];
        playable[0] = w1;
        playable[1] = m1;
        playable[2] = p1;
>>>>>>> Stashed changes

        Tools.clearConsole(); //sin este primer clear, no funcionan los colores en CMD windows

        do {
            //////////////////////////////////////////
            /////////  START GAME SECTION  ///////////
            //////////////////////////////////////////
            sectionRunning = true;
            StartSetup.waitScreen();
            do {
                TimeUnit.MILLISECONDS.sleep(1000 / INPUT_RATE);
                if (!INPUT.equals("")) {
                    INPUT = "";
                    sectionRunning = false;
                }
            } while (sectionRunning);

            StartSetup.loadingAnimation();

            sectionRunning = true;
            StartSetup.startMenu(menuOption);
            do {
                TimeUnit.MILLISECONDS.sleep(1000 / INPUT_RATE);

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
<<<<<<< Updated upstream
                            case 4:
=======
                            case 1: //play
                                boardSizeScreen();
                                characterSelectorScreen();
                                gameDifficultyScreen();
                                playingGame();
                                break;
                            case 2: //tutorial
                                break;
                            case 3: //config
                                break;
                            case 4: //exit
>>>>>>> Stashed changes
                                Tools.clearConsole();
                                System.out.println("[PANTALLA ADIOS]");
                                sectionRunning = false;
                                isGameRunning = false;
                                break;
                        }
                        sectionRunning = false;
                        break;
                }
            } while (sectionRunning);

            //////////////////////////////////////////
            ////////  INGAME PLAYING SECTION  ////////
            //////////////////////////////////////////
            if (isGameRunning == false) {
                keyInput.dispose();
            }
        } while (isGameRunning);
    }

    //////////////////////////////////////////
    //////////////  FUNCIONES  ///////////////
    //////////////////////////////////////////
<<<<<<< Updated upstream
=======
    /**
     * Imprime la pantalla de espera inicial. No continua hasta recibir un
     * INPUT.
     *
     * @throws InterruptedException
     */
    public static void printWaitScreen() throws InterruptedException {
        boolean noUserActivity = true;
        int counter = 0, state = 0;

        StartSetup.waitScreen(state);
        do {
            //truco para cambiar fotograma inicio cada 1s pero seguir leyendo el INPUT cada 50ms.
            //sin el truco, leeriamos el input cada 1s al imprimir el nuevo fotograma y no seria muy instantaneo el pulsar enter.
            if (counter == 18) {
                if (state == 0) {
                    state++;
                    StartSetup.waitScreen(state);
                } else {
                    state--;
                    StartSetup.waitScreen(state);
                }
                counter = 0;
            }

            TimeUnit.MILLISECONDS.sleep(1000 / INPUT_RATE);
            if (!INPUT.equals("")) {
                INPUT = "";
                noUserActivity = false;
            }
            counter++;
        } while (noUserActivity);
    }

    /**
     * Menu donde el usuario escoje el tamaño del tablero.
     *
     * @throws InterruptedException
     */
    public static void boardSizeScreen() throws InterruptedException {
        int valorInicial = (MIN_BOARD_SIZE + MAX_BOARD_SIZE) / 2;
        INPUT = "";
        SECTION_RUNNING = true;
        Tools.clearConsole();
        menuOption = 1;
        widthBoard = valorInicial;
        heightBoard = valorInicial;
        StartSetup.boardSizeScreen(valorInicial, valorInicial, menuOption);

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

    /**
     * Menu elecion personaje con el que se empieza la partida.
     *
     * @throws InterruptedException
     */
    public static void characterSelectorScreen() throws InterruptedException {
        INPUT = "";
        SECTION_RUNNING = true;
        Tools.clearConsole();
        menuOption = 1;
        StartSetup.characterSelectorScreen(menuOption, secondSelection);

        do {
            switch (INPUT) {
                case "up":
                    if (secondSelection == 2) {
                        secondSelection = 1;
                        StartSetup.characterSelectorScreen(menuOption, secondSelection);
                    }
                    INPUT = "";
                    break;
                case "down":
                    if (secondSelection == 1) {
                        secondSelection = 2;
                        StartSetup.characterSelectorScreen(menuOption, secondSelection);
                    }
                    INPUT = "";
                    break;
                case "left":
                    if (menuOption > 1 && secondSelection == 1) {
                        menuOption--;
                        StartSetup.characterSelectorScreen(menuOption, secondSelection);
                    }
                    INPUT = "";
                    break;
                case "right":
                    if (menuOption < 3 && secondSelection == 1) {
                        menuOption++;
                        StartSetup.characterSelectorScreen(menuOption, secondSelection);
                    } else if (secondSelection == 2) {
                        character = menuOption;
                        SECTION_RUNNING = false;
                    }
                    INPUT = "";
                    break;
                case "enter":
                    if (secondSelection == 1) {
                        secondSelection = 2;
                        StartSetup.characterSelectorScreen(menuOption, secondSelection);
                    } else if (secondSelection == 2) {
                        character = menuOption;
                        SECTION_RUNNING = false;
                    }
                    INPUT = "";
                    break;
            }
            TimeUnit.MILLISECONDS.sleep(1000 / INPUT_RATE);
        } while (SECTION_RUNNING);
    }

    /**
     * Menu donde escogemos la dificultad del juego.
     *
     * @throws InterruptedException
     */
    public static void gameDifficultyScreen() throws InterruptedException {
        INPUT = "";
        SECTION_RUNNING = true;
        Tools.clearConsole();
        numEnemies = 1;
        menuOption = 1;
        StartSetup.gameDifficultyScreen(menuOption);

        do {
            switch (INPUT) {
                case "up":
                    if (menuOption > 1) {
                        menuOption--;
                        StartSetup.gameDifficultyScreen(menuOption);
                        INPUT = "";
                    }
                    break;
                case "down":
                    if (menuOption < 3) {
                        menuOption++;
                        StartSetup.gameDifficultyScreen(menuOption);
                        INPUT = "";
                    }
                    break;
                case "right":
                case "enter":
                    //dependiendo la dificultad, hay un rango de enemigos que apareceran
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

    public static void playingGame() throws InterruptedException {

        boolean playingGame = true;
        Board.printBoard(widthBoard, heightBoard);
        Board.showMenu();

        do {
            switch (INPUT) {
                case "up":
                    Player.movYPositive(1, board, playable, character, widthBoard, heightBoard, Board.voidSquare);
                    Board.printBoard(widthBoard, heightBoard);
                    Board.showMenu();
                    INPUT = "";
                    break;
                case "down":
                    Player.movYNegative(1, board, playable, character, widthBoard, heightBoard, Board.voidSquare);
                    Board.printBoard(widthBoard, heightBoard);
                    Board.showMenu();
                    INPUT = "";
                    break;
                case "left":
                    Player.movXNegative(1, board, playable, character, widthBoard, heightBoard, Board.voidSquare);
                    Board.printBoard(widthBoard, heightBoard);
                    Board.showMenu();
                    INPUT = "";
                    break;
                case "right":
                    Player.movXPositive(1, board, playable, character, widthBoard, heightBoard, Board.voidSquare);
                    Board.printBoard(widthBoard, heightBoard);
                    Board.showMenu();
                    INPUT = "";
                    break;
                case "2":
                    break;
                case "4": //exit
                    Tools.clearConsole();
                    StartSetup.startMenu(menuOption);
                    playingGame = false;
                    break;
            }
            TimeUnit.MILLISECONDS.sleep(1000 / INPUT_RATE);
        } while (playingGame);
    }
    
>>>>>>> Stashed changes
}
