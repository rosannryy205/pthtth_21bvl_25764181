package server;

import java.io.*;
import java.net.*;

// Luong rieng xu ly giao tiep cho tung Client
public class ClientHandler extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String name;
    private String currentRoom;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // Thiet lap luong I/O de nhan va gui du lieu chuoi
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);

            // 1. Nhan ten Client dang ky ngay sau khi ket noi
            name = in.readLine();
            System.out.println("Client dang ky ten: " + name);

            // Vong lap chinh: chon phong va chat
            while (true) {
                // Gui danh sach phong hien co cho Client
                out.println("\n--- DANH SACH PHONG CHAT: " + RoomManager.getRoomList() + " ---");
                out.println("Nhap ten phong muon vao (hoac nhap ten moi de tao phong):");

                // Doc ten phong client muon tham gia
                String chosenRoom = in.readLine();
                if (chosenRoom == null || chosenRoom.equalsIgnoreCase("/exit")) {
                    break; // Thoat neu client ngat ket noi hoac go /exit
                }

                chosenRoom = chosenRoom.trim();
                if (chosenRoom.isEmpty()) continue;
                if (chosenRoom.equalsIgnoreCase("/quit")) {
                    out.println("Ban chua vao phong nao de /quit. Vui long chon mot phong!");
                    continue;
                }

                // Vao phong chat
                currentRoom = chosenRoom;
                RoomManager.joinRoom(currentRoom, this);
                out.println(">>> Ban da vao phong [" + currentRoom + "]. Go '/quit' de doi phong, '/exit' de thoat. <<<");
                RoomManager.broadcast(currentRoom, ">>> [" + name + "] da tham gia phong chat! <<<", this);

                // Vong lap nhan va phat tin nhan trong phong
                String msg;
                while ((msg = in.readLine()) != null) {
                    msg = msg.trim();

                    // Lenh /quit: Roi phong hien tai de chon phong khac
                    if (msg.equalsIgnoreCase("/quit")) {
                        leaveCurrentRoom();
                        break; // Thoat vong lap chat de quay lai vong lap chon phong
                    }

                    // Lenh /exit: Thoat hoan toan
                    if (msg.equalsIgnoreCase("/exit")) {
                        leaveCurrentRoom();
                        return; // Ket thuc thread, nhay xuong finally
                    }

                    // Phat tin nhan den cac thanh vien khac trong CUNG phong
                    if (!msg.isEmpty()) {
                        RoomManager.broadcast(currentRoom, "[" + currentRoom + "] " + name + ": " + msg, this);
                    }
                }

                // Neu client mat ket noi dot ngot khi dang chat
                if (msg == null) {
                    leaveCurrentRoom();
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Client [" + name + "] da mat ket noi.");
        } finally {
            leaveCurrentRoom();
            closeConnection();
        }
    }

    // Gui tin nhan rieng den Client nay
    public void sendMessage(String message) {
        out.println(message);
    }

    // Roi phong chat hien tai
    private void leaveCurrentRoom() {
        if (currentRoom != null) {
            RoomManager.leaveRoom(currentRoom, this);
            RoomManager.broadcast(currentRoom, ">>> [" + name + "] da roi phong! <<<", this);
            out.println("Ban da roi khoi phong [" + currentRoom + "].");
            currentRoom = null;
        }
    }

    // Dong socket va giai phong tai nguyen
    private void closeConnection() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Da giai phong tai nguyen cua Client [" + name + "].");
    }
}
