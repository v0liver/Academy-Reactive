package academy.esercizi;

    public class Esercizio_9_3_2 {

        public static void main(String[] args) {
            int[] punto1 = {0, 10};
            int[] punto2 = {10, 10};
            int[] punto3 = {10, 0};
            int[] punto4 = {0, 0};
            boolean noFigura = false;
            academy.esercizi.Esercizio_9_3 test = new academy.esercizi.Esercizio_9_3();


            noFigura = test.stampaNomeFigura(punto1, punto2, punto3, punto4);
            test.stampaFigura(punto1, punto2, punto3, punto4,noFigura);

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

            return d1 == d2 && d2 == d3 && d3 == d4 && calcolaDiagonale == calcolaDiagonale2;
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

            if (isParalleloAllAsseX(punto1, punto2) && isParalleloAllAsseX(punto3, punto4)) {
                if (isParalleloAllAsseY(punto4, punto1)) {
                    return !isParalleloAllAsseY(punto2, punto3);

                } else if (isParalleloAllAsseY(punto2, punto3)) {

                    return !isParalleloAllAsseY(punto1, punto4);
                }

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

                figura = "rRettangolo";
            } else if (isARombo(punto1, punto2, punto3, punto4)) {
                figura = "Rombo";

            } else if (isATrapezioRettangolo(punto1, punto2, punto3, punto4)) {
                figura = "Trapezio Rettangolo";
            }
            System.out.println(figura);
            if (figura == "Nessuna forma prevista") {
                return true;
            }
            return false;
        }

        void stampaFigura(int[] punto1, int[] punto2, int[] punto3, int[] punto4, boolean noFigura) {
            int maxAscisse=0;
            int maxOrdinate=0;


            if (punto1[0]>maxAscisse){
                maxAscisse= punto1[0];
            }if (punto1[1]>maxOrdinate){
                maxOrdinate= punto1[1];
            }

            if (punto2[0]>maxAscisse){
                maxAscisse=punto2[0];
            }if (punto2[1]>maxOrdinate){
                maxOrdinate=punto2[1];
            }
            if (punto3[0]>maxAscisse){
                maxAscisse= punto3[0];
            }if (punto3[1]>maxOrdinate){
                maxOrdinate= punto3[1];
            }
            if (punto4[0]>maxAscisse){
                maxAscisse= punto4[0];
            }if (punto4[1]>maxOrdinate){
                maxOrdinate= punto4[1];
            }
                



            char[][] areaDisegno=new char[maxAscisse][maxOrdinate];

            for (int i = 0; i < maxAscisse; i++) {
                for (int j = 0; j < maxOrdinate; j++) {
                    areaDisegno[i][j]='O';
                }

            }


                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        System.out.print(areaDisegno[i][j]);

                    }
                    System.out.println();
                }
            }


        }



