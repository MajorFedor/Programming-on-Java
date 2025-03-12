import java.util.Scanner;

public class Main {

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
            System.out.println("Login less then 5.");
            validate = false;
        }
        if(login.contains(" ")){
            validate = false;
        }
        return validate;
    }

    public static boolean passwordValidate(String password){ //ДОПИСАТЬ КОД
        String str = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890!@#$%^&*:;";
        char[] allowedChars = str.toCharArray();
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


        return validate;
    }


    public static void register(Scanner sc, String[] login, String[] password) { // ИСПРАВИТЬ КОД

        while (true) {

            System.out.println("Enter ur login. (5 characters, w/o space's)");
            String loginInput = sc.nextLine();
            System.out.println("Enter ur password. (10 symbols, w/o space's, 3 numbers in pass, 1 special symbol)");
            String passwordInput = sc.nextLine();

            if(loginValidate(loginInput) == true && passwordValidate(passwordInput) == true && checkRestrictedWords(loginInput, passwordInput) == true){
                for(int i = 0 ; i < 15; i++){
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
            }
        }

    }

    public static void login(Scanner sc, String[] login, String[] password){ // ЕЩЕ НЕ ЗНАЮ
        while(true){
            System.out.println("Enter ur login.");
            String loginInput = sc.nextLine();
            System.out.println("Enter ur password.");
            String passwordInput = sc.nextLine();

            for(int i = 0 ; i < 15; i++){
                if(login[i] != null && login[i].equals(loginInput) && password[i] != null && password[i].equals(passwordInput)){
                    System.out.println("Login successfuly. welcome" + loginInput);
                    break;
                }
            }
            System.out.println("Incorrect login or password. try again");
            return;
        }
    }

    public static void authMenu(Scanner sc, String[] login, String[] password) {

        while (true) {
            String AuthMenu = """
                    --Authentication--
                    1. Register.
                    2. Login.
                    3. Admin panel.
                    4. Exit
                    """;
            System.out.println(AuthMenu + "Choose one of the option:");
            int choice = sc.nextInt();

            switch (choice){
                case 1:
                    register(sc, login, password);
                    break;
                case 2:
                    login(sc, login, password);
                    break;
                case 3:
                    adminPanel(sc);
                    break;
                case 4:
                    return;
                default:
                    login(sc, login, password);
                    break;
            }
        }
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String[] login = new String[15];
        String[] password = new String[15];

        authMenu(sc, login, password);
    }

    public static void adminPanel(Scanner sc){

    }
}
