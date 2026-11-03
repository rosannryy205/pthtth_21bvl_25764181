package Lab6.Bai3;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SNTServer {
    public static void main(String[] args) {
        try {
            int port = 1099;
            Registry registry = LocateRegistry.createRegistry(port);

            SNTService service = new SNTServiceImpl();
            registry.rebind("SNTService", service);

            System.out.println("RMI Server dang chay tai cong " + port + "...");
        } catch (Exception e) {
            System.err.println("Loi Server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
