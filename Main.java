
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static final int maxUsers = 15;

    public static Boolean checkRestrictedWords(String login, String password) { //correct
        String[] restrictedWords = {"admin", "Admin", "pass", "password", "qwerty", "ytrewq", "zurik"};

        for(String str : restrictedWords){
            if(login.contains(str)){
                System.out.println("Login contains restricted word");
                return false;
            }
            if(password.contains(str)){
                System.out.println("Password contains restricted word");
                return false;
            }
        }
        return true;
    }

    public static boolean loginValidate (String login){ // correct
        boolean validate = true;
        if(login.length() < 5){
            validate = false;
        }
        if(login.contains(" ")){
            validate = false;
        }
        return validate;
    }

    public static boolean passwordValidate(String password){ //ДОПИСАТЬ КОД - correct
        boolean validate = true;

        //e
        if(password.length() < 10){
            System.out.println("Password less than 10 symbols");
            validate = false;
        }
        //d
        if(password.contains(" ")){
            System.out.println("Spaces in password cannot be used.");
            validate = false;
        }

        //c
        int numCount = 0;
        for(char c : password.toCharArray()){
            if(Character.isDigit(c)){
                numCount++;
            }
        }
        if(numCount < 3){
            System.out.println("Numbers in password less than 3.");
            validate = false;
        }
        if(password.matches("\\w") == true){ // подсмотрел в инете єто и решил скопировать полностью потому что не понял как интерпритировать по своему https://ru.stackoverflow.com/questions/694275/%D0%9C%D0%B5%D1%82%D0%BE%D0%B4-matches-string-regex 
            System.out.println("Password doesnt contain spec symbol");
            validate = false;
        }


        return validate;
    }


    public static void register(Scanner sc,String[] login, String[] password, int maxUsers) { // ИСПРАВИТЬ КОД

        if(login.length > maxUsers){
            throw new ArrayIndexOutOfBoundsException("User limit");
        }
        if(password.length > maxUsers){
            throw new ArrayIndexOutOfBoundsException("Password limit");
        }

        System.out.println("Enter ur login. (5 characters, w/o space's)");
        String loginInput = sc.nextLine();

        System.out.println("Enter ur password. (10 symbols, w/o space's, 3 numbers in pass, 1 special symbol)");
        String passwordInput = sc.nextLine();

        while (true) {

            try {
                if(loginValidate(loginInput) == true && passwordValidate(passwordInput) == true && checkRestrictedWords(loginInput, passwordInput) == true){
                    for(int i = 0 ; i < maxUsers; i++){
                        if(login[i] == null){
                            login[i] = loginInput;
                        }
                        if(password[i] == null){
                            password[i] = passwordInput;
                        }
                        System.out.println("Registration succesfully");
                        return;
                    }
                } else {
                    System.out.println("Registration failed. Try again");
                    loginInput = sc.nextLine();
                    passwordInput = sc.nextLine();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("User limit");
                return;
            }
        }

    }

    public static void login(Scanner sc, String[] login, String[] password, int maxUsers){ // ЕЩЕ НЕ ЗНАЮ
        while(true){
            System.out.println("Enter ur login.");
            String loginInput = sc.nextLine();
            System.out.println("Enter ur password.");
            String passwordInput = sc.nextLine();

            try{
                for(int i = 0 ; i < 3; i++){
                    if(login[i] != null && login[i].equals(loginInput) && password[i] != null && password[i].equals(passwordInput)){
                        System.out.println("Login successfuly. welcome " + loginInput);
                        return;
                    } else {
                        System.out.println("Incorrect login or password. try again");
                    }
                }
            } catch(NoSuchElementException e) {
                System.out.println("Incorrect input");
            }
        }
    }

    public static void authMenu(Scanner sc, String[] login, String[] password, int maxUsers) {

        while (true) {
            String AuthMenu = """
                    --Authentication--
                    1. Register.
                    2. Login.
                    3. Admin panel.
                    4. Exit
                    """;
            System.out.println(AuthMenu + "Choose one of the option:");

            try {

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice){
                    case 1:
                        register(sc ,login, password, maxUsers);
                        break;
                    case 2:
                        login(sc, login, password, maxUsers);
                        break;
                    case 3:
                        adminLogin(sc, login, password);
                        break;
                    case 4:
                        System.out.println("App closed.");
                        return;
                    default:
                        System.out.println("Incorrect option");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Incorrect input.");
                sc.nextLine();
            }
        }
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String[] login = new String[maxUsers];
        String[] password = new String[maxUsers];

        authMenu(sc, login, password, maxUsers);
    }

    public static void adminPanel(Scanner sc, String[] login, String[] password){
        while (true) {
            String adminMenu = """
                    --Admin Menu--
                    1.delete user.
                    2. Soon.
                    3. Soon.
                    """;

            try{
                System.out.println(adminMenu + "Enter ur option.");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        deleteUser(sc, login, password, maxUsers);
                        break;
                    default:
                        System.out.println("Incorrect option");
                        break;
                }
            } catch (InputMismatchException e){
                System.out.println("Uncorrect input, try again");
                sc.nextLine();
            }
        }
    }

    public static void adminLogin(Scanner sc, String[] login, String[] password){
        while (true) {
            System.out.println("Enter ur admin login.");
            String adminLogin = sc.nextLine();
            System.out.println("Enter ur admin password");
            String adminPassword = sc.nextLine();

            try{
                if(adminLogin.equals("admin") && adminPassword.equals("Zurik123")){
                    adminPanel(sc, login, password);
                } else {
                    System.out.println("Uncorrect login or password");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Please enter correct request.");
            }
        }
    }

    public static String[] deleteUser (Scanner sc, String[] login, String[] password, int maxUsers){
        sc.nextLine();
        while (true) {
            System.out.println("Enter login, which u want to delete.");
            String loginInput = sc.nextLine();

            try{
                for(int i = 0 ; i < maxUsers ; i++){
                    if(loginInput.equals(login[i])){
                        login[i] = null;
                        password[i] = null;
                        System.out.println("User deleted " + loginInput);
                        return login;
                    } else {
                        System.out.println("User not found");
                        break;
                    }
                }
            } catch(NoSuchElementException e){
                System.out.println("Uncorrect request. try again");
            }
        }
    }
}
