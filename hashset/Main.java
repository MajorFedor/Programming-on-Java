package hashset;

import java.util.Scanner;
import hashset.UserActions.UserActions;
import hashset.Users.User;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static UserActions userActions = new UserActions();
    private static User currentUser = null;

    public static void main(String[] args) {
        loginMenu();
    }

    public static void loginMenu() {
        while (true) {
            System.out.println("""
                    ------Login Menu-------
                    1. Sign in
                    2. Sign up
                    3. User actions
                    4. Exit
                    """);

            System.out.print("Choose one of this options: ");
            int value = 0;
            if (sc.hasNextInt()) {
                value = sc.nextInt();
                sc.nextLine();
            } else {
                sc.next();
                System.out.println("Type number.");
                continue;
            }

            switch (value) {
                case 1:
                    signIn();
                    break;
                case 2:
                    signUp();
                    break;
                case 3:
                    if (!checkLoggedIn()) {
                        continue;
                    }
                    actionsMenu();
                    break;
                case 4:
                    System.out.println("Exit.");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public static void actionsMenu() {
        while (true) {
            System.out.println("""
                    ------Actions Menu-------
                    1. Check if user registered
                    2. Remove user by id
                    3. Logout user by id
                    4. Print total unique users
                    5. Display all users
                    6. Back
                    """);

            System.out.print("Choose: ");
            int value = 0;
            if (sc.hasNextInt()) {
                value = sc.nextInt();
                sc.nextLine();
            } else {
                sc.next();
                System.out.println("Type number.");
                continue;
            }

            switch (value) {
                case 1:
                    if (!checkLoggedIn()) {
                        return;
                    }
                    System.out.print("Enter login to check: ");
                    String login = sc.next();
                    userActions.isUserRegistered(login);
                    break;
                case 2:
                    if (!checkLoggedIn()) {
                        return;
                    }
                    System.out.print("Enter user id: ");
                    if (sc.hasNextInt()) {
                        value = sc.nextInt();
                        sc.nextLine();
                    } else {
                        sc.nextLine();
                        System.out.println("Type number.");
                        continue;
                    }
                    break;
                case 3:
                    if (!checkLoggedIn()) {
                        return;
                    }
                    System.out.print("Enter user id: ");
                    if (sc.hasNextInt()) {
                        int id = sc.nextInt();
                        sc.nextLine();
                        userActions.logoutUser(id);
                        if (currentUser != null && currentUser.getId().equals(id)) {
                            currentUser = null;
                            System.out.println("You logged out. Returning to login menu.");
                            return;
                        }
                    } else {
                        sc.next();
                        System.out.println("Invalid id.");
                    }
                    break;
                case 4:
                    if (!checkLoggedIn()) {
                        return;
                    }
                    userActions.printTotalUniqueUser();
                    break;
                case 5:
                    if (!checkLoggedIn()) {
                        return;
                    }
                    userActions.displayAllUsers();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public static void signIn() {
        System.out.print("Enter login: ");
        String name = sc.next();
        System.out.print("Enter password: ");
        String password = sc.next();

        User user = userActions.loginUser(name, password);
        if (user != null) {
            currentUser = user;
            System.out.println("Login successful! Welcome, " + name + ".");
        } else {
            System.out.println("Invalid login or password.");
        }
    }

    public static void signUp() {
        System.out.print("Enter new login: ");
        String name = sc.next();
        System.out.print("Enter new password: ");
        String password = sc.next();
        userActions.registerUser(name, password);
    }

    private static boolean checkLoggedIn() {
        if (currentUser != null && currentUser.isLoggedIn()) {
            return true;
        }
        currentUser = null;
        System.out.println("You are not logged in. Please sign in first.");
        signIn();
        return currentUser != null && currentUser.isLoggedIn();
    }
}