
package gamejava;


public abstract class Player {
    protected int LV;
    protected int sackOfCoins;
    protected String icon;
    protected int xpos;
    protected int ypos;
    protected int HP;
    protected int DMG;

    public Player() {
    }

    //Mètodes
    
    //NULL_CELLS y mov son dos constantes, mov determina el numero de casillas que se podra mover el personaje y NULL_CELLS son los huecos del array que en teoria no hay nada.
    /*
        Metodes mov , augmenta una casella en cas de que la seguent casella sigui un "-"
        nullsCells = constant del main que indicara que es troba a les caselles nul·les.
    */
    public void movXPositive(int mov, String[][] board, Player[]playable,int character,int widthBoard ,int heigthBoard,String NULL_CELLS){
       String nextPosition = nextXPositive(board,playable,character,widthBoard,heigthBoard,mov);
       if(nextPosition.equals(NULL_CELLS)){
        board[playable[character].getYpos()][playable[character].getXpos()] = NULL_CELLS;
        this.xpos += mov;
        board[playable[character].getYpos()][playable[character].getXpos()] = playable[character].getIcon();
       }
    }
    public void movXNegative(int mov,String[][] board,Player[]playable,int character,int widthBoard ,int heigthBoard,String NULL_CELLS){
       String nextPosition = nextXNegative(board,playable,character,widthBoard,heigthBoard,mov);
       if(nextPosition.equals(NULL_CELLS)){
        board[playable[character].getYpos()][playable[character].getXpos()] = NULL_CELLS;
        this.xpos -= mov;
        board[playable[character].getYpos()][playable[character].getXpos()] = playable[character].getIcon();
       }
    }
    public void movYPositive(int mov,String[][] board,Player[]playable,int character,int widthBoard ,int heigthBoard,String NULL_CELLS){
       String nextPosition = nextYPositive(board,playable,character,widthBoard,heigthBoard,mov);
       if(nextPosition.equals(NULL_CELLS)){
         board[playable[character].getYpos()][playable[character].getXpos()] = NULL_CELLS;
        this.ypos -= mov;
        board[playable[character].getYpos()][playable[character].getXpos()] = playable[character].getIcon();
       }
    }
    public void movYNegative(int mov,String[][] board,Player[]playable,int character,int widthBoard ,int heigthBoard,String NULL_CELLS){
       String nextPosition = nextYNegative(board,playable,character,widthBoard,heigthBoard,mov);
       if(nextPosition.equals(NULL_CELLS)){
         board[playable[character].getYpos()][playable[character].getXpos()] = NULL_CELLS;
        this.ypos += mov;
        board[playable[character].getYpos()][playable[character].getXpos()] = playable[character].getIcon();
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
    
    public void gainCoins(){
        this.sackOfCoins += 1;
    }
    public void loseCoins(){
        this.sackOfCoins -= 1;
    }
 

    //Getters i setters
    public int getLV() {
        return LV;
    }
    public int getSackOfCoins() {
        return sackOfCoins;
    }

    public int getDMG() {
        return DMG;
    }

    public String getIcon() {
        return icon;
    }

    public int getXpos() {
        return xpos;
    }

    public int getYpos() {
        return ypos;
    }

    public int getHP() {
        return HP;
    }

    public void setLV(int LV) {
        this.LV = LV;
    }

    public void setSackOfCoins(int sackOfCoins) {
        this.sackOfCoins = sackOfCoins;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setXpos(int xpos) {
        this.xpos = xpos;
    }

    public void setYpos(int ypos) {
        this.ypos = ypos;
    }
    
}
