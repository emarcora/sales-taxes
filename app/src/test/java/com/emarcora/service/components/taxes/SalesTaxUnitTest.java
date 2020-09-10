package com.emarcora.service.components.taxes;

import com.emarcora.model.Item;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;

public class SalesTaxUnitTest {

    private SalesTax salesTax;

    @BeforeEach
    void setup() {
        salesTax = new SalesTax();
        ReflectionTestUtils.setField(salesTax, "salesTaxPct", new BigDecimal("0.10"));
    }

    @Test
    void givenABookItem_whenComputingTaxes_thenReturn0() {
        Item item = new Item().price(new BigDecimal(12.49)).name("book").type(Item.TypeEnum.BOOK).imported(false).quantity(1);
        Assert.assertThat(salesTax.applyTo(item), Matchers.is(BigDecimal.ZERO));
    }

    @Test
    void givenAFoodItem_whenComputingTaxes_thenReturn0() {
        Item item = new Item().price(new BigDecimal(12.49)).name("book").type(Item.TypeEnum.FOOD).imported(false).quantity(1);
        Assert.assertThat(salesTax.applyTo(item), Matchers.is(BigDecimal.ZERO));
    }

    @Test
    void givenAMedicalItem_whenComputingTaxes_thenReturn0() {
        Item item = new Item().price(new BigDecimal(12.49)).name("book").type(Item.TypeEnum.MEDICAL).imported(false).quantity(1);
        Assert.assertThat(salesTax.applyTo(item), Matchers.is(BigDecimal.ZERO));
    }

    @Test
    void givenAMusicCDItem_whenComputingTaxes_thenReturnProperTax() {
         Item item = new Item().price(new BigDecimal(10)).name("music CD").type(Item.TypeEnum.OTHER).imported(false).quantity(1);
        Assert.assertThat(salesTax.applyTo(item), Matchers.is(new BigDecimal("1.00")));
    }

}