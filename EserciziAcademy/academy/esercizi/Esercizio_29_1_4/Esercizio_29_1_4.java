package academy.esercizi.Esercizio_29_1_4;

public class Esercizio_29_1_4 {
    static int riga =2;
    static int colonna = 1;
    public static void main(String[] args)  {
        CSVReader csvReader = new CSVReader("C:\\Users\\V.Oliveri-cons\\Documents\\rootGit\\gitAcademy\\EserciziAcademy\\academy\\esercizi\\File\\File.csv");

        System.out.println("Il numero di righe del file è: " + csvReader.numberOfRows());
        System.out.println("Il numero di colonne nella riga "  + riga  + " è " + csvReader.numberOfFields(riga));
        System.out.printf("L'elemento a riga %d e colonna %d è %s ", riga, colonna, csvReader.field(riga,colonna));
    }
}
