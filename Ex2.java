import java.util.InputMismatchException;
import java.util.Scanner;

class OutOfRangeException extends Exception {
    public OutOfRangeException(String message) {
        super(message);
    }
}

public class Ex2 {
    public static void main(String[] args) {
        Scanner eingabe = new Scanner(System.in);
        double kontostand = 1000.00;

        while (true) {
            System.out.println("Funktion Auswählen:\n 1 für Einzahlung \n 2 für Auszahlung \n 3 für Überweisung \n 4 für Kontostand abfragen \n 5 zum Beenden");
            int funktionAuswahl = eingabe.nextInt();
            if (funktionAuswahl == 5) {
                System.out.println("Auf Wiedersehen");
                break;
            }

            try {
                switch (funktionAuswahl) {
                    case 1:
                        System.out.println("Welcher Betrag soll eingezahlt werden?");
                        double betragEinzahlung = eingabe.nextDouble();
                        if (betragEinzahlung < 0) throw new OutOfRangeException("Negative Beträge können nicht eingezahlt werden.");
                        kontostand += betragEinzahlung;
                        break;
                    case 2:
                        System.out.println("Welcher Betrag soll ausgezahlt werden?");
                        double betragAuszahlung = eingabe.nextDouble();
                        if (betragAuszahlung < 0 || betragAuszahlung > kontostand) throw new OutOfRangeException("Negative Beträge können nicht ausgezahlt werden oder Betrag überschreitet den Kontostand.");
                        kontostand -= betragAuszahlung;
                        break;
                    case 3:
                        System.out.println("Kontonummer des Empfängers angeben:");
                        int ueberweisungKontonummer = eingabe.nextInt();
                        System.out.println("Überweisungsbetrag angeben:");
                        double betragUeberweisung = eingabe.nextDouble();
                        if (betragUeberweisung < 0) throw new OutOfRangeException("Negative Beträge können nicht überwiesen werden.");
                        kontostand -= betragUeberweisung;
                        System.out.println("Es wurden " + betragUeberweisung + " an die Kontonummer " + ueberweisungKontonummer + " überwiesen.");
                        break;
                    case 4:
                        System.out.println("Ihr Kontostand beträgt " + kontostand);
                        break;
                    default:
                        System.out.println("Ungültige Auswahl.");
                        break;
                }
            } catch (OutOfRangeException | InputMismatchException e) {
                System.out.println(e.getMessage());
                if (e instanceof InputMismatchException) {
                    eingabe.next(); // Verwirft die ungültige Eingabe, um eine Endlosschleife zu vermeiden
                }
            }
        }
        eingabe.close(); // Schließt den Scanner am Ende der Nutzung
    }
}
