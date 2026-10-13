package server;

import java.util.*;

// Lop quan ly danh sach phong chat va thanh vien trong tung phong
public class RoomManager {
    // Map luu: Ten Phong (String) -> Danh sach cac ClientHandler trong phong
    private static Map<String, List<ClientHandler>> rooms = new HashMap<>();

    // Khoi tao cac phong mac dinh ban dau
    public static synchronized void initRooms() {
        rooms.put("Study", new ArrayList<>());
        rooms.put("Game", new ArrayList<>());
        rooms.put("TraiNghiem", new ArrayList<>());
    }

    // Dua client vao phong (neu phong chua co thi tu dong tao moi)
    public static synchronized void joinRoom(String roomName, ClientHandler client) {
        if (!rooms.containsKey(roomName)) {
            rooms.put(roomName, new ArrayList<>());
        }
        rooms.get(roomName).add(client);
    }

    // Xoa client khoi phong
    public static synchronized void leaveRoom(String roomName, ClientHandler client) {
        if (roomName != null && rooms.containsKey(roomName)) {
            rooms.get(roomName).remove(client);
        }
    }

    // Gui tin nhan den tat ca client trong CUNG phong (tru nguoi gui)
    public static synchronized void broadcast(String roomName, String message, ClientHandler sender) {
        if (roomName != null && rooms.containsKey(roomName)) {
            for (ClientHandler client : rooms.get(roomName)) {
                if (client != sender) {
                    client.sendMessage(message);
                }
            }
        }
    }

    // Lay chuoi danh sach ten cac phong hien co
    public static synchronized String getRoomList() {
        return rooms.keySet().toString();
    }
}
