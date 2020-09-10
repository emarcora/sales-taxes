package com.emarcora.service.components;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;

public class TaxRoundingUnitTest {

    private TaxRounding taxRounding;

    @BeforeEach
    void setup() {
        taxRounding = new TaxRounding();
        ReflectionTestUtils.setField(taxRounding, "roundScale", new BigDecimal("0.05"));
    }

    @Test
    void givenAValueWithDecimalsGreaterThanZero_whenRoundValue_thenReturnRoundUpToFive() {
        Assert.assertThat(taxRounding.roundValue(new BigDecimal("1.01")), Matchers.is(new BigDecimal("1.05")));
    }

    @Test
    void givenAValueWithDecimalsEqualsToZero_whenRoundValue_thenReturnTheSame() {
        Assert.assertThat(taxRounding.roundValue(new BigDecimal("1.00")), Matchers.is(new BigDecimal("1.00")));
    }

    @Test
    void givenAValueWithDecimalsGreaterThanFive_whenRoundValue_thenReturnRoundUpToTen() {
        Assert.assertThat(taxRounding.roundValue(new BigDecimal("1.06")), Matchers.is(new BigDecimal("1.10")));
    }

    @Test
    void givenAValueWithThreeDecimals_whenRoundValue_thenReturnRoundUpToTwoDecimals() {
        Assert.assertThat(taxRounding.roundValue(new BigDecimal("0.556")), Matchers.is(new BigDecimal("0.60")));
    }

}