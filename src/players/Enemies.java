
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package players;

import gamejava.GameJava;
import outputs.Board;
import utilities.Tools;


public class Enemies {
    public static int xpos;
    public static int ypos;
    
    public Enemies(){
        
    }

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
    
   public static void movXPositive(int mov, String[][] board, int widthBoard, int heightBoard, String NULL_CELLS, int x, int y){
       String nextPosition = nextXPositive(board,widthBoard,heightBoard,mov,x,y);
       if(nextPosition.equals(NULL_CELLS)){
        board[y][x] = NULL_CELLS;
        x += mov;
        board[y][x] = Board.Enemy;
       }
       
    }
    public static void movXNegative(int mov, String[][] board, int widthBoard, int heigthBoard, String NULL_CELLS, int x, int y){
       String nextPosition = nextXNegative(board,widthBoard,heigthBoard,mov,x,y);
       if(nextPosition.equals(NULL_CELLS)){
        board[y][x] = NULL_CELLS;
        x -= mov;
        board[y][x] = Board.Enemy;
       }
    }
    public static void movYPositive(int mov, String[][] board, int widthBoard, int heigthBoard, String NULL_CELLS,int x, int y){
       String nextPosition = nextYPositive(board,widthBoard,heigthBoard,mov,x,y);
       if(nextPosition.equals(NULL_CELLS)){
        board[y][x] = NULL_CELLS;
        y -= mov;
        board[y][x] = Board.Enemy;
       }
    }
    public static void movYNegative(int mov, String[][] board, int widthBoard, int heigthBoard, String NULL_CELLS,int x, int y){
       String nextPosition = nextYNegative(board,widthBoard,heigthBoard,mov,x,y);
       if(nextPosition.equals(NULL_CELLS)){
        board[y][x] = NULL_CELLS;
        y += mov;
        board[y][x] = Board.Enemy;
       }
    }
    
    
    
    /*
        Mètodes nextPosition que ens retornen el contingut de la seguent casella, 
        en cas de que es surti de la graella ens retornarà el String = "DON'T EXISTS".
    */
    public static String nextXPositive(String[][] board, int widthBoard, int heigthBoard, int mov, int x, int y){
        String nextPosition="DON'T EXISTS";
        int xTemp = x+mov;
        
        if(xTemp>=0&&xTemp<=widthBoard-1){
            nextPosition = board[y][xTemp];
        }
       return nextPosition;
    }
    public static String nextXNegative(String[][] board, int widthBoard, int heigthBoard, int mov, int x, int y){
        String nextPosition="DON'T EXISTS";
        int xTemp = x-mov;
        
        if(xTemp>=0&&xTemp<=widthBoard-1){
            nextPosition = board[y][xTemp];
        }
       return nextPosition;
    }
    public static String nextYNegative(String[][] board, int widthBoard, int heigthBoard, int mov, int x, int y){
        String nextPosition="DON'T EXISTS";
        int yTemp = y+mov;
        
        if(yTemp>=0&&yTemp<=heigthBoard-1){
            nextPosition = board[yTemp][x];
        }
       return nextPosition;
    }
    public static String nextYPositive(String[][] board,int widthBoard ,int heigthBoard, int mov, int x,int y){
        String nextPosition="DON'T EXISTS";
        int yTemp = y-mov;
        
        if(yTemp>=0&&yTemp<=heigthBoard-1){
            nextPosition = board[yTemp][x];
        }
       return nextPosition;
    }
     
    public static void randomEnemyMovement(int mov, String[][] board, Enemies[]playable, int character, int widthBoard, int heigthBoard, String NULL_CELLS, int x, int y) {
        int probability = 0;
        int whereToMove = Tools.random(1,4);
        switch(GameJava.difficultSelection) {
            case 1:
                probability = Tools.random(1,3);
                break;
            case 2:
                probability = Tools.random(1,2);
                break;
            case 3:
                probability = 1;
                break;
        }
        switch (probability){
            case 1: 
                switch (whereToMove){
                    case 1:
                        Enemies.movYPositive(mov, board, widthBoard, heigthBoard, NULL_CELLS,x,y);
                        break;
                    case 2:
                        Enemies.movXPositive(mov, board, widthBoard, heigthBoard, NULL_CELLS,x,y);
                        break;
                    case 3:
                       Enemies.movYNegative(mov, board, widthBoard, heigthBoard, NULL_CELLS,x,y);
                        break;
                    case 4:
                        Enemies.movXNegative(mov, board, widthBoard, heigthBoard, NULL_CELLS,x,y);
                        break;
                }
                break;
        }
    }
    
    public static void moveEnemies(int number,String[][] board,Enemies[] enemies,int widthBoard, int heigthBoard, String NULL_CELLS) {
        int enemyNum = 0;
        
        for (int i = 0; i < board.length; i++ ){
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].equals(Board.Enemy)||board[i][j].equals(Board.EnemyRight)||board[i][j].equals(Board.EnemyLeft)) {
                    randomEnemyMovement(1,board,enemies,enemyNum, widthBoard,heigthBoard,NULL_CELLS,j,i);
                    enemyNum++;
                }
            }
        }
        setEnemiesDirection(board,widthBoard,heigthBoard, Board.voidSquare);
    }
    
    public static void setEnemiesDirection(String[][]board,int widthBoard, int heigthBoard, String NULL_CELLS){
        for (int i = 0; i < board.length; i++) {
          for (int j = 0; j < board[i].length; j++) {
		if (board[i][j].equals(Board.Enemy)||board[i][j].equals(Board.EnemyRight)||board[i][j].equals(Board.EnemyLeft)) {
                    if (j<=Player.xpos){
                        board[i][j] = Board.EnemyRight;
                    }
 		    else {
                        board[i][j] = Board.EnemyLeft;
                    }
                }
            }
        }
    }
    
}