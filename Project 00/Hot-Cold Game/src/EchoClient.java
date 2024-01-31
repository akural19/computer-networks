import java.io.*; 
import java.net.*;

// Second Player 
public class EchoClient {
	public static void main(String[] args) throws IOException {
		
		// Define the hostname for the server
		String host = "localhost";
		
		// Create a BufferedReader to read input from the console
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		
		// Prompt the user to enter the port for the server socket
		System.out.println("Enter server socket's port");
		
		// Read the port number from the console and parse it to an integer
		int port = Integer.parseInt(stdIn.readLine());
		
		// Create a socket and connect to the server using the specified host and port
		Socket echoSocket = new Socket(host, port);
		
		// Display information about the server socket
		System.out.println("Server socket: ");
		System.out.println(echoSocket.getRemoteSocketAddress());
		
		// Create input and output streams for communication with the server
		PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader
				(new InputStreamReader(echoSocket.getInputStream()));
		
		// Receive the first player's name from the server
		String firstPlayerName = in.readLine();
		
		// Prompt the second player to enter their name and show the first player's name
		System.out.println("Player 2, you will be playing with " 
		+ firstPlayerName + ", please enter your name:");
		
		// Read the name of the second player from the console and send it to the server
		String secondPlayerName = stdIn.readLine();
		out.println(secondPlayerName);
		
		// Play the game for 3 rounds
		for (int ii = 0; ii < 3; ii++) {
			
			// Wait for the signal from the server to allow user to make a guess
			System.out.println("Waiting for player 1 guess...");
			in.readLine();
			
			// Prompt the second player to enter their x and y guesses
			System.out.println(secondPlayerName + ", please enter your x and y guesses,"
					+ " comma seperated.");
			
			// Read the guess from the console and send the it to the server
			out.println(stdIn.readLine());
			
			// Recieve the round winner feedback from the server and print to the console
			System.out.println(in.readLine());
		}
		
		// Display the final game result received from the server
		System.out.println(in.readLine());
		
		// Close the input and output streams and the socket
		in.close();
		out.close();
		stdIn.close();
		echoSocket.close();
	}
}