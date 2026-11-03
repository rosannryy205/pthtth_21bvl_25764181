package Lab6.Bai5;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 1099;

        try (Scanner scanner = new Scanner(System.in)) {
            Registry registry = LocateRegistry.getRegistry(host, port);
            ChatService service = (ChatService) registry.lookup("ChatService");

            System.out.println("==========================================");
            System.out.println("       UNG DUNG CHAT 1-1 VOI RMI          ");
            System.out.println("==========================================");
            System.out.println("Ket noi den Chat Server thanh cong!");
            System.out.println("Nhap tin nhan (go 'bye' hoac 'exit' de thoat):");

            while (true) {
                System.out.print("\nClient: ");
                String message = scanner.nextLine().trim();

                if (message.isEmpty()) {
                    continue;
                }

                // Goi phuong thuc sendMessage tren Server
                service.sendMessage(message);

                // Nhan va hien thi phan hoi tu Server
                String response = service.getResponse();
                System.out.println(response);

                if (message.equalsIgnoreCase("bye") || message.equalsIgnoreCase("exit") || message.equalsIgnoreCase("tam biet")) {
                    System.out.println("-> Da dong phien chat!");
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Loi Client: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
