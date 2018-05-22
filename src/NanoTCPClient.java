import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.io.*;

public class NanoTCPClient {
    public static void main(String[] args) {
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
                    System.out.println(br.readLine());
                }
                if(brIn.ready()){
                    pw.println(brIn.readLine());
                }
                Thread.sleep(100);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}