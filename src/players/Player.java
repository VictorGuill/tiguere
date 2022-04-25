
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

    //Mètodes
    
    //NULL_CELLS y mov son dos constantes, mov determina el numero de casillas que se podra mover el personaje y NULL_CELLS son los huecos del array que en teoria no hay nada.
    /*
        Metodes mov , augmenta una casella en cas de que la seguent casella sigui un "-"
        nullsCells = constant del main que indicara que es troba a les caselles nul·les.
        Variable direction es mandarà a la funció Board.Character, aquesta funció retornarà el String del jugador amb una espasa mirant en la direcció
        que es mou el jugador.
        En cas de que pasi per sobre d'una moneda aquesta s'esborrara i sumarà 1 al recompte de monedes del jugador
    */
    public static void movXPositive(int mov, String[][] board, Player[]playable, int character, int widthBoard, int heightBoard, String NULL_CELLS){
       String nextPosition = nextXPositive(board,playable,character,widthBoard,heightBoard,mov);
       if(nextPosition.equals(NULL_CELLS)||nextPosition.equals(Board.Coin)){
        board[playable[character].getYpos()][playable[character].getXpos()] = NULL_CELLS;
        Player.xpos += mov;
        direction = "right";
        Board.saveCharacter(direction);
        board[playable[character].getYpos()][playable[character].getXpos()] = Board.Character;
        if(nextPosition.equals(Board.Coin)){
            gainCoins();
        }
       }
       if(Player.xpos==widthBoard-1){
        direction = "left";
        Board.saveCharacter(direction);
        board[playable[character].getYpos()][playable[character].getXpos()] = Board.Character;
       }
    }
    public static void movXNegative(int mov, String[][] board, Player[]playable, int character, int widthBoard, int heigthBoard, String NULL_CELLS){
       String nextPosition = nextXNegative(board,playable,character,widthBoard,heigthBoard,mov);
       if(nextPosition.equals(NULL_CELLS)||nextPosition.equals(Board.Coin)){
        board[playable[character].getYpos()][playable[character].getXpos()] = NULL_CELLS;
        Player.xpos -= mov;
        direction = "left";
        Board.saveCharacter(direction);
        board[playable[character].getYpos()][playable[character].getXpos()] = Board.Character;
        if(nextPosition.equals(Board.Coin)){
            gainCoins();
        }
       }

       if(Player.xpos==0){
        direction = "right";
        Board.saveCharacter(direction);
        board[playable[character].getYpos()][playable[character].getXpos()] = Board.Character;
       }
    }
    public static void movYPositive(int mov, String[][] board, Player[]playable, int character, int widthBoard, int heigthBoard, String NULL_CELLS){
       String nextPosition = nextYPositive(board,playable,character,widthBoard,heigthBoard,mov);
       if(nextPosition.equals(NULL_CELLS)||nextPosition.equals(Board.Coin)){
         board[playable[character].getYpos()][playable[character].getXpos()] = NULL_CELLS;
        Player.ypos -= mov;
        Board.saveCharacter(direction);
        board[playable[character].getYpos()][playable[character].getXpos()] = Board.Character;
        if(nextPosition.equals(Board.Coin)){
            gainCoins();
        }
       }
    }
    public static void movYNegative(int mov, String[][] board, Player[]playable, int character, int widthBoard, int heigthBoard, String NULL_CELLS){
       String nextPosition = nextYNegative(board,playable,character,widthBoard,heigthBoard,mov);
       if(nextPosition.equals(NULL_CELLS)||nextPosition.equals(Board.Coin)){
         board[playable[character].getYpos()][playable[character].getXpos()] = NULL_CELLS;
        Player.ypos += mov;
        Board.saveCharacter(direction);
        board[playable[character].getYpos()][playable[character].getXpos()] = Board.Character;
        if(nextPosition.equals(Board.Coin)){
            gainCoins();
        }
       }  
    }
    
    /*
        Mètodes nextPosition que ens retornen el contingut de la seguent casella, 
        en cas de que es surti de la graella ens retornarà el String = "DON'T EXISTS".
    */
    public static String nextXPositive(String[][] board, Player[]playable, int character, int widthBoard, int heigthBoard, int mov){
        String nextPosition="DON'T EXISTS";
        int xTemp = playable[character].getXpos()+mov;
        
        if(xTemp>=0&&xTemp<=widthBoard-1){
            nextPosition = board[playable[character].getYpos()][xTemp];
        }
       return nextPosition;
    }
    public static String nextXNegative(String[][] board, Player[]playable, int character, int widthBoard, int heigthBoard, int mov){
        String nextPosition="DON'T EXISTS";
        int xTemp = playable[character].getXpos()-mov;
        
        if(xTemp>=0&&xTemp<=widthBoard-1){
            nextPosition = board[playable[character].getYpos()][xTemp];
        }
       return nextPosition;
    }
    public static String nextYNegative(String[][] board, Player[]playable, int character, int widthBoard, int heigthBoard, int mov){
        String nextPosition="DON'T EXISTS";
        int yTemp = playable[character].getYpos()+mov;
        
        if(yTemp>=0&&yTemp<=heigthBoard-1){
            nextPosition = board[yTemp][playable[character].getXpos()];
        }
       return nextPosition;
    }
    public static String nextYPositive(String[][] board, Player[]playable, int character, int widthBoard ,int heigthBoard, int mov){
        String nextPosition="DON'T EXISTS";
        int yTemp = playable[character].getYpos()-mov;
        
        if(yTemp>=0&&yTemp<=heigthBoard-1){
            nextPosition = board[yTemp][playable[character].getXpos()];
        }
       return nextPosition;
    }
    
    // pickUpCoin compara la seguent posició de l'array i en cas de que hi hagi una moneda l'esborra del mapa i suma 1 al recompte de monedes
    public static void pickUpCoin(String[][] board, Player[]playable, int character, int widthBoard, int heigthBoard, String NULL_CELLS) {
        boolean isCoin = false;
        
        int x = getXpos();
        int y = getYpos();
        
        String nextXPositive = nextXPositive(board,playable,character,widthBoard,heigthBoard,1);
        String nextXNegative = nextXNegative(board,playable,character,widthBoard,heigthBoard,1);
        String nextYPositive = nextYPositive(board,playable,character,widthBoard,heigthBoard,1);
        String nextYNegative = nextYNegative(board,playable,character,widthBoard,heigthBoard,1);
        
        do {
            if (nextXPositive.equals(NULL_CELLS)) {
                GameJava.board[y][x+1] = Board.voidSquare;
                sackOfCoins++;
                isCoin = true;
            } else if(nextXNegative.equals(NULL_CELLS)) {
                GameJava.board[y][x-1] = Board.voidSquare;
                sackOfCoins++;
                isCoin = true;
            } else if (nextYPositive.equals(NULL_CELLS)){
                GameJava.board[y-1][x] = Board.voidSquare;
                sackOfCoins++;
                isCoin = true;
            } else if(nextYNegative.equals(NULL_CELLS)) {
                GameJava.board[y+1][x] = Board.voidSquare;
                sackOfCoins++;
                isCoin = true;
            } else {
                isCoin = true;
            }
        }while(!isCoin);
        
    }
   
    ///Mètode basicAttack que retorna un dany a un enemic en base al nivell del personatge i el seu dany base.
    public static void basicAttack(String[][] board, Player[]playable,int character,int widthBoard ,int heigthBoard, String NULL_CELLS){
        boolean isAttack;
        
        int x = getXpos();
        int y = getYpos();
        
        String nextXPositive = nextXPositive(board,playable,character,widthBoard,heigthBoard,1);
        String nextXNegative = nextXNegative(board,playable,character,widthBoard,heigthBoard,1);
        String nextYPositive = nextYPositive(board,playable,character,widthBoard,heigthBoard,1);
        String nextYNegative = nextYNegative(board,playable,character,widthBoard,heigthBoard,1);
        
        do {
            if (nextXPositive.equals(NULL_CELLS)){
                GameJava.board[y][x+1] = Board.voidSquare;
                kills++;
                isAttack = false;
            } else if (nextXNegative.equals(NULL_CELLS)){
                GameJava.board[y][x-1] = Board.voidSquare;
                kills++;
                isAttack = false;
            } else if (nextYPositive.equals(NULL_CELLS)){
                GameJava.board[y-1][x] = Board.voidSquare;
                kills++;
                isAttack = false;
            } else if (nextYNegative.equals(NULL_CELLS)){
                GameJava.board[y+1][x] = Board.voidSquare;
                kills++;
                isAttack = false;
            } else {
                isAttack = false;
            }
        }  while (isAttack);
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
