import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {

    public static final Scanner sc = new Scanner(System.in);

    public static void menu(){
        String filepath = "";

        String menu= """
                    --Menu--
                    1. Write file.
                    2. Read file.
                    3. Exit.\n
                    """;
        
        while (true) {
            System.out.println(menu + "Choose option");
            
            try {
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        filepath = writeFile(filepath); //помог гпт я не понимаю почему у меня не возвращается переменная filepath
                        break;
                    case 2:
                        readFile(filepath);
                        break;
                    case 3:
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

    public static String writeFile(String filePath) {
        FileWriter fw = null;
        boolean append = false;

        System.out.println("1. Rewrite file.\n2. Append to file.");
        while(true){
            try {
                int choice = sc.nextInt();
                if (choice == 2) {
                    append = true;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Incorrect input. try again.");
                sc.next();
            }
        }
        
        try {
            sc.nextLine();

            System.out.println("Enter ur filepath. ex. C:\\Users\\zurabik\\file.txt");
            filePath = sc.nextLine();
            fw  = new FileWriter(filePath, append);
        } catch (NoSuchElementException e) {
            System.out.println("String empty.");
            sc.nextLine();
        } catch (IOException e) {
            System.out.print("File an occupation. try again.");
            sc.nextLine();
        }


        try {

            System.out.println("Type string.");
            String string = sc.nextLine();
            fw.write(string);
            System.out.println("Ur string is successfully writing.");
            return filePath;
        } catch (NoSuchElementException e){
            System.out.println("Enter true filepath.");
        } catch (IOException e) {
            System.out.print("File an occupation. try again.");
            sc.nextLine();
        } finally {
            try {
                if (fw != null){
                    fw.close();
                }
            } catch (IOException e) {
                System.out.print("File an occupation. Try again.");
            }
        }
        return filePath;
    }

    public static void readFile(String filepath){
        FileReader fr = null;

        try{
            fr = new FileReader(filepath);
            System.out.println("File contains: ");
            String s = "";

            while (fr.ready()) {
                s += (char) fr.read();
            }
            System.out.println(s);
        } catch (FileNotFoundException e) {
            System.out.println("File not found! ");
        } catch (IOException e) {
            System.out.println("Cannot read file! ");
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                System.out.println("File an occupation! ");
            }
        }
    }

    public static void main(String[] args){
        menu();
    }
}
