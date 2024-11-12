package academy.esercizi.Esercizio_27_2;

public enum Valore {
    ASSO("Asso", 1),
    DUE("2", 2),
    TRE("3", 3),
    QUATTRO("4", 4),
    CINQUE("5", 5),
    SEI("6", 6),
    SETTE("7", 7),
    OTTO("8", 8),
    NOVE("9", 9),
    DIECI("10", 10),
    FANTE("Fante", 11),
    REGINA("Regina", 12),
    RE("Re", 13);

    private final String nome;
    private final int valoreNumerico;

    Valore(String nome, int valoreNumerico) {
        this.nome = nome;
        this.valoreNumerico = valoreNumerico;
    }

    public String getNome() {
        return nome;
    }

    public int getValoreNumerico() {
        return valoreNumerico;
    }
}
