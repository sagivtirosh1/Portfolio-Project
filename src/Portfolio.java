import java.util.ArrayList;
import java.util.List;

/**
 * A proof-of-concept component that aggregates multiple Stocks. Purpose: Object
 * Composition and Internal Representation Description: Unlike the analyzer
 * script, this file demonstrates how the Stock component can be used as the
 * underlying data structure for a larger, more complex component. The Portfolio
 * class acts as a "wrapper" that manages a collection of Stock objects.
 */
public class Portfolio {

    private List<Stock> holdings;

    public Portfolio() {
        this.holdings = new ArrayList<>();
    }

    /**
     * Adds a new stock to the portfolio.
     */
    public void acquireStock(String ticker, int initialPrice) {
        this.holdings.add(new Stock1L(ticker, initialPrice));
    }

    /**
     * Simulates a market day passing by appending new prices to all holdings.
     * 
     * @requires newPrices.length == holdings.size()
     */
    public void endMarketDay(int[] newPrices) {
        for (int i = 0; i < this.holdings.size(); i++) {
            this.holdings.get(i).updatePrice(newPrices[i]);
        }
    }

    /**
     * Calculates the total current value of the portfolio in cents.
     */
    public int getTotalNetWorth() {
        int total = 0;
        for (Stock s : this.holdings) {
            total += s.currentPrice();
        }
        return total;
    }

    /**
     * Finds and returns the ticker symbol of the best performing stock based on
     * its all-time peak price.
     */
    public String getStarPerformer() {
        if (this.holdings.isEmpty())
            return "NO HOLDINGS";

        Stock best = this.holdings.get(0);
        for (Stock s : this.holdings) {
            if (s.maxPrice() > best.maxPrice()) {
                best = s;
            }
        }
        return best.tickerSymbol();
    }
}