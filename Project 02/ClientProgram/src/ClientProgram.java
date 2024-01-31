import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientProgram {

	public static void main(String[] args) throws IOException {
		System.out.print("Type your prefered connection (SSL/TCP): "); 
		BufferedReader std_in = new BufferedReader(new InputStreamReader(System.in));
		String choise = std_in.readLine();
		if (choise.equals("SSL")) {
			new SSLClient();
		}
		else if (choise.equals("TCP")) {
			new TCPClient();
		}
		else {
			System.out.print("Not supported connection type, restart the program!");
		}
	}

}
