package Lab4.src.baitap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class bai2_DigitToWordClient {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        String host = "127.0.0.1";
        int port = 5000;
        try (
                Socket socket = new Socket(host, port);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                PrintWriter out = new PrintWriter(
                        new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
                Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8.name())
            ) {
                System.out.println("Da ket noi toi Server, nhap 1 so 0-9 hoac QUIT de thoat.");

                while (true) {
                    System.out.println("Nhap");
                    String input = sc.nextLine();

                    out.println(input);

                    if(input.equals("QUIT")){
                        System.out.println("Dang ngat ket noi...");
                        break;
                    }

                    String response = in.readLine();
                    System.out.println("Server tra ve: " + response);
                }
        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        }
    }
}
