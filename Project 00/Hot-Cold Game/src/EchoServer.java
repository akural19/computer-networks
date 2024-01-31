import java.io.*; 
import java.net.*;
import java.util.Random;

// First Player
public class EchoServer {
	public static void main(String[] args) throws IOException {
		
		// Create a BufferedReader to read input from the console
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		
		// Prompt the user to enter the port for the welcoming socket
		System.out.println("Enter welcoming socket's port");
		
		// Read the socker number from the console and parse it to integer
		int port = Integer.parseInt(stdIn.readLine());

		// Create a ServerSocket and bind it to the specified port
		ServerSocket serverSocket = new ServerSocket(port);
		System.out.println("Waiting for client to connect...");
		
		// Accept a connection from a client
		Socket clientSocket = serverSocket.accept();
		
		// Print the client socket adress to the console
		System.out.println("Client socket: " + clientSocket.getRemoteSocketAddress());
		
		// Prompt the first player to enter their name and read from the console
		System.out.println("Player 1, please enter your name:");
		String firstPlayerName = stdIn.readLine();
		
		// Create input and output streams for communication with the client
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader
				(new InputStreamReader(clientSocket.getInputStream()));
		
		// Send the first player's name to the client
		out.println(firstPlayerName);
		
		// Prompt to indicate waiting for the second player's name
		System.out.println("Waiting for player 2 name...");
		
		// Receive the second player's name from the client and display in console
		String secondPlayerName = in.readLine();
		System.out.println("You are playing with " + secondPlayerName);
		
		// Initializing the scores for each player
		int playerOneScore = 0;
		int playerTwoScore = 0;
		
		// Play the game for 3 rounds
		for (int ii = 0; ii < 3; ii++) { 
			// Generate random target coordinates for the current round
			Random random = new Random();
			int[] target = new int[2];
			target[0] = random.nextInt(256);
			target[1] = random.nextInt(256);
			
			// Prompt the first player to enter their x and y guesses
			System.out.println(firstPlayerName + ", please enter your x and y guesses,"
					+ " comma seperated.");
			
			// Parse the player's guesses into integer array
			int[] playerOneGuess = guessParser(stdIn.readLine());
			
			// Signaling client to allow user to make a guess
			out.println();
			
			// Receive the second player's guess and parse the guess into integer array
			System.out.println("Waiting for player 2 guess...");
			int[] playerTwoGuess = guessParser(in.readLine());
			
			// Calculate the Euclidean distance between target and player's guesses
			double distance1 = euclidianDistance(target, playerOneGuess);
			double distance2 = euclidianDistance(target, playerTwoGuess);
		
			// Decide the winner of the round and increment their score by one
			// unless it is a draw
			String winnerName;
			if (distance1 < distance2) {
				winnerName = firstPlayerName;
				playerOneScore++;
			}
			else if (distance1 == distance2) {
				winnerName = "Both players";
			}
			else {
				winnerName = secondPlayerName;
				playerTwoScore++;
			}
			
			// Display the winner of the current round
			String levelPrompt = "Winner for round " + (ii + 1) + " is " + winnerName;
			System.out.println(levelPrompt);
			
			// Send the winner of the current round information to the client
			out.println(levelPrompt);
		}
		
		// Determine and display the winner of the game
		String endPrompt;
		if (playerOneScore > playerTwoScore) {
			endPrompt = "Game Winner is " + firstPlayerName;
		}
		else if (playerOneScore == playerTwoScore) {
			endPrompt = "Game is tied";
		}
		else {
			endPrompt = "Game Winner is " + secondPlayerName;
		}
		System.out.println(endPrompt);
		
		// Send the winner of the game information to the client
		out.println(endPrompt);
		
		// Close the streams and sockets
		in.close();
		out.close();
		stdIn.close();
		clientSocket.close();
		serverSocket.close();
	}
	
	// Calculate the Euclidean distance between two points
	public static double euclidianDistance(int[] target, int[] guess) {
		double d0 = target[0] - guess[0];
		double d1 = target[1] - guess[1];
		
		return Math.sqrt(d0 * d0 + d1 * d1);
	}
	
	// Parse a comma-separated string of two integers into an int array
	public static int[] guessParser(String line) {
		String[] guess = line.split(", ");
		int[] array = new int[2];		
		array[0] = Integer.parseInt(guess[0]);
		array[1] = Integer.parseInt(guess[1]);
		return array;
	}
}