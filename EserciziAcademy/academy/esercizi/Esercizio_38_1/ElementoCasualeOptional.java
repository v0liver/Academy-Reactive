package academy.esercizi.Esercizio_38_1;

import java.util.Optional;
import java.util.Random;

public class ElementoCasualeOptional {
    Optional<Integer> valore;

    public ElementoCasualeOptional() {
     this(setValore());
    }

    public ElementoCasualeOptional(Optional<Integer> valore) {
        this.valore = valore;
    }

    public static Optional<Integer> setValore(){
        Optional<Integer> tmp;
        boolean isEmpty = new Random().nextBoolean();
        if (isEmpty){
            tmp=Optional.empty();
        }else tmp=  Optional.of(new Random().nextInt(20001 - 100) + 100);
        return tmp;
    }
}
