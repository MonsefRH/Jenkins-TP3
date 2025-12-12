package Ex1;

import org.example.project.Ex1.ExchangeRate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExchangeRateTest {
    private ExchangeRate exchangeRate;

    @BeforeEach
    void setUp() {
        exchangeRate = new ExchangeRate();
    }

    @Test
    void testGetMadToEurRate() {
        assertEquals(0.09, exchangeRate.getMadToEurRate(), 0.001);
    }

    @Test
    void testGetEurToMadRate() {
        assertEquals(11.11, exchangeRate.getEurToMadRate(), 0.001);
    }

    @Test
    void testSetMadToEurRateValid() {
        exchangeRate.setMadToEurRate(0.1);
        assertEquals(0.1, exchangeRate.getMadToEurRate(), 0.001);
        assertEquals(10.0, exchangeRate.getEurToMadRate(), 0.001);
    }

    @Test
    void testSetMadToEurRateNegative() {
        assertThrows(IllegalArgumentException.class, () -> exchangeRate.setMadToEurRate(-0.1));
    }

    @Test
    void testSetMadToEurRateZero() {
        assertThrows(IllegalArgumentException.class, () -> exchangeRate.setMadToEurRate(0));
    }

    @Test
    void testSetEurToMadRateValid() {
        exchangeRate.setEurToMadRate(10.0);
        assertEquals(10.0, exchangeRate.getEurToMadRate(), 0.001);
        assertEquals(0.1, exchangeRate.getMadToEurRate(), 0.001);
    }

    @Test
    void testSetEurToMadRateNegative() {
        assertThrows(IllegalArgumentException.class, () -> exchangeRate.setEurToMadRate(-10.0));
    }
}
