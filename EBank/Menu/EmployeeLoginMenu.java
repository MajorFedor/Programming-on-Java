package EBank.Menu;

import java.util.Scanner;
import EBank.Bank;
import EBank.Persons.Employee;

public class EmployeeLoginMenu {

    private final Scanner sc = new Scanner(System.in);
    private final Bank bank;

    public EmployeeLoginMenu(Bank bank) {
        this.bank = bank;
    }

    public void start() {
        System.out.println("\n--- Employee Login ---");
        System.out.print("Enter your employee ID: ");

        if (!sc.hasNextLong()) {
            System.out.println("Invalid input.");
            sc.nextLine();
            return;
        }

        long empId = sc.nextLong();
        sc.nextLine();

        Employee employee = null;
        for (Employee e : bank.getEmployees()) {
            if (e.getEmployeeId() == empId) {
                employee = e;
                break;
            }
        }

        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        System.out.println("Welcome, " + employee.getName() + " (" + employee.getPosition() + ")!");
        new EmployeeMenu(bank, employee).start();
    }
}