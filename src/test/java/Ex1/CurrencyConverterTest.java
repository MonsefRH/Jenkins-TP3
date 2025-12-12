package Ex1;

import org.example.project.Ex1.CurrencyConverter;
import org.example.project.Ex1.ExchangeRate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CurrencyConverterTest {
    private ExchangeRate exchangeRate;
    private CurrencyConverter converter;

    @BeforeEach
    void setUp() {
        exchangeRate = new ExchangeRate();
        converter = new CurrencyConverter(exchangeRate);
    }

    @Test
    void testConvertMadToEurZero() {
        assertEquals(0.0, converter.convertMadToEur(0), 0.001);
    }

    @Test
    void testConvertMadToEurNegative() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertMadToEur(-1));
    }

    @Test
    void testConvertMadToEurPositive() {
        assertEquals(0.09, converter.convertMadToEur(1), 0.001);
    }

    @Test
    void testConvertEurToMadZero() {
        assertEquals(0.0, converter.convertEurToMad(0), 0.001);
    }

    @Test
    void testConvertEurToMadNegative() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertEurToMad(-1));
    }

    @Test
    void testConvertEurToMadPositive() {
        assertEquals(11.11, converter.convertEurToMad(1), 0.001);
    }

    @Test
    void testConvertMadToEurRandom() {
        double amount = Math.random() * 100;
        double expected = amount * 0.09;
        assertEquals(expected, converter.convertMadToEur(amount), 0.001);
    }

    @Test
    void testConvertEurToMadRandom() {
        double amount = Math.random() * 100;
        double expected = amount * 11.11;
        assertEquals(expected, converter.convertEurToMad(amount), 0.001);
    }
}
