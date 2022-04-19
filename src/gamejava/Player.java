
package gamejava;


public abstract class Player {
    public static int LV;
    public static int sackOfCoins;
    public static String icon;
    public static int xpos;
    public static int ypos;
    public static int HP;
    public static int DMG;

    public Player() {
    }

    //Mètodes
    
    //NULL_CELLS y mov son dos constantes, mov determina el numero de casillas que se podra mover el personaje y NULL_CELLS son los huecos del array que en teoria no hay nada.
    /*
        Metodes mov , augmenta una casella en cas de que la seguent casella sigui un "-"
        nullsCells = constant del main que indicara que es troba a les caselles nul·les.
    */
    public static void movXPositive(int mov, String[][] board, Player[]playable,int character,int widthBoard ,int heigthBoard,String NULL_CELLS){
       String nextPosition = nextXPositive(board,playable,character,widthBoard,heigthBoard,mov);
       if(nextPosition.equals(NULL_CELLS)){
        board[playable[character].getYpos()][playable[character].getXpos()] = NULL_CELLS;
        Player.xpos += mov;
        board[playable[character].getYpos()][playable[character].getXpos()] = Board.Character;
       }
    }
    public static void movXNegative(int mov,String[][] board,Player[]playable,int character,int widthBoard ,int heigthBoard,String NULL_CELLS){
       String nextPosition = nextXNegative(board,playable,character,widthBoard,heigthBoard,mov);
       if(nextPosition.equals(NULL_CELLS)){
        board[playable[character].getYpos()][playable[character].getXpos()] = NULL_CELLS;
        Player.xpos -= mov;
        board[playable[character].getYpos()][playable[character].getXpos()] = Board.Character;
       }
    }
    public static void movYPositive(int mov,String[][] board,Player[]playable,int character,int widthBoard ,int heigthBoard,String NULL_CELLS){
       String nextPosition = nextYPositive(board,playable,character,widthBoard,heigthBoard,mov);
       if(nextPosition.equals(NULL_CELLS)){
         board[playable[character].getYpos()][playable[character].getXpos()] = NULL_CELLS;
        Player.ypos -= mov;
        board[playable[character].getYpos()][playable[character].getXpos()] = Board.Character;
       }
    }
    public static void movYNegative(int mov,String[][] board,Player[]playable,int character,int widthBoard ,int heigthBoard,String NULL_CELLS){
       String nextPosition = nextYNegative(board,playable,character,widthBoard,heigthBoard,mov);
       if(nextPosition.equals(NULL_CELLS)){
         board[playable[character].getYpos()][playable[character].getXpos()] = NULL_CELLS;
        Player.ypos += mov;
        board[playable[character].getYpos()][playable[character].getXpos()] = Board.Character;
       }
    }
    
    
    
    /*
        Mètodes nextPosition que ens retornen el contingut de la seguent casella, 
        en cas de que es surti de la graella ens retornarà el String = "DON'T EXISTS".
    */
    public static String nextXPositive(String[][] board,Player[]playable,int character,int widthBoard ,int heigthBoard, int mov){
        String nextPosition="DON'T EXISTS";
        int xTemp = playable[character].getXpos()+mov;
        
        if(xTemp>=0&&xTemp<=widthBoard-1){
            nextPosition =board[playable[character].getYpos()][xTemp];
        }
       return nextPosition;
    }
    public static String nextXNegative(String[][] board,Player[]playable,int character,int widthBoard ,int heigthBoard, int mov){
        String nextPosition="DON'T EXISTS";
        int xTemp = playable[character].getXpos()-mov;
        
        if(xTemp>=0&&xTemp<=widthBoard-1){
            nextPosition =board[playable[character].getYpos()][xTemp];
        }
       return nextPosition;
    }
    public static String nextYNegative(String[][] board,Player[]playable,int character,int widthBoard ,int heigthBoard, int mov){
        String nextPosition="DON'T EXISTS";
        int yTemp = playable[character].getYpos()+mov;
        
        if(yTemp>=0&&yTemp<=heigthBoard-1){
            nextPosition =board[yTemp][playable[character].getXpos()];
        }
       return nextPosition;
    }
    public static String nextYPositive(String[][] board,Player[]playable,int character,int widthBoard ,int heigthBoard, int mov){
        String nextPosition="DON'T EXISTS";
        int yTemp = playable[character].getYpos()-mov;
        
        if(yTemp>=0&&yTemp<=heigthBoard-1){
            nextPosition =board[yTemp][playable[character].getXpos()];
        }
       return nextPosition;
    }
    
    ///Mètode basicAttack que retorna un dany a un enemic en base al nivell del personatge i el seu dany base.
    public static float basicAttack(Player[]playable,int character){
        return playable[character].getDMG()* (playable[character].getLV()/10);
    }
    
    public static void gainCoins(){
        sackOfCoins += 1;
    }
    public static void loseCoins(){
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

    public static String getIcon() {
        return icon;
    }

    public static int getXpos() {
        return xpos;
    }

    public static int getYpos() {
        return ypos;
    }

    public  static int getHP() {
        return HP;
    }

    public  static void setLV(int LV) {
        Player.LV = LV;
    }

    public static void setSackOfCoins(int sackOfCoins) {
        Player.sackOfCoins = sackOfCoins;
    }

    public static void setIcon(String icon) {
        Player.icon = icon;
    }

    public  static void setHP(int HP) {
        Player.HP = HP;
    }

    public  static void setXpos(int xpos) {
        Player.xpos = xpos;
    }

    public  static void setYpos(int ypos) {
        Player.ypos = ypos;
    }
    
}