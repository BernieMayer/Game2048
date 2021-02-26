import java.util.Random;

public class Game2048 {
    int[][] gameBoard;


    public Game2048() {
        gameBoard = new int[4][4];

        gameBoard[0][0] = 2;
        gameBoard[0][1] = 4;
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }
}
