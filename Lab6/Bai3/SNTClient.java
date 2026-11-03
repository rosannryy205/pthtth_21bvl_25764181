package Lab6.Bai3;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class SNTClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 1099;

        try (Scanner scanner = new Scanner(System.in)) {
            Registry registry = LocateRegistry.getRegistry(host, port);
            
            SNTService service = (SNTService) registry.lookup("SNTService");

            System.out.print("Nhap vao so nguyen can kiem tra: ");
            int n = scanner.nextInt();

            boolean result = service.SNT(n);

            if (result) {
                System.out.println("Ket qua: " + n + " la so nguyen to.");
            } else {
                System.out.println("Ket qua: " + n + " khong phai la so nguyen to.");
            }
        } catch (Exception e) {
            System.err.println("Loi Client: " + e.getMessage());
            e.printStackTrace();
        }
    }
}