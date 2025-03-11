import java.util.Scanner;

public class Main {

    public static boolean loginValidate (String login){
        boolean validate = false;

        if(login.length() < 5){
            System.out.println("Login less then 5.");
            
        } else {
            validate = true;
        }

        if(login.contains(" ")){
            return false;
        }

        return validate;
    }

    public static boolean passwordValidate(String password){
        String str = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        char[] allowedChars = str.toCharArray();

        if(password.length() < 10){
            System.out.println("Password less than 10 symbols");
            return false;
        }
        if(password.contains(" ")){
            System.out.println("Spaces in password cannot be used.");
            return false;
        }

        int numCount = 0;
        for(char c : password.toCharArray()){
            if(Character.isDigit(c)){
                numCount++;
            }
        }
        if(numCount < 3){
            System.out.println("Numbers in password less than 3.");
            return false;
        }

//        for(char c : password.toCharArray()){
//            if(){
//                return false;
//            }
//        }

        return true;

    }


    public static void register(Scanner sc) {

        System.out.println("Enter ur login.");
        String login = sc.nextLine();

        sc.nextLine();

        System.out.println("Enter ur password.");
        String password = sc.nextLine();

        loginValidate(login);
        passwordValidate(password);


    }

    public static void login(Scanner sc){
        System.out.println("privet");
    }

    public static void authMenu(Scanner sc) {

        String AuthMenu = """
                --Authentication--
                1. Register.
                2. Login.
                3. Exit
                
                """;
        System.out.println(AuthMenu + "Choose one of the option:");
        int choice = sc.nextInt();

        switch (choice){
            case 1:
                register(sc);
                break;
            case 2:
                login(sc);
                break;
            case 3:
                break;
            default:
                login(sc);
                break;
        }
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String[] login = new String[15];
        String[] password = new String[15];

        authMenu(sc);
    }
}