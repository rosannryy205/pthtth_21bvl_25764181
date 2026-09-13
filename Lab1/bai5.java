import java.util.Scanner;

public class bai5 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Boolean isrun = true;
        while (isrun){
            System.out.print("Vui lòng nhập tháng: ");
            int so = scanner.nextInt();
            switch(so){
                case 1:
                    System.out.println("Tháng 1");
                    break;
                case 2:
                    System.out.println("Tháng 2");
                    break;
                case 3:
                    System.out.println("Tháng 3");
                    break;
                case 4:
                    System.out.println("Tháng 4");
                    break;
                case 5:
                    System.out.println("Tháng 5");
                    break;
                case 6:
                    System.out.println("Tháng 6");
                    break;
                case 7:
                    System.out.println("Tháng 7");
                    break;
                case 8:
                    System.out.println("Tháng 8");
                    break;
                case 9:
                    System.out.println("Tháng 9");
                    break;
                case 10:
                    System.out.println("Tháng 10");
                    break;
                case 11:
                    System.out.println("Tháng 11");
                    break;
                case 12:
                    System.out.println("Tháng 12");
                    break;
                default:
                    System.out.println("Không có tháng này");
                    isrun = false;
                    scanner.close();
                    break;
            }
        }
    }
}
