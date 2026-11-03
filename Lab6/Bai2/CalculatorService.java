package Lab6.Bai2;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface  CalculatorService extends Remote {
    int add (int a, int b) throws RemoteException;
}
