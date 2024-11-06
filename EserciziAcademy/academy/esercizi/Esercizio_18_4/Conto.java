package academy.esercizi.Esercizio_18_4;

import java.math.BigDecimal;

public class Conto {
   private BigDecimal balance = BigDecimal.valueOf(0D);

    public Conto(BigDecimal balance) {
        this.balance = balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
