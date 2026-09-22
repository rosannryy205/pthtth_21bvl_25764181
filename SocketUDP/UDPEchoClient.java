package SocketUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPEchoClient {
    public static int serverPort = 6789;
    public static void main(String[] args) throws InterruptedException {
       
        try {
            DatagramSocket ds = new DatagramSocket();
            InetAddress serverIP = InetAddress.getByName("localhost");
            System.out.println("UDP Client đã được tạo");

            for(char ch='0'; ch <= '9'; ch++){
                String sendData = "data | " + ch; //String.valueOf(ch);
                byte[] sendBuffer = sendData.getBytes();
                DatagramPacket dp = new DatagramPacket(sendBuffer, sendBuffer.length, serverIP, 6789); 
                ds.send(dp);

                byte[] resBuffer = new byte[1024];
                DatagramPacket resPacket = new DatagramPacket(resBuffer, resBuffer.length);
                ds.receive(resPacket);

                String resData = new String(resPacket.getData(), resPacket.getOffset(), resPacket.getLength());
                System.out.println("Server phản hồi: " + resData);

                Thread.sleep(2000);
            }
            System.out.println("Client đã gửi đủ từ 0->9 và nhận đủ phản hồi");   
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
