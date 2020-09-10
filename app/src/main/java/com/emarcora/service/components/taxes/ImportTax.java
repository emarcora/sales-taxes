package com.emarcora.service.components.taxes;


import com.emarcora.model.Item;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@NoArgsConstructor
@Component
public class ImportTax implements Tax{

    @Value("${import.tax.pct}")
    private BigDecimal importTaxPct;

    @Override
    public BigDecimal applyTo(Item item) {
         if (item.getImported()) {
            return item.getPrice().multiply(importTaxPct).multiply(BigDecimal.valueOf(item.getQuantity()));
        }
        return BigDecimal.ZERO;
    }
}
