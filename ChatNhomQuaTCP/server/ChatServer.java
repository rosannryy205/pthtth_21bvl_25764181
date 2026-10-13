package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// Lop chinh khoi chay Server TCP
public class ChatServer {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        // Khoi tao danh sach cac phong chat mac dinh
        RoomManager.initRooms();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("=== SERVER CHAT TCP DA KHOI DONG (PORT: " + PORT + ") ===");

            // Vong lap lien tuc cho va chap nhan Client ket noi
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Co client moi ket noi tu: " + socket.getRemoteSocketAddress());

                // Tao luong ClientHandler rieng phuc vu cho client nay
                ClientHandler handler = new ClientHandler(socket);
                handler.start();
            }
        } catch (IOException e) {
            System.out.println("Loi Server: " + e.getMessage());
        }
    }
}
