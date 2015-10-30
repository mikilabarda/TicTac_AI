public class AI{
  public void AI(level){
    private int gamelevel = level;
    Game ttt;
    Frame ui;

    public AIgalaw(position){
      // AI action

      }
    }

    private void RandomLevel(turn){
      public char[][] available = ttt.emptyCells(); //add this function to Game.java
      public char[][] randCell = available[Math.floor(Math.random() * available.length)][Math.floor(Math.random() * available.length)];
      public int move = new AIgalaw(randCell);
      public int next = move.apply(ttt.play);
      ui.actionPerformed(randCell, turn);
    }


    this.notify = public void menu(turn){
      switch(level){
      //choose a level
      case 0: RandomLevel(turn); break;
      /*case 1: level_2(turn); break;
      case 2: level_3(turn); break;*/
      }
    }
  }
}
