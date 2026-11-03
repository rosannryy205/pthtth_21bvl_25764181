package Lab6.Bai8;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Map;
import java.util.Scanner;

public class FlightBookingClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 1099;

        try (Scanner scanner = new Scanner(System.in)) {
            Registry registry = LocateRegistry.getRegistry(host, port);
            FlightBookingService service = (FlightBookingService) registry.lookup("FlightBookingService");
            System.out.println("Ket noi den He thong Dat ve may bay (RMI) thanh cong!");

            while (true) {
                System.out.println("\n==========================================");
                System.out.println("     HE THONG QUAN LY DAT VE MAY BAY     ");
                System.out.println("==========================================");
                System.out.println("1. Xem danh sach tat ca chuyen bay & so ghe trong");
                System.out.println("2. Kiem tra so ghe trong theo ma chuyen bay");
                System.out.println("3. Dat ve may bay (bookTicket)");
                System.out.println("4. Huy dat ve may bay (cancelBooking)");
                System.out.println("0. Thoat");
                System.out.print("Vui long chon chuc nang (0-4): ");

                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1": {
                        Map<String, Integer> flights = service.getAllFlights();
                        System.out.println("\n--- DANH SACH CHUYEN BAY HIEN CO ---");
                        System.out.printf("%-15s | %-15s%n", "Ma chuyen bay", "So ghe con trong");
                        System.out.println("------------------------------------");
                        for (Map.Entry<String, Integer> entry : flights.entrySet()) {
                            System.out.printf("%-15s | %-15d%n", entry.getKey(), entry.getValue());
                        }
                        break;
                    }
                    case "2": {
                        System.out.print("Nhap ma chuyen bay can kiem tra (vi du: VN123): ");
                        String fn = scanner.nextLine().trim();
                        if (fn.isEmpty()) {
                            System.out.println("-> Ma chuyen bay khong duoc de trong!");
                            break;
                        }
                        int seats = service.availableSeats(fn);
                        if (seats >= 0) {
                            System.out.println("-> Chuyen bay " + fn.toUpperCase() + " con trong: " + seats + " ghe.");
                        } else {
                            System.out.println("-> Khong tim thay chuyen bay co ma: " + fn);
                        }
                        break;
                    }
                    case "3": {
                        System.out.print("Nhap ma chuyen bay can dat ve: ");
                        String fn = scanner.nextLine().trim();
                        System.out.print("Nhap so luong ghe can dat: ");
                        try {
                            int seats = Integer.parseInt(scanner.nextLine().trim());
                            if (seats <= 0) {
                                System.out.println("-> So luong ghe phai lon hon 0!");
                                break;
                            }
                            boolean success = service.bookTicket(fn, seats);
                            if (success) {
                                System.out.println("-> Dat ve thanh cong cho chuyen bay " + fn.toUpperCase() + " (" + seats + " ghe)!");
                                int rem = service.availableSeats(fn);
                                System.out.println("-> So ghe con lai: " + rem);
                            } else {
                                System.out.println("-> Dat ve that bai! Chuyen bay khong ton tai hoac khong du so ghe trong.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("-> So luong ghe phai la so nguyen hop le!");
                        } catch (Exception e) {
                            System.out.println("-> Loi khi dat ve: " + e.getMessage());
                        }
                        break;
                    }
                    case "4": {
                        System.out.print("Nhap ma chuyen bay can huy ve: ");
                        String fn = scanner.nextLine().trim();
                        System.out.print("Nhap so luong ghe can huy: ");
                        try {
                            int seats = Integer.parseInt(scanner.nextLine().trim());
                            if (seats <= 0) {
                                System.out.println("-> So luong ghe phai lon hon 0!");
                                break;
                            }
                            boolean success = service.cancelBooking(fn, seats);
                            if (success) {
                                System.out.println("-> Huy ve thanh cong cho chuyen bay " + fn.toUpperCase() + " (" + seats + " ghe)!");
                                int rem = service.availableSeats(fn);
                                System.out.println("-> So ghe con lai sau khi huy: " + rem);
                            } else {
                                System.out.println("-> Huy ve that bai! Chuyen bay khong ton tai hoac so ve huy vuot qua so ve da dat.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("-> So luong ghe phai la so nguyen hop le!");
                        } catch (Exception e) {
                            System.out.println("-> Loi khi huy ve: " + e.getMessage());
                        }
                        break;
                    }
                    case "0": {
                        System.out.println("Cam on ban da su dung he thong Dat ve! Tam biet.");
                        return;
                    }
                    default:
                        System.out.println("-> Lua chon khong hop le! Vui long chon tu 0 den 4.");
                }
            }
        } catch (Exception e) {
            System.err.println("Loi Client: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
