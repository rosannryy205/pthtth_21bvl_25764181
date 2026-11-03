package Lab6.Bai1;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class HelloServer {
    public static void main(String[] args) {
        try {
            // Khoi tao RMI Registry tai cong 1099
            Registry registry = LocateRegistry.createRegistry(1099);

            // Tao doi tuong thuc thi
            HelloService service = new HelloServiceImpl();

            // Dang ky dich vu voi ten "HelloService"
            registry.rebind("HelloService", service);

            System.out.println("RMI Server đang chạy và sẵn sàng nhận kết nối...");
        } catch (Exception e) {
            System.err.println("Lỗi Server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
