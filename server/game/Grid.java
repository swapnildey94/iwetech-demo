package game;

public class Grid
{
  int length;
  int width;
  int nplayers;
  Cell[][] Board;

  Grid(int length, int width, int nplayers)
  {
    this.length = length;
    this.width = width;
    this.nplayers = nplayers;
    Board = new Cell[length][width];

    for(int i = 0; i < length; i++)
    {
      for(int j = 0; j < width; j++)
      {
        Board[i][j] = new Cell();
        Board[i][j].setXY(i, j);
      }
    }
  }

  int getStability(int i, int j) {
    // TODO

    if(cornerCell(i,j)) {
      return 1;
    } else if(boundaryCell(i,j)) {
     return 2;
    }
    return 3;
  }

  boolean cornerCell(int i, int j)
  {
    // TODO

    if(i == 0 && j == 0) {
      return true;
    }
    if(i == length-1 && j == 0) {
      return true;
    }
    if(i == length-1 && j == width-1) {
      return true;
    }
    if(i == 0 && j == width-1) {
      return true;
    }

    return false;
  }

  boolean boundaryCell(int i, int j)
  {
    // TODO

    if(i == 0 || j == 0 || i == length-1 || j == width - 1)
    {
      return true;
    }

    return false;
  }

  private boolean isOutOfBound(int i, int j) {
    // TODO
    // Determine if i, j is out of the bounds of the grid

    return (i < 0 || j < 0 || i >= length || j >= width);
  }

  public boolean canPlace(int i, int j, int player)
  {
    // TODO

    // Ignore the move if it is out of bounds of the grid
    if(isOutOfBound(i, j))
    {
      return false;
    }
    // Ignore the move if another player's orb is already present 
    if (Board[i][j].getPlayer() != -1 && Board[i][j].getPlayer() != player) {
      return false;
    }

    return true;
  }
  
  public void place(int i, int j, int player)
  {
    // TODO
    System.out.println("Placing " + i + " " + j);

    if (isOutOfBound(i, j)) {
      return;
    }

    Cell cell = Board[i][j];
    cell.setValue(cell.getValue() + 1);
    cell.setPlayer(player);

    if(cell.getValue() > getStability(i, j)) {
      cell.setValue(0);
      cell.setPlayer(-1);
      place(i-1, j, player);
      place(i+1, j, player);
      place(i, j-1, player);
      place(i, j+1, player);
    }
  }

  public int getValue(int i, int j)
  {
    return Board[i][j].getValue();
  }

  public int getPlayer(int i , int j)
  {
    return Board[i][j].getPlayer();
  }

  public boolean isPlayerPresent(int player) {
    // TODO
    for (int i = 0; i < length; i++) {
      for (int j = 0; j < width; j++) {
        if (Board[i][j].getPlayer() == player) {
          return true;
        }
      }
    }
    return false;
  }

  public Cell[][] getBoard() {
    return Board;
  }
}
