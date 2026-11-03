package Lab6.Bai9;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class AuctionClient {

    // Lop nhan callback thong bao thoi gian thuc tu Server
    public static class ClientCallbackImpl extends UnicastRemoteObject implements AuctionCallback {
        public ClientCallbackImpl() throws RemoteException {
            super();
        }

        @Override
        public void notifyNewBid(String message) throws RemoteException {
            System.out.println("\n>>> [THONG BAO REAL-TIME] " + message);
            System.out.print("Vui long chon chuc nang (0-5): ");
        }
    }

    public static void main(String[] args) {
        String host = "localhost";
        int port = 1099;
        DecimalFormat df = new DecimalFormat("#,##0.00");

        try (Scanner scanner = new Scanner(System.in)) {
            Registry registry = LocateRegistry.getRegistry(host, port);
            AuctionService service = (AuctionService) registry.lookup("AuctionService");
            System.out.println("Ket noi den He thong Dau gia truc tuyen (RMI) thanh cong!");

            // Khoi tao callback de nhan thong bao khi co gia thau moi
            ClientCallbackImpl callback = new ClientCallbackImpl();
            try {
                service.registerCallback(callback);
            } catch (Exception e) {
                System.out.println("Chu y: Khong the dang ky kenh thong bao real-time (" + e.getMessage() + ")");
            }

            System.out.print("Nhap ten cua ban: ");
            String userName = scanner.nextLine().trim();
            if (userName.isEmpty()) {
                userName = "NguoiDung_" + System.currentTimeMillis() % 1000;
            }
            System.out.println("Xin chao, " + userName + "!");

            while (true) {
                System.out.println("\n=================================================");
                System.out.println("       HE THONG DAU GIA TRUC TUYEN (RMI)         ");
                System.out.println("=================================================");
                System.out.println("1. [Seller] Them san pham vao he thong dau gia");
                System.out.println("2. [Bidder] Xem danh sach san pham dang dau gia");
                System.out.println("3. [Bidder] Dat gia thau (Bid) cho san pham");
                System.out.println("4. [Chung]  Xem chi tiet 1 san pham");
                System.out.println("5. [Chung]  Xem lich su cac thong bao dau gia");
                System.out.println("0. Thoat");
                System.out.print("Vui long chon chuc nang (0-5): ");

                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1": {
                        System.out.println("\n--- [SELLER] THEM SAN PHAM DAU GIA ---");
                        System.out.print("Nhap ten san pham: ");
                        String productName = scanner.nextLine().trim();
                        if (productName.isEmpty()) {
                            System.out.println("-> Ten san pham khong duoc de trong!");
                            break;
                        }

                        System.out.print("Nhap gia khoi diem (VND): ");
                        try {
                            double startingPrice = Double.parseDouble(scanner.nextLine().trim());
                            if (startingPrice <= 0) {
                                System.out.println("-> Gia khoi diem phai lon hon 0!");
                                break;
                            }

                            int newId = service.addProduct(productName, startingPrice, userName);
                            System.out.println("-> Them san pham thanh cong! Ma san pham (ID): " + newId);
                        } catch (NumberFormatException e) {
                            System.out.println("-> Gia khoi diem khong hop le!");
                        } catch (Exception e) {
                            System.out.println("-> Loi khi them san pham: " + e.getMessage());
                        }
                        break;
                    }
                    case "2": {
                        System.out.println("\n--- [BIDDER] DANH SACH SAN PHAM DANG DAU GIA ---");
                        List<Product> list = service.getProducts();
                        if (list.isEmpty()) {
                            System.out.println("Hien tai chua co san pham nao trong he thong.");
                        } else {
                            System.out.printf("%-5s | %-28s | %-16s | %-16s | %-15s | %-15s%n",
                                    "ID", "Ten san pham", "Gia khoi diem", "Gia cao nhat", "Nguoi giu gia", "Nguoi ban");
                            System.out.println("-----------------------------------------------------------------------------------------------------------------");
                            for (Product p : list) {
                                System.out.printf("%-5d | %-28s | %16s | %16s | %-15s | %-15s%n",
                                        p.getId(), p.getName(),
                                        df.format(p.getStartingPrice()) + " d",
                                        df.format(p.getHighestBid()) + " d",
                                        p.getHighestBidder(),
                                        p.getSeller());
                            }
                        }
                        break;
                    }
                    case "3": {
                        System.out.println("\n--- [BIDDER] DAT GIA THAU (BID) ---");
                        System.out.print("Nhap Ma san pham (ID) muon dau gia: ");
                        try {
                            int productId = Integer.parseInt(scanner.nextLine().trim());
                            Product p = service.getProduct(productId);
                            if (p == null) {
                                System.out.println("-> Khong tim thay san pham voi ID: " + productId);
                                break;
                            }

                            System.out.println("-> San pham: " + p.getName());
                            System.out.println("-> Gia khoi diem: " + df.format(p.getStartingPrice()) + " VND");
                            System.out.println("-> Gia cao nhat hien tai: " + df.format(p.getHighestBid()) + " VND (Boi: " + p.getHighestBidder() + ")");
                            System.out.print("Nhap gia thau cua ban (phai > " + df.format(p.getHighestBid()) + " VND): ");

                            double bidAmount = Double.parseDouble(scanner.nextLine().trim());
                            if (bidAmount <= p.getHighestBid()) {
                                System.out.println("-> Gia thau phai cao hon gia cao nhat hien tai (" + df.format(p.getHighestBid()) + " VND)!");
                                break;
                            }

                            boolean success = service.placeBid(productId, bidAmount, userName);
                            if (success) {
                                System.out.println("-> Ban da dat gia thau thanh cong! Ban hien dang la nguoi tra gia cao nhat.");
                            } else {
                                System.out.println("-> Dat gia thau that bai! Co the da co nguoi khac dat gia cao hon.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("-> Du lieu nhap vao khong hop le!");
                        } catch (Exception e) {
                            System.out.println("-> Loi khi dat gia thau: " + e.getMessage());
                        }
                        break;
                    }
                    case "4": {
                        System.out.print("Nhap Ma san pham (ID) can xem: ");
                        try {
                            int productId = Integer.parseInt(scanner.nextLine().trim());
                            Product p = service.getProduct(productId);
                            if (p == null) {
                                System.out.println("-> Khong tim thay san pham voi ID: " + productId);
                            } else {
                                System.out.println("\n--- CHI TIET SAN PHAM #" + p.getId() + " ---");
                                System.out.println("Ten san pham:       " + p.getName());
                                System.out.println("Nguoi ban:          " + p.getSeller());
                                System.out.println("Gia khoi diem:      " + df.format(p.getStartingPrice()) + " VND");
                                System.out.println("Gia cao nhat:       " + df.format(p.getHighestBid()) + " VND");
                                System.out.println("Nguoi tra cao nhat: " + p.getHighestBidder());
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("-> ID san pham phai la so nguyen!");
                        } catch (Exception e) {
                            System.out.println("-> Loi: " + e.getMessage());
                        }
                        break;
                    }
                    case "5": {
                        System.out.println("\n--- LICH SU CAC THONG BAO DAU GIA ---");
                        List<String> logs = service.getNotificationLogs();
                        if (logs.isEmpty()) {
                            System.out.println("Chua co thong bao nao.");
                        } else {
                            for (String log : logs) {
                                System.out.println(log);
                            }
                        }
                        break;
                    }
                    case "0": {
                        try {
                            service.unregisterCallback(callback);
                            UnicastRemoteObject.unexportObject(callback, true);
                        } catch (Exception ignored) {
                        }
                        System.out.println("Cam on ban da tham gia Dau gia! Tam biet.");
                        return;
                    }
                    default:
                        System.out.println("-> Lua chon khong hop le! Vui long chon tu 0 den 5.");
                }
            }
        } catch (Exception e) {
            System.err.println("Loi Client: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
