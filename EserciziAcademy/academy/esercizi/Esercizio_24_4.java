package academy.esercizi;

public class Esercizio_24_4 {
    final int maxAsterischiOrizzontali = 40;
    final int maxAsterischiVerticale = 20;

    public static void main(String[] args) {
        String[] nomi = {"Vito", "Giuseppee", "Laura"};
        int[] valori = {2, 3, 20};
        int valoreMassimo = valoreMassimo(valori);
        Esercizio_24_4 test = new Esercizio_24_4();
        System.out.println("------------Orizzontale------------");
        test.stampaOrizzontale(nomi, valori, valoreMassimo);
        System.out.println();
        System.out.println("------------Verticale------------");

        test.stampaVerticale(nomi, valori, valoreMassimo);

    }

    public void stampaVerticale(String[] nomi, int[] valori, int valoreMassimo) {

        int[] lunghezzaAsterischi = new int[valori.length];


        for (int i = 0; i < valori.length; i++) {
            lunghezzaAsterischi[i] = (int) ((double) valori[i] / valoreMassimo * maxAsterischiVerticale);
        }


        for (int i = maxAsterischiVerticale; i > 0; i--) {
            for (int j = 0; j < valori.length; j++) {
                if (lunghezzaAsterischi[j] > i) {
                    stampaSpazi(nomi[j],true);
                    System.out.print("*");
                    stampaSpazi(nomi[j],false);
                } else {
                    stampaSpazi(nomi[j],true);
                    System.out.print(" ");
                    stampaSpazi(nomi[j],false);
                }
            }
            System.out.println();
        }


        for (int i = 0; i < nomi.length; i++) {
            System.out.print(nomi[i] + " ");
        }
        System.out.println();
    }


    public void stampaOrizzontale(String[] nomi, int[] valori, int valoreMassimo) {

        for (int i = 0; i < nomi.length; i++) {

            int lunghezzaAsterischi = valoreMassimo * valori[i] / maxAsterischiOrizzontali;
            System.out.printf("%-10s ", nomi[i]);
            if (valori[i] != valoreMassimo) {
                for (int j = 0; j < lunghezzaAsterischi; j++) {
                    System.out.print("*");

                }
                System.out.println();
            } else {
                for (int j = 0; j < maxAsterischiOrizzontali; j++) {
                    System.out.print("*");

                }
                System.out.println();

            }
        }

    }


    public static int valoreMassimo(int[] valori) {
        int valoreMassimo = 0;

        for (int i = 0; i < valori.length; i++) {
            if (valori[i] > valoreMassimo) {
                valoreMassimo = valori[i];

            }

        }
        return valoreMassimo;
    }

    private void stampaSpazi(String nome, boolean aggiungiSpazioDispari) {
        for (int i = 0; i < nome.length() / 2; i++) {
            System.out.print(" ");
        }
        if (nome.length() % 2 != 0 && aggiungiSpazioDispari) {
            System.out.print(" ");
        }
    }

}
