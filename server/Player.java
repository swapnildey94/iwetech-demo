import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import game.*;

public class Player extends Thread
{
  int player;
  int x;
  int y;
  Socket socket;
  Game game;
  BufferedReader input;
  PrintWriter output;

  public Player(Socket socket, int player, Game game)
  {
    this.socket = socket;
    this.player = player;
    this.game = game;

    try
    {
      input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      output = new PrintWriter(socket.getOutputStream(), true);
      output.println("WELCOME ");
      output.println("MESSAGE Waiting for opponents to connect...");
    }catch(IOException e)
    {
      System.out.println("Player disconnected: " + e);
    }
  }

  public void displayBoard()
  {
    output.print("BOARD ");
    output.print(game.getLength());
    output.print(game.getWidth());

    for(int i = 0; i < game.getLength(); i++)
    {
      for(int j = 0; j < game.getWidth(); j++)
      {
        int player = game.getCellPlayer(i, j);
        output.print(player == -1 ? 9 : player);
        output.print(game.getCellValue(i, j));
      }
    }
    output.println();
  }

  public void run()
  {
    try
    {
      output.println("MESSAGE All players are connected");

      if(player == 0)
      {
        output.println("MESSAGE Your turn");
      }

      while(true)
      {
        int turn = game.getTurn();
        while(player != turn)
        {
            if(turn != game.getTurn())
            {
              if(game.isGameOver(player))
              {
                output.print("GAMEOVER ");
                output.println(turn);
                return;
              }
              turn = game.getTurn();
              displayBoard();
            }
            output.println('-');
        }

        output.println("MESSAGE Waiting for your move:");
        output.println("WAIT ");

        String command = input.readLine();
        if (!command.equals(command)) {
          System.out.println(command);
        }
        if(command.startsWith("MOVE"))
        {
          int x = command.charAt(5) - '0';
          int y = command.charAt(6) - '0';

          System.out.println("Making move");
          game.move(x, y);
          displayBoard();
          if(game.isGameOver(player))
          {
            output.print("GAMEOVER ");
            output.println(player);
            return;
          }
        }
        else if(command.startsWith("QUIT"))
        {
          return;
        }
      }
    }catch(IOException e)
    {
      System.out.println("Player disconnected:" + e);
    }
    finally
    {
      try{socket.close();} catch(IOException e){}
    }
  }
}
