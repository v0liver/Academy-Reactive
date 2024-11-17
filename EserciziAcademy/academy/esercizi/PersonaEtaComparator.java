package academy.esercizi;

import java.util.Comparator;

public class PersonaEtaComparator implements Comparator<Persona> {
    @Override
    public int compare(Persona o1, Persona o2) {
        if (o1.eta>o2.eta) {
            return 1;
        } else if (o1.eta<o2.eta) {
            return -1;
        }
        return 0;
    }
}
