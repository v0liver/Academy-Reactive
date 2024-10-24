package academy.esercizi;

public class Esercizio_9_3 {

    public static void main(String[] args) {
        int[] punto1 = {1, 1};
        int[] punto2 = {-1, 1};
        int[] punto3 = {-1, -1};
        int[] punto4 = {1, -1};

        System.out.println(isAQuadrato(punto1, punto2, punto3, punto4));


    }


    static double distanzaTraDuePuntiAlQuadrato(int[] punto1, int[] punto2) {
        //return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);//calcolo la distanza tra due coordinate al quadrato
        return (punto2[0] - punto1[0]) * (punto2[0] - punto1[0]) + (punto2[1] - punto1[1]) * (punto2[1] - punto1[1]);
    }

    public static boolean isAQuadrato(int[] punto1, int[] punto2, int[] punto3, int[] punto4) {
        double d1 = distanzaTraDuePuntiAlQuadrato(punto1, punto2);
        double d2 = distanzaTraDuePuntiAlQuadrato(punto1, punto2);
        double d3 = distanzaTraDuePuntiAlQuadrato(punto1, punto2);
        double d4 = distanzaTraDuePuntiAlQuadrato(punto1, punto2);

        return d1 == d2 && d2 == d3 && d3 == d4 && angoliSonoRetti(punto1, punto2, punto3, punto4);
    }

    public static boolean angoliSonoRetti(int[] punto1, int[] punto2, int[] punto3, int[] punto4) {
        //Todo
        int vettore1x = punto2[0] - punto1[0];//Vettore dal punto 1 al punto 2 (x1-x0)
        int vettore1y = punto2[1] - punto1[1];//(y1-y0)
        int vettore2x = punto3[0] - punto2[0];//vettore dal punto 2 al punto 3
        int vettore2y = punto3[1] - punto2[1];
        int vettore3x = punto4[0] - punto3[0];//Vettore dal punto 3 al punto 4
        int vettore3y = punto4[1] - punto3[1];
        int vettore4x = punto1[0] - punto4[0];//Vettore dal punto 4 al punto 1
        int vettore4y = punto1[1] - punto4[1];


        return (vettore1x * vettore2x + vettore1y + vettore2y == 0) && (vettore2x * vettore3x + vettore2y * vettore3y == 0) && (vettore3x * vettore4x + vettore3y * vettore4y == 0) && (vettore4x * vettore1x + vettore4y * vettore1y == 0); // Prodotto scalare tra i vettori per verificare l'ortogonalità
    }

    public boolean isARettangolo(int[] punto1, int[] punto2, int[] punto3, int[] punto4) {
        //Todo
        return true;
    }


    public boolean isARombo(int[] punto1, int[] punto2, int[] punto3, int[] punto4) {
        //todo
        return true;
    }
}
