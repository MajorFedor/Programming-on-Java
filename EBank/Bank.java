package EBank;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import EBank.BankAccount.Account.BankAccount;
import EBank.Observer.BankEvent;
import EBank.Observer.StatisticEvent;
import EBank.Persons.Client;
import EBank.Persons.Employee;

public class Bank implements Serializable {

    private static Bank instance;

    private final String name;
    private final List<Client> clients;
    private final List<Employee> employees;
    private final Map<Long, BankAccount> bankAccounts;
    private final StatisticEvent statistic;

    private Bank(String name) {
        this.name = name;
        this.clients = new ArrayList<>();
        this.employees = new ArrayList<>();
        this.bankAccounts = new HashMap<>();
        this.statistic = new StatisticEvent();
    }

    public static Bank getInstance(String name) {
        if (instance == null) {
            instance = new Bank(name);
        }
        return instance;
    }

    public void addClient(Client cl) {
        clients.add(cl);
    }

    public void addEmployee(Employee emp) {
        employees.add(emp);
    }

    public void addBankAccounts(BankAccount ba) {
        subscribeStatistics(ba);
        bankAccounts.put(ba.getId(), ba);
    }

    public void removeClient(Client cl) {
        clients.remove(cl);
    }

    public void removeEmployee(Employee emp) {
        employees.remove(emp);
    }

    public void removeBankAccount(BankAccount ba) {
        bankAccounts.remove(ba.getId(), ba);
    }

    public String getName() {
        return name;
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Map<Long, BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public StatisticEvent getStatistic() {
        return statistic;
    }

    public void saveToFile(String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(this);
            System.out.println("Bank saved to " + filePath);
        } catch (IOException e) {
            System.out.println("Save error: " + e.getMessage());
        }
    }

    public static Bank loadFromFile(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            Bank loaded = (Bank) ois.readObject();
            instance = loaded;
            IdRestore.restore(loaded);
            loaded.resubscribeAllAccounts();
            return loaded;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Load error: " + e.getMessage());
            return null;
        }
    }

    private void resubscribeAllAccounts() {
        for (BankAccount ba : bankAccounts.values()) {
            subscribeStatistics(ba);
        }
    }

    private void subscribeStatistics(BankAccount ba) {
        for (BankEvent event : BankEvent.values()) {
            ba.events.clearListeners(event);
            ba.events.subscribe(event, statistic);
        }
    }

    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                ", clients=" + clients.size() +
                ", accounts=" + bankAccounts.size() +
                ", employees=" + employees.size() +
                "}";
    }
}