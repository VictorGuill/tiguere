package gamejava;

import java.util.concurrent.TimeUnit;

public class GameJava {

    final static int INPUT_RATE = 20; //delay entre consultas del input

    public static String INPUT = ""; //guarda la ultima tecla pulsada
    public static int menuOption = 1;

    public static void main(String[] args) throws InterruptedException {
        InputListener keyInput = new InputListener(); //crea y abre la ventana java

        int widthBoard,
                heightBoard,
                numEnemies,
                character;

        boolean isGameRunning = true,
                sectionRunning;

        Tools.clearConsole(); //sin este primer clear, no funcionan los colores en CMD windows

        do {
            //////////////////////////////////////////
            /////////  START GAME SECTION  ///////////
            //////////////////////////////////////////
            sectionRunning = true;
            StartSetup.waitScreen();
            do {
                TimeUnit.MILLISECONDS.sleep(1000 / INPUT_RATE);
                if (!INPUT.equals("")) {
                    INPUT = "";
                    sectionRunning = false;
                }
            } while (sectionRunning);

            StartSetup.loadingAnimation();

            sectionRunning = true;
            StartSetup.startMenu(menuOption);
            do {
                TimeUnit.MILLISECONDS.sleep(1000 / INPUT_RATE);

                switch (INPUT) {
                    case "up":
                        if (menuOption > 1) {
                            menuOption--;
                            StartSetup.startMenu(menuOption);
                            INPUT = "";
                        }
                        break;
                    case "down":
                        if (menuOption < 4) {
                            menuOption++;
                            StartSetup.startMenu(menuOption);
                            INPUT = "";
                        }
                        break;
                    case "enter":
                        switch (menuOption) {
                            case 4:
                                Tools.clearConsole();
                                System.out.println("[PANTALLA ADIOS]");
                                sectionRunning = false;
                                isGameRunning = false;
                                break;
                        }
                        sectionRunning = false;
                        break;
                }
            } while (sectionRunning);

            //////////////////////////////////////////
            ////////  INGAME PLAYING SECTION  ////////
            //////////////////////////////////////////
            if (isGameRunning == false) {
                keyInput.dispose();
            }
        } while (isGameRunning);
    }

    //////////////////////////////////////////
    //////////////  FUNCIONES  ///////////////
    //////////////////////////////////////////
}
