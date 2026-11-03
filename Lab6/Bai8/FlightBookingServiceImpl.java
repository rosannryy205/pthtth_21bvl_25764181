package Lab6.Bai8;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class FlightBookingServiceImpl extends UnicastRemoteObject implements FlightBookingService {
    // Map luu so ghe trong hien tai
    private final Map<String, Integer> flightSeats = new LinkedHashMap<>();
    // Map luu tong so ghe toi da ban dau
    private final Map<String, Integer> flightCapacity = new LinkedHashMap<>();

    public FlightBookingServiceImpl() throws RemoteException {
        super();
        // Khoi tao danh sach chuyen bay mau
        addFlight("VN123", 50); // Ha Noi - TP.HCM
        addFlight("VJ456", 30); // Da Nang - Ha Noi
        addFlight("QH789", 20); // TP.HCM - Phu Quoc
        addFlight("VN302", 40); // Ha Noi - Tokyo
    }

    private void addFlight(String flightNumber, int seats) {
        flightSeats.put(flightNumber.toUpperCase(), seats);
        flightCapacity.put(flightNumber.toUpperCase(), seats);
    }

    private String getTime() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    @Override
    public synchronized int availableSeats(String flightNumber) throws RemoteException {
        if (flightNumber == null) return -1;
        String fn = flightNumber.trim().toUpperCase();
        Integer seats = flightSeats.get(fn);
        if (seats != null) {
            System.out.println("[" + getTime() + "] [Server] Kiem tra ghe chuyen bay " + fn + ": con " + seats + " ghe.");
            return seats;
        } else {
            System.out.println("[" + getTime() + "] [Server] Kiem tra chuyen bay " + fn + ": Khong tim thay!");
            return -1;
        }
    }

    @Override
    public synchronized boolean bookTicket(String flightNumber, int seats) throws RemoteException {
        if (flightNumber == null || seats <= 0) {
            return false;
        }
        String fn = flightNumber.trim().toUpperCase();
        if (!flightSeats.containsKey(fn)) {
            System.out.println("[" + getTime() + "] [Server] Dat ve that bai: Chuyen bay " + fn + " khong ton tai!");
            return false;
        }

        int currentSeats = flightSeats.get(fn);
        if (currentSeats >= seats) {
            int newSeats = currentSeats - seats;
            flightSeats.put(fn, newSeats);
            System.out.println("[" + getTime() + "] [Server] Dat ve thanh cong chuyen bay " + fn + ": " + seats + " ve. Con lai: " + newSeats + " ghe.");
            return true;
        } else {
            System.out.println("[" + getTime() + "] [Server] Dat ve that bai chuyen bay " + fn + ": Yeu cau " + seats + " ghe nhung chi con " + currentSeats + " ghe.");
            return false;
        }
    }

    @Override
    public synchronized boolean cancelBooking(String flightNumber, int seats) throws RemoteException {
        if (flightNumber == null || seats <= 0) {
            return false;
        }
        String fn = flightNumber.trim().toUpperCase();
        if (!flightSeats.containsKey(fn)) {
            System.out.println("[" + getTime() + "] [Server] Huy ve that bai: Chuyen bay " + fn + " khong ton tai!");
            return false;
        }

        int currentSeats = flightSeats.get(fn);
        int maxCapacity = flightCapacity.get(fn);
        int bookedSeats = maxCapacity - currentSeats;

        if (seats > bookedSeats) {
            System.out.println("[" + getTime() + "] [Server] Huy ve that bai chuyen bay " + fn + ": So ghe yeu cau huy (" + seats + ") lon hon so ghe da dat (" + bookedSeats + ").");
            return false;
        }

        int newSeats = currentSeats + seats;
        flightSeats.put(fn, newSeats);
        System.out.println("[" + getTime() + "] [Server] Huy ve thanh cong chuyen bay " + fn + ": " + seats + " ve. Ghe trong hien tai: " + newSeats + "/" + maxCapacity + ".");
        return true;
    }

    @Override
    public synchronized Map<String, Integer> getAllFlights() throws RemoteException {
        return Collections.unmodifiableMap(new LinkedHashMap<>(flightSeats));
    }
}
