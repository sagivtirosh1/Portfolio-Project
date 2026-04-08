
/**
 * Enhanced interface for the Stock component.
 */
public interface Stock extends StockKernel {

    /**
     * Reports the difference between the current and previous price.
     * 
     * @requires historySize() > 1
     * @return change in cents
     * @ensures priceChange = currentPrice() - priceAt(historySize() - 2)
     */
    int priceChange();

    /**
     * Calculates the average price over the last n points.
     * 
     * @param interval
     *            number of recent points to average
     * @requires 1 <= interval <= |this.history|
     * @return moving average
     * @ensures movingAverage = (sum i: [|this.history| - interval,
     *          |this.history|) (this.history[i])) / interval and this = #this
     */
    double movingAverage(int interval);

    /**
     * Returns the highest price recorded.
     * 
     * @return maximum recorded price in cents
     * @ensures maxPrice = max({i: integer where 0 <= i <
     *          |this.history|}(this.history[i])) and this = #this
     */
    int maxPrice();

    /**
     * Reports if current price is above historical average.
     * 
     * @return true if bullish, false otherwise
     * @ensures isBullish = (last(this.history) > (sum i: [0, |this.history|)
     *          (this.history[i])) / |this.history|) and this = #this
     */
    boolean isBullish();
}