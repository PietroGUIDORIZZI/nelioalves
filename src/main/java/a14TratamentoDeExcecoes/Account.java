package a14TratamentoDeExcecoes;

public class Account {

    private Integer number;
    private String holder;
    private Double balance;
    private Double withdrawLimit;

    public Account() {
    }

    public Account(Integer number,
            String holder,
            Double balance,
            Double withdrawLimit) {
        this.number = number;
        this.holder = holder;
        this.balance = balance;
        this.withdrawLimit = withdrawLimit;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            return;
        }
        throw new IllegalArgumentException("Deposit amount must be positive!");
    }

    public void withdraw(double amount) throws UnavailableOperationException {
        boolean enough = amount <= this.balance;
        boolean enoughLimit = amount <= this.withdrawLimit;
        if (!enough && !enoughLimit) {
            throw new UnavailableOperationException(
                    "Not enough balance(" + this.balance + ") and not enough limit("
                            + this.withdrawLimit + ") ");
        } else if (!enough) {
            throw new UnavailableOperationException("Not enough balance");
        } else if (!enoughLimit) {
            throw new UnavailableOperationException("Not enough limit");
        } else {
            this.balance -= amount;
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "number=" + number +
                ", holder='" + holder + '\'' +
                ", balance=" + balance +
                ", withdrawLimit=" + withdrawLimit +
                '}';
    }
}


