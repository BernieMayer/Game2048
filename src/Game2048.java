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

        if (move == Move.RIGHT) {
            moveTilesRight();
            mergeTilesRight();
        } else if (move == Move.LEFT) {
            moveTilesLeft();
            //mergeTilesLeft();
        } else if (move == Move.UP) {
            //moveTilesUp();
            //mergeTilesUp();
        } else if (move == Move.DOWN) {
            //moveTilesDown();
            //mergeTilesDown();
        }

    }

    private void mergeTilesRight() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = gameBoard[i].length; j >=1 ; j--) {
                if (gameBoard[i][j] == gameBoard[i][j -1]) {

                }
            }
        }
    }
    private void moveTilesRight() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 1;  j < gameBoard[i].length; j++) {
                if (gameBoard[i][j - 1] != 0 && gameBoard[i][j] == 0) {
                    gameBoard[i][j] = gameBoard[i][j - 1];
                    gameBoard[i][j - 1] = 0;
                }
            }
        }
    }

    private void moveTilesLeft() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = gameBoard[i].length - 2;  j >= 0; j--) {
                if (gameBoard[i][j] == 0 && gameBoard[i][j + 1] != 0) {
                    gameBoard[i][j] = gameBoard[i][j + 1];
                    gameBoard[i][j + 1] = 0;
                }
            }
        }
    }

//    private void applyMoveToRowGivenMove(int[] row, Move move) {
//
//        if (move = Move.RIGHT) {
//            applyRightMove(row);
//        }
//    }

    private void applyLeftMerge(int[] row) {
        for (int i = row.length - 1; i > 1; i--) {
            if (row[i] == row[i - 1]) {
                row[i - 1] = row[i] * 2;
            }
        }
    }

    private void applyRightMerge(int[] row) {
        for (int i = 0; i < row.length -1; i++) {
            if (row[i] == row[i + 1]) {
                row[i + 1] = 2 * row[i];
            }
        }
    }

    private void applyRightMove(int[] row) {
        for (int i = 0; i < row.length - 1; i++) {
            if (row[i] == row[i + 1]) {
                row[i + 1] = row[i];
            } else if (row[i] != 0 && row[i + 1] == 0) {
                row[i + 1] = row[i];
                row[i] = 0;
            }
        }
        row[0] = 0;
    }
    /*
    private void moveTiles(Move move) {
        int[] currentIndex;
        int[] adjacentIndex;

        //slide the current tile
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {

                int current = gameBoard[i][j];
            }
        }
    }

    private void swapIndicesOnGameBoard(int[] currentIndex, int[] otherIndex) {
        int temp = gameBoard[currentIndex[0]][currentIndex[1]];
        gameBoard[otherIndex[0]][otherIndex[1]] = gameBoard[currentIndex[0]][currentIndex[1]];
        gameBoard[otherIndex[0]][otherIndex[1]] = temp;
    }

    private void mergeAdjacentTiles(Move move) {
        int[] currentIndex;
        int[] adjacentIndex;

        currentIndex = getInitialIndexForMove(move);
        adjacentIndex = getNextIndex(currentIndex, move);
        while(adjacentIndex != null) {
            int currentValue = gameBoard[currentIndex[0]][currentIndex[1]];
            int adjacentValue = gameBoard[adjacentIndex[0]][adjacentIndex[1]];
            if (currentValue == adjacentValue) {
                if (move == Move.LEFT) {
                    gameBoard[adjacentIndex[0]][adjacentIndex[1]] = currentValue + adjacentValue;
                } else if (move == Move.RIGHT) {
                    gameBoard[adjacentIndex[0]][adjacentIndex[1]] = currentValue + adjacentValue;
                } else if (move == Move.UP) {
                    gameBoard[adjacentIndex[0]][adjacentIndex[1]] = currentValue + adjacentValue;
                } else if (move == Move.DOWN) {
                    gameBoard[adjacentIndex[0]][adjacentIndex[1]] = currentValue + adjacentValue;
                }
            }

            currentIndex = adjacentIndex;
            adjacentIndex = getNextIndex(currentIndex, move);
        }

    }*/

    private int[] getInitialIndexForMove(Move move) {
        switch (move) {
            case LEFT:
                return new int[]{0, 3};
            case RIGHT:
                return new int[]{0, 0};
            case UP:
                return new int[]{0, 0};
            case DOWN:
                return new int[]{3, 0};
            default:
                throw new IllegalStateException("Unexpected value: " + move);
        }
    }

    private int[] getNextIndex(int[] currentIndex, Move move) {
        if (move == Move.RIGHT) {
            if (currentIndex[0] == (GAME_BOARD_HEIGHT - 1) && currentIndex[1] == (GAME_BOARD_WIDTH - 1)) {
                return null;
            }

            int x = currentIndex[1] + move.getxDelta();
            int y = currentIndex[0];

            if (x >= GAME_BOARD_WIDTH) {
                y += 1;
                x = x%(GAME_BOARD_WIDTH -1);
            }

            return new int[]{y, x};

        } else if (move == Move.LEFT) {
            if (currentIndex[1] == 0  && currentIndex[0] == (GAME_BOARD_WIDTH -1)) {
                return null;
            }
            int x = currentIndex[1] + move.getxDelta();
            int y = currentIndex[0];

            if (x < 0) {
                y += 1;
                x = GAME_BOARD_WIDTH - 1;
            }
            return new int[]{y, x};
        } else if (move == Move.UP) {
            if (currentIndex[0] == (GAME_BOARD_HEIGHT - 1) && currentIndex[1] == (GAME_BOARD_WIDTH - 1)) {
                return null;
            }

            int x = currentIndex[1];
            int y = currentIndex[0] + move.getyDelta();

            if (y >= GAME_BOARD_HEIGHT) {
                x += 1;
                y = 0;
            }

            return new int[]{y, x};
        } else if (move == Move.DOWN) {
            if (currentIndex[0] == GAME_BOARD_WIDTH - 1 && currentIndex[1] == 0) {
                return  null;
            }

            int x = currentIndex[1];
            int y = currentIndex[0] + move.getyDelta();

            if (y < 0) {
                x = 0;
                y = GAME_BOARD_HEIGHT - 1;
            }

            return new int[]{y, x};
        }
        return null;
    }

/*
    private int[] getNextIndex(int[] currentIndex, Move move) {
        //cases where the currentIndex is the last index
        if (move == Move.RIGHT  && currentIndex[0] == 3 && currentIndex[1] == 3) {
            return null;
        } else if (move == Move.LEFT && currentIndex[0] == 3 && currentIndex[1] == 3) {
            return null;
        } else if (move == Move.DOWN && currentIndex[0] == 3 && currentIndex[1] == 0) {
            return null;
        } else if (move == Move.UP && currentIndex[0] == 3 && currentIndex[1] == 3) {
            return null;
        }

        int x = currentIndex[0] + move.getxDelta();
        int y = currentIndex[1] + move.getyDelta();

        if (x >=(GAME_BOARD_WIDTH)){
            x = x%(GAME_BOARD_HEIGHT - 1);
            y += 1;
        } else if (y >=(GAME_BOARD_HEIGHT)) {
            x += 1;
            y = y%(GAME_BOARD_HEIGHT -1);
        } else if (x < 0 && Move.LEFT == move) {
            x = GAME_BOARD_WIDTH - 1;
            y += 1;
        } else if (y < 0  && Move.DOWN == move) {
            y = GAME_BOARD_HEIGHT -1;
            x +=1;
        }

        return new int[]{y,x};
    }*/


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
        LEFT(-1, 0),
        RIGHT(1, 0),
        UP(0, 1),
        DOWN(0, -1);

        private int xDelta, yDelta;

        Move(int xDelta, int yDelta){
            this.xDelta = xDelta;
            this.yDelta = yDelta;
        }

        int getxDelta() {
            return xDelta;
        }

        int getyDelta() {
            return yDelta;
        }
    }


    protected class TestAccessor {

        void setGameBoard(int[][] testGameBoard) {
            gameBoard = testGameBoard;
        }

    }
}
