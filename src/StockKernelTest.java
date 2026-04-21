import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class StockKernelTest {

    @Test
    public void testConstructorAndGetters() {
        Stock sTest = new Stock1L("AAPL", 15000);
        
        assertEquals("AAPL", sTest.tickerSymbol());
        assertEquals(1, sTest.historySize());
        assertEquals(15000, sTest.currentPrice());
        assertEquals(15000, sTest.priceAt(0));
    }

    @Test
    public void testUpdatePrice() {
        Stock sTest = new Stock1L("GOOG", 280000);
        Stock sExpected = new Stock1L("GOOG", 280000);
        
        // Manually build expected state
        sExpected.updatePrice(285000);
        
        // Call method on test object
        sTest.updatePrice(285000);

        assertEquals(2, sTest.historySize());
        assertEquals(285000, sTest.currentPrice());
        assertEquals(285000, sTest.priceAt(1));
        
        // Verify state remains exactly as expected
        assertEquals(sExpected, sTest); 
    }

    @Test
    public void testClear() {
        Stock sTest = new Stock1L("AMZN", 13000);
        sTest.updatePrice(13500);
        
        Stock sExpected = new Stock1L("TEMP", 1); // Default clear state
        
        sTest.clear();
        
        assertEquals("TEMP", sTest.tickerSymbol());
        assertEquals(1, sTest.historySize());
        assertEquals(1, sTest.currentPrice());
        assertEquals(sExpected, sTest);
    }
}