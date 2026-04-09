import java.util.ArrayList;
import java.util.List;

/**
 * {@code Stock} represented as a {@code String} and a {@code List} of prices,
 * with implementations of primary methods.
 *
 * @convention <pre>
 * $this.ticker is not null and $this.ticker.length > 0 and
 * $this.history is not null and $this.history.size > 0 and
 * [every element in $this.history is > 0]
 * </pre>
 * @correspondence <pre>
 * this = ($this.ticker, [elements in $this.history])
 * </pre>
 */
public class Stock1L extends StockSecondary {

    /*
     * Private members --------------------------------------------------------
     */

    private String ticker;
    private List<Integer> history;

    /**
     * Creator of initial representation.
     */
    private void createNewRep(String symbol, int initialPrice) {
        this.ticker = symbol;
        this.history = new ArrayList<>();
        this.history.add(initialPrice);
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * Constructor from symbol and initial price. * @param symbol the ticker
     * symbol
     * 
     * @param initialPrice
     *            the starting price in cents
     */
    public Stock1L(String symbol, int initialPrice) {
        assert symbol != null
                && symbol.length() > 0 : "Violation of: symbol is not empty";
        assert initialPrice > 0 : "Violation of: initialPrice > 0";
        this.createNewRep(symbol, initialPrice);
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    public final Stock createNewRep() {
        // Since the interface requires an initial price, we use a default
        return new Stock1L("TEMP", 1);
    }

    @Override
    public final void clear() {
        this.createNewRep("TEMP", 1);
    }

    @Override
    public final void transferFrom(Stock source) {
        assert source != null : "Violation of: source is not null";
        assert source instanceof Stock1L : "Violation of: source is of type Stock1L";
        assert source != this : "Violation of: source is not this";

        Stock1L localSource = (Stock1L) source;
        this.ticker = localSource.ticker;
        this.history = localSource.history;

        localSource.createNewRep("TEMP", 1);
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void updatePrice(int price) {
        assert price > 0 : "Violation of: price > 0";
        this.history.add(price);
    }

    @Override
    public final int currentPrice() {
        return this.history.get(this.history.size() - 1);
    }

    @Override
    public final int priceAt(int timeStep) {
        assert 0 <= timeStep && timeStep < this.history
                .size() : "Violation of: 0 <= timeStep < |this.history|";
        return this.history.get(timeStep);
    }

    @Override
    public final int historySize() {
        return this.history.size();
    }

    @Override
    public final String tickerSymbol() {
        return this.ticker;
    }

    @Override
    public Stock newInstance() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException(
                "Unimplemented method 'newInstance'");
    }
}