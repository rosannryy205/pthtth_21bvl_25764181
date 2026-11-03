package Lab6.Bai6;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AccountServer {
    public static void main(String[] args) {
        try {
            int port = 1099;
            Registry registry;
            try {
                registry = LocateRegistry.createRegistry(port);
            } catch (Exception e) {
                registry = LocateRegistry.getRegistry(port);
            }

            // Khoi tao tai khoan ngan hang voi so du ban dau la 10,000,000 VND
            double initialBalance = 10000000.0;
            AccountService service = new AccountServiceImpl(initialBalance);
            registry.rebind("AccountService", service);

            System.out.println("==========================================");
            System.out.println("     NGAN HANG DIEN TU RMI SERVER         ");
            System.out.println("==========================================");
            System.out.println("Cong RMI: " + port);
            System.out.println("So du ban dau cua tai khoan: 10,000,000.00 VND");
            System.out.println("Dich vu 'AccountService' da san sang phuc vu!");
        } catch (Exception e) {
            System.err.println("Loi khoi dong Bank Server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
