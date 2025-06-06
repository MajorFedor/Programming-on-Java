import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Main{

    public static final int size = 70;
    public static final String[] notes = new String[size];
    public static final LocalDateTime[] data = new LocalDateTime[size];

    public static void menu(){
        Scanner sc = new Scanner(System.in);
        String menu = """
                ---Menu---
                1. Add note.
                2. Remove note.
                3. Review all note.
                4. Exit.
                """;
        while (true) {
            System.out.println(menu);
            try {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        addNote(sc);
                        break;
                    case 2:
                        removeNote(sc);
                        break;
                    case 3:
                        reviewNote(sc);
                        break;
                    case 4:
                        return;
                    default:
                        addNote(sc);
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error" + e.getMessage());
            }
        }
    }

    public static void addNote(Scanner sc){
        LocalDateTime date = null;

        while (true) {
            try {
                System.out.println("Enter date for note. ex. 2025-04-28T23:59:59");
                date = LocalDateTime.of(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
                sc.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Some troubles " + e.getMessage());
            } catch (DateTimeException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("Enter your note:");
        String notate = sc.nextLine();
        for(int i = 0; i < size ; i++){
            if( i == size){
                System.out.println("No place in notes, Try to delete another.");
            }


            if(data[i] == null){
                data[i] = date;
                notes[i] = notate;
                System.out.println("Operation successfully");
                break;
            }
        }
    }
    public static void removeNote(Scanner sc){
        try {
            System.out.println("Enter date to delete note");
            LocalDateTime date = LocalDateTime.of(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());

            for(int i = 0; i < size; i++){
                if(date.equals(data[i])){
                    data[i] = null;
                    notes[i] = null;
                    System.out.println("Note successfully deleted.");
                }
            }
        } catch (DateTimeException e){
            System.out.println("Some troubles" + e.getMessage());
        }
    }
    public static void reviewNote(Scanner sc){
        for(int i = 0; i < size; i++){
            if(data[i] != null){
                System.out.println(data[i] + " : "  + notes[i]);
                System.out.println();
            }
        }
    }
    public static void main(String[] args) {
        menu();
    }
}