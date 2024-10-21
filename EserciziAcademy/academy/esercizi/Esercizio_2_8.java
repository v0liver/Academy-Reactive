package academy.esercizi;

public class Esercizio_2_8 {
    public static void main(String[] args) {
        int numeroLanci= 1000;
        java.util.Random r = new java.util.Random();
        int[] tabella = new int[6];

        //lancio il dado e popolo l'array tabella
        for (int i = 0; i < numeroLanci; i++) {
            int lancioDado=r.nextInt(6)+1;
            tabella[lancioDado-1]=tabella[lancioDado-1]+1;
        }

        for (int i = 0; i < tabella.length; i++) {
            System.out.println((i+1)+ " " + tabella[i]);
        }


    }
}
