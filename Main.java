import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileReader;
import java.io.BufferedReader;

public class Main {

    public static Scanner sc = new Scanner(System.in);
    public static String settings = "settings.txt";
    public static String statistic = "statistic.txt";
    public static int boardSize = 3;
    public static String firstPlayer = "PlayerX";
    public static String secondPlayer = "PlayerO";
    public static final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    public static final LocalDateTime date = LocalDateTime.now();

    public static void saveStats(String winner, int size, String firstPlayer, String secondPlayer) {
    try (FileWriter fw = new FileWriter(statistic, true)) {
        fw.write("Winner: " + winner + "\n");
        fw.write("Date: " + date.format(format) + "\n");
        fw.write("Field size: " + size + "x" + size + "\n");
        fw.write("First Player: " + firstPlayer + "\n");
        fw.write("Second Player: " + secondPlayer + "\n");
    } catch (IOException e) {
        System.out.println("File already occured.");
    }
}
   
    public static void showStats(){
        File f = new File(statistic);
        if(!f.exists()){
            System.out.println("Play 1 game to show statistic");
            return;
        }

        try (BufferedReader fr = new BufferedReader(new FileReader(statistic))) {
            String line;

            while((line = fr.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Oops, file already occured.");
        }
    }

    public static void checkExistingSettings(){

        File f = new File(settings); // https://stackoverflow.com/questions/1816673/how-do-i-check-if-a-file-exists-in-java
         if(!f.exists()){
            System.out.println("Didnt found any settings. They will be automatically set to default.");
            saveSettings();
         } else {
            System.out.println("Settings have been uploaded");
            loadSettings();
         }
    }

    public static void saveSettings() {

        String size = String.valueOf(boardSize);
        try (FileWriter fw = new FileWriter(settings)) {
            fw.write(firstPlayer + "\n");
            fw.write(secondPlayer + "\n");
            fw.write(size);
            System.out.println("Settings have been saved.");
        } catch (IOException e) {
            System.out.println("File already occured.");
        }
    }

    public static void loadSettings() {
         try (Scanner fs = new Scanner(new File("settings.txt"))) {
            firstPlayer = fs.nextLine();
            secondPlayer = fs.nextLine();
            boardSize = Integer.parseInt(fs.nextLine());

        } catch (IOException e) {
            System.out.println("Woopsy file already occured.");
        }
    }

    public static void changeName() {

        String menu = """
            First player name: %s
            Second player name: %s
            Do you want to change it?
            Type "yes" if u want, and "no" to return in menu
            """;
        System.out.printf(menu, firstPlayer, secondPlayer);

        String choice = sc.nextLine();
        if(choice.equals("Yes")){
            System.out.println("First player name is:");
            String firstChange = sc.nextLine();
            firstPlayer = firstChange;

            System.out.println("Second player name is:");
            String secondChange = sc.nextLine();
            secondPlayer = secondChange;
        } else {
            return;
        }
    }

    public static void settingsMenu() {

        String settingsMenu = """
                --Settings--
                1. Change size.
                2. Change player name.
                3. Save settings.
                4. Load settings.
                5. Back to menu.
                """;

        while (true) {
            System.out.println(settingsMenu);

            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        settingsSizeMenu();
                        break;
                    case 2:
                        changeName();
                        break;
                    case 3:
                        saveSettings();
                        break;
                    case 4:
                        loadSettings();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Choose correct menu item. ");
                }
                
            }
        }
    }

    public static int settingsSizeMenu() {
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

                switch (choice) {
                    case 1:
                        boardSize = 3;
                        break;
                    case 2:
                        boardSize = 5;
                        break;
                    case 3:
                        boardSize = 7;
                        break;
                    case 4:
                        boardSize = 9;
                        break;
                    default:
                        boardSize = 3;
                        System.out.println("");
                        break;
                }
                break;
            } else {
                System.out.println("Please enter a number.");
                sc.nextLine();
            }
        }
        return boardSize;
    }

    public static void startGameMenu() {
        String startGameMenu = """
                --Game--
                1.Start game.
                2.Back to menu.
                """;

        while (true) {
            System.out.println(startGameMenu);

            System.out.println("Next step: ");
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();

                if (choice == 1) {
                    startGame(boardSize, true);
                } else if (choice == 2) {
                    break;
                }
            }
        }
    }

    public static void menu() {
        String menu = """
                --Menu--
                1. Start game.
                2. Settings.
                3. Show statistic.
                4. Exit.
                """;

        while (true) {
            System.out.println(menu);

            System.out.println("Next step: ");
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        startGame(boardSize, true);
                        break;
                    case 2:
                        settingsMenu();
                        break;
                    case 3:
                        showStats();
                        break;
                    case 4:
                        return;
                    default:
                        break;
                }
            }
        }
    }

    public static void main(String[] args) {
        checkExistingSettings();
        menu();
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
            String playerName = playerX ? firstPlayer : secondPlayer;
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

            if (checkWin(board, currentchar, size)) {
                gameEnd = true;
                System.out.println("Player " + playerName + "("+currentchar + ") wins!");
                saveStats(playerName, size, firstPlayer, secondPlayer);
            } else if (isBoardFull(board, size)) {
                gameEnd = true;
                System.out.println("It's a draw!");
                saveStats(playerName, size, firstPlayer, secondPlayer);
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