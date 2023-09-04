import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BankService {
    private Map<String, BigDecimal> accounts;
    private Map<String, String> accountOwners;
    private int nextAccountNumber = 1;

    public BankService() {
        accounts = new HashMap<>();
        accountOwners = new HashMap<>();
    }

    // Methode zum Eröffnen eines Kontos für einen Kunden und Rückgabe der neuen Kontonummer
    public String openAccount(String customerName) {
        String accountNumber = generateAccountNumber();
        accounts.put(accountNumber, BigDecimal.ZERO);
        accountOwners.put(accountNumber, customerName);
        return accountNumber;
    }

    // Methode zum Einzahlen von Geld auf ein Konto
    public boolean deposit(String accountNumber, BigDecimal amount) {
        if (!accounts.containsKey(accountNumber) || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return false; // Konto nicht gefunden oder ungültiger Betrag
        }

        BigDecimal currentBalance = accounts.get(accountNumber);
        BigDecimal newBalance = currentBalance.add(amount);
        accounts.put(accountNumber, newBalance);
        return true;
    }

    // Methode zum Abheben von Geld von einem Konto
    public boolean withdraw(String accountNumber, BigDecimal amount) {
        if (!accounts.containsKey(accountNumber) || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return false; // Konto nicht gefunden oder ungültiger Betrag
        }

        BigDecimal currentBalance = accounts.get(accountNumber);
        if (currentBalance.compareTo(amount) < 0) {
            return false; // Nicht genug Guthaben auf dem Konto
        }

        BigDecimal newBalance = currentBalance.subtract(amount);
        accounts.put(accountNumber, newBalance);
        return true;
    }

    // Methode zum Durchführen einer Überweisung von einer Kontonummer zu einer anderen
    public boolean transfer(String sourceAccountNumber, String targetAccountNumber, BigDecimal amount) {
        if (!accounts.containsKey(sourceAccountNumber) || !accounts.containsKey(targetAccountNumber)) {
            return false; // Konten nicht gefunden
        }

        BigDecimal sourceBalance = accounts.get(sourceAccountNumber);
        if (sourceBalance.compareTo(amount) < 0) {
            return false; // Nicht genug Guthaben auf dem Quellkonto
        }

        BigDecimal targetBalance = accounts.get(targetAccountNumber);
        sourceBalance = sourceBalance.subtract(amount);
        targetBalance = targetBalance.add(amount);

        accounts.put(sourceAccountNumber, sourceBalance);
        accounts.put(targetAccountNumber, targetBalance);
        return true;
    }

    // Private Methode zur Generierung einer neuen Kontonummer
    private String generateAccountNumber() {
        String accountNumber = "ACC" + nextAccountNumber;
        nextAccountNumber++;
        return accountNumber;
    }
    // Methode zum Abrufen des Kontostands
    public BigDecimal getBalance(String accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            return accounts.get(accountNumber);
        } else {
            return BigDecimal.ZERO; // Konto nicht gefunden, Rückgabe von 0 als Standardwert
        }
    }

}
