import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.junit.Assert.fail;

public class Game2048Test {

   @Test
   public void testBasicBoardCreation(){
      Game2048 game2048 = new Game2048();


      int[][] gameBoard = game2048.getGameBoard();


      int countElements = 0;
      Assert.assertThat(gameBoard, CoreMatchers.notNullValue());

      for (int i = 0; i < 4; i++) {
         for (int j = 0; j < 4; j++) {
            if (gameBoard[i][j] == 2 || gameBoard[i][j] == 4) {
               countElements++;
            } else if (gameBoard[i][j] != 0) {
               fail();
            }
         }
      }

      Assert.assertThat(countElements, CoreMatchers.is(2));
   }

}
