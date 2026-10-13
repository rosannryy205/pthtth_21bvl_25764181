package client;

import java.io.BufferedReader;
import java.io.IOException;

// Luong chay ngam lien tuc doc tin nhan tu Server gui ve
public class ReadThread extends Thread {
    private BufferedReader in;

    public ReadThread(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            String serverMsg;
            // Cho va doc tin nhan tu Server, in ra console
            while ((serverMsg = in.readLine()) != null) {
                System.out.println(serverMsg);
            }
        } catch (IOException e) {
            // Khi socket dong luc thoat chuong trinh
        }
    }
}
