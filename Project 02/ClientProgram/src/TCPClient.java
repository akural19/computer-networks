import java.util.Scanner;


public class TCPClient {

    /**
     * @param args the command line arguments
     */
    public TCPClient() {
        TCPConnectionToServer tCPConnectionToServer = new TCPConnectionToServer("localhost", 6666);
        tCPConnectionToServer.Connect();
        String message = "71959COMP416";
        tCPConnectionToServer.SendForAnswer(message);
        tCPConnectionToServer.Disconnect();
    } 
}