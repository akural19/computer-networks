package Package;
public class Server {
	public static void main(String[] args) {
		(new TCPServerInitializer()).start();
		(new SSLServerInitializer()).start();
	}
}