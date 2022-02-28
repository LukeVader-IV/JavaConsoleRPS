package net.RPS;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import net.RPS.GameLogic.MainLoop;
import net.RPS.GameLogic.Player;


public class Launcher {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        boolean menu = true;

        String input = "";
        boolean checkInput = true;

        while(menu) {
            //display the le stuff
            System.out.println("\t\tRock Paper Scissor  O N L I N E !\n\n\t\t\t\t\tHugo Costa (Koraku)");
            System.out.println("0 - Host a game\n1 - Join a hosted game\n2 - Quit");

            //Input
            while(checkInput) {
                System.out.println("->");
                input = scan.next();

                if(MainLoop.isIn(input.toLowerCase(), new String[] {"0", "1", "2", "quit"})) {
                    checkInput = false;
                } else {
                    checkInput = true;
                    input = "";
                    System.out.println("Please choose a valid option.");
                }
            }

            //Actually doing stuff with said input.
            if(input.toLowerCase().equals("quit") || input.toLowerCase().equals("2")) {
                scan.close();//Clse the scanner stream and quit
                break;
            } else if (input.toLowerCase().equals("0")) {
                hostGame(scan);
            } else if (input.toLowerCase().equals("1")) {
                joinGame(scan);
            }
            checkInput = true;
        }

        System.out.println("Quitting...");
        


    }

    private static void hostGame(Scanner scan) {
        System.out.println("Setting up game...");

        String in = "";
        boolean inputCheck = true;

        //Player OBJ
        Player player = new Player();

        //Name
        while(inputCheck) {
            System.out.println("Choose a name (max 20 char) : ");
            in = scan.next();
            if (in.length() > 20) {
                System.out.println("max 20 char.");
                in = "";
            } else {
                inputCheck = false;
            }
        }
        player.setName(in);

        //port
        Socket other = null;
        ServerSocket ss = null;
        //Check for input
    
        try {
               
            ss = new ServerSocket(0);
            System.out.println("Game launched on port " + ss.getLocalPort() + ".\nWaiting for player to connect.");

            other = ss.accept();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //launch the dam thing
        
        new MainLoop().mainLoop(other, player);
        
    }

    private static void joinGame(Scanner scan) {
        String in = "";
        boolean inputCheck = true;

        //Player OBJ
        Player player = new Player();

        //Name
        while(inputCheck) {
            System.out.println("Choose a name (max 20 char) : ");
            in = scan.next();
            if (in.length() > 20) {
                System.out.println("max 20 char.");
                in = "";
            } else {
                inputCheck = false;
            }
        }
        player.setName(in);

        //Port
        int port = 0;
        String ip = "";
        String tmp = "";
        inputCheck = true;

        Socket s = null;

        while(inputCheck) {
            System.out.println("Indicate game host and port (IPADRESS:PORT) : ");
            tmp = scan.next();
            
            String[] tmpArray = tmp.split(":");

            try {
                ip = tmpArray[0];
                port = Integer.parseInt(tmpArray[1]);

                s = new Socket(ip, port);
                inputCheck = false;

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Please follow the template IPADRESS:PORT\n");
                inputCheck = true;
            } catch (IOException e) {
                System.out.println("Can't create socket :\nCheck internet connection\nCheck IP and Port.\n");
                inputCheck = true;
            }
        }
        System.out.println("Connection established.\n");

        new MainLoop().mainLoop(s, player);    
    }
}
