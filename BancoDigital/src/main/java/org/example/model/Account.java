package org.example.model;

import org.example.exception.InsufficientBalanceException;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    private final int agency = 1;
    private int number;
    private Customer holder;
    protected double balance;
    List<String> transactions = new ArrayList<>();

    public Account(Customer holder) {
        this.holder = holder;
    }

    public void deposit(double amount) {
        if(amount <= 0) throw new IllegalArgumentException("Valor inválido");
        balance += amount;
        transactions.add(String.format("Depósito: +R$%.2f", amount));
    }

    public void withdraw(double amount) {
        if(amount <= 0) throw new IllegalArgumentException("Valor inválido");
        if(!canWithdraw(amount)) throw new InsufficientBalanceException("Saldo insuficiente");
        executeWithdraw(amount);
        transactions.add(String.format("Saque: -R$%.2f", amount));
    }

    protected abstract boolean canWithdraw(double amount);
    protected abstract void executeWithdraw(double amount);

    public void transfer(Account destination, double amount) {
        withdraw(amount);
        destination.deposit(amount);
        transactions.add(String.format("Transferência para %d: -R$%.2f",
                destination.getNumber(), amount));
    }

    public abstract void applyMonthlyFee();

    public void printStatement() {
        System.out.println("\n=== Extrato da Conta " + number + " ===");
        System.out.println("Titular: " + holder.getName());
        transactions.forEach(t -> System.out.println("• " + t));
        System.out.println(String.format("Saldo Atual: R$%.2f", balance));
    }

    // Getters e Setters
    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }
    public Customer getHolder() { return holder; }
    public double getBalance() { return balance; }
}
