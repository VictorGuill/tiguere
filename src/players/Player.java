package players;

import outputs.Board;
import gamejava.GameJava;
import static gamejava.GameJava.INPUT;
import static gamejava.GameJava.INPUT_RATE;
import gamejava.Play;
import java.util.concurrent.TimeUnit;
import outputs.Screens;

public abstract class Player {

    public static int LV;
    public static int sackOfCoins;
    public static int xpos;
    public static int ypos;
    public static String direction;
    public static int HP;
    public static int DMG;
    public static int kills;
    public static int MAXHP = 100;

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
        if (nextPosition.equals(Board.voidSquare) || nextPosition.equals(Board.Coin)||nextPosition.equals(Board.PotionHP) && Player.HP < Player.MAXHP||nextPosition.equals (Board.visiblePotion)) {
            GameJava.board[Player.getYpos()][Player.getXpos()] = Board.voidSquare;
            Player.xpos += mov;
            direction = "right";
            Board.saveCharacter(direction);
            GameJava.board[Player.getYpos()][Player.getXpos()] = Board.Character;

            nextObject(nextPosition);
        }
        wallR_Direction();
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
        if (nextPosition.equals(Board.voidSquare) || nextPosition.equals(Board.Coin)||nextPosition.equals(Board.PotionHP) && Player.HP < Player.MAXHP||nextPosition.equals (Board.visiblePotion)) {
            GameJava.board[Player.getYpos()][Player.getXpos()] = Board.voidSquare;
            Player.xpos -= mov;
            direction = "left";
            Board.saveCharacter(direction);
            GameJava.board[Player.getYpos()][Player.getXpos()] = Board.Character;
            
            nextObject(nextPosition);

        }
        wallL_Direction();
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
        if (nextPosition.equals(Board.voidSquare) || nextPosition.equals(Board.Coin)||nextPosition.equals(Board.PotionHP) && Player.HP < Player.MAXHP||nextPosition.equals (Board.visiblePotion)) {
            GameJava.board[Player.getYpos()][Player.getXpos()] = Board.voidSquare;
            Player.ypos -= mov;
            Board.saveCharacter(direction);
            GameJava.board[Player.getYpos()][Player.getXpos()] = Board.Character;
            
            nextObject(nextPosition);
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
        if (nextPosition.equals(Board.voidSquare) || nextPosition.equals(Board.Coin)||nextPosition.equals(Board.PotionHP) && Player.HP < Player.MAXHP||nextPosition.equals (Board.visiblePotion)) {
            GameJava.board[Player.getYpos()][Player.getXpos()] = Board.voidSquare;
            Player.ypos += mov;
            Board.saveCharacter(direction);
            GameJava.board[Player.getYpos()][Player.getXpos()] = Board.Character;
            
            nextObject(nextPosition);
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
     * Funcion que ejecutara diferentes métodos en relacion al objeto que recojes de la siguiente posición
     * @param nextPosition que objeto hay en la siguiente casilla 
     */
    
    public static void nextObject(String nextPosition){
             if (nextPosition.equals(Board.Coin)) {
                gainCoins();
            }
            
            else if (nextPosition.equals(Board.PotionHP)){
                Player.HP += 50;
                
                if(Player.HP > Player.MAXHP){
                   Player.HP = MAXHP;
                }   
            }
        
            else if (nextPosition.equals(Board.visiblePotion)){
                Board.printDistance += 1; 
            }
    }
    
    /**
     * Función que en caso de llegar al lado derecho del mapa cambiara la dirección de la espada
     */
    public static void wallR_Direction(){
        if (Player.xpos == GameJava.widthBoard - 1) {
            direction = "left";
            Board.saveCharacter(direction);
            GameJava.board[Player.getYpos()][Player.getXpos()] = Board.Character;
        }
    }
    /**
     * Función que en caso de llegar a los limites derechos del tablero cambiara la dirección de la espada hacia la izquierda
     */
    
    public static void wallL_Direction(){
        if (Player.xpos == 0) {
            direction = "right";
            Board.saveCharacter(direction);
            GameJava.board[Player.getYpos()][Player.getXpos()] = Board.Character;
        }
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
    public static void basicAttack() throws InterruptedException {
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
                whileAttack();
                isAttack = false;
            } else if (nextXNegative.equals(Board.EnemyRight) || nextXNegative.equals(Board.EnemyLeft) || nextXNegative.equals(Board.Enemy)) {
                GameJava.board[y][x - 1] = Board.voidSquare;
                whileAttack();
                isAttack = false;
            } else if (nextYPositive.equals(Board.EnemyRight) || nextYPositive.equals(Board.EnemyLeft) || nextYPositive.equals(Board.Enemy)) {
                GameJava.board[y - 1][x] = Board.voidSquare;
                whileAttack();
                isAttack = false;
            } else if (nextYNegative.equals(Board.EnemyRight) || nextYNegative.equals(Board.EnemyLeft) || nextYNegative.equals(Board.Enemy)) {
                GameJava.board[y + 1][x] = Board.voidSquare;
                whileAttack();
                isAttack = false;
            } else {
                isAttack = false;
            }
        } while (isAttack);
    }

    /**
     * Método que se inicia cuando se ataca a un enemigo. Inicializa el enemigo
     * e imprime el tablero de combate. El combate funciona por turnos y el
     * personaje se cura al 100, sube de nivel y aumenta su daño en 2 al matar a
     * un enemigo.
     *
     * @throws InterruptedException
     */
    public static void whileAttack() throws InterruptedException {
        boolean stillCombat = true;
        INPUT = "";
        boolean playerTurn = true;
        Enemies e = new Enemies();
        Enemies.setUpEnemy(e);
        Screens.printRing(Board.Character, Board.Enemy, playerTurn);
        do {
            if (e.HP > 0 && Player.HP > 0) {
                if (playerTurn) {
                    switch (INPUT) {
                        case "1":
                            playerTurn = false;
                            Enemies.HP -= Player.DMG;
                            if (Enemies.HP <= 0) {
                                Enemies.HP = 0;
                                kills++;
                                LV++;
                                DMG += 2;
                                Play.numBattles++; //sumamos 1 batalla
                                priest.numHeals = 0; //reseteamos que se pueda curar
       
                            }
                            Screens.printRing(Board.Character, Board.Enemy, playerTurn);
                            INPUT = "";
                            TimeUnit.MILLISECONDS.sleep(20000 / INPUT_RATE);
                            break;
                    }
                } else {
                    Player.HP -= Enemies.attack;
                    if (Player.HP < 0) {
                        Player.HP = 0;
                        Play.numBattles++; //sumamos 1 batalla
                        priest.numHeals = 0; //reseteamos que se pueda curar
                    }
                    playerTurn = true;
                    Screens.printRing(Board.Character, Board.Enemy, playerTurn);
                    TimeUnit.MILLISECONDS.sleep(20000 / INPUT_RATE);
                }
            } else {
                stillCombat = false;
                Enemies.setUpEnemy(e);
            }
            TimeUnit.MILLISECONDS.sleep(10000 / INPUT_RATE);
        } while (stillCombat);
        INPUT = "";
        Board.printBoard();

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
