package Lab6.Bai2;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorServer {
    public static void main(String[] args) {
        try {
            int port = 1099;

            // Khoi tao registry chay port 1099
            Registry registry = LocateRegistry.createRegistry(port);

            // Khởi tạo đối tượng Remote
            CalculatorService service = new CalculatorServiceImpl();

            // Đăng ký dịch vụ vào Registry
            registry.rebind("CalculatorService", service);

            System.out.println("Server RMI da khoi dong tai cong " + port + " va san sang phuc vu!");
        } catch (Exception e) {
            System.err.println("Loi khoi dong Server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
