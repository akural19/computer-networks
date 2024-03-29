import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Yahya Hassanzadeh on 20/09/2017.
 */

public class TCPConnectionToServer {

    private Socket tcpSocket;
    protected BufferedReader is;
    protected PrintWriter os;

    protected String serverAddress;
    protected int serverPort;

    /**
     *
     * @param address IP address of the server, if you are running the server on the same computer as client, put the address as "localhost"
     * @param port port number of the server
     */
    public TCPConnectionToServer(String address, int port) {
        serverAddress = address;
        serverPort    = port;
    }

    /**
     * Establishes a socket connection to the server that is identified by the serverAddress and the serverPort
     */
    public void Connect() {
        try {
            tcpSocket = new Socket(serverAddress, serverPort);
 
            is = new BufferedReader(new InputStreamReader(tcpSocket.getInputStream()));
            os = new PrintWriter(tcpSocket.getOutputStream());

            System.out.println("Successfully connected to server at " + tcpSocket.getRemoteSocketAddress());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * sends the message String to the server and retrives the answer
     * @param message input message string to the server
     * @return the received server answer
     */
    public void SendForAnswer(String message) {
        String response = new String();
        char[] charArray = message.toCharArray();
        try {
        	long start_time = System.currentTimeMillis();
        	for (int ii = 0; ii < 12; ii++) {
	        	os.println(charArray[ii]);
	        	os.flush();
	        	response = is.readLine();
	        	System.out.println(response);
        	}
        	long end_time = System.currentTimeMillis();
        	System.out.printf("%.3f sec", ((end_time - start_time) / Math.pow(10, 3)));
        }
        catch(IOException e) {
            e.printStackTrace();
            System.out.println("TCPConnectionToServer. SendForAnswer. Socket read Error");
        }
    }

    /**
     * Disconnects the socket and closes the buffers
     */
    public void Disconnect() {
        try {
            is.close();
            os.close();
            tcpSocket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}