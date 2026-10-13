package client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

// Lop chinh khoi chay Client TCP
public class ChatClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== CHUONG TRINH TCP CHAT CLIENT ===");

        // 1. Nhap ten nguoi dung truoc khi ket noi
        System.out.print("Nhap ten cua ban: ");
        String name = scanner.nextLine().trim();
        while (name.isEmpty()) {
            System.out.print("Ten khong duoc de trong, vui long nhap lai: ");
            name = scanner.nextLine().trim();
        }

        try {
            // 2. Mo ket noi Socket toi Server
            Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);

            // Gui ten vua nhap sang Server de dang ky
            out.println(name);

            // 3. Bat luong doc tin nhan tu Server (chay song song, khong nghen ban phim)
            ReadThread readThread = new ReadThread(in);
            readThread.start();

            // 4. Luong chinh (main): Doc du lieu tu ban phim va gui cho Server
            while (true) {
                String userInput = scanner.nextLine();
                out.println(userInput);

                // Neu nguoi dung nhap lenh /exit thi dung chuong trinh
                if (userInput.trim().equalsIgnoreCase("/exit")) {
                    break;
                }
            }

            // Dong socket va ket thuc
            socket.close();
            System.out.println("Da ngat ket noi khoi Server. Tam biet!");

        } catch (IOException e) {
            System.out.println("Loi ket noi toi Server: " + e.getMessage());
        }
    }
}
