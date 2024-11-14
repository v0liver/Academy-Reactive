package academy.esercizi.Esercizio_29_1_4;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class CSVReader {
    public String path;


    public CSVReader(String path) {
        this.path = path;
    }


    public int numberOfRows() {


        return listaFile().size();
    }

    private List<String> listaFile() {
        List<String> contenutoFile;
        try {
            contenutoFile = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return contenutoFile;
    }


    public int numberOfFields(int riga) {

        String contentoRiga = listaFile().get(riga);
        String[] colonne = contentoRiga.split("[,;]");
        return colonne.length;
    }

    public String field(int riga, int colonna) {
        String contenutoRiga = listaFile().get(riga);
        String[] splitRiga = contenutoRiga.split("[,;]");

        return splitRiga[colonna];
    }

}
