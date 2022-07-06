public class Sudoku {
    public int[][] Board;

    private boolean isInRow(int[][] board, int row, int value) {
        for (int c = 0; c <= 8; c++) {
            if (board[row][c] == value) {
                return true;
            }
        }
        return false;
    }

    private boolean isInColumn(int[][] board, int column, int value) {
        for (int r = 0; r <= 8; r++) {
            if (board[r][column] == value) {
                return true;
            }
        }
        return false;
    }

    private boolean isInGroup(int[][] board, int row, int column, int value) {
        int groupRow = (row / 3) * 3;
        int groupColumn = (column / 3) * 3;
        for (int r = groupRow; r <= groupRow + 3; r++) {
            for (int c = groupColumn; c <= groupColumn + 3; c++) {
                if (board[r][c] == value) {
                    return true;
                }
            }
        }
        return false;
    }
}
