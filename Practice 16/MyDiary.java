import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MyDiary {
    public static final int size = 70;
    public static final LocalDateTime[] data = new LocalDateTime[size];
    public static final String[] notes = new String[size];
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void start() {
        Scanner sc = new Scanner(System.in);
        String filepath = "";
        System.out.println("1. Create new diary \n2. Load existing diary");
        while (true) {
            try {
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        System.out.println("Enter path for creating new file: ");
                        filepath = sc.nextLine();
                        break;
                    case 2:
                        System.out.println("Enter already existing file path: ");
                        filepath = sc.nextLine();
                        utils.FileUtils.readFullFile(filepath);
                        break;
                    default:
                        System.out.println("Enter 1 or 2.");
                        continue;
                }
                break;
            } catch (InputMismatchException e){
                System.out.println("Some troubles. Check if you type correct.");
                sc.nextLine();
            }
        }
        menu(filepath);
    }

    public static void menu(String filepath) {
        Scanner sc = new Scanner(System.in);
        String menu = """
                --- Menu ---
                1. Add note
                2. Remove note
                3. Review all notes
                4. Exit
                """;

        while (true) {
            System.out.println(menu);
            try {
                int input = sc.nextInt();
                sc.nextLine();
                switch (input) {
                    case 1:
                        addNote();
                        break;
                    case 2:
                        removeNote();
                        break;
                    case 3:
                        reviewNotes();
                        break;
                    case 4:
                        checkSaveFile(filepath);
                        return;
                    default:
                        System.out.println("Wrong option.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Some troubles: " + e.getMessage());
                sc.nextLine();
            }
        }
    }

    public static void addNote() {
        Scanner sc = new Scanner(System.in);
        LocalDateTime date = null;
        while (true) {
            try {
                System.out.println("Enter date and time (yyyy MM dd HH mm ss):");
                date = LocalDateTime.of(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
                sc.nextLine();
                break;
            } catch (DateTimeException e) {
                System.out.println("Invalid format. Try again.");
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again.");
                sc.nextLine();
            }
        }

        System.out.println("Enter note:");
        String note = sc.nextLine();

        for (int i = 0; i < size; i++) {
            if (data[i] == null) {
                data[i] = date;
                notes[i] = note;
                System.out.println("Note saved.");
                return;
            }
        }
        System.out.println("Diary full. Remove a note first.");
    }

    public static void removeNote() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter date and time of note to delete (yyyy MM dd HH mm ss):");
        try {
            LocalDateTime date = LocalDateTime.of(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
            sc.nextLine();
            for (int i = 0; i < size; i++) {
                if (date.equals(data[i])) {
                    data[i] = null;
                    notes[i] = null;
                    System.out.println("Note deleted.");
                    return;
                }
            }
            System.out.println("Note not found.");
        } catch (DateTimeException e) {
            System.out.println("Incorrect date and time.");
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Incorrect input data.");
            sc.nextLine();
        }
    }

    public static void reviewNotes() {
        for (int i = 0; i < size; i++) {
            if (data[i] != null) {
                System.out.println(data[i].format(formatter));
                System.out.println(notes[i]);
                System.out.println();
            }
        }
    }

    public static void checkSaveFile(String filepath){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Do you want to save the diary? (yes/no)");
            String save = sc.nextLine();
            if (save.equals("yes")) {
                System.out.println("Enter file path to save:");
                String savepath = sc.nextLine();
                utils.FileUtils.saveFile(savepath, data, notes, formatter);
                break;
            } else if (save.equals("no")) {
                break;
            } else {
                System.out.println("Please type 'yes' or 'no'.");
            }
        }
        System.out.println("Goodbye!");
    }
}
