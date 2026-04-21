import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class StockSecondaryTest {

    @Test
    public void testPriceChangePositive() {
        Stock sTest = new Stock1L("MSFT", 30000);
        sTest.updatePrice(30500);
        
        Stock sExpected = new Stock1L("MSFT", 30000);
        sExpected.updatePrice(30500);

        assertEquals(500, sTest.priceChange());
        assertEquals(sExpected, sTest); // Verify state is unaltered
    }

    @Test
    public void testMovingAverage() {
        Stock sTest = new Stock1L("NVDA", 40000);
        sTest.updatePrice(41000);
        sTest.updatePrice(42000);
        
        Stock sExpected = new Stock1L("NVDA", 40000);
        sExpected.updatePrice(41000);
        sExpected.updatePrice(42000);

        // Average of last 2: (41000 + 42000) / 2 = 41500
        assertEquals(41500.0, sTest.movingAverage(2), 0.001);
        assertEquals(sExpected, sTest);
    }

    @Test
    public void testMaxPrice() {
        Stock sTest = new Stock1L("TSLA", 25000);
        sTest.updatePrice(27000);
        sTest.updatePrice(24000);
        
        Stock sExpected = new Stock1L("TSLA", 25000);
        sExpected.updatePrice(27000);
        sExpected.updatePrice(24000);

        assertEquals(27000, sTest.maxPrice());
        assertEquals(sExpected, sTest);
    }

    @Test
    public void testIsBullishTrue() {
        Stock sTest = new Stock1L("META", 30000);
        sTest.updatePrice(29000);
        sTest.updatePrice(32000); // Current is 32000. Avg is 30333.33
        
        Stock sExpected = new Stock1L("META", 30000);
        sExpected.updatePrice(29000);
        sExpected.updatePrice(32000);

        assertTrue(sTest.isBullish());
        assertEquals(sExpected, sTest);
    }
}