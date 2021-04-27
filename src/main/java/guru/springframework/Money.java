package guru.springframework;

import java.util.Objects;

@SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
public class Money implements Expression {
    protected final int amount;
    protected final String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money dollar(int amount) {
        return new Money(amount, "USD");
    }


    public static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    public String currency() {
        return this.currency;
    }


    public boolean equals(Object object) {
        Money money = (Money) object;
        return amount == money.amount
                && Objects.equals(this.currency, money.currency);
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }

    @Override
    public Expression times(int multiplier) {
        return new Money(amount * multiplier, this.currency);
    }

    @Override
    public Expression plus(Expression added) {
        return new Sum(this, added);
    }

    @Override
    public Money reduce(Bank bank, String to) {
        int rate = bank.rate(this.currency, to);
        return new Money(amount / rate, to);
    }
}
