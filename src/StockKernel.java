import components.standard.Standard;

/**
 * Kernel interface for the Stock component.
 *
 * @mathmodel type StockKernel is (
 * ticker: string,
 * history: string of integer
 * )
 * @initially <pre>
 * (String symbol, int initialPrice):
 * requires: symbol is not empty and initialPrice > 0
 * ensures: this = (symbol, <initialPrice>)
 * </pre>
 */
public interface StockKernel extends Standard<Stock> {

    /**
     * Adds a new price point to the history.
     * @param price the new price in cents
     * @updates this
     * @requires price > 0
     * @ensures this.history = #this.history * <price>
     */
    void updatePrice(int price);

    /**
     * Reports the most recent price.
     * @return the current price in cents
     * @ensures currentPrice = last(this.history)
     */
    int currentPrice();

    /**
     * Reports the price at a specific time step.
     * @param timeStep the index in history
     * @requires 0 <= timeStep < |this.history|
     * @return the price at the time step
     * @ensures priceAt = this.history[timeStep]
     */
    int priceAt(int timeStep);

    /**
     * Reports the number of recorded prices.
     * @return total entries in history
     * @ensures historySize = |this.history|
     */
    int historySize();

    /**
     * Reports the stock ticker symbol.
     * @return the ticker symbol
     * @ensures tickerSymbol = this.ticker
     */
    String tickerSymbol();
}