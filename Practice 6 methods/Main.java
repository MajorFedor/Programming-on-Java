import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static String userString;
    public static boolean stringFirstCheck = false; 
    public static boolean stringSecondCheck = false; 

    public static String stringCheck(){
        
        int wordLength = 0;
        int wordNum = 0;

        while (true) {

            System.out.println("Enter string");
            userString = sc.nextLine();

            char[] strToChar = userString.toCharArray();

            for(char c : strToChar){
                if (c != ' ') {
                    wordLength++;
                } else {
                    if (wordLength >= 3) {
                        stringFirstCheck = true;
                    }
                    if (wordLength > 0) {
                        wordNum++;
                    }
                    wordLength = 0;
                }
            }

            if (wordLength > 0) {
                wordNum++;
            }
            if(stringFirstCheck == true && wordNum >= 2){
                stringSecondCheck = true;
            }


            if(stringFirstCheck == true && stringSecondCheck == true){
                System.out.println("Ur string is valid");
                System.out.println();
                return userString;
            } else {
                System.out.println("Invalid input");
                System.out.println();
                stringFirstCheck = false;
                stringSecondCheck = false;
                continue;
            }
        }
        
    }

    public static String reverseStringByChar(String userString){

        char[] strToChar = userString.toCharArray();
        String reversedString = "";

        for (int i = strToChar.length - 1; i >= 0; i--) {
            reversedString = reversedString + strToChar[i];
        }

        System.out.println("Reversed string by char: " + reversedString);
        return reversedString;

    }

    public static String reverseString(String userstString){

        char[] strToChar = userstString.toCharArray();

        String reversedStringSecond = "";

        for (int i = strToChar.length - 1; i >= 0; i--) {
            reversedStringSecond += strToChar[i];
        }

        String[] reversedTable = reversedStringSecond.split(" ");

        for (int i = reversedTable.length - 1; i >= 0; i--) {
            System.out.print(reversedTable[i] + ' ');
        }
        System.out.println();
        
        return reversedStringSecond;
    }

    public static boolean Error() {
        if (userString == null || userString.isEmpty()) {
            System.out.println("Error. Try using the first function (String checker) first.");
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
        while (true) {

            String menu = """
                1. String checker.
                2. Reverse string by char.
                3. Reverse string.
                """;

            System.out.println("Enter ur choice: ");
            System.out.println();

            System.out.println(menu);
        
            int choice;

            if(sc.hasNextInt()){
                choice = sc.nextInt();
                sc.nextLine();

                if(choice == 1){
                    stringCheck();
                } else if(choice == 2 ){
                    if(!Error()){
                        reverseStringByChar(userString);
                    }
                } else if(choice == 3){
                    if(!Error()){
                        reverseString(userString);
                    }
                } else {
                    System.out.println("Uncorrect choice. try again");
                    continue;
                }
            } else{
                System.out.println("Uncorrect number.");
            }
        }
        
    }
}
