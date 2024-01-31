package Package;

public class TCPServerInitializer extends Thread {
	
	public void run() {
		TCPServer tcpServer = new TCPServer(6666);
	}
}