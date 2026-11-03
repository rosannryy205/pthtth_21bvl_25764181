package Lab6.Bai4;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ContactClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 1099;

        try (Scanner scanner = new Scanner(System.in)) {
            // Ket noi den RMI Registry
            Registry registry = LocateRegistry.getRegistry(host, port);

            // Tra cuu doi tuong ContactService
            ContactService service = (ContactService) registry.lookup("ContactService");
            System.out.println("Ket noi den Contact Server thanh cong!");

            while (true) {
                System.out.println("\n==========================================");
                System.out.println("   HE THONG QUAN LY DANH BA TU XA (RMI)   ");
                System.out.println("==========================================");
                System.out.println("1. Them lien he moi");
                System.out.println("2. Sua so dien thoai lien he");
                System.out.println("3. Xoa lien he");
                System.out.println("4. Tim kiem lien he");
                System.out.println("0. Thoat");
                System.out.print("Vui long chon chuc nang (0-4): ");

                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1": {
                        System.out.print("Nhap ten lien he: ");
                        String name = scanner.nextLine().trim();
                        System.out.print("Nhap so dien thoai: ");
                        String phone = scanner.nextLine().trim();

                        if (name.isEmpty() || phone.isEmpty()) {
                            System.out.println("-> Ten va so dien thoai khong duoc de trong!");
                            break;
                        }

                        service.addContact(name, phone);
                        System.out.println("-> Them lien he thanh cong!");
                        break;
                    }
                    case "2": {
                        System.out.print("Nhap ten lien he can sua: ");
                        String name = scanner.nextLine().trim();

                        if (name.isEmpty()) {
                            System.out.println("-> Ten khong duoc de trong!");
                            break;
                        }

                        String currentPhone = service.findContact(name);
                        if (currentPhone == null) {
                            System.out.println("-> Khong tim thay lien he co ten: '" + name + "' de sua!");
                        } else {
                            System.out.println("-> Tim thay lien he. So dien thoai hien tai: " + currentPhone);
                            System.out.print("Nhap so dien thoai moi: ");
                            String newPhone = scanner.nextLine().trim();
                            if (newPhone.isEmpty()) {
                                System.out.println("-> So dien thoai moi khong duoc de trong!");
                                break;
                            }
                            service.addContact(name, newPhone);
                            System.out.println("-> Cap nhat so dien thoai cho '" + name + "' thanh cong!");
                        }
                        break;
                    }
                    case "3": {
                        System.out.print("Nhap ten lien he can xoa: ");
                        String name = scanner.nextLine().trim();

                        if (name.isEmpty()) {
                            System.out.println("-> Ten khong duoc de trong!");
                            break;
                        }

                        boolean isDeleted = service.deleteContact(name);
                        if (isDeleted) {
                            System.out.println("-> Da xoa thanh cong lien he: '" + name + "'!");
                        } else {
                            System.out.println("-> Khong tim thay lien he: '" + name + "' de xoa!");
                        }
                        break;
                    }
                    case "4": {
                        System.out.print("Nhap ten lien he can tim: ");
                        String name = scanner.nextLine().trim();

                        if (name.isEmpty()) {
                            System.out.println("-> Ten khong duoc de trong!");
                            break;
                        }

                        String phone = service.findContact(name);
                        if (phone != null) {
                            System.out.println("-> Thong tin lien he: [Ten: " + name + " | SDT: " + phone + "]");
                        } else {
                            System.out.println("-> Khong tim thay lien he co ten: '" + name + "' trong danh ba!");
                        }
                        break;
                    }
                    case "0":
                        System.out.println("Tam biet!");
                        return;

                    default:
                        System.out.println("-> Lua chon khong hop le! Vui long chon tu 0 den 4.");
                        break;
                }
            }
        } catch (Exception e) {
            System.err.println("Loi Client: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
