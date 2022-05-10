package outputs;

import gamejava.*;
import static outputs.Screens.colorText;
import static outputs.Screens.colorUI;
import utilities.*;
import players.*;

public class Board {

    public static char CHAR_GUERRERO = '¥',
            CHAR_MAGO = '£',
            CHAR_SACERDOTE = '±',
            CHAR_ENEMY = '¤',
            CHAR_COIN = '$',
            CHAR_POTION_HP ='♥',
            CHAR_VISIBLE_POTION = '¶';

    public static String HP[] = new String[10];
    public static boolean firstPrint = true;
    public static boolean firstCharacter = true;
    public static int printDistance = 4;
    public static String Character,
            Enemy = (Tools.print(Screens.enemieArmorColor, "", "╠") + CHAR_ENEMY + Tools.print(Screens.enemieArmorColor, "", "╣")),
            Coin,
            EnemyLeft = (Tools.print(Screens.enemieArmorColor, "", "╠") + Tools.print(Screens.enemieColor, "", String.valueOf(CHAR_ENEMY)) + Tools.print(Screens.enemieSword, "", "┘")),
            EnemyRight = (Tools.print(Screens.enemieSword, "", "└") + Tools.print(Screens.enemieColor, "", String.valueOf(CHAR_ENEMY)) + Tools.print(Screens.enemieArmorColor, "", "╣")),
            PotionHP = (" " + CHAR_POTION_HP + " "),
            visiblePotion = (" " + CHAR_VISIBLE_POTION + " "),
            voidSquare = "░░░",
            bgColor = "green",
            coinColor = "yellow",
            enemyColor = "red",
            healthPotionColor = "red",
            visionPotionColor = "purple";

    /**
     * Llena el Board de casilla vacias y, despues, añade las coins e enemigos.
     *
     * @param width Anchura del Board.
     * @param height Altura del Board.
     */
    public static void setMap(int width, int height) {

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (GameJava.board[i][j] == null) {
                    GameJava.board[i][j] = voidSquare;
                }
            }
        }
        randomPositions(GameJava.numCoins, height - 1, width - 1, (" " + CHAR_COIN + " "));
        randomPositions(GameJava.numEnemies, height - 1, width - 1, (Enemy));
        randomPositions(GameJava.numHP_Potions, height - 1, width - 1, PotionHP);
        randomPositions(GameJava.numVisiblePotions, height - 1, width - 1, visiblePotion);

        //Inicialitzem els valors
        GameJava.board[0][0] = Character;
        Player.xpos = 0;
        Player.ypos = 0;
        Player.direction = "right";
        Player.kills = 0;
        Player.sackOfCoins = 0;
        Player.HP = Player.MAXHP;
        Player.LV = 1;
        Player.DMG = 20;
    }

    /**
     * Crea la cantidad de monedas dependiendo la dificultad. Si tenemos una
     * dificultad invalida ("IMPOSIBLE"), establece 4 coins.
     *
     * @return Un entero entre el margen que hay.
     */
    public static int randomCoin() {
        switch (GameJava.difficultSelection) {
            case 1:
                return GameJava.numCoins = Tools.random(1, 2);
            case 2:
                return GameJava.numCoins = Tools.random(3, 4);
            case 3:
                return GameJava.numCoins = Tools.random(5, 6);
        }
        return GameJava.numCoins = 4;
    }

    public static int randomHP_Potion() {
        switch (GameJava.difficultSelection) {
            case 1:
                return GameJava.numHP_Potions = Tools.random(0, 1);
            case 2:
                return GameJava.numHP_Potions = Tools.random(1, 3);
            case 3:
                return GameJava.numHP_Potions = Tools.random(3, 4);
        }
        return GameJava.numHP_Potions;
    }

    public static int randomVisiblePotion() {
        switch (GameJava.difficultSelection) {
            case 1:
                return GameJava.numVisiblePotions = Tools.random(0, 1);
            case 2:
                return GameJava.numVisiblePotions = Tools.random(1, 2);
            case 3:
                return GameJava.numVisiblePotions = Tools.random(2, 3);
        }
        return GameJava.numVisiblePotions;
    }

    /*
    Mejorar comentarios de los atributos de abajo.
     */
    /**
     * Escribe el String que recibe en una posicion aleatoria del Board.
     *
     * @param number Cantidad de objetos.
     * @param rowLimit Limite de fila.
     * @param columnLimit Limite de columna.
     * @param icon Tipo de casilla (Vacia,Enemigo,Moneda...).
     */
    public static void randomPositions(int number, int rowLimit, int columnLimit, String icon) {
        int counter = 0;
        int rowPosition, columnPosition;
        do {
            rowPosition = Tools.random(0, rowLimit);
            columnPosition = Tools.random(0, columnLimit);
            if (GameJava.board[rowPosition][columnPosition].equals(voidSquare)) {
                GameJava.board[rowPosition][columnPosition] = icon;
                counter++;
            }

        } while (counter < number);
    }

    /**
     * Imprime el tablero de juego + HUD.
     */
    public static void printBoard() {
        int hueco = voidSquare.length();
        int wBoard = GameJava.widthBoard, hBoard = GameJava.heightBoard;

        Tools.clearConsole();
          for (int i = 0; i < GameJava.enemies.size(); i++) {
              System.out.println(GameJava.enemies.get(i));              
          }
        Character = saveCharacter("right");
        Coin = (" " + CHAR_COIN + " ");
        if (firstPrint) {
            GameJava.numCoins = randomCoin();
            GameJava.numHP_Potions = randomHP_Potion();
            GameJava.numVisiblePotions = randomVisiblePotion();
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
                            } else if (j == wBoard + 14) {
                                System.out.print(Tools.print(colorUI, "", "║"));
                            } else if (j == wBoard + 3) {
                                if (Player.HP >= Player.MAXHP) {
                                    System.out.print("HP " + String.valueOf(Player.HP));
                                } else if (Player.HP >= 10) {
                                    System.out.print("HP  " + String.valueOf(Player.HP));
                                } else {
                                    System.out.print("HP   " + String.valueOf(Player.HP));
                                }

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
                            } else if (j == wBoard + 15) {
                                System.out.print(Tools.print(colorUI, "", "║"));
                            } else if (j == wBoard + 3) {
                                System.out.print("LVL " + String.valueOf(Player.LV));
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

    /**
     * Imprime menu de acciones disponibles dependiendo el personaje.
     */
    public static void showMenu() {
        if (GameJava.character == 1) {
            System.out.println(Tools.print(colorUI, "", "[") + Tools.print(colorText, "", "1")
                    + Tools.print(colorUI, "", "] - ") + Tools.print(colorText, "", "ATACK")
                    + Tools.print(colorUI, "", "\t[") + Tools.print(colorText, "", "2")
                    + Tools.print(colorUI, "", "] - ") + Tools.print(colorText, "", "CHANGE CHARACTER")
                    + Tools.print(colorUI, "", "\n[") + Tools.print(colorText, "", "3")
                    + Tools.print(colorUI, "", "] - ") + Tools.print(colorText, "", "PICK UP OBJECT")
                    + Tools.print(colorUI, "", "\t[") + Tools.print(colorText, "", "4")
                    + Tools.print(colorUI, "", "] - ") + Tools.print(colorText, "", "MOVEx2")
                    + Tools.print(colorUI, "", "\t[") + Tools.print(colorText, "", "ESC")
                    + Tools.print(colorUI, "", "] - ") + Tools.print(colorText, "", "EXIT"));
        } else if (GameJava.character == 2) {
            System.out.println(Tools.print(colorUI, "", "[") + Tools.print(colorText, "", "1")
                    + Tools.print(colorUI, "", "] - ") + Tools.print(colorText, "", "ATACK")
                    + Tools.print(colorUI, "", "\t[") + Tools.print(colorText, "", "2")
                    + Tools.print(colorUI, "", "] - ") + Tools.print(colorText, "", "CHANGE CHARACTER")
                    + Tools.print(colorUI, "", "\n[") + Tools.print(colorText, "", "3")
                    + Tools.print(colorUI, "", "] - ") + Tools.print(colorText, "", "PICK UP OBJECT")
                    + Tools.print(colorUI, "", "\t[") + Tools.print(colorText, "", "4")
                    + Tools.print(colorUI, "", "] - ") + Tools.print(colorText, "", "HEAL")
                    + Tools.print(colorUI, "", "\t[") + Tools.print(colorText, "", "ESC")
                    + Tools.print(colorUI, "", "] - ") + Tools.print(colorText, "", "EXIT"));
        } else {
            System.out.println(Tools.print(colorUI, "", "[") + Tools.print(colorText, "", "1")
                    + Tools.print(colorUI, "", "] - ") + Tools.print(colorText, "", "ATACK")
                    + Tools.print(colorUI, "", "\t[") + Tools.print(colorText, "", "2")
                    + Tools.print(colorUI, "", "] - ") + Tools.print(colorText, "", "CHANGE CHARACTER")
                    + Tools.print(colorUI, "", "\n[") + Tools.print(colorText, "", "3")
                    + Tools.print(colorUI, "", "] - ") + Tools.print(colorText, "", "PICK UP OBJECT")
                    + Tools.print(colorUI, "", "\t[") + Tools.print(colorText, "", "ESC")
                    + Tools.print(colorUI, "", "] - ") + Tools.print(colorText, "", "EXIT"));
        }
    }

    /**
     * Guarda la direcion del jugador.
     *
     * @param direction right/left.
     * @return String con el jugador mirando en la posicion correcta.
     */
    public static String saveCharacter(String direction) {
        switch (GameJava.character) {
            case 0://GUERRERO
                switch (direction) {
                    case "right":
                        return Character = (" " + CHAR_GUERRERO + "/");
                    case "left":
                        return Character = ("\\" + CHAR_GUERRERO + " ");
                }
            case 1://MAGO
                switch (direction) {
                    case "right":
                        return Character = (" " + CHAR_MAGO + "|");
                    case "left":
                        return Character = ("|" + CHAR_MAGO + " ");
                }
            case 2://SACERDOTE
                switch (direction) {
                    case "right":
                        return Character = (" " + CHAR_SACERDOTE + "/");
                    case "left":
                        return Character = ("\\" + CHAR_SACERDOTE + " ");
                }
        }
        return "";
        /*
        No me convence esta solucion, creo que cada subclase de player 
        deberia tener un atributo CHAR donde esta su caracter guardado y
        en otro CHAR guardar el tipo tipo de arma que tiene. gunLeft \   gunRight /
        Asi en esta funcion simplemente un if RIGHT or LEFT y devolvemos arma y char del this.personaje.        
         */
    }

    /*
    DELETE COMENT. Mejorar esta funcion, muy dificil de leer.
     */
    /**
     * Lee el array Board e imprime el caracter y color correspondiente.
     *
     * @param row Casilla de la fila que estamos leyendo. X
     * @param column Columna en la que estamos. Y
     */
    public static void printPosition(int row, int column) {
        if (Player.getYpos() - printDistance <= column && Player.getYpos() + printDistance >= column && Player.getXpos() - printDistance <= row && Player.getXpos() + printDistance >= row) {
            if (Player.getXpos() == row && Player.getYpos() == column) {
                System.out.print(saveCharacter(Player.direction));
            } else if (GameJava.board[column][row].equals(voidSquare)) {
                System.out.print(Tools.print(bgColor, "", GameJava.board[column][row]));
            } else if (GameJava.board[column][row].equals(" " + CHAR_COIN + " ")) {
                System.out.print(Tools.print(coinColor, "", GameJava.board[column][row]));
            } else if (GameJava.board[column][row].equals(PotionHP)) {
                System.out.print(Tools.print(healthPotionColor, "", GameJava.board[column][row]));
            } else if (GameJava.board[column][row].equals(visiblePotion)) {
                System.out.print(Tools.print(visionPotionColor, "", GameJava.board[column][row]));
            } else if (GameJava.board[column][row].equals(" " + CHAR_GUERRERO + " ")
                    || GameJava.board[column][row].equals(" " + CHAR_MAGO + " ")
                    || GameJava.board[column][row].equals(" " + CHAR_SACERDOTE + " ")
                    || GameJava.board[column][row].equals(Enemy)
                    || GameJava.board[column][row].equals(EnemyLeft)
                    || GameJava.board[column][row].equals(EnemyRight)) {
                System.out.print(Tools.print(enemyColor, "", GameJava.board[column][row]));
            }
        } else {
            System.out.print("   ");
        }
    }
}
