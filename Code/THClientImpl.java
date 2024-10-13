import java.rmi.*;
import java.rmi.server.*;
/*
 *  Η κλάση που δίνει την υλοποίηση στο remote object του client για την επικοινωνία με τον server
 */
public class THClientImpl extends UnicastRemoteObject
    implements THClientInterface {
    
    private String serverResponse;

    public THClientImpl() 
        throws RemoteException {
        super();
        serverResponse = "";
    }
  
    public String notifyCallbackClient(String msg)
        throws RemoteException {
        String returnMessage = "Call back received: " + msg;
        System.out.println(returnMessage);

        return returnMessage;
    }

    public String updateClient(String upd)
        throws RemoteException {
        System.out.println(upd);
        
        return upd;
    }

    public String getServerResponse()
        throws RemoteException {
        return serverResponse;
    }

}
