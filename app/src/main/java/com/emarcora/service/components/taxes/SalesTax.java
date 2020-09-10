package com.emarcora.service.components.taxes;


import com.emarcora.model.Item;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@Component
public class SalesTax implements Tax {

    private static final List<Item.TypeEnum> SPECIAL_ITEM_TYPES = Arrays.asList(Item.TypeEnum.BOOK, Item.TypeEnum.FOOD, Item.TypeEnum.MEDICAL);
    @Value("${sales.tax.pct}")
    private BigDecimal salesTaxPct;


    @Override
    public BigDecimal applyTo(Item item) {
        if(SPECIAL_ITEM_TYPES.stream().anyMatch(typeEnum -> typeEnum.equals(item.getType()))) {
            return BigDecimal.ZERO;
        }
        return item.getPrice().multiply(salesTaxPct).multiply(BigDecimal.valueOf(item.getQuantity()));
    }
}
