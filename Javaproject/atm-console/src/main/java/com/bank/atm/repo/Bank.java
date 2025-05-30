// ATM Console Application - Model for Accou
package com.bank.atm.repo;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.bank.atm.model.Account;

public class Bank {
    private final Map<String, Account> accounts = new HashMap<>();
    private static final String FILE_NAME = "accounts.dat";

    public Bank() {
        loadAccounts();
    }

    public Optional<Account> find(String number) {
        return Optional.ofNullable(accounts.get(number));
    }

    public void add(Account a) {
        accounts.put(a.getNumber(), a);
        saveAccounts();
    }

    public void addAccount(String number, String pin, String holder, double balance) {
        Account newAccount = new Account(number, pin, holder, balance);
        accounts.put(number, newAccount);
        saveAccounts();
    }

    private void saveAccounts() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(accounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAccounts() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Map<String, Account> savedAccounts = (Map<String, Account>) in.readObject();
            accounts.putAll(savedAccounts);
        } catch (IOException | ClassNotFoundException e) {
            // If there is an exception, it might mean the file is not present or is corrupt.
            // Log the error.
            System.out.println("No existing accounts found. Starting fresh.");
        }
    }

    public static Bank demoBank() {
        Bank b = new Bank();
        // Preload with demo accounts, optional
        b.add(new Account("1001", "1234", "Alice", 1200));
        b.add(new Account("1002", "5678", "Bob", 800));
        b.add(new Account("1003", "4321", "Carol", 150));
        return b;
    }
}

