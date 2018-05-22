import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.io.*;

public class Red3O {
    static String[] gameBoard = {"*", "*", "*", "*", "*", "*", "*", "*", "*"};
    public static boolean checkMove(String position){
        int placement = Integer.parseInt(position);
        if(placement >= 0 && placement < 9) {
            if(gameBoard[placement].equals("*")) {
                return true;
            }
            else {
                System.out.println("Der er allerede sat en brik på position " + position + "!");
            }
        }
        else {
            System.out.println("Den position eksisterer ikke på spillebrættet!");
        }
        return false;
    }

    public static void insertX(String position){
        int placement = Integer.parseInt(position);
        gameBoard[placement] = "X";
        printBoard();
    }

    public static void insertO(String position){
        int placement = Integer.parseInt(position);
        gameBoard[placement] = "O";
        printBoard();
    }

    public static void printBoard(){
        System.out.println("");
        System.out.println("Spillebrættet ser således ud:");
        System.out.println(gameBoard[0] + gameBoard[1] +gameBoard[2]);
        System.out.println(gameBoard[3] + gameBoard[4] +gameBoard[5]);
        System.out.println(gameBoard[6] + gameBoard[7] +gameBoard[8]);
    }

    public static void main(String[] args) {
        String[] gameBoard = {"*", "*", "*", "*", "*", "*", "*", "*", "*"};
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            System.out.println("Opretter forbindelse til server");

            Socket socket = new Socket(ip, 5555);
            System.out.println("Forbindelse oprettet!");

            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader brIn = new BufferedReader(new InputStreamReader(System.in));

            while(true){
                if(br.ready()){
                    // Her modtager jeg X fra modstanderen
                    String next = br.readLine();
                    insertX(next);
                }
                if(brIn.ready()){
                    // Her tjekker jeg trækket, og evt. indsætter O
                    String next = brIn.readLine();
                    if(checkMove(next)) {
                        insertO(next);
                        pw.println(next);
                    }
                    else {
                        System.out.println("En fejl er opstået!");
                    }
                }
                Thread.sleep(100);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}