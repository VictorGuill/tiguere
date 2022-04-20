package gamejava;

/**
 *
 * @author mariagarriga
 */
public class magician extends Player{
    //spells list
    //Create potion
    
    //Move +2 @override
    public static void movXPositive(int mov, String[][] board, Player[]playable,int character,int widthBoard ,int heigthBoard,String NULL_CELLS){
       String nextPosition = nextXPositive(board,playable,character,widthBoard,heigthBoard,mov);
       if(nextPosition.equals(NULL_CELLS)){
        board[Player.getYpos()][Player.getXpos()] = NULL_CELLS;
        Player.xpos += mov+2;
        board[Player.getYpos()][Player.getXpos()] = Board.Character;
       }
    }
    
    public static void movXNegative(int mov,String[][] board,Player[]playable,int character,int widthBoard ,int heigthBoard,String NULL_CELLS){
       String nextPosition = nextXNegative(board,playable,character,widthBoard,heigthBoard,mov);
       if(nextPosition.equals(NULL_CELLS)){
        board[Player.getYpos()][Player.getXpos()] = NULL_CELLS;
        Player.xpos -= mov-2;
        board[Player.getYpos()][Player.getXpos()] = Board.Character;
       }
    }
    
    public static void movYPositive(int mov,String[][] board,Player[]playable,int character,int widthBoard ,int heigthBoard,String NULL_CELLS){
       String nextPosition = nextYPositive(board,playable,character,widthBoard,heigthBoard,mov);
       if(nextPosition.equals(NULL_CELLS)){
         board[Player.getYpos()][Player.getXpos()] = NULL_CELLS;
        Player.ypos -= mov-2;
        board[Player.getYpos()][Player.getXpos()] = Board.Character;
       }
    }
    
    public static void movYNegative(int mov,String[][] board,Player[]playable,int character,int widthBoard ,int heigthBoard,String NULL_CELLS){
       String nextPosition = nextYNegative(board,playable,character,widthBoard,heigthBoard,mov);
       if(nextPosition.equals(NULL_CELLS)){
         board[Player.getYpos()][Player.getXpos()] = NULL_CELLS;
        Player.ypos += mov+2;
        board[Player.getYpos()][Player.getXpos()] = Board.Character;
       }
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