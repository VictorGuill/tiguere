package players;

import gamejava.GameJava;
import outputs.Board;
import utilities.Tools;

public class Enemies {

    /*cambiar*/
    public int HP,
            attack,
            LVL,
            ypos,
            xpos;
    public boolean isAlive;
    public static int maxHP = 100;

    public Enemies() {
        this.isAlive = true;
    }

    /**
     * Movera el enemigo (en el eje positivo horizontalmente) en caso de que la
     * siguente casilla sea un voidSquare que es una casilla vacia
     *
     * @param mov numeros de casillas a moverse por el personaje
     */
    public void movXPositive(int mov, int i) {
        int x = GameJava.enemies.get(i).getXpos();
        int y = GameJava.enemies.get(i).getYpos();

        String nextPosition = nextXPositive(mov, x, y);
        if (nextPosition.equals(Board.voidSquare)) {
            GameJava.board[GameJava.enemies.get(i).getYpos()][GameJava.enemies.get(i).getXpos()] = Board.voidSquare;
            GameJava.enemies.get(i).xpos += mov;
            GameJava.board[GameJava.enemies.get(i).getYpos()][GameJava.enemies.get(i).getXpos()] = Board.Enemy;
        }

    }

    /**
     * Movera el enemigo (en el eje negativo horizontalmente) en caso de que la
     * siguente casilla sea un voidSquare que es una casilla vacia
     *
     * @param mov numeros de casillas a moverse por el personaje
     */
    public void movXNegative(int mov, int i) {
        int x = GameJava.enemies.get(i).getXpos();
        int y = GameJava.enemies.get(i).getYpos();
        String nextPosition = nextXNegative(mov, x, y);
        if (nextPosition.equals(Board.voidSquare)) {
            GameJava.board[GameJava.enemies.get(i).getYpos()][GameJava.enemies.get(i).getXpos()] = Board.voidSquare;
            GameJava.enemies.get(i).xpos -= mov;
            GameJava.board[GameJava.enemies.get(i).getYpos()][GameJava.enemies.get(i).getXpos()] = Board.Enemy;
        }
    }

    /**
     * Movera el enemigo (en el eje positivo verticalmente) en caso de que la
     * siguente casilla sea un voidSquare que es una casilla vacia
     *
     * @param mov numeros de casillas a moverse por el personaje
     */
    public void movYPositive(int mov, int i) {
        int x = GameJava.enemies.get(i).getXpos();
        int y = GameJava.enemies.get(i).getYpos();
        String nextPosition = nextYPositive(mov, x, y);

        if (nextPosition.equals(Board.voidSquare)) {
            GameJava.board[GameJava.enemies.get(i).getYpos()][GameJava.enemies.get(i).getXpos()] = Board.voidSquare;
            GameJava.enemies.get(i).ypos -= mov;
            GameJava.board[GameJava.enemies.get(i).getYpos()][GameJava.enemies.get(i).getXpos()] = Board.Enemy;
        }
    }

    /**
     * Movera el enemigo (en el eje negativo verticalmente) en caso de que la
     * siguente casilla sea un voidSquare que es una casilla vacia
     *
     * @param mov numeros de casillas a moverse por el personaje
     */
    public void movYNegative(int mov, int i) {
        int x = GameJava.enemies.get(i).getXpos();
        int y = GameJava.enemies.get(i).getYpos();
        String nextPosition = nextYNegative(mov, x, y);
        if (nextPosition.equals(Board.voidSquare)) {
            GameJava.board[GameJava.enemies.get(i).getYpos()][GameJava.enemies.get(i).getXpos()] = Board.voidSquare;
            GameJava.enemies.get(i).ypos += mov;
            GameJava.board[GameJava.enemies.get(i).getYpos()][GameJava.enemies.get(i).getXpos()] = Board.Enemy;
        }
    }

    /*
        Mètodes nextPosition que ens retornen el contingut de la seguent casella, 
        en cas de que es surti de la graella ens retornarà el String = "DON'T EXISTS".
     */
    /**
     * Funcion que devuelve lo que hay en la casilla siguiente del personaje si
     * se mueve hacia la derecha
     *
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
     * Funcion que devuelve lo que hay en la casilla siguiente del personaje si
     * se mueve hacia la izquierda
     *
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
     * Funcion que devuelve lo que hay en la casilla siguiente del personaje si
     * se mueve hacia abajo
     *
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
     * Funcion que devuelve lo que hay en la casilla siguiente del personaje si
     * se mueve hacia arriba
     *
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
     * Funcion que movera aleatoriamente el personaje 1 casilla en cualquier
     * dirección , con una probabilidad de no moverse (esta probabilidad se
     * reduce a medida que augmenta la dificultad)
     *
     * @param mov numeros de casillas a moverse por el personaje
     */
    public void randomEnemyMovement(int mov, int i) {
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
                        GameJava.enemies.get(i).movYPositive(mov, i);
                        break;
                    case 2:
                        GameJava.enemies.get(i).movXPositive(mov, i);
                        break;
                    case 3:
                        GameJava.enemies.get(i).movYNegative(mov, i);
                        break;
                    case 4:
                        GameJava.enemies.get(i).movXNegative(mov, i);
                        break;
                }
                break;
        }
    }

    /**
     * Funcion que buscara en el tablero a un enemigo y le hará moverse con la
     * función randomEnemyMovement , y con la setEnemiDirection cambaiara la
     * vista del personaje
     */
    public static void moveEnemies() {
        for (int i = 0; i < GameJava.enemies.size(); i++) {
            if (GameJava.enemies.get(i).isAlive) {
                GameJava.enemies.get(i).randomEnemyMovement(1, i);
            }

        }
        setEnemiesDirection();
    }

    /**
     * Función que buscara todos los enemigos del array , despues comparará su
     * posición con la de tu y finalmente cambiara su vista para situarlo
     * mirando hacia ti
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
     * En referencia a la dificultat se determinaran unas caracteristicas para
     * luchar contra él
     */
    public static void setUpEnemy(int i) {
        GameJava.enemies.get(i).setHP(100);
        int attack;
        switch (GameJava.difficultSelection) {
            case 1:
                GameJava.enemies.get(i).setLVL(1);
                attack = Tools.random(10, 13);
                GameJava.enemies.get(i).setAttack(attack);
                break;
            case 2:
                GameJava.enemies.get(i).setLVL(2);
                attack = Tools.random(14, 17);
                GameJava.enemies.get(i).setAttack(attack);
                break;
            default:
                GameJava.enemies.get(i).setLVL(3);
                attack = Tools.random(18, 20);
                GameJava.enemies.get(i).setAttack(attack);
                break;
        }
    }

    public static void setEnemiesPostions() {
        int i = 0;
        while (i < GameJava.enemies.size()) {
            for (int j = 0; j < GameJava.board.length; j++) {
                for (int k = 0; k < GameJava.board[j].length; k++) {
                    if (GameJava.board[j][k].equals(Board.Enemy) || GameJava.board[j][k].equals(Board.EnemyLeft) || GameJava.board[j][k].equals(Board.EnemyRight)) {
                        GameJava.enemies.get(i).setXpos(k);
                        GameJava.enemies.get(i).setYpos(j);
                        i++;
                    }
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

    public int getHP() {
        return HP;
    }

    public int getAttack() {
        return attack;
    }

    public int getLVL() {
        return LVL;
    }

    public int getYpos() {
        return ypos;
    }

    public int getXpos() {
        return xpos;
    }

    public static int getMaxHP() {
        return maxHP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setLVL(int LVL) {
        this.LVL = LVL;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

}
