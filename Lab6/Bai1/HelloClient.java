package Lab6.Bai1;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class HelloClient {
    public static void main(String[] args) {
        try {
            //Ket noi den Registry tai localhost:1099
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            //Lookup doi tuong da dang ky
            HelloService service = (HelloService) registry.lookup("HelloService");

            //Trieu doi phuong tu xa
            String response = service.sayHello();

            System.out.println("Kết quả nhận từ Server: " + response);
        } catch (Exception e) {
            System.err.println("Loi client: "+ e.getMessage());
            e.printStackTrace();
        }
    }
}
