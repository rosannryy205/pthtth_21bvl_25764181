package Lab6.Bai3;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SNTServiceImpl extends UnicastRemoteObject implements SNTService {
    public SNTServiceImpl() throws RemoteException {
        super();
    }

    @Override 
    public boolean SNT(int n) throws RemoteException {
        System.out.println("Server nhan duoc so: " + n);

        if (n < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
