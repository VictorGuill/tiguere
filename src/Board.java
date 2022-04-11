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
    public static void printBoard(int wBoard, int hBoard) {
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
                                System.out.print("░░░░░");
                            }else if(j == (wBoard + 1)) {
                                System.out.print(Tools.print(colorUI, "", "║"));
                            } else if (j == wBoard + 19){
                                System.out.print(Tools.print(colorUI, "", "║"));
                            } else if (j == wBoard + 3) {
                                System.out.print(0);
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
                                System.out.print("░░░░░");
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
                                System.out.print("░░░░░");
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
                        System.out.print("░░░░░");
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
                        System.out.print("░░░░░");
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
}
