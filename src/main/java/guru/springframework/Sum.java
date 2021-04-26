package guru.springframework;

public class Sum implements Expression {
    Money augmend;
    Money addend;

    public Sum(Money augmend, Money addend) {
        this.augmend = augmend;
        this.addend = addend;
    }

    @Override
    public  Money reduce(Bank bank,String to){
        int amount=augmend.amount+addend.amount;
        return  new Money(amount,to);
    }
}
