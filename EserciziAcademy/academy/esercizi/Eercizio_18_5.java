package academy.esercizi;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Eercizio_18_5 {
    public static void main(String[] args) {

        BigDecimal contoBancario = new BigDecimal("1824");
        BigDecimal interessi = new BigDecimal("0.69");



        contoBancario= contoBancario.add(contoBancario.multiply(interessi.divide(new BigDecimal("100"),
                new MathContext(4))));
        System.out.println(contoBancario);

    }


}
