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

        System.out.print("Che operazione vuoi eseguire? (D-deposit,W-withdrawal,T-transfer):");
        operazione = scan.nextLine().toLowerCase();


        switch (operazione) {
            case "d":
                do {

                    System.out.print("Su quale conto vuoi eseguire l'operazione di deposito?(C-checking/S-savings)");
                    qualeConto = scan.nextLine().toLowerCase();
                    if ("c".equalsIgnoreCase(qualeConto)) {

                        System.out.println("Quanto vuoi depositare?");
                        BigDecimal deposito = new BigDecimal(scan.nextLine());
                        contocOrrente.setBalance(contocOrrente.getBalance().add(deposito));
                        System.out.println("Saldo conto corrente: " + contocOrrente.getBalance() + "$");

                    } else if ("s".equalsIgnoreCase(qualeConto))

                        System.out.println("Non puoi depositare direttamente nel conto di risparmio");

                    else System.out.println("Conto non riconosciuto");

                }while ("s".equalsIgnoreCase(qualeConto));
                break;

            case "w":


                do {
                    System.out.print("Su quale conto vuoi eseguire l'operazione di prelievo?(C-checking/S-savings)");
                    qualeConto = scan.nextLine().toLowerCase();

                if ("c".equalsIgnoreCase(qualeConto)) {
                    BigDecimal prelievo;

                    do {
                        System.out.print("Quanto vuoi prelevare?");
                        prelievo = new BigDecimal(scan.nextLine());

                        if (contocOrrente.getBalance().subtract(prelievo).compareTo(BigDecimal.ZERO) >= 0) {

                            contocOrrente.setBalance(contocOrrente.getBalance().subtract(prelievo));
                            System.out.println("Saldo conto corrente: " + contocOrrente.getBalance() + "$");

                        } else System.out.printf("Non puoi prelevare più di %s %n12", contocOrrente.getBalance());

                    } while (contocOrrente.getBalance().subtract(prelievo).compareTo(BigDecimal.ZERO) < 0);

                } else if ("s".equalsIgnoreCase(qualeConto)) {

                    System.out.println("Non puoi prelevare direttamente nel conto di risparmio");
                }
                else System.out.println("Conto non riconosciuto");


                }while ("s".equalsIgnoreCase(qualeConto));
                break;

            case "t":
                System.out.print("Su quale conto vuoi eseguire l'operazione di trasferimento?(C-checking/S-savings)");
                qualeConto = scan.nextLine().toLowerCase();
                BigDecimal bonifico;
                if ("c".equalsIgnoreCase(qualeConto)) {


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

                } else if ("s".equalsIgnoreCase(qualeConto)) {
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
