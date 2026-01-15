import java.rmi.*;
/*
 *  The class that provides the reference to the remote object of the client for communication with the server
 */
public interface THClientInterface extends Remote {
    
    public String notifyCallbackClient(String msg)
        throws RemoteException;
    
    public String updateClient(String upd) // Communication from the server to the client
        throws RemoteException;
}
