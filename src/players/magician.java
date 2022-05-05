package players;

import outputs.Board;
import gamejava.GameJava;
import java.util.concurrent.TimeUnit;

public class magician extends Player {

    /**
     * HABILIDADES ESPECIALES DE MOVIMIENTO.
     *
     * Cuando usamos el mago tenemos la posibilidad de movernos dos casillas a
     * la vez.
     */
    public static void motionSkill(int mov) throws InterruptedException {
        GameJava.INPUT = "";
        boolean motionSkills = true;

        do {
            switch (GameJava.INPUT) {
                case "up":
                    movYPositive(mov);
                    Enemies.moveEnemies();
                    Board.printBoard();
                    break;
                case "down":
                    movYNegative(mov);
                    Enemies.moveEnemies();
                    Board.printBoard();
                    break;
                case "left":
                    movXNegative(mov);
                    Enemies.moveEnemies();
                    Board.printBoard();
                    break;
                case "right":
                    movXPositive(mov);
                    Enemies.moveEnemies();
                    Board.printBoard();
                    break;
                case "1":
                    //Player.basicAttack();
                    Enemies.moveEnemies();
                    break;
                case "4":
                    motionSkills = false;
                    break;
            }
            GameJava.INPUT = "";
            Enemies.setEnemiesDirection();
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
