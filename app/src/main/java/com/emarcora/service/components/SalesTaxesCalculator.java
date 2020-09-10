package com.emarcora.service.components;

import com.emarcora.model.Item;
import com.emarcora.model.TaxesResponse;
import com.emarcora.service.components.taxes.Tax;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
@AllArgsConstructor
public class SalesTaxesCalculator {

    private TaxRounding taxRounding;
    List<Tax> taxes;

    public TaxesResponse computeTaxes(List<Item> items) {
        TaxesResponse response = new TaxesResponse();
        items.forEach(item -> updateResponseWithItem(item, response));
        return response;
    }


    private void updateResponseWithItem(Item item, TaxesResponse response) {
        BigDecimal taxesAmount = computeItemTaxes(item);

        response.addItemsItem(item.price(sum(item.getPrice(), taxesAmount)))
                .totalAmount(sum(response.getTotalAmount(), item.getPrice()))
                .salesTaxes(sum(response.getSalesTaxes(), taxesAmount));
    }

    private BigDecimal computeItemTaxes(Item item) {
        return taxRounding.roundValue(sum(taxes, t -> t.applyTo(item)));
    }

    private BigDecimal sum(BigDecimal value, @NonNull BigDecimal addValue) {
        return Optional.ofNullable(value).map(v -> v.add(addValue)).orElse(addValue);
    }

    private BigDecimal sum(List<Tax> taxes, Function<Tax, BigDecimal> mapper) {
        return taxes.stream()
                .map(mapper)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
