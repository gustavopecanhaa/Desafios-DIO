package org.example.service;

import org.example.model.Account;
import org.example.model.CheckingAccount;
import org.example.model.Customer;
import org.example.model.SavingsAccount;

import java.util.ArrayList;
import java.util.List;

public class BankService {
    private List<Customer> customers = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();
    private int accountCounter = 0;

    public Customer createCustomer(String name, String email) {
        Customer customer = new Customer(name, email);
        customers.add(customer);
        return customer;
    }

    public Account openAccount(Customer customer, String accountType) {
        Account newAccount = switch(accountType.toLowerCase()) {
            case "corrente" -> new CheckingAccount(customer, 500.0);
            case "poupança" -> new SavingsAccount(customer);
            default -> throw new IllegalArgumentException("Tipo de conta inválido");
        };
        newAccount.setNumber(++accountCounter);
        customer.addAccount(newAccount);
        accounts.add(newAccount);
        return newAccount;
    }

    public void applyMonthlyFees() {
        accounts.forEach(acc -> {
            if(acc instanceof CheckingAccount) {
                acc.applyMonthlyFee();
            } else if(acc instanceof SavingsAccount) {
                acc.applyMonthlyFee();
            }
        });
    }

    public void transfer(Account source, Account target, double amount) {
        if(source == null || target == null) {
            throw new IllegalArgumentException("Contas inválidas");
        }
        source.transfer(target, amount);
    }

    // Métodos auxiliares
    public List<Customer> getCustomers() { return customers; }
    public List<Account> getAccounts() { return accounts; }
}