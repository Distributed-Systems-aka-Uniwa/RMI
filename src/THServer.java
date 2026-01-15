import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.*;

import javax.swing.text.Style;

public class THServer {

    public THServer() {
        try {
            THInterface remoteObj = new THImpl();
            System.out.println("RMI registry on...\nWaiting for requests...\n");
            System.out.println("[Hostname]   : localhost");
            System.out.println("[Portnumber] : 1099");
            System.out.println("------------------------------------\n");
            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://localhost:1099/TheatreService", remoteObj);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new THServer();
    }
    
}
