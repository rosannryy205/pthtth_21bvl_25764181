package Lab6.Bai7;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class GeometryClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 1099;

        try (Scanner scanner = new Scanner(System.in)) {
            Registry registry = LocateRegistry.getRegistry(host, port);
            GeometryService service = (GeometryService) registry.lookup("GeometryService");
            System.out.println("Ket noi den He thong Tinh Dien Tich tu xa (RMI) thanh cong!");

            while (true) {
                System.out.println("\n==========================================");
                System.out.println("     CHUONG TRINH TINH DIEN TICH HINH HOC ");
                System.out.println("==========================================");
                System.out.println("1. Tinh dien tich Hinh chu nhat");
                System.out.println("2. Tinh dien tich Hinh tron");
                System.out.println("3. Tinh dien tich Hinh tam giac");
                System.out.println("0. Thoat");
                System.out.print("Vui long chon loai hinh (0-3): ");

                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1": {
                        try {
                            System.out.print("Nhap chieu rong: ");
                            double width = Double.parseDouble(scanner.nextLine().trim());
                            System.out.print("Nhap chieu dai: ");
                            double height = Double.parseDouble(scanner.nextLine().trim());

                            if (width < 0 || height < 0) {
                                System.out.println("-> Chieu rong va chieu dai phai >= 0!");
                                break;
                            }

                            double area = service.rectangleArea(width, height);
                            System.out.printf("-> Dien tich Hinh chu nhat (%.2f x %.2f) la: %.4f%n", width, height, area);
                        } catch (NumberFormatException e) {
                            System.out.println("-> Gia tri nhap vao khong hop le!");
                        } catch (Exception e) {
                            System.out.println("-> Loi tinh toan: " + e.getMessage());
                        }
                        break;
                    }
                    case "2": {
                        try {
                            System.out.print("Nhap ban kinh (r): ");
                            double radius = Double.parseDouble(scanner.nextLine().trim());

                            if (radius < 0) {
                                System.out.println("-> Ban kinh phai >= 0!");
                                break;
                            }

                            double area = service.circleArea(radius);
                            System.out.printf("-> Dien tich Hinh tron (r = %.2f) la: %.4f%n", radius, area);
                        } catch (NumberFormatException e) {
                            System.out.println("-> Gia tri nhap vao khong hop le!");
                        } catch (Exception e) {
                            System.out.println("-> Loi tinh toan: " + e.getMessage());
                        }
                        break;
                    }
                    case "3": {
                        try {
                            System.out.print("Nhap do dai day: ");
                            double base = Double.parseDouble(scanner.nextLine().trim());
                            System.out.print("Nhap chieu cao: ");
                            double height = Double.parseDouble(scanner.nextLine().trim());

                            if (base < 0 || height < 0) {
                                System.out.println("-> Do dai day va chieu cao phai >= 0!");
                                break;
                            }

                            double area = service.triangleArea(base, height);
                            System.out.printf("-> Dien tich Hinh tam giac (day=%.2f, cao=%.2f) la: %.4f%n", base, height, area);
                        } catch (NumberFormatException e) {
                            System.out.println("-> Gia tri nhap vao khong hop le!");
                        } catch (Exception e) {
                            System.out.println("-> Loi tinh toan: " + e.getMessage());
                        }
                        break;
                    }
                    case "0": {
                        System.out.println("Tam biet va hen gap lai!");
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
