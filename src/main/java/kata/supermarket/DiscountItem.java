package kata.supermarket;

import java.math.BigDecimal;

public interface DiscountItem extends Item {
    BigDecimal discount();
}
