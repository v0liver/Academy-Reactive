package academy.esercizi;

public class Esercizio_9_3 {

    public static void main(String[] args) {

        int[] punto1 = {1, 1};
        int[] punto2 = {3, 4};
        int[] punto3 = {5, 4};
        int[] punto4 = {5, 1};


        Esercizio_9_3 test = new Esercizio_9_3();


        test.stampaFigura(punto1, punto2, punto3, punto4);


    }


    static double distanzaTraDuePuntiAlQuadrato(int[] punto1, int[] punto2) {
        //return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);//calcolo la distanza tra due coordinate al quadrato
        return (punto2[0] - punto1[0]) * (punto2[0] - punto1[0]) + (punto2[1] - punto1[1]) * (punto2[1] - punto1[1]);
    }

    public boolean isAQuadrato(int[] punto1, int[] punto2, int[] punto3, int[] punto4) {
        double d1 = distanzaTraDuePuntiAlQuadrato(punto1, punto2);
        double d2 = distanzaTraDuePuntiAlQuadrato(punto2, punto3);
        double d3 = distanzaTraDuePuntiAlQuadrato(punto3, punto4);
        double d4 = distanzaTraDuePuntiAlQuadrato(punto4, punto1);

        double calcolaDiagonale = distanzaTraDuePuntiAlQuadrato(punto1, punto3);
        double calcolaDiagonale2 = distanzaTraDuePuntiAlQuadrato(punto2, punto4);

        return d1 == d2 && d2 == d3 && d3 == d4 && calcolaDiagonale == calcolaDiagonale2 && isParalleloAllAsseX(punto1, punto2);
    }


    public boolean isARettangolo(int[] punto1, int[] punto2, int[] punto3, int[] punto4) {

        double d1 = distanzaTraDuePuntiAlQuadrato(punto1, punto2);
        double d2 = distanzaTraDuePuntiAlQuadrato(punto2, punto3);
        double d3 = distanzaTraDuePuntiAlQuadrato(punto3, punto4);
        double d4 = distanzaTraDuePuntiAlQuadrato(punto4, punto1);


        return d1 == d3 && d2 == d4 && d1 != d2 && d3 != d4;
    }


    public boolean isARombo(int[] punto1, int[] punto2, int[] punto3, int[] punto4) {

        double d1 = distanzaTraDuePuntiAlQuadrato(punto1, punto2);
        double d2 = distanzaTraDuePuntiAlQuadrato(punto2, punto3);
        double d3 = distanzaTraDuePuntiAlQuadrato(punto3, punto4);
        double d4 = distanzaTraDuePuntiAlQuadrato(punto4, punto1);

        double calcoloDiagonale1 = distanzaTraDuePuntiAlQuadrato(punto1, punto3);
        double calcoloDiagonale2 = distanzaTraDuePuntiAlQuadrato(punto2, punto4);

        return d1 == d2 && d2 == d3 && d3 == d4 && calcoloDiagonale1 != calcoloDiagonale2;


    }


    public boolean isATrapezioRettangolo(int[] punto1, int[] punto2, int[] punto3, int[] punto4) {

        if ((isParalleloAllAsseX(punto4, punto1) && isParalleloAllAsseY(punto1, punto2) && isParalleloAllAsseX(punto2, punto3) && !isParalleloAllAsseY(punto3, punto4)) || ((isParalleloAllAsseX(punto2, punto3) && isParalleloAllAsseY(punto3, punto4) && isParalleloAllAsseX(punto4, punto1) && !isParalleloAllAsseY(punto1, punto2)))) {

            return true;

        }

        return false;
    }


    boolean isParalleloAllAsseX(int[] punto1, int[] punto2) {

        return punto1[1] == punto2[1];

    }

    boolean isParalleloAllAsseY(int[] punto1, int[] punto2) {

        return punto1[0] == punto2[0];
    }


    boolean stampaNomeFigura(int[] punto1, int[] punto2, int[] punto3, int[] punto4) {
        String figura = "Nessuna forma prevista";
        System.out.print("La figura è: ");
        if (isAQuadrato(punto1, punto2, punto3, punto4)) {
            figura = "Quadrato";
        } else if (isARettangolo(punto1, punto2, punto3, punto4)) {

            figura = "Rettangolo";
        } else if (isARombo(punto1, punto2, punto3, punto4)) {
            figura = "Rombo";

        } else if (isATrapezioRettangolo(punto1, punto2, punto3, punto4)) {
            figura = "Trapezio Rettangolo";
        }
        System.out.println(figura);
        return figura.equals("Nessuna forma prevista");
    }

    void stampaFigura(int[] punto1, int[] punto2, int[] punto3, int[] punto4) {
        stampaNomeFigura(punto1, punto2, punto3, punto4);

        int maxAscisse = 0;
        int maxOrdinate = 0;

        // Calcola il massimo valore per le ascisse
        if (punto1[0] > maxAscisse) {
            maxAscisse = punto1[0];
        }
        if (punto2[0] > maxAscisse) {
            maxAscisse = punto2[0];
        }
        if (punto3[0] > maxAscisse) {
            maxAscisse = punto3[0];
        }
        if (punto4[0] > maxAscisse) {
            maxAscisse = punto4[0];
        }

        // Calcola il massimo valore per le ordinate
        if (punto1[1] > maxOrdinate) {
            maxOrdinate = punto1[1];
        }
        if (punto2[1] > maxOrdinate) {
            maxOrdinate = punto2[1];
        }
        if (punto3[1] > maxOrdinate) {
            maxOrdinate = punto3[1];
        }
        if (punto4[1] > maxOrdinate) {
            maxOrdinate = punto4[1];
        }

        char[][] areaDisegno = new char[maxOrdinate + 1][maxAscisse + 1];
        for (int i = 0; i <= maxOrdinate; i++) {
            for (int j = 0; j <= maxAscisse; j++) {
                areaDisegno[i][j] = 'O';
            }
        }

        areaDisegno[punto1[1]][punto1[0]] = 'X';
        areaDisegno[punto2[1]][punto2[0]] = 'X';
        areaDisegno[punto3[1]][punto3[0]] = 'X';
        areaDisegno[punto4[1]][punto4[0]] = 'X';

        // Stampa la matrice
        for (int i = maxOrdinate; i >= 0; i--) {
            for (int j = 0; j <= maxAscisse; j++) {
                System.out.printf("%3c", areaDisegno[i][j]);
            }
            System.out.println();
        }
    }


}



