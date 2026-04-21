public class StockMarketAnalyzer {
    /**
     * 
     * Purpose: Direct Client Usage and Secondary Method Demonstration
     Description: This file serves as a straightforward "driver" or client program. 
     It demonstrates how a developer might use the Stock component directly 
     within a main method to track a single equity over a period of time.
     */
    public static void main(String[] args) {
        // Initialize a new stock
        Stock techStock = new Stock1L("NVDA", 40000); // $400.00
        
        // Simulate a week of closing prices
        techStock.updatePrice(41000); // Tuesday
        techStock.updatePrice(40500); // Wednesday
        techStock.updatePrice(42500); // Thursday
        techStock.updatePrice(43000); // Friday
        
        System.out.println("=== End of Week Report for " + techStock.tickerSymbol() + " ===");
        
        // Demonstrating Kernel Methods
        System.out.println("\n-- Basic Info --");
        System.out.println("Days Tracked: " + techStock.historySize());
        System.out.println("Initial Opening Price (Day 0): $" + (techStock.priceAt(0) / 100.0));
        System.out.println("Current Closing Price: $" + (techStock.currentPrice() / 100.0));
        
        // Demonstrating Secondary Methods
        System.out.println("\n-- Analytics --");
        System.out.println("Daily Change (from yesterday): $" + (techStock.priceChange() / 100.0));
        System.out.println("Highest Price This Week: $" + (techStock.maxPrice() / 100.0));
        System.out.println("3-Day Moving Avg: $" + (techStock.movingAverage(3) / 100.0));
        
        if (techStock.isBullish()) {
            System.out.println("Market Sentiment: BULLISH 🚀");
        } else {
            System.out.println("Market Sentiment: BEARISH 📉");
        }

        // Demonstrating Object Methods (toString)
        System.out.println();
        System.out.println("-- System Data Dump --");
        System.out.println("Raw History: " + techStock.toString());

        // Demonstrating Standard Methods (transferFrom and clear)
        System.out.println();
        System.out.println("-- End of Year Archiving --");
        
        // Create an empty temporary stock
        Stock archive = new Stock1L("TEMP", 1); 
        
        // Transfer data to the archive
        archive.transferFrom(techStock);
        System.out.println("Archive successfully populated: " + archive.toString());
        System.out.println("Original stock post-transfer: " + techStock.toString());

        // Clear the archive to free memory
        archive.clear();
        System.out.println("Archive cleared: " + archive.toString());
    }
}