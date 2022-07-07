import java.util.ArrayList;
import java.util.Random;

public class Sudoku {
    public int[][] Board = new int[9][9];

    private boolean isInRow(int row, int value) {
        for (int c = 0; c <= 8; c++) {
            if (Board[row][c] == value) {
                return true;
            }
        }
        return false;
    }

    private boolean isInColumn(int column, int value) {
        for (int r = 0; r <= 8; r++) {
            if (Board[r][column] == value) {
                return true;
            }
        }
        return false;
    }

    private boolean isInGroup(int row, int column, int value) {
        int groupRow = (row / 3) * 3;
        int groupColumn = (column / 3) * 3;
        for (int r = groupRow; r <= groupRow + 2; r++) {
            for (int c = groupColumn; c <= groupColumn + 2; c++) {
                if (Board[r][c] == value) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean isFilled() {
        for (int r = 0; r <= 8; r++) {
            for (int c = 0; c <= 8; c++) {
                if (Board[r][c] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isWinner() {
        int value;
        boolean isDuplicated;
        for (int r = 0; r <= 8; r++) {
            for (int c = 0; c <= 8; c++) {
                value = Board[r][c];
                Board[r][c] = 0;
                isDuplicated = isInRow(r, value) || isInColumn(c, value) || isInGroup(r, c, value);
                if (isDuplicated) {
                    return false;
                }
                Board[r][c] = value;
            }
        }
        return true;
    }

    private boolean Solve() {
        if (isFilled()) {
            if (isWinner()) {
                return true;
            }
            return false;
        }

        boolean isDuplicated;
        for (int r = 0; r <= 8; r++) {
            for (int c = 0; c <= 8; c++) {
                if (Board[r][c] == 0) {
                    ArrayList<Integer> choices = new ArrayList<Integer>();
                    Random rand = new Random();
                    for (int e = 1; e <= 9; e++) {
                        choices.add(e);
                    }
                    while (choices.size() > 0) {
                        int randChoice = rand.nextInt(choices.size());
                        int v = choices.get(randChoice);
                        choices.remove(randChoice);
                        isDuplicated = isInRow(r, v) || isInColumn(c, v) || isInGroup(r, c, v);
                        if (!isDuplicated) {
                            Board[r][c] = v;
                            if (Solve()) {
                                return true;
                            } else {
                                Board[r][c] = 0;
                            }
                        }
                    }
                    if (Board[r][c] == 0) {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public void New() {
        Random rand = new Random();
        Board[0][0] = rand.nextInt(10);
        Solve();

        int row = 0;
        int column = 0;
        for (int d = 0; d < 20; d++) {
            do{
                row = rand.nextInt(9);
                column = rand.nextInt(9);
            } while (Board[row][column] == 0);
            Board[row][column] = 0;
        }
    }
}
