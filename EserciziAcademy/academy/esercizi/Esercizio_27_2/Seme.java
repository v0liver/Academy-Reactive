package academy.esercizi.Esercizio_27_2;

public enum Seme {
    CUORI("Cuori"),
    FIORI("Fiori"),
    QUADRI("Quadri"),
    PICCHE("Picche");

    private final String nome;

    Seme(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
