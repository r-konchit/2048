import javafx.scene.shape.StrokeLineCap;
import processing.core.PApplet;

import java.util.ArrayList;

public class GameBoard {
    private int[][] grid;
    private int v, c;
    private ArrayList<String> direction = new ArrayList<>();

    public GameBoard(int width, int height) {

        v = 0;

        grid = new int[height][width];

        direction.add("UP");
        direction.add("DOWN");
        direction.add("LEFT");
        direction.add("RIGHT");

        for (int i = 0; i < 3; i++) {
            placeRanInt(grid);
        }

        display(grid);


    }

    public void move(int key) {

        System.out.println("You are moving " + direction.get(key));

        if (hasMove()) {
            if (key == 0) {
                for (int i = 0; i < grid[0].length; i++) {
                    shiftColUp(i);
                    placeRanInt(grid);
                }
            }

            if (key == 1) {
                for (int i = 0; i < grid[0].length; i++) {
                    shiftColDown(i);
                }
                placeRanInt(grid);
            }

            if (key == 2) {
                for (int i = 0; i < grid.length; i++) {
                    shiftColLeft(i);
                }
                placeRanInt(grid);
            }

            if (key == 3) {
                for (int i = 0; i < grid[0].length; i++) {
                    shiftColRight(i);
                }
                placeRanInt(grid);
            }
        }



//        display(grid);


    }

    public boolean isGameOver() {
        v = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2048) {
                    return true;
                }
            }
        }

        if (!hasMove()) {
//            System.out.println("no more moves");
            if (isFull()) {
//                System.out.println("full");
                return true;
            }
        }


        return false;
    }

    public int[][] getGrid() {
        return grid;
    }

    // Return true if the row and column in location loc are in bounds for the grid
    public boolean isInGrid(int row, int col) {
        if (row >= 0 && col >= 0) {
            if (row < grid.length && col < grid[0].length) {
                return true;
            }
        }
        return false;
    }

    boolean isFull() {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 0) {
                    count++;
                }
            }
        }
        if (count == (grid.length * grid.length)) {
            return true;
        }

        return false;
    }

    boolean hasMove() {

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (isInGrid(i + 1, j)) {
                    if (grid[i][j] == grid[i + 1][j]
                            || grid[i + 1][j] == 0) {
                        return true;
                    }
                }
                if (isInGrid(i - 1, j)) {
                    if (grid[i][j] == grid[i - 1][j]
                            || grid[i - 1][j] == 0) {
                        return true;
                    }
                }
                if (isInGrid(i, j + 1)) {
                    if (grid[i][j] == grid[i][j + 1]
                            || grid[i][j + 1] == 0) {
                        return true;
                    }
                }
                if (isInGrid(i, j - 1)) {
                    if (grid[i][j] == grid[i][j - 1]
                            || grid[i][j - 1] == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    private void placeRanInt(int[][] grid) {

        if (!isFull()) {
            int row, col;

            int n;
            int x = (int) (Math.random() * 8);
            if (x == 8) {
                n = 8;
            } else if (x > 3 && x < 8) {
                n = 4;
            } else {
                n = 2;
            }
            do {
                row = (int) (Math.random() * grid.length);
                col = (int) (Math.random() * grid[0].length);
                System.out.println("+1");
            } while (grid[row][col] != 0);
            grid[row][col] = n;
        }
    }


    public void display(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + "  ");
            }
            System.out.println();
        }
    }


    void shiftColDown(int col) {
        for (int i = 0; i < grid.length - 1; i++) {
            for (int row = 2; row >= 0; row--) {

                if (grid[row][col] != 0) {
                    if (grid[row + 1][col] == 0) {
                        grid[row + 1][col] = grid[row][col];
                        grid[row][col] = 0;
                    }
                    if (grid[row + 1][col] == grid[row][col]) {
                        grid[row + 1][col] += grid[row][col];
                        grid[row][col] = 0;
                    }
                }
            }
        }
    }

    void shiftColUp(int col) {
        for (int i = 0; i < grid.length - 1; i++) {
            for (int row = 1; row <= 3; row++) {
                if (grid[row][col] != 0) {
                    if (grid[row - 1][col] == 0) {
                        grid[row - 1][col] = grid[row][col];
                        grid[row][col] = 0;
                    } else if (grid[row - 1][col] == grid[row][col]) {
                        grid[row - 1][col] += grid[row][col];
                        grid[row][col] = 0;
                    }
                }
            }
        }
    }

    private void shiftColRight(int row) {
        for (int i = 0; i < grid.length - 1; i++) {
            for (int col = 0; col <= 2; col++) {
                if (grid[row][col] != 0) {
                    if (grid[row][col + 1] == 0) {
                        grid[row][col + 1] = grid[row][col];
                        grid[row][col] = 0;
                    }
                    if (grid[row][col + 1] == grid[row][col]) {
                        grid[row][col + 1] += grid[row][col];
                        grid[row][col] = 0;
                    }
                }
            }
        }
    }

    private void shiftColLeft(int row) {
        for (int i = 0; i < grid.length - 1; i++) {
            for (int col = 3; col > 0; col--) {
                if (grid[row][col] != 0) {
                    if (grid[row][col - 1] == 0) {
                        grid[row][col - 1] = grid[row][col];
                        grid[row][col] = 0;
                    }
                    if (grid[row][col - 1] == grid[row][col]) {
                        grid[row][col - 1] += grid[row][col];
                        grid[row][col] = 0;
                    }
                }
            }
        }
    }
}

