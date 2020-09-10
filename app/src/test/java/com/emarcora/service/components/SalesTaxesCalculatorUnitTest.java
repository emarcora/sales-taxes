package com.emarcora.service.components;

import com.emarcora.model.Item;
import com.emarcora.model.TaxesResponse;
import com.emarcora.service.components.taxes.ImportTax;
import com.emarcora.service.components.taxes.SalesTax;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SalesTaxesCalculatorUnitTest {

    @Mock
    TaxRounding taxRounding;

    SalesTaxesCalculator salesTaxesCalculator;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        salesTaxesCalculator = new SalesTaxesCalculator(taxRounding, Arrays.asList(new SalesTax(), new ImportTax()));
    }

    @Test
    void givenAListOfItems_whenComputingTaxes_AllFieldsArePopulatedIntoResponse() {
        Mockito.when(taxRounding.roundValue(ArgumentMatchers.any())).thenReturn(new BigDecimal("0.00"));

        List<Item> items = Collections.singletonList(new Item().price(new BigDecimal("10")).imported(false).name("book").quantity(1).type(Item.TypeEnum.BOOK));

        TaxesResponse response = salesTaxesCalculator.computeTaxes(items);
        Assert.assertThat(response.getItems().size(), Matchers.is(1));
        Assert.assertThat(response.getItems().get(0).getPrice(), Matchers.is(new BigDecimal("10.00")));
        Assert.assertThat(response.getSalesTaxes(), Matchers.is(new BigDecimal("0.00")));
        Assert.assertThat(response.getTotalAmount(), Matchers.is(new BigDecimal("10.00")));
    }

}

