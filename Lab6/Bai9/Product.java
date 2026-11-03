package Lab6.Bai9;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private double startingPrice;
    private double highestBid;
    private String highestBidder;
    private String seller;

    public Product(int id, String name, double startingPrice, String seller) {
        this.id = id;
        this.name = name;
        this.startingPrice = startingPrice;
        this.highestBid = startingPrice;
        this.highestBidder = "Chua co";
        this.seller = seller;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getStartingPrice() {
        return startingPrice;
    }

    public synchronized double getHighestBid() {
        return highestBid;
    }

    public synchronized void setHighestBid(double highestBid) {
        this.highestBid = highestBid;
    }

    public synchronized String getHighestBidder() {
        return highestBidder;
    }

    public synchronized void setHighestBidder(String highestBidder) {
        this.highestBidder = highestBidder;
    }

    public String getSeller() {
        return seller;
    }

    public String formatPrice(double price) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(price) + " VND";
    }

    @Override
    public String toString() {
        return String.format("[#%d] %-25s | Khoi diem: %15s | Gia hien tai: %15s | Nguoi tra cao nhat: %s (Nguoi ban: %s)",
                id, name, formatPrice(startingPrice), formatPrice(highestBid), highestBidder, seller);
    }
}
