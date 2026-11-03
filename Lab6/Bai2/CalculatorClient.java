package Lab6.Bai2;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class CalculatorClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 1099;

        try (Scanner scanner = new Scanner(System.in)) {
            // Kết nối đến RMI Registry
            Registry registry = LocateRegistry.getRegistry(host, port);

            // Tra cứu dịch vụ
            CalculatorService service = (CalculatorService) registry.lookup("CalculatorService");

            // Nhập dữ liệu từ bàn phím
            System.out.print("Nhap so a: ");
            int a = scanner.nextInt();

            System.out.print("Nhap so b: ");
            int b = scanner.nextInt();

            // Gọi phương thức từ xa
            int sum = service.add(a, b);

            System.out.println("Ket qua tu Server: " + a + " + " + b + " = " + sum);
        } catch (Exception e) {
            System.err.println("Loi Client: " + e.getMessage());
            e.printStackTrace();
        }
    }
}