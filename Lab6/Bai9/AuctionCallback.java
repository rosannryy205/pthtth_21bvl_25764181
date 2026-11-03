package Lab6.Bai9;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AuctionCallback extends Remote {
    void notifyNewBid(String message) throws RemoteException;
}
