package gamejava;

import static gamejava.GameJava.INPUT;
import static gamejava.GameJava.INPUT_RATE;
import java.util.concurrent.TimeUnit;
import outputs.Board;
import outputs.Screens;
import players.Enemies;
import players.Player;
import players.magician;
import players.priest;

public class Play {

    public static boolean isPlayingGame;
    public static int numBattles;
    /**
     * Imprime el tablero y se encarga de escuchar el INPUT del usuario.
     *
     * @throws InterruptedException
     */
    public static void playingGame() throws InterruptedException {
        int objects;
        boolean isPlayingGame = true;
        INPUT = "";
        Screens.boardSizeScreen();
        Screens.characterSelectorScreen();
        Screens.gameDifficultyScreen();

        Board.printBoard();
        Enemies.setEnemiesDirection();
        Enemies.setEnemiesPostions();
        objects = GameJava.numCoins + GameJava.numEnemies;
        numBattles = 0;
        do {
            
            switch (INPUT) {
                case "up":
                    Player.movYPositive(1);
                    Enemies.moveEnemies();
                    Board.printBoard();
                    break;
                case "down":
                    Player.movYNegative(1);
                    Enemies.moveEnemies();
                    Board.printBoard();
                    break;
                case "left":
                    Player.movXNegative(1);
                    Enemies.moveEnemies();
                    Board.printBoard();
                    break;
                case "right":
                    Player.movXPositive(1);
                    Enemies.moveEnemies();
                    Board.printBoard();
                    break;
                case "1":
                    Player.basicAttack();
                    Enemies.moveEnemies();
                    break;
                case "2":
                    Screens.characterSelectorScreen();
                    Board.printBoard();
                    Enemies.moveEnemies();
                    break;
                case "3":
                    Player.pickUpCoin();
                    Enemies.moveEnemies();
                    break;
                case "4": //curarse
                    if (GameJava.character == 2) {
                        priest.HealCharacter();
                        Enemies.moveEnemies();
                        Board.printBoard();
                    } else if (GameJava.character == 1) {
                        magician.motionSkill(2);
                        Enemies.moveEnemies();
                        Board.printBoard();
                    }
                    break;
                case "escape":
                case "0": //exit
                    Screens.startMenu(1);
                    isPlayingGame = false;
                    GameJava.enemies.clear();
                    break;
            }
            INPUT = "";
            Enemies.setEnemiesDirection();
            TimeUnit.MILLISECONDS.sleep(1000 / INPUT_RATE);
            if (objects == numBattles + Player.sackOfCoins || Player.HP == 0) {
                Screens.endGameScreen();
                Screens.startMenu(1);
                isPlayingGame = false;
                Screens.startMenu(1);
                GameJava.enemies.clear();
            }
        } while (isPlayingGame);
    }
}
