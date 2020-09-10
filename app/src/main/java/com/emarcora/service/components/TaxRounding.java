package com.emarcora.service.components;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@NoArgsConstructor
public class TaxRounding {
    @Value("${round.scale}")
    private BigDecimal roundScale;

    BigDecimal roundValue(BigDecimal value) {
        BigDecimal roundFactor = BigDecimal.ONE.divide(roundScale, 2, RoundingMode.CEILING);
        return value.multiply(roundFactor).setScale(0, RoundingMode.UP).divide(roundFactor, 2, RoundingMode.CEILING);
    }

}
