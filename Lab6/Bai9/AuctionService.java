package Lab6.Bai9;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface AuctionService extends Remote {
    // 1. Chuc nang nguoi ban (Seller): Them san pham dau gia
    int addProduct(String name, double startingPrice, String seller) throws RemoteException;

    // 2. Chuc nang nguoi dau gia (Bidder): Xem danh sach san pham, dat gia thau
    List<Product> getProducts() throws RemoteException;
    Product getProduct(int id) throws RemoteException;
    boolean placeBid(int productId, double bidAmount, String bidderName) throws RemoteException;

    // 3. Chuc nang thong bao thoi gian thuc (Realtime Notification Callback)
    void registerCallback(AuctionCallback callback) throws RemoteException;
    void unregisterCallback(AuctionCallback callback) throws RemoteException;
    List<String> getNotificationLogs() throws RemoteException;
}
