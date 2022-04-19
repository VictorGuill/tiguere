/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamejava;

import static gamejava.StartSetup.colorUI;

/**
 *
 * @author Paxsem
 */
public class Board {
    public static String HP[] = new String[10];
    public static boolean firstPrint = true;
    public static String Character,
                        voidSquare = "░░░░░";
    public static void printBoard(int wBoard, int hBoard) {
        GameJava.board = new String[hBoard][wBoard];
        int coins = randomCoin();
        Character = saveCharacter();
        if (firstPrint) {
			setMap(wBoard,hBoard);
			firstPrint = false;
		}
        
        for (int i = 0; i < hBoard + 2; i++) {
            if (i == 0){
                for(int j = 0; j < (wBoard*5) + 20; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(StartSetup.colorUI, "", "╔"));
                    } else if (j == (wBoard*5) + 1){
                        System.out.print(Tools.print(StartSetup.colorUI, "", "╗"));
                    } else if (j > (wBoard*5) + 2 && j != (wBoard*5) + 19){
                        System.out.print(Tools.print(StartSetup.colorUI, "", "═"));
                    } else if (j == (wBoard*5) + 19){
                        System.out.print(Tools.print(StartSetup.colorUI, "", "╗"));
                    }else {
                        System.out.print(Tools.print(StartSetup.colorUI, "", "═"));
                    }
                }
            } else if (i == hBoard + 1){
                for(int j = 0; j < (wBoard*5) + 2; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(StartSetup.colorUI, "", "╚"));
                    } else if (j == (wBoard*5) + 1){
                        System.out.print(Tools.print(StartSetup.colorUI, "", "╝"));
                    } else {
                        System.out.print(Tools.print(StartSetup.colorUI, "", "═"));
                    }
                }
            } else if (i > 0 && i < 5){
                switch (i) {
                    case 2:
                        for (int j = 0; j < wBoard + 20; j++){
                            if (j == 0) {
                                System.out.print(Tools.print(colorUI, "", "║"));
                            }else if (j < wBoard + 1) {
                                printPosition(j-1,i-1);
                            }else if(j == (wBoard + 1)) {
                                System.out.print(Tools.print(colorUI, "", "║"));
                            } else if (j == wBoard + 19){
                                System.out.print(Tools.print(colorUI, "", "║"));
                            } else if (j == wBoard + 3) {
                                System.out.print(GameJava.numCoins);
                            }else if (j == wBoard + 5) {
                                System.out.print("C");
                            }else if (j == wBoard + 6) {
                                System.out.print("O");
                            }else if (j == wBoard + 7) {
                                System.out.print("I");
                            }else if (j == wBoard + 8) {
                                System.out.print("N");
                            }else if (j == wBoard + 9) {
                                System.out.print("S");
                            }
                            else {
                                System.out.print(" ");
                            }
                        }
                        break;
                    case 3:
                        for (int j = 0; j < wBoard + 20; j++){
                            if (j == 0) {
                                System.out.print(Tools.print(colorUI, "", "║"));
                            }else if (j < wBoard + 1) {
                                printPosition(j-1,i-1);
                            }else if(j == (wBoard + 1)) {
                                System.out.print(Tools.print(colorUI, "", "║"));
                            } else if (j == wBoard + 19){
                                System.out.print(Tools.print(colorUI, "", "║"));
                            } else if (j == wBoard + 3) {
                                System.out.print(GameJava.numEnemies);
                            }else if (j == wBoard + 5) {
                                System.out.print("E");
                            }else if (j == wBoard + 6) {
                                System.out.print("N");
                            }else if (j == wBoard + 7) {
                                System.out.print("E");
                            }else if (j == wBoard + 8) {
                                System.out.print("M");
                            }else if (j == wBoard + 9) {
                                System.out.print("I");
                            }else if (j == wBoard + 10) {
                                System.out.print("E");
                            }else if (j == wBoard + 11) {
                                System.out.print("S");
                            }else {
                                System.out.print(" ");
                            }
                        }
                        break;
                    default:
                        for (int j = 0; j < wBoard + 20; j++){
                            if (j == 0) {
                                System.out.print(Tools.print(colorUI, "", "║"));
                            }else if (j < wBoard + 1) {
                                printPosition(j-1,i-1);
                                
                            }else if(j == (wBoard + 1)) {
                                System.out.print(Tools.print(colorUI, "", "║"));
                            } else if (j == wBoard + 19){
                                System.out.print(Tools.print(colorUI, "", "║"));
                            } else {
                                System.out.print(" ");
                            }
                        }       break;
                }
                
            }else if (i == 5) {
                for (int j = 0; j < wBoard + 20; j++){
                    if (j == 0) {
                        System.out.print(Tools.print(colorUI, "", "║"));
                    }else if (j < wBoard + 1) {
                        printPosition(j-1,i-1);
                    }else if(j == (wBoard + 1)) {
                        System.out.print(Tools.print(colorUI, "", "║"));
                    }else if (j < wBoard + 19){
                        System.out.print(Tools.print(StartSetup.colorUI, "", "═"));
                    } else {
                        System.out.print(Tools.print(StartSetup.colorUI, "", "╝"));
                    }
                }
            }else {
                for (int j = 0; j < wBoard + 2; j++) {
                    if (j == 0 || j == wBoard + 1) {
                        System.out.print(Tools.print(colorUI, "", "║"));
                    } else {
                        printPosition(j-1,i-1);
                    }
                }
                
            }
            System.out.println("");
        }
        
        
        
        
    }
    
    public static void showMenu() {
        System.out.println("1 - ATACK           2 - CHANGE CHARACTER  \n"
                            + "3 - PICK UP OBJECT  4 - EXIT ");
    }

    
    public static String saveCharacter() {
        if (GameJava.character == 1) {
            Character = ("░░"+ GameJava.CHAR_GUERRERO + "░░");
        } else if (GameJava.character == 2){
            Character =("░░"+ GameJava.CHAR_MAGO + "░░");
        } else {
            Character =("░░"+ GameJava.CHAR_SACERDOTE + "░░");
        }
        return Character;
    }
    
    public static void printPosition(int row, int column) {
        System.out.print(GameJava.board[column][row]);
    } 
    
    public static int randomCoin() {
        if (GameJava.numEnemies == 1 || GameJava.numEnemies == 2) {
            GameJava.numCoins = Tools.random(1,2);
        } else if (GameJava.numEnemies == 3 || GameJava.numEnemies == 4) {
            GameJava.numCoins = Tools.random(3,4);
        } else {
            GameJava.numCoins = Tools.random(5, 6);
        }
        return GameJava.numCoins;
    }
    
    public static void randomPositions(int number,int rowLimit, int columnLimit, String icon) {
        int counter = 0;
        int rowPosition, columnPosition;
        do {
            rowPosition = Tools.random(0, rowLimit);
            columnPosition = Tools.random(0, columnLimit);
            if (GameJava.board[columnPosition][rowPosition].equals(voidSquare)){
                GameJava.board[columnPosition][rowPosition] = icon;
                
                ++counter;
            }
            
        }while(counter < number);    
    }
    
    // method check if string is null or empty
    public static boolean isNullEmpty(String str) {

        // check if string is null
        if (str == null) {
            return true;
        }

        // check if string is empty
        else return str.isEmpty();
    }
    
    public static void setMap(int width, int height) {
        
            for(int i = 0; i < height; i++) {
                for(int j = 0; j < width; j++){
                    if (isNullEmpty(GameJava.board[i][j])) {
                        GameJava.board[i][j] = voidSquare;
                    }
                }
            }
            randomPositions(GameJava.numCoins,height-1,width-1,("░░" + GameJava.CHAR_COIN + "░░"));
            randomPositions(GameJava.numEnemies,height-1,width-1,("░░" + GameJava.CHAR_ENEMY + "░░"));
            GameJava.board[0][0] = Character;
        
    }
    
    
}

