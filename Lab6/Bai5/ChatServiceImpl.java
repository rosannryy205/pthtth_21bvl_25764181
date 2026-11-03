package Lab6.Bai5;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ChatServiceImpl extends UnicastRemoteObject implements ChatService {
    private String lastResponse = "";

    public ChatServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public synchronized void sendMessage(String message) throws RemoteException {
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println("[" + time + "] [Client]: " + message);

        String trimmed = message.trim().toLowerCase();
        if (trimmed.equals("bye") || trimmed.equals("exit") || trimmed.equals("tam biet")) {
            lastResponse = "Server: Tam biet ban! Hen gap lai.";
        } else if (trimmed.equals("hello") || trimmed.equals("hi") || trimmed.equals("xin chao")) {
            lastResponse = "Server: Xin chao! Rat vui duoc ket noi voi ban.";
        } else if (trimmed.contains("time") || trimmed.contains("gio")) {
            lastResponse = "Server: Bay gio la " + time;
        } else if (trimmed.contains("khoe khong") || trimmed.contains("how are you")) {
            lastResponse = "Server: Toi la Server RMI, toi van hoat dong rat tot. Cam on ban!";
        } else {
            lastResponse = "Server: Da nhan tin nhan luc [" + time + "]: \"" + message + "\"";
        }
    }

    @Override
    public synchronized String getResponse() throws RemoteException {
        return lastResponse;
    }
}
