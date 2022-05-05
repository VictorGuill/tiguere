package players;

import gamejava.GameJava;
import outputs.Board;
import utilities.Tools;

public class Enemies {

    public   int HP,
                    attack, 
                    LVL,
            ypos,
            xpos,
                    maxHP = 100;

    public Enemies() {

    }
    
    /**
     * Movera el enemigo (en el eje positivo horizontalmente) en caso de que la siguente casilla sea un voidSquare que es una casilla vacia
     * @param mov numeros de casillas a moverse por el personaje
     * @param x posición en x del enemigo
     * @param y posición en y del enemigo
     */

    public static void movXPositive(int mov, int x, int y) {

        String nextPosition = nextXPositive(mov, x, y);
        if (nextPosition.equals(Board.voidSquare)) {
            GameJava.board[y][x] = Board.voidSquare;
            x += mov;
            GameJava.board[y][x] = Board.Enemy;
        }

    }
    
      /**
     * Movera el enemigo (en el eje negativo horizontalmente) en caso de que la siguente casilla sea un voidSquare que es una casilla vacia
     * @param mov numeros de casillas a moverse por el personaje
     * @param x posición en x del enemigo
     * @param y posición en y del enemigo
     */

    public static void movXNegative(int mov, int x, int y) {

        String nextPosition = nextXNegative(mov, x, y);
        if (nextPosition.equals(Board.voidSquare)) {
            GameJava.board[y][x] = Board.voidSquare;
            x -= mov;
            GameJava.board[y][x] = Board.Enemy;
        }
    }
    
      /**
     * Movera el enemigo (en el eje positivo verticalmente) en caso de que la siguente casilla sea un voidSquare que es una casilla vacia
     * @param mov numeros de casillas a moverse por el personaje
     * @param x posición en x del enemigo
     * @param y posición en y del enemigo
     */

    public static void movYPositive(int mov, int x, int y) {

        String nextPosition = nextYPositive(mov, x, y);
        if (nextPosition.equals(Board.voidSquare)) {
            GameJava.board[y][x] = Board.voidSquare;
            y -= mov;
            GameJava.board[y][x] = Board.Enemy;
        }
    }
    
      /**
     * Movera el enemigo (en el eje negativo verticalmente) en caso de que la siguente casilla sea un voidSquare que es una casilla vacia
     * @param mov numeros de casillas a moverse por el personaje
     * @param x posición en x del enemigo
     * @param y posición en y del enemigo
     */
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
    
    /**
     * Funcion que devuelve lo que hay en la casilla siguiente del personaje si se mueve hacia la derecha
     * @param mov numeros de casillas a moverse por el personaje
     * @param x posición en x del enemigo
     * @param y posición en y del enemigo
     * @return nextPosition Que hay en la siguiente casilla del personaje 
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
    
        /**
     * Funcion que devuelve lo que hay en la casilla siguiente del personaje si se mueve hacia la izquierda
     * @param mov numeros de casillas a moverse por el personaje
     * @param x posición en x del enemigo
     * @param y posición en y del enemigo
     * @return nextPosition Que hay en la siguiente casilla del personaje 
     */
    public static String nextXNegative(int mov, int x, int y) {
        String nextPosition = "DON'T EXISTS";
        int width = GameJava.widthBoard;
        int xTemp = x - mov;

        if (xTemp >= 0 && xTemp <= width - 1) {
            nextPosition = GameJava.board[y][xTemp];
        }
        return nextPosition;
    }

        /**
     * Funcion que devuelve lo que hay en la casilla siguiente del personaje si se mueve hacia abajo
     * @param mov numeros de casillas a moverse por el personaje
     * @param x posición en x del enemigo
     * @param y posición en y del enemigo
     * @return nextPosition Que hay en la siguiente casilla del personaje 
     */
    public static String nextYNegative(int mov, int x, int y) {
        String nextPosition = "DON'T EXISTS";
        int height = GameJava.heightBoard;
        int yTemp = y + mov;

        if (yTemp >= 0 && yTemp <= height - 1) {
            nextPosition = GameJava.board[yTemp][x];
        }
        return nextPosition;
    }
    
        /**
     * Funcion que devuelve lo que hay en la casilla siguiente del personaje si se mueve hacia arriba
     * @param mov numeros de casillas a moverse por el personaje
     * @param x posición en x del enemigo
     * @param y posición en y del enemigo
     * @return nextPosition Que hay en la siguiente casilla del personaje 
     */
    public static String nextYPositive(int mov, int x, int y) {
        String nextPosition = "DON'T EXISTS";
        int height = GameJava.heightBoard;
        int yTemp = y - mov;

        if (yTemp >= 0 && yTemp <= height - 1) {
            nextPosition = GameJava.board[yTemp][x];
        }
        return nextPosition;
    }
    
    /**
     * Funcion que movera aleatoriamente el personaje 1 casilla en cualquier dirección , con una probabilidad de no moverse (esta probabilidad se reduce a medida 
     * que augmenta la dificultad)
     * @param mov numeros de casillas a moverse por el personaje
     * @param x posición en x del enemigo
     * @param y posición en y del enemigo
     */

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
    
    /**
     * Funcion que buscara en el tablero a un enemigo y le hará moverse con la función randomEnemyMovement , y con la setEnemiDirection 
     * cambaiara la vista del personaje
     */
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
    /**
     * Función que buscara todos los enemigos del array , despues comparará su posición con la de tu y finalmente cambiara su vista para situarlo mirando hacia ti
     */
 
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
    
    /**
     * En referencia a la dificultat se determinaran unas caracteristicas para luchar contra él
     * @param e enemigo creado a la hora de luchar contra él 
     */

    public static void setUpEnemy(Enemies e) {
        //Enemies.HP = 100;
        switch (GameJava.difficultSelection) {
            case 1:
                e.LVL = 1;
                e.attack = Tools.random(10, 13);
                break;
            case 2:
                e.LVL = 2;
                e.attack = Tools.random(14, 17);
                break;
            default:
                e.LVL = 3;
                e.attack = Tools.random(18, 20);
                break;
        }
    }
    
        public static void setEnemiesPostions(){
           
         int i =0;
        while(i< GameJava.enemies.size())
            for (int j = 0; j < GameJava.board.length; j++) {
                for (int k = 0; k < GameJava.board[j].length; k++) {
                    if(GameJava.board[j][k].equals(Board.Enemy)||GameJava.board[j][k].equals(Board.EnemyLeft)||GameJava.board[j][k].equals(Board.EnemyRight)){
                        GameJava.enemies.get(i).setXpos(k);
                        GameJava.enemies.get(i).setYpos(j);
                        i++;
                    }
                }
 
            }
        
    }

    @Override
    public String toString() {
        return "Enemies{" + "ypos=" + ypos + ", xpos=" + xpos + '}';
    }
        
        

    public void setYpos(int ypos) {
        this.ypos = ypos;
    }

    public void setXpos(int xpos) {
        this.xpos = xpos;
    }
        
        
        
        

}
