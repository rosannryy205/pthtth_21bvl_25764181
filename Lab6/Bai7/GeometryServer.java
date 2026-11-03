package Lab6.Bai7;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class GeometryServer {
    public static void main(String[] args) {
        try {
            int port = 1099;
            Registry registry;
            try {
                registry = LocateRegistry.createRegistry(port);
            } catch (Exception e) {
                registry = LocateRegistry.getRegistry(port);
            }

            GeometryService service = new GeometryServiceImpl();
            registry.rebind("GeometryService", service);

            System.out.println("==========================================");
            System.out.println("     TINH DIEN TICH HINH HOC RMI SERVER   ");
            System.out.println("==========================================");
            System.out.println("Cong RMI: " + port);
            System.out.println("Dich vu 'GeometryService' da dang ky thanh cong!");
            System.out.println("San sang tinh toan dien tich theo yeu cau...");
        } catch (Exception e) {
            System.err.println("Loi khoi dong Geometry Server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
