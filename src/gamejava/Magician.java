package gamejava;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author mariagarriga
 */
public class magician extends Player{
    //spells list
    //Create potion
    
    //Move +2 @override
    public static void movXPositive(int mov, String[][] board, Player[]playable,int character,int widthBoard ,int heightBoard,String NULL_CELLS){
       String nextPosition = nextXPositive(board,playable,character,widthBoard,heightBoard,mov);
       if(nextPosition.equals(NULL_CELLS)){
        board[playable[character].getYpos()][playable[character].getXpos()] = NULL_CELLS;
        Player.xpos += mov;
        board[playable[character].getYpos()][playable[character].getXpos()] = Board.Character;
       }
    }
    public static void movXNegative(int mov,String[][] board,Player[]playable,int character,int widthBoard ,int heightBoard,String NULL_CELLS){
       String nextPosition = nextXNegative(board,playable,character,widthBoard,heightBoard,mov);
       if(nextPosition.equals(NULL_CELLS)){
        board[playable[character].getYpos()][playable[character].getXpos()] = NULL_CELLS;
        Player.xpos -= mov;
        board[playable[character].getYpos()][playable[character].getXpos()] = Board.Character;
       }
    }
    public static void movYPositive(int mov,String[][] board,Player[]playable,int character,int widthBoard ,int heightBoard,String NULL_CELLS){
       String nextPosition = nextYPositive(board,playable,character,widthBoard,heightBoard,mov);
       if(nextPosition.equals(NULL_CELLS)){
         board[playable[character].getYpos()][playable[character].getXpos()] = NULL_CELLS;
        Player.ypos -= mov;
        board[playable[character].getYpos()][playable[character].getXpos()] = Board.Character;
       }
    }
    public static void movYNegative(int mov,String[][] board,Player[]playable,int character,int widthBoard ,int heightBoard,String NULL_CELLS){
       String nextPosition = nextYNegative(board,playable,character,widthBoard,heightBoard,mov);
       if(nextPosition.equals(NULL_CELLS)){
         board[playable[character].getYpos()][playable[character].getXpos()] = NULL_CELLS;
        Player.ypos += mov;
        board[playable[character].getYpos()][playable[character].getXpos()] = Board.Character;
       }
    }
    
    public static void motionSkill(int mov, String[][] board, Player[] playable, int character, int widthBoard, int heightBoard, String NULL_CELLS) throws InterruptedException {
        GameJava.INPUT = "";
        boolean motionSkills = true;
        
        do {
            switch (GameJava.INPUT) {
                case "up":
                    movYPositive(2, board, playable, character, widthBoard, heightBoard, Board.voidSquare);
                    Board.printBoard(widthBoard, heightBoard);
                    Board.showMenu();
                    GameJava.INPUT = "";
                    break;
                case "down":
                    movYNegative(2, board, playable, character, widthBoard, heightBoard, Board.voidSquare);
                    Board.printBoard(widthBoard, heightBoard);
                    Board.showMenu();
                    GameJava.INPUT = "";
                    break;
                case "left":
                    movXNegative(2, board, playable, character, widthBoard, heightBoard, Board.voidSquare);
                    Board.printBoard(widthBoard, heightBoard);
                    Board.showMenu();
                    GameJava.INPUT = "";
                    break;
                case "right":
                    movXPositive(2, board, playable, character, widthBoard, heightBoard, Board.voidSquare);
                    Board.printBoard(widthBoard, heightBoard);
                    Board.showMenu();
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


Attack enemy //atac especial

Move around

Take a potion //personatge

Combine potions

Pick up object //personatge

Buy object //personatge

Sell object //personatge

*/