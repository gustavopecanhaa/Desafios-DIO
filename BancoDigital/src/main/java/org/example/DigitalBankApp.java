package org.example;

import org.example.model.Account;
import org.example.model.Customer;
import org.example.service.BankService;

public class DigitalBankApp {
    public static void main(String[] args) {
        BankService bank = new BankService();

        // Criação de clientes
        Customer alice = bank.createCustomer("Alice Silva", "alice@email.com");
        Customer bob = bank.createCustomer("Bob Souza", "bob@email.com");

        // Abertura de contas
        Account aliceChecking = bank.openAccount(alice, "corrente");
        Account aliceSavings = bank.openAccount(alice, "poupança");
        Account bobChecking = bank.openAccount(bob, "corrente");

        // Operações bancárias
        aliceChecking.deposit(1500.00);
        aliceChecking.transfer(aliceSavings, 300.00);
        aliceChecking.withdraw(200.00);
        bobChecking.deposit(800.00);
        bank.transfer(aliceSavings, bobChecking, 100.00);

        // Aplicação de taxas mensais
        bank.applyMonthlyFees();

        // Exibição de informações
        alice.printAccountsSummary();
        bob.printAccountsSummary();

        aliceChecking.printStatement();
        bobChecking.printStatement();
    }
}