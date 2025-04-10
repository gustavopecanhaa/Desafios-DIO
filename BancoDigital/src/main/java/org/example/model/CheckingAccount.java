package org.example.model;

public class CheckingAccount extends Account implements FeeSchedule {
    private double overdraftLimit;
    private static final double MONTHLY_FEE = 12.90;
    private static final double TRANSACTION_FEE = 0.50;

    public CheckingAccount(Customer holder, double overdraftLimit) {
        super(holder);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    protected boolean canWithdraw(double amount) {
        return (balance + overdraftLimit) >= (amount + TRANSACTION_FEE);
    }

    @Override
    protected void executeWithdraw(double amount) {
        balance -= (amount + TRANSACTION_FEE);
    }

    @Override
    public void applyMonthlyFee() {
        balance -= MONTHLY_FEE;
        super.transactions.add(String.format("Taxa Mensal: -R$%.2f", MONTHLY_FEE));
    }

    // Implementação da interface
    @Override
    public double getMonthlyFee() { return MONTHLY_FEE; }
    @Override
    public double getTransactionFee() { return TRANSACTION_FEE; }
}
