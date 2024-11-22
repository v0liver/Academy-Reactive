package academy.esercizi;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class Esercizio_37_7 {
    public static void main(String[] args) throws IOException {
        Esercizio_37_7 prova = new Esercizio_37_7();
        prova.letturaFileTxt();
        System.out.println("-------------------------------------------------------------------------------");
        prova.letturaFileJava();
    }

    public void letturaFileTxt() throws IOException {

        try (BufferedReader fileTxt = new BufferedReader(new FileReader(new File("EserciziAcademy\\academy\\esercizi\\File\\File_Esercizio_37_7.txt")))) {
            List<String> listaFile = new ArrayList<>();

            while (fileTxt.ready()) {
                String rigaFile = fileTxt.readLine();

                String[] rigaFileSplit = rigaFile.split("[\\s.,]");
                Collections.addAll(listaFile, rigaFileSplit);
                listaFile.sort(Comparator.naturalOrder());
                mappa(listaFile).forEach((s, i) -> System.out.println("La parola " + "\"" + s + "\"" + " è ripetuta " + i + " volte."));

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, Long> mappa(List<String> listaFile) {
        Map<String, Long> mappa = new TreeMap<>();
        mappa = listaFile.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        //mappa = listaFile.stream().collect(Collectors.toMap(s -> s, pippo->10L));
        return mappa;
    }


    public void letturaFileJava() throws FileNotFoundException {
        String url = "C:\\Users\\V.Oliveri-cons\\Documents\\rootGit\\gitAcademy\\EserciziAcademy\\academy\\esercizi\\Esercizio_22_1.java";
        int contatoreRiga = 1;
        try (Scanner scanner = new Scanner(new File(url))) {
            while (scanner.hasNextLine()) {
                String riga = scanner.nextLine();
                String[] rigaSplittata = riga.split("[^A-Za-z0-9_]+");
                for (int i = 0; i < rigaSplittata.length; i++) {
                    if (!rigaSplittata[i].isEmpty()) {
                        System.out.printf("L'identificatore %-20s è presente sulla riga %-3d", rigaSplittata[i], contatoreRiga);
                        System.out.println();
                    }
                }
                contatoreRiga++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}


