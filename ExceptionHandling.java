import java.util.InputMismatchException;
import java.util.Scanner;
import static javax.swing.JOptionPane.*;
class OutOfRangeException extends Exception {
    public OutOfRangeException(String message) {
        super (message);
    }
}
public class ExceptionHandling {
public static void main(String[] args) {
double kontostand=0.00;
Scanner eingabe =new Scanner(System.in);

String eOderA=showInputDialog("E oder A"); //Abfrage, ob Einzahlung oder Auszahlung gewollt ist

try {
System.out.println("Welcher Betrag soll überwiesen weden?");


double uberweisungBetrag=eingabe.nextDouble();
if(uberweisungBetrag<0){
    throw new OutOfRangeException ("Negative Beträge können nicht eingezahlt werden.");
}
} catch(InputMismatchException e) {
    System.out.println("Fehler: Bitte geben Sie eine Zahl ein.");  



//IF Funktion zur Berechnung des Kontostands 
kontostand=kontostand+uberweisungBetrag;
System.out.println("Eingabe:"+uberweisungBetrag);



System.out.println(kontostand);
System.out.println(eOderA);
    

}
    
}}