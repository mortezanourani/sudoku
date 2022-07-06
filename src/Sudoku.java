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
    
    private boolean isFilled(int[][] board) {
        for (int r = 0; r <= 8; r++) {
            for (int c = 0; c <= 8; c++) {
                if (board[r][c] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isWinner(int[][] board) {
        int value;
        boolean isDuplicated;
        for (int r = 0; r <= 8; r++) {
            for (int c = 0; c <= 8; c++) {
                value = board[r][c];
                board[r][c] = 0;
                isDuplicated = isInRow(board, r, value) || isInColumn(board, c, value) || isInGroup(board, r, c, value);
                if (isDuplicated) {
                    return false;
                }
                board[r][c] = value;
            }
        }
        return true;
    }

    private boolean hasSolution(int[][] board) {
        if (isFilled(board)) {
            if (isWinner(board)) {
                return true;
            }
            return false;
        }

        boolean isDuplicated;
        for (int r = 0; r <= 8; r++) {
            for (int c = 0; c <= 8; c++) {
                if (board[r][c] != 0) {
                    continue;
                } else {
                    for (int v = 1; v <= 9; v++) {
                        isDuplicated = isInRow(board, r, v) || isInColumn(board, c, v) || isInGroup(board, r, c, v);
                        if (isDuplicated) {
                            continue;
                        } else {
                            board[r][c] = v;
                            if (hasSolution(board)) {
                                return true;
                            } else {
                                board[r][c] = 0;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
