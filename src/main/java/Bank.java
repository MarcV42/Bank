import java.math.BigDecimal;

public class Bank {
    public static void main(String[] args) {
        BankService bankService = new BankService();

        // Eröffne Konten für Kunden
        String account1 = bankService.openAccount("Alice");
        String account2 = bankService.openAccount("Bob");

        // Einzahlen auf Konten
        boolean depositSuccess = bankService.deposit(account1, new BigDecimal("1000.00"));
        if (depositSuccess) {
            System.out.println("Einzahlung auf Konto 1 erfolgreich.");
        }

        // Abheben von Konten
        boolean withdrawSuccess = bankService.withdraw(account2, new BigDecimal("500.00"));
        if (withdrawSuccess) {
            System.out.println("Abhebung von Konto 2 erfolgreich.");
        }

        // Durchführen einer Überweisung
        boolean transferSuccess = bankService.transfer(account1, account2, new BigDecimal("200.00"));
        if (transferSuccess) {
            System.out.println("Überweisung von Konto 1 nach Konto 2 erfolgreich.");
        }

        // Ausgabe des Kontostands
        BigDecimal balance1 = bankService.getBalance(account1);
        BigDecimal balance2 = bankService.getBalance(account2);

        System.out.println("Kontostand von Konto 1: " + balance1);
        System.out.println("Kontostand von Konto 2: " + balance2);

    }
}
