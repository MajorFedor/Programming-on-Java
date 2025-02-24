import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);

    public static void settingsMenu(int size) {
        String settingsMenu = """
                --Settings--
                1. Change size.
                2. Back to menu.
                """;

        while (true) {
            System.out.println(settingsMenu);

            if (sc.hasNextInt()) {
                int choice = sc.nextInt();

                if (choice == 1) {
                    size = settingsSizeMenu(size);
                } else if (choice == 2) {
                    break;
                }
            } else {
                System.out.println("Uncorrect function.");
                sc.next();
            }
        }
    }

    public static int settingsSizeMenu( int size) {
        String settingsSizeMenu = """
                --Board Sizes--
                1. 3x3.
                2. 5x5.
                3. 7x7.
                4. 9x9.
                5. Back to settings
                """;

        while (true) {
            System.out.println(settingsSizeMenu);

            if (sc.hasNextInt()) {
                int choice = sc.nextInt();

                if (choice == 1) {
                    size = 3;
                } else if (choice == 2) {
                    size = 5;
                } else if (choice == 3) {
                    size = 7;
                } else if (choice == 4) {
                    size = 9;
                } else {
                    break;
                }

                System.out.println("Board size changed to " + size);
                return size;
            } else {
                System.out.println("Uncorrect function.");
                sc.next();
            }
        }
        return size;
    }

    public static void startGameMenu() {
        String startGameMenu = """
                --Game--
                1.Start game.
                2.Back to menu.
                """;

        int size = 3;

        while (true) {
            System.out.println(startGameMenu);

            System.out.println("Next step: ");
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();

                if (choice == 1) {
                    startGame(size, true);
                } else if (choice == 2) {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        String menu = """
                --Menu--
                1. Start game.
                2. Settings.
                3. Exit.
                """;

        int size = 3;

        while (true) {
            System.out.println(menu);

            System.out.println("Next step: ");
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();

                if (choice == 1) {
                    startGameMenu();
                } else if (choice == 2) {
                    settingsMenu(size);
                } else if (choice == 3) {
                    break;
                }
            } else {
                System.out.println("Uncorrect function.");
                sc.next();
            }
        }
    }

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

    public static void startGame(int size, boolean playerX) {
        System.out.println("Game started. Field size = " + size);
        char currentchar;
        char[][] board = initializeBoard(size);
        boolean gameEnd = false;

        board(board, size);

        while (!gameEnd) {
            currentchar = playerX ? 'X' : 'O';
            System.out.println("It's " + currentchar + "'s turn");

            int firstCoord;
            int secondCoord;

            System.out.println("Enter first coordinate.");
            while (true) {
                if (sc.hasNextInt()) {
                    firstCoord = sc.nextInt() - 1;
                    if (firstCoord < 0 || firstCoord >= size) {
                        System.out.println("Coordinate out of bounds. Try again.");
                    } else {
                        break;
                    }
                } else {
                    sc.next();
                }
            }

            System.out.println("Enter second coordinate.");
            while (true) {
                if (sc.hasNextInt()) {
                    secondCoord = sc.nextInt() - 1;
                    if (secondCoord < 0 || secondCoord >= size) {
                        System.out.println("Coordinate out of bounds. Try again.");
                    } else {
                        break;
                    }
                } else {
                    sc.next();
                }
            }

            if (board[firstCoord][secondCoord] == ' ') {
                board[firstCoord][secondCoord] = currentchar;
            } else {
                System.out.println("Cell is already occupied.");
            }

            if (checkWin(board, currentchar, size)) {
                gameEnd = true;
                System.out.println("Player " + currentchar + " wins!");
            } else if (isBoardFull(board, size)) {
                gameEnd = true;
                System.out.println("It's a draw!");
            }

            playerX = !playerX;
            board(board, size);
        }

        System.out.println("Game over. \n1.Restart Game \n2.Exit.");
        while (true) {
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();

                if (choice == 1) {
                    gameEnd = false;
                    startGame(size, playerX);
                    break;
                } else if (choice == 2) {
                    break;
                } else {
                    System.out.println("Uncorrect function.");
                    continue;
                }
            }
        }
    }

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
