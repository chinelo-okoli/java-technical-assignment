package kata.supermarket;

import java.math.BigDecimal;

public class ItemByUnit implements Item {

    private final Product product;
    private final BigDecimal units;
    
    ItemByUnit(final Product product) {
        this.product = product;
        this.units = BigDecimal.ONE;
    }

    ItemByUnit(final Product product, long units) {
    	this.product = product;
    	this.units = BigDecimal.valueOf(units);
    }
    
    public BigDecimal price() {
        return product.pricePerUnit().multiply(units);
    }
}
