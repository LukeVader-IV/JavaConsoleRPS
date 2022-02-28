package net.RPS.GameLogic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class MainLoop {

    public MainLoop(){
        Moves.innitMoves();
    }

    public void mainLoop(Socket socket, Player player1) {
        Moves.innitMoves();

        Scanner scan = new Scanner(System.in);
        Player player2 = new Player();
        boolean playing = true;

        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());


            //Setup player2
            //send the p1 data (which is p2 on the other side)
            out.writeUTF(player1.getName());
            //Get the data from the other side (which is p2 here)
            player2.setName(in.readUTF());

            System.out.println("You are playing with : " + player2.getName() + " !");

            String received = ""; //The thing that we recieve
            String toSend = ""; //What we need to send :p

            //The actual main loop xd
            while (playing) {
                boolean inputCheck;

                do {

                    //Ask for input
                    System.out.println("Rock(r) / Paper(p) / Scissor(s) (or quit :p) : ");

                    System.out.print("> ");
                    toSend = scan.next().toLowerCase();
                    System.out.println();

                    inputCheck = !isIn(toSend, "r", "p", "s", "quit");
                    if (inputCheck) {
                        System.out.println("Please enter valid input.");
                        toSend = "";
                    }


                } while (inputCheck);


                if (toSend.equals("quit")) {
                    playing = false;
                    continue;
                    //We don't run the bottom code and go to the next loop cycle.
                    //No need to send the message, it won't be seen.
                }

                try {
                    out.writeUTF(toSend);
                    received = in.readUTF();
                }catch (IOException e){
                    //Connexion has been lost
                    System.out.println("Connexion with "+player2.getName()+" has been lost");
                    playing = false;
                    continue;
                }

                //Process the damn thing finally !
                int duelResult = Moves.processDuel(toSend, received);

                //Set score
                if (duelResult == 0) {
                    player1.setScore(player1.getScore() + 1);
                } else if (duelResult == 1) {
                    player2.setScore(player2.getScore() + 1);
                }


                //Send back the data (score and stuff(mostly score...))
                out.writeInt(player1.getScore());

                //Receive the le data
                player2.setScore(in.readInt());

                //display
                display(toSend, received, duelResult, player1, player2);



            }

            System.out.println("Game ended.");
            //TODO : Display Scores uwu

            socket.close();
            in.close();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        
        
    }

    private void display(String p1Move, String p2Move, int duelResult, Player player1, Player player2) {
        Moves p1 = Moves.getFromString(p1Move);
        Moves p2 = Moves.getFromString(p2Move);

        String str = "";

        str += ("\t\t\t--| " +player1.getName() + " VS " + player2.getName()+" |--\n");
        str += ("Score : " + player1.getScore() + " - " + player2.getScore()+ "\n\n");

        for(int i = 0; i<p1.getASCII().length;++i) {
            str += p1.getASCII()[i] + "\t\t\t" + p2.getASCIIReversed()[i]+"\n";
        }
        str += "\n\n";

        System.out.println(str);





    }

    public static boolean isIn(String check, String... l) {
        for(String el : l) {
            if (check.equals(el)) {
                return true;
            }
        }
        return false;
    }
}
