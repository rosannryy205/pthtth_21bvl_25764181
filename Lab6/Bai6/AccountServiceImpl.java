package Lab6.Bai6;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AccountServiceImpl extends UnicastRemoteObject implements AccountService {
    private double balance;
    private final DecimalFormat df = new DecimalFormat("#,##0.00");

    public AccountServiceImpl(double initialBalance) throws RemoteException {
        super();
        this.balance = initialBalance;
    }

    private String getTime() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    @Override
    public synchronized double getBalance() throws RemoteException {
        System.out.println("[" + getTime() + "] [Server] Client kiem tra so du: " + df.format(balance) + " VND");
        return balance;
    }

    @Override
    public synchronized void deposit(double amount) throws RemoteException {
        if (amount <= 0) {
            System.out.println("[" + getTime() + "] [Server] Gui tien that bai: So tien khong hop le (" + amount + ")");
            throw new IllegalArgumentException("So tien gui phai lon hon 0!");
        }
        balance += amount;
        System.out.println("[" + getTime() + "] [Server] Gui tien thanh cong: +" + df.format(amount) + " VND | So du hien tai: " + df.format(balance) + " VND");
    }

    @Override
    public synchronized boolean withdraw(double amount) throws RemoteException {
        if (amount <= 0) {
            System.out.println("[" + getTime() + "] [Server] Rut tien that bai: So tien khong hop le (" + amount + ")");
            return false;
        }
        if (amount <= balance) {
            balance -= amount;
            System.out.println("[" + getTime() + "] [Server] Rut tien thanh cong: -" + df.format(amount) + " VND | So du con lai: " + df.format(balance) + " VND");
            return true;
        } else {
            System.out.println("[" + getTime() + "] [Server] Rut tien that bai: So du khong du (Yeu cau: " + df.format(amount) + ", Hien co: " + df.format(balance) + ")");
            return false;
        }
    }
}
