import org.jetbrains.annotations.TestOnly;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.SplittableRandom;

public class Game2048 {
    int[][] gameBoard;  //use 0 to indicate that a position on the board is empty

    final int GAME_BOARD_WIDTH = 4;
    final int GAME_BOARD_HEIGHT = 4;


    SplittableRandom random;

    TestAccessor testAccessor;

    public Game2048() {
        gameBoard = new int[GAME_BOARD_WIDTH][GAME_BOARD_HEIGHT];
        random = new SplittableRandom();

        //add the initial two tiles to the gameboard
        addTileToGameBoard();
        addTileToGameBoard();

        testAccessor = new TestAccessor();

    }

    public void makeMove(Move move) {

    }

    private void addTileToGameBoard() {
        ArrayList<int[]> emptyBoardSpaces = new ArrayList<>();
        for (int i = 0; i < GAME_BOARD_WIDTH; i++) {
            for (int j = 0; j < GAME_BOARD_HEIGHT; j++) {
                if (gameBoard[i][j] == 0) {
                    emptyBoardSpaces.add(new int[]{i, j});
                }
            }
        }

        Collections.shuffle(emptyBoardSpaces);

        if (gameBoard.length > 0) {
            gameBoard[emptyBoardSpaces.get(0)[0]][emptyBoardSpaces.get(0)[1]] = generateTileNumber();
        }
    }

    private int generateTileNumber() {
        int result = random.nextInt(1, 11);

        //90%  of the time we want the result to be 2
        if (result <= 9) {
            return 2;
        } else {
            return 4;
        }
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

    @TestOnly
    public TestAccessor getTestAccessor() {
        return testAccessor;
    }


    public enum Move {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }


    protected class TestAccessor {

        void setGameBoard(int[][] testGameBoard) {
            gameBoard = testGameBoard;
        }

    }
}
