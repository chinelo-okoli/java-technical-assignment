package kata.supermarket;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BasketDiscountTest {

    @MethodSource
    @ParameterizedTest
    void basketProvidesTotalDiscountValue(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalDiscountValue() {
        return Stream.of(
                singleItemBuyOneGetOneFreeDiscount(),
                evenItemsBuyOneGetOneFreeDiscount(),
                oddItemsBuyOneGetOneFreeDiscount(),
                multipleItemsByWeightAndByUnit()
        );
    }

	private static Arguments multipleItemsByWeightAndByUnit() {
		return Arguments.of("multiple items by weight and by unit", "4.35", 
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), multiplePacksOfDigestives(4)));
	}

	private static Arguments oddItemsBuyOneGetOneFreeDiscount() {
		return Arguments.of("odd number of items priced with bogof discount", "4.65", Collections.singleton(multiplePacksOfDigestives(5)));
		
	}

	private static Arguments evenItemsBuyOneGetOneFreeDiscount() {
		return Arguments.of("even number of items priced with bogof discount", "3.10", Collections.singleton(multiplePacksOfDigestives(4)));
	}

	private static Arguments singleItemBuyOneGetOneFreeDiscount() {
		return Arguments.of("a single item priced with bogof discount", "0.49", Collections.singleton(aPintOfMilk()));
	}
    
    private static Item aPintOfMilk() {
        return new Product(new BigDecimal("0.49")).oneOf();
    }
    
    private static Item multiplePacksOfDigestives(long units) {
        return new Product(new BigDecimal("1.55")).unitsOf(units);
    }
    
    private static WeighedProduct aKiloOfAmericanSweets() {
        return new WeighedProduct(new BigDecimal("4.99"));
    }
    
    private static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
    }
    
}
