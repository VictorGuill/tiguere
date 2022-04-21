package gamejava;

import static gamejava.GameJava.INPUT;
import static gamejava.GameJava.INPUT_RATE;
import static gamejava.GameJava.SECTION_RUNNING;
import static gamejava.GameJava.board;
import static gamejava.GameJava.character;
import static gamejava.GameJava.heightBoard;
import static gamejava.GameJava.playable;
import static gamejava.GameJava.widthBoard;
import java.util.concurrent.TimeUnit;
import outputs.Board;
import outputs.Screens;
import players.Player;
import players.magician;
import utilities.Tools;

public class Play {

    public static void playingGame() throws InterruptedException {
        boolean playingGame = true;
        
        INPUT = "";
        
        Screens.boardSizeScreen();
        Screens.characterSelectorScreen();
        Screens.gameDifficultyScreen();

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
                case "1":
                    Player.basicAttack(board, playable, character, widthBoard, heightBoard, (" " + GameJava.CHAR_ENEMY + " "));
                    break;
                case "2":
                    changeCharacter(playable);
                    break;
                case "3":
                    Player.pickUpCoin(board, playable, character, widthBoard, heightBoard, (" " + GameJava.CHAR_COIN + " "));
                    break;
                case "4": //exit
                    Tools.clearConsole();
                    Screens.startMenu(1);
                    playingGame = false;
                    break;
                case "5":
                    if (Board.Character.equals((" " + GameJava.CHAR_MAGO + " "))){
                        magician.motionSkill(board, playable, character, widthBoard, heightBoard, Board.voidSquare);
                        Board.printBoard(widthBoard, heightBoard);
                        Board.showMenu();
                    }
                    break;
            }
            TimeUnit.MILLISECONDS.sleep(1000 / INPUT_RATE);
        } while (playingGame);
    }

    public static void changeCharacter(Player[] playable) throws InterruptedException {
        int yTemp = playable[character].getYpos();
        int xTemp = playable[character].getXpos();

        Screens.characterSelectorScreen();
        SECTION_RUNNING = true;

        Player.setYpos(yTemp);
        Player.setXpos(xTemp);

        board[yTemp][xTemp] = Board.Character;
    }
}
