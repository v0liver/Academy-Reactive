package academy.esercizi;

public class Persona implements Comparable<Persona>{
    String nome;
    int eta;
    public Persona(String nome, int eta) {
        super();
        this.nome = nome;
        this.eta = eta;
    }
    @Override
    public int compareTo(Persona o) {
        return this.nome.compareTo(o.nome);
    }
}
