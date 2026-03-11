
/**
 * Enhanced interface for the Stock component.
 */
public interface Stock extends StockKernel {

    /**
     * Reports the difference between the current and previous price.
     * @requires historySize() > 1
     * @return change in cents
     * @ensures priceChange = currentPrice() - priceAt(historySize() - 2)
     */
    int priceChange();

    /**
     * Calculates the average price over the last n points.
     * @param interval number of recent points to average
     * @requires 1 <= interval <= historySize()
     * @return moving average
     */
    double movingAverage(int interval);

    /**
     * Returns the highest price recorded.
     * @return maximum recorded price in cents
     */
    int maxPrice();

    /**
     * Reports if current price is above historical average.
     * @return true if bullish, false otherwise
     */
    boolean isBullish();
}