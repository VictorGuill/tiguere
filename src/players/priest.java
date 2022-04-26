package players;

/**
 *
 * @author mariagarriga
 */
public class priest extends Player {

    /**
     * CURAR AL PERSONAJE. Si el personaje está muerto, le revivimos. Si no,
     * subimos +10.
     */
    public static void HealCharacter() {
        if (Player.HP == 0) { //Si está muerto revive
            ReviveCharacter();
        } else {
            Player.HP += 10;
            if (Player.HP > Player.MAXHP) {
                Player.HP = Player.MAXHP;
            }
        }
    }

    /**
     * REVIVIR EL PERSONAJE. Dependiendo del nivel del personaje aumentamos más
     * o menos la vida al revivivr personaje.
     */
    public static void ReviveCharacter() {
        if (Player.LV >= 3) {
            Player.HP = Player.MAXHP;
        } else if (Player.LV == 2) {
            Player.HP = 200;
        } else {
            Player.HP = 100;
        }
    }
}

/*

Priests are  guys in  dark robes and a walking stick. 
Their special skill allows them to heal characters and even revive them. 
This healing restores HP and so does revival. 
It will be more or less effective depending on how powerful the priest is.

*/
