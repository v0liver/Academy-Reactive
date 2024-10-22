package academy.esercizi;

public class Esercizio_6_1 {

        public static void main(String[] args) {

            System.out.printf("%-4s %-6s %-6s %-6s%n", "x", "x^2", "x^3", "x^4");

            for (int x = 1; x <= 10; x++) {
                int x2 = x * x;
                int x3 = x2 * x;
                int x4 = x3 * x;
                System.out.printf("%-6d %-6d %-6d %-6d%n", x, x2, x3, x4);
            }
        }
    }


