package Lab6.Bai5;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ChatServer {
    public static void main(String[] args) {
        try {
            int port = 1099;
            Registry registry;
            try {
                registry = LocateRegistry.createRegistry(port);
            } catch (Exception e) {
                registry = LocateRegistry.getRegistry(port);
            }

            ChatService service = new ChatServiceImpl();
            registry.rebind("ChatService", service);

            System.out.println("==========================================");
            System.out.println("       CHAT SERVER RMI DANG CHAY          ");
            System.out.println("==========================================");
            System.out.println("Cong RMI: " + port);
            System.out.println("Dich vu 'ChatService' da duoc dang ky thanh cong!");
            System.out.println("Dang cho tin nhan tu Client...");
        } catch (Exception e) {
            System.err.println("Loi khoi dong Chat Server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
