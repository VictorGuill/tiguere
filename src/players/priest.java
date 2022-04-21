package players;

/**
 *
 * @author mariagarriga
 */
public class priest extends Player{
    // heal character/revive (+10)
    // level of the priest heal more effective or not (+30, +50, +70 de vida, per nivell)
    public static int MAXLIFE = 300;

    public static int HealCharacter(int HP,int level) {
        if (HP <= 0) { //if charachter dead, revive
            return ReviveCharacter(HP, level);
        } else {
            HP += 100;
            if (HP > MAXLIFE){
                HP = MAXLIFE;
            }
            return HP; //sumem 100 punts de vida     
        }
    }
    
    //REVIVIM AL PERSONANTGE
    /*fer un if perquè depenen del nivell del priest pugi més la vida o no, 
    si está a un nivell alt si que maxim pero sino pujarà menys nivell de vida */
    public static int ReviveCharacter(int HP, int level) {
        if (level >= 3) {
            return HP = MAXLIFE;
        } else if (level == 2) {
            return HP = 200;
        } else {
            return HP = 100;
        }
    }
}

/*
Priests are  guys in  dark robes and a walking stick. 
Their special skill allows them to heal characters and even revive them. 
This healing restores HP and so does revival. 
It will be more or less effective depending on how powerful the priest is.


Attack enemy

Take a potion

Heal character

Revive character

*/