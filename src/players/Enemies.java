/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package players;

import gamejava.GameJava;
import outputs.Board;
import static players.Player.direction;
import utilities.Tools;


public class Enemies {
    public static int xpos;
    public static int ypos;

    public static int getXpos() {
        return xpos;
    }

    public static int getYpos() {
        return ypos;
    }

    public static void setXpos(int xpos) {
        Enemies.xpos = xpos;
    }

    public static void setYpos(int ypos) {
        Enemies.ypos = ypos;
    }
    
    
   
    
     public static void movXPositive(int mov, String[][] board, Enemies[]playable, int character, int widthBoard, int heightBoard, String NULL_CELLS){
       String nextPosition = nextXPositive(board,playable,character,widthBoard,heightBoard,mov);
       if(nextPosition.equals(NULL_CELLS)){
        board[playable[character].getYpos()][playable[character].getXpos()] = NULL_CELLS;
        Enemies.xpos += mov;
        board[playable[character].getYpos()][playable[character].getXpos()] = Board.Enemy;
       }
    }
    public static void movXNegative(int mov, String[][] board, Enemies[]playable, int character, int widthBoard, int heigthBoard, String NULL_CELLS){
       String nextPosition = nextXNegative(board,playable,character,widthBoard,heigthBoard,mov);
       if(nextPosition.equals(NULL_CELLS)){
        board[playable[character].getYpos()][playable[character].getXpos()] = NULL_CELLS;
        Enemies.xpos -= mov;
        
        board[playable[character].getYpos()][playable[character].getXpos()] = Board.Enemy;
       }

  
    }
    public static void movYPositive(int mov, String[][] board, Enemies[]playable, int character, int widthBoard, int heigthBoard, String NULL_CELLS){
       String nextPosition = nextYPositive(board,playable,character,widthBoard,heigthBoard,mov);
       if(nextPosition.equals(NULL_CELLS)){
         board[playable[character].getYpos()][playable[character].getXpos()] = NULL_CELLS;
        Enemies.ypos -= mov;
        board[playable[character].getYpos()][playable[character].getXpos()] = Board.Enemy;
       }
    }
    public static void movYNegative(int mov, String[][] board, Enemies[]playable, int character, int widthBoard, int heigthBoard, String NULL_CELLS){
       String nextPosition = nextYNegative(board,playable,character,widthBoard,heigthBoard,mov);
       if(nextPosition.equals(NULL_CELLS)){
        board[playable[character].getYpos()][playable[character].getXpos()] = NULL_CELLS;
        Enemies.ypos += mov;
        board[playable[character].getYpos()][playable[character].getXpos()] = Board.Enemy;
       }
    }
    
    
    
    /*
        Mètodes nextPosition que ens retornen el contingut de la seguent casella, 
        en cas de que es surti de la graella ens retornarà el String = "DON'T EXISTS".
    */
    public static String nextXPositive(String[][] board,Enemies[]playable, int character, int widthBoard, int heigthBoard, int mov){
        String nextPosition="DON'T EXISTS";
        int xTemp = playable[character].getXpos()+mov;
        
        if(xTemp>=0&&xTemp<=widthBoard-1){
            nextPosition = board[playable[character].getYpos()][xTemp];
        }
       return nextPosition;
    }
    public static String nextXNegative(String[][] board,Enemies[]playable, int character, int widthBoard, int heigthBoard, int mov){
        String nextPosition="DON'T EXISTS";
        int xTemp = playable[character].getXpos()-mov;
        
        if(xTemp>=0&&xTemp<=widthBoard-1){
            nextPosition = board[playable[character].getYpos()][xTemp];
        }
       return nextPosition;
    }
    public static String nextYNegative(String[][] board, Enemies[]playable, int character, int widthBoard, int heigthBoard, int mov){
        String nextPosition="DON'T EXISTS";
        int yTemp = playable[character].getYpos()+mov;
        
        if(yTemp>=0&&yTemp<=heigthBoard-1){
            nextPosition = board[yTemp][playable[character].getXpos()];
        }
       return nextPosition;
    }
    public static String nextYPositive(String[][] board, Enemies[]playable, int character, int widthBoard ,int heigthBoard, int mov){
        String nextPosition="DON'T EXISTS";
        int yTemp = playable[character].getYpos()-mov;
        
        if(yTemp>=0&&yTemp<=heigthBoard-1){
            nextPosition = board[yTemp][playable[character].getXpos()];
        }
       return nextPosition;
    }
    public static void randomEnemyMovement(int mov, String[][] board, Enemies[]playable, int character, int widthBoard, int heigthBoard, String NULL_CELLS) {
        int probability = Tools.random(1,2);
        int whereToMove = Tools.random(1,4);
        switch (probability){
            case 1: 
                switch (whereToMove){
                    case 1:
                        Enemies.movYPositive(mov, board, playable, character, widthBoard, heigthBoard, NULL_CELLS);
                        break;
                    case 2:
                        Enemies.movXPositive(mov, board, playable, character, widthBoard, heigthBoard, NULL_CELLS);
                        break;
                    case 3:
                        Enemies.movYNegative(mov, board, playable, character, widthBoard, heigthBoard, NULL_CELLS);
                        break;
                    default:
                        Enemies.movXNegative(mov, board, playable, character, widthBoard, heigthBoard, NULL_CELLS);
                        break;
                }
                break;
        }
    }
    
    public static void moveEnemies(Enemies[] enemies,int widthBoard, int heigthBoard, String NULL_CELLS) {
        for (int i = 0; i < enemies.length; i++) {
            randomEnemyMovement(1,GameJava.board, GameJava.enemies, i,widthBoard,heigthBoard,NULL_CELLS);
        }
    }
    
}
