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

        return d1 == d2 && d2 == d3 && d3 == d4 && angoliSonoRetti(punto1,punto2,punto3,punto4);
    }

    public boolean angoliSonoRetti(int [] punto1, int[] punto2, int[] punto3, int[] punto4) {
        //Todo
        int vettore1x;//Vettore dal punto 0 al punto 1
        int vettore1y;
        int vettore2x;//vettore dal punto 1 al punto 2
        int vettore2y;
        int vettore3x;//Vettore dal punto 2 al punto 3
        int vettore3y;
        int vettore4x;//Vettore dal punto 3 al punto 4
        int vettore4y;

        return true; // Prodotto scalare tra i vettori per verificare l'ortogonalità
    }

    public boolean isARettangolo(int[] punto1, int[] punto2, int[] punto3, int[] punto4){
        //Todo
        return true;
    }


    public boolean isARombo(int[] punto1, int[] punto2, int[] punto3, int[] punto4){
       //todo
        return true;
    }
}
