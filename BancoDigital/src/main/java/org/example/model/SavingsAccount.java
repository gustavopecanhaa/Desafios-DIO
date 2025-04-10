package org.example.model;

public class SavingsAccount extends Account {
    private static final double MONTHLY_INTEREST = 0.02;

    public SavingsAccount(Customer holder) {
        super(holder);
    }

    @Override
    protected boolean canWithdraw(double amount) {
        return balance >= amount;
    }

    @Override
    protected void executeWithdraw(double amount) {
        balance -= amount;
    }

    @Override
    public void applyMonthlyFee() {
        double interest = balance * MONTHLY_INTEREST;
        balance += interest;
        super.transactions.add(String.format("Juros Mensais: +R$%.2f", interest));
    }
}
