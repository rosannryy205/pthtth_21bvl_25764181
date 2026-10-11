package Lab4.src.baitap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class bai2_DigitToWordServer {
    public static void main(String[] args) {
        int port = 5000;
        String[] words = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server dang chay tren cong: " + port + "...");

            while (true) {
                Socket socket = serverSocket.accept();

                System.out.println("Client da ket noi: " + socket.getInetAddress().getHostAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
                String request;
                while ((request = in.readLine()) != null) {
                    if(request.equals("QUIT")){
                        System.out.println("Client yeu cau ngat ket noi");
                        break;
                    }

                    if(request.length() == 1 && Character.isDigit(request.charAt(0))){
                        int digit = request.charAt(0) - '0';
                        out.println(words[digit]);
                    } else {
                        out.println("ERR INVALID_DIGIT");
                    }
                }
                socket.close();
            }
        } catch (Exception e) {
           System.err.println("ERROR: "+ e.getMessage());
    }
}
}