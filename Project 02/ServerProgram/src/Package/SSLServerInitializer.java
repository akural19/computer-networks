package Package;

public class SSLServerInitializer extends Thread {

	public void run() {
		SSLServer sslServer = new SSLServer(9999);
	}
}
