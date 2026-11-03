package Lab6.Bai7;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GeometryServiceImpl extends UnicastRemoteObject implements GeometryService {
    public GeometryServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public double rectangleArea(double width, double height) throws RemoteException {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Chieu rong va chieu dai phai >= 0");
        }
        double area = width * height;
        System.out.printf("[Server] Tinh dien tich Hinh chu nhat: rong=%.2f, dai=%.2f => Dien tich=%.4f%n", width, height, area);
        return area;
    }

    @Override
    public double circleArea(double radius) throws RemoteException {
        if (radius < 0) {
            throw new IllegalArgumentException("Ban kinh phai >= 0");
        }
        double area = Math.PI * radius * radius;
        System.out.printf("[Server] Tinh dien tich Hinh tron: r=%.2f => Dien tich=%.4f%n", radius, area);
        return area;
    }

    @Override
    public double triangleArea(double base, double height) throws RemoteException {
        if (base < 0 || height < 0) {
            throw new IllegalArgumentException("Do dai day va chieu cao phai >= 0");
        }
        double area = 0.5 * base * height;
        System.out.printf("[Server] Tinh dien tich Hinh tam giac: day=%.2f, cao=%.2f => Dien tich=%.4f%n", base, height, area);
        return area;
    }
}
