package Lab6.Bai9;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AuctionServer {
    public static void main(String[] args) {
        try {
            int port = 1099;
            Registry registry;
            try {
                registry = LocateRegistry.createRegistry(port);
            } catch (Exception e) {
                registry = LocateRegistry.getRegistry(port);
            }

            AuctionService service = new AuctionServiceImpl();
            registry.rebind("AuctionService", service);

            System.out.println("=================================================");
            System.out.println("     HE THONG DAU GIA TRUC TUYEN RMI SERVER      ");
            System.out.println("=================================================");
            System.out.println("Cong RMI: " + port);
            System.out.println("Dich vu 'AuctionService' da dang ky thanh cong!");
            System.out.println("Server dang quan ly danh sach san pham va gia thau.");
            System.out.println("San sang cap nhat thoi gian thuc...");
        } catch (Exception e) {
            System.err.println("Loi khoi dong Auction Server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
