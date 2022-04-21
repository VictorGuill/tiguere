package players;

import static gamejava.GameJava.character;

/**
 *
 * @author mariagarriga
 */
public class priest extends Player{
    // heal character/revive (+10)
    // level of the priest heal more effective or not (+30, +50, +70 de vida, per nivell)
    public static int LV = 1;
    
    public static void HealCharacter(Player[] playable, int character) {
        if (playable[character].HP <= 0) { //if charachter dead, revive
            ReviveCharacter(playable, character);
        } else {
            if (playable[character].HP > MAXHP){
                playable[character].HP = MAXHP;
            } else { 
                playable[character].HP += 10; //sumem 10 punts de vida 
            }    
        }
    }
    
    //REVIVIM AL PERSONANTGE
    /*fer un if perquè depenen del nivell del priest pugi més la vida o no, 
    si está a un nivell alt si que maxim pero sino pujarà menys nivell de vida */
    public static void ReviveCharacter(Player[] playable, int character) {
        if (priest.LV >= 3) {
            playable[character].HP = MAXHP;
        } else if (priest.LV == 2) {
            playable[character].HP = 20;
        } else {
            playable[character].HP = 10;
        }
    }
}

/*

Priests are  guys in  dark robes and a walking stick. 
Their special skill allows them to heal characters and even revive them. 
This healing restores HP and so does revival. 
It will be more or less effective depending on how powerful the priest is.

Attack enemy //atacEspecial (?)

Heal character

Revive character

*/