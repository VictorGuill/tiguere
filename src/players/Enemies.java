package players;

import gamejava.GameJava;
import outputs.Board;
import utilities.Tools;

public class Enemies {

    public static int xpos;
    public static int ypos;
    public static int HP;
    public static int attack;
    public static int LVL;
    public static int maxHP = 100;

    public Enemies() {

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

    public static void movXPositive(int mov, int x, int y) {
        
        String nextPosition = nextXPositive(mov, x, y);
        if (nextPosition.equals(Board.voidSquare)) {
            GameJava.board[y][x] = Board.voidSquare;
            x += mov;
            GameJava.board[y][x] = Board.Enemy;
        }

    }

    public static void movXNegative(int mov, int x, int y) {

        String nextPosition = nextXNegative(mov, x, y);
        if (nextPosition.equals(Board.voidSquare)) {
            GameJava.board[y][x] = Board.voidSquare;
            x -= mov;
            GameJava.board[y][x] = Board.Enemy;
        }
    }

    public static void movYPositive(int mov, int x, int y) {
        
        String nextPosition = nextYPositive(mov, x, y);
        if (nextPosition.equals(Board.voidSquare)) {
            GameJava.board[y][x] = Board.voidSquare;
            y -= mov;
            GameJava.board[y][x] = Board.Enemy;
        }
    }

    public static void movYNegative(int mov, int x, int y) {

        String nextPosition = nextYNegative(mov, x, y);
        if (nextPosition.equals(Board.voidSquare)) {
            GameJava.board[y][x] = Board.voidSquare;
            y += mov;
            GameJava.board[y][x] = Board.Enemy;
        }
    }

    /*
        Mètodes nextPosition que ens retornen el contingut de la seguent casella, 
        en cas de que es surti de la graella ens retornarà el String = "DON'T EXISTS".
     */
    public static String nextXPositive(int mov, int x, int y) {
        String nextPosition = "DON'T EXISTS";
        int width = GameJava.widthBoard;
        int xTemp = x + mov;
        

        if (xTemp >= 0 && xTemp <= width - 1) {
            nextPosition = GameJava.board[y][xTemp];
        }
        return nextPosition;
    }

    public static String nextXNegative(int mov, int x, int y) {
        String nextPosition = "DON'T EXISTS";
        int width = GameJava.widthBoard;
        int xTemp = x - mov;

        if (xTemp >= 0 && xTemp <= width - 1) {
            nextPosition = GameJava.board[y][xTemp];
        }
        return nextPosition;
    }

    public static String nextYNegative(int mov, int x, int y) {
        String nextPosition = "DON'T EXISTS";
        int height = GameJava.heightBoard;
        int yTemp = y + mov;

        if (yTemp >= 0 && yTemp <= height - 1) {
            nextPosition = GameJava.board[yTemp][x];
        }
        return nextPosition;
    }

    public static String nextYPositive(int mov, int x, int y) {
        String nextPosition = "DON'T EXISTS";
        int height = GameJava.heightBoard;
        int yTemp = y - mov;

        if (yTemp >= 0 && yTemp <= height - 1) {
            nextPosition = GameJava.board[yTemp][x];
        }
        return nextPosition;
    }

    public static void randomEnemyMovement(int mov, int x, int y) {
        int probability = 0;
        int whereToMove = Tools.random(1, 4);
        
        switch (GameJava.difficultSelection) {
            case 1:
                probability = Tools.random(1, 3);
                break;
            case 2:
                probability = Tools.random(1, 2);
                break;
            case 3:
                probability = 1;
                break;
        }
        switch (probability) {
            case 1:
                switch (whereToMove) {
                    case 1:
                        Enemies.movYPositive(mov, x, y);
                        break;
                    case 2:
                        Enemies.movXPositive(mov, x, y);
                        break;
                    case 3:
                        Enemies.movYNegative(mov, x, y);
                        break;
                    case 4:
                        Enemies.movXNegative(mov, x, y);
                        break;
                }
                break;
        }
    }

    public static void moveEnemies() {
        int enemyNum = 0;
        String board[][] = GameJava.board;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].equals(Board.Enemy) || board[i][j].equals(Board.EnemyRight) || board[i][j].equals(Board.EnemyLeft)) {
                    randomEnemyMovement(1, j, i);
                    enemyNum++;
                }
            }
        }
        setEnemiesDirection();
    }

    public static void setEnemiesDirection() {
        String[][] board = GameJava.board;
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].equals(Board.Enemy) || board[i][j].equals(Board.EnemyRight) || board[i][j].equals(Board.EnemyLeft)) {
                    if (j <= Player.xpos) {
                        board[i][j] = Board.EnemyRight;
                    } else {
                        board[i][j] = Board.EnemyLeft;
                    }
                }
            }
        }
    }

}
