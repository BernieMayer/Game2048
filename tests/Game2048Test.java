import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;


import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class Game2048Test {

   @Test
   public void testBasicBoardCreation(){
      Game2048 game2048 = new Game2048();


      int[][] gameBoard = game2048.getGameBoard();


      int countElements = 0;
      assertThat(gameBoard, CoreMatchers.notNullValue());

      for (int i = 0; i < 4; i++) {
         for (int j = 0; j < 4; j++) {
            if (gameBoard[i][j] == 2 || gameBoard[i][j] == 4) {
               countElements++;
            } else if (gameBoard[i][j] != 0) {
               fail();
            }
         }
      }

      assertThat(countElements, CoreMatchers.is(2));
   }

   @Test
   public void testSimpleMoveLeftLogic() {

      int[][] testBoard = new int[][]{ {2, 2, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};

      Game2048 game2048 = new Game2048();
      Game2048.TestAccessor testAccessor = game2048.getTestAccessor();

      testAccessor.setGameBoard(testBoard);

      game2048.makeMove(Game2048.Move.LEFT);

      assertThat(game2048.getGameBoard()[0][0], CoreMatchers.is(4));
   }

   @Test
   public void testSimpleMoveRightLogic() {
      int[][] testBoard = new int[][]{ {0, 0, 2, 2}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};

      Game2048 game2048 = new Game2048();
      Game2048.TestAccessor testAccessor = game2048.getTestAccessor();

      testAccessor.setGameBoard(testBoard);

      game2048.makeMove(Game2048.Move.RIGHT);

      assertThat(game2048.getGameBoard()[0][3], CoreMatchers.is(4));
   }

   @Test
   public void testSimpleMoveDownLogic() {
      int[][] testBoard = new int[][]{ {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 2}, {0, 0, 0, 2}};

      Game2048 game2048 = new Game2048();
      Game2048.TestAccessor testAccessor = game2048.getTestAccessor();

      testAccessor.setGameBoard(testBoard);

      game2048.makeMove(Game2048.Move.DOWN);


      assertThat(game2048.getGameBoard()[3][3], CoreMatchers.is(4));

   }

   @Test
   public void testSimpleMoveUpLogic() {
      int[][] testBoard = new int[][]{ {0, 0, 0, 2}, {0, 0, 0, 2}, {0, 0, 0, 0}, {0, 0, 0, 0}};

      Game2048 game2048 = new Game2048();
      Game2048.TestAccessor testAccessor = game2048.getTestAccessor();

      testAccessor.setGameBoard(testBoard);

      game2048.makeMove(Game2048.Move.UP);

      assertThat(game2048.getGameBoard()[0][3], CoreMatchers.is(4));

   }

}
