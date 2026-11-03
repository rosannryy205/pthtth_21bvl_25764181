package Lab6.Bai4;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class ContactServiceImpl extends UnicastRemoteObject implements ContactService {
    private final HashMap<String, String> contacts;

    public ContactServiceImpl() throws RemoteException {
        super();
        this.contacts = new HashMap<>();
    }

    @Override
    public synchronized void addContact(String name, String phone) throws RemoteException {
        contacts.put(name, phone);
        System.out.println("[SERVER] Da luu/cap nhat lien he: " + name + " -> " + phone);
    }

    @Override
    public synchronized String findContact(String name) throws RemoteException {
        System.out.println("[SERVER] Nhan yeu cau tim kiem lien he: " + name);
        return contacts.get(name);
    }

    @Override
    public synchronized boolean deleteContact(String name) throws RemoteException {
        System.out.println("[SERVER] Nhan yeu cau xoa lien he: " + name);
        if (contacts.containsKey(name)) {
            contacts.remove(name);
            System.out.println("[SERVER] Da xoa lien he: " + name);
            return true;
        } else {
            System.out.println("[SERVER] Khong tim thay de xoa: " + name);
            return false;
        }
    }
}
