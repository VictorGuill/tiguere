package outputs;

import gamejava.GameJava;
import utilities.Tools;
import players.Player;
import static outputs.Screens.colorUI;

public class Board {

    public static String HP[] = new String[10];
    public static boolean firstPrint = true;
    public static boolean firstCharacter = true;
    public static String Character,
            Enemy,
            Coin,
            EnemyRight = (" " + GameJava.CHAR_ENEMY + "/"),
            EnemyLeft = ("\\" + GameJava.CHAR_ENEMY + " "),
            voidSquare = "░░░",
            voidCharacterSides = "░",
            bgColor = "green",
            coinColor = "yellow",
            enemyColor = "red";

    public static void printBoard(int wBoard, int hBoard) {
        int hueco = voidSquare.length();

        Tools.clearConsole();

        Character = saveCharacter("right");
        Enemy = saveEnemy();
        Coin = (" " + GameJava.CHAR_COIN + " ");
        if (firstPrint) {
            GameJava.numCoins = randomCoin();
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
                Tools.printRow('╚', '═', (wBoard * hueco) + 2, '╝', Screens.colorUI);
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
                            } else if (j == wBoard + 15) {
                                System.out.print(Tools.print(colorUI, "", "║"));
                            } else if (j == wBoard + 3) {
                                System.out.print(Player.sackOfCoins);
                            } else if (j == wBoard + 5) {
                                System.out.print("COINS");
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
                            } else if (j == wBoard + 15) {
                                System.out.print(Tools.print(colorUI, "", "║"));
                            } else if (j == wBoard + 3) {
                                System.out.print(Player.kills);
                            } else if (j == wBoard + 5) {
                                System.out.print("KILLS");
                            } else {
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
            } else if (i > 5 && i < 10) {
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
                            } else if (j == wBoard + 3) {
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
                            } else if (j == wBoard + 3) {
                                System.out.print("L");
                            } else if (j == wBoard + 4) {
                                System.out.print("V");
                            } else if (j == wBoard + 5) {
                                System.out.print("L");
                            } else if (j == wBoard + 7) {
                                System.out.print(String.valueOf(Player.LV));
                            } else {
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
            } else {
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
        showMenu();
    }

    public static void showMenu() {
        if (GameJava.character == 1) {
            System.out.println("1 - ATACK           2 - CHANGE CHARACTER  \n"
                    + "3 - PICK UP OBJECT  5 - MOVE*2 ");
        } else {
            System.out.println("1 - ATACK           2 - CHANGE CHARACTER  \n"
                    + "3 - PICK UP OBJECT");
        }
    }

    public static String saveCharacter(String direction) {
        switch (GameJava.character) {
            case 0:
                if (direction.equals("right")) {
                    return Character = (" " + GameJava.CHAR_GUERRERO + "/");
                } else {
                    return Character = ("\\" + GameJava.CHAR_GUERRERO + " ");
                }
            case 1:
                if (direction.equals("right")) {
                    return Character = (" " + GameJava.CHAR_MAGO + "|");
                } else {
                    return Character = ("|" + GameJava.CHAR_MAGO + " ");
                }
            default:
                if (direction.equals("right")) {
                    return Character = (" " + GameJava.CHAR_SACERDOTE + "/");
                } else {
                    return Character = ("\\" + GameJava.CHAR_SACERDOTE + " ");
                }
        }
    }

    public static String saveEnemy() {
        Enemy = (" " + GameJava.CHAR_ENEMY + " ");
        return Enemy;
    }

    public static void printPosition(int row, int column) {
        int printDistance = 4;

        if (Player.getYpos() - printDistance <= column
                && Player.getYpos() + printDistance >= column
                && Player.getXpos() - printDistance <= row
                && Player.getXpos() + printDistance >= row) {
            if (Player.getXpos() == row && Player.getYpos() == column) {
                System.out.print(saveCharacter(Player.direction));
            } else if (GameJava.board[column][row].equals(voidSquare)) {
                System.out.print(Tools.print(bgColor, "", GameJava.board[column][row]));
            } else if (GameJava.board[column][row].equals(" " + GameJava.CHAR_COIN + " ")) {
                System.out.print(Tools.print(coinColor, "", GameJava.board[column][row]));
            } else if (GameJava.board[column][row].equals(" " + GameJava.CHAR_GUERRERO + " ")
                    || GameJava.board[column][row].equals(" " + GameJava.CHAR_MAGO + " ")
                    || GameJava.board[column][row].equals(" " + GameJava.CHAR_SACERDOTE + " ")
                    || GameJava.board[column][row].equals(" " + GameJava.CHAR_ENEMY + " ")
                    || GameJava.board[column][row].equals(" " + GameJava.CHAR_ENEMY + "/")
                    || GameJava.board[column][row].equals("\\" + GameJava.CHAR_ENEMY + " ")) {
                System.out.print(Tools.print(enemyColor, "", GameJava.board[column][row]));
            }
        } else {
            System.out.print("   ");
        }
    }

    public static int randomCoin() {
        switch (GameJava.difficultSelection) {
            case 1:
                return GameJava.numCoins = Tools.random(1, 2);
            case 2:
                return GameJava.numCoins = Tools.random(3, 4);
            default:
                return GameJava.numCoins = Tools.random(5, 6);
        }
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

    public static void setMap(int width, int height) {

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (GameJava.board[i][j] == null) {
                    GameJava.board[i][j] = voidSquare;
                }
            }
        }
        randomPositions(GameJava.numCoins, height - 1, width - 1, (" " + GameJava.CHAR_COIN + " "));
        randomPositions(GameJava.numEnemies, height - 1, width - 1, (" " + GameJava.CHAR_ENEMY + " "));

        //Inicialitzem els valors
        GameJava.board[0][1] = Character;
        Player.xpos = 1;
        Player.ypos = 0;
        Player.direction = "right";
        Player.kills = 0;
        Player.sackOfCoins = 0;
        Player.HP = 100;
        Player.LV = 1;
    }

}
