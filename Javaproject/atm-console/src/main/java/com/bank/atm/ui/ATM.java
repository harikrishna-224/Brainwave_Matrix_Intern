// ATM Console Application - Model for Account
package com.bank.atm.ui;

import java.util.Scanner;

import com.bank.atm.model.Account;
import com.bank.atm.repo.Bank;

public class ATM {
    private final Bank bank;
    private Account current;
    private final Scanner in = new Scanner(System.in);

    public ATM(Bank bank) {
        this.bank = bank;
    }

    public void start() {
        splash();
        while (true) {
            if (current == null) {
                loginFlow();
            } else {
                accountMenu();
            }
        }
    }

    private void splash() {
        System.out.println("=== JAVA ATM v1.0 ===");
    }

    private void loginFlow() {
        System.out.println("""
            Please choose an option:
            1. New User
            2. Existing User
            0. Quit
        """);
        System.out.print("Option: ");
        String option = in.nextLine().trim();

        switch (option) {
            case "1" -> createAccount(); // New User
            case "2" -> existingUserLogin(); // Existing User
            case "0" -> quit(); // Quit
            default -> {
                System.out.println("Invalid option\n");
                return;
            }
        }
    }

    private void existingUserLogin() {
        System.out.print("Account number: ");
        String acc = in.nextLine().trim();
        if ("0".equals(acc)) quit();

        var maybeAcc = bank.find(acc);
        if (maybeAcc.isEmpty()) {
            System.out.println("Account not found\n");
            return;
        }
        
        System.out.print("PIN: ");
        String pin = in.nextLine().trim();
        if (maybeAcc.get().verifyPin(pin)) {
            current = maybeAcc.get();
            System.out.printf("Welcome, %s!\n", current.getHolder());
        } else {
            System.out.println("Wrong PIN\n");
        }
    }

    private void accountMenu() {
        try {
            System.out.println("""
                1. Balance
                2. Deposit
                3. Withdraw
                4. Transfer
                5. Change PIN
                6. Logout
                0. Exit
            """);
            System.out.print("Option: ");
            int opt = Integer.parseInt(in.nextLine().trim());

            switch (opt) {
                case 1 -> balance();
                case 2 -> deposit();
                case 3 -> withdraw();
                case 4 -> transfer();
                case 5 -> changePin();
                case 6 -> { current = null; System.out.println("Logged out\n"); }
                case 0 -> quit();
                default -> System.out.println("Invalid option");
            }
        } catch (NumberFormatException e) {
            System.out.println("Enter a number");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();
    }

    private void balance() {
        System.out.printf("Balance: $%.2f\n", current.getBalance());
    }

    private void deposit() {
        System.out.print("Deposit amount: ");
        double amount = Double.parseDouble(in.nextLine().trim());
        current.deposit(amount);
        System.out.printf("Deposited $%.2f\n", amount);
    }

    private void withdraw() {
        System.out.print("Withdraw amount: ");
        double amount = Double.parseDouble(in.nextLine().trim());
        current.withdraw(amount);
        System.out.printf("Withdrew $%.2f\n", amount);
    }

    private void transfer() {
        System.out.print("Destination account: ");
        String dest = in.nextLine().trim();
        var destAcc = bank.find(dest);
        if (destAcc.isEmpty()) {
            System.out.println("Destination account not found");
            return;
        }
        System.out.print("Amount: ");
        double amount = Double.parseDouble(in.nextLine().trim());
        current.withdraw(amount);
        destAcc.get().deposit(amount);
        System.out.printf("Transferred $%.2f\n", amount);
    }

    private void changePin() {
        System.out.print("Current PIN: ");
        String oldPin = in.nextLine().trim();
        System.out.print("New PIN: ");
        String newPin = in.nextLine().trim();
        current.changePin(oldPin, newPin);
        System.out.println("PIN changed");
    }

    private void createAccount() {
        System.out.print("Enter new account number: ");
        String number = in.nextLine().trim();
        System.out.print("Enter new PIN: ");
        String pin = in.nextLine().trim();
        System.out.print("Enter your name: ");
        String holder = in.nextLine().trim();
        System.out.print("Enter opening balance: ");
        double balance = Double.parseDouble(in.nextLine().trim());
        bank.addAccount(number, pin, holder, balance);
        System.out.println("Account created successfully! Please log in.\n");
    }

    private void quit() {
        System.out.println("Thank you for using the ATM. Goodbye!");
        System.exit(0);
    }
}