package gamejava;

import outputs.Screens;
import outputs.Board;
import utilities.Tools;
import utilities.InputListener;
import players.Player;
import players.magician;
import players.warrior;
import players.priest;
import java.util.concurrent.TimeUnit;

public class GameJava {

    public static int MIN_BOARD_SIZE = 10, MAX_BOARD_SIZE = 20; //maximo y minimo permitido valores tamaño tablero
    public static char CHAR_GUERRERO = '¥', CHAR_MAGO = '£', CHAR_SACERDOTE = '±', CHAR_ENEMY = '¤', CHAR_COIN = '$';

    public static int INPUT_RATE = 20; //delay entre consultas del input
    public static String INPUT = ""; //guarda la ultima tecla pulsada
    public static int menuOption = 1,
            secondSelection = 1, //valor para cuando hay mas de una selecion en un menu (Ej: choose caracter)
            character = 1, //personage del jugador
            widthBoard,
            heightBoard,
            numEnemies = 1, //numero de enemigos dependiendo la dificultad
            numCoins = 1;
    public static boolean SECTION_RUNNING = true;
    public static String[][] board;
    public static magician m1 = new magician();
    public static priest p1 = new priest();
    public static warrior w1 = new warrior();

    public static Player[] playable;

    public static void main(String[] args) throws InterruptedException {
        InputListener keyInput = new InputListener(); //crea y abre la ventana java

        boolean isGameRunning = true;
        playable = new Player[3];
        playable[0] = w1;
        playable[1] = m1;
        playable[2] = p1;

        /////////////////////////////////////////////////////
        //////////////   EMPIEZA EL PROGRAMA   //////////////
        /////////////////////////////////////////////////////
        Screens.printWaitScreen(); //pantalla de espera para empezar
        Screens.loadingAnimation(); //animacion de carga
        Screens.startMenu(menuOption); //pantalla menu

        do {
            switch (INPUT) {
                case "up":
                    if (menuOption > 1) {
                        menuOption--;
                        Screens.startMenu(menuOption);
                    }
                    break;
                case "down":
                    if (menuOption < 3) {
                        menuOption++;
                        Screens.startMenu(menuOption);
                    }
                    break;
                case "enter":
                    switch (menuOption) {
                        case 1: //play
                            Play.playingGame();
                            break;
                        case 2: //tutorial
                            break;
                        case 3: //exit
                            Screens.credits();
                            isGameRunning = false;
                            keyInput.dispose(); //elimina ventana creada por JAVA
                            break;
                    }
                    break;
                case "escape":
                    isGameRunning = false;
                    keyInput.dispose(); //elimina ventana creada por JAVA
                    break;
            }
            INPUT = "";
            TimeUnit.MILLISECONDS.sleep(1000 / INPUT_RATE);
        } while (isGameRunning);
    }
}
