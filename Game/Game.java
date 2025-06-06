package Game;

import Settings.*;
import Statistic.*;
import java.util.Scanner;

public class Game {
    private static String winnerName = "";
    public static void startGame(int size, boolean playerX) {
        System.out.println("Game started. Field size = " + size);
        char currentchar;
        char[][] board = Board.initializeBoard(size);
        boolean gameEnd = false;
        Scanner sc = new Scanner(System.in);

        Board.board(board, size);

        while (!gameEnd) {
            currentchar = playerX ? 'X' : 'O';
            String playerName = playerX ? SettingsData.firstPlayer : SettingsData.secondPlayer;
            System.out.println("It's " + playerName + "'s turn");

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

            if (CheckWin.checkWin(board, currentchar, size)) {
                gameEnd = true;
                winnerName = playerName;
                System.out.println("Player " + playerName + "("+currentchar + ") wins!");
                SaveStatistic.saveStats();
            } else if (CheckWin.isBoardFull(board, size)) {
                gameEnd = true;
                System.out.println("It's a draw!");
                SaveStatistic.saveStats();
            }

            playerX = !playerX;
            Board.board(board, size);
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

    public static String winner(){
        return winnerName;
    }
}
