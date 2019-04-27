package game;

public class Game
{
	int length;
	int width;
	int nplayers;
	int turn;
	boolean [] start; // Determines if the ith player has made atlease one move.
	Grid grid;

	public Game(int length, int width, int nplayers)
	{
		this.width = width;
		this.length = length;
		this.nplayers = nplayers;
		start = new boolean[nplayers];
		for(int i = 0; i < nplayers; i++)
		{
			start[i] = true;
		}
		turn = 0;
		grid = new Grid(length, width, nplayers);
	}

	public void move(int i, int j)
	{
		if(start[turn] || grid.isPlayerPresent(turn))
		{
			// Discard an invalid move
			if(!grid.canPlace(i, j, turn)) {
				return;
			}
			grid.place(i, j, turn);
			// System.out.println("Placing on grid " + new Integer(i).toString() + " " + new Integer(j).toString());
		}
		start[turn] = false;
		turn = (turn + 1) % nplayers;
		System.out.println("New turn: " + new Integer(turn));
	}

	public int getTurn()
	{
		return turn;
	}

	public boolean isGameOver(int player) {
		// TODO

		for (int i = 0; i < nplayers; i++) {
	      if(start[i]) {
	        return false;
	      }
	    }

	    Cell[][] Board = grid.getBoard();

	    int player = -1;
	    for (int i = 0; i < length; i++) {
	      for (int j = 0; j < width; j++) {
	        int playerOnCell = Board[i][j].getPlayer();
	        if (playerOnCell != -1) {
	          player = playerOnCell;
	          break;
	        }
	      }
	    }

	    for (int i = 0; i < length; i++) {
	      for (int j = 0; j < width; j++) {
	        int playerOnCell = Board[i][j].getPlayer();
	        if (playerOnCell != -1 && player != playerOnCell) {
	          return false;
	        }
	      }
	    }

	    return true;
	}

	public int getCellValue(int i, int j)
	{
		return grid.getValue(i , j);
	}

	public int getCellPlayer(int i, int j)
	{
		return grid.getPlayer(i, j);
	}

	public int getLength()
	{
		return length;
	}

	public int getWidth()
	{
		return width;
	}
}
