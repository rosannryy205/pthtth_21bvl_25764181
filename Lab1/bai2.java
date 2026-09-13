import java.util.Scanner;
public class bai2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("What's your name? ");
        String str = scanner.nextLine();
        System.out.println("Hi, I am " + str + "!");
        scanner.close();
    }
}
