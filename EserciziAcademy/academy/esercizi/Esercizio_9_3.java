package academy.esercizi;

public class Esercizio_9_3 {

    public static void main(String[] args) {

        int[] punto1 = {3, 3};
        int[] punto2 = {5, 0};
        int[] punto3 = {5, 6};
        int[] punto4 = {7, 3};
//        int[] punto1 = {2, 3};
//        int[] punto2 = {2, 7};
//        int[] punto3 = {5, 3};
//        int[] punto4 = {5, 7};


        Esercizio_9_3 test = new Esercizio_9_3();


        test.stampaFigura(punto1, punto2, punto3, punto4);


    }


    static double distanzaTraDuePunti(int[] punto1, int[] punto2) {
        //return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);//calcolo la distanza tra due coordinate al quadrato
//            return ((punto2[0] - punto1[0]) * (punto2[0] - punto1[0])) + ((punto2[1] - punto1[1]) * (punto2[1] -
//         punto1[1]));

        return Math.sqrt(Math.pow(punto2[0] - punto1[0], 2) + Math.pow(punto2[1] - punto1[1], 2));


    }

    public void riconosciFigura(int[] punto1, int[] punto2, int[] punto3, int[] punto4) {
        double d1 = distanzaTraDuePunti(punto1, punto2);
        double d2 = distanzaTraDuePunti(punto2, punto4);
        double d3 = distanzaTraDuePunti(punto4, punto3);
        double d4 = distanzaTraDuePunti(punto3, punto1);

        boolean isParalleloAsseX = isParalleloAllAsseX(punto1, punto2);
        boolean isParalleloAsseY = isParalleloAllAsseY(punto1, punto2);

        double diagonale1 = distanzaTraDuePunti(punto1, punto4);
        double diagonale2 = distanzaTraDuePunti(punto2, punto3);


        String figura = "Nessuna forma prevista";
        System.out.print("La figura è: ");
        if (isAQuadrato(d1, d2, d3, d4, diagonale1, diagonale2, isParalleloAsseX, isParalleloAsseY)) {
            figura = "Quadrato";
        } else if (isARettangolo(d1, d2, d3, d4)) {

            figura = "Rettangolo";
        } else if (isARombo(d1, d2, d3, d4, diagonale1, diagonale2)) {
            figura = "Rombo";

        } else if (isATrapezioRettangolo(punto1, punto2, punto3, punto4)) {
            figura = "Trapezio Rettangolo";
        }
        System.out.println(figura);

    }


    public boolean isAQuadrato(double d1, double d2, double d3, double d4, double diagonale1, double diagonale2, boolean isParalleloAsseX, boolean isParalleloAsseY) {


        return d1 == d2 && d2 == d3 && d3 == d4 && diagonale1 == diagonale2 && (isParalleloAsseX || isParalleloAsseY);
    }


    public boolean isARettangolo(double d1, double d2, double d3, double d4) {

        return d1 == d3 && d2 == d4 && d1 != d2 && d3 != d4;
    }


    public boolean isARombo(double d1, double d2, double d3, double d4, double diagonale1, double diagonale2) {


        return d1 == d2 && d2 == d3 && d3 == d4 && diagonale1 != diagonale2;


    }


    public boolean isATrapezioRettangolo(int[] punto1, int[] punto2, int[] punto3, int[] punto4) {

        return (isParalleloAllAsseX(punto3, punto1) && isParalleloAllAsseY(punto1, punto2) && isParalleloAllAsseX(punto2, punto4) && !isParalleloAllAsseY(punto4, punto3)) || ((isParalleloAllAsseX(punto2, punto3) && isParalleloAllAsseY(punto3, punto4) && isParalleloAllAsseX(punto4, punto1) && !isParalleloAllAsseY(punto1, punto2)));
    }


    boolean isParalleloAllAsseX(int[] punto1, int[] punto2) {

        return punto1[1] == punto2[1];

    }

    boolean isParalleloAllAsseY(int[] punto1, int[] punto2) {

        return punto1[0] == punto2[0];
    }


    void stampaFigura(int[] punto1, int[] punto2, int[] punto3, int[] punto4) {
        // stampaNomeFigura(punto1, punto2, punto3, punto4);
        riconosciFigura(punto1, punto2, punto3, punto4);

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



