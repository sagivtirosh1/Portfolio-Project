public abstract class StockSecondary implements Stock {

    /*
     * Secondary Methods ------------------------------------------------------
     */

    @Override
    public int priceChange() {
        // Check the precondition: historySize() > 1
        assert this.historySize() > 1 : "Violation of: historySize() > 1";
        
        return this.currentPrice() - this.priceAt(this.historySize() - 2);
    }

    @Override
    public double movingAverage(int interval) {
        // Check the precondition: 1 <= interval <= historySize()
        assert interval >= 1 : "Violation of: 1 <= interval";
        assert interval <= this.historySize() : "Violation of: interval <= historySize()";
        
        double sum = 0.0;
        int size = this.historySize();
        
        // Loop through the last 'interval' elements
        for (int i = size - interval; i < size; i++) {
            sum += this.priceAt(i);
        }
        
        return sum / interval;
    }

    @Override
    public int maxPrice() {
        // A stock should always have at least one price based on kernel init
        assert this.historySize() > 0 : "Violation of: historySize() > 0";
        
        int max = this.priceAt(0);
        for (int i = 1; i < this.historySize(); i++) {
            int current = this.priceAt(i);
            if (current > max) {
                max = current;
            }
        }
        return max;
    }

    @Override
    public boolean isBullish() {
        // Compare current price against the all-time historical average
        int size = this.historySize();
        assert size > 0 : "Violation of: historySize() > 0";
        
        return this.currentPrice() > this.movingAverage(size);
    }

    /*
     * Object Methods ---------------------------------------------------------
     */

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("<").append(this.tickerSymbol()).append(", [");
        
        int size = this.historySize();
        for (int i = 0; i < size; i++) {
            result.append(this.priceAt(i));
            if (i < size - 1) {
                result.append(", ");
            }
        }
        result.append("]>");
        
        return result.toString();
    }

    @Override
    public boolean equals(Object obj) {
        // Check identity
        if (obj == this) {
            return true;
        }
        // Check null
        if (obj == null) {
            return false;
        }
        // Check if the object implements the same interface
        if (!(obj instanceof Stock)) {
            return false;
        }
        
        Stock other = (Stock) obj;
        
        // Check ticker symbol
        if (!this.tickerSymbol().equals(other.tickerSymbol())) {
            return false;
        }
        
        // Check history size
        if (this.historySize() != other.historySize()) {
            return false;
        }
        
        // Check all historical data points
        for (int i = 0; i < this.historySize(); i++) {
            if (this.priceAt(i) != other.priceAt(i)) {
                return false;
            }
        }
        
        return true;
    }

    @Override
    public int hashCode() {
        // Start with the ticker symbol's hash code
        int result = this.tickerSymbol().hashCode();
        
        // Fold in the history prices
        for (int i = 0; i < this.historySize(); i++) {
            result = 31 * result + this.priceAt(i);
        }
        
        return result;
    }
}