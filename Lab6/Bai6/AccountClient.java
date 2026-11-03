package Lab6.Bai6;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.DecimalFormat;
import java.util.Scanner;

public class AccountClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 1099;
        DecimalFormat df = new DecimalFormat("#,##0.00");

        try (Scanner scanner = new Scanner(System.in)) {
            Registry registry = LocateRegistry.getRegistry(host, port);
            AccountService service = (AccountService) registry.lookup("AccountService");
            System.out.println("Ket noi den He thong Ngan hang tu xa (RMI) thanh cong!");

            while (true) {
                System.out.println("\n==========================================");
                System.out.println("     HE THONG QUAN LY TAI KHOAN NGAN HANG ");
                System.out.println("==========================================");
                System.out.println("1. Kiem tra so du (getBalance)");
                System.out.println("2. Gui tien vao tai khoan (deposit)");
                System.out.println("3. Rut tien tu tai khoan (withdraw)");
                System.out.println("0. Thoat");
                System.out.print("Vui long chon chuc nang (0-3): ");

                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1": {
                        double currentBalance = service.getBalance();
                        System.out.println("-> So du hien tai cua ban la: " + df.format(currentBalance) + " VND");
                        break;
                    }
                    case "2": {
                        System.out.print("Nhap so tien muon gui (VND): ");
                        try {
                            double amount = Double.parseDouble(scanner.nextLine().trim());
                            if (amount <= 0) {
                                System.out.println("-> So tien gui phai lon hon 0!");
                                break;
                            }
                            service.deposit(amount);
                            System.out.println("-> Gui tien thanh cong!");
                            System.out.println("-> So du moi: " + df.format(service.getBalance()) + " VND");
                        } catch (NumberFormatException e) {
                            System.out.println("-> So tien nhap khong hop le!");
                        } catch (Exception e) {
                            System.out.println("-> Loi khi gui tien: " + e.getMessage());
                        }
                        break;
                    }
                    case "3": {
                        System.out.print("Nhap so tien muon rut (VND): ");
                        try {
                            double amount = Double.parseDouble(scanner.nextLine().trim());
                            if (amount <= 0) {
                                System.out.println("-> So tien rut phai lon hon 0!");
                                break;
                            }
                            boolean success = service.withdraw(amount);
                            if (success) {
                                System.out.println("-> Rut tien thanh cong! Vui long nhan tien.");
                                System.out.println("-> So du con lai: " + df.format(service.getBalance()) + " VND");
                            } else {
                                System.out.println("-> Rut tien that bai! So du khong du hoac so tien khong hop le.");
                                System.out.println("-> So du hien tai: " + df.format(service.getBalance()) + " VND");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("-> So tien nhap khong hop le!");
                        } catch (Exception e) {
                            System.out.println("-> Loi khi rut tien: " + e.getMessage());
                        }
                        break;
                    }
                    case "0": {
                        System.out.println("Cam on ban da su dung dich vu Ngan hang! Tam biet.");
                        return;
                    }
                    default:
                        System.out.println("-> Lua chon khong hop le! Vui long chon tu 0 den 3.");
                }
            }
        } catch (Exception e) {
            System.err.println("Loi Client: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
