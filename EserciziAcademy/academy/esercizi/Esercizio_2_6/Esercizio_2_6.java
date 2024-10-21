package academy.esercizi.Esercizio_2_6;

public class Esercizio_2_6 {
    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7,8,9,10};
        int posizioneDaEliminare=4;
        for (int i = posizioneDaEliminare; i < array.length-1; i++) {
            array[i]=array[i+1];
        }
        array[array.length-1]=0;

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
    }
            
            
}
