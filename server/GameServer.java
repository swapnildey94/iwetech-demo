
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import game.*;

public class GameServer
{

	public static void main(String [] args) throws Exception {


		int nplayers;
		int length;
		int width;

		Scanner s = new Scanner(System.in);
		System.out.print("Enter the number of players: ");
		nplayers = s.nextInt();
		System.out.print("Enter the length: ");
		length = s.nextInt();
		System.out.print("Enter the width: ");
		width = s.nextInt();

		initializeGameServer(nplayers, length, width);

	}

	private static void initializeGameServer(int nplayers, int length, int width) throws Exception {
		System.out.println("Initializing Chain Reaction server is starting...");

		int PORT_NO = 6666;
		ServerSocket listener = new ServerSocket(PORT_NO);

		try {
			while(true) {
				Game game = new Game(length, width, nplayers);
				Player [] players = new Player[nplayers];

				for(int i = 0; i < nplayers; i++) {
					players[i] = new Player(listener.accept(), i, game);
				}

				for(int i = 0; i < nplayers; i++) {
					players[i].start();
				}
			}
		} finally {
			listener.close();
		}
	}
}

