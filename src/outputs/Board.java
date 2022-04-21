/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package outputs;

import gamejava.GameJava;
import utilities.Tools;
import players.Player;
import static outputs.Screens.colorUI;

/**
 *
 * @author Paxsem
 */
public class Board {

    public static String HP[] = new String[10];
    public static boolean firstPrint = true;
    public static boolean firstCharacter = true;
    public static String Character,
            voidSquare = "░░░",
            voidCharacterSides = "░";
    

    public static void printBoard(int wBoard, int hBoard) {
    int hueco = voidSquare.length();
        
        
        Tools.clearConsole();
        
        Character = saveCharacter();
        if (firstPrint) {
            GameJava.numCoins= randomCoin();
            GameJava.board = new String[hBoard][wBoard];
            setMap(wBoard, hBoard);
            firstPrint = false;
        }

        for (int i = 0; i < hBoard + 2; i++) {
            if (i == 0) {
                for (int j = 0; j < (wBoard * hueco) + 20; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(Screens.colorUI, "", "╔"));
                    } else if (j == (wBoard * hueco) + 1) {
                        System.out.print(Tools.print(Screens.colorUI, "", "╦"));
                    } else if (j == (wBoard * hueco) + 19) {
                        System.out.print(Tools.print(Screens.colorUI, "", "╗"));
                    } else {
                        System.out.print(Tools.print(Screens.colorUI, "", "═"));
                    }
                }
            } else if (i == hBoard + 1) {
                for (int j = 0; j < (wBoard * hueco) + 2; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(Screens.colorUI, "", "╚"));
                    } else if (j == (wBoard * hueco) + 1) {
                        System.out.print(Tools.print(Screens.colorUI, "", "╝"));
                    } else {
                        System.out.print(Tools.print(Screens.colorUI, "", "═"));
                    }
                }
            } else if (i > 0 && i < 5) {
                switch (i) {
                    case 2:
                        for (int j = 0; j < wBoard + 20; j++) {
                            if (j == 0) {
                                System.out.print(Tools.print(colorUI, "", "║"));
                            } else if (j < wBoard + 1) {
                                printPosition(j - 1, i - 1);
                            } else if (j == (wBoard + 1)) {
                                System.out.print(Tools.print(colorUI, "", "║"));
                            } else if (j == wBoard + 19) {
                                System.out.print(Tools.print(colorUI, "", "║"));
                            } else if (j == wBoard + 3) {
                                System.out.print(Player.sackOfCoins);
                            } else if (j == wBoard + 5) {
                                System.out.print("C");
                            } else if (j == wBoard + 6) {
                                System.out.print("O");
                            } else if (j == wBoard + 7) {
                                System.out.print("I");
                            } else if (j == wBoard + 8) {
                                System.out.print("N");
                            } else if (j == wBoard + 9) {
                                System.out.print("S");
                            } else {
                                System.out.print(" ");
                            }
                        }
                        break;
                    case 3:
                        for (int j = 0; j < wBoard + 20; j++) {
                            if (j == 0) {
                                System.out.print(Tools.print(colorUI, "", "║"));
                            } else if (j < wBoard + 1) {
                                printPosition(j - 1, i - 1);
                            } else if (j == (wBoard + 1)) {
                                System.out.print(Tools.print(colorUI, "", "║"));
                            } else if (j == wBoard + 19) {
                                System.out.print(Tools.print(colorUI, "", "║"));
                            } else if (j == wBoard + 3) {
                                System.out.print(Player.kills);
                            } else if (j == wBoard + 5) {
                                System.out.print("K");
                            } else if (j == wBoard + 6) {
                                System.out.print("I");
                            } else if (j == wBoard + 7) {
                                System.out.print("L");
                            } else if (j == wBoard + 8) {
                                System.out.print("L");
                            } else if (j == wBoard + 9) {
                                System.out.print("S");
                            }  else {
                                System.out.print(" ");
                            }
                        }
                        break;
                    default:
                        for (int j = 0; j < wBoard + 20; j++) {
                            if (j == 0) {
                                System.out.print(Tools.print(colorUI, "", "║"));
                            } else if (j < wBoard + 1) {
                                printPosition(j - 1, i - 1);

                            } else if (j == (wBoard + 1)) {
                                System.out.print(Tools.print(colorUI, "", "║"));
                            } else if (j == wBoard + 19) {
                                System.out.print(Tools.print(colorUI, "", "║"));
                            } else {
                                System.out.print(" ");
                            }
                        }
                        break;
                }

            } else if (i == 5) {
                for (int j = 0; j < wBoard + 20; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(colorUI, "", "║"));
                    } else if (j < wBoard + 1) {
                        printPosition(j - 1, i - 1);
                    } else if (j == (wBoard + 1)) {
                        System.out.print(Tools.print(colorUI, "", "╠"));
                    } else if (j < wBoard + 19) {
                        System.out.print(Tools.print(Screens.colorUI, "", "═"));
                    } else {
                        System.out.print(Tools.print(Screens.colorUI, "", "╣"));
                    }
                }
            } else if (i > 5 && i < 10){
                switch (i) {
                    case 6:
                        for (int j = 0; j < wBoard + 20; j++) {
                            if (j == 0) {
                                System.out.print(Tools.print(colorUI, "", "║"));
                            } else if (j < wBoard + 1) {
                                printPosition(j - 1, i - 1);

                            } else if (j == (wBoard + 1)) {
                                System.out.print(Tools.print(colorUI, "", "║"));
                            } else if (j == wBoard + 19) {
                                System.out.print(Tools.print(colorUI, "", "║"));
                            } else {
                                System.out.print(" ");
                            }
                        }
                        break;
                    case 7:
                        for (int j = 0; j < wBoard + 18; j++) {
                            if (j == 0) {
                                System.out.print(Tools.print(colorUI, "", "║"));
                            } else if (j < wBoard + 1) {
                                printPosition(j - 1, i - 1);

                            } else if (j == (wBoard + 1)) {
                                System.out.print(Tools.print(colorUI, "", "║"));
                            } else if (j == wBoard + 17) {
                                System.out.print(Tools.print(colorUI, "", "║"));
                            }  else if (j == wBoard + 3) {
                                System.out.print("H");
                            } else if (j == wBoard + 4) {
                                System.out.print("P");
                            } else if (j == wBoard + 6) {
                                System.out.print(String.valueOf(Player.HP));
                            } else {
                                System.out.print(" ");
                            }
                        }
                        break;
                    case 8:
                        for (int j = 0; j < wBoard + 20; j++) {
                            if (j == 0) {
                                System.out.print(Tools.print(colorUI, "", "║"));
                            } else if (j < wBoard + 1) {
                                printPosition(j - 1, i - 1);

                            } else if (j == (wBoard + 1)) {
                                System.out.print(Tools.print(colorUI, "", "║"));
                            } else if (j == wBoard + 19) {
                                System.out.print(Tools.print(colorUI, "", "║"));
                            }  else if (j == wBoard + 3) {
                                System.out.print("L");
                            } else if (j == wBoard + 4) {
                                System.out.print("V");
                            } else if (j == wBoard + 5) {
                                System.out.print("L");
                            } else if (j == wBoard + 7) {
                                System.out.print(String.valueOf(Player.LV));
                            }  else {
                                System.out.print(" ");
                            }
                        }
                        break;
                    case 9:
                        for (int j = 0; j < wBoard + 20; j++) {
                            if (j == 0) {
                                System.out.print(Tools.print(colorUI, "", "║"));
                            } else if (j < wBoard + 1) {
                                printPosition(j - 1, i - 1);

                            } else if (j == (wBoard + 1)) {
                                System.out.print(Tools.print(colorUI, "", "║"));
                            } else if (j == wBoard + 19) {
                                System.out.print(Tools.print(colorUI, "", "║"));
                            } else {
                                System.out.print(" ");
                            }
                        }
                        break;
                        
                    
                }
            } else if (i == 10) {
                for (int j = 0; j < wBoard + 20; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(colorUI, "", "║"));
                    } else if (j < wBoard + 1) {
                        printPosition(j - 1, i - 1);
                    } else if (j == (wBoard + 1)) {
                        System.out.print(Tools.print(colorUI, "", "╠"));
                    } else if (j < wBoard + 19) {
                        System.out.print(Tools.print(Screens.colorUI, "", "═"));
                    } else {
                        System.out.print(Tools.print(Screens.colorUI, "", "╝"));
                    }
                }
            }else {
                for (int j = 0; j < wBoard + 2; j++) {
                    if (j == 0 || j == wBoard + 1) {
                        System.out.print(Tools.print(colorUI, "", "║"));
                    } else {
                        printPosition(j - 1, i - 1);
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
        if (GameJava.character == 0) {
            Character = (" " + GameJava.CHAR_GUERRERO + " ");
        } else if (GameJava.character == 1) {
            Character = (" " + GameJava.CHAR_MAGO + " ");
        } else {
            Character = (" " + GameJava.CHAR_SACERDOTE + " ");
        }
        return Character;
    }

    public static void printPosition(int row, int column) {
        System.out.print(GameJava.board[column][row]);
    }

    public static int randomCoin() {
        if (GameJava.numEnemies == 1 || GameJava.numEnemies == 2) {
            GameJava.numCoins = Tools.random(1, 2);
        } else if (GameJava.numEnemies == 3 || GameJava.numEnemies == 4) {
            GameJava.numCoins = Tools.random(3, 4);
        } else {
            GameJava.numCoins = Tools.random(5, 6);
        }
        return GameJava.numCoins;
    }

    public static void randomPositions(int number, int rowLimit, int columnLimit, String icon) {
        int counter = 0;
        int rowPosition, columnPosition;
        do {
            rowPosition = Tools.random(0, rowLimit);
            columnPosition = Tools.random(0, columnLimit);
            if (GameJava.board[rowPosition][columnPosition].equals(voidSquare)) {
                GameJava.board[rowPosition][columnPosition] = icon;

                ++counter;
            }

        } while (counter < number);
    }

    // method check if string is null or empty
    public static boolean isNullEmpty(String str) {

        // check if string is null
        if (str == null) {
            return true;
        } // check if string is empty
        else {
            return str.isEmpty();
        }
    }

    public static void setMap(int width, int height) {

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (isNullEmpty(GameJava.board[i][j])) {
                    GameJava.board[i][j] = voidSquare;
                }
            }
        }
        randomPositions(GameJava.numCoins, height - 1, width - 1, (" " + GameJava.CHAR_COIN + " "));
        randomPositions(GameJava.numEnemies, height - 1, width - 1, (" " + GameJava.CHAR_ENEMY + " "));
        
        //Inicialitzem els valors
        GameJava.board[0][0] = Character;
        Player.xpos = 0;
        Player.ypos = 0;
        Player.kills = 0;
        Player.sackOfCoins = 0;
        Player.HP = 100;
        Player.LV = 1;
    }

}
