package com.emarcora.service.components.taxes;

import com.emarcora.model.Item;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;

public class ImportTaxUnitTest {

    private ImportTax importTax;

    @BeforeEach
    void setup() {
        importTax = new ImportTax();
        ReflectionTestUtils.setField(importTax, "importTaxPct", new BigDecimal("0.05"));
    }

    @Test
    void givenANotImportedItem_whenComputingTaxes_thenReturn0() {
        Item item = new Item().price(new BigDecimal(12.49)).name("book").type(Item.TypeEnum.BOOK).imported(false).quantity(1);
        Assert.assertThat(importTax.applyTo(item), Matchers.is(BigDecimal.ZERO));
    }

    @Test
    void givenAnImportedItem_whenComputingTaxes_thenReturnProperTax() {
         Item item = new Item().price(new BigDecimal(10)).name("book").type(Item.TypeEnum.BOOK).imported(true).quantity(1);
        Assert.assertEquals(importTax.applyTo(item), new BigDecimal("0.50"));
    }

}