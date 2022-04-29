package players;

public class priest extends Player {

    public static int numHeals = 0;

    /**
     * CURAR AL PERSONAJE. Si el personaje está muerto, le revivimos. Si no,
     * subimos +10.
     */
    public static void HealCharacter() throws InterruptedException {
        if (numHeals < 2) {
            numHeals++;
            if (Player.HP == 0) { //Si está muerto revive
                ReviveCharacter();
            } else {
                Player.HP += 10;
                if (Player.HP > Player.MAXHP) {
                    Player.HP = Player.MAXHP;
                }
            }
        }
    }

    /**
     * REVIVIR EL PERSONAJE. Dependiendo del nivel del personaje aumentamos más
     * o menos la vida al revivivr personaje.
     */
    public static void ReviveCharacter() {
        if (Player.LV >= 5) {
            Player.HP = Player.MAXHP;
        } else if (Player.LV == 3 || Player.LV == 4) {
            Player.HP += Player.MAXHP * 0.7;
        } else {
            Player.HP += Player.MAXHP * 0.5;
        }
    }
}

/*

Priests are  guys in  dark robes and a walking stick. 
Their special skill allows them to heal characters and even revive them. 
This healing restores HP and so does revival. 
It will be more or less effective depending on how powerful the priest is.

 */
