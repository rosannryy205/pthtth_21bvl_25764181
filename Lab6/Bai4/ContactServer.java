package Lab6.Bai4;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ContactServer {
    public static void main(String[] args) {
        try {
            int port = 1099;

            // Khoi tao hoac lay RMI Registry tai cong 1099
            Registry registry;
            try {
                registry = LocateRegistry.createRegistry(port);
            } catch (Exception e) {
                registry = LocateRegistry.getRegistry(port);
            }

            // Khoi tao doi tuong remote
            ContactService service = new ContactServiceImpl();

            // Dang ky dich vu voi ten "ContactService"
            registry.rebind("ContactService", service);

            System.out.println("Contact Server RMI dang chay tai cong " + port + " va san sang phuc vu!");
        } catch (Exception e) {
            System.err.println("Loi khoi dong Server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
