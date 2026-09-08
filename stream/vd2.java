package stream;

import java.io.IOException;
import java.io.InputStream;
import static java.lang.Thread.*;

public class vd2 {
    public static void main(String[] args) throws InterruptedException, IOException {
        InputStream is = System.in;

        try {
            while (true) {
                // Kiểm tra xem có bao nhiêu byte dữ liệu có sẵn mà không bị chặn để đọc từ
                // luồng đầu vào
                if (is.available() > 0) {
                    byte[] buffer = new byte[is.available()];
                    int bytesRead = is.read(buffer);
                    if (bytesRead == -1) {
                        break; // Kết thúc vòng lặp nếu không còn dữ liệu để đọc
                    }
                    String str = new String(buffer, 0, bytesRead);
                    System.out.println(str); // In ra chuỗi đã đọc từ luồng đầu vào
                } else {
                    System.out.print('.');
                    sleep(100);
                }
            }
        } catch (IOException e) {
        }
    }
}