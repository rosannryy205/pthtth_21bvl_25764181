package Lab6.Bai3;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SNTService extends Remote {
    boolean SNT(int n) throws RemoteException;
}
