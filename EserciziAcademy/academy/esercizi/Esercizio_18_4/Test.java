package academy.esercizi.Esercizio_18_4;

import java.math.BigDecimal;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        BigDecimal importoContoCorrente;
        BigDecimal importoContoRisparmio;
        String operazione;
        String qualeConto;
        Scanner scan = new Scanner(System.in);


        do {
            System.out.print("Inserisci il saldo del conto corrente:");
            importoContoCorrente = new BigDecimal(String.valueOf(scan.nextLine()));


        } while (importoContoCorrente.compareTo(BigDecimal.ZERO) < 0);

        Conto contocOrrente = new Conto(importoContoCorrente);



        do {
            System.out.print("Inserisci il saldo del conto di risparmio:");
            importoContoRisparmio = new BigDecimal(scan.nextLine());


        } while (importoContoRisparmio.compareTo(BigDecimal.ZERO) < 0);

        Conto contoRisparmio = new Conto(importoContoRisparmio);

        System.out.print("Che operazione vuoi eseguire? (deposit,withdrawal,transfer):");
        operazione = scan.nextLine().toLowerCase();

        System.out.printf("Su quale conto vuoi eseguire l'operazione di %s?(checking/savings)", operazione);
        qualeConto = scan.nextLine().toLowerCase();

        switch (operazione) {
            case "deposit":
                if ("checking".equalsIgnoreCase(qualeConto)) {

                    System.out.println("Quanto vuoi depositare?");
                    BigDecimal deposito = new BigDecimal(scan.nextLine());
                    contocOrrente.setBalance(contocOrrente.getBalance().add(deposito));
                    System.out.println("Saldo conto corrente: " + contocOrrente.getBalance() + "$");

                } else if ("savings".equalsIgnoreCase(qualeConto))

                    System.out.println("Non puoi depositare direttamente nel conto di risparmio");

                else System.out.println("Conto non riconosciuto");
                break;


            case "withdrawal":
                if ("checking".equalsIgnoreCase(qualeConto)) {
                    BigDecimal prelievo;

                    do {
                        System.out.print("Quanto vuoi prelevare?");
                        prelievo = new BigDecimal(scan.nextLine());

                        if (contocOrrente.getBalance().subtract(prelievo).compareTo(BigDecimal.ZERO) >= 0) {

                            contocOrrente.setBalance(contocOrrente.getBalance().subtract(prelievo));
                            System.out.println("Saldo conto corrente: " + contocOrrente.getBalance() + "$");

                        } else System.out.printf("Non puoi prelevare più di %s %n12", contocOrrente.getBalance());

                    } while (contocOrrente.getBalance().subtract(prelievo).compareTo(BigDecimal.ZERO) < 0);

                } else if ("savings".equalsIgnoreCase(qualeConto)) {

                    System.out.println("Non puoi prelevare direttamente nel conto di risparmio");
                }
                else System.out.println("Conto non riconosciuto");

                break;

            case "transfer":
                BigDecimal bonifico;
                if ("checking".equalsIgnoreCase(qualeConto)) {


                    do {
                        System.out.print("Quanto vuoi bonificare?");
                        bonifico = new BigDecimal(scan.nextLine());

                        if (contocOrrente.getBalance().subtract(bonifico).compareTo(BigDecimal.ZERO) >= 0) {

                            contoRisparmio.setBalance(contoRisparmio.getBalance().add(bonifico));
                            contocOrrente.setBalance(contocOrrente.getBalance().subtract(bonifico));
                            System.out.println("Saldo conto corrente: " + contocOrrente.getBalance() + "$");
                            System.out.println("Saldo conto risparmio: " + contoRisparmio.getBalance() + "$");

                        } else System.out.printf("Non puoi bonificare più di %s %n12", contocOrrente.getBalance());

                    } while (contocOrrente.getBalance().subtract(bonifico).compareTo(BigDecimal.ZERO) < 0);

                } else if ("savings".equalsIgnoreCase(qualeConto)) {
                    do {
                        System.out.print("Quanto vuoi bonificare?");
                        bonifico = new BigDecimal(scan.nextLine());
                        if (contoRisparmio.getBalance().subtract(bonifico).compareTo(BigDecimal.ZERO) >= 0) {

                            contocOrrente.setBalance(contocOrrente.getBalance().add(bonifico));
                            contoRisparmio.setBalance(contoRisparmio.getBalance().subtract(bonifico));
                            System.out.println("Saldo conto corrente: " + contocOrrente.getBalance() + "$");
                            System.out.println("Saldo conto risparmio: " + contoRisparmio.getBalance() + "$");

                        } else System.out.printf("Non puoi bonificare più di %s %n", contocOrrente.getBalance());
                    } while (contocOrrente.getBalance().subtract(bonifico).compareTo(BigDecimal.ZERO) < 0);


                } else System.out.println("Conto non riconosciuto.");
                break;
            default:
                System.out.println("Operazione non riconosciuta.");
                break;
        }
    }
}
