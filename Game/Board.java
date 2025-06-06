package Game;

public class Board {
    public static char[][] initializeBoard(int size) {
        char[][] board = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = ' ';
            }
        }
        return board;
    }

    public static void board(char[][] board, int size) {
        System.out.print("  ");
        for (int j = 1; j <= size; j++) {
            System.out.print(j + " ");
        }
        System.out.println();

        for (int i = 0; i < size; i++) {
            System.out.print((i + 1) + "");
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j]);
                if (j < size - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();

            if (i < size - 1) {
                System.out.print(" ");
                for (int j = 0; j < size * 2 - 1; j++) {
                    System.out.print("-");
                }
                System.out.println();
            }
        }
    }
}
