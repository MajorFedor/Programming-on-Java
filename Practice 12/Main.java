import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.*;

public class Main {

    public static final Scanner sc = new Scanner(System.in);

    public static void menu(String filepath){
        String menu= """
                    --Menu--
                    1. Write file.
                    2. Read file.
                    3. Read file with diapazones.
                    4. Exit.\n
                    """;
        
        while (true) {
            System.out.println(menu + "Choose option");
            
            try {
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        writeFile(filepath);
                        break;
                    case 2:
                        readFullFile(filepath);
                        break;
                    case 3:
                        readDiapazoneFile(filepath);
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid input\n");
                }
            } catch(IllegalStateException e ){
                System.out.println("Illegal error\n");
            } catch (InputMismatchException e){
                System.out.println("Input Error\n");
                sc.nextLine();
            } 
        }
    }

    public static boolean appendToFile(){
        boolean append = false;
        System.out.println("1. Rewrite file.\n2. Append to file.");
        while(true){
            try {
                int choice = sc.nextInt();
                sc.nextLine();
                if (choice == 2) {
                    append = true;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Incorrect input. try again.");
                sc.next();
            }
        }
        return append;

    }

    public static void writeFile(String filepath){
        boolean append = appendToFile();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, append))) {
            System.out.println("Type your string.");
            String str;
            int strCounter = 1;
            while(true){
                System.out.print(strCounter + " ");
                str = sc.nextLine();
                if(str.isEmpty()){
                    break;
                }
                writer.write(str);
                writer.newLine();
                strCounter++;
            }
            System.out.println("Text has been append to file successfully");
        } catch (IOException e) {
            System.out.println("Some troubles." + e.getMessage());
        }
    }

    // public static void writeRowFile(String filepath){
    //     boolean append = true;
    //     String line;
    //     int rowCounter = 1;

    //     System.out.println("Enter your row.");
    //     int rowChoice = sc.nextInt();

    //     try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, append));
    //          BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
    //         while ((line = reader.readLine()) != null) {
    //             if(rowCounter == rowChoice){

    //             }
    //         }
    //     } catch (IOException e) {
    //         System.out.println("File occured. Try again.");
    //     }
    // }

    public static void readFullFile(String filepath){
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            int strCounter = 0;
            while ((line = reader.readLine()) != null) {
                strCounter++;
                System.out.println(strCounter+ " " + line);                
            }
        } catch (IOException e) {
            System.out.println("Some troubles. " + e.getMessage());
        }
    }

    public static int[] diapazones(){
        int[] diapazone = new int[2];
        while (true) {
            try{
                System.out.println("Type where diapazone start.");
                diapazone[0] = sc.nextInt();
                System.out.println("Type where diapazone end.");
                diapazone[1] = sc.nextInt();

                if(diapazone[0] < diapazone[1] && diapazone[0] != 0 && diapazone[1] != 0){
                    break;
                } else {
                    System.out.println("Incorrect diapazone's. Try again.");
                }

            } catch (InputMismatchException e ){
                System.out.println("Input only numbers. " + e.getMessage());
                sc.next();
            }
        }
        return diapazone;
    }

    public static void readDiapazoneFile(String filepath){
        int[] diapazones = diapazones();
        int start = diapazones[0];
        int end = diapazones[1];
        int lineNumber = 1;
        String line;
        System.out.println("Lines in range " + start + " to " + end);

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            while ((line = reader.readLine()) != null) {
                if(lineNumber >= start && lineNumber <= end){
                    System.out.println(lineNumber + " " + line);
                }
                lineNumber++;
            }
        } catch (IOException e) {
            System.out.println("Some Troubles" + e.getMessage());
        }
    }

    public static void main(String[] args){

        System.out.println("Type file path. ex. E:\\1234\\file.txt");
        String filepath = null;
        try {
            filepath = sc.nextLine();
        } catch (NoSuchElementException e) {
            System.out.println("Some troubles. Try again. " + e.getMessage());
        }

        menu(filepath);
    }
}