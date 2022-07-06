public class Sudoku {
    public int[][] Board;

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
        for (int r = groupRow; r <= groupRow + 3; r++) {
            for (int c = groupColumn; c <= groupColumn + 3; c++) {
                if (Board[r][c] == value) {
                    return true;
                }
            }
        }
        return false;
    }
}
