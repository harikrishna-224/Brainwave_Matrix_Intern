// ATM Console Application - Model for Account
package com.bank.atm.model;
import java.io.Serializable;
public class Account implements Serializable {
    private static final long serialVersionUID = 1L; // Recommended for Serializable classes

    private final String number;
    private String pin;
    private double balance;
    private final String holder;

    public Account(String number, String pin, String holder, double opening) {
        this.number = number;
        this.pin = pin;
        this.holder = holder;
        this.balance = opening;
    }

    public boolean verifyPin(String attempt) {
        return pin.equals(attempt);
    }

    public void changePin(String oldPin, String newPin) {
        if (!verifyPin(oldPin)) throw new IllegalArgumentException("Incorrect current PIN");
        if (newPin.length() < 4) throw new IllegalArgumentException("PIN must have at least 4 digits");
        pin = newPin;
    }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be > 0");
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0 || amount > balance) throw new IllegalArgumentException("Invalid amount");
        balance -= amount;
    }

    public double getBalance() { return balance; }
    public String getHolder() { return holder; }
    public String getNumber() { return number; }
}