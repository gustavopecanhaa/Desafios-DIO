package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Customer {
    private final String id;
    private String name;
    private String email;
    private List<Account> accounts = new ArrayList<>();

    public Customer(String name, String email) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.name = name;
        this.email = email;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void printAccountsSummary() {
        System.out.println("\n=== Contas de " + name + " ===");
        accounts.forEach(acc -> System.out.println(
                String.format("Conta %d: R$%.2f (%s)",
                        acc.getNumber(),
                        acc.getBalance(),
                        acc.getClass().getSimpleName())
        ));
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<Account> getAccounts() { return accounts; }
}
