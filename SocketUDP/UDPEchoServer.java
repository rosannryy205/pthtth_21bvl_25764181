package SocketUDP;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPEchoServer {
    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket(6789);
            System.out.println("UDP Server đã được tạo");

            while (true) {
                for(int i=0 ; i<10; i++){
                    byte[] receiveBuffer = new byte[1024];
                    DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                    ds.receive(receivePacket);

                    //lay du lieu trong receivePacket ra ---> xu ly them yeu cau ---> roi sendPacket de gui lai

                    String receiveData = new String(receivePacket.getData(), receivePacket.getOffset(), receivePacket.getLength());
                    System.out.println("Server nhận được: " + receiveData);

                    byte[] sendBuffer = receiveData.getBytes();
                    DatagramPacket dp = new DatagramPacket(sendBuffer, sendBuffer.length, receivePacket.getAddress(), receivePacket.getPort());
                    ds.send(dp);
                }
            }
        } catch (IOException e) {
           System.out.println(e);
        }
    }
}
