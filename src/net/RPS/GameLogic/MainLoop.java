package net.RPS.GameLogic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MainLoop {

    public MainLoop(){
        Moves.innitMoves();
    }

    public void mainLoop(Socket socket, Player player1) {
        Moves.innitMoves();

        Scanner scan = new Scanner(System.in);
        Player player2 = new Player();

        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());


            //Setup player2
            //send the p1 data (wich is p2 on the other side)
            out.writeUTF(player1.getName());
            //Get the data from the other side (wich is p2 here)
            player2.setName(in.readUTF());

            System.out.println("You are playing with : " +player2.getName() + " !");

            String received = ""; //The thing that we recieve
            String toSend = ""; //What we need to send :p

            //The actual main loop xd
            while(!(received.toLowerCase().equals("quit"))) {
                boolean inputCheck = true;

                while (inputCheck) {

                    //Ask for input
                    System.out.println("Rock(r) / Paper(p) / Scissor(s) (or quit :p) : ");
                    toSend = scan.next();

                    if (isIn(toSend.toLowerCase(), new String[] {"r", "p", "s", "quit"})) {
                        inputCheck = false;
                    } else {
                        System.out.println("Please enter valid input.");
                        toSend = "";
                        inputCheck = true;
                    }
                
                }

                out.writeUTF(toSend);
               
                //recieve
                received = in.readUTF();

                if (!toSend.equals("quit") || !received.equals("quit")) {

                    //Process the damn thing finally !
                    int duelResult = Moves.processDuel(toSend, received);

                    //Set score
                    if (duelResult == 0) {
                        player1.setScore(player1.getScore()+1);
                    } else if (duelResult == 1){
                        player2.setScore(player2.getScore()+1);
                    }


                    //Send back the data (score and stuff(mostly score...))
                    out.writeInt(player1.getScore());

                    //Receive the le data
                    player2.setScore(in.readInt());

                    //display
                    display(toSend, received, duelResult, player1, player2);

                } else {
                    System.out.println("Game ended.");
                    //TODO : Display Scores uwu
                }

            
        }

        socket.close();
        in.close();
        out.close();
        scan.close();

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

    public static boolean isIn(String check, String[] l) {
        for(String el : l) {
            if (check.equals(el)) {
                return true;
            }
        }
        return false;
    }
}
