package academy.esercizi.Esercizio_34_1;

public interface Raddoppiabile {
    final String DESCRIZIONE_CLASSE = "Questa interfaccia raddoppia il valore di un oggetto Raddoppiabile";

    void raddoppia();

     default boolean isDimezzabile() {
        throw new RuntimeException("NON SONO ANCORA STATO IMPLEMENTATO");
    }

    static String descrivi(){
        return DESCRIZIONE_CLASSE;
    }

}
