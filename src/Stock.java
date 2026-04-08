import java.util.ArrayList;
import java.util.List;

/**
 * Proof of concept for the Stock representation component.
 * This class demonstrates the core logic for price tracking and analytics
 * using a simplified, single-class structure.
 */
public class Stock {

    // --- REPRESENTATION ---
    
    private final String ticker;
    private final List<Integer> history; // Prices stored in cents
    private int currentPrice;

    /**
     * Constructor to initialize the stock.
     * @param ticker the stock symbol (e.g., "ROBT")
     * @param initialPrice the starting price in cents
     */
    public Stock(String ticker, int initialPrice) {
        this.ticker = ticker;
        this.history = new ArrayList<>();
        this.history.add(initialPrice);
        this.currentPrice = initialPrice;
    }

    // --- KERNEL-STYLE METHODS ---

    /**
     * Adds a new price point and updates the current state.
     */
    public void updatePrice(int price) {
        this.history.add(price);
        this.currentPrice = price;
    }

    /**
     * Returns the most recent price.
     */
    public int currentPrice() {
        return this.currentPrice;
    }

    /**
     * Returns the price at a specific step in time.
     */
    public int priceAt(int timeStep) {
        if (timeStep < 0 || timeStep >= this.history.size()) {
            throw new IndexOutOfBoundsException("Invalid time step.");
        }
        return this.history.get(timeStep);
    }

    // --- SECONDARY-STYLE METHODS ---

    /**
     * Calculates the highest price found in the history.
     * Example of deriving data from the representation.
     */
    public int maxPrice() {
        int max = this.history.get(0);
        for (int price : this.history) {
            if (price > max) {
                max = price;
            }
        }
        return max;
    }

    /**
     * Returns true if the current price is higher than the historical average.
     */
    public boolean isBullish() {
        double sum = 0;
        for (int price : this.history) {
            sum += price;
        }
        double average = sum / this.history.size();
        return (double) this.currentPrice > average;
    }

    // --- MAIN METHOD FOR DEMONSTRATION ---

    public static void main(String[] args) {
        // Initialize the component for our Robot project
        Stock robotStock = new Stock("ROBT", 5000); // Starting at $50.00
        System.out.println("Ticker: " + robotStock.ticker);
        System.out.println("Initial Price: " + robotStock.currentPrice() + " cents");

        // Simulate market movement
        robotStock.updatePrice(5200);
        robotStock.updatePrice(4800);
        robotStock.updatePrice(6100); // Recent spike

        System.out.println("--- Market Update ---");
        System.out.println("Current Price: " + robotStock.currentPrice() + " cents");
        System.out.println("Highest Recorded Price: " + robotStock.maxPrice() + " cents");
        System.out.println("Is the stock currently bullish? " + robotStock.isBullish());
        
        // Accessing historical data
        System.out.println("Price at time step 1: " + robotStock.priceAt(1) + " cents");
    }
}