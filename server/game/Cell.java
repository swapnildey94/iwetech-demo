package game;

public class Cell
{
  int x, y;
  int value;
  int player;

  Cell()
  {
    this.x = -1;
    this.y = -1;
    this.player = -1;
    value = 0;
  }
  public int getValue()
  {
    return value;
  }
  public int getPlayer()
  {
    return player;
  }

  public void setValue(int value)
  {
    this.value = value;
  }
  public void setPlayer(int player)
  {
    this.player = player;
  }
  public void setXY(int x, int y)
  {
    this.x = x;
    this.y = y;
  }
}
