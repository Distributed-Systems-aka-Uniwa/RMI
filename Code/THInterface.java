import java.rmi.Remote;
import java.rmi.RemoteException;
import java.lang.String;
import java.util.ArrayList;
/*
 *  Η κλάση που δίνει την αναφορά του remote object του RMI registry που σηκώνει ο server
 */
public interface THInterface extends Remote {
    
    public ArrayList<AvailableSeats> list(String hostname) 
        throws RemoteException;
    
    public int book(String hostname, String type, int number, String name, THClientInterface clientObj, final int PRINTMSGS) 
        throws RemoteException;
        
    public void registerForCallback(THClientInterface callbackClient, String type)
        throws RemoteException;
    
    public void unregisterForCallback(THClientInterface callbackClient, String type)
        throws RemoteException;
 
    public ArrayList<Reservations> guests(String hostname) 
        throws RemoteException;
 
    public ArrayList<Reservations> cancel(String hostname, String type, int number, String name, THClientInterface clientObj) 
        throws RemoteException;
    
    public String updateServer(String upd) // Επικοινωνία από client στον server
        throws RemoteException;
}
