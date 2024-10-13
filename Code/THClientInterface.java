import java.rmi.*;
/*
 *  Η κλάση που δίνει την αναφορά στο remote object του client για την επικοινωνία με τον server
 */
public interface THClientInterface extends Remote {
    
    public String notifyCallbackClient(String msg)
        throws RemoteException;
    
    public String updateClient(String upd) // Επικοινωνία από τον server στον client
        throws RemoteException;
}
