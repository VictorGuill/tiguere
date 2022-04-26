package players;

import outputs.Board;
import gamejava.GameJava;

public abstract class Player {

    public static int LV;
    public static int sackOfCoins;
    public static int xpos;
    public static int ypos;
    public static String direction;
    public static int HP;
    public static int DMG;
    public static int kills;
    public static int MAXHP = 300;

    public Player() {
    }

    //Métodos
    /**
     * Función que hace avanzar una posición en horizontal en caso de que la
     * casilla siguiente sea nula o contenga una moneda(al posicionarse por
     * encima se sumara 1 a las coins totales y se borrara)
     *
     * @param mov Cantidad de casillas a mover en la direccion.
     */
    public static void movXPositive(int mov) {
        String nextPosition = nextXPositive(mov);
        /*
            En caso de que la siguiente casilla sea un vacio o una moneda entrara, después cojera la casilla donde te encuentres actualmente y la 
            establecera como un voidSquare , sumará 1 en la xpos y mandara a saveCharacter el string right que pondra la espada del jugador mirando hacia la derecha
            Finalmente guardara el personaje en la nueva posición
         */
        if (nextPosition.equals(Board.voidSquare) || nextPosition.equals(Board.Coin)) {
            GameJava.board[Player.getYpos()][Player.getXpos()] = Board.voidSquare;
            Player.xpos += mov;
            direction = "right";
            Board.saveCharacter(direction);
            GameJava.board[Player.getYpos()][Player.getXpos()] = Board.Character;

            //En caso de que la siguiente casilla sea una moneda sumara 1 al recuento de monedas y al colocarse encima borrara la coin del mapa.
            if (nextPosition.equals(Board.Coin)) {
                gainCoins();
            }
        }

        // En caso de llegar a los limites derechos del mapa dara la vuelta a la posición de la espada utilizando la función saveCharacter del Board
        if (Player.xpos == GameJava.widthBoard - 1) {
            direction = "left";
            Board.saveCharacter(direction);
            GameJava.board[Player.getYpos()][Player.getXpos()] = Board.Character;
        }
    }

    /**
     * Función que hace retroceder una posición en horizontal en caso de que la
     * casilla siguiente sea nula o contenga una moneda(al posicionarse por
     * encima se sumara 1 a las coins totales y se borrara)
     *
     * @param mov Cantidad de casillas a mover en la direccion.
     */
    public static void movXNegative(int mov) {
        String nextPosition = nextXNegative(mov);

        // Funcionamiento similar a movXPositive solo que está vez hacia el lado izquiero del tablero, saveCharacter pondrá la espada mirando hacia la izquierda
        if (nextPosition.equals(Board.voidSquare) || nextPosition.equals(Board.Coin)) {
            GameJava.board[Player.getYpos()][Player.getXpos()] = Board.voidSquare;
            Player.xpos -= mov;
            direction = "left";
            Board.saveCharacter(direction);
            GameJava.board[Player.getYpos()][Player.getXpos()] = Board.Character;
            if (nextPosition.equals(Board.Coin)) {
                gainCoins();
            }
        }
        //En caso de llegar a los limites izquierdos del tablero cambiara la dirección de la espada hacia la derecha

        if (Player.xpos == 0) {
            direction = "right";
            Board.saveCharacter(direction);
            GameJava.board[Player.getYpos()][Player.getXpos()] = Board.Character;
        }
    }

    /**
     * Función que hace avanzar una posición en vertical en caso de que la
     * casilla siguiente sea nula o contenga una moneda(al posicionarse por
     * encima se sumara 1 a las coins totales y se borrara)
     *
     * @param mov Cantidad de casillas a mover en la direccion.
     */
    public static void movYPositive(int mov) {
        String nextPosition = nextYPositive(mov);

        //Funcionamiento similar a las dos funciones anteriores solo que esta vez en el eje de la y positivas (como es un array al subir una fila su numero desciende)
        //En este caso la dirección de la espada se queda igual que en el ultimo movimiento en horizontal
        if (nextPosition.equals(Board.voidSquare) || nextPosition.equals(Board.Coin)) {
            GameJava.board[Player.getYpos()][Player.getXpos()] = Board.voidSquare;
            Player.ypos -= mov;
            Board.saveCharacter(direction);
            GameJava.board[Player.getYpos()][Player.getXpos()] = Board.Character;
            if (nextPosition.equals(Board.Coin)) {
                gainCoins();
            }
        }
    }

    /**
     * Función que hace retroceder una posición en vertical en caso de que la
     * casilla siguiente sea nula o contenga una moneda(al posicionarse por
     * encima se sumara 1 a las coins totales y se borrara)
     *
     * @param mov Cantidad de casillas a mover en la direccion.
     */
    public static void movYNegative(int mov) {
        String nextPosition = nextYNegative(mov);

        //Funcionamiento similar a las dos funciones anteriores solo que esta vez en el eje de la y negativas (como es un array al bajar una fila su numero asciende)
        if (nextPosition.equals(Board.voidSquare) || nextPosition.equals(Board.Coin)) {
            GameJava.board[Player.getYpos()][Player.getXpos()] = Board.voidSquare;
            Player.ypos += mov;
            Board.saveCharacter(direction);
            GameJava.board[Player.getYpos()][Player.getXpos()] = Board.Character;
            if (nextPosition.equals(Board.Coin)) {
                gainCoins();
            }
        }
    }

    /**
     * Función que te retorna que hay en la siguiente posición en horizontal del
     * array basandose en la xpos y ypos del Player
     *
     * @param mov Cantidad de casillas a mover en la direccion.
     * @return nextPostition
     */
    public static String nextXPositive(int mov) {
        String nextPosition = "DON'T EXISTS";
        int xTemp = Player.getXpos() + mov;

        if (xTemp >= 0 && xTemp <= GameJava.widthBoard - 1) {
            nextPosition = GameJava.board[Player.getYpos()][xTemp];
        }
        return nextPosition;
    }

    /**
     * Función que te retorna que hay en la anterior posición en horizontal del
     * array basandose en la xpos y ypos del Player
     *
     * @param mov Cantidad de casillas a mover en la direccion.
     * @return nextPostition
     */
    public static String nextXNegative(int mov) {
        String nextPosition = "DON'T EXISTS";
        int xTemp = Player.getXpos() - mov;

        if (xTemp >= 0 && xTemp <= GameJava.widthBoard - 1) {
            nextPosition = GameJava.board[Player.getYpos()][xTemp];
        }
        return nextPosition;
    }

    /**
     * Función que te retorna que hay en la anterior posición en vertical del
     * array basandose en la xpos y ypos del Player
     *
     * @param mov Cantidad de casillas a mover en la direccion.
     * @return nextPostition
     */
    public static String nextYNegative(int mov) {
        String nextPosition = "DON'T EXISTS";
        int yTemp = Player.getYpos() + mov;

        if (yTemp >= 0 && yTemp <= GameJava.heightBoard - 1) {
            nextPosition = GameJava.board[yTemp][Player.getXpos()];
        }
        return nextPosition;
    }

    /**
     * Función que te retorna que hay en la siguiente posición en vertical del
     * array basandose en la xpos y ypos del Player
     *
     * @param mov Cantidad de casillas a mover en la direccion.
     * @return nextPostition
     */
    public static String nextYPositive(int mov) {
        String nextPosition = "DON'T EXISTS";
        int yTemp = Player.getYpos() - mov;

        if (yTemp >= 0 && yTemp <= GameJava.heightBoard - 1) {
            nextPosition = GameJava.board[yTemp][Player.getXpos()];
        }
        return nextPosition;
    }

    /**
     * Compara la siguiente casilla en relación a la xPos y yPos del Player, en
     * caso de que haya una moneda la borra del array y suma 1 al recuento de
     * monedas del jugador
     */
    public static void pickUpCoin() {
        boolean isCoin = false;

        int x = getXpos();
        int y = getYpos();

        String nextXPositive = nextXPositive(1);
        String nextXNegative = nextXNegative(1);
        String nextYPositive = nextYPositive(1);
        String nextYNegative = nextYNegative(1);

        do {
            if (nextXPositive.equals(Board.Coin)) {
                GameJava.board[y][x + 1] = Board.voidSquare;
                sackOfCoins++;
                isCoin = true;
            } else if (nextXNegative.equals(Board.Coin)) {
                GameJava.board[y][x - 1] = Board.voidSquare;
                sackOfCoins++;
                isCoin = true;
            } else if (nextYPositive.equals(Board.Coin)) {
                GameJava.board[y - 1][x] = Board.voidSquare;
                sackOfCoins++;
                isCoin = true;
            } else if (nextYNegative.equals(Board.Coin)) {
                GameJava.board[y + 1][x] = Board.voidSquare;
                sackOfCoins++;
                isCoin = true;
            } else {
                isCoin = true;
            }
        } while (!isCoin);

    }

    /**
     * Método que inicia un ataque en caso de que la siguiente casilla en
     * relación con el personaje sea uno de los posibles enemigos (estado normal
     * o cuando se encuentra mirando en alguna de las posiciones posibles
     */
    public static void basicAttack() {
        boolean isAttack;

        int x = getXpos();
        int y = getYpos();

        String nextXPositive = nextXPositive(1);
        String nextXNegative = nextXNegative(1);
        String nextYPositive = nextYPositive(1);
        String nextYNegative = nextYNegative(1);

        do {
            if (nextXPositive.equals(Board.EnemyRight) || nextXPositive.equals(Board.EnemyLeft) || nextXPositive.equals(Board.Enemy)) {
                GameJava.board[y][x + 1] = Board.voidSquare;
                kills++;
                isAttack = false;
            } else if (nextXNegative.equals(Board.EnemyRight) || nextXNegative.equals(Board.EnemyLeft) || nextXNegative.equals(Board.Enemy)) {
                GameJava.board[y][x - 1] = Board.voidSquare;
                kills++;
                isAttack = false;
            } else if (nextYPositive.equals(Board.EnemyRight) || nextYPositive.equals(Board.EnemyLeft) || nextYPositive.equals(Board.Enemy)) {
                GameJava.board[y - 1][x] = Board.voidSquare;
                kills++;
                isAttack = false;
            } else if (nextYNegative.equals(Board.EnemyRight) || nextYNegative.equals(Board.EnemyLeft) || nextYNegative.equals(Board.Enemy)) {
                GameJava.board[y + 1][x] = Board.voidSquare;
                kills++;
                isAttack = false;
            } else {
                isAttack = false;
            }
        } while (isAttack);
    }

    public static void gainCoins() {
        sackOfCoins += 1;
    }

    public static void loseCoins() {
        sackOfCoins -= 1;
    }

    //Getters i setters
    public static int getLV() {
        return LV;
    }

    public static int getSackOfCoins() {
        return sackOfCoins;
    }

    public static int getDMG() {
        return DMG;
    }

    public static int getXpos() {
        return xpos;
    }

    public static int getYpos() {
        return ypos;
    }

    public static int getHP() {
        return HP;
    }

    public static void setLV(int LV) {
        Player.LV = LV;
    }

    public static void setSackOfCoins(int sackOfCoins) {
        Player.sackOfCoins = sackOfCoins;
    }

    public static void setHP(int HP) {
        Player.HP = HP;
    }

    public static void setXpos(int xpos) {
        Player.xpos = xpos;
    }

    public static void setYpos(int ypos) {
        Player.ypos = ypos;
    }

}
