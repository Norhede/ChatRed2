import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class NanoTCPServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5555);
            Socket socket = serverSocket.accept(); // blokerer

            OutputStream outputStream = socket.getOutputStream();

            PrintWriter pw = new PrintWriter(outputStream, true);

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader brIn = new BufferedReader(new InputStreamReader(System.in));

            pw.println("Chat er startet!");

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