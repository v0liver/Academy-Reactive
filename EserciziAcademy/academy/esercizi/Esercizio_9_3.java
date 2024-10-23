package academy.esercizi;

public class Esercizio_9_3 {

    int[] punto1 = new int[2];
    int[] punto2 = new int[2];
    int[] punto3 = new int[2];
    int[] punto4 = new int[2];

    static double distanzaTraDuePuntiAlQuadrato(int[] punto1, int[] punto2) {
        //return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);//calcolo la distanza tra due coordinate al quadrato
        return (punto2[0] - punto1[0]) * (punto2[0] - punto1[0]) + (punto2[1] - punto1[1]) * (punto2[1] - punto1[1]);
    }

    public boolean isAQuadrato(int[] punto1, int[] punto2, int[] punto3, int[] punto4) {
        double d1 = distanzaTraDuePuntiAlQuadrato(punto1, punto2);
        double d2 = distanzaTraDuePuntiAlQuadrato(punto1, punto2);
        double d3 = distanzaTraDuePuntiAlQuadrato(punto1, punto2);
        double d4 = distanzaTraDuePuntiAlQuadrato(punto1, punto2);

        return d1 == d2 && d2 == d3 && d3 == d4;
    }
}
