package gamejava;

/**
 *
 * @author mariagarriga
 */
public class magician extends Player{
    //spells list
    //Create potion
    
    //Move +2 @override
    public static int[][] MoveAround(int[][] position) {
        for (int i = 0; i < position.length; i++){
            for (int j = 0; j < position[i].length; j++){
               position[i][j] += 2; 
            }
        }
        return position;
    }
}
/*
Magicians have a range of spells they can use, but they cannot carry weapons. 
Their  special skill allows them to mix potions from their existing inventory 
in order to create new ones. 
They also have a driving motion skill which allows them to move forward a certain distance. 


Attack enemy //atac especial

Move around

Take a potion //personatge

Combine potions

Pick up object //personatge

Buy object //personatge

Sell object //personatge

*/