
package gamejava;

import outputs.Screens;
import utilities.InputListener;
import players.Player;
import players.magician;
import players.warrior;
import players.priest;
import java.util.concurrent.TimeUnit;
import players.Enemies;

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
            numEnemies, //numero de enemigos dependiendo la dificultad
            numCoins,
            difficultSelection;
    public static boolean SECTION_RUNNING = true;
    public static String[][] board;
    public static magician m1 = new magician();
    public static priest p1 = new priest();
    public static warrior w1 = new warrior();
    public static Enemies e1 = new Enemies();
    public static Enemies e2 = new Enemies();
    public static Enemies e3 = new Enemies();
    public static Enemies e4 = new Enemies();
    public static Enemies e5 = new Enemies();
    public static Enemies e6 = new Enemies();

    public static Player[] playable;
    public static Enemies[] enemies;

    public static void main(String[] args) throws InterruptedException {
        InputListener keyInput = new InputListener(); //crea y abre la ventana java

        boolean isGameRunning = true;
        playable = new Player[3];
        playable[0] = w1;
        playable[1] = m1;
        playable[2] = p1;
        
        enemies = new Enemies[6];
        enemies[0] = e1;
        enemies[1] = e2;
        enemies[2] = e3;
        enemies[3] = e4;
        enemies[4] = e5;
        enemies[5] = e6;
        

        /////////////////////////////////////////////////////
        //////////////   EMPIEZA EL PROGRAMA   //////////////
        /////////////////////////////////////////////////////
        Screens.printWaitScreen(); //pantalla de espera para empezar
        Screens.loadingAnimation(); //animacion de carga
        Screens.startMenu(menuOption); //pantalla menu
        INPUT = "";
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
                            Play.playingGame = true;
                            Play.playingGame();
                            Screens.credits();
                            isGameRunning = false;
                            keyInput.dispose(); //elimina ventana creada por JAVA
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
            TimeUnit.MILLISECONDS.sleep(1000 / INPUT_RATE);
        } while (isGameRunning);
    }
}
