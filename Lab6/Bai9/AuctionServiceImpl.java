package Lab6.Bai9;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class AuctionServiceImpl extends UnicastRemoteObject implements AuctionService {
    private final List<Product> products = new ArrayList<>();
    private final List<AuctionCallback> callbacks = new CopyOnWriteArrayList<>();
    private final List<String> notificationLogs = new ArrayList<>();
    private final AtomicInteger nextId = new AtomicInteger(1);
    private final DecimalFormat df = new DecimalFormat("#,##0.00");

    public AuctionServiceImpl() throws RemoteException {
        super();
        // Khoi tao cac san pham dau gia mau
        addProductInternal("Dong ho Rolex Submariner", 15000000.0, "Cua hang Dong Ho Thuy Si");
        addProductInternal("Buc tranh Pho co Ha Noi (1985)", 5000000.0, "Hoa si Nguyen");
        addProductInternal("Dien thoai iPhone 16 Pro Max", 28000000.0, "The Gioi Di Dong");
    }

    private void addProductInternal(String name, double startingPrice, String seller) {
        int id = nextId.getAndIncrement();
        Product p = new Product(id, name, startingPrice, seller);
        products.add(p);
    }

    private String getTime() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    private void broadcastNotification(String message) {
        String timedMessage = "[" + getTime() + "] " + message;
        synchronized (notificationLogs) {
            notificationLogs.add(timedMessage);
            if (notificationLogs.size() > 100) {
                notificationLogs.remove(0);
            }
        }

        // Gui thong bao den tat ca client dang ket noi
        List<AuctionCallback> deadCallbacks = new ArrayList<>();
        for (AuctionCallback cb : callbacks) {
            try {
                cb.notifyNewBid(timedMessage);
            } catch (Exception e) {
                deadCallbacks.add(cb);
            }
        }
        callbacks.removeAll(deadCallbacks);
    }

    @Override
    public synchronized int addProduct(String name, double startingPrice, String seller) throws RemoteException {
        if (name == null || name.trim().isEmpty() || startingPrice <= 0) {
            throw new IllegalArgumentException("Thong tin san pham khong hop le!");
        }

        int id = nextId.getAndIncrement();
        Product product = new Product(id, name.trim(), startingPrice, (seller == null || seller.trim().isEmpty()) ? "An danh" : seller.trim());
        products.add(product);

        String logMsg = String.format("[Server] Seller '%s' da them san pham #%d: '%s' voi gia khoi diem %s VND.",
                product.getSeller(), id, product.getName(), df.format(startingPrice));
        System.out.println(logMsg);

        broadcastNotification(String.format("SAN PHAM MOI: #%d - '%s' vua duoc dua vao dau gia boi '%s' voi gia khoi diem: %s VND!",
                id, product.getName(), product.getSeller(), df.format(startingPrice)));

        return id;
    }

    @Override
    public synchronized List<Product> getProducts() throws RemoteException {
        return new ArrayList<>(products);
    }

    @Override
    public synchronized Product getProduct(int id) throws RemoteException {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public synchronized boolean placeBid(int productId, double bidAmount, String bidderName) throws RemoteException {
        Product target = null;
        for (Product p : products) {
            if (p.getId() == productId) {
                target = p;
                break;
            }
        }

        if (target == null) {
            System.out.println("[" + getTime() + "] [Server] Dat gia that bai: Khong tim thay san pham #" + productId);
            return false;
        }

        if (bidAmount <= target.getHighestBid()) {
            System.out.println(String.format("[%s] [Server] Dat gia that bai cho #%d: Gia dua ra (%s VND) khong cao hon gia hien tai (%s VND).",
                    getTime(), productId, df.format(bidAmount), df.format(target.getHighestBid())));
            return false;
        }

        // Cap nhat gia cao nhat va nguoi dat gia
        String bidder = (bidderName == null || bidderName.trim().isEmpty()) ? "An danh" : bidderName.trim();
        target.setHighestBid(bidAmount);
        target.setHighestBidder(bidder);

        String serverLog = String.format("[%s] [Server] GIA THAU MOI CHO #%d ('%s'): %s VND boi '%s'!",
                getTime(), target.getId(), target.getName(), df.format(bidAmount), bidder);
        System.out.println(serverLog);

        // Thong bao den tat ca client thoi gian thuc
        String notification = String.format("GIA THAU MOI: San pham #%d ('%s') vua duoc '%s' tra gia cao hon: %s VND!",
                target.getId(), target.getName(), bidder, df.format(bidAmount));
        broadcastNotification(notification);

        return true;
    }

    @Override
    public void registerCallback(AuctionCallback callback) throws RemoteException {
        if (callback != null && !callbacks.contains(callback)) {
            callbacks.add(callback);
            System.out.println("[" + getTime() + "] [Server] Mot client da dang ky nhan thong bao dau gia. (Tong so listeners: " + callbacks.size() + ")");
        }
    }

    @Override
    public void unregisterCallback(AuctionCallback callback) throws RemoteException {
        if (callback != null) {
            callbacks.remove(callback);
            System.out.println("[" + getTime() + "] [Server] Mot client da huy dang ky thong bao dau gia. (Tong so listeners: " + callbacks.size() + ")");
        }
    }

    @Override
    public synchronized List<String> getNotificationLogs() throws RemoteException {
        return new ArrayList<>(notificationLogs);
    }
}
