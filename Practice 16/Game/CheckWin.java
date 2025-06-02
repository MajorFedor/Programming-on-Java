package Game;

public class CheckWin {
    public static boolean checkWin(char[][] board, char currentchar, int size) {
        for (int i = 0; i < size; i++) {
            boolean win = true;
            for (int j = 0; j < size; j++) {
                if (board[i][j] != currentchar) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }

        for (int i = 0; i < size; i++) {
            boolean win = true;
            for (int j = 0; j < size; j++) {
                if (board[j][i] != currentchar) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }

        boolean diagonalLeftSide = true;
        boolean diagonalRightSide = true;
        for (int i = 0; i < size; i++) {
            if (board[i][i] != currentchar) {
                diagonalLeftSide = false;
            }
            if (board[i][size - i - 1] != currentchar) {
                diagonalRightSide = false;
            }
        }

        return diagonalLeftSide || diagonalRightSide;
    }

    public static boolean isBoardFull(char[][] board, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
