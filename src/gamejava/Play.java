package gamejava;

import static gamejava.GameJava.INPUT;
import static gamejava.GameJava.INPUT_RATE;
import static gamejava.GameJava.board;
import static gamejava.GameJava.character;
import static gamejava.GameJava.heightBoard;
import static gamejava.GameJava.playable;
import static gamejava.GameJava.widthBoard;
import java.util.concurrent.TimeUnit;
import outputs.Board;
import outputs.Screens;
import players.Enemies;
import players.Player;
import players.magician;

public class Play {

    public static boolean isPlayingGame;

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

        Board.printBoard(widthBoard, heightBoard);
        Enemies.setEnemiesDirection(board, widthBoard, heightBoard, Board.voidSquare);
        objects = GameJava.numCoins + GameJava.numEnemies;

        do {
            switch (INPUT) {
                case "up":
                    Player.movYPositive(1, board, playable, character, widthBoard, heightBoard, Board.voidSquare);
                    Enemies.moveEnemies(GameJava.numEnemies, GameJava.board, GameJava.enemies, widthBoard, heightBoard, Board.voidSquare);
                    Board.printBoard(widthBoard, heightBoard);
                    break;
                case "down":
                    Player.movYNegative(1, board, playable, character, widthBoard, heightBoard, Board.voidSquare);
                    Enemies.moveEnemies(GameJava.numEnemies, GameJava.board, GameJava.enemies, widthBoard, heightBoard, Board.voidSquare);
                    Board.printBoard(widthBoard, heightBoard);
                    break;
                case "left":
                    Player.movXNegative(1, board, playable, character, widthBoard, heightBoard, Board.voidSquare);
                    Enemies.moveEnemies(GameJava.numEnemies, GameJava.board, GameJava.enemies, widthBoard, heightBoard, Board.voidSquare);
                    Board.printBoard(widthBoard, heightBoard);
                    break;
                case "right":
                    Player.movXPositive(1, board, playable, character, widthBoard, heightBoard, Board.voidSquare);
                    Enemies.moveEnemies(GameJava.numEnemies, GameJava.board, GameJava.enemies, widthBoard, heightBoard, Board.voidSquare);
                    Board.printBoard(widthBoard, heightBoard);
                    break;
                case "1":
                    Player.basicAttack(board, playable, character, widthBoard, heightBoard, Board.EnemyRight, Board.EnemyLeft, Board.Enemy);
                    Enemies.moveEnemies(GameJava.numEnemies, GameJava.board, GameJava.enemies, widthBoard, heightBoard, Board.voidSquare);
                    break;
                case "2":
                    Screens.characterSelectorScreen();
                    Board.printBoard(widthBoard, heightBoard);
                    Enemies.moveEnemies(GameJava.numEnemies, GameJava.board, GameJava.enemies, widthBoard, heightBoard, Board.voidSquare);
                    break;
                case "3":
                    Player.pickUpCoin(board, playable, character, widthBoard, heightBoard, (" " + GameJava.CHAR_COIN + " "));
                    Enemies.moveEnemies(GameJava.numEnemies, GameJava.board, GameJava.enemies, widthBoard, heightBoard, Board.voidSquare);
                    break;
                case "4": //exit
                    Screens.startMenu(1);
                    isPlayingGame = false;
                    break;
                case "5":
                    if (GameJava.character == 1) {
                        magician.motionSkill(1, board, playable, character, widthBoard, heightBoard, Board.voidSquare);
                        Enemies.moveEnemies(GameJava.numEnemies, GameJava.board, GameJava.enemies, widthBoard, heightBoard, Board.voidSquare);
                        Board.printBoard(widthBoard, heightBoard);
                    }
                    break;
            }
            INPUT = "";
            if (objects == Player.kills + Player.sackOfCoins) {
                Screens.endGameScreen();
            }
            Enemies.setEnemiesDirection(board, widthBoard, heightBoard, Board.voidSquare);
            TimeUnit.MILLISECONDS.sleep(1000 / INPUT_RATE);
        } while (isPlayingGame);
    }
}
