package EBank.Persons;


import java.util.Objects;

import EBank.BankAccount.Account.BankAccount;
import EBank.Exceptions.InvalidSalaryException;

public class Employee extends Person{

    private long employeeId;
    private String position;
    private int salary;
    private static long nextEmployeeId = 1;

    public Employee(String fullName, String passportNumber, String mobileNumber, int age, String dateOfBirth, String residentialAddress, String position, int salary){
        super(fullName, passportNumber, mobileNumber, age, dateOfBirth, residentialAddress);
        this.position = position;
        if(salary <= 0){
            throw new InvalidSalaryException("Salary must be positive.");
        }
        this.salary = salary;
        this.employeeId = nextEmployeeId++;
    }

    public void blockAccount(BankAccount account) {
        account.block();
    }

    public void unblockAccount(BankAccount account) {
        account.unblock();
    }


    public long getEmployeeId(){
        return employeeId;
    }

    public String getPosition(){
        return position;
    }

    public int getSalary(){
        return salary;
    }

    public void setPosition(String pos){
        if(pos.isBlank()){
            throw new IllegalArgumentException("Incorrect position");
        }
        this.position = pos;
    }

    public void setSalary(int salary){
        if(salary <= 0){
            throw new InvalidSalaryException("Salary must be positive.");
        };
        this.salary = salary;
    }

    @Override
    public String toString(){
        return "Employee{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", position='" + position + '\'' +
                ", employeeNumber=" + employeeId +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee emp = (Employee) o;
        return employeeId == emp.getEmployeeId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId);
    }

}
