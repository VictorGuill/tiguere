package gamejava;

import java.util.concurrent.TimeUnit;

public class StartSetup {

    public static String colorUI = "cyan",
            colorText = "yellow",
            colorHighlight = "red";

    public static int w = 45,
            h = 11;

    ////////// PANTALLA ESPERA INICIO //////////
    public static void waitScreen(int State) {
        Tools.clearConsole();
        /*
        System.out.println(""
                + "           ________________________          \n"
                + "          / ╦ ╦╦   ╔═╗╔═╗╔═╗╔═╗╔╦╗ \\        \n"
                + "         /  ╠═╣║───╚═╗╠═╝║╣ ║╣  ║║  \\       \n"
                + "        /   ╩ ╩╩   ╚═╝╩  ╚═╝╚═╝═╩╝   \\      \n"
                + "       /   ╔╗╔╔═╗╦ ╦  ╦ ╦╔═╗╦  ╦╔═╗   \\     \n"
                + "      /    ║║║║╣ ║║║  ║║║╠═╣╚╗╔╝║╣     \\    \n"
                + "     /     ╝╚╝╚═╝╚╩╝  ╚╩╝╩ ╩ ╚╝ ╚═╝     \\   \n"
                + "    /  ╔═╗╔═╗╦ ╦╦    ╦ ╦╦ ╦╔╗╔╔╦╗╔═╗╦═╗  \\  \n"
                + "   /   ╚═╗║ ║║ ║║    ╠═╣║ ║║║║ ║ ║╣ ╠╦╝   \\ \n"
                + "  /    ╚═╝╚═╝╚═╝╩═╝  ╩ ╩╚═╝╝╚╝ ╩ ╚═╝╩╚═    \\\n"
                + "  \\                                        /\n"
                + "   \\________Hit any key to begin..._______/ ");
         */

        System.out.print(
                ""
                + Tools.print(colorUI, "", "           ________________________          \n")
                + Tools.print(colorUI, "", "          / ") + Tools.print(colorText, "", "╦ ╦╦   ╔═╗╔═╗╔═╗╔═╗╔╦╗") + Tools.print(colorUI, "", " \\        \n")
                + Tools.print(colorUI, "", "         /  ") + Tools.print(colorText, "", "╠═╣║───╚═╗╠═╝║╣ ║╣  ║║") + Tools.print(colorUI, "", "  \\       \n")
                + Tools.print(colorUI, "", "        /   ") + Tools.print(colorText, "", "╩ ╩╩   ╚═╝╩  ╚═╝╚═╝═╩╝") + Tools.print(colorUI, "", "   \\      \n")
                + Tools.print(colorUI, "", "       /   ") + Tools.print(colorText, "", "╔╗╔╔═╗╦ ╦  ╦ ╦╔═╗╦  ╦╔═╗") + Tools.print(colorUI, "", "   \\     \n")
                + Tools.print(colorUI, "", "      /    ") + Tools.print(colorText, "", "║║║║╣ ║║║  ║║║╠═╣╚╗╔╝║╣") + Tools.print(colorUI, "", "     \\    \n")
                + Tools.print(colorUI, "", "     /     ") + Tools.print(colorText, "", "╝╚╝╚═╝╚╩╝  ╚╩╝╩ ╩ ╚╝ ╚═╝") + Tools.print(colorUI, "", "     \\   \n")
                + Tools.print(colorUI, "", "    /  ") + Tools.print(colorText, "", "╔═╗╔═╗╦ ╦╦    ╦ ╦╦ ╦╔╗╔╔╦╗╔═╗╦═╗") + Tools.print(colorUI, "", "  \\  \n")
                + Tools.print(colorUI, "", "   /   ") + Tools.print(colorText, "", "╚═╗║ ║║ ║║    ╠═╣║ ║║║║ ║ ║╣ ╠╦╝") + Tools.print(colorUI, "", "   \\ \n")
                + Tools.print(colorUI, "", "  /    ") + Tools.print(colorText, "", "╚═╝╚═╝╚═╝╩═╝  ╩ ╩╚═╝╝╚╝ ╩ ╚═╝╩╚═") + Tools.print(colorUI, "", "    \\\n")
                + Tools.print(colorUI, "", "  \\                                        /\n"));

        if (State % 2 == 0) {
            System.out.println(Tools.print(colorUI, "", "   \\_______") + Tools.print("red", "", " Hit any key to begin... ") + Tools.print(colorUI, "", "______/ "));
        } else {
            System.out.println(Tools.print(colorUI, "", "   \\_______") + Tools.print("white", "red", " Hit any key to begin... ") + Tools.print(colorUI, "", "______/ "));
        }
    }

    public static void startMenu(int menuOption) {
        Tools.clearConsole();

        for (int i = 0; i < h; i++) {
            if (i == 0) {
                for (int j = 0; j < w; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(colorUI, "", "╔"));
                    } else if (j == w - 1) {
                        System.out.print(Tools.print(colorUI, "", "╗"));
                    } else {
                        System.out.print(Tools.print(colorUI, "", "═"));
                    }
                }
            } else if (i == h - 1) {
                for (int j = 0; j < w; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(colorUI, "", "╚"));
                    } else if (j == w - 1) {
                        System.out.print(Tools.print(colorUI, "", "╝"));
                    } else {
                        System.out.print(Tools.print(colorUI, "", "═"));
                    }
                }
            } else if (i == 2) {
                System.out.print(Tools.print(colorUI, "", "║                  "));
                System.out.print(Tools.print("blue", colorText, " MENU "));
                System.out.print(Tools.print(colorUI, "", "                   ║"));
            } else if (i == 5) {
                System.out.print(Tools.print(colorUI, "", "║"));
                if (menuOption == 1) {
                    System.out.print("              > ");
                    System.out.print(Tools.print("white", colorHighlight, "PLAY"));
                    System.out.print("                       ");
                } else {
                    System.out.print(Tools.print(colorText, "", "               PLAY                        "));
                }
                System.out.print(Tools.print(colorUI, "", "║"));
            } else if (i == 6) {
                System.out.print(Tools.print(colorUI, "", "║"));
                if (menuOption == 2) {
                    System.out.print("              > ");
                    System.out.print(Tools.print("white", colorHighlight, "TUTORIAL"));
                    System.out.print("                   ");
                } else {
                    System.out.print(Tools.print(colorText, "", "               TUTORIAL                    "));
                }
                System.out.print(Tools.print(colorUI, "", "║"));
            } else if (i == 7) {
                System.out.print(Tools.print(colorUI, "", "║"));
                if (menuOption == 3) {
                    System.out.print("              > ");
                    System.out.print(Tools.print("white", colorHighlight, "CONFIG"));
                    System.out.print("                     ");
                } else {
                    System.out.print(Tools.print(colorText, "", "               CONFIG                      "));
                }
                System.out.print(Tools.print(colorUI, "", "║"));
            } else if (i == 8) {
                System.out.print(Tools.print(colorUI, "", "║"));
                if (menuOption == 4) {
                    System.out.print("              > ");
                    System.out.print(Tools.print("white", colorHighlight, "EXIT"));
                    System.out.print("                       ");
                } else {
                    System.out.print(Tools.print(colorText, "", "               EXIT                        "));
                }
                System.out.print(Tools.print(colorUI, "", "║"));
            } else {
                for (int j = 0; j < w; j++) {
                    if (j == 0 || j == w - 1) {
                        System.out.print(Tools.print(colorUI, "", "║"));
                    } else {
                        System.out.print(" ");
                    }
                }

            }
            System.out.println("");
        }
    }

    public static void loadingAnimation() throws InterruptedException {
        Tools.clearConsole();
        /*
        TimeUnit.MILLISECONDS.sleep(200);
        System.out.println("RCPI: SSDT Ox0000000077FD4000 000360");
        TimeUnit.MILLISECONDS.sleep(150);
        System.out.println("(v01 SataRe SataTabl 00001000 INTL 20120913) \n"
                + "RCPI: LPIT 0x0000000077842188 000094 \n"
                + "(v01 INTEL SKL 00000000 MSFT 0000005F) ");
        TimeUnit.MILLISECONDS.sleep(150);
        System.out.println("RCPI: DBG2 0x0000000077842288 000054 \n"
                + "(v00 INTEL 00000000 MSFT 0000005F)");
        System.out.println("RCPI: SSDT 0x0000000077FD0000 000616 (v02 INTEL xh_rvp08 00000000 INTL 20120913) ");
        TimeUnit.MILLISECONDS.sleep(150);
        System.out.println("RCPI: RAFT Ox0000000077R428F8 000536 (v01 ALASKA °EMI:TT 01072009 MSFT 00000097)");
        TimeUnit.MILLISECONDS.sleep(150);
        System.out.println("RCPI: SSDT Ox0000000077FC9000 005212 (v02 SaSsdt SaSsdt 00003000 INTL 20120913)");
        TimeUnit.MILLISECONDS.sleep(150);
        System.out.println("RCPI: SSDT Ox0000000077FC7000 000E58 (v02 CpuRef CpuSsdt 00003000 INTL 20120913)");
        TimeUnit.MILLISECONDS.sleep(150);
        System.out.println("RCPI Accepted: [\\_SB .PCIO.XHC .RHUB.HS11] Namespace lookup failure, HE_NOT_FOUND (20140828/dswtoad-125)\n"
                + "RCPI Exception: RE_NOT_FOUN15, During name lookup/catalog (20140828/psobject-131) \n"
                + "RCPI: Rll RCPI Tables successfully acquired ");
        TimeUnit.MILLISECONDS.sleep(300);
        System.out.println("RgopCredentialManager::start called \n"
                + "RgopRCPICPU: Processorld=1 LocalRpicId=0 Enabled");
        System.out.println("RgopRCPICPU: Processorld=2 LocalRpicId=2 Enabled\n"
                + "RgopRCPICPU: Processorld=3 LocalApicId=4 Enabled \n"
                + "AgopRCPICPU: Processor Id=4 LocalRpicId=6 Enabled");
        TimeUnit.MILLISECONDS.sleep(150);
        System.out.println("calling JAVA .jar RMFI loaded:");
        System.out.println("RCPI: sleep states S3 S4 S5 HID: Legacy shim 2 pci (build 16:59:56 Mar 3 2017), flags Oxe3000, pfm64 (39 c) Ox7f80000000, Ox80000000 ");
        TimeUnit.MILLISECONDS.sleep(50);
        System.out.println("HID: Legacy shim 2 pu  I PCI configuration begin ]");

        TimeUnit.MILLISECONDS.sleep(300);

        */
        
        for (int i = 0; i < 7; i++) {
            Tools.clearConsole();
            loadingStartScreen(i);
            TimeUnit.MILLISECONDS.sleep(300);
        }
        TimeUnit.MILLISECONDS.sleep(700);
    }

    public static void loadingStartScreen(int counter) throws InterruptedException {
        for (int i = 0; i < h; i++) {
            if (i == 0) {
                for (int j = 0; j < w; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(colorUI, "", "╔"));
                    } else if (j == w - 1) {
                        System.out.print(Tools.print(colorUI, "", "╗"));
                    } else {
                        System.out.print(Tools.print(colorUI, "", "═"));
                    }
                }
            } else if (i == h - 1) {
                for (int j = 0; j < w; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(colorUI, "", "╚"));
                    } else if (j == w - 1) {
                        System.out.print(Tools.print(colorUI, "", "╝"));
                    } else {
                        System.out.print(Tools.print(colorUI, "", "═"));
                    }
                }
            } else if (i == 6) {
                for (int j = 0; j < w; j++) {
                    if (j == 0 || j == w - 1) {
                        System.out.print(Tools.print(colorUI, "", "║"));
                    } else if (j == 14) {
                        System.out.print(Tools.print(colorUI, "", "["));
                    } else if (j == w - 15) {
                        System.out.print(Tools.print(colorUI, "", "]"));
                    } else if (j >= 15 && j < w - 15) {
                        if (j == 15) {
                            switch (counter) {
                                case 0:
                                    System.out.print(Tools.print(colorUI, "", "░░░░░░░░░░░░░░░"));
                                    break;
                                case 1:
                                    System.out.print(Tools.print(colorUI, "", "▓▓▒░░░░░░░░░░░░"));
                                    break;
                                case 2:
                                    System.out.print(Tools.print(colorUI, "", "▓▓▓▓▓▒░░░░░░░░░"));
                                    break;
                                case 3:
                                    System.out.print(Tools.print(colorUI, "", "▓▓▓▓▓▓▓▓▒░░░░░░"));
                                    break;
                                case 4:
                                    System.out.print(Tools.print(colorUI, "", "▓▓▓▓▓▓▓▓▓▓▓▒░░░"));
                                    break;
                                case 5:
                                    System.out.print(Tools.print(colorUI, "", "▓▓▓▓▓▓▓▓▓▓▓▓▓▒░"));
                                    break;
                                case 6:
                                    System.out.print(Tools.print(colorUI, "", "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓"));
                                    break;
                            }
                        }
                    } else {
                        System.out.print(Tools.print(colorUI, "", " "));
                    }
                }
            } else if (i == 7) {
                System.out.print(Tools.print(colorUI, "", "║"));
                System.out.print("                 ");
                switch (counter) {
                    case 0:
                        System.out.print("LOADING" + Tools.print(colorUI, "", " [/]"));
                        break;
                    case 1:
                        System.out.print("LOADING" + Tools.print(colorUI, "", " [─]"));
                        break;
                    case 2:
                        System.out.print("LOADING" + Tools.print(colorUI, "", " [\\]"));
                        break;
                    case 3:
                        System.out.print("LOADING" + Tools.print(colorUI, "", " [/]"));
                        break;
                    case 4:
                        System.out.print("LOADING" + Tools.print(colorUI, "", " [─]"));
                        break;
                    case 5:
                        System.out.print("LOADING" + Tools.print(colorUI, "", " [\\]"));
                        break;
                    case 6:
                        System.out.print(" ¡DONE!    ");
                        break;
                }
                System.out.print("               ");
                System.out.print(Tools.print(colorUI, "", "║"));
            } else {
                for (int j = 0; j < w; j++) {
                    if (j == 0 || j == w - 1) {
                        System.out.print(Tools.print(colorUI, "", "║"));
                    } else {
                        System.out.print(" ");
                    }
                }

            }
            System.out.println("");
        }
    }

    public static void boardSizeScreen(int wValue, int hValue, int optionSelected) {
        Tools.clearConsole();

        for (int i = 0; i < h; i++) {
            if (i == 0) {
                for (int j = 0; j < w; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(colorUI, "", "╔"));
                    } else if (j == w - 1) {
                        System.out.print(Tools.print(colorUI, "", "╗"));
                    } else {
                        System.out.print(Tools.print(colorUI, "", "═"));
                    }
                }
            } else if (i == h - 1) {
                for (int j = 0; j < w; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(colorUI, "", "╚"));
                    } else if (j == w - 1) {
                        System.out.print(Tools.print(colorUI, "", "╝"));
                    } else {
                        System.out.print(Tools.print(colorUI, "", "═"));
                    }
                }
            } else if (i == 2) {
                System.out.print(Tools.print(colorUI, "", "║               "));
                System.out.print(Tools.print("blue", colorText, " BOARD  SIZE "));
                System.out.print(Tools.print(colorUI, "", "               ║"));
            } else if (i == 4) {
                System.out.print(Tools.print(colorUI, "", "║               "));
                if (wValue <= GameJava.MIN_BOARD_SIZE) {
                    System.out.print("  ");
                } else {
                    System.out.print(Tools.print(colorUI, "", spaceSingleDigit(wValue - 1)));
                }
                System.out.print("                ");
                if (hValue <= GameJava.MIN_BOARD_SIZE) {
                    System.out.print("  ");
                } else {
                    System.out.print(Tools.print(colorUI, "", spaceSingleDigit(hValue - 1)));
                }
                System.out.print(Tools.print(colorUI, "", "        ║"));
            } else if (i == 5) {
                System.out.print(Tools.print(colorUI, "", "║"));
                System.out.print(Tools.print(colorText, "", "       WIDTH: "));
                System.out.print(Tools.print(colorUI, "", "║"));
                if (optionSelected == 1) {
                    System.out.print(Tools.print("white", "red", spaceSingleDigit(wValue)));
                } else {
                    System.out.print(Tools.print("white", "", spaceSingleDigit(wValue)));
                }
                System.out.print(Tools.print(colorUI, "", "║"));
                System.out.print(Tools.print(colorText, "", "      HEIGHT: "));
                System.out.print(Tools.print(colorUI, "", "║"));
                if (optionSelected == 2) {
                    System.out.print(Tools.print("white", "red", spaceSingleDigit(hValue)));
                } else {
                    System.out.print(Tools.print("white", "", spaceSingleDigit(hValue)));
                }
                System.out.print(Tools.print(colorUI, "", "║       ║"));
            } else if (i == 6) {
                System.out.print(Tools.print(colorUI, "", "║               "));
                if (wValue >= GameJava.MAX_BOARD_SIZE) {
                    System.out.print("  ");
                } else {
                    System.out.print(Tools.print(colorUI, "", spaceSingleDigit(wValue + 1)));
                }
                System.out.print("                ");
                if (hValue >= GameJava.MAX_BOARD_SIZE) {
                    System.out.print("  ");
                } else {
                    System.out.print(Tools.print(colorUI, "", spaceSingleDigit(hValue + 1)));
                }
                System.out.print(Tools.print(colorUI, "", "        ║"));
            } else if (i == 9) {
                if (optionSelected == 3) {
                    System.out.print(Tools.print(colorUI, "", "║                 "));
                    System.out.print(Tools.print("white", "red", " N E X T "));
                    System.out.print(Tools.print(colorUI, "", "                 ║"));
                } else {
                    System.out.print(Tools.print(colorUI, "", "║                  N E X T                  ║"));
                }
            } else {
                for (int j = 0; j < w; j++) {
                    if (j == 0 || j == w - 1) {
                        System.out.print(Tools.print(colorUI, "", "║"));
                    } else {
                        System.out.print(" ");
                    }
                }

            }
            System.out.println("");
        }
    }

    public static void characterSelectorScreen(int optionSelected, int secondSelection) {
        Tools.clearConsole();

        for (int i = 0; i < h; i++) {
            if (i == 0) {
                for (int j = 0; j < w; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(colorUI, "", "╔"));
                    } else if (j == w - 1) {
                        System.out.print(Tools.print(colorUI, "", "╗"));
                    } else {
                        System.out.print(Tools.print(colorUI, "", "═"));
                    }
                }
            } else if (i == h - 1) {
                for (int j = 0; j < w; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(colorUI, "", "╚"));
                    } else if (j == w - 1) {
                        System.out.print(Tools.print(colorUI, "", "╝"));
                    } else {
                        System.out.print(Tools.print(colorUI, "", "═"));
                    }
                }
            } else if (i == 2) {
                System.out.print(Tools.print(colorUI, "", "║             "));
                System.out.print(Tools.print("blue", colorText, " CHOOSE CHARACTER "));
                System.out.print(Tools.print(colorUI, "", "            ║"));
            } else if (i == 5) {
                System.out.print(Tools.print(colorUI, "", "║                             ┌─────┐       ║"));
            } else if (i == 6) {
                if (optionSelected == 1) {
                    System.out.print(Tools.print(colorUI, "", "║             "));
                    if (secondSelection == 1) {
                        System.out.print(Tools.print("white", "red", "WARRIOR"));
                    } else {
                        System.out.print(Tools.print(colorText, "", "WARRIOR"));
                    }
                    System.out.print(Tools.print(colorUI, "", " >       │  "));
                    System.out.print(Tools.print(colorText, "", String.valueOf(GameJava.CHAR_GUERRERO)));
                    System.out.print(Tools.print(colorUI, "", "  │       ║"));
                } else if (optionSelected == 2) {
                    System.out.print(Tools.print(colorUI, "", "║           < "));
                    if (secondSelection == 1) {
                        System.out.print(Tools.print("white", "red", "WIZARD"));
                    } else {
                        System.out.print(Tools.print(colorText, "", "WIZARD"));
                    }
                    System.out.print(Tools.print(colorUI, "", " >        │  "));
                    System.out.print(Tools.print(colorText, "", String.valueOf(GameJava.CHAR_MAGO)));
                    System.out.print(Tools.print(colorUI, "", "  │       ║"));
                } else if (optionSelected == 3) {
                    System.out.print(Tools.print(colorUI, "", "║           < "));
                    if (secondSelection == 1) {
                        System.out.print(Tools.print("white", "red", "PRIEST"));
                    } else {
                        System.out.print(Tools.print(colorText, "", "PRIEST"));
                    }
                    System.out.print(Tools.print(colorUI, "", "          │  "));
                    System.out.print(Tools.print(colorText, "", String.valueOf(GameJava.CHAR_SACERDOTE)));
                    System.out.print(Tools.print(colorUI, "", "  │       ║"));
                }
            } else if (i == 7) {
                System.out.print(Tools.print(colorUI, "", "║                             └─────┘       ║"));
            } else if (i == 9) {
                if (secondSelection == 2) {
                    System.out.print(Tools.print(colorUI, "", "║                 "));
                    System.out.print(Tools.print("white", "red", " N E X T "));
                    System.out.print(Tools.print(colorUI, "", "                 ║"));
                } else {
                    System.out.print(Tools.print(colorUI, "", "║                  N E X T                  ║"));
                }
            } else {
                for (int j = 0; j < w; j++) {
                    if (j == 0 || j == w - 1) {
                        System.out.print(Tools.print(colorUI, "", "║"));
                    } else {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println("");
        }
    }

    public static void gameDifficultyScreen(int menuOption) {
        Tools.clearConsole();

        for (int i = 0; i < h; i++) {
            if (i == 0) {
                for (int j = 0; j < w; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(colorUI, "", "╔"));
                    } else if (j == w - 1) {
                        System.out.print(Tools.print(colorUI, "", "╗"));
                    } else {
                        System.out.print(Tools.print(colorUI, "", "═"));
                    }
                }
            } else if (i == h - 1) {
                for (int j = 0; j < w; j++) {
                    if (j == 0) {
                        System.out.print(Tools.print(colorUI, "", "╚"));
                    } else if (j == w - 1) {
                        System.out.print(Tools.print(colorUI, "", "╝"));
                    } else {
                        System.out.print(Tools.print(colorUI, "", "═"));
                    }
                }
            } else if (i == 2) {
                System.out.print(Tools.print(colorUI, "", "║             "));
                System.out.print(Tools.print("blue", colorText, " GAME DIFFICULTY "));
                System.out.print(Tools.print(colorUI, "", "             ║"));
            } else if (i == 5) {
                System.out.print(Tools.print(colorUI, "", "║"));
                if (menuOption == 1) {
                    System.out.print("                 > ");
                    System.out.print(Tools.print("white", colorHighlight, "EASY"));
                    System.out.print("                    ");
                } else {
                    System.out.print(Tools.print(colorText, "", "                   EASY                    "));
                }
                System.out.print(Tools.print(colorUI, "", "║"));
            } else if (i == 6) {
                System.out.print(Tools.print(colorUI, "", "║"));
                if (menuOption == 2) {
                    System.out.print("                > ");
                    System.out.print(Tools.print("white", colorHighlight, "MEDIUM"));
                    System.out.print("                   ");
                } else {
                    System.out.print(Tools.print(colorText, "", "                  MEDIUM                   "));
                }
                System.out.print(Tools.print(colorUI, "", "║"));
            } else if (i == 7) {
                System.out.print(Tools.print(colorUI, "", "║"));
                if (menuOption == 3) {
                    System.out.print("                 > ");
                    System.out.print(Tools.print("white", colorHighlight, "HARD"));
                    System.out.print("                    ");
                } else {
                    System.out.print(Tools.print(colorText, "", "                   HARD                    "));
                }
                System.out.print(Tools.print(colorUI, "", "║"));
            } else {
                for (int j = 0; j < w; j++) {
                    if (j == 0 || j == w - 1) {
                        System.out.print(Tools.print(colorUI, "", "║"));
                    } else {
                        System.out.print(" ");
                    }
                }

            }
            System.out.println("");
        }
    }

    /**
     * Coje un int y le añade un 0 si es solo 1 digito.
     *
     * @param number Numero a formatear.
     * @return String formateada.
     */
    public static String spaceSingleDigit(int number) {
        if (number < 10) {
            return "0" + String.valueOf(number);
        } else {
            return String.valueOf(number);
        }
    }
}
