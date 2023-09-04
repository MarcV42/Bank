import java.math.BigDecimal;

public record Client(
        String firstName,
        String lastName,
        BigDecimal customerNumber) {
}
