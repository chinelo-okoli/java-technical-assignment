package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ItemByUnit implements DiscountItem {

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

	@Override
	public BigDecimal discount() {
		final BigDecimal TWO = BigDecimal.valueOf(2);
		
		// is units even ?
		boolean isEven = this.units.remainder(TWO).equals(BigDecimal.ZERO);
		
		BigDecimal discount;
		
		// implement bogof discount by halving price for multiple even items
		// when unit is single product, there is no discount
		// when unit is multiple odd count then half off the even items only
		if (isEven) discount = this.price().divide(TWO).setScale(2, RoundingMode.HALF_UP);
		else if (this.units.equals(BigDecimal.ONE)) discount = BigDecimal.ZERO; // no discount on single unit
		else discount = this.price().subtract(this.product.pricePerUnit()).divide(TWO).setScale(2, RoundingMode.HALF_UP);
		
		return discount;
	}
}
