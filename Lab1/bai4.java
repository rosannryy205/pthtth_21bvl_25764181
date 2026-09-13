import java.util.Scanner;

public class bai4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Kiểm tra số chẳn lẻ: ");
        System.out.print("Vui lòng nhập số kiểm tra:  ");
        int soKiemTra = scanner.nextInt();
        if (soKiemTra % 2 == 0) {
            System.out.println("Số " + soKiemTra + " là số chẳn");
        } else {
            System.out.println("Số " + soKiemTra + " là số lẻ");
        }
        scanner.close();
    }
}
