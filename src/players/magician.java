package players;

import outputs.Board;
import gamejava.GameJava;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author mariagarriga
 */
public class magician extends Player {

    /**
     * HABILIDADES ESPECIALES DE MOVIMIENTO.
     *
     * Cuando usamos el mago tenemos la posibilidad de movernos dos casillas a
     * la vez.
     */
    public static void motionSkill(int mov, String[][] board, Player[] playable, int character, int widthBoard, int heightBoard, String NULL_CELLS) throws InterruptedException {
        GameJava.INPUT = "";
        boolean motionSkills = true;

        do {
            switch (GameJava.INPUT) {
                case "up":
                    movYPositive(2, board, playable, character, widthBoard, heightBoard, Board.voidSquare);
                    Board.printBoard(widthBoard, heightBoard);
                    GameJava.INPUT = "";
                    break;
                case "down":
                    movYNegative(2, board, playable, character, widthBoard, heightBoard, Board.voidSquare);
                    Board.printBoard(widthBoard, heightBoard);
                    GameJava.INPUT = "";
                    break;
                case "left":
                    movXNegative(2, board, playable, character, widthBoard, heightBoard, Board.voidSquare);
                    Board.printBoard(widthBoard, heightBoard);
                    GameJava.INPUT = "";
                    break;
                case "right":
                    movXPositive(2, board, playable, character, widthBoard, heightBoard, Board.voidSquare);
                    Board.printBoard(widthBoard, heightBoard);
                    GameJava.INPUT = "";
                    break;
                case "5":
                    motionSkills = false;
                    GameJava.INPUT = "";
                    break;
            }
            TimeUnit.MILLISECONDS.sleep(1000 / GameJava.INPUT_RATE);
        } while (motionSkills);
    }
}

/*

Magicians have a range of spells they can use, but they cannot carry weapons. 
Their  special skill allows them to mix potions from their existing inventory 
in order to create new ones. 
They also have a driving motion skill which allows them to move forward a certain distance. 

*/
