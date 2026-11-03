package Lab6.Bai8;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class FlightBookingServer {
    public static void main(String[] args) {
        try {
            int port = 1099;
            Registry registry;
            try {
                registry = LocateRegistry.createRegistry(port);
            } catch (Exception e) {
                registry = LocateRegistry.getRegistry(port);
            }

            FlightBookingService service = new FlightBookingServiceImpl();
            registry.rebind("FlightBookingService", service);

            System.out.println("==========================================");
            System.out.println("   HE THONG DAT VE MAY BAY RMI SERVER     ");
            System.out.println("==========================================");
            System.out.println("Cong RMI: " + port);
            System.out.println("Dich vu 'FlightBookingService' da dang ky thanh cong!");
            System.out.println("Cac chuyen bay mau da khoi tao:");
            System.out.println(" - VN123: Ha Noi - TP.HCM (50 ghe)");
            System.out.println(" - VJ456: Da Nang - Ha Noi (30 ghe)");
            System.out.println(" - QH789: TP.HCM - Phu Quoc (20 ghe)");
            System.out.println(" - VN302: Ha Noi - Tokyo (40 ghe)");
            System.out.println("San sang phuc vu yeu cau dat ve...");
        } catch (Exception e) {
            System.err.println("Loi khoi dong Flight Booking Server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
